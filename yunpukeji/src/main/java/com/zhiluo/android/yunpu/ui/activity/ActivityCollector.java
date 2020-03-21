package com.zhiluo.android.yunpu.ui.activity;

import android.app.Activity;

import com.zhiluo.android.yunpu.login.activity.LoginActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于管理所有的活动
 * Created by Cheng on 2017/2/13.
 */

public class ActivityCollector {
    public static List<Activity> mActivities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        mActivities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        mActivities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : mActivities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
