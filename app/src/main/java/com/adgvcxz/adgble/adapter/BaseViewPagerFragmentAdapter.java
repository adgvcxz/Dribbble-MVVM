package com.adgvcxz.adgble.adapter;

import android.databinding.ObservableList;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.adgvcxz.adgble.binding.ItemViewSelector;
import com.adgvcxz.adgble.fragment.BaseViewModelFragment;
import com.adgvcxz.adgble.util.Util;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/20.
 */

public class BaseViewPagerFragmentAdapter<T extends OnCreateViewListener> extends FragmentPagerAdapter {

    private List<T> items;
    private ItemViewSelector itemView;
    private PageTitles pageTitles;
    private final WeakReferenceOnListChangedCallback<T> callback = new WeakReferenceOnListChangedCallback<>(this);

    public BaseViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        BaseViewModelFragment fragment = BaseViewModelFragment.newInstance(
                itemView.bindingVariable(position, items.get(position)), itemView.layoutRes(position, items.get(position)));
        return fragment.setItem(items.get(position));
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (pageTitles != null) {
            return pageTitles.getPageTitle(position);
        }
        return super.getPageTitle(position);
    }

    public void setItems(List<T> items) {
        if (this.items == items) {
            return;
        }
        if (this.items instanceof ObservableList) {
            ((ObservableList<T>) this.items).removeOnListChangedCallback(callback);
        }
        if (items instanceof ObservableList) {
            ((ObservableList<T>) items).addOnListChangedCallback(callback);
        }
        this.items = items;
        notifyDataSetChanged();
    }

    public void setItemView(ItemViewSelector<T> itemView) {
        this.itemView = itemView;
    }

    public void setPageTitles(PageTitles pageTitles) {
        this.pageTitles = pageTitles;
    }


    private static class WeakReferenceOnListChangedCallback<T extends OnCreateViewListener> extends ObservableList.OnListChangedCallback<ObservableList<T>> {
        final WeakReference<BaseViewPagerFragmentAdapter<T>> adapterRef;

        WeakReferenceOnListChangedCallback(BaseViewPagerFragmentAdapter<T> adapter) {
            this.adapterRef = new WeakReference<>(adapter);
        }

        @Override
        public void onChanged(ObservableList sender) {
            BaseViewPagerFragmentAdapter<T> adapter = adapterRef.get();
            if (adapter == null) {
                return;
            }
            Util.ensureChangeOnMainThread();
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
            onChanged(sender);
        }

        @Override
        public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
            onChanged(sender);
        }

        @Override
        public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
            onChanged(sender);
        }

        @Override
        public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
            onChanged(sender);
        }
    }


    public interface PageTitles {
        CharSequence getPageTitle(int position);
    }
}
