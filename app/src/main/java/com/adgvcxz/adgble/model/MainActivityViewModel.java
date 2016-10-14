package com.adgvcxz.adgble.model;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/14.
 */

public class MainActivityViewModel extends BaseObservable {
    public final ObservableBoolean changeMode = new ObservableBoolean(false);
}
