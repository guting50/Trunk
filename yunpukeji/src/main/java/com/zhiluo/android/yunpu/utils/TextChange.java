package com.zhiluo.android.yunpu.utils;

/**
 * Created by YSL on 2018-11-22.
 */

public class TextChange {

    //指定位子插入字符
    public static String insertString(int i, String str1, String str2){
        StringBuilder sb = new StringBuilder(str1);//构造一个StringBuilder对象
        sb.insert(i, str2);//在指定的位置1，插入指定的字符串
        str1 = sb.toString();
        return str1;
    }

    /**
     * 去掉指定位子字符
     * @param i
     * @param string
     * @return
     */
    public static String subString(int i,String string){
        String result = "";
        result = string.substring(0,i-1) +string.substring(i);
        return result;
    }
}
