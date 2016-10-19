package com.adgvcxz.adgble.binding;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

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
        Log.e("zhaow", refreshing + "    " + swipeRefreshLayout.isRefreshing());
        if (refreshing ^ swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(refreshing);
        }
    }

    @BindingAdapter({"topMargin"})
    public static void setTopMargin(SwipeRefreshLayout swipeRefreshLayout, int topMargin) {
        Log.e("zhaow",  "  setTopMargin  " + swipeRefreshLayout.isRefreshing());
        swipeRefreshLayout.setProgressViewOffset(false, topMargin / 2, topMargin * 3 / 2);
    }
}
