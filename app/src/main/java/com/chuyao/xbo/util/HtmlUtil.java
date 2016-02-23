package com.chuyao.xbo.util;

import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

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

        return text.replaceAll(SHORT_LINK_PATTERN, SHORT_LINK_REPLACEMENT);
    }

    private static ForegroundColorSpan matchLinkSpan(String text){
        Pattern pattern = Pattern.compile(SHORT_LINK_PATTERN);
        return null;
    }

}
