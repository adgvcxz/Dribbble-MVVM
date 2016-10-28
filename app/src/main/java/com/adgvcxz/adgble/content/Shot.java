package com.adgvcxz.adgble.content;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/10.
 */

public class Shot extends BaseObservable implements Serializable {

    public int id;
    private String title;
    public String description;
    public int width;
    public int height;

    /*
     * The normal image is typically 400x300, but may be smaller if created before October 4th, 2012.
     * The width and height provide the size of the normal image.
     *
     * The hidpi image may or may not be present, but will always be 800x600.
     *
     * The teaser image is typically 200x150, but may be smaller if created before October 4th, 2012.
     *
     * If the animated attribute of the shot is true,
     * the highest resolution image available (hidpi or normal) will be animated (smaller images will be stills).
     */
    public Images images;

    @SerializedName("views_count")
    private int viewsCount;
    @SerializedName("likes_count")
    private int likesCount;
    @SerializedName("comments_count")
    private int commentsCount;
    @SerializedName("attachments_count")
    public int attachmentsCount;
    @SerializedName("rebounds_count")
    public int reboundsCount;
    @SerializedName("buckets_count")
    public int bucketsCount;

    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;

    public String html_url;
    public String attachments_url;
    public String buckets_url;
    public String comments_url;
    public String likes_url;
    public String projects_url;
    public String rebounds_url;

    public boolean animated;

    public List<String> tags = new ArrayList<>();

    public User user;
    public Team team;

    private int marginLeft;
    private int marginRight;

    @Bindable
    public Images getImages() {
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

    public void setTitle(String title) {
        this.title = title;
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

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof Shot && (this == o || id == ((Shot) o).id);
    }

    @Override
    public int hashCode() {
        return ((Integer) id).hashCode();
    }


    public static class Images extends BaseObservable implements Serializable {
        private String hidpi;
        private String normal;
        private String teaser;

        @Bindable
        public String getHidpi() {
            return hidpi != null ? hidpi : normal != null ? normal : teaser;
        }

        public void setHidpi(String hidpi) {
            this.hidpi = hidpi;
        }

        @Bindable
        public String getNormal() {
            return normal;
        }

        public void setNormal(String normal) {
            this.normal = normal;
        }

        @Bindable
        public String getTeaser() {
            return teaser;
        }

        public void setTeaser(String teaser) {
            this.teaser = teaser;
        }

        // @DebugLog
        public
        @NonNull
        String getType() {
            String name = getHidpi();
            if (name != null) {
                int i = name.lastIndexOf('.');
                if (i != -1 && i + 1 < name.length()) {
                    return name.substring(i + 1);
                }
            }
            return "null";
        }
    }

}
