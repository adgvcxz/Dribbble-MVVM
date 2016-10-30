package com.adgvcxz.adgble.util;

import android.databinding.ObservableInt;

import com.adgvcxz.adgble.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/15.
 */

public class ThemeUtil {

    public static final int Night = 0;
    public static final int Day = 1;

    public static int sTheme = Night;

    public static ObservableInt theme = new ObservableInt(sTheme);

    public static final List<Integer> sColorPrimary = new ArrayList<>(Arrays.asList(R.color.color_primary_dark,
            R.color.color_primary_light));

    public static final List<Integer> sColorPrimaryDark = new ArrayList<>(Arrays.asList(R.color.color_primary_status_bar_dark
            , R.color.color_primary_status_bar_light));

    public static final List<Integer> sColorMainBackground = new ArrayList<>(Arrays.asList(R.color.main_bg_dark
            , R.color.main_bg_light));

    public static final List<Integer> sColorSecondBackground = new ArrayList<>(Arrays.asList(R.color.second_bg_dark
            , R.color.second_bg_light));

    public static final List<Integer> sColorCardBackground = new ArrayList<>(Arrays.asList(R.color.card_bg_dark
            , R.color.card_bg_light));

    public static final List<Integer> sColorPrimaryText = new ArrayList<>(Arrays.asList(R.color.primary_text_color_dark
            , R.color.primary_text_color_light));

    public static final List<Integer> sColorSecondText = new ArrayList<>(Arrays.asList(R.color.second_text_color_dark
            , R.color.second_text_color_light));

    public static final List<Integer> sColorDivider = new ArrayList<>(Arrays.asList(R.color.divider_color_dark
            , R.color.divider_color_light));

}
