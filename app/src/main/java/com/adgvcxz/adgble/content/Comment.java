package com.adgvcxz.adgble.content;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.io.Serializable;
import java.util.Date;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/30.
 */

public class Comment extends BaseObservable implements Serializable {

    public long id;
    public String body;
    public Integer likesCount;
    public String likesUrl;
    public Date createdAt;
    public Date updatedAt;
    public User user;

    @Bindable
    public String getBody() {
        return body;
    }
}
