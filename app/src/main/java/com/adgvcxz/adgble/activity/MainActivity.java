package com.adgvcxz.adgble.activity;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.databinding.ActivityMainBinding;
import com.adgvcxz.adgble.fragment.BaseViewModelFragment;
import com.adgvcxz.adgble.fragment.ShotsFragment;
import com.adgvcxz.adgble.model.DrawerMenuViewModel;
import com.adgvcxz.adgble.model.MainActivityViewModel;
import com.android.databinding.library.baseAdapters.BR;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainActivityViewModel viewModel = new MainActivityViewModel();
        binding.setModel(viewModel);
        getSupportFragmentManager().beginTransaction().replace(binding.mainContent.getId(), new ShotsFragment()).commit();
        BaseViewModelFragment drawerFragment = BaseViewModelFragment.newInstance(BR.model, R.layout.fragment_drawer_menu).setItem(new DrawerMenuViewModel());
        getSupportFragmentManager().beginTransaction().replace(binding.navigationView.getId(), drawerFragment).commit();
    }
}
