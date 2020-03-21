package com.zhiluo.android.yunpu.yslutils;

import android.util.Log;

/**
 * Created by YSL on 2018-03-13.
 */

public class LogUtils {
    private static final boolean SWITCHER = true;

    public static synchronized void Le(String msg) {
        if (SWITCHER) {
            Log.e("LOG_MES_E", msg);
        }
    }

    public static synchronized void Ld(String msg) {
        if (SWITCHER) {
            Log.d("LOG_MES_D", msg);
        }
    }

    public static synchronized void Li(String msg) {
        if (SWITCHER) {
            Log.i("LOG_MES_I", msg);
        }
    }

    public static synchronized void Lb(String msg) {
        if (SWITCHER) {
            Log.i("Broad_Cast", msg);
        }
    }
}
