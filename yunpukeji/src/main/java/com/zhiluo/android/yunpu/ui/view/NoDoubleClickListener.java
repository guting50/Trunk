package com.zhiluo.android.yunpu.ui.view;

import android.view.View;

import java.util.Calendar;

/**
 * Created by yinshangyong on 2016/10/8.
 * 自定义OnclicListener跳转时用户连续点击导致多次打开同一界面
 */
public abstract class NoDoubleClickListener implements View.OnClickListener
{
    public static final int MIN_CLICK_DELAY_TIME = 1500;
    private long lastClickTime = 0;

    @Override
    public void onClick(View v)
    {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME)
        {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        }
    }

    public abstract void onNoDoubleClick(View v);
}
