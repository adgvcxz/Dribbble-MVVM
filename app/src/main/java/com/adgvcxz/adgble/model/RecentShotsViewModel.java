package com.adgvcxz.adgble.model;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.binding.BaseItemViewModel;
import com.adgvcxz.adgble.binding.ItemView;
import com.android.databinding.library.baseAdapters.BR;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/12.
 */

public class RecentShotsViewModel extends BaseRecyclerViewModel {

    public ObservableArrayList<RecentShotsItemViewModel> items = new ObservableArrayList<>();

    public final ItemView itemView = ItemView.of(BR.item, R.layout.item_record_shot);


    public static class RecentShotsItemViewModel extends BaseItemViewModel {
        public final ObservableField<String> imageUrl = new ObservableField<>();

        @Override
        public String toString() {
            return "imageUrl" + imageUrl.get();
        }
    }
}
