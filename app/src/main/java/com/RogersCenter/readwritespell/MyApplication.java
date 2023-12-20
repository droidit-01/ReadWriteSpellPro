package com.RogersCenter.readwritespell;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Create the notification channel
        MainActivity.createNotificationChannel(this);
    }
}


