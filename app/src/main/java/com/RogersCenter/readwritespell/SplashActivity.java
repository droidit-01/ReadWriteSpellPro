package com.RogersCenter.readwritespell;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    ImageView ivSplash;
    ProgressBar progressBar;
    TextView tvSplashVersion;
    MediaPlayer mPlayer2;
    private Float upload_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivSplash = findViewById(R.id.iv_splash);
        progressBar = findViewById(R.id.progressBar);
        tvSplashVersion = findViewById(R.id.tv_splash_version);
//        progressBar.setMax(100);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        Log.d("WINDow", "" + getWindow().getDecorView().getSystemUiVisibility());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mPlayer2 = MediaPlayer.create(SplashActivity.this, R.raw.opening1);
        mPlayer2.start();
        mPlayer2.setLooping(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, FlowSelectionActivity.class);
                startActivity(i);
                finish();
                if (mPlayer2 != null) {
                    if (mPlayer2.isPlaying()) {
                        mPlayer2.stop();
                    }
                }
            }
        }, 3000);

        try {
            PackageManager manager = getApplicationContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getApplicationContext().getPackageName(), 0);
            Float app_version = Float.valueOf(info.versionCode);
            Log.d("VERSION", "" + info.versionCode);
            tvSplashVersion.setText("Version " + info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPlayer2 != null) {
            if (mPlayer2.isPlaying()) {
                mPlayer2.stop();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPlayer2 != null) {
            if (mPlayer2.isPlaying()) {
                mPlayer2.stop();
            }
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
}