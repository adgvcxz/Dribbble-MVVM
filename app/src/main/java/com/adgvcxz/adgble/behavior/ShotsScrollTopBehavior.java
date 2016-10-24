package com.adgvcxz.adgble.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/19.
 */

public class ShotsScrollTopBehavior extends CoordinatorLayout.Behavior<AppBarLayout> {

    private int actionBarHeight;
    private float velocity;
    private float minVelocity;

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        velocity = 0;
        return target instanceof SwipeRefreshLayout;
    }


    public ShotsScrollTopBehavior(Context context, AttributeSet set) {
        super(context, set);
        minVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        actionBarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        float translation = child.getTranslationY() - dyConsumed;
        if (translation > 0) {
            translation = 0;
        } else if (translation < -actionBarHeight) {
            translation = -actionBarHeight;
        }
        if (translation != child.getTranslationY()) {
            child.setTranslationY(translation);
        }
        ViewCompat.offsetTopAndBottom(child, (int) translation);
    }


    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        float translation = child.getTranslationY();
        if (translation != 0 && translation != -actionBarHeight) {
            if ((velocity == 0 && translation > -actionBarHeight / 2) || (velocity != 0 && velocity < -minVelocity)) {
                int duration = (int) Math.abs(child.getTranslationY());
                duration = velocity == 0 ? duration * 2 : duration;
                child.animate().translationY(0).setDuration(duration).start();
            } else {
                int duration = (int) Math.abs(child.getTranslationY() + actionBarHeight);
                duration = velocity == 0 ? duration * 2 : duration;
                child.animate().translationY(-actionBarHeight).setDuration(duration).start();
            }
        }
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, float velocityX, float velocityY) {
        velocity = velocityY;
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }
}
