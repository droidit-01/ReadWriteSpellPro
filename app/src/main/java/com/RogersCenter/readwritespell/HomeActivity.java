package com.RogersCenter.readwritespell;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.RogersCenter.readwritespell.tutorials.TutorialsHomeActivity;
import com.android.vending.expansion.zipfile.APKExpansionSupport;
import com.android.vending.expansion.zipfile.ZipResourceFile;
import com.google.android.vending.expansion.downloader.DownloadProgressInfo;
import com.google.android.vending.expansion.downloader.DownloaderClientMarshaller;
import com.google.android.vending.expansion.downloader.DownloaderServiceMarshaller;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.google.android.vending.expansion.downloader.IDownloaderService;
import com.google.android.vending.expansion.downloader.IStub;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements IDownloaderClient {
    public static final String FILENAME = "main." + BuildConfig.VERSION_CODE + "." + BuildConfig.APPLICATION_ID + ".obb";
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1337;
    public static ZipResourceFile.ZipEntryRO[] descriptor;
    public static List<String> VIDEOS = new ArrayList<>();
    public static ZipResourceFile expansionFile;
    TextView tvHomeTitle;
    TextView tvHomeExercise;
    TextView tvHomeTutorial, tv_progress, tv_loading;
    View view, view2;
    TextView tvHomeExit;
    MediaPlayer mPlayer2;
    ProgressBar progress_home;
    private IDownloaderService remoteService;
    private IStub downloaderClientStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        progress_home = findViewById(R.id.progress_home);
        tvHomeTitle = findViewById(R.id.tv_home_title);
        tvHomeExercise = findViewById(R.id.tv_home_exercise);
        tvHomeTutorial = findViewById(R.id.tv_home_tutorial);
        view = findViewById(R.id.view);
        view2 = findViewById(R.id.view2);
        tvHomeExit = findViewById(R.id.tv_home_exit);
        tv_progress = findViewById(R.id.tv_progress);
        tv_loading = findViewById(R.id.tv_loading);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //here access obb folder
        File obbDir = getObbDir();
        File obbFile = new File(obbDir, FILENAME);
        if (obbFile.exists()) {
            checkForVideo(obbFile, HomeActivity.this);
        } else {
            downloadObbFile(obbFile);
            checkForVideo(obbFile, HomeActivity.this);
        }
        try {
            // Check if expansion files are available before going any further
            if (!expansionFilesDelivered()) {
                // Build an Intent to start this activity from the Notification
                Intent notifierIntent = new Intent(this, HomeActivity.class);
                notifierIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);

                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                        notifierIntent, PendingIntent.FLAG_UPDATE_CURRENT  | PendingIntent.FLAG_IMMUTABLE );

                // Start the download service (if required)
                int startResult =
                        DownloaderClientMarshaller.startDownloadServiceIfRequired(this,
                                pendingIntent, SampleDownloaderService.class);
                // If download has started, initialize this activity to show
                // download progress
                if (startResult != DownloaderClientMarshaller.NO_DOWNLOAD_REQUIRED) {
                    // This is where you do set up to display the download
                    // progress (next step)
                    downloaderClientStub = DownloaderClientMarshaller.CreateStub(this,
                            SampleDownloaderService.class);




                    return;
                } // If the download wasn't necessary, fall through to start the app
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mPlayer2 = MediaPlayer.create(HomeActivity.this, R.raw.opening2);
        mPlayer2.start();
        mPlayer2.setLooping(true);

        if (StaticValues.GetPreferences("FLOW", HomeActivity.this).equalsIgnoreCase("RECOMMENDED")) {
            String LASTOPENCLASS = StaticValues.GetPreferences("Last_Open_Lesson", HomeActivity.this);
            String LASTOPENParent = StaticValues.GetPreferences("Last_Open_ParentLesson", HomeActivity.this);
            if (LASTOPENCLASS != null && LASTOPENParent != null) {
                stopmusic();
                if (LASTOPENParent.equalsIgnoreCase("Tutorials"))
                {
                    tvHomeTutorial.startAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.image_click));
                    Intent i2 = new Intent(HomeActivity.this, TutorialsHomeActivity.class);
                    startActivity(i2);
                }
                else {
                    Intent i = new Intent(HomeActivity.this, ChaptersActivity.class);
                    startActivity(i);
                }

            }

        }

        tvHomeExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopmusic();
                tvHomeExercise.startAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.image_click));
                Intent i1 = new Intent(HomeActivity.this, ChaptersActivity.class);
                startActivity(i1);
            }
        });
        tvHomeTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopmusic();
                tvHomeTutorial.startAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.image_click));
                Intent i2 = new Intent(HomeActivity.this, TutorialsHomeActivity.class);
                startActivity(i2);
            }
        });
        tvHomeExit.setOnClickListener(view -> {
            stopmusic();
            finishAffinity();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPlayer2 != null)
            if (mPlayer2.isPlaying()) {
                mPlayer2.stop();
            }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPlayer2 != null) {
            if (!mPlayer2.isPlaying()) {
                mPlayer2.start();
            }
        }

    }

    public void stopmusic() {
        if (mPlayer2 != null) {
            if (mPlayer2.isPlaying()) {
                mPlayer2.stop();
            }
        }
    }

//    private void downloadObbFile(File obbFile) {
//        if (null != downloaderClientStub) {
//            tv_progress.setVisibility(View.VISIBLE);
//            progress_home.setVisibility(View.VISIBLE);
//            downloaderClientStub.connect(this);
//        } else {
//            tv_loading.setVisibility(View.GONE);
//        }
//        Log.w("MainActivity", "DOWNLOAD!");
//    }

    private void downloadObbFile(File obbFile) {
        if (null != downloaderClientStub) {
            tv_progress.setVisibility(View.VISIBLE);
            progress_home.setVisibility(View.VISIBLE);
            downloaderClientStub.connect(this);
        } else {
            tv_loading.setVisibility(View.GONE);
        }
        Log.w("MainActivity", "DOWNLOAD EXPANSION FILE!");

        // Get the app's private external storage directory
        File privateStorageDir = getExternalFilesDir(null);

        // Create a new file in the private storage directory for the expansion file
        File privateObbFile = new File(privateStorageDir, FILENAME);

        // Now you can use privateObbFile for downloading the expansion file
        // ...

        // Example:
        // downloaderClientStub.downloaderService.getAlarmReceiver().requestDownloadStatus();

        // Remember to update the other parts of your code that use obbFile
        // with privateObbFile instead
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    File obbDir = getObbDir();
                    File obbFile = new File(obbDir, FILENAME);

                    if (obbFile.exists()) {
                        checkForVideo(obbFile, HomeActivity.this);
                    } else {
                        downloadObbFile(obbFile);
                        checkForVideo(obbFile, HomeActivity.this);
                    }

                    Toast.makeText(this, "PERMISSION ACCEPTED", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    //here I have to set data to list
    private void checkForVideo(File obb, Context ctx) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        } else {
            if (obb.exists()) {
                try {
                    expansionFile = APKExpansionSupport.getAPKExpansionZipFile(this, BuildConfig.VERSION_CODE, 0);
                    Log.d("Myfiles", "path " + obb.getPath() + "....");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                descriptor = expansionFile.getAllEntries();
                for (int i = 0; i < descriptor.length; i++) {
                    VIDEOS.add(descriptor[i].mFileName);
                    Log.d("Myfiles", "path " + descriptor[i].mFileName + "....");
                }
                if (descriptor.length > 0) {
//                    tv_progress.setVisibility(View.GONE);
                    tv_loading.setVisibility(View.GONE);
                    progress_home.setVisibility(View.GONE);
//                    tvHomeTitle,tvHomeExercise,tvHomeExercise
                    tvHomeTitle.setAlpha((float) 1);
                    tvHomeTitle.setEnabled(true);
                    tvHomeTitle.setClickable(true);
                    tvHomeExercise.setAlpha((float) 1);
                    tvHomeExercise.setEnabled(true);
                    tvHomeExercise.setClickable(true);
                    tvHomeTutorial.setAlpha((float) 1);
                    tvHomeTutorial.setEnabled(true);
                    tvHomeTutorial.setClickable(true);

                } else {
//                    tv_progress.setVisibility(View.VISIBLE);
                    tv_loading.setVisibility(View.VISIBLE);
                    progress_home.setVisibility(View.VISIBLE);
                    tvHomeTitle.setAlpha((float) 0.2);
                    tvHomeTitle.setEnabled(false);
                    tvHomeTitle.setClickable(false);
                    tvHomeExercise.setAlpha((float) 0.2);
                    tvHomeExercise.setEnabled(false);
                    tvHomeExercise.setClickable(false);
                    tvHomeTutorial.setAlpha((float) 0.2);
                    tvHomeTutorial.setEnabled(false);
                    tvHomeTutorial.setClickable(false);
                }
            } else {
                downloadObbFile(obb);
            }
        }
    }

    private boolean expansionFilesDelivered() {
        File obbDir = getObbDir();
        File obbFile = new File(obbDir, FILENAME);
        return obbFile.exists();
    }

    @Override
    protected void onStart() {
        if (null != downloaderClientStub) {
            tv_progress.setVisibility(View.VISIBLE);
            progress_home.setVisibility(View.VISIBLE);
            downloaderClientStub.connect(this);
        } else {
            tv_loading.setVisibility(View.GONE);
        }
        super.onStart();
    }

    /**
     * Disconnect the stub from our service on stop
     */
    @Override
    protected void onStop() {
        if (null != downloaderClientStub) {
            downloaderClientStub.disconnect(this);
        }
        super.onStop();
    }

    @Override
    public void onServiceConnected(Messenger m) {
        remoteService = DownloaderServiceMarshaller.CreateProxy(m);
        remoteService.onClientUpdated(downloaderClientStub.getMessenger());

    }

    @Override
    public void onDownloadStateChanged(int newState) {

        switch (newState) {
            case IDownloaderClient.STATE_IDLE:
                tv_loading.setVisibility(View.VISIBLE);
                // STATE_IDLE means the service is listening, so it's
                // safe to start making calls via mRemoteService.
                Toast.makeText(HomeActivity.this, "IDLE", Toast.LENGTH_SHORT).show();
                tv_loading.setText("IDLE");
                break;
            case IDownloaderClient.STATE_CONNECTING:
                tv_loading.setVisibility(View.VISIBLE);
                Toast.makeText(HomeActivity.this, "CONNECTING", Toast.LENGTH_SHORT).show();
                tv_loading.setText("CONNECTING");
                break;
            case IDownloaderClient.STATE_FETCHING_URL:
                tv_loading.setVisibility(View.VISIBLE);
                Toast.makeText(HomeActivity.this, "FETCHING URL", Toast.LENGTH_SHORT).show();
                tv_loading.setText("FETCHING URL");
                break;
            case IDownloaderClient.STATE_DOWNLOADING:
                tv_loading.setVisibility(View.VISIBLE);
                Toast.makeText(HomeActivity.this, "DOWNLOADING", Toast.LENGTH_SHORT).show();
                tv_loading.setText("Downloading..");
                break;
            case IDownloaderClient.STATE_FAILED_CANCELED:
                tv_loading.setVisibility(View.VISIBLE);
                Toast.makeText(HomeActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
                tv_loading.setText("Canceled");
                break;
            case IDownloaderClient.STATE_FAILED:
                tv_loading.setVisibility(View.VISIBLE);
                Toast.makeText(HomeActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                tv_loading.setText("Failed");
                break;
            case IDownloaderClient.STATE_FAILED_FETCHING_URL:
                tv_loading.setVisibility(View.VISIBLE);
                Toast.makeText(HomeActivity.this, "Failed fetch", Toast.LENGTH_SHORT).show();
                tv_loading.setText("Failed fetch");
                break;
            case IDownloaderClient.STATE_FAILED_UNLICENSED:
                tv_loading.setVisibility(View.VISIBLE);
                Toast.makeText(HomeActivity.this, "Failed unlicensed", Toast.LENGTH_SHORT).show();
                tv_loading.setText("Failed unlicensed");
                break;
            case IDownloaderClient.STATE_PAUSED_NEED_CELLULAR_PERMISSION:
                tv_loading.setVisibility(View.VISIBLE);
                Toast.makeText(HomeActivity.this, "Need cellular permission", Toast.LENGTH_SHORT).show();
                tv_loading.setText("Need cellular permission");
                break;
            case IDownloaderClient.STATE_PAUSED_WIFI_DISABLED_NEED_CELLULAR_PERMISSION:
                tv_loading.setVisibility(View.VISIBLE);
                Toast.makeText(HomeActivity.this, "Wifi disabled need cellular permission", Toast.LENGTH_SHORT).show();
                tv_loading.setText("Wifi disabled need cellular permission");
                break;
            case IDownloaderClient.STATE_PAUSED_BY_REQUEST:
                Toast.makeText(HomeActivity.this, "Paused by request", Toast.LENGTH_SHORT).show();
                tv_loading.setVisibility(View.VISIBLE);
                tv_loading.setText("Paused by request");
                break;
            case IDownloaderClient.STATE_PAUSED_ROAMING:
                Toast.makeText(HomeActivity.this, "Paused roaming", Toast.LENGTH_SHORT).show();
                tv_loading.setVisibility(View.VISIBLE);
                tv_loading.setText("Paused roaming");
                break;
            case IDownloaderClient.STATE_PAUSED_SDCARD_UNAVAILABLE:
                Toast.makeText(HomeActivity.this, "SDCard unavailable", Toast.LENGTH_SHORT).show();
                tv_loading.setVisibility(View.VISIBLE);
                tv_loading.setText("SDCard unavailable");
                break;
            case IDownloaderClient.STATE_COMPLETED:
                Toast.makeText(HomeActivity.this, "State completed", Toast.LENGTH_SHORT).show();
                tv_loading.setText("State completed");
                tv_loading.setVisibility(View.GONE);
                return;
            default:
                tv_loading.setVisibility(View.GONE);
                Toast.makeText(HomeActivity.this, "Unknown state", Toast.LENGTH_SHORT).show();
                tv_loading.setText("Unknown state");
        }
    }

    @Override
    public void onDownloadProgress(DownloadProgressInfo progress) {
//        tv_progress.setVisibility(View.VISIBLE);
        progress_home.setVisibility(View.VISIBLE);
        double progressDouble = (double) progress.mOverallProgress / 1520625009d * 100d;
        int progressVal = (int) progressDouble;
        Log.w("DOWNLOAD", "PROGRESS " + progressDouble);
        Log.w("DOWNLOAD", "PROGRESS " + progressVal);
//        Toast.makeText(HomeActivity.this, progressVal + " %", Toast.LENGTH_LONG).show();
//        tv_progress.setText("" + progressVal);
        progress_home.setProgress(progressVal, true);
    }
}