package com.adgvcxz.adgble.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.adgvcxz.adgble.binding.ItemView;

import java.util.ArrayList;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/12.
 */

public class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<T> items;
    private ItemView itemView;
    private LayoutInflater inflater;
    private boolean loadMore;
    private boolean isLoadAll;

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
        binding.setVariable(itemView.bindingVariable(), items.get(position));
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        } else {
            if (loadMore && !isLoadAll) {
                return items.size() + 1;
            }
        }
        return items.size();
    }

    public void setItems(ArrayList<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setItemView(ItemView itemView) {
        this.itemView = itemView;
    }

    @Override
    public int getItemViewType(int position) {
        return itemView.layoutRes();
    }

    public void setLoadMore(boolean loadMore) {
        this.loadMore = loadMore;
    }

    public void setLoadAll(boolean loadAll) {
        isLoadAll = loadAll;
    }
}
