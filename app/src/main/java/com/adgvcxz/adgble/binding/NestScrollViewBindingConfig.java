package com.adgvcxz.adgble.binding;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.support.v4.widget.NestedScrollView;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/25.
 */

public class NestScrollViewBindingConfig {

    @InverseBindingAdapter(attribute = "scrollY", event = "scrollYAttrChanged")
    public static int setScrollY(NestedScrollView nestedScrollView) {
        return nestedScrollView.getScrollY();
    }

    @BindingAdapter({"scrollYAttrChanged"})
    public static void setScrollYListener(NestedScrollView nestedScrollView, InverseBindingListener scrollYAttrChanged) {
        if (scrollYAttrChanged != null) {
            nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    scrollYAttrChanged.onChange();
                }
            });
        }
    }

    @BindingAdapter({"scrollY"})
    public static void setScrollY(NestedScrollView nestedScrollView, int scrollY) {
        if (scrollY != nestedScrollView.getScrollY()) {
            nestedScrollView.setScrollY(scrollY);
        }
    }
}
