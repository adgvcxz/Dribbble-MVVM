package com.adgvcxz.adgble.model;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableLong;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.adgvcxz.adgble.binding.ViewBindingConfig;
import com.adgvcxz.adgble.content.Comment;
import com.adgvcxz.adgble.util.ObservableString;

import java.util.Date;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/31.
 */

public class CommentViewModel extends BaseObservable {

    public final ObservableField<UserViewModel> user;
    public final ObservableString body;
    public final ObservableInt likesCount;
    public final ObservableField<Date> createdAt;
    public final ObservableInt anim;
    public final Point point;

    public CommentViewModel(Comment comment) {
        user = new ObservableField<>(new UserViewModel(comment.user));
        body = new ObservableString(comment.body);
        likesCount = new ObservableInt(comment.likesCount);
        createdAt = new ObservableField<>(comment.createdAt);
        anim = new ObservableInt(ViewBindingConfig.AnimInit);
        point = new Point();
    }

    public final View.OnClickListener clickLike = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Click Like", Toast.LENGTH_SHORT).show();
        }
    };
    public final View.OnClickListener clickReply = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Click Reply", Toast.LENGTH_SHORT).show();
        }
    };
    public final View.OnClickListener clickCopy = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Click Copy", Toast.LENGTH_SHORT).show();
        }
    };

    public final View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                point.set((int) motionEvent.getX(), (int) motionEvent.getY());
            }
            return false;
        }
    };
}
