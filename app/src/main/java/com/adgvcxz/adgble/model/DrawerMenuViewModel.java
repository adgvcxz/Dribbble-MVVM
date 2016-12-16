package com.adgvcxz.adgble.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.util.ThemeUtil;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/14.
 */

public class DrawerMenuViewModel extends BaseFragmentViewModel {

    public void onClickSetting(View view) {
        ThemeUtil.theme.set(ThemeUtil.theme.get() == ThemeUtil.Day ? ThemeUtil.Night : ThemeUtil.Day);
    }

    public static final Parcelable.Creator<DrawerMenuViewModel> CREATOR = new Creator<DrawerMenuViewModel>() {
        @Override
        public DrawerMenuViewModel createFromParcel(Parcel parcel) {
            return new DrawerMenuViewModel();
        }

        @Override
        public DrawerMenuViewModel[] newArray(int i) {
            return new DrawerMenuViewModel[i];
        }
    };


    @Override
    public int layoutId() {
        return R.layout.fragment_drawer_menu;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
