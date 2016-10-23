package com.adgvcxz.adgble.model;

import android.content.Context;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/12.
 */

public class RecentShotsViewModel extends ShotsListViewModel {
    public RecentShotsViewModel(Context context) {
        super(context);
    }

    @Override
    String getSorts() {
        return "recent";
    }
}
