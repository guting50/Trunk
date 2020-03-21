package com.zhiluo.android.yunpu.yslutils;

import android.content.Context;
import android.os.Environment;

import com.zhiluo.android.yunpu.member.manager.bean.MemberInfoBean;


/**
 * Created by ${YSL} on 2018-03-19.
 */

public class Constant {
    public static Context mContext;
    public static final String APK_URL = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/download";
    public static final String APP_NAME="yunshangpu.apk";
    public static MemberInfoBean memberInfoBean;
}
