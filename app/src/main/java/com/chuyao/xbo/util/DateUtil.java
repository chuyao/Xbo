package com.chuyao.xbo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by chuyao on 16-2-22.
 */
public class DateUtil {

    private static final String DATE_FORMAT_DEFAULT = "EEE MMM dd HH:mm:ss Z yyyy";
    private static final String DATE_FORMAT_1 = "yyyy-MM-dd HH:mm";
    private static final String DATE_FORMAT_2 = "MM-dd HH:mm";
    private static final String DATE_FORMAT_3 = "HH:mm";

    public static String formatCommonDate(String dateStr){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_DEFAULT, Locale.ENGLISH);
        try {
            Date date = simpleDateFormat.parse(dateStr);
            int thisYear = calendar.get(Calendar.YEAR);
            int today = calendar.get(Calendar.DAY_OF_YEAR);
            calendar.setTime(date);
            int srcYear = calendar.get(Calendar.YEAR);
            int srcDay = calendar.get(Calendar.DAY_OF_YEAR);
            if(thisYear == srcYear){
                if(today == srcDay){
                    simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_3);
                }else{
                    simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_2);
                }
            }else{
                simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_1);
            }
            return simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

}
