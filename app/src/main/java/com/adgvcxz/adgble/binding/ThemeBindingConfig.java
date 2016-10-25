package com.adgvcxz.adgble.binding;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.util.ThemeUtil;
import com.adgvcxz.adgble.util.Util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

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

    @BindingAdapter(value = {"osVersionMarginTop"}, requireAll = false)
    public static void setStatusBarMarginTop(View view, int osVersion) {
        Observable.just(osVersion).filter(integer -> osVersion > Build.VERSION_CODES.KITKAT)
                .map(integer -> view.getLayoutParams())
                .ofType(ViewGroup.MarginLayoutParams.class)
                .subscribe(marginLayoutParams -> {
                    marginLayoutParams.topMargin = Util.getStatusBarHeight(view.getContext());
                    view.setLayoutParams(marginLayoutParams);
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

    @BindingAdapter({"colorPrimaryTheme"})
    public static void setColorPrimary(View view, int theme) {
        int color = ContextCompat.getColor(view.getContext(), ThemeUtil.sColorPrimary.get(theme));
        if (view instanceof RecyclerView) {
            setEdgeGlowColor((RecyclerView) view, color);
        } else if (view instanceof SwipeRefreshLayout) {
            ((SwipeRefreshLayout) view).setColorSchemeColors(color);
        } else {
            view.setBackgroundColor(color);
        }
    }

    @BindingAdapter({"mainBackgroundTheme"})
    public static void setMainBackground(View view, int theme) {
        int color = ContextCompat.getColor(view.getContext(), ThemeUtil.sColorMainBackground.get(theme));
        view.setBackgroundColor(color);
    }

    @BindingAdapter({"progressBarColorTheme"})
    public static void setProgressBarColor(ProgressBar progressBar, int theme) {
        int color = ContextCompat.getColor(progressBar.getContext(), ThemeUtil.sColorMainBackground.get(theme));
        progressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
    }

    @BindingAdapter({"colorPrimaryStatusBg"})
    public static void colorPrimaryStatusBg(View view, int theme) {
        int color = ContextCompat.getColor(view.getContext(), ThemeUtil.sColorPrimaryDark.get(theme));
        view.setBackgroundColor(color);
    }

    @BindingAdapter({"cardColorTheme"})
    public static void setCardColorTheme(View view, int theme) {
        int color = ContextCompat.getColor(view.getContext(), ThemeUtil.sColorCardBackground.get(theme));
        view.setBackgroundColor(color);
    }

    @BindingAdapter({"textPrimaryColor"})
    public static void setPrimaryTextColor(TextView textView, int theme) {
        int color = ContextCompat.getColor(textView.getContext(), ThemeUtil.sColorPrimaryText.get(theme));
        textView.setTextColor(color);
    }

    @BindingAdapter({"textSecondColor"})
    public static void setSecondTextColor(TextView textView, int theme) {
        int color = ContextCompat.getColor(textView.getContext(), ThemeUtil.sColorSecondText.get(theme));
        textView.setTextColor(color);
    }


    public static void setEdgeGlowColor(final RecyclerView recyclerView, final int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                final Class<?> clazz = RecyclerView.class;
                for (final String name : new String[]{"ensureTopGlow", "ensureBottomGlow"}) {
                    Method method = clazz.getDeclaredMethod(name);
                    method.setAccessible(true);
                    method.invoke(recyclerView);
                }
                for (final String name : new String[]{"mTopGlow", "mBottomGlow"}) {
                    final Field field = clazz.getDeclaredField(name);
                    field.setAccessible(true);
                    final Object edge = field.get(recyclerView); // android.support.v4.widget.EdgeEffectCompat
                    final Field fEdgeEffect = edge.getClass().getDeclaredField("mEdgeEffect");
                    fEdgeEffect.setAccessible(true);
                    ((EdgeEffect) fEdgeEffect.get(edge)).setColor(color);
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }
}
