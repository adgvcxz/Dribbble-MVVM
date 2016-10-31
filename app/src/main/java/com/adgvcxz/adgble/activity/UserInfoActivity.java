package com.adgvcxz.adgble.activity;

import android.animation.Animator;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.databinding.ActivityUserInfoBinding;
import com.adgvcxz.adgble.util.Util;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/31.
 */

public class UserInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_user_info);
        binding.setModel(getIntent().getParcelableExtra(Util.DATA));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.avatar.post(() -> {
                View view = binding.avatar;
                Animator animator = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight() / 2, 0, view.getHeight());
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(500);
                animator.start();
            });

        }
    }

}
