package com.adgvcxz.adgble.binding;

import android.databinding.BindingAdapter;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;

import com.adgvcxz.adgble.util.Util;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import rx.Observable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/25.
 */

public class ViewBindingConfig {

    @BindingAdapter({"statusBarHeight"})
    public static void setLayoutHeight(View view, int version) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (version > Build.VERSION_CODES.KITKAT) {
            lp.height = Util.getStatusBarHeight(view.getContext());
        } else {
            lp.height = 0;
        }
        view.setLayoutParams(lp);
    }

    @BindingAdapter({"android:translationY"})
    public static void setTranslationY(View view, int translationY) {
        if (view.getTranslationY() != translationY) {
            view.setTranslationY(translationY);
        }
    }

    @BindingAdapter({"android:layout_marginLeft"})
    public static void setMarginLeft(View view, int left) {
        Observable.just(view.getLayoutParams()).ofType(ViewGroup.MarginLayoutParams.class)
                .filter(marginLayoutParams -> marginLayoutParams.leftMargin != left).subscribe(marginLayoutParams -> {
            marginLayoutParams.leftMargin = left;
            view.setLayoutParams(marginLayoutParams);
        });
    }

    @BindingAdapter({"android:layout_marginRight"})
    public static void setMarginRight(View view, int right) {
        Observable.just(view.getLayoutParams()).ofType(ViewGroup.MarginLayoutParams.class)
                .filter(marginLayoutParams -> marginLayoutParams.rightMargin != right).subscribe(marginLayoutParams -> {
            marginLayoutParams.rightMargin = right;
            view.setLayoutParams(marginLayoutParams);
        });
    }

    @BindingAdapter({"android:layout_marginBottom"})
    public static void setMarginBottom(View view, int bottom) {
        Observable.just(view.getLayoutParams()).ofType(ViewGroup.MarginLayoutParams.class)
                .filter(marginLayoutParams -> marginLayoutParams.bottomMargin != bottom).subscribe(marginLayoutParams -> {
            marginLayoutParams.bottomMargin = bottom;
            view.setLayoutParams(marginLayoutParams);
        });
    }

    @BindingAdapter({"android:elevation"})
    public static void setElevation(View view, int elevation) {
        Observable.just(elevation).filter(integer -> Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT).subscribe(integer -> {
            ViewCompat.setElevation(view, elevation);
        });
    }

    @BindingAdapter({"html"})
    public static void displayHtml(HtmlTextView view, @Nullable String html) {
        if (html != null) {
            view.setHtml(html, new HtmlResImageGetter(view));
        }
    }
}
