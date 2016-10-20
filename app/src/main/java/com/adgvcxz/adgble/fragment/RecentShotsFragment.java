package com.adgvcxz.adgble.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adgvcxz.adgble.databinding.FragmentRecentShotsBinding;
import com.adgvcxz.adgble.model.RecentShotsViewModel;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/11.
 */

public class RecentShotsFragment extends BaseFragment {

    private RecentShotsViewModel viewModel;
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentRecentShotsBinding binding = FragmentRecentShotsBinding.inflate(inflater, container, false);
        viewModel = new RecentShotsViewModel(getActivity());
        binding.setModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
