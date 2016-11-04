package com.adgvcxz.adgble.binding;

import android.app.Activity;
import android.databinding.BindingAdapter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.util.RxUtil;
import com.adgvcxz.adgble.util.Util;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/22.
 */

public class ToolbarBindingConfig {

    @BindingAdapter({"navigationIcon", "navigationOnClickListener"})
    public static void setNavigation(Toolbar toolbar, int icon, View.OnClickListener listener) {
        toolbar.setNavigationIcon(icon);
        if (listener != null) {
            toolbar.setNavigationOnClickListener(listener);
        }
    }

    @BindingAdapter({"menu", "menuClickListener"})
    public static void setMenu(Toolbar toolbar, int res, Toolbar.OnMenuItemClickListener listener) {
        toolbar.inflateMenu(res);
        if (listener != null) {
            toolbar.setOnMenuItemClickListener(listener);
        }
    }

    @BindingAdapter({"backPaletteUrl", "navigationOnClickListener"})
    public static void setBackPaletteIcon(Toolbar toolbar, String url, View.OnClickListener listener) {
        Observable.just(url).filter(s -> !TextUtils.isEmpty(url))
                .flatMap(s -> {
                        if (Util.isImageDownloaded(toolbar.getContext(), Uri.parse(s))) {
                            return Observable.just(BitmapFactory.decodeFile(Util.getCachedImageOnDisk(toolbar.getContext(), Uri.parse(url)).getAbsolutePath()))
                                    .flatMap(bitmap -> RxUtil.toObservablePalette(bitmap, Color.parseColor("#424242")));
                        }
                        return Observable.just(Color.parseColor("#424242"));
                }).subscribeOn(Schedulers.io())
                .map(integer -> {
                    Drawable drawable = ContextCompat.getDrawable(toolbar.getContext(), R.mipmap.ic_back_arrow);
                    drawable.setColorFilter(integer, PorterDuff.Mode.SRC_ATOP);
                    return drawable;
                }).subscribeOn(AndroidSchedulers.mainThread())
                .map(drawable -> {
                    toolbar.setNavigationIcon(drawable);
                    return toolbar.getContext();
                }).delay(300, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
                .subscribe(context -> {
                    ViewCompat.animate(toolbar).alpha(1f).setDuration(200).start();
                    toolbar.setNavigationOnClickListener(listener);
                });
    }
}
