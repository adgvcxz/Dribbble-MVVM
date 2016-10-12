package com.adgvcxz.adgble.binding;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
}
