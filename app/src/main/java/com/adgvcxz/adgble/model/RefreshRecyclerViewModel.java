package com.adgvcxz.adgble.model;

import android.databinding.ObservableBoolean;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/18.
 */

public abstract class RefreshRecyclerViewModel<T> extends BaseRecyclerViewModel<T> {

    public final ObservableBoolean refreshing = new ObservableBoolean(false);

    public final SwipeRefreshLayout.OnRefreshListener onRefreshListener = this::loadFirstData;

    void loadFirstData() {
        if (!refreshing.get()) {
            page = 1;
            refreshing.set(true);
            loadData();
        }
    }

    @Override
    void loadSuccess() {
        refreshing.set(false);
    }

    @Override
    void loadFailed() {
        refreshing.set(false);
    }
}
