package com.adgvcxz.adgble.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/19.
 */

public class ShotsScrollTopBehavior extends CoordinatorLayout.Behavior<AppBarLayout> {

    private int touchSlop;
    private int scrolledDistance = 0;
    private int scrollY = 0;
    private int actionBarHeight;
    private AppBarLayout child;

    public ShotsScrollTopBehavior(Context context, AttributeSet set) {
        super(context, set);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop() * 4;
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        actionBarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        scrolledDistance = 0;
//        if (target.getTag() == null) {
//            target.setOnTouchListener(touchListener);
//            target.setTag(touchListener);
//            this.child = child;
//        }
        return target instanceof SwipeRefreshLayout;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
//        scrollY += dyConsumed;
//        float translation = child.getTranslationY();
//        if (scrollY > mActionBarHeight) {
//            if (scrolledDistance > touchSlop && translation == 0) {
//                scrolledDistance = 0;
//                ViewCompat.animate(child).translationY(-actionBarHeight).setDuration(actionBarHeight).start();
//            } else if (scrolledDistance < -touchSlop && translation == -actionBarHeight) {
//                scrolledDistance = 0;
//                ViewCompat.animate(child).translationY(0).setDuration(actionBarHeight).start();
//            }
//            if ((dyConsumed > 0 && translation == 0) || (translation == -actionBarHeight && dyConsumed < 0)) {
//                scrolledDistance += dyConsumed;
//            }
//        } else if (translation == -actionBarHeight) {
//            ViewCompat.animate(child).translationY(0).setDuration(actionBarHeight).start();
//        }

        float translation = child.getTranslationY() - dyConsumed;
        if (translation > 0) {
            translation = 0;
        } else if (translation < -actionBarHeight) {
            translation = -actionBarHeight;
        }
        child.setTranslationY(translation);
    }


    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        float translation = child.getTranslationY();
        if (translation != 0 && translation != -actionBarHeight) {
            if (translation > -actionBarHeight / 2) {
                child.animate().translationY(0).setDuration((long) Math.abs(child.getTranslationY()) * 2).start();
            } else {
                child.animate().translationY(-actionBarHeight).setDuration((long) Math.abs(child.getTranslationY() + actionBarHeight) * 2).start();
            }
        }
    }
}
