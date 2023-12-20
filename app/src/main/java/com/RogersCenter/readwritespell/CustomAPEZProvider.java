package com.RogersCenter.readwritespell;

import android.net.Uri;

import com.android.vending.expansion.zipfile.APEZProvider;

import java.io.File;

public class CustomAPEZProvider extends APEZProvider {
    private static final String AUTHORITY = "com.RogersCenter.readwritespell.provider";

    public static Uri buildUri(String path) {

        String contentPath = "content://" + AUTHORITY +
                File.separator +
                path;

        return Uri.parse(contentPath);
    }

    @Override
    public String getAuthority() {
        return AUTHORITY;
    }
}
