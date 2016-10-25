package com.adgvcxz.adgble.content;

import java.io.Serializable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/10.
 */

public class Team implements Serializable {
    public int id;
    public String name;
    public String username;
    public String html_url;
    public String avatar_url;
    public String bio;
    public String location;

    public Links links;

    public int buckets_count;
    public int comments_received_count;
    public int followers_count;
    public int followings_count;
    public int likes_count;
    public int likes_received_count;
    public int members_count;
    public int projects_count;
    public int rebounds_received_count;
    public int shots_count;

    public boolean can_upload_shot;
    public String type;
    public boolean pro;

    public String buckets_url;
    public String followers_url;
    public String following_url;
    public String likes_url;
    public String members_url;
    public String shots_url;
    public String team_shots_url;
    public String created_at;
    public String updated_at;
}
