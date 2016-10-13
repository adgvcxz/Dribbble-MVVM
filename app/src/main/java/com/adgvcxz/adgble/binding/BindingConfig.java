package com.adgvcxz.adgble.binding;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;

import com.adgvcxz.adgble.adapter.BaseRecyclerViewAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/11.
 */

public class BindingConfig {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(SimpleDraweeView simpleDraweeView, String imageUrl) {
        simpleDraweeView.setImageURI(Uri.parse(imageUrl));
    }

    @BindingAdapter({"layoutManager"})
    public static void setLayoutManager(RecyclerView recyclerView, LayoutManager.LayoutManagerFactory factory) {
        recyclerView.setLayoutManager(factory.create(recyclerView));
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter({"itemView", "items", "adapter"})
    public static <T> void setAdapter(RecyclerView recyclerView, ItemViewSelector<T> itemView, ArrayList<T> items, BaseRecyclerViewAdapter adapter) {
        if (itemView == null) {
            throw new IllegalArgumentException("itemView must not be null");
        }

        RecyclerView.Adapter oldAdapter = recyclerView.getAdapter();
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
        if (loadMore) {

        }
    }

    @BindingConversion
    public static ItemViewSelector toItemViewSelector(ItemView itemView) {
        return new SingleItemViewSelector(itemView);
    }

    public static ItemViewSelector toItemViewSelector(List<ItemView> itemViews) {
        return new MutliItemViewSelector(itemViews);
    }


}
