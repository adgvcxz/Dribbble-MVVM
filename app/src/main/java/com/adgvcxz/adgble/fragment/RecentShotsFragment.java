package com.adgvcxz.adgble.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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
        RetrofitSingleton.getInstance().getShots(0, "recent").subscribe(shots -> {
            Log.e("zhaow", "加载成功" + shots.size());
            for (Shot shot: shots) {
                RecentShotsViewModel.RecentShotsItemViewModel item = new RecentShotsViewModel.RecentShotsItemViewModel();
                item.imageUrl.set("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
                model.items.add(item);
            }
        }, throwable -> {
        });
        return binding.getRoot();
    }
}
