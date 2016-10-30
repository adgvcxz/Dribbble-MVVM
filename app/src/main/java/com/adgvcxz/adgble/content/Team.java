package com.adgvcxz.adgble.content;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/10.
 */

public class Team implements Parcelable {
    public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
        public Team createFromParcel(Parcel source) {
            return new Team(source);
        }

        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
    public Long id;
    public String name;
    public String username;
    public String htmlUrl;
    public String avatarUrl;
    public String bio;
    public String location;
    public Links links;
    public Integer bucketsCount;
    public Integer followersCount;
    public Integer followingsCount;
    public Integer likesCount;
    public Integer membersCount;
    public Integer projectsCount;
    public Integer shotsCount;
    public String type;
    public Boolean pro;
    public String bucketsUrl;
    public String followersUrl;
    public String followingUrl;
    public String likesUrl;
    public String membersUrl;
    public String shotsUrl;
    public String teamShotsUrl;
    public Date createdAt;
    public Date updatedAt;

    public Team() {
    }

    private Team(Parcel in) {
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
        this.membersCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.projectsCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.shotsCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.type = in.readString();
        this.pro = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.bucketsUrl = in.readString();
        this.followersUrl = in.readString();
        this.followingUrl = in.readString();
        this.likesUrl = in.readString();
        this.membersUrl = in.readString();
        this.shotsUrl = in.readString();
        this.teamShotsUrl = in.readString();
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
        dest.writeParcelable(this.links, 0);
        dest.writeValue(this.bucketsCount);
        dest.writeValue(this.followersCount);
        dest.writeValue(this.followingsCount);
        dest.writeValue(this.likesCount);
        dest.writeValue(this.membersCount);
        dest.writeValue(this.projectsCount);
        dest.writeValue(this.shotsCount);
        dest.writeString(this.type);
        dest.writeValue(this.pro);
        dest.writeString(this.bucketsUrl);
        dest.writeString(this.followersUrl);
        dest.writeString(this.followingUrl);
        dest.writeString(this.likesUrl);
        dest.writeString(this.membersUrl);
        dest.writeString(this.shotsUrl);
        dest.writeString(this.teamShotsUrl);
        dest.writeLong(createdAt != null ? createdAt.getTime() : -1);
        dest.writeLong(updatedAt != null ? updatedAt.getTime() : -1);
    }
}
