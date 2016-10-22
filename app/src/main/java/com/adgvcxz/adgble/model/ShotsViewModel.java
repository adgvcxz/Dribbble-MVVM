package com.adgvcxz.adgble.model;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.adapter.BaseViewPagerFragmentAdapter;
import com.adgvcxz.adgble.binding.ItemView;
import com.adgvcxz.adgble.binding.LayoutManager;
import com.android.databinding.library.baseAdapters.BR;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/20.
 */

public class ShotsViewModel extends BaseViewModel implements Toolbar.OnMenuItemClickListener {
    public BaseViewPagerFragmentAdapter adapter;
    public final ItemView itemView = ItemView.of(BR.model, R.layout.fragment_recent_shots);
    public final ObservableArrayList<RecentShotsViewModel> items = new ObservableArrayList<>();
//    public final ObservableInt position = new ObservableInt(0);
    public final ShotsToolbarViewModel shotsToolbarViewModel = new ShotsToolbarViewModel(this);
    public final BaseViewPagerFragmentAdapter.PageTitles pageTitles = position -> "Shots" + position;

    public ShotsViewModel(Context context, FragmentManager fm) {
        adapter = new BaseViewPagerFragmentAdapter(fm);
        items.add(new RecentShotsViewModel(context));
        items.add(new RecentShotsViewModel(context));
        items.add(new RecentShotsViewModel(context));
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        RecentShotsViewModel model = items.get(shotsToolbarViewModel.position.get());
        switch (item.getItemId()) {
            case R.id.fm_shots_large:
                model.itemView.changeLayoutRes(R.layout.item_shot_large_without_info);
                model.layoutManager.set(LayoutManager.linear());
                break;
            case R.id.fm_shots_small:
                model.itemView.changeLayoutRes(R.layout.item_shot_small_without_info);
                model.layoutManager.set(LayoutManager.gridLoadMore2());
                break;
        }
        return false;
    }
}
