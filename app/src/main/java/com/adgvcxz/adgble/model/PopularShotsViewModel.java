package com.adgvcxz.adgble.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.adapter.TopMarginSelector;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/23.
 */

public class PopularShotsViewModel extends ShotsListViewModel {

    public PopularShotsViewModel(TopMarginSelector selector) {
        super(selector);
    }

    @Override
    String getSorts() {
        return "";
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_shots_list;
    }

    public static final Parcelable.Creator<ShotsListViewModel> CREATOR = new Creator<ShotsListViewModel>() {
        @Override
        public ShotsListViewModel createFromParcel(Parcel parcel) {
            return new PopularShotsViewModel(parcel);
        }

        @Override
        public ShotsListViewModel[] newArray(int i) {
            return new ShotsListViewModel[i];
        }
    };

    public PopularShotsViewModel(Parcel parcel) {
        super(parcel);
    }

}
