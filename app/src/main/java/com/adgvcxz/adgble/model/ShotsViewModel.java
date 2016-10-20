package com.adgvcxz.adgble.model;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.support.v4.app.FragmentManager;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.adapter.BaseViewPagerFragmentAdapter;
import com.adgvcxz.adgble.binding.ItemView;
import com.android.databinding.library.baseAdapters.BR;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/20.
 */

public class ShotsViewModel extends BaseViewModel {
    public BaseViewPagerFragmentAdapter adapter;
    public final ItemView itemView = ItemView.of(BR.model, R.layout.fragment_recent_shots);
    public final ObservableArrayList<RecentShotsViewModel> items = new ObservableArrayList<>();
    public final ObservableInt position = new ObservableInt(0);
    public final BaseViewPagerFragmentAdapter.PageTitles pageTitles = position -> "Shots" + position;

    public ShotsViewModel(Context context, FragmentManager fm) {
        adapter = new BaseViewPagerFragmentAdapter(fm);
        items.add(new RecentShotsViewModel(context));
        items.add(new RecentShotsViewModel(context));
        items.add(new RecentShotsViewModel(context));
    }
}
