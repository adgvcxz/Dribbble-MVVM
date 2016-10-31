package com.adgvcxz.adgble.util;

import android.databinding.ObservableField;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/31.
 */

public class ObservableString extends ObservableField<String> implements Parcelable {

    public ObservableString(String str) {
        super(str);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(get());
    }

    public static final Parcelable.Creator<ObservableString> CREATOR
            = new Parcelable.Creator<ObservableString>() {

        @Override
        public ObservableString createFromParcel(Parcel parcel) {
            return new ObservableString(parcel.readString());
        }

        @Override
        public ObservableString[] newArray(int size) {
            return new ObservableString[size];
        }
    };
}
