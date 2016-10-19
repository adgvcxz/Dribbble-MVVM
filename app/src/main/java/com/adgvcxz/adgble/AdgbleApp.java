package com.adgvcxz.adgble;

import android.app.Application;
import android.content.Context;

import com.adgvcxz.adgble.api.OkHttpUtil;
import com.adgvcxz.adgble.util.FontsOverride;
import com.adgvcxz.adgble.util.IMMLeaks;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/10.
 */

public class AdgbleApp extends Application {

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        IMMLeaks.fixFocusedViewLeak(this);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        refWatcher = LeakCanary.install(this);
        FontsOverride.setDefaultFont(this, "DEFAULT", "hobostd.otf");
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory.newBuilder(this, OkHttpUtil.generateOkHttpClient().build())
                .setDownsampleEnabled(true).build();
        Fresco.initialize(this, config);
    }

    public static RefWatcher getRefWatcher(Context context) {
        AdgbleApp application = (AdgbleApp) context.getApplicationContext();
        return application.refWatcher;
    }
}
