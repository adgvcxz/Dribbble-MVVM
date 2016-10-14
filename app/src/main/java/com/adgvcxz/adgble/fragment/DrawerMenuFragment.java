package com.adgvcxz.adgble.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adgvcxz.adgble.databinding.FragmentDrawerMenuBinding;
import com.adgvcxz.adgble.model.DrawerMenuViewModel;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/14.
 */

public class DrawerMenuFragment extends BaseFragment {

    private DrawerMenuViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentDrawerMenuBinding binding = FragmentDrawerMenuBinding.inflate(inflater, container, false);
        viewModel = new DrawerMenuViewModel();
        binding.setModel(viewModel);
        return binding.getRoot();
    }

}
