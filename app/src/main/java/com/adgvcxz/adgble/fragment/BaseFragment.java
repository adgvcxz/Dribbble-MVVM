package com.adgvcxz.adgble.fragment;

import android.support.v4.app.Fragment;

import com.adgvcxz.adgble.AdgbleApp;
import com.squareup.leakcanary.RefWatcher;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/13.
 */

public class BaseFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = AdgbleApp.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
