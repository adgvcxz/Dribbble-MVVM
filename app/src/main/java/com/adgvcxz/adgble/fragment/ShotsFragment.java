package com.adgvcxz.adgble.fragment;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.binding.LayoutManager;
import com.adgvcxz.adgble.databinding.FragmentShotsBinding;
import com.adgvcxz.adgble.model.ShotsViewModel;
import com.adgvcxz.adgble.util.Util;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/19.
 */

public class ShotsFragment extends BaseFragment implements Toolbar.OnMenuItemClickListener {

    private AppBarLayout appBarLayout;
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
        Toolbar toolbar = (Toolbar) binding.getRoot().findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.fm_shots);
        toolbar.setOnMenuItemClickListener(this);
        appBarLayout = (AppBarLayout) binding.getRoot().findViewById(R.id.app_bar_layout);
        actionBarHeight = Util.getActionBarHeight(getActivity());
        viewModel.position.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if (appBarLayout.getTranslationY() == -actionBarHeight) {
                    ViewCompat.animate(appBarLayout).translationY(0).setDuration((long) Math.abs(appBarLayout.getTranslationY())).start();
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        viewModel.items.get(viewModel.position.get()).layoutManager.set(LayoutManager.gridLoadMore2());
        return false;
    }
}
