package com.adgvcxz.adgble.util;

import android.content.Context;
import android.os.Looper;

import com.adgvcxz.adgble.R;

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

    public static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        } else {
            return context.getResources().getDimensionPixelSize(R.dimen.status_bar_height);
        }
    }
}
