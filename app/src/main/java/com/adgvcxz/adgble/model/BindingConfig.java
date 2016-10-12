package com.adgvcxz.adgble.model;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.adgvcxz.adgble.adapter.BaseRecyclerViewAdapter;
import com.adgvcxz.adgble.adapter.ItemView;
import com.adgvcxz.adgble.util.LayoutManager;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

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
    @BindingAdapter({"itemView", "items"})
    public static <T> void setAdapter(RecyclerView recyclerView, ItemView itemView, ArrayList<T> items) {
        if (itemView == null) {
            throw new IllegalArgumentException("itemView must not be null");
        }

        BaseRecyclerViewAdapter<T> adapter = (BaseRecyclerViewAdapter<T>) recyclerView.getAdapter();
        if (adapter == null) {
            adapter = new BaseRecyclerViewAdapter();
            adapter.setValues(items);
            adapter.setItemView(itemView);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setValues(items);
        }
    }
}
