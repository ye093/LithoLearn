package com.yejy.litholearn;

import android.app.Application;

import com.facebook.soloader.SoLoader;

/**
 * @author YeJY
 * @version 1.0
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, false);
    }
}
