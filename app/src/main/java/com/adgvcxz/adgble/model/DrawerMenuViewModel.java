package com.adgvcxz.adgble.model;

import android.databinding.BaseObservable;
import android.view.View;

import com.adgvcxz.adgble.adapter.OnCreateViewListener;
import com.adgvcxz.adgble.util.ThemeUtil;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/14.
 */

public class DrawerMenuViewModel extends BaseObservable implements OnCreateViewListener {

    public void onClickSetting(View view) {
        ThemeUtil.theme.set(ThemeUtil.theme.get() == ThemeUtil.Day ? ThemeUtil.Night : ThemeUtil.Day);
    }

    @Override
    public void onCreateView() {

    }
}
