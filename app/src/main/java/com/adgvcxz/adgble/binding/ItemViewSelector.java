package com.adgvcxz.adgble.binding;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/12.
 */

public interface ItemViewSelector<T> {
    int layoutRes(int position, T item);
    int bindingVariable(int position, T item);
}
