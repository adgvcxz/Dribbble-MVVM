package com.adgvcxz.adgble;

import android.app.Application;
import android.support.v4.graphics.ColorUtils;

import com.adgvcxz.adgble.api.RetrofitSingleton;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/10.
 */

public class AdgbleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
