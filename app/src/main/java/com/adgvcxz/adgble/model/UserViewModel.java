package com.adgvcxz.adgble.model;

import android.databinding.BaseObservable;
import android.databinding.ObservableLong;
import android.os.Parcel;
import android.os.Parcelable;

import com.adgvcxz.adgble.content.User;
import com.adgvcxz.adgble.util.ObservableString;

/**
 * zhaowei
 * Created by zhaowei on 2016/11/3.
 */

public class UserViewModel extends BaseObservable implements Parcelable {

    public final ObservableLong id;
    public final ObservableString username;
    public final ObservableString avatar;

    public UserViewModel(User user) {
        id = new ObservableLong(user.id);
        username = new ObservableString(user.username);
        avatar = new ObservableString(user.avatarUrl);
    }


    public static final Parcelable.Creator<UserViewModel> CREATOR
            = new Parcelable.Creator<UserViewModel>() {

        @Override
        public UserViewModel createFromParcel(Parcel source) {
            return new UserViewModel(source);
        }

        @Override
        public UserViewModel[] newArray(int size) {
            return new UserViewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    private UserViewModel(Parcel parcel) {
        id = parcel.readParcelable(ObservableLong.class.getClassLoader());
        username = parcel.readParcelable(ObservableString.class.getClassLoader());
        avatar = parcel.readParcelable(ObservableString.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(id, flags);
        parcel.writeParcelable(username, flags);
        parcel.writeParcelable(avatar, flags);
    }
}
