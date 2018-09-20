package com.ragentek.application;

import android.app.Application;

import com.ragentek.database.SPManager;

/**
 * Created by Jun.wang on 2018/9/13.
 */

public class TranslateApplication extends Application {
    private static TranslateApplication mTranslateApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mTranslateApplication = this;
    }

    public static TranslateApplication getInstance() {
        return mTranslateApplication;
    }
}
