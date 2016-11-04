package com.adgvcxz.adgble.activity;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.databinding.ActivityShotsDetailBinding;
import com.adgvcxz.adgble.model.ShotsDetailViewModel;
import com.adgvcxz.adgble.util.Util;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.DraweeTransition;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/24.
 */

public class ShotsDetailActivity extends BaseActivity {

    ShotsDetailViewModel viewModel;
    ActivityShotsDetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shots_detail);
        initTranslation(binding);
        viewModel = new ShotsDetailViewModel(this, getIntent().getParcelableExtra(Util.DATA));
        binding.setModel(viewModel);
    }

    private void initTranslation(ActivityShotsDetailBinding binding) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setAlpha(binding.toolbar, 0f);
            getWindow().setSharedElementEnterTransition(DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.CENTER_CROP, ScalingUtils.ScaleType.CENTER_CROP));
            getWindow().setSharedElementReturnTransition(DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.CENTER_CROP, ScalingUtils.ScaleType.CENTER_CROP));
            ViewCompat.setTransitionName(binding.imageView, Util.ShotImage);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (viewModel.animPosition != -1 && keyCode == KeyEvent.KEYCODE_BACK) {
            viewModel.hideLikeLayout();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setAlpha(binding.toolbar, 0f);
        }
        super.onBackPressed();
    }
}
