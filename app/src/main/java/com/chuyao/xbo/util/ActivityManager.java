package com.chuyao.xbo.util;

import android.content.Context;

import java.util.Vector;

/**
 * Created by chuyao on 16-1-14.
 */
public class ActivityManager {

    private static Vector actVector = new Vector();
    private static Context ctx;

    public static void init(Context ctx){
        ActivityManager.ctx = ctx;
    }

    public static void add(String actName){
        actVector.add(actName);
    }

    public static void remove(String actName){
        actVector.remove(actName);
    }

    public static void clear(){
        actVector.clear();
    }

}
