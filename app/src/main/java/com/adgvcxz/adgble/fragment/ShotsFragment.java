package com.adgvcxz.adgble.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.databinding.FragmentShotsBinding;
import com.adgvcxz.adgble.model.ShotsViewModel;
import com.adgvcxz.adgble.util.RxUtil;
import com.adgvcxz.adgble.util.Util;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/19.
 */

public class ShotsFragment extends BaseFragment {

    private FragmentShotsBinding binding;
    private ShotsViewModel viewModel;
    private int actionBarHeight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentShotsBinding.inflate(inflater, container, false);
        binding.setTitle(getString(R.string.shots));
        viewModel = new ShotsViewModel(getActivity(), getChildFragmentManager());
        binding.setModel(viewModel);
        TabLayout tabLayout = (TabLayout) binding.getRoot().findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(binding.viewPager);
        binding.viewPager.setOffscreenPageLimit(2);
        View view = binding.getRoot().findViewById(R.id.app_bar_layout);
        actionBarHeight = Util.getActionBarHeight(getActivity());
        RxUtil.toObservableInt(viewModel.shotsToolbarViewModel.position).filter(integer -> view.getTranslationY() == -actionBarHeight)
                .subscribe(integer -> {
                    ViewCompat.animate(view).translationY(0).setDuration((long) Math.abs(view.getTranslationY())).start();
                });
        return binding.getRoot();
    }

}
