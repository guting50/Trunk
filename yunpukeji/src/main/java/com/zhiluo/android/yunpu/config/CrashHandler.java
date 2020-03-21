package com.zhiluo.android.yunpu.config;

import android.content.Context;
import android.util.Log;

import com.zhiluo.android.yunpu.BuildConfig;

import java.util.concurrent.TimeoutException;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";
    private Context mContext;
    volatile private static CrashHandler sCrashHandler;
    final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

    private CrashHandler(Context context) {
        mContext = context;
    }

    public static CrashHandler getInstance(Context context){
        if (sCrashHandler == null) {
            synchronized (CrashHandler.class){
                if (sCrashHandler == null) {
                    //使用Application Context
                    sCrashHandler=new CrashHandler(context.getApplicationContext());
                }
            }
        }
        return sCrashHandler;
    }


    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (BuildConfig.DEBUG) Log.d(TAG, "uncaughtException: "+t.getName()+" "+e.getMessage());
        //这里可以将异常信息保存或上传
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                if (t.getName().equals("FinalizerWatchdogDaemon") && e instanceof TimeoutException) {
                    //ignore it
                } else {
                    defaultUncaughtExceptionHandler.uncaughtException(t, e);
                }
            }
        });

        //可根据情况选择是否干掉当前的进程
//        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
