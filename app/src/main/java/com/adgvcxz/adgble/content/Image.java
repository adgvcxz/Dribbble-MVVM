package com.adgvcxz.adgble.content;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/30.
 */

public class Image implements Parcelable {


    public String hidpi;
    public String normal;
    public String teaser;

    public Image() {
    }

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
}
