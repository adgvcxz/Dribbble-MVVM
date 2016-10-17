package com.adgvcxz.adgble.model;

import android.databinding.ObservableBoolean;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/11.
 */

public class BaseRecyclerViewModel extends BaseViewModel {

    public final ObservableBoolean isLoadAll = new ObservableBoolean(false);
    public final ObservableBoolean loadMore = new ObservableBoolean(true);
    public final ObservableBoolean loadSuccess = new ObservableBoolean(true);
    boolean loading = false;
    int page = 0;

    public BaseRecyclerViewModel() {
        loadData(page);
    }

    /**
     * 实现加载更多
     */
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
                        loadData(page);
                    }
                }
            }
        }
    };


    public void loadData(int page){
        loading = true;
    }
}
