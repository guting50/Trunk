package com.zhiluo.android.yunpu.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhiluo.android.yunpu.R;

/**
 * 自定义Toast
 * 作者：罗咏哲 on 2017/8/15 15:13.
 * 邮箱：137615198@qq.com
 */

public class CustomToast {
    private Toast mToast;


    /**
     * @param context ,
     * @return .
     * 用单例，避免多重Toast弹出
     */
    private Toast getTosat(Context context) {
        if (mToast == null) {
            mToast = new Toast(context);
            return mToast;
        } else {
            return mToast;
        }
    }

    public CustomToast(Context context, CharSequence text, int duration) {
        View v = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        TextView textView = (TextView) v.findViewById(R.id.tv_toast);
        textView.setText(text);
        getTosat(context);
        mToast.setDuration(duration);
        mToast.setView(v);
    }

    public CustomToast(Context context, int resId, int duration) {
        View v = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        TextView textView = (TextView) v.findViewById(R.id.tv_toast);
        textView.setText(String.valueOf(resId));
        getTosat(context);
        mToast.setDuration(duration);
        mToast.setView(v);
    }

    public static CustomToast makeText(Context context, CharSequence text, int duration) {
        return new CustomToast(context, text, duration);
    }

    public static CustomToast makeText(Context context, int resId, int duration) {
        return new CustomToast(context, resId, duration);
    }



    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            mToast.setGravity(gravity, xOffset, yOffset);
        }
    }
}
