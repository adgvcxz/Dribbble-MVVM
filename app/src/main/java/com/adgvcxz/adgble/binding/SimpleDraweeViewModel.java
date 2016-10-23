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

public class SimpleDraweeViewModel {

    @BindingAdapter(value = {"imageUrl", "thumbnail"}, requireAll = false)
    public static void loadImage(SimpleDraweeView simpleDraweeView, String imageUrl, String thumbnail) {
        ImageDecodeOptions decodeOptions = ImageDecodeOptions.newBuilder().setDecodePreviewFrame(true).build();
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageUrl))
                .setImageDecodeOptions(decodeOptions)
                .setResizeOptions(new ResizeOptions(240, 240))
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

}
