package com.adgvcxz.adgble.binding;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/13.
 */

public class ItemViewManager<T> {

    private ItemViewSelector<T> itemViewSelector;


    public ItemViewManager(ItemView itemView) {

    }

    public int layoutRes(int position, T item) {
        return itemViewSelector.layoutRes(position, item);
    }

    public int bindingVariable(int position, T item) {
        return itemViewSelector.bindingVariable(position, item);
    }
}
