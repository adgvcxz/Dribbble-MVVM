package com.adgvcxz.adgble.binding;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.support.design.widget.TabLayout;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/20.
 */

public class TabLayoutBindingConfig {

    @InverseBindingAdapter(attribute = "position", event = "positionAttrChanged")
    public static int getPosition(TabLayout tabLayout) {
        return tabLayout.getSelectedTabPosition();
    }

    @BindingAdapter({"positionAttrChanged"})
    public static void setPositionListener(TabLayout tabLayout, InverseBindingListener positionAttrChanged) {
        if (positionAttrChanged != null) {
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    positionAttrChanged.onChange();
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
    }

    @BindingAdapter("position")
    public static void setPosition(TabLayout tabLayout, int position) {
        if (position != tabLayout.getSelectedTabPosition()) {
            TabLayout.Tab tab = tabLayout.getTabAt(position);
            if (tab != null) {
                tab.select();
            }
        }
    }

}
