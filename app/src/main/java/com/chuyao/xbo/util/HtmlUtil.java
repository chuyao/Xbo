package com.chuyao.xbo.util;

import android.text.style.ForegroundColorSpan;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chuyao on 16-2-2.
 */
public class HtmlUtil {

    //sina short link
    private static final String SHORT_LINK_PATTERN = "http://t.cn/[a-zA-Z_0-9]{7}";
    //
    private static final String SHORT_LINK_REPLACEMENT = "[链接]";

//    public static SpannableStringBuilder convertWeiboText(String text){
//        return null;
//    }

    public static String convertWeiboText(String text){
        String[] list = text.split(SHORT_LINK_PATTERN);
        Pattern p = Pattern.compile(SHORT_LINK_PATTERN);
        Matcher m = p.matcher(text);
        if(m.find()){
            int count = m.groupCount();
        }
        return text.replaceAll(SHORT_LINK_PATTERN, SHORT_LINK_REPLACEMENT);
    }

    private static ForegroundColorSpan matchLinkSpan(String text){
        return null;
    }

}
