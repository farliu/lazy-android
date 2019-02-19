package com.ofcoder.lazy.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Yzker on 2017/1/18.
 */
public class SharedPreferenceUtil {
    static SharedPreferences sp;
    static SharedPreferences.Editor editor;

    /**
     * 根据key得到存在SharedPreferences里的值
     * @param context
     * @param spKey
     * @return
     */
    public static String getSpValueByKey(Context context, String spKey){
        if(sp==null){
            sp = context.getSharedPreferences("phoneInfo",0);
        }
        return sp.getString(spKey,"");
    }

    /**
     * 将值写入SharedPreferences
     */
    public static void setSpValueByKey(Context context, String spKey, String spValue){
        if(editor==null){
            sp = context.getSharedPreferences("phoneInfo",0);
            editor = sp.edit();
        }
        editor.putString(spKey,spValue);
        editor.commit();
    }
}
