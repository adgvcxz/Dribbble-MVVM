package com.adgvcxz.adgble.model;

import android.databinding.ObservableBoolean;

import com.adgvcxz.adgble.rxbus.RxBus;
import com.adgvcxz.adgble.rxbus.RxBusClickDrawerMenu;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/14.
 */

public class MainActivityViewModel extends BaseViewModel {
    public final ObservableBoolean isDrawerOpen = new ObservableBoolean(false);

    public MainActivityViewModel() {
        RxBus.getDefault().toObservable(RxBusClickDrawerMenu.class).filter(rxBusClickDrawerMenu -> !isDrawerOpen.get())
                .subscribe(rxBusClickDrawerMenu -> {
                    isDrawerOpen.set(true);
                });
    }

}
