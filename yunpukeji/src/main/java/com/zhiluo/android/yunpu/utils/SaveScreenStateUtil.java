package com.zhiluo.android.yunpu.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 保存筛选数据
 */
public class SaveScreenStateUtil {
    private Context mContext;
    private String mFileName;

    public SaveScreenStateUtil(Context mContext) {
        this.mContext = mContext;
    }

    public SaveScreenStateUtil(Context mContext, String mFileName) {
        this.mContext = mContext;
        this.mFileName = mFileName;
    }

    public void put(String key, String value) {
        SharedPreferences sp = mContext.getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String get(String key, String defaultValue) {
        SharedPreferences sp = mContext.getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    public void clean() {
        SharedPreferences sp = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().apply();
    }
    public void clean(String fileName) {
        SharedPreferences sp = mContext.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().apply();
    }
}
