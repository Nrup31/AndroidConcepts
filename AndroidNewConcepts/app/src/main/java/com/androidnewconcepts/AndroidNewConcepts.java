package com.androidnewconcepts;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by indianic on 05/01/16.
 */
public class AndroidNewConcepts extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
