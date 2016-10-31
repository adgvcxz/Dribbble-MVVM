package com.adgvcxz.adgble.content;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/10.
 */

public class User implements Parcelable {
    public long id;
    public String name;
    public String username;
    public String htmlUrl;
    public String avatarUrl; // 用户头像
    public String bio;
    public String location;
    public Links links;
    public int bucketsCount;
    public int commentsReceivedCount;
    public int followersCount;
    public int followingsCount;
    public int likesCount;
    public int likesReceivedCount;
    public int projectsCount;
    public int reboundsReceivedCount;
    public int shotsCount;
    public int teamsCount;
    public boolean canUploadShot;
    public String type;
    public boolean pro;
    public String bucketsUrl;
    public String followersUrl;
    public String followingUrl;
    public String likesUrl;
    public String shotsUrl;
    public String teamsUrl;
    public Date createdAt;
    public Date updatedAt;

    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User() {
    }

    private User(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.username = in.readString();
        this.htmlUrl = in.readString();
        this.avatarUrl = in.readString();
        this.bio = in.readString();
        this.location = in.readString();
        this.links = in.readParcelable(Links.class.getClassLoader());
        this.bucketsCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.followersCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.followingsCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.likesCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.projectsCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.shotsCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.teamsCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.type = in.readString();
        this.pro = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.bucketsUrl = in.readString();
        this.followersUrl = in.readString();
        this.followingUrl = in.readString();
        this.likesUrl = in.readString();
        this.shotsUrl = in.readString();
        this.teamsUrl = in.readString();
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        long tmpUpdatedAt = in.readLong();
        this.updatedAt = tmpUpdatedAt == -1 ? null : new Date(tmpUpdatedAt);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.username);
        dest.writeString(this.htmlUrl);
        dest.writeString(this.avatarUrl);
        dest.writeString(this.bio);
        dest.writeString(this.location);
        dest.writeParcelable(this.links, flags);
        dest.writeValue(this.bucketsCount);
        dest.writeValue(this.followersCount);
        dest.writeValue(this.followingsCount);
        dest.writeValue(this.likesCount);
        dest.writeValue(this.projectsCount);
        dest.writeValue(this.shotsCount);
        dest.writeValue(this.teamsCount);
        dest.writeString(this.type);
        dest.writeValue(this.pro);
        dest.writeString(this.bucketsUrl);
        dest.writeString(this.followersUrl);
        dest.writeString(this.followingUrl);
        dest.writeString(this.likesUrl);
        dest.writeString(this.shotsUrl);
        dest.writeString(this.teamsUrl);
        dest.writeLong(createdAt != null ? createdAt.getTime() : -1);
        dest.writeLong(updatedAt != null ? updatedAt.getTime() : -1);
    }
}
