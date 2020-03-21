package com.zhiluo.android.yunpu.utils;

import android.util.Log;

/**
 * logcat分段打印长度大于4000byte的数据
 * author Yuan Cheng
 * blog yuancheng91.top
 * email jasoncheng9111@163.com
 * created 2017/3/4 21:47
 */
public class LogTruncationUtil
{
    /**
     * 截断输出日志
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg)
    {
        if (tag == null || tag.length() == 0 || msg == null || msg.length() == 0)
            return;

        int segmentSize = 2 * 1024;
        long length = msg.length();
        if (length <= segmentSize)
        {
            // 长度小于等于限制直接打印
            Log.d(tag, msg);
        }
        else
        {
            while (msg.length() > segmentSize)
            {
                // 循环分段打印日志
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                Log.d(tag, logContent);
            }
            Log.d(tag, msg);// 打印剩余日志
        }
    }
}