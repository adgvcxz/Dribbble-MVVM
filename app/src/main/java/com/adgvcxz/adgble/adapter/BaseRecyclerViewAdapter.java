package com.adgvcxz.adgble.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/12.
 */

public class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<T> values;
    private ItemView itemView;
    private LayoutInflater inflater;

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
        binding.setVariable(itemView.bindingVariable(), values.get(position));
    }

    @Override
    public int getItemCount() {
        return values == null? 0 : values.size();
    }

    public void setValues(ArrayList<T> values) {
        this.values = values;
        notifyDataSetChanged();
    }

    public void setItemView(ItemView itemView) {
        this.itemView = itemView;
    }

    @Override
    public int getItemViewType(int position) {
        return itemView.layoutRes();
    }
}
