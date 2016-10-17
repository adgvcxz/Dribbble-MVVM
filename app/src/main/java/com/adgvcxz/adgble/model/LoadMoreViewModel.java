package com.adgvcxz.adgble.model;

import android.databinding.ObservableBoolean;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/17.
 */

public class LoadMoreViewModel extends BaseViewModel {
    public final ObservableBoolean loading = new ObservableBoolean(false);
    public final ObservableBoolean loadSuccess = new ObservableBoolean(true);
}
