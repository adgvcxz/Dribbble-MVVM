package com.adgvcxz.adgble.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * zhaowei
 * Created by zhaowei on 2016/11/24.
 */

public class TouchUpCoordinatorLayout extends CoordinatorLayout {

    public TouchUpCoordinatorLayout(Context context) {
        super(context);
    }

    public TouchUpCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchUpCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            Log.e("zhaow", "======1======3");
        }
        return super.dispatchTouchEvent(ev);
    }
}
