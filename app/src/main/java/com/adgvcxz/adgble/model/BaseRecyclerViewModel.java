package com.adgvcxz.adgble.model;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import rx.Observable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/11.
 */

public abstract class BaseRecyclerViewModel<T> extends BaseViewModel {

    public ObservableArrayList<T> items = new ObservableArrayList<>();
    public final ObservableBoolean isLoadAll = new ObservableBoolean(false);
    public final ObservableBoolean loadMore = new ObservableBoolean(true);
    public final ObservableBoolean loadSuccess = new ObservableBoolean(true);
    boolean loading = false;
    private int page = 0;

    public BaseRecyclerViewModel() {
        loadData();
    }

    private void loadData() {
        loading = true;
        request(page).subscribe(ts -> {
            if (page == 0) {
                items.clear();
            }
            items.addAll(ts);
            loadSuccess.set(true);
            loading = false;
            page += 1;
            if (page == 3) {
                isLoadAll.set(true);
            }
        }, throwable -> {
            loadSuccess.set(false);
            loading = false;
        });
    }

    public final RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (recyclerView.getAdapter() != null && !isLoadAll.get()) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastPosition = linearLayoutManager.findLastVisibleItemPosition();
                int count = linearLayoutManager.getItemCount();
                if (!loading && (count == lastPosition + 1)) {
                    if (loadSuccess.get()) {
                        loadData();
                    }
                }
            }
        }
    };


    public abstract Observable<List<T>> request(int page);
}
