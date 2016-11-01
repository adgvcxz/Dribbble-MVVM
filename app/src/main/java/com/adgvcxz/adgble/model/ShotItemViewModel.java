package com.adgvcxz.adgble.model;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableLong;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.adgvcxz.adgble.activity.UserInfoActivity;
import com.adgvcxz.adgble.content.Image;
import com.adgvcxz.adgble.content.Shot;
import com.adgvcxz.adgble.util.ObservableString;
import com.adgvcxz.adgble.util.Util;

import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/31.
 */

public class ShotItemViewModel extends BaseObservable implements Parcelable {

    public final ObservableLong id;
    public final ObservableString avatar;
    public final ObservableString username;
    public final ObservableString title;
    public final ObservableString description;
    public final ObservableField<Date> createdAt;
    public final ObservableString image;
    public final ObservableString thumbnail;
    public final ObservableInt likesCount;
    public final ObservableInt commentsCount;
    public final ObservableInt bucketsCount;
    public final ObservableInt viewsCount;
    public final ObservableInt marginLeft;
    public final ObservableInt marginRight;

    public ShotItemViewModel(Shot shot) {
        this(shot, 0, 0);
    }

    public ShotItemViewModel(Shot shot, int left, int right) {
        id = new ObservableLong(shot.id);
        avatar = new ObservableString(shot.user.avatarUrl);
        username = new ObservableString(shot.user.username);
        title = new ObservableString(shot.title);
        description = new ObservableString(shot.description);
        createdAt = new ObservableField<>(shot.createdAt);
        likesCount = new ObservableInt(shot.likesCount);
        commentsCount = new ObservableInt(shot.commentsCount);
        bucketsCount = new ObservableInt(shot.bucketsCount);
        viewsCount = new ObservableInt(shot.viewsCount);
        marginLeft = new ObservableInt(left);
        marginRight = new ObservableInt(right);
        Image image = shot.images;
        this.image = new ObservableString(image.hidpi != null ? image.hidpi : image.normal != null ? image.normal : image.teaser);
        thumbnail = new ObservableString(image.teaser);
    }

    public View.OnClickListener onClickAvatar = view -> {
        Observable.just(view.getContext()).ofType(Activity.class)
                .subscribe(activity -> {
                    ActivityOptionsCompat opts = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, Util.UserAvatar);
                    Intent intent = new Intent(activity, UserInfoActivity.class);
                    intent.putExtra(Util.DATA, ShotItemViewModel.this);
                    ActivityCompat.startActivity(activity, intent, opts.toBundle());
                });
    };


    //=========================Parcelable========================
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(id, flags);
        parcel.writeParcelable(avatar, flags);
        parcel.writeParcelable(username, flags);
        parcel.writeParcelable(title, flags);
        parcel.writeParcelable(description, flags);
        parcel.writeLong(createdAt.get() == null ? -1 : createdAt.get().getTime());
        parcel.writeParcelable(likesCount, flags);
        parcel.writeParcelable(commentsCount, flags);
        parcel.writeParcelable(bucketsCount, flags);
        parcel.writeParcelable(viewsCount, flags);
        parcel.writeParcelable(marginLeft, flags);
        parcel.writeParcelable(marginRight, flags);
        parcel.writeParcelable(image, flags);
        parcel.writeParcelable(thumbnail, flags);
    }

    public static final Parcelable.Creator<ShotItemViewModel> CREATOR
            = new Parcelable.Creator<ShotItemViewModel>() {

        @Override
        public ShotItemViewModel createFromParcel(Parcel source) {
            return new ShotItemViewModel(source);
        }

        @Override
        public ShotItemViewModel[] newArray(int size) {
            return new ShotItemViewModel[size];
        }
    };

    ShotItemViewModel(Parcel parcel) {
        id = parcel.readParcelable(ObservableLong.class.getClassLoader());
        avatar = parcel.readParcelable(ObservableString.class.getClassLoader());
        username = parcel.readParcelable(ObservableString.class.getClassLoader());
        title = parcel.readParcelable(ObservableString.class.getClassLoader());
        description = parcel.readParcelable(ObservableString.class.getClassLoader());
        long tmpUpdatedAt = parcel.readLong();
        createdAt = new ObservableField<>(tmpUpdatedAt == -1 ? null : new Date(tmpUpdatedAt));
        likesCount = parcel.readParcelable(ObservableInt.class.getClassLoader());
        commentsCount = parcel.readParcelable(ObservableInt.class.getClassLoader());
        bucketsCount = parcel.readParcelable(ObservableInt.class.getClassLoader());
        viewsCount = parcel.readParcelable(ObservableInt.class.getClassLoader());
        marginLeft = parcel.readParcelable(ObservableInt.class.getClassLoader());
        marginRight = parcel.readParcelable(ObservableInt.class.getClassLoader());
        image = parcel.readParcelable(ObservableString.class.getClassLoader());
        thumbnail = parcel.readParcelable(ObservableString.class.getClassLoader());
    }
}
