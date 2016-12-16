package com.adgvcxz.adgble.model;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.adapter.TopMarginSelector;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/12.
 */

public class RecentShotsViewModel extends ShotsListViewModel {

    public RecentShotsViewModel(TopMarginSelector selector) {
        super(selector);
    }

    @Override
    String getSorts() {
        return "recent";
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_shots_list;
    }
}
