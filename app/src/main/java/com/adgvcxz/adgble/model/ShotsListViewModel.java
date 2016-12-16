package com.adgvcxz.adgble.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.adgvcxz.adgble.AdgbleApp;
import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.activity.ShotsDetailActivity;
import com.adgvcxz.adgble.adapter.TopMarginSelector;
import com.adgvcxz.adgble.api.RetrofitSingleton;
import com.adgvcxz.adgble.binding.ItemView;
import com.adgvcxz.adgble.binding.OnRecyclerViewItemClickListener;
import com.adgvcxz.adgble.util.RxUtil;
import com.adgvcxz.adgble.util.Util;
import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/23.
 */

public abstract class ShotsListViewModel extends BaseFragmentViewModel {


    public ShotsRefreshRecyclerViewModel recyclerViewModel;

    public ShotsListViewModel(TopMarginSelector selector) {
        recyclerViewModel = new ShotsRefreshRecyclerViewModel(selector);
    }


    @Override
    public void onCreateView(View view) {
        recyclerViewModel.loadFirstData();
        recyclerViewModel.refreshing.set(true);
    }

    abstract String getSorts();


    public class ShotsRefreshRecyclerViewModel extends RefreshRecyclerViewModel<ShotItemViewModel> {

        private final int padding;

        public final ItemView itemView = ItemView.of(BR.item, R.layout.item_shot_large_without_info);

        public final OnRecyclerViewItemClickListener clickListener = (recyclerView, position, v) ->
                Observable.just(recyclerView.getContext()).ofType(Activity.class)
                        .subscribe(activity -> {
                            View view = v.findViewById(R.id.item_shot_image);
                            if (view != null) {
                                ActivityOptionsCompat opts = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, Util.ShotImage);
                                Intent intent = new Intent(activity, ShotsDetailActivity.class);
                                intent.putExtra(Util.DATA, items.get(position));
                                ActivityCompat.startActivity(activity, intent, opts.toBundle());
                            }
                        });

        public ShotsRefreshRecyclerViewModel(TopMarginSelector selector) {
            super();
            padding = AdgbleApp.getContext().getResources().getDimensionPixelSize(R.dimen.item_shots_padding);
            topMarginSelector = new ObservableField<>(selector);
            topMargin.set(topMarginSelector.get().topMargin(null, 0));
            RxUtil.toObservableField(topMarginSelector).subscribe(selector1 -> {
                topMargin.set(selector1.topMargin(null, 0));
            });
        }

        @Override
        public Observable<List<ShotItemViewModel>> request(int page) {
            return RetrofitSingleton.getInstance().getShots(page, getSorts()).flatMapIterable(shots -> shots)
                    .collect((Callable<List<ShotItemViewModel>>) ArrayList::new, (shots, shot) -> {
                        boolean size0 = shots.size() % 2 == 0;
                        shots.add(new ShotItemViewModel(shot, size0 ? padding : padding / 2, size0 ? padding / 2 : padding));
                    }).toObservable();
        }
    }

    public ShotsListViewModel(Parcel parcel) {
        super();

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
