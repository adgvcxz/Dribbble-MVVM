package com.adgvcxz.adgble.model;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Build;
import android.view.View;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.adapter.TopMarginSelector;
import com.adgvcxz.adgble.binding.ItemView;
import com.adgvcxz.adgble.binding.MutliItemViewSelector;
import com.adgvcxz.adgble.content.Shot;
import com.adgvcxz.adgble.util.RxUtil;
import com.adgvcxz.adgble.util.Util;
import com.android.databinding.library.baseAdapters.BR;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/25.
 */

public class ShotsDetailViewModel extends BaseRecyclerViewModel {

    public final Shot shotItemViewModel;
    public final ObservableInt translationY = new ObservableInt();
    public final ObservableInt scrollY = new ObservableInt();

    public final ItemView imageItemView = ItemView.of(BR.item, R.layout.item_shot_large_without_info);

    public final MutliItemViewSelector itemView = MutliItemViewSelector.newInstance((position, item) -> imageItemView);

    public ShotsDetailViewModel(Context context, Shot model) {
        int topMargin = context.getResources().getDimensionPixelSize(R.dimen.detail_toolbar_height);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            topMargin += Util.getStatusBarHeight(context);
        }
        final int margin = topMargin;
        this.topMarginSelector = new ObservableField<>((TopMarginSelector) (view, position) -> position == 0 ? margin : 0);
        shotItemViewModel = model;
        int height = context.getResources().getDimensionPixelSize(R.dimen.detail_toolbar_height) - Util.getActionBarHeight(context) * 2;
        RxUtil.toObservableInt(scrollY).map(integer -> integer > height ? height : integer)
                .filter(integer -> integer != translationY.get()).subscribe(translationY::set);
        for (int i = 0; i < 10; i++) {
            items.add(shotItemViewModel);
        }
    }

    @Override
    public Observable<List> request(int page) {
        return null;
    }
}
