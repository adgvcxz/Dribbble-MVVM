package com.adgvcxz.adgble.binding;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.util.ThemeUtil;
import com.adgvcxz.adgble.util.Util;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/17.
 */

public class ThemeBindingConfig {

    @BindingAdapter(value = {"osVersion", "appTheme"}, requireAll = false)
    public static void setOsVersion(LinearLayout linearLayout, int osVersion, int theme) {
        Observable.just(osVersion).filter(integer -> osVersion > Build.VERSION_CODES.KITKAT).map(integer -> {
            View view = linearLayout.findViewById(R.id.statusBarId);
            if (view == null) {
                view = new View(linearLayout.getContext());
                view.setId(R.id.statusBarId);
                linearLayout.addView(view, 0, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Util.getStatusBarHeight(linearLayout.getContext())));
            }
            return view;
        }).subscribe(view -> {
            colorPrimaryStatusBg(view, theme);
        });
    }

    @BindingAdapter({"themeAnim"})
    public static void changeTheme(FrameLayout layout, int theme) {
        Observable.just(theme).filter(theme1 -> theme1 != ThemeUtil.sTheme).map(theme1 -> {
            ImageView imageView = (ImageView) layout.findViewById(R.id.themeImage);
            if (imageView == null) {
                imageView = new ImageView(layout.getContext());
                layout.addView(imageView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setOnTouchListener((v, event) -> true);
            }
            layout.setDrawingCacheEnabled(true);
            imageView.setImageBitmap(Bitmap.createBitmap(layout.getDrawingCache()));
            layout.setDrawingCacheEnabled(false);
            imageView.animate().alpha(0).setDuration(350).start();
            return imageView;
        }).delay(400, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(imageView -> {
            layout.removeView(imageView);
            ThemeUtil.sTheme = theme;
            System.gc();
            Runtime.getRuntime().gc();
        });
    }

    @BindingAdapter({"colorPrimaryBg"})
    public static void colorPrimaryBg(View view, int theme) {
        switch (theme) {
            case ThemeUtil.Night:
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.color_primary_teal));
                break;
            case ThemeUtil.Day:
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.color_primary_light));
                break;
        }
    }

    @BindingAdapter({"colorPrimaryStatusBg"})
    public static void colorPrimaryStatusBg(View view, int theme) {
        switch (theme) {
            case ThemeUtil.Night:
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.color_primary_status_bar_teal));
                break;
            case ThemeUtil.Day:
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.color_primary_status_bar_light));
                break;
        }
    }

    @BindingAdapter({"navigationBg"})
    public static void colorNavigationBg(NavigationView navigationView, int theme) {
        switch (theme) {
            case ThemeUtil.Night:
                navigationView.setBackgroundColor(ContextCompat.getColor(navigationView.getContext(), R.color.navigation_bg_teal));
                break;
            case ThemeUtil.Day:
                navigationView.setBackgroundColor(ContextCompat.getColor(navigationView.getContext(), R.color.navigation_bg_light));
                break;
        }
    }
}
