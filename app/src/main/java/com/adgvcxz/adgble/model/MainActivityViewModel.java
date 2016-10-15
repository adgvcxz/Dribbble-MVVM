package com.adgvcxz.adgble.model;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/14.
 */

public class MainActivityViewModel extends BaseThemeViewModel {

    public final ObservableBoolean changeMode = new ObservableBoolean(false);

    @BindingAdapter({"appTheme"})
    public static void changeTheme(FrameLayout layout, int theme) {
        ImageView imageView = new ImageView(layout.getContext());
        layout.addView(imageView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        layout.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(layout.getDrawingCache());
        imageView.setImageBitmap(bitmap);
        imageView.animate().alpha(0).setDuration(350).start();
        Observable.timer(400, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            layout.removeView(imageView);
        });
    }
}
