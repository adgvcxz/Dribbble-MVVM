package com.adgvcxz.adgble.util;

import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.adgvcxz.adgble.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/15.
 */

public class ThemeUtil {

    public static final int Day = 0;
    public static final int Night = 1;

    public static int sTheme = Day;

    public static ObservableInt theme = new ObservableInt(sTheme);

    public static final List<Integer> sColorPrimary = new ArrayList<>(Arrays.asList(R.color.color_primary_light
            , R.color.color_primary_dark));

    public static final List<Integer> sColorPrimaryDark = new ArrayList<>(Arrays.asList(R.color.color_primary_status_bar_light
            , R.color.color_primary_status_bar_dark));

    public static final List<Integer> sColorMainBackground = new ArrayList<>(Arrays.asList(R.color.main_bg_light
            , R.color.main_bg_dark));

    public static final List<Integer> sColorCardBackground = new ArrayList<>(Arrays.asList(R.color.card_bg_light
            , R.color.card_bg_dark));

    public static final List<Integer> sColorPrimaryText = new ArrayList<>(Arrays.asList(R.color.primary_text_color_light
            , R.color.primary_text_color_dark));

    public static final List<Integer> sColorSecondText = new ArrayList<>(Arrays.asList(R.color.second_text_color_light
            , R.color.second_text_color_dark));

}
