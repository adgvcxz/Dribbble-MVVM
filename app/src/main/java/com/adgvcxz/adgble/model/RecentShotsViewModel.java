package com.adgvcxz.adgble.model;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.adapter.BaseRecyclerViewAdapter;
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
//    public final MutliItemViewSelector itemView = MutliItemViewSelector.add(ItemView.of(BR.item, R.layout.item_record_shot)
//        , ItemView.of(BR.item, R.layout.item_record_shot_text));

    public final BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter();


    public static class RecentShotsItemViewModel extends BaseItemViewModel {
        public final ObservableField<String> imageUrl = new ObservableField<>();

        public void onClick(View view) {
            Toast.makeText(view.getContext(), imageUrl.get(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public String toString() {
            return "imageUrl" + imageUrl.get();
        }
    }
}
