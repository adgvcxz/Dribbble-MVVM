package com.adgvcxz.adgble.binding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.adgvcxz.adgble.R;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/12.
 */

public class LayoutManager {

    protected LayoutManager(){

    }

    public interface LayoutManagerFactory {
        RecyclerView.LayoutManager create(RecyclerView recyclerView);
    }

    public static LayoutManagerFactory linear() {
        return recyclerView -> new LinearLayoutManager(recyclerView.getContext());
    }

    public static LayoutManagerFactory gridLoadMore2() {
        return recyclerView -> {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), 2);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = recyclerView.getAdapter().getItemViewType(position);
                    return type == R.layout.item_load_more ? 2 : 1;
                }
            });
            return gridLayoutManager;
        };
    }
}
