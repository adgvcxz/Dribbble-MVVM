package com.adgvcxz.adgble.binding;

import java.util.Arrays;
import java.util.List;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/13.
 */

public class MutliItemViewSelector<T> implements ItemViewSelector<T> {

    private List<ItemView> itemViews;

    public MutliItemViewSelector(List<ItemView> itemViews) {
        this.itemViews = itemViews;
    }


    @Override
    public int layoutRes(int position, T item) {
        return itemViews.get(position % 2).layoutRes();
    }

    @Override
    public int bindingVariable(int position, T item) {
        return itemViews.get(position % 2).bindingVariable();
    }

    public static MutliItemViewSelector add(ItemView... itemViews) {
        List list = Arrays.asList(itemViews);
        return new MutliItemViewSelector(list);
    }
}
