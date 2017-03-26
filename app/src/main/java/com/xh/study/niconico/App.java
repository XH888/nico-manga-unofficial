package com.xh.study.niconico;

import android.app.Application;

/**
 * Created by xh on 1/25/17.
 */

public class App extends Application {

    public static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }
}
