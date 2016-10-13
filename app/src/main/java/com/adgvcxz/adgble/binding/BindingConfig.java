package com.adgvcxz.adgble.binding;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;

import com.adgvcxz.adgble.adapter.BaseRecyclerViewAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/11.
 */

public class BindingConfig {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(SimpleDraweeView simpleDraweeView, String imageUrl) {
        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(imageUrl))
                //.setResizeOptions(new ResizeOptions(parent.getWidth(),150))
                .setProgressiveRenderingEnabled(true)
                .setLocalThumbnailPreviewsEnabled(true)
                .build();




        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setOldController(simpleDraweeView.getController())
                .setAutoPlayAnimations(false)
                .build();



//        holder.draweeView.setController(controller);
//
//
//
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setOldController(simpleDraweeView.getController())
//                .setUri(Uri.parse(imageUrl))
//                .setAutoPlayAnimations(true)
//                .build();
        simpleDraweeView.setController(controller);
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
//                    adapter.notifyDataSetChanged();
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
