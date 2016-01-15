package com.chuyao.xbo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by chuyao on 16-1-14.
 */
public class XboApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
