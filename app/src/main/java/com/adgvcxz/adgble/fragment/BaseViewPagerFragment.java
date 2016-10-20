package com.adgvcxz.adgble.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adgvcxz.adgble.adapter.OnCreateViewListener;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/20.
 */

public class BaseViewPagerFragment extends BaseFragment {

    @LayoutRes
    private int layoutId;
    private OnCreateViewListener item;

    public static BaseViewPagerFragment newInstance(@LayoutRes int layoutId, int bindingVariable) {
        BaseViewPagerFragment fragment = new BaseViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("layoutId", layoutId);
        bundle.putInt("bindingVariable", bindingVariable);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutId = getArguments().getInt("layoutId");
        int bindingVariable = getArguments().getInt("bindingVariable");
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        binding.setVariable(bindingVariable, item);
        item.onCreateView();
        return binding.getRoot();
    }

    public void setItem(OnCreateViewListener item) {
        this.item = item;
    }
}
