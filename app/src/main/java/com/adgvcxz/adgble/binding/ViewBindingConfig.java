package com.adgvcxz.adgble.binding;

import android.animation.Animator;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.adapters.ListenerUtil;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.OvershootInterpolator;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.fragment.BaseViewModelFragment;
import com.adgvcxz.adgble.model.BaseFragmentViewModel;
import com.adgvcxz.adgble.util.ThemeUtil;
import com.adgvcxz.adgble.util.Util;
import com.android.databinding.library.baseAdapters.BR;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/25.
 */

public class ViewBindingConfig {

    public static final int AnimInit = -1;
    public static final int AnimShow = 1;
    public static final int AnimHide = 0;

    @BindingAdapter({"statusBarHeight"})
    public static void setLayoutHeight(View view, int version) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (version > Build.VERSION_CODES.KITKAT) {
            lp.height = Util.getStatusBarHeight(view.getContext());
        } else {
            lp.height = 0;
        }
        view.setLayoutParams(lp);
    }

    @BindingAdapter({"android:translationY"})
    public static void setTranslationY(View view, int translationY) {
        if (view.getTranslationY() != translationY) {
            view.setTranslationY(translationY);
        }
    }

    @BindingAdapter({"android:layout_marginLeft"})
    public static void setMarginLeft(View view, int left) {
        Observable.just(view.getLayoutParams()).ofType(ViewGroup.MarginLayoutParams.class)
                .filter(marginLayoutParams -> marginLayoutParams.leftMargin != left).subscribe(marginLayoutParams -> {
            marginLayoutParams.leftMargin = left;
            view.setLayoutParams(marginLayoutParams);
        });
    }

    @BindingAdapter({"android:layout_marginRight"})
    public static void setMarginRight(View view, int right) {
        Observable.just(view.getLayoutParams()).ofType(ViewGroup.MarginLayoutParams.class)
                .filter(marginLayoutParams -> marginLayoutParams.rightMargin != right).subscribe(marginLayoutParams -> {
            marginLayoutParams.rightMargin = right;
            view.setLayoutParams(marginLayoutParams);
        });
    }

    @BindingAdapter({"android:layout_marginBottom"})
    public static void setMarginBottom(View view, int bottom) {
        Observable.just(view.getLayoutParams()).ofType(ViewGroup.MarginLayoutParams.class)
                .filter(marginLayoutParams -> marginLayoutParams.bottomMargin != bottom).subscribe(marginLayoutParams -> {
            marginLayoutParams.bottomMargin = bottom;
            view.setLayoutParams(marginLayoutParams);
        });
    }

    @BindingAdapter({"android:elevation"})
    public static void setElevation(View view, int elevation) {
        Observable.just(elevation).filter(integer -> Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT).subscribe(integer -> {
            ViewCompat.setElevation(view, elevation);
        });
    }

    @BindingAdapter({"elevation"})
    public static void setCardViewElevation(CardView view, int elevation) {
        Observable.just(elevation).filter(integer -> Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT).subscribe(integer -> {
            view.setCardElevation(elevation);
        });
    }

    @BindingAdapter({"touchListener"})
    public static void setOnTouchListener(View view, View.OnTouchListener listener) {
        View.OnTouchListener oldValue = ListenerUtil.trackListener(view, listener, R.id.onTouchListener);
        if (oldValue != null) {
            view.setOnTouchListener(null);
        }
        if (listener != null) {
            view.setOnTouchListener(listener);
        }
    }

    @BindingAdapter({"fragment"})
    public static void addFragment(ViewGroup viewGroup, BaseFragmentViewModel model) {
        Observable.just(viewGroup.getContext()).ofType(FragmentActivity.class).subscribe(fragmentActivity -> {
            BaseViewModelFragment fragment = BaseViewModelFragment.newInstance(model);
            fragmentActivity.getSupportFragmentManager().beginTransaction().replace(viewGroup.getId(), fragment).commit();
        });
    }

    @InverseBindingAdapter(attribute = "revealAnim", event = "revealAnimAttrChanged")
    public static int getRevealAnim(View view) {
        return AnimInit;
    }

    @BindingAdapter(value = {"revealAnim", "point", "revealAnimAttrChanged"}, requireAll = false)
    public static void setRevealAnim(View view, int anim, Point point, InverseBindingListener revealAnimAttrChanged) {
        //TODO 适配5.0以下
        //应该用隐藏的写法   这段纯属学习rx的一些api
        if (anim == AnimShow) {
            showRevealAnim(view, point);
        } else if (anim == AnimHide) {
            hideReveal(view, point, revealAnimAttrChanged);
        }
    }

    private static void hideReveal(View view, Point point, InverseBindingListener revealAnimAttrChanged) {
        revealAnimAttrChanged.onChange();
        Observable.just(view).throttleFirst(1200, TimeUnit.MILLISECONDS)
                .map(view1 -> {
                    ViewGroup vg = (ViewGroup) view;
                    for (int i = 0; i < vg.getChildCount(); i++) {
                        View v = vg.getChildAt(i);
                        ViewPropertyAnimator animator = v.animate()
                                .scaleX(0).scaleY(0)
                                .setInterpolator(new AnticipateOvershootInterpolator())
                                .setDuration(240);
                        animator.setStartDelay(i * 50);
                        animator.start();
                    }
                    return view1;
                }).delay(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 120 : 200, TimeUnit.MILLISECONDS)
                .map(integer -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Animator animator = ViewAnimationUtils.createCircularReveal(view, point.x, point.y, view.getWidth(), 0);
                        animator.setDuration(350);
                        animator.start();
                    } else {
                        view.post(() -> ViewCompat.animate(view).translationX(-view.getWidth()).setDuration(240).start());
                    }
                    return 350L;
                }).delay(350, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View v = vg.getChildAt(i);
                v.setVisibility(View.INVISIBLE);
            }
            view.setBackgroundColor(Color.TRANSPARENT);
        }, throwable -> {
            //滑动过程中view被recyclerView回收
        });
    }

    private static void showRevealAnim(View view, Point point) {
        Observable.just(view).throttleFirst(400, TimeUnit.MILLISECONDS)
                .map(integer -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ThemeBindingConfig.setCardColorTheme(view, ThemeUtil.theme.get());
                        Animator animator = ViewAnimationUtils.createCircularReveal(view, point.x, point.y, 0, view.getWidth());
                        animator.setDuration(350);
                        animator.start();
                    } else {
                        ThemeBindingConfig.setCardColorTheme(view, ThemeUtil.theme.get());
                        view.setTranslationX(-view.getWidth());
                        ViewCompat.animate(view).translationX(0).setDuration(240).start();
                    }
                    return view;
                }).delay(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 120 : 200, TimeUnit.MILLISECONDS).ofType(ViewGroup.class).flatMap(viewGroup -> Observable.range(0, viewGroup.getChildCount())
                .map(viewGroup::getChildAt)
        ).scan(new Pair<Integer, View>(0, null), (integerViewPair, view2) -> new Pair<>(integerViewPair.first + 50, view2))
                .skip(1).flatMap(new Function<Pair<Integer, View>, Observable<View>>() {
            @Override
            public Observable<View> apply(Pair<Integer, View> integerViewPair) throws Exception {
                return Observable.just(integerViewPair.second).delay(integerViewPair.first, TimeUnit.MILLISECONDS);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(view1 -> {
            ViewPropertyAnimator animator = view1.animate()
                    .scaleX(1).scaleY(1)
                    .setInterpolator(new OvershootInterpolator())
                    .setDuration(240);
            view1.setVisibility(View.VISIBLE);
            animator.start();
        });
    }
}
