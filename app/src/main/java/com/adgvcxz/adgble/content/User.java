package com.adgvcxz.adgble.content;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/10.
 */

public class User extends BaseObservable implements Serializable {
    public int id;
    public String name;
    private String username;
    public String html_url;

    @SerializedName("avatar_url")
    private String avatarUrl; // 用户头像

    public String bio;
    public String location;

    public Links links;

    public int buckets_count;
    public int comments_received_count;
    public int followers_count;
    public int followings_count;
    public int likes_count;
    public int likes_received_count;
    public int projects_count;
    public int rebounds_received_count;
    public int shots_count;
    public int teams_count;

    public boolean can_upload_shot;
    public String type;
    public boolean pro;

    public String buckets_url;
    public String followers_url;
    public String following_url;
    public String likes_url;
    public String shots_url;
    public String teams_url;
    public String created_at;
    public String updated_at;

    @Bindable
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
