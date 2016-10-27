package com.adgvcxz.adgble.binding;

import android.databinding.BindingAdapter;
import android.view.View;
import android.view.ViewGroup;

import rx.Observable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/25.
 */

public class ViewBindingConfig {

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
}
