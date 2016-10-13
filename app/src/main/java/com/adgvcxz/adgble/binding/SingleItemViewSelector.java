package com.adgvcxz.adgble.binding;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/13.
 */

public class SingleItemViewSelector<T> implements ItemViewSelector<T> {

    private ItemView itemView;

    public SingleItemViewSelector(ItemView itemView) {
        this.itemView = itemView;
    }

    @Override
    public int layoutRes(int position, T item) {
        return itemView.layoutRes();
    }

    @Override
    public int bindingVariable(int position, T item) {
        return itemView.bindingVariable();
    }
}
