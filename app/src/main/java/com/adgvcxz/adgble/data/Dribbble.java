package com.adgvcxz.adgble.data;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.net.Uri;

import com.android.databinding.library.baseAdapters.BR;
import com.facebook.drawee.view.SimpleDraweeView;


/**
 * zhaowei
 * Created by zhaowei on 2016/10/10.
 */

public class Dribbble extends BaseObservable {

    private String name;
    public ObservableField<String> title = new ObservableField<>();
    private String url;

    public Dribbble(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }
}
