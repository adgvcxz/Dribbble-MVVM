package com.adgvcxz.adgble.model;

import android.databinding.BaseObservable;
import android.view.View;

import com.adgvcxz.adgble.util.ThemeUtil;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/14.
 */

public class DrawerMenuViewModel extends BaseObservable {

    public void onClickSetting(View view) {
        ThemeUtil.theme.set(ThemeUtil.theme.get() == ThemeUtil.Day ? ThemeUtil.Night : ThemeUtil.Day);
//        RxBus.getDefault().post(new RxBusChangeTheme(ThemeUtil.sTheme == ThemeUtil.Day ? ThemeUtil.Night : ThemeUtil.Day));
//        if (ThemeUtil.sTheme == ThemeUtil.Night) {
//            ThemeUtil.sTheme = ThemeUtil.Day;
//        } else {
//            ThemeUtil.sTheme = ThemeUtil.Night;
//        }
//        Log.e("zhaow", "========" + ThemeUtil.sTheme);
    }

}
