package com.adgvcxz.adgble.model;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.adapter.ItemView;
import com.android.databinding.library.baseAdapters.BR;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/12.
 */

public class RecentShotsViewModel {

    public ObservableArrayList<RecentShotsItemViewModel> items = new ObservableArrayList<>();

    public final ItemView itemView = ItemView.of(BR.item, R.layout.item_record_shot);


    public static class RecentShotsItemViewModel extends BaseObservable {
        public final ObservableField<String> imageUrl = new ObservableField<>();

        @Override
        public String toString() {
            return "imageUrl" + imageUrl.get();
        }
    }
}
