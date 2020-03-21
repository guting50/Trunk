package com.zhiluo.android.yunpu.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：罗咏哲 on 2017/12/5 17:56.
 * 邮箱：137615198@qq.com
 * 用SharedPreferences保存形如List<T>的数据到本地</>
 */

public class SaveListData {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public SaveListData(Context mContext, String preferenceName) {
        preferences = mContext.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    /**
     * 保存List
     *
     * @param tag
     * @param datalist
     */
    public <T> void saveDataList(String tag, List<T> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        editor.clear();
        editor.putString(tag, strJson);
        editor.commit();
    }

    /**
     * 获取List
     *
     * @param tag
     * @return
     */
    public <T> List<T> getDataList(String tag) {
        List<T> datalist = new ArrayList<T>();
        String strJson = preferences.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = CommonFun.JsonToObj(strJson, new TypeToken<List<T>>() {
        }.getType());
        return datalist;
    }
}
