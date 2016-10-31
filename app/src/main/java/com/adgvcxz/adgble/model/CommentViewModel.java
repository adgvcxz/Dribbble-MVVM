package com.adgvcxz.adgble.model;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableLong;

import com.adgvcxz.adgble.content.Comment;
import com.adgvcxz.adgble.util.ObservableString;

import java.util.Date;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/31.
 */

public class CommentViewModel extends BaseObservable {

    public final ObservableLong userId;
    public final ObservableString username;
    public final ObservableString avatar;
    public final ObservableString body;
    public final ObservableInt likesCount;
    public final ObservableField<Date> createdAt;

    public CommentViewModel(Comment comment) {
        userId = new ObservableLong(comment.user.id);
        username = new ObservableString(comment.user.username);
        avatar = new ObservableString(comment.user.avatarUrl);
        body = new ObservableString(comment.body);
        likesCount = new ObservableInt(comment.likesCount);
        createdAt = new ObservableField<>(comment.createdAt);
    }
}
