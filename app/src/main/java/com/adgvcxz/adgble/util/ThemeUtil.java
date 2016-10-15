package com.adgvcxz.adgble.util;

import android.databinding.BindingAdapter;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.adgvcxz.adgble.R;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/15.
 */

public class ThemeUtil {

    public static final int Day = 1;
    public static final int Night = 2;

    public static int sTheme = Day;

    @BindingAdapter({"colorPrimaryBg"})
    public static void colorPrimaryBg(View view, int theme) {
        switch (theme) {
            case Night:
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.color_primary_teal));
                break;
            case Day:
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.color_primary_light));
                break;
        }
    }

    @BindingAdapter({"colorPrimaryStatusBg"})
    public static void colorPrimaryStatusBg(View view, int theme) {
        switch (theme) {
            case Night:
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.color_primary_status_bar_teal));
                break;
            case Day:
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.color_primary_status_bar_light));
                break;
        }
    }
    @BindingAdapter({"navigationBg"})
    public static void colorNavigationBg(NavigationView navigationView, int theme) {
        switch (theme) {
            case Night:
                navigationView.setBackgroundColor(ContextCompat.getColor(navigationView.getContext(), R.color.navigation_bg_teal));
                break;
            case Day:
                navigationView.setBackgroundColor(ContextCompat.getColor(navigationView.getContext(), R.color.navigation_bg_light));
                break;
        }
    }
}
