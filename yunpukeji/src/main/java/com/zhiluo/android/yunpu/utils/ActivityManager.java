package com.zhiluo.android.yunpu.utils;

import android.app.Activity;

import com.zhiluo.android.yunpu.member.manager.activity.MemberListActivity;

import java.util.HashSet;

/**
 * Created by YSL on 2018-08-08.
 */

public class ActivityManager {

    private static ActivityManager instance = new ActivityManager();
    private static HashSet<Activity> hashSet = new HashSet<>();
    private ActivityManager(){

    }
    public static ActivityManager getInstance() {
        return instance;
    }

    /**
     * 每一个Activity 在 onCreate 方法的时候，可以装入当前this
     * @param activity
     */
    public void addActivity(Activity activity) {
        try {
            hashSet.add(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用此方法用于销毁所有的Activity，然后我们在调用此方法之前，调到登录的Activity
     */
    public void exit() {
        try {
            for (Activity activity : hashSet) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除activty
     *
     * @param activity**/

    public void removeActivity(Activity activity){
        try {
            hashSet.remove(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
