package com.adgvcxz.adgble.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.binding.LayoutManager;
import com.adgvcxz.adgble.databinding.FragmentRecentShotsBinding;
import com.adgvcxz.adgble.model.RecentShotsViewModel;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/11.
 */

public class RecentShotsFragment extends BaseFragment {

    private RecentShotsViewModel viewModel;

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fm_shots, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fm_shots_range:
                viewModel.itemView.changeLayoutRes(R.layout.item_shots_small);
                viewModel.layoutManager.set(LayoutManager.gridLoadMore2());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
