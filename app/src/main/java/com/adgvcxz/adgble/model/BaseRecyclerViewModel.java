package com.adgvcxz.adgble.model;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.adgvcxz.adgble.binding.OnRecyclerViewItemClickListener;

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
    public final LoadMoreViewModel loadMoreViewModel = new LoadMoreViewModel();
    private int page = 1;

    public BaseRecyclerViewModel() {
        loadData();
    }

    private void loadData() {
        loadMoreViewModel.loading.set(true);
        request(page).subscribe(ts -> {
            if (page == 0) {
                items.clear();
            }
            items.addAll(ts);
            loadMoreViewModel.loadSuccess.set(true);
            loadMoreViewModel.loading.set(false);
            page += 1;
        }, throwable -> {
            loadMoreViewModel.loadSuccess.set(false);
            loadMoreViewModel.loading.set(false);
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
                if (!loadMoreViewModel.loading.get() && (count == lastPosition + 1)) {
                    if (loadMoreViewModel.loadSuccess.get()) {
                        loadData();
                    }
                }
            }
        }
    };

    public final OnRecyclerViewItemClickListener onLoadMoreClickListener = (recyclerView, position, v) -> {
        if (!loadMoreViewModel.loading.get() && !loadMoreViewModel.loadSuccess.get()) {
            loadData();
        }
    };


    public abstract Observable<List<T>> request(int page);
}
