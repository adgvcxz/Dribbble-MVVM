package com.adgvcxz.adgble.model;

import android.content.Context;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/23.
 */

public class PopularShotsViewModel extends ShotsListViewModel {
    public PopularShotsViewModel(Context context) {
        super(context);
    }

    @Override
    String getSorts() {
        return "";
    }
}
