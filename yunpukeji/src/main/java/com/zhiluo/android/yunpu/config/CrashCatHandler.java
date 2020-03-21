package com.zhiluo.android.yunpu.config;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 捕获未捕获得异常
 * Created by dell on 2018/3/7.
 */

public class CrashCatHandler implements Thread.UncaughtExceptionHandler {
    private Context mContext;

    public CrashCatHandler(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //具体处理按需求实现
        Log.i("lyz", "uncaughtException: 执行了");
        e.printStackTrace();
        restartApplication();
    }

    public void init() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private void restartApplication() {
        final Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }

}
