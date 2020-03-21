package com.zhiluo.android.yunpu.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.ListView;

import com.zhiluo.android.yunpu.R;

/**
 * ListView简单封装
 * 作者：罗咏哲 on 2017/8/30 10:14.
 * 邮箱：137615198@qq.com
 */

public class MyListView extends ListView {
    public MyListView(Context context) {
        super(context);
        init();
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2
                , MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    /**
     * 设置一些属性
     */
    private void init() {
//        //分割线颜色
//        this.setDivider(new ColorDrawable(getResources().getColor(R.color.color_device_line_gray)));
//        //分割线宽度
//        this.setDividerHeight(2);
        //背景颜色
        this.setBackgroundColor(Color.WHITE);
        //隐藏滚动条
        this.setVerticalScrollBarEnabled(true);
    }
}
