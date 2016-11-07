package com.adgvcxz.adgble;

import com.facebook.stetho.Stetho;

/**
 * zhaowei
 * Created by zhaowei on 2016/11/7.
 */

public class DebugApplication extends AdgbleApp {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(this);

        // enable chrome dev tools
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this));

        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(this));

        Stetho.Initializer initializer = initializerBuilder.build();

        Stetho.initialize(initializer);
    }
}
