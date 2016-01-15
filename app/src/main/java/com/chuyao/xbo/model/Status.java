package com.chuyao.xbo.model;

/**
 * Created by chuyao on 16-1-15.
 */

import java.util.Arrays;

/**
 * 微博实体
 */
public class Status {

    public String created_at;
    public long id;
    public String mid;
    public String idstr;
    public String text;
    public int textLength;
    public int source_allowclick;
    public int source_type;
    public String source;
    public boolean favorited;
    public boolean truncated;
    public String in_reply_to_status_id;
    public String in_reply_to_user_id;
    public String in_reply_to_screen_name;
    public String[] pic_urls;
    public String thumbnail_pic;
    public String bmiddle_pic;
    public String original_pic;
    public int reposts_count;
    public int comments_count;
    public int attitudes_count;
    public boolean isLongText;
    public int mlevel;
    public int biz_feature;
    public String rid;
    public int userType;

    @Override
    public String toString() {
        return "Status{" +
                "created_at='" + created_at + '\'' +
                ", id=" + id +
                ", mid='" + mid + '\'' +
                ", idstr='" + idstr + '\'' +
                ", text='" + text + '\'' +
                ", textLength=" + textLength +
                ", source_allowclick=" + source_allowclick +
                ", source_type=" + source_type +
                ", source='" + source + '\'' +
                ", favorited=" + favorited +
                ", truncated=" + truncated +
                ", in_reply_to_status_id='" + in_reply_to_status_id + '\'' +
                ", in_reply_to_user_id='" + in_reply_to_user_id + '\'' +
                ", in_reply_to_screen_name='" + in_reply_to_screen_name + '\'' +
                ", pic_urls=" + Arrays.toString(pic_urls) +
                ", thumbnail_pic='" + thumbnail_pic + '\'' +
                ", bmiddle_pic='" + bmiddle_pic + '\'' +
                ", original_pic='" + original_pic + '\'' +
                ", reposts_count=" + reposts_count +
                ", comments_count=" + comments_count +
                ", attitudes_count=" + attitudes_count +
                ", isLongText=" + isLongText +
                ", mlevel=" + mlevel +
                ", biz_feature=" + biz_feature +
                ", rid='" + rid + '\'' +
                ", userType=" + userType +
                '}';
    }
}
