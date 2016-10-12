package com.adgvcxz.adgble.binding;

import android.databinding.BaseObservable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/12.
 */

public class BaseItemViewModel extends BaseObservable {

    public static final int TypeNormal = 1;
    public static final int TypeLoadMore = 2;

    public int viewType;

}
