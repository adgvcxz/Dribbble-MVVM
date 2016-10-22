package com.adgvcxz.adgble.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/22.
 */

public class ToolbarBindingConfig {

    @BindingAdapter({"navigationIcon", "navigationOnClickListener"})
    public static void setNavigation(Toolbar toolbar, int icon, View.OnClickListener listener) {
        toolbar.setNavigationIcon(icon);
        if (listener != null) {
            toolbar.setNavigationOnClickListener(listener);
        }
    }

    @BindingAdapter({"menu", "menuClickListener"})
    public static void setMenu(Toolbar toolbar, int res, Toolbar.OnMenuItemClickListener listener) {
        toolbar.inflateMenu(res);
        if (listener != null) {
            toolbar.setOnMenuItemClickListener(listener);
        }
    }
}
