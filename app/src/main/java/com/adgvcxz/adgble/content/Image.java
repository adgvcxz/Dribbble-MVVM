package com.adgvcxz.adgble.content;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/30.
 */

public class Image extends BaseObservable implements Parcelable {


    private String hidpi;
    private String normal;
    private String teaser;

    public Image(){}

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    private Image(Parcel in) {
        this.hidpi = in.readString();
        this.normal = in.readString();
        this.teaser = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.hidpi);
        parcel.writeString(this.normal);
        parcel.writeString(this.teaser);
    }


    @Bindable
    public String getHidpi() {
        return hidpi != null ? hidpi : normal != null ? normal : teaser;
    }

    public void setHidpi(String hidpi) {
        this.hidpi = hidpi;
    }

    @Bindable
    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    @Bindable
    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }
}
