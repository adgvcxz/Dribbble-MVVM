package com.adgvcxz.adgble.model;

import android.content.Context;
import android.content.res.TypedArray;
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
    public final ShotsToolbarViewModel shotsToolbarViewModel = new ShotsToolbarViewModel(this);
    private String[] titles;
    public final BaseViewPagerFragmentAdapter.PageTitles pageTitles = position -> titles[position];

    private int normalMargin;
    private int cardTopMargin;

    public ShotsViewModel(Context context, FragmentManager fm) {
        titles = context.getResources().getStringArray(R.array.shots_sorts);
        adapter = new BaseViewPagerFragmentAdapter(fm);
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        normalMargin = (int) styledAttributes.getDimension(0, 0) * 2;
        cardTopMargin = normalMargin + context.getResources().getDimensionPixelSize(R.dimen.item_shots_padding);
        items.add(new PopularShotsViewModel(context, (view, position) -> position == 0 ? normalMargin : 0));
        items.add(new RecentShotsViewModel(context, (view, position) -> position == 0 ? normalMargin : 0));
        styledAttributes.recycle();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        ShotsListViewModel model = items.get(shotsToolbarViewModel.position.get());
        int res = model.itemView.layoutRes();
        switch (menuItem.getItemId()) {
            case R.id.fm_shots_large:
                if (res != R.layout.item_shot_large_without_info) {
                    model.itemView.changeLayoutRes(R.layout.item_shot_large_without_info);
                    model.topMarginSelector.set((view, position) -> position == 0 ? normalMargin : 0);
                    if (res == R.layout.item_shot_small_without_info || res == R.layout.item_shot_small_with_info) {
                        model.layoutManager.set(LayoutManager.linear());
                    } else {
                        model.adapter.notifyDataSetChanged();
                    }
                }
                break;
            case R.id.fm_shots_small:
                if (res != R.layout.item_shot_small_without_info) {
                    model.itemView.changeLayoutRes(R.layout.item_shot_small_without_info);
                    model.topMarginSelector.set((view, position) -> position == 0 ? normalMargin : 0);
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
                    model.topMarginSelector.set((view, position) -> position == 0 ? cardTopMargin : 0);
                    if (res == R.layout.item_shot_small_without_info || res == R.layout.item_shot_small_with_info) {
                        model.layoutManager.set(LayoutManager.linear());
                    } else {
                        model.adapter.notifyDataSetChanged();
                    }
                }
                break;
            case R.id.fm_shots_small_info:
                if (res != R.layout.item_shot_small_with_info) {
                    model.itemView.changeLayoutRes(R.layout.item_shot_small_with_info);
                    model.topMarginSelector.set((view, position) -> position == 0 || position == 1 ? cardTopMargin : 0);
                    if (res == R.layout.item_shot_large_without_info || res == R.layout.item_shot_large_with_info) {
                        model.layoutManager.set(LayoutManager.gridLoadMore2());
                    } else {
                        model.adapter.notifyDataSetChanged();
                    }
                }
                break;
        }
        return false;
    }
}
