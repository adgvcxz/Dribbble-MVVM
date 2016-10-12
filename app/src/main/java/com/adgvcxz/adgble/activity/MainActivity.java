package com.adgvcxz.adgble.activity;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.ViewGroup;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.data.Dribbble;
import com.adgvcxz.adgble.databinding.ActivityMainBinding;
import com.adgvcxz.adgble.fragment.RecentShotsFragment;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Dribbble dribbble = new Dribbble("", "");
        binding.setDribbble(dribbble);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) binding.imageView.getLayoutParams();
        Observable.timer(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
//            FloatEvaluator floatEvaluator = new FloatEvaluator() {
//                @Override
//                public Float evaluate(float fraction, Number startValue, Number endValue) {
//                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) binding.imageView.getLayoutParams();
//                    layoutParams.horizontalBias = startValue.floatValue() + (endValue.floatValue() - startValue.floatValue()) * fraction;
//                    binding.imageView.setLayoutParams(layoutParams);
//                    return super.evaluate(fraction, startValue, endValue);
//                }
//            };
//            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.5f, 0.7f);
//            valueAnimator.setEvaluator(floatEvaluator);
//            valueAnimator.start();
            layoutParams.horizontalBias = 0.7f;
            binding.imageView.setLayoutParams(layoutParams);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                TransitionManager.beginDelayedTransition((ViewGroup) binding.getRoot());
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, new RecentShotsFragment()).commit();
    }
}
