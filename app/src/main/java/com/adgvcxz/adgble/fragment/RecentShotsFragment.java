package com.adgvcxz.adgble.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adgvcxz.adgble.api.RetrofitSingleton;
import com.adgvcxz.adgble.content.Shot;
import com.adgvcxz.adgble.databinding.FragmentRecentShotsBinding;
import com.adgvcxz.adgble.model.RecentShotsViewModel;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/11.
 */

public class RecentShotsFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentRecentShotsBinding binding = FragmentRecentShotsBinding.inflate(inflater, container, false);
        RecentShotsViewModel model = new RecentShotsViewModel();
        binding.setModel(model);
        return binding.getRoot();
    }
}
