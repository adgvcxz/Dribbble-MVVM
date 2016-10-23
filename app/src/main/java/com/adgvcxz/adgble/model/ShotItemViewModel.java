package com.adgvcxz.adgble.model;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/23.
 */

public class ShotItemViewModel extends BaseObservable {

    public final ObservableField<String> imageUrl = new ObservableField<>();
    public final ObservableField<String> thumbnail = new ObservableField<>();
    public final ObservableField<String> avatar = new ObservableField<>();

    @Override
    public String toString() {
        return "imageUrl" + imageUrl.get();
    }
}
