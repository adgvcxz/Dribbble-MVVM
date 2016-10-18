package com.adgvcxz.adgble.binding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EdgeEffect;

import com.adgvcxz.adgble.R;

import java.lang.reflect.Field;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/18.
 */

public class SwipeRefreshLayoutBindingConfig {


    @BindingAdapter(value = {"onRefreshListener", "refreshingAttrChanged"}, requireAll = false)
    public static void setOnRefreshListener(SwipeRefreshLayout swipeRefreshLayout, SwipeRefreshLayout.OnRefreshListener listener, InverseBindingListener inverseBindingListener) {
        if (listener != null) {
            swipeRefreshLayout.setOnRefreshListener(listener);
        }
    }

    @BindingAdapter({"refreshing"})
    public static void setRefreshing(SwipeRefreshLayout swipeRefreshLayout, boolean refreshing) {
//        ChangeEdgeEffect(swipeRefreshLayout.getContext(), swipeRefreshLayout, ContextCompat.getColor(swipeRefreshLayout.getContext(), R.color.color_primary_teal));
        if (refreshing ^ swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(refreshing);
        }
    }
}
