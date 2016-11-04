package com.adgvcxz.adgble.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Looper;

import com.adgvcxz.adgble.AdgbleApp;
import com.adgvcxz.adgble.R;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.request.ImageRequest;

import java.io.File;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/17.
 */

public class Util {

    public static final String APP = "dribbble";
    public static final String DATA = "DATA";

    public static final String ShotImage = "Translate:ShotImage";
    public static final String UserAvatar = "Translate:UserAvatar";

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

    public static int getActionBarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        int height = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        return height;
    }

    public static int dpToPx(Context context, int dp) {
        return (int) (context.getResources().getDisplayMetrics().density * dp);
    }

    public static int getSize(int resId) {
        return AdgbleApp.getContext().getResources().getDimensionPixelSize(resId);
    }

    public static boolean isImageDownloaded(Context context, Uri loadUri) {
        if (loadUri == null) {
            return false;
        }
        CacheKey cacheKey = DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(ImageRequest.fromUri(loadUri), context);
        return ImagePipelineFactory.getInstance().getMainFileCache().hasKey(cacheKey) || ImagePipelineFactory.getInstance().getSmallImageFileCache().hasKey(cacheKey);
    }

    //return file or null
    public static File getCachedImageOnDisk(Context context, Uri loadUri) {
        File localFile = null;
        if (loadUri != null) {
            CacheKey cacheKey = DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(ImageRequest.fromUri(loadUri), context);
            if (ImagePipelineFactory.getInstance().getMainFileCache().hasKey(cacheKey)) {
                BinaryResource resource = ImagePipelineFactory.getInstance().getMainFileCache().getResource(cacheKey);
                localFile = ((FileBinaryResource) resource).getFile();
            } else if (ImagePipelineFactory.getInstance().getSmallImageFileCache().hasKey(cacheKey)) {
                BinaryResource resource = ImagePipelineFactory.getInstance().getSmallImageFileCache().getResource(cacheKey);
                localFile = ((FileBinaryResource) resource).getFile();
            }
        }
        return localFile;
    }
}
