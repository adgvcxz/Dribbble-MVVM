package com.adgvcxz.adgble.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.binding.ThemeBindingConfig;
import com.adgvcxz.adgble.util.ThemeUtil;
import com.adgvcxz.adgble.util.Util;

import java.util.List;

import io.reactivex.Observable;

/**
 * zhaowei
 * Created by zhaowei on 2016/11/2.
 */

public class TagsViewGroup extends ViewGroup {

    private int horizontalSpacing = 0;
    private int verticalSpacing = 0;
    private int innerHorizontalSpacing = 0;
    private int innerVerticalSpacing = 0;

    public TagsViewGroup(Context context) {
        this(context, null);
    }

    public TagsViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initAttrs(context, attrs);
    }

    public TagsViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initAttrs(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TagsViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
        initAttrs(context, attrs);
    }

    private void init() {
        horizontalSpacing = Util.dpToPx(getContext(), 8);
        verticalSpacing = Util.dpToPx(getContext(), 4);
        innerHorizontalSpacing = Util.dpToPx(getContext(), 8);
        innerVerticalSpacing = Util.dpToPx(getContext(), 4);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TagsViewGroup);
        if (array != null) {
            horizontalSpacing = array.getDimensionPixelSize(R.styleable.TagsViewGroup_horizontal_spacing, horizontalSpacing);
            verticalSpacing = array.getDimensionPixelSize(R.styleable.TagsViewGroup_vertical_spacing, verticalSpacing);
            innerHorizontalSpacing = array.getDimensionPixelSize(R.styleable.TagsViewGroup_inner_horizontal_spacing, innerHorizontalSpacing);
            innerVerticalSpacing = array.getDimensionPixelSize(R.styleable.TagsViewGroup_inner_vertical_spacing, innerVerticalSpacing);
            array.recycle();
        }
    }

    public void setTags(List<String> tags, OnTagClickListener listener) {
        removeAllViews();
        Observable.fromIterable(tags).scan(0, (integer, s) -> {
            FrameLayout layout = new FrameLayout(getContext());
            TextView tv = new TextView(getContext());
            tv.setTextColor(ContextCompat.getColor(getContext(), R.color.color_accent));
            tv.setTextSize(15);
            tv.setPadding(innerHorizontalSpacing, innerVerticalSpacing, innerHorizontalSpacing, innerVerticalSpacing);
            tv.setText(s);
            if (listener != null) {
                tv.setOnClickListener(view -> listener.onClick(tv, integer, s));
            }
            ThemeBindingConfig.setCardColorTheme(layout, ThemeUtil.theme.get());
            ViewCompat.setElevation(layout, Util.dpToPx(getContext(), 6));
            layout.addView(tv);
            addView(layout);
            return integer + 1;
        }).subscribe(integer -> {
            requestLayout();
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        int left = getPaddingLeft();
        int top = getPaddingTop();
        int maxChildHeight = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                left += childWidth;
                if (left + getPaddingRight() >= width) {
                    left = getPaddingLeft() + childWidth;
                    top += maxChildHeight;
                    top += verticalSpacing;
                    maxChildHeight = childHeight;
                } else {
                    maxChildHeight = Math.max(maxChildHeight, childHeight);
                }
                left += horizontalSpacing;
            }
        }
        top += maxChildHeight;
        top += (getPaddingTop() * 1.5);
        setMeasuredDimension(width, top);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = getPaddingLeft();
        int childTop = getPaddingTop();
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            final int width = child.getMeasuredWidth();
            final int height = child.getMeasuredHeight();
            if (child.getVisibility() != GONE) {
                if (childLeft + width + getPaddingRight() >= getMeasuredWidth()) {
                    childLeft = getPaddingLeft();
                    childTop += height;
                    childTop += verticalSpacing;
                }
                child.layout(childLeft, childTop, childLeft + width, childTop + height);
                childLeft += width;
                childLeft += horizontalSpacing;
            }
        }
    }

    public interface OnTagClickListener {
        void onClick(View view, int position, String tag);
    }
}
