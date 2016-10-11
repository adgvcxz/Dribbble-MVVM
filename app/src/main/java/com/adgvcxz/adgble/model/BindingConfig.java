package com.adgvcxz.adgble.model;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/11.
 */

public class BindingConfig {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(SimpleDraweeView simpleDraweeView, String imageUrl) {
        simpleDraweeView.setImageURI(Uri.parse(imageUrl));
    }

    @BindingAdapter({"loadMoreListener"})
    public static void addOnLoadMoreListener(RecyclerView recyclerView, BaseRecyclerViewModel.OnLoadMoreListener loadMoreListener) {
        if (loadMoreListener != null) {

        }
    }
}
