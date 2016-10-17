package com.adgvcxz.adgble.util;

import android.os.Looper;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/17.
 */

public class Util {
    public static void ensureChangeOnMainThread() {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new IllegalStateException("You must only modify the ObservableList on the main thread.");
        }
    }
}
