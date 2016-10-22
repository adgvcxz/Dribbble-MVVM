package com.adgvcxz.adgble.binding;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.adapters.ListenerUtil;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.adgvcxz.adgble.R;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/22.
 */

public class DrawerLayoutBindingConfig {


    @InverseBindingAdapter(attribute = "isDrawerOpen", event = "isDrawerOpenAttrChanged")
    public static boolean isDrawerOpen(DrawerLayout drawerLayout) {
        return drawerLayout.isDrawerOpen(GravityCompat.START);
    }

    @BindingAdapter({"isDrawerOpenAttrChanged"})
    public static void setDrawerListener(DrawerLayout drawerLayout, InverseBindingListener isOpenDrawerAttrChanged) {
        DrawerLayout.DrawerListener newValue = new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                isOpenDrawerAttrChanged.onChange();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                isOpenDrawerAttrChanged.onChange();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        };
        DrawerLayout.DrawerListener oldValue = ListenerUtil.trackListener(drawerLayout, newValue, R.id.drawerListener);
        if (oldValue != null) {
            drawerLayout.removeDrawerListener(oldValue);
        }
        drawerLayout.addDrawerListener(newValue);
    }

    @BindingAdapter({"isDrawerOpen"})
    public static void setIsDrawerOpen(DrawerLayout drawerLayout, boolean isOpen) {
        if (isOpen ^ drawerLayout.isDrawerOpen(GravityCompat.START)) {
            if (isOpen) {
                drawerLayout.openDrawer(GravityCompat.START);
            } else {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        }
    }

}
