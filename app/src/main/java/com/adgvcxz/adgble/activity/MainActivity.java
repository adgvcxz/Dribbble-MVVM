package com.adgvcxz.adgble.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.databinding.ActivityMainBinding;
import com.adgvcxz.adgble.fragment.DrawerMenuFragment;
import com.adgvcxz.adgble.fragment.RecentShotsFragment;
import com.adgvcxz.adgble.model.MainActivityViewModel;
import com.adgvcxz.adgble.rxbus.RxBus;
import com.adgvcxz.adgble.rxbus.RxBusChangeTheme;


public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {

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
        RxBus.getDefault().toObservable(RxBusChangeTheme.class).map(drawerLayout -> {
            binding.drawerLayout.setDrawingCacheEnabled(true);
            return Bitmap.createBitmap(binding.drawerLayout.getDrawingCache());
        }).subscribe(bitmap -> {
            binding.changeModeImage.setImageBitmap(bitmap);
            binding.drawerLayout.setDrawingCacheEnabled(false);
            viewModel.changeMode.set(true);
            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.change_theme);
            animation.setAnimationListener(MainActivity.this);
            binding.changeModeImage.startAnimation(animation);
            binding.statusBar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.color_primary_status_bar_teal));
            findViewById(R.id.toolbar).setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.color_primary_teal));
        });
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        viewModel.changeMode.set(false);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
