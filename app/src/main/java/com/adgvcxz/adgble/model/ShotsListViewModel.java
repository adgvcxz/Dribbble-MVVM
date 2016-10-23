package com.adgvcxz.adgble.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.adgvcxz.adgble.R;
import com.adgvcxz.adgble.adapter.OnCreateViewListener;
import com.adgvcxz.adgble.api.RetrofitSingleton;
import com.adgvcxz.adgble.binding.ItemView;
import com.adgvcxz.adgble.binding.OnRecyclerViewItemClickListener;
import com.adgvcxz.adgble.util.RxUtil;
import com.android.databinding.library.baseAdapters.BR;

import java.util.List;

import rx.Observable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/23.
 */

public abstract class ShotsListViewModel extends RefreshRecyclerViewModel<ShotItemViewModel> implements OnCreateViewListener {


    public ShotsListViewModel(Context context) {
        super();
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        topMargin = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
    }

    public final ItemView itemView = ItemView.of(BR.item, R.layout.item_shot_large_without_info);

    public final OnRecyclerViewItemClickListener clickListener = new OnRecyclerViewItemClickListener() {
        @Override
        public void onClick(RecyclerView recyclerView, int position, View v) {
            Toast.makeText(recyclerView.getContext(), items.get(position).imageUrl.get(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public Observable<List<ShotItemViewModel>> request(int page) {
        return RetrofitSingleton.getInstance().getShots(page, getSorts()).compose(RxUtil.rxTransformList(shot -> {
            ShotItemViewModel model = new ShotItemViewModel();
            model.imageUrl.set(shot.images.getHeightImageUri());
            model.thumbnail.set(shot.images.teaser);
            model.avatar.set(shot.user.avatar_url);
            return model;
        }));
    }

    @Override
    public void onCreateView() {
        loadFirstData();
        refreshing.set(true);
    }

    abstract String getSorts();
}
