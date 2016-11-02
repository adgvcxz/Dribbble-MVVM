package com.adgvcxz.adgble.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adgvcxz.adgble.adapter.OnFragmentViewModelListener;
import com.adgvcxz.adgble.model.BaseFragmentViewModel;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/20.
 */

public class BaseViewModelFragment extends BaseFragment {

    public static final String LayoutId = "LayoutId";
    public static final String BindingVariable = "BindingVariable";
    public static final String ViewModel = "ViewModel";

    private OnFragmentViewModelListener model;

    public static BaseViewModelFragment newInstance(int bindingVariable, @LayoutRes int layoutId) {
        BaseViewModelFragment fragment = new BaseViewModelFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(LayoutId, layoutId);
        bundle.putInt(BindingVariable, bindingVariable);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId = getArguments().getInt(LayoutId);
        int bindingVariable = getArguments().getInt(BindingVariable);
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        if (model != null) {
            binding.setVariable(bindingVariable, model);
            model.onCreateView();
        }
        return binding.getRoot();
    }

    public OnFragmentViewModelListener getModel() {
        return model;
    }

    public BaseViewModelFragment setModel(OnFragmentViewModelListener model) {
        this.model = model;
        return this;
    }
}
