package com.adgvcxz.adgble.binding;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.databinding.DataBindingUtil;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ListenerUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.adapter.BaseRecyclerViewAdapter;
import com.adgvcxz.adgble.adapter.TopMarginSelector;
import com.adgvcxz.adgble.databinding.ItemLoadMoreBinding;

import java.util.List;

import io.reactivex.Observable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/11.
 */

public class RecyclerViewBindingConfig {

    @BindingAdapter({"layoutManager"})
    public static void setLayoutManager(RecyclerView recyclerView, LayoutManager.LayoutManagerFactory factory) {
        recyclerView.setLayoutManager(factory.create(recyclerView));
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"itemView", "items", "adapter", "topMarginSelector"}, requireAll = false)
    public static <T> void setAdapter(RecyclerView recyclerView, ItemViewSelector<T> itemView, List<T> items, BaseRecyclerViewAdapter adapter, TopMarginSelector topMargin) {
        if (itemView == null) {
            throw new IllegalArgumentException("itemView must not be null");
        }

        RecyclerView.Adapter oldAdapter = recyclerView.getAdapter();
        adapter.setTopMargin(topMargin);
        if (oldAdapter == null) {
            adapter.setItems(items);
            adapter.setItemView(itemView);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setItems(items);
        }
    }

    @BindingAdapter(value = {"loadMore", "isLoadAll"}, requireAll = false)
    public static void setLoadMore(RecyclerView recyclerView, boolean loadMore, boolean isLoadAll) {
        Observable.just(recyclerView.getAdapter()).ofType(BaseRecyclerViewAdapter.class)
                .filter(adapter -> adapter.isLoadMore() != loadMore || adapter.isLoadAll() != isLoadAll)
                .subscribe(adapter -> {
                    adapter.setLoadMore(loadMore);
                    adapter.setLoadAll(isLoadAll);
                });
    }

    @BindingAdapter(value = {"onItemClickListener", "onClickLoadMore"}, requireAll = false)
    public static void setOnItemClickListener(RecyclerView recyclerView, OnRecyclerViewItemClickListener listener, OnRecyclerViewItemClickListener loadMoreListener) {
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                view.setOnClickListener(v -> {
                    RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(view);
                    ViewDataBinding binding = DataBindingUtil.getBinding(holder.itemView);
                    if (!(binding instanceof ItemLoadMoreBinding)) {
                        if (listener != null) {
                            listener.onClick(recyclerView, holder.getAdapterPosition(), view);
                        }
                    } else {
                        if (loadMoreListener != null) {
                            loadMoreListener.onClick(recyclerView, holder.getAdapterPosition(), view);
                        }
                    }
                });
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                
            }
        });
    }

    @BindingAdapter({"onScrollListener"})
    public static void setOnScrollListener(RecyclerView recyclerView, RecyclerView.OnScrollListener listener) {
        RecyclerView.OnScrollListener oldValue = ListenerUtil.trackListener(recyclerView, listener, R.id.onScrollListener);
        if (oldValue != null) {
            recyclerView.removeOnScrollListener(oldValue);
        }
        if (listener != null) {
            recyclerView.addOnScrollListener(listener);
        }
    }

    @BindingConversion
    public static ItemViewSelector toItemViewSelector(ItemView itemView) {
        return new SingleItemViewSelector(itemView);
    }

//    public static ItemViewSelector toItemViewSelector(List<ItemView> itemViews) {
//        return new MutliItemViewSelector(itemViews);
//    }


    @InverseBindingAdapter(attribute = "firstItemTop", event = "firstItemTopAttrChanged")
    public static int getFirstItemTop(RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int position = manager.findFirstVisibleItemPosition();
            if (position == 0) {
                return recyclerView.getChildAt(0).getTop();
            }
        }
        return -1;
    }

    @BindingAdapter({"firstItemTopAttrChanged"})
    public static void setFirstItemTopListener(RecyclerView recyclerView, InverseBindingListener firstItemTopAttrChanged) {
        if (firstItemTopAttrChanged != null) {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int position = manager.findFirstVisibleItemPosition();
                    if (position == 0) {
                        firstItemTopAttrChanged.onChange();
                    }
                }
            });
        }
    }

    @BindingAdapter({"firstItemTop"})
    public static void setFirstItemTop(RecyclerView recyclerView, int firstItemTop) {
//        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
//            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//            if (recyclerView.getChildAt(0) != null) {
//                if (recyclerView.getChildAt(0).getTop() != firstItemTop) {
//                    manager.scrollToPositionWithOffset(0, firstItemTop);
//                }
//            }
//        }
    }
}
