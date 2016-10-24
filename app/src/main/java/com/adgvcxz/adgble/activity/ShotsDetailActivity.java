package com.adgvcxz.adgble.activity;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.databinding.ActivityShotsDetailBinding;
import com.adgvcxz.adgble.util.Util;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/24.
 */

public class ShotsDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        ActivityShotsDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_shots_detail);
        int height = getResources().getDimensionPixelSize(R.dimen.detail_toolbar_height) - Util.getActionBarHeight(this) * 2;
        binding.scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int y = scrollY > height ? height : scrollY;
                if (y != -binding.toolbarLayout.getTranslationY()) {
                    binding.toolbarLayout.setTranslationY(-y);
                    binding.toolbar.setTranslationY(y);
                    binding.imageView.setTranslationY(y / 2);
                }
            }
        });
    }
}
