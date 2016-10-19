package com.adgvcxz.adgble.activity;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.databinding.ActivityMainBinding;
import com.adgvcxz.adgble.fragment.DrawerMenuFragment;
import com.adgvcxz.adgble.fragment.RecentShotsFragment;
import com.adgvcxz.adgble.model.MainActivityViewModel;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainActivityViewModel viewModel = new MainActivityViewModel();
        setSupportActionBar((Toolbar) binding.getRoot().findViewById(R.id.toolbar));
        binding.setModel(viewModel);
        getSupportFragmentManager().beginTransaction().replace(binding.mainContent.getId(), new RecentShotsFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(binding.navigationView.getId(), new DrawerMenuFragment()).commit();
    }
}
