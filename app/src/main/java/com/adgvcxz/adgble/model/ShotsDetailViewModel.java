package com.adgvcxz.adgble.model;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.api.RetrofitSingleton;
import com.adgvcxz.adgble.binding.ItemView;
import com.adgvcxz.adgble.binding.MutliItemViewSelector;
import com.adgvcxz.adgble.binding.OnRecyclerViewItemClickListener;
import com.adgvcxz.adgble.binding.ViewBindingConfig;
import com.adgvcxz.adgble.util.RxUtil;
import com.adgvcxz.adgble.util.Util;
import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/25.
 */

public class ShotsDetailViewModel extends BaseRecyclerViewModel<BaseObservable> {

    public final ShotItemViewModel shotItemViewModel;
    public final ObservableInt translationY = new ObservableInt();
    public final ObservableInt firstItemTop = new ObservableInt();
    public final ObservableInt elevation = new ObservableInt();

    public final ItemView commentItemView = ItemView.of(BR.item, R.layout.item_shot_comment);
    public final ItemView headerItemView = ItemView.of(BR.item, R.layout.item_shot_detail_header);

    public final MutliItemViewSelector itemView = MutliItemViewSelector.newInstance((position, item) -> position == 0 ? headerItemView : commentItemView);
    public int animPosition = -1;
    public Activity activity;

    public final OnRecyclerViewItemClickListener clickListener = (recyclerView, position, v) -> {
        Observable.just(position).map(items::get).ofType(CommentViewModel.class)
                .subscribe(commentViewModel -> {
                    if (animPosition >= 1 && animPosition != position) {
                        CommentViewModel model = (CommentViewModel) items.get(animPosition);
                        if (model.anim.get() == ViewBindingConfig.AnimShow) {
                            model.anim.set(ViewBindingConfig.AnimHide);
                        }
                    }
                    animPosition = position;
                    int anim = commentViewModel.anim.get();
                    if (anim == ViewBindingConfig.AnimInit || anim == ViewBindingConfig.AnimHide) {
                        commentViewModel.anim.set(ViewBindingConfig.AnimShow);
                    } else {
                        animPosition = -1;
                        commentViewModel.anim.set(ViewBindingConfig.AnimHide);
                    }
                });
    };


    public RecyclerView.OnScrollListener statusListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            onScrollListener.onScrolled(recyclerView, dx, dy);
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            onScrollListener.onScrollStateChanged(recyclerView, newState);
            if (animPosition != -1) {
                hideLikeLayout();
            }
        }
    };

    public View.OnClickListener onBackClickListener = view -> activity.onBackPressed();

    public ShotsDetailViewModel(Activity activity, ShotItemViewModel model) {
        this.activity = activity;
        int topMargin = activity.getResources().getDimensionPixelSize(R.dimen.detail_toolbar_height);
        final int margin = topMargin;
        shotItemViewModel = model;
        int temp = activity.getResources().getDimensionPixelSize(R.dimen.detail_toolbar_height) - Util.getActionBarHeight(activity) * 2;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            temp -= Util.getStatusBarHeight(activity);
//        }
        int height = temp;
        this.topMarginSelector = new ObservableField<>((view, position) -> position == 0 ? margin : 0);
        float density = activity.getResources().getDisplayMetrics().density;
        RxUtil.toObservableInt(firstItemTop).filter(integer -> integer != -1).map(integer -> -integer + topMargin)
                .map(integer -> integer > height ? height : integer)
                .filter(integer -> integer != translationY.get()).subscribe(integer -> {
            translationY.set(integer);
            elevation.set((int) (8 * integer * density / height));
        });
        items.add(shotItemViewModel);
        resetStart = items.size();
        loadData();
    }

    public void hideLikeLayout() {
        CommentViewModel model = (CommentViewModel) items.get(animPosition);
        if (model.anim.get() == ViewBindingConfig.AnimShow) {
            animPosition = -1;
            model.anim.set(ViewBindingConfig.AnimHide);
        }
    }

    @Override
    public Observable<List<BaseObservable>> request(int page) {
        return RetrofitSingleton.getInstance().getComments(shotItemViewModel.id.get(), page).flatMapIterable(comments -> comments)
                .collect((Callable<List<BaseObservable>>) ArrayList::new, (models, comment) -> models.add(new CommentViewModel(comment)))
                .toObservable();
    }
}
