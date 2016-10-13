package com.adgvcxz.adgble.model;

import android.databinding.ObservableBoolean;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/11.
 */

public class BaseRecyclerViewModel extends BaseViewModel {

    public ObservableBoolean isLoadAll = new ObservableBoolean(false);
    public ObservableBoolean loadMore = new ObservableBoolean(true);
    private ObservableBoolean loading;

    /**
     * 实现加载更多
     */
    protected RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (recyclerView.getAdapter() != null && isLoadAll.get()) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastPosition = linearLayoutManager.findLastVisibleItemPosition();
                int count = linearLayoutManager.getItemCount();
                if (!loading.get() && (count == lastPosition + 1)) {
                    loading.set(true);
//                    loading. = true;
//                    if (mOnLoadMoreListener != null && mAdapter.isLoadSuccess()) {
//                        mOnLoadMoreListener.loadMore();
//                    }
                }
            }
        }
    };

    public void loadMore(){}
}
