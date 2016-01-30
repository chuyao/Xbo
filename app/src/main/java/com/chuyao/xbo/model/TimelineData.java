package com.chuyao.xbo.model;

import java.util.List;

/**
 * Created by chuyao on 16-1-30.
 */
public class TimelineData {

    public List<Status> statuses;
    public boolean hasvisible;
    public long previous_cursor;
    public long next_cursor;
    public int total_number;
    public int interval;
    public int uve_blank;
    public long since_id;
    public long max_id;
    public int has_unread;

}
