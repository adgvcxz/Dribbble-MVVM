package com.adgvcxz.adgble.binding;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.adgvcxz.adgble.adapter.BaseRecyclerViewAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/11.
 */

public class BindingConfig {

    @BindingAdapter(value = {"imageUrl", "thumbnail"}, requireAll = false)
    public static void loadImage(SimpleDraweeView simpleDraweeView, String imageUrl, String thumbnail) {
        ImageDecodeOptions decodeOptions = ImageDecodeOptions.newBuilder().setDecodePreviewFrame(true).build();
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageUrl))
                .setImageDecodeOptions(decodeOptions)
                .setProgressiveRenderingEnabled(true)
                .build();
        ImageRequest lowRequest = null;
        if (!TextUtils.isEmpty(thumbnail)) {
            lowRequest = ImageRequest.fromUri(thumbnail);
        }
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setLowResImageRequest(lowRequest)
                .setOldController(simpleDraweeView.getController())
                .setAutoPlayAnimations(true)
                .build();

        simpleDraweeView.setController(draweeController);
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
        Observable.just(recyclerView.getAdapter()).ofType(BaseRecyclerViewAdapter.class)
                .filter(adapter -> adapter.isLoadMore() != loadMore || adapter.isLoadAll() != isLoadAll)
                .subscribe(adapter -> {
                    adapter.setLoadMore(loadMore);
                    adapter.setLoadAll(isLoadAll);
                });
    }

    @BindingAdapter({"onItemClickListener"})
    public static void setOnItemClickListener(RecyclerView recyclerView, OnRecyclerViewItemClickListener listener) {
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                view.setOnClickListener(v -> {
                    RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(view);
                    listener.onClick(recyclerView, holder.getAdapterPosition(), view);
                });
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {

            }
        });
    }

    @BindingConversion
    public static ItemViewSelector toItemViewSelector(ItemView itemView) {
        return new SingleItemViewSelector(itemView);
    }

    public static ItemViewSelector toItemViewSelector(List<ItemView> itemViews) {
        return new MutliItemViewSelector(itemViews);
    }


}
