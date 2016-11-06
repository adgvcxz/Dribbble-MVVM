package com.adgvcxz.adgble.model;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableLong;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.Toast;

import com.adgvcxz.adgble.activity.UserInfoActivity;
import com.adgvcxz.adgble.content.Image;
import com.adgvcxz.adgble.content.Shot;
import com.adgvcxz.adgble.util.ObservableString;
import com.adgvcxz.adgble.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/31.
 */

public class ShotItemViewModel extends BaseObservable implements Parcelable {

    public final ObservableLong id;
    public ObservableField<UserViewModel> user;
    public final ObservableString title;
    public final ObservableString description;
    public final ObservableField<Date> createdAt;
    public final ObservableString image;
    public final ObservableString thumbnail;
    public final ObservableInt likesCount;
    public final ObservableInt commentsCount;
    public final ObservableInt bucketsCount;
    public final ObservableInt viewsCount;
    public final ObservableArrayList<String> tags;
    public final ObservableString url;
    public final ObservableInt marginLeft;
    public final ObservableInt marginRight;

    public ShotItemViewModel(Shot shot) {
        this(shot, 0, 0);
    }

    public ShotItemViewModel(Shot shot, int left, int right) {
        id = new ObservableLong(shot.id);
        title = new ObservableString(shot.title);
        user = new ObservableField<>(new UserViewModel(shot.user));
        description = new ObservableString(shot.description);
        createdAt = new ObservableField<>(shot.createdAt);
        likesCount = new ObservableInt(shot.likesCount);
        commentsCount = new ObservableInt(shot.commentsCount);
        bucketsCount = new ObservableInt(shot.bucketsCount);
        viewsCount = new ObservableInt(shot.viewsCount);
        tags = new ObservableArrayList<>();
        tags.addAll(shot.tags);
        url = new ObservableString(shot.htmlUrl);
        marginLeft = new ObservableInt(left);
        marginRight = new ObservableInt(right);
        Image image = shot.images;
        this.image = new ObservableString(image.hidpi != null ? image.hidpi : image.normal != null ? image.normal : image.teaser);
        thumbnail = new ObservableString(image.teaser);
    }

    //=============================Extension==========================
    public View.OnClickListener onClickAvatar = view -> Observable.just(view.getContext()).ofType(Activity.class)
            .subscribe(activity -> {
                ActivityOptionsCompat opts = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, Util.UserAvatar);
                Intent intent = new Intent(activity, UserInfoActivity.class);
                intent.putExtra(Util.DATA, this.user.get());
                ActivityCompat.startActivity(activity, intent, opts.toBundle());
            });


    public void onClickTag(View view, int position, String tag) {
        Toast.makeText(view.getContext(), tag, Toast.LENGTH_SHORT).show();
    }

    public void share(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url.get());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);
    }

    public void like(View view) {
        //TODO
        Toast.makeText(view.getContext(), "Likes", Toast.LENGTH_SHORT).show();
    }

    //=========================Parcelable========================
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        List<String> tags = new ArrayList<>();
        tags.addAll(this.tags);
        parcel.writeParcelable(id, flags);
        parcel.writeParcelable(user.get(), flags);
        parcel.writeParcelable(title, flags);
        parcel.writeParcelable(description, flags);
        parcel.writeLong(createdAt.get() == null ? -1 : createdAt.get().getTime());
        parcel.writeParcelable(likesCount, flags);
        parcel.writeParcelable(commentsCount, flags);
        parcel.writeParcelable(bucketsCount, flags);
        parcel.writeParcelable(viewsCount, flags);
        parcel.writeList(tags);
        parcel.writeParcelable(url, flags);
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

    private ShotItemViewModel(Parcel parcel) {
        id = parcel.readParcelable(ObservableLong.class.getClassLoader());
        user = new ObservableField<>(parcel.readParcelable(UserViewModel.class.getClassLoader()));
        title = parcel.readParcelable(ObservableString.class.getClassLoader());
        description = parcel.readParcelable(ObservableString.class.getClassLoader());
        long tmpUpdatedAt = parcel.readLong();
        createdAt = new ObservableField<>(tmpUpdatedAt == -1 ? null : new Date(tmpUpdatedAt));
        likesCount = parcel.readParcelable(ObservableInt.class.getClassLoader());
        commentsCount = parcel.readParcelable(ObservableInt.class.getClassLoader());
        bucketsCount = parcel.readParcelable(ObservableInt.class.getClassLoader());
        viewsCount = parcel.readParcelable(ObservableInt.class.getClassLoader());
        ArrayList<String> tags = parcel.readArrayList(String.class.getClassLoader());
        this.tags = new ObservableArrayList<>();
        this.tags.addAll(tags);
        url = parcel.readParcelable(ObservableString.class.getClassLoader());
        marginLeft = parcel.readParcelable(ObservableInt.class.getClassLoader());
        marginRight = parcel.readParcelable(ObservableInt.class.getClassLoader());
        image = parcel.readParcelable(ObservableString.class.getClassLoader());
        thumbnail = parcel.readParcelable(ObservableString.class.getClassLoader());
    }
}
