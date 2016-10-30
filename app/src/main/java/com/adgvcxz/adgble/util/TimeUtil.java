package com.adgvcxz.adgble.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/26.
 */

public class TimeUtil {

    public static String getTimeAgo(Date date) {
        int stamp = (int) ((System.currentTimeMillis() - date.getTime()) / 1000);
        if (stamp < 0) {
            stamp = 0;
        }
        int minute = stamp / 60;
        if (minute < 60) {
            minute = minute < 1 ? 1 : minute;
            return minute + " minutes ago";
        } else if (minute < 1440) {
            return minute / 60 + " hours ago";
        } else if (minute < 7 * 1440) {
            return minute / 1440 + " days ago";
        } else {
            return (minute / (30 * 1440)) + " months ago";
        }
    }

    public static String getTimeYMD(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
        return dateFormat.format(date);
    }
}
