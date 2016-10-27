package com.adgvcxz.adgble.model;

import android.content.Context;

import com.adgvcxz.adgble.adapter.TopMarginSelector;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/12.
 */

public class RecentShotsViewModel extends ShotsListViewModel {

    public RecentShotsViewModel(Context context, TopMarginSelector selector) {
        super(context, selector);
    }

    @Override
    String getSorts() {
        return "recent";
    }
}
