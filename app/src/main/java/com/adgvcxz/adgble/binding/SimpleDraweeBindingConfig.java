package com.adgvcxz.adgble.binding;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/23.
 */

public class SimpleDraweeBindingConfig {

    @BindingAdapter(value = {"imageUrl", "thumbnail", "maxSize"}, requireAll = false)
    public static void loadImage(SimpleDraweeView simpleDraweeView, String imageUrl, String thumbnail, int maxSize) {
        ImageDecodeOptions decodeOptions = ImageDecodeOptions.newBuilder().setDecodePreviewFrame(true).build();
        ImageRequestBuilder requestBuilder = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageUrl))
                .setImageDecodeOptions(decodeOptions)
                .setProgressiveRenderingEnabled(true);
        if (maxSize != 0) {
            int size = simpleDraweeView.getContext().getResources().getDimensionPixelSize(maxSize);
            if (size != 0) {
                requestBuilder.setResizeOptions(new ResizeOptions(size, size));
            } else {
                requestBuilder.setResizeOptions(new ResizeOptions(maxSize, maxSize));
            }
        }
        ImageRequest lowRequest = null;
        if (!TextUtils.isEmpty(thumbnail)) {
            lowRequest = ImageRequest.fromUri(thumbnail);
        }
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(requestBuilder.build())
                .setLowResImageRequest(lowRequest)
                .setOldController(simpleDraweeView.getController())
                .setAutoPlayAnimations(true)
                .build();

        simpleDraweeView.setController(draweeController);
    }

}
