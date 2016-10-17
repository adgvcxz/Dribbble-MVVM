package com.adgvcxz.adgble.model;

import android.databinding.ObservableInt;

import com.adgvcxz.adgble.util.ThemeUtil;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/15.
 */

public class BaseThemeViewModel extends BaseViewModel {
    public final ObservableInt theme = new ObservableInt(ThemeUtil.sTheme);
}
