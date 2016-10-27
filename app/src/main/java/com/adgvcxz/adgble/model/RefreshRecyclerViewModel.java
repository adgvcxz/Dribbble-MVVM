package com.adgvcxz.adgble.model;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/18.
 */

public abstract class RefreshRecyclerViewModel<T> extends BaseRecyclerViewModel<T> {

    public final ObservableBoolean refreshing = new ObservableBoolean(false);
    public final SwipeRefreshLayout.OnRefreshListener onRefreshListener = this::loadFirstData;
    public final ObservableInt topMargin = new ObservableInt();

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
