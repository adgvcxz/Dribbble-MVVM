package com.adgvcxz.adgble.model;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.rxbus.RxBus;
import com.adgvcxz.adgble.rxbus.RxBusClickDrawerMenu;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/22.
 */

public class ShotsToolbarViewModel extends BaseObservable {

    public final ObservableInt position = new ObservableInt(0);
    public final int rightIcon = R.mipmap.ic_menu;
    public final View.OnClickListener listener = v -> RxBus.getDefault().post(new RxBusClickDrawerMenu());

    public final int menu = R.menu.fm_shots;
    public final Toolbar.OnMenuItemClickListener onMenuItemClickListener;

    public ShotsToolbarViewModel(Toolbar.OnMenuItemClickListener listener) {
        onMenuItemClickListener = listener;
    }
}
