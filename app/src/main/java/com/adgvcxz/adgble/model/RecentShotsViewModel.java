package com.adgvcxz.adgble.model;

import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.adapter.BaseRecyclerViewAdapter;
import com.adgvcxz.adgble.api.RetrofitSingleton;
import com.adgvcxz.adgble.binding.BaseItemViewModel;
import com.adgvcxz.adgble.binding.ItemView;
import com.adgvcxz.adgble.binding.OnRecyclerViewItemClickListener;
import com.adgvcxz.adgble.util.RxUtil;
import com.android.databinding.library.baseAdapters.BR;

import java.util.List;

import rx.Observable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/12.
 */

public class RecentShotsViewModel extends BaseRecyclerViewModel<RecentShotsViewModel.RecentShotsItemViewModel> {


    public final ItemView itemView = ItemView.of(BR.item, R.layout.item_record_shot);
//    public final MutliItemViewSelector itemView = MutliItemViewSelector.add(ItemView.of(BR.item, R.layout.item_record_shot)
//        , ItemView.of(BR.item, R.layout.item_record_shot_text));

    public final BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(loadMoreViewModel);

    public final OnRecyclerViewItemClickListener clickListener = new OnRecyclerViewItemClickListener() {
        @Override
        public void onClick(RecyclerView recyclerView, int position, View v) {
            Toast.makeText(recyclerView.getContext(), items.get(position).imageUrl.get(), Toast.LENGTH_SHORT).show();
            Log.e("zhaow", items.get(position).imageUrl.get());
        }
    };

    @Override
    public Observable<List<RecentShotsItemViewModel>> request(int page) {
        return RetrofitSingleton.getInstance().getShots(page, "recent").compose(RxUtil.rxTransformList(shot -> {
            RecentShotsItemViewModel model = new RecentShotsItemViewModel();
            model.imageUrl.set(shot.images.getHeightImageUri());
            model.thumbnail.set(shot.images.teaser);
            return model;
        }));
    }

    public static class RecentShotsItemViewModel extends BaseItemViewModel {

        public final ObservableField<String> imageUrl = new ObservableField<>();
        public final ObservableField<String> thumbnail = new ObservableField<>();

        @Override
        public String toString() {
            return "imageUrl" + imageUrl.get();
        }
    }
}
