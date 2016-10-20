package com.adgvcxz.adgble.binding;

import android.databinding.BindingAdapter;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import com.adgvcxz.adgble.adapter.BaseRecyclerViewAdapter;
import com.adgvcxz.adgble.adapter.BaseViewPagerFragmentAdapter;

import java.util.List;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/20.
 */

public class ViewPagerBindingConfig {

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"itemView", "items", "fmAdapter", "pageTitles"}, requireAll = false)
    public static <T> void setAdapter(ViewPager viewPager, ItemViewSelector<T> itemView, List<T> items, BaseViewPagerFragmentAdapter adapter, BaseViewPagerFragmentAdapter.PageTitles pageTitles) {
        if (itemView == null) {
            throw new IllegalArgumentException("itemView must not be null");
        }

        FragmentPagerAdapter oldAdapter = (FragmentPagerAdapter) viewPager.getAdapter();
        if (oldAdapter == null) {
            adapter.setItems(items);
            adapter.setItemView(itemView);
            adapter.setPageTitles(pageTitles);
            viewPager.setAdapter(adapter);
        } else {
            adapter.setItems(items);
        }
    }

}
