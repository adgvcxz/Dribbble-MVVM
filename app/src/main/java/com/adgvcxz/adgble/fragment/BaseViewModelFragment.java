package com.adgvcxz.adgble.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adgvcxz.adgble.model.BaseFragmentViewModel;
import com.android.databinding.library.baseAdapters.BR;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/20.
 */

public class BaseViewModelFragment<T extends BaseFragmentViewModel, B extends ViewDataBinding> extends BaseFragment {

    public static final String Model = "Model";

    T model;
    B binding;

    public static BaseViewModelFragment newInstance(BaseFragmentViewModel model) {
        BaseViewModelFragment fragment = new BaseViewModelFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Model, model);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        model = getArguments().getParcelable(Model);
        if (model != null) {
            binding = DataBindingUtil.inflate(inflater, model.layoutId(), container, false);
            binding.setVariable(BR.model, model);
            model.onCreateView(binding.getRoot());
        }
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        model.onDestroyView();
    }
}
