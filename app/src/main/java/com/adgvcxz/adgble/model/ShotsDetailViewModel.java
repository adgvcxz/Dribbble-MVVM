package com.adgvcxz.adgble.model;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Build;
import android.view.View;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.adapter.TopMarginSelector;
import com.adgvcxz.adgble.content.Shot;
import com.adgvcxz.adgble.util.RxUtil;
import com.adgvcxz.adgble.util.Util;

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
    }

    @Override
    public Observable<List> request(int page) {
        return null;
    }
}
