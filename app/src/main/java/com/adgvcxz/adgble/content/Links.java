package com.adgvcxz.adgble.content;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/10.
 */

public class Links implements Parcelable {


    public String web;
    public String twitter;

    public Links(){}

    public static final Creator<Links> CREATOR = new Creator<Links>() {
        public Links createFromParcel(Parcel source) {
            return new Links(source);
        }

        public Links[] newArray(int size) {
            return new Links[size];
        }
    };

    private Links(Parcel in) {
        this.web = in.readString();
        this.twitter = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(web);
        dest.writeString(twitter);
    }
}
