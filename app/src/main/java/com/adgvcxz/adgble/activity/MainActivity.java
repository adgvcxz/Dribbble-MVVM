package com.adgvcxz.adgble.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.databinding.ActivityMainBinding;
import com.adgvcxz.adgble.fragment.DrawerMenuFragment;
import com.adgvcxz.adgble.fragment.RecentShotsFragment;
import com.adgvcxz.adgble.model.MainActivityViewModel;
import com.adgvcxz.adgble.rxbus.RxBus;
import com.adgvcxz.adgble.rxbus.RxBusChangeTheme;
import com.adgvcxz.adgble.util.ThemeUtil;

import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;


public class MainActivity extends BaseActivity {

    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        viewModel = new MainActivityViewModel();
        binding.setModel(viewModel);
        getSupportFragmentManager().beginTransaction().replace(binding.mainContent.getId(), new RecentShotsFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(binding.navigationView.getId(), new DrawerMenuFragment()).commit();
        initChangeTheme();
        AnimationDrawable animationDrawable = new AnimationDrawable();
    }

    private void initChangeTheme() {
        Subscription subscription = RxBus.getDefault().toObservable(RxBusChangeTheme.class).map(changeTheme -> {
            binding.drawerLayout.setDrawingCacheEnabled(true);
            viewModel.theme.set(changeTheme.getTheme());
            ThemeUtil.sTheme = changeTheme.getTheme();
            ImageView imageView = new ImageView(MainActivity.this);
            binding.frameLayout.addView(imageView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setImageBitmap(Bitmap.createBitmap(binding.drawerLayout.getDrawingCache()));
            binding.drawerLayout.setDrawingCacheEnabled(false);
            imageView.animate().alpha(0).setDuration(350).start();
            imageView.setOnTouchListener((v, event) -> true);
            return imageView;
        }).debounce(400, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(imageView -> {
            binding.frameLayout.removeView(imageView);
            System.gc();
            Runtime.getRuntime().gc();
        });
        subscriptions.add(subscription);
    }
}
