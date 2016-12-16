package com.adgvcxz.adgble.model;

import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * zhaowei
 * Created by zhaowei on 2016/11/2.
 */

public abstract class BaseFragmentViewModel extends BaseViewModel implements Parcelable {

    public void onCreateView(View view) {

    }

    public void onDestroyView() {

    }

    @LayoutRes
    public abstract int layoutId();

}
