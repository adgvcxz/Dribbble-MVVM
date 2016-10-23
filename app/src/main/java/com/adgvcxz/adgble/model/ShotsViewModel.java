package com.adgvcxz.adgble.model;

import android.content.Context;
import android.databinding.ObservableArrayList;
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
    public final ItemView itemView = ItemView.of(BR.model, R.layout.fragment_shots_list);
    public final ObservableArrayList<ShotsListViewModel> items = new ObservableArrayList<>();
    //    public final ObservableInt position = new ObservableInt(0);
    public final ShotsToolbarViewModel shotsToolbarViewModel = new ShotsToolbarViewModel(this);
    private String[] titles;
    public final BaseViewPagerFragmentAdapter.PageTitles pageTitles = position -> titles[position];

    public ShotsViewModel(Context context, FragmentManager fm) {
        titles = context.getResources().getStringArray(R.array.shots_sorts);
        adapter = new BaseViewPagerFragmentAdapter(fm);
        items.add(new PopularShotsViewModel(context));
        items.add(new RecentShotsViewModel(context));
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        ShotsListViewModel model = items.get(shotsToolbarViewModel.position.get());
        int res = model.itemView.layoutRes();
        switch (item.getItemId()) {
            case R.id.fm_shots_large:
                if (res != R.layout.item_shot_large_without_info) {
                    model.itemView.changeLayoutRes(R.layout.item_shot_large_without_info);
                    if (res == R.layout.item_shot_small_without_info) {
                        model.layoutManager.set(LayoutManager.linear());
                    } else {
                        model.adapter.notifyDataSetChanged();
                    }
                }
                break;
            case R.id.fm_shots_small:
                if (res != R.layout.item_shot_small_without_info) {
                    model.itemView.changeLayoutRes(R.layout.item_shot_small_without_info);
                    if (res == R.layout.item_shot_large_without_info || res == R.layout.item_shot_large_with_info) {
                        model.layoutManager.set(LayoutManager.gridLoadMore2());
                    } else {
                        model.adapter.notifyDataSetChanged();
                    }
                }
                break;
            case R.id.fm_shots_large_info:
                if (res != R.layout.item_shot_large_with_info) {
                    model.itemView.changeLayoutRes(R.layout.item_shot_large_with_info);
                    if (res == R.layout.item_shot_small_without_info) {
                        model.layoutManager.set(LayoutManager.linear());
                    } else {
                        model.adapter.notifyDataSetChanged();
                    }
                }
                break;
        }
        return false;
    }
}
