package com.android.vending.expansion.zipfile;

/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//To implement APEZProvider in your application, you'll want to change
//the AUTHORITY to match what you define in the manifest.

import com.android.vending.expansion.zipfile.ZipResourceFile.ZipEntryRO;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.BaseColumns;


import java.io.IOException;
import java.util.ArrayList;

/**
 * This content provider is an optional part of the library.
 * 
 * <p>Most apps don't need to use this class. This defines a
 * ContentProvider that marshalls the data from the ZIP files through a
 * content provider Uri in order to provide file access for certain Android APIs 
 * that expect Uri access to media files.
 *
 */
public abstract class APEZProvider extends ContentProvider {	

	public static final String FILEID = BaseColumns._ID;
	public static final String FILENAME = "ZPFN";
	public static final String ZIPFILE = "ZFIL";
	public static final String MODIFICATION = "ZMOD";
	public static final String CRC32 = "ZCRC";
	public static final String COMPRESSEDLEN = "ZCOL";
	public static final String UNCOMPRESSEDLEN = "ZUNL";
	public static final String COMPRESSIONTYPE = "ZTYP";
	public static final String[] ALL_FIELDS = {
		FILEID,
		FILENAME,
		ZIPFILE,
		MODIFICATION,
		CRC32,
		COMPRESSEDLEN,
		UNCOMPRESSEDLEN,
		COMPRESSIONTYPE
	};
	public static final int FILEID_IDX = 0;
	public static final int FILENAME_IDX = 1;
	public static final int ZIPFILE_IDX = 2;
	public static final int MOD_IDX = 3;
	public static final int CRC_IDX = 4;
	public static final int COMPLEN_IDX = 5;
	public static final int UNCOMPLEN_IDX = 6;
	public static final int COMPTYPE_IDX = 7;
	public static final int[] ALL_FIELDS_INT = {
		FILEID_IDX,
		FILENAME_IDX,
		ZIPFILE_IDX,
		MOD_IDX,
		CRC_IDX,
		COMPLEN_IDX,
		UNCOMPLEN_IDX,
		COMPTYPE_IDX
	};
	static private final String NO_FILE = "N";
	private ZipResourceFile mAPKExtensionFile;
	private boolean mInit;
	
	/**
	 * This needs to match the authority in your manifest
	 */
	public abstract String getAuthority();

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		return "vnd.android.cursor.item/asset";
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void initIfNecessary() {
	    if ( !mInit ) {
            Context ctx = getContext();
			assert ctx != null;
			PackageManager pm = ctx.getPackageManager();
            ProviderInfo pi = pm.resolveContentProvider(getAuthority(), PackageManager.GET_META_DATA);
            PackageInfo packInfo;
            try {
                packInfo = pm.getPackageInfo(ctx.getPackageName(), 0);
            } catch (NameNotFoundException e1) {
                e1.printStackTrace();
                return;
            }
            int patchFileVersion;
            int mainFileVersion;
            int appVersionCode = packInfo.baseRevisionCode;
            String[] resourceFiles = null;
			assert pi != null;
			if ( null != pi.metaData ) {
                mainFileVersion = pi.metaData.getInt("mainVersion", appVersionCode);
                patchFileVersion = pi.metaData.getInt("patchVersion", appVersionCode);
                String mainFileName = pi.metaData.getString("mainFilename", NO_FILE);
                if (!NO_FILE.equals(mainFileName)) {
                    String patchFileName = pi.metaData.getString("patchFilename", NO_FILE);
                    if (!NO_FILE.equals(patchFileName)) {
                        resourceFiles = new String[] { mainFileName, patchFileName };
                    } else {
                        resourceFiles = new String[] { mainFileName };
                    }
                }
            } else {
                mainFileVersion = patchFileVersion = appVersionCode;
            }
            try {
                if ( null == resourceFiles ) {
                    mAPKExtensionFile = APKExpansionSupport.getAPKExpansionZipFile(ctx, mainFileVersion, patchFileVersion);
                } else {
                    mAPKExtensionFile = APKExpansionSupport.getResourceZipFile(resourceFiles);
                }
                mInit = true;
			} catch (IOException e) {
                e.printStackTrace();                
            }
	    }
	}

	@Override
	public boolean onCreate() {
	    return true;
	}

	@Override
	public AssetFileDescriptor openAssetFile(Uri uri, String mode) {
        initIfNecessary();
		String path = uri.getEncodedPath();
		assert path != null;
		if ( path.startsWith("/") ) {
			path = path.substring(1);
		}
		return mAPKExtensionFile.getAssetFileDescriptor(path);		
	}



	@Override
	public ContentProviderResult[] applyBatch(
			ArrayList<ContentProviderOperation> operations)
			throws OperationApplicationException {
        initIfNecessary();
		return super.applyBatch(operations);
	}

	@Override
	public ParcelFileDescriptor openFile(Uri uri, String mode) {
        initIfNecessary();
		try{
			AssetFileDescriptor af = openAssetFile(uri, mode);
			if ( null != af ) {
				return af.getParcelFileDescriptor();
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
	    initIfNecessary();
		// lists all of the items in the file that match
		ZipEntryRO[] zipEntries;
		if ( null == mAPKExtensionFile ) {
			zipEntries = new ZipEntryRO[0];
		} else {
			zipEntries = mAPKExtensionFile.getAllEntries();
		}
		int[] intProjection;
		if ( null == projection )  {
			intProjection = ALL_FIELDS_INT;
			projection = ALL_FIELDS;
		} else {
			int len = projection.length;
			intProjection = new int[len];
			for ( int i = 0; i < len; i++ ) {
				switch (projection[i]) {
					case FILEID:
						intProjection[i] = FILEID_IDX;
						break;
					case FILENAME:
						intProjection[i] = FILENAME_IDX;
						break;
					case ZIPFILE:
						intProjection[i] = ZIPFILE_IDX;
						break;
					case MODIFICATION:
						intProjection[i] = MOD_IDX;
						break;
					case CRC32:
						intProjection[i] = CRC_IDX;
						break;
					case COMPRESSEDLEN:
						intProjection[i] = COMPLEN_IDX;
						break;
					case UNCOMPRESSEDLEN:
						intProjection[i] = UNCOMPLEN_IDX;
						break;
					case COMPRESSIONTYPE:
						intProjection[i] = COMPTYPE_IDX;
						break;
					default:
						throw new RuntimeException();
				}
			}
		}
		MatrixCursor mc = new MatrixCursor(projection, zipEntries.length);
		int len = intProjection.length;
		for ( ZipEntryRO zer : zipEntries ) {
			MatrixCursor.RowBuilder rb = mc.newRow();
			for ( int i = 0; i < len; i++ ) {				
				switch (intProjection[i]) {
					case FILEID_IDX:
						rb.add(i);
						break;
					case FILENAME_IDX:
						rb.add(zer.mFileName);
						break;
					case ZIPFILE_IDX:
						rb.add(zer.getZipFileName());
						break;
					case MOD_IDX:
						rb.add(zer.mWhenModified);
						break;
					case CRC_IDX:
						rb.add(zer.mCRC32);
						break;
					case COMPLEN_IDX:
						rb.add(zer.mCompressedLength);
						break;
					case UNCOMPLEN_IDX:
						rb.add(zer.mUncompressedLength);
						break;
					case COMPTYPE_IDX:
						rb.add(zer.mMethod);
						break;
				}
			}
		}
		return mc;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
