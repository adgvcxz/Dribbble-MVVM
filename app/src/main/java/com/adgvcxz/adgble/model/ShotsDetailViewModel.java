package com.adgvcxz.adgble.model;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.adapter.TopMarginSelector;
import com.adgvcxz.adgble.api.RetrofitSingleton;
import com.adgvcxz.adgble.binding.ItemView;
import com.adgvcxz.adgble.binding.MutliItemViewSelector;
import com.adgvcxz.adgble.util.RxUtil;
import com.adgvcxz.adgble.util.Util;
import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/25.
 */

public class ShotsDetailViewModel extends BaseRecyclerViewModel<BaseObservable> {

    public final ShotItemViewModel shotItemViewModel;
    public final ObservableInt translationY = new ObservableInt();
    public final ObservableInt firstItemTop = new ObservableInt();
    public final ObservableInt elevation = new ObservableInt();

    public final ItemView commentItemView = ItemView.of(BR.item, R.layout.item_shot_comment);
    public final ItemView headerItemView = ItemView.of(BR.item, R.layout.item_shot_detail_header);

    public final MutliItemViewSelector itemView = MutliItemViewSelector.newInstance((position, item) -> position == 0 ? headerItemView : commentItemView);

    public ShotsDetailViewModel(Activity activity, ShotItemViewModel model) {
        int topMargin = activity.getResources().getDimensionPixelSize(R.dimen.detail_toolbar_height);
        final int margin = topMargin;
        shotItemViewModel = model;
        int temp = activity.getResources().getDimensionPixelSize(R.dimen.detail_toolbar_height) - Util.getActionBarHeight(activity) * 2;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            temp -= Util.getStatusBarHeight(activity);
//        }
        int height = temp;
        this.topMarginSelector = new ObservableField<>((view, position) -> position == 0 ? margin : 0);
        float density = activity.getResources().getDisplayMetrics().density;
        RxUtil.toObservableInt(firstItemTop).filter(integer -> integer != -1).map(integer -> -integer + topMargin)
                .map(integer -> integer > height ? height : integer)
                .filter(integer -> integer != translationY.get()).subscribe(integer -> {
            translationY.set(integer);
            elevation.set((int) (8 * integer * density / height));
        });
        items.add(shotItemViewModel);
        resetStart = items.size();
        loadData();
    }

    @Override
    public Observable<List<BaseObservable>> request(int page) {
        return RetrofitSingleton.getInstance().getComments(shotItemViewModel.id.get(), page).flatMapIterable(comments -> comments)
                .collect((Callable<List<BaseObservable>>) ArrayList::new, (models, comment) -> models.add(new CommentViewModel(comment)))
                .toObservable();
    }
}
