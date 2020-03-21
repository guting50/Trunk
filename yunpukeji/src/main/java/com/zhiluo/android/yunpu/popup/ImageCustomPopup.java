package com.zhiluo.android.yunpu.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.zhiluo.android.yunpu.R;

public class ImageCustomPopup extends PopupWindow {

    public ImageCustomPopup(Context context, View parent, String url) {
        View view = View.inflate(context, R.layout.popup_image_custom, null);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
//        setHeight(ScreenUtils.getScreenHeight() - BarUtils.getStatusBarHeight());
        //PopupWindow响应返回键，那么必须给PopupWindow设置一个背景才行
        setBackgroundDrawable(new ColorDrawable(0x00000000));
        setFocusable(true);
        //点击区域外不关闭弹框
        setOutsideTouchable(true);
        setContentView(view);
        showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        update();

        ImageView iv_bg = (ImageView) view.findViewById(R.id.iv_bg);
        Glide.with(context).load(url).into(iv_bg);
    }
}
