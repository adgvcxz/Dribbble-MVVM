package com.adgvcxz.adgble.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.activity.ShotsDetailActivity;
import com.adgvcxz.adgble.adapter.OnCreateViewListener;
import com.adgvcxz.adgble.adapter.TopMarginSelector;
import com.adgvcxz.adgble.api.RetrofitSingleton;
import com.adgvcxz.adgble.binding.ItemView;
import com.adgvcxz.adgble.binding.OnRecyclerViewItemClickListener;
import com.adgvcxz.adgble.content.Shot;
import com.adgvcxz.adgble.util.RxUtil;
import com.adgvcxz.adgble.util.Util;
import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/23.
 */

public abstract class ShotsListViewModel extends RefreshRecyclerViewModel<Shot> implements OnCreateViewListener {

    private final int padding;

    public ShotsListViewModel(Context context, TopMarginSelector selector) {
        super();
        padding = context.getResources().getDimensionPixelSize(R.dimen.item_shots_padding);
        topMarginSelector = new ObservableField<>(selector);
        topMargin.set(topMarginSelector.get().topMargin(null, 0));
        RxUtil.toObservableInt(topMarginSelector).subscribe(selector1 -> {
            topMargin.set(selector1.topMargin(null, 0));
        });
    }

    public final ItemView itemView = ItemView.of(BR.item, R.layout.item_shot_large_without_info);

    public final OnRecyclerViewItemClickListener clickListener = (recyclerView, position, v) ->
            Observable.just(recyclerView.getContext()).ofType(Activity.class)
                    .subscribe(activity -> {
                        View view = v.findViewById(R.id.item_shot_image);
                        if (view != null) {
                            ActivityOptionsCompat opts = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, new Pair<>(view, Util.ShotImage));
                            Intent intent = new Intent(activity, ShotsDetailActivity.class);
                            intent.putExtra("Data", items.get(position));
                            ActivityCompat.startActivity(activity, intent, opts.toBundle());
                        }
                    });

    @Override
    public Observable<List<Shot>> request(int page) {
        return RetrofitSingleton.getInstance().getShots(page, getSorts()).flatMapIterable(shots -> shots).collect(ArrayList::new, (shots, shot) -> {
            shot.setMarginLeft(shots.size() == 0 ? padding : padding / 2);
            shot.setMarginRight(shots.size() == 0 ? padding / 2 : padding);
            shots.add(shot);
        });
    }

    @Override
    public void onCreateView() {
        loadFirstData();
        refreshing.set(true);
    }

    abstract String getSorts();
}
