package com.chuyao.xbo.util;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import java.util.regex.Pattern;

/**
 * Created by chuyao on 16-2-2.
 */
public class HtmlUtil {

    private static final String SHORT_LINK_PATTERN = "";

    public static SpannableStringBuilder convertWeiboText(String text){
        return null;
    }

    private static ForegroundColorSpan matchLinkSpan(String text){
        Pattern pattern = Pattern.compile(SHORT_LINK_PATTERN);
        return null;
    }

}
