package com.adgvcxz.adgble.content;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/10.
 */

public class Shot extends BaseObservable implements Parcelable {

    public long id;
    public String title;
    public String description;
    public int width;
    public int height;
    public Image images;
    public int viewsCount;
    public int likesCount;
    public int commentsCount;
    public int attachmentsCount;
    public int reboundsCount;
    public int bucketsCount;
    public Date createdAt;
    public Date updatedAt;
    public String htmlUrl;
    public String attachmentsUrl;
    public String bucketsUrl;
    public String commentsUrl;
    public String likesUrl;
    public String projectsUrl;
    public String reboundsUrl;
    public boolean animated;
    public String reboundSourceUrl;
    public List<String> tags = new ArrayList<>();
    public User user;
    public Team team;
    public int marginLeft;
    public int marginRight;

    public static final Parcelable.Creator<Shot> CREATOR = new Parcelable.Creator<Shot>() {
        public Shot createFromParcel(Parcel source) {
            return new Shot(source);
        }

        public Shot[] newArray(int size) {
            return new Shot[size];
        }
    };

    public Shot() {
    }

    private Shot(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.title = in.readString();
        this.description = in.readString();
        this.width = (Integer) in.readValue(Integer.class.getClassLoader());
        this.height = (Integer) in.readValue(Integer.class.getClassLoader());
        this.images = in.readParcelable(Image.class.getClassLoader());
        this.viewsCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.likesCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.commentsCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.attachmentsCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.reboundsCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.bucketsCount = (Integer) in.readValue(Integer.class.getClassLoader());
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        long tmpUpdatedAt = in.readLong();
        this.updatedAt = tmpUpdatedAt == -1 ? null : new Date(tmpUpdatedAt);
        this.htmlUrl = in.readString();
        this.attachmentsUrl = in.readString();
        this.bucketsUrl = in.readString();
        this.commentsUrl = in.readString();
        this.likesUrl = in.readString();
        this.projectsUrl = in.readString();
        this.reboundsUrl = in.readString();
        this.reboundSourceUrl = in.readString();
        this.tags = new ArrayList<>();
        in.readList(this.tags, List.class.getClassLoader());
        this.user = in.readParcelable(User.class.getClassLoader());
        this.team = in.readParcelable(Team.class.getClassLoader());
        this.animated = in.readByte() != 0;
        this.marginLeft = in.readInt();
        this.marginRight = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeValue(this.width);
        dest.writeValue(this.height);
        dest.writeParcelable(this.images, flags);
        dest.writeValue(this.viewsCount);
        dest.writeValue(this.likesCount);
        dest.writeValue(this.commentsCount);
        dest.writeValue(this.attachmentsCount);
        dest.writeValue(this.reboundsCount);
        dest.writeValue(this.bucketsCount);
        dest.writeLong(createdAt != null ? createdAt.getTime() : -1);
        dest.writeLong(updatedAt != null ? updatedAt.getTime() : -1);
        dest.writeString(this.htmlUrl);
        dest.writeString(this.attachmentsUrl);
        dest.writeString(this.bucketsUrl);
        dest.writeString(this.commentsUrl);
        dest.writeString(this.likesUrl);
        dest.writeString(this.projectsUrl);
        dest.writeString(this.reboundsUrl);
        dest.writeString(this.reboundSourceUrl);
        dest.writeList(this.tags);
        dest.writeParcelable(this.user, 0);
        dest.writeParcelable(this.team, flags);
        dest.writeByte((byte) (animated ? 1 : 0));
        dest.writeInt(this.marginLeft);
        dest.writeInt(this.marginRight);
    }


    @Bindable
    public Image getImages() {
        return images;
    }

    @Bindable
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    @Bindable
    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    @Bindable
    public int getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
    }

    @Bindable
    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    @Bindable
    public int getCommentsCount() {
        return commentsCount;
    }

    @Bindable
    public int getViewsCount() {
        return viewsCount;
    }


    @Bindable
    public int getBucketsCount() {
        return bucketsCount;
    }

    @Bindable
    public String getDescription() {
        return description;
    }
}
