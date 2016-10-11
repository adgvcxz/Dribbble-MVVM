package com.adgvcxz.adgble.model;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/11.
 */

public class BaseRecyclerViewModel extends BaseViewModel {

    private boolean isLoadAll;
    private boolean loading;

    /**
     * 实现加载更多
     */
    protected RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (recyclerView.getAdapter() != null && isLoadAll) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastPosition = linearLayoutManager.findLastVisibleItemPosition();
                int count = linearLayoutManager.getItemCount();
                if (!loading && (count == lastPosition + 1)) {
                    loading = true;
//                    if (mOnLoadMoreListener != null && mAdapter.isLoadSuccess()) {
//                        mOnLoadMoreListener.loadMore();
//                    }
                }
            }
        }
    };

    public void loadMore(){}
}
