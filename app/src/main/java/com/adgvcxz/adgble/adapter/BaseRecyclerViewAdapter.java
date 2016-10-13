package com.adgvcxz.adgble.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.binding.ItemView;
import com.adgvcxz.adgble.binding.ItemViewSelector;
import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/12.
 */

public class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<T> items;
    private ItemViewSelector<T> itemView;
    private LayoutInflater inflater;
    private boolean loadMore = true;
    private boolean isLoadAll = false;

    private ItemView loadMoreItemView = ItemView.of(BR.item, R.layout.item_load_more);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, viewType, parent, false);
        return new RecyclerView.ViewHolder(binding.getRoot()) {};
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewDataBinding binding = DataBindingUtil.getBinding(holder.itemView);
        if (position == items.size()) {
            binding.setVariable(loadMoreItemView.bindingVariable(), inflater.getContext().getString(R.string.loading));
        } else {
            binding.setVariable(itemView.bindingVariable(position, items.get(position)), items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (items == null || items.size() == 0) {
            return 0;
        } else {
            if (loadMore && !isLoadAll) {
                return items.size() + 1;
            }
        }
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == items.size()) {
            return loadMoreItemView.layoutRes();
        }
        return itemView.layoutRes(position, items.get(position));
    }

    public void setItems(ArrayList<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setItemView(ItemViewSelector<T> itemView) {
        this.itemView = itemView;
    }

    public void setLoadMore(boolean loadMore) {
        this.loadMore = loadMore;
    }

    public void setLoadAll(boolean loadAll) {
        isLoadAll = loadAll;
    }
}
