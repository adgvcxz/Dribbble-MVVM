package com.adgvcxz.adgble.model;

import android.databinding.BaseObservable;
import android.view.View;

import com.adgvcxz.adgble.rxbus.RxBus;
import com.adgvcxz.adgble.rxbus.RxBusChangeTheme;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/14.
 */

public class DrawerMenuViewModel extends BaseObservable {

    public void onClickSetting(View view) {
        RxBus.getDefault().post(new RxBusChangeTheme(1));
    }

}
