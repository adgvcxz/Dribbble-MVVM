package com.adgvcxz.adgble.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/23.
 */

public class ShotItemViewModel extends BaseObservable {

    public String imageUrl = "";
    public String thumbnail = "";
    public String avatar = "";

    @Override
    public String toString() {
        return "imageUrl" + imageUrl;
    }

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Bindable
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Bindable
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
