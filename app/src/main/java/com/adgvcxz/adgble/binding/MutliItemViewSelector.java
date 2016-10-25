package com.adgvcxz.adgble.binding;

import java.util.Arrays;
import java.util.List;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/13.
 */

public class MutliItemViewSelector<T> implements ItemViewSelector<T> {

    private OnItemViewSelector<T> selector;

    public MutliItemViewSelector(OnItemViewSelector selector) {
        this.selector = selector;
    }


    @Override
    public int layoutRes(int position, T item) {
        return selector.onItemView(position, item).layoutRes();
    }

    @Override
    public int bindingVariable(int position, T item) {
        return selector.onItemView(position, item).bindingVariable();
    }

    public static MutliItemViewSelector newInstance(OnItemViewSelector selector) {
        return new MutliItemViewSelector(selector);
    }

    public interface OnItemViewSelector<T> {
        ItemView onItemView(int position, T item);
    }
}
