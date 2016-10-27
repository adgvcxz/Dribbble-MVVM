package com.adgvcxz.adgble.activity;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.transition.Transition;
import android.view.WindowManager;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.content.Shot;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        ActivityShotsDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_shots_detail);
        initTranslation(binding);
        ShotsDetailViewModel model = new ShotsDetailViewModel(this, (Shot) getIntent().getSerializableExtra("Data"));
        binding.setModel(model);
    }

    private void initTranslation(ActivityShotsDetailBinding binding) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementEnterTransition(DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.CENTER_CROP, ScalingUtils.ScaleType.CENTER_CROP));
            getWindow().setSharedElementReturnTransition(DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.CENTER_CROP, ScalingUtils.ScaleType.CENTER_CROP));
            Transition.TransitionListener listener = new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                }

                @Override
                public void onTransitionEnd(Transition transition) {

                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            };
            ViewCompat.setTransitionName(binding.imageView, Util.ShotImage);
            getWindow().getSharedElementEnterTransition().addListener(listener);
        }
    }
}
