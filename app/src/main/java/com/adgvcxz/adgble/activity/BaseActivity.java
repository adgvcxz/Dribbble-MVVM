package com.adgvcxz.adgble.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import rx.Subscription;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/15.
 */

public class BaseActivity extends AppCompatActivity {

    ArrayList<Subscription> subscriptions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscriptions = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (Subscription subscription : subscriptions) {
            subscription.unsubscribe();
        }
    }
}
