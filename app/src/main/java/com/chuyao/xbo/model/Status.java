package com.chuyao.xbo.model;

/**
 * Created by chuyao on 16-1-15.
 */

import com.sina.weibo.sdk.openapi.models.User;

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
    public long biz_feature;
    public String rid;
    public int userType;
    public User user;

    @Override
    public String toString() {
        return "";
    }
}
