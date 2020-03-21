package com.zhiluo.android.yunpu.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zhiluo.android.yunpu.R;

/**
 * ListView简单封装
 * 作者：罗咏哲 on 2017/8/30 10:14.
 * 邮箱：137615198@qq.com
 */

public class BaseListView extends ListView {
    public BaseListView(Context context) {
        super(context);
        init();
    }

    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    /**
     * 设置一些属性
     */
    @SuppressLint("ResourceType")
    private void init() {
        //分割线颜色
        this.setDivider(new ColorDrawable(getResources().getColor(R.color.color_device_line_gray)));
        //分割线宽度
        this.setDividerHeight(2);
        //背景颜色
       // this.setBackgroundColor(Color.WHITE);
        //隐藏滚动条
        this.setVerticalScrollBarEnabled(true);
        this.setEmptyView(findViewById(R.layout.empty_list_view));
    }


}
