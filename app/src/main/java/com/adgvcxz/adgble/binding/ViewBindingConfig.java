package com.adgvcxz.adgble.binding;

import android.databinding.BindingAdapter;
import android.view.View;

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
}
