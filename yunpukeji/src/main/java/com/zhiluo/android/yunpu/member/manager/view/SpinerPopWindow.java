package com.zhiluo.android.yunpu.member.add.view;

import android.content.Context;
import android.widget.ListView;
import android.widget.PopupWindow;

/**
 * 作者：罗咏哲 on 2017/7/19 09:45.
 * 邮箱：137615198@qq.com
 */

public class SpinerPopWindow extends PopupWindow  {
    private Context mContext;
    private ListView mListView;

    public SpinerPopWindow(Context context) {
        super(context);
        mContext = context;
        init();
    }

    private void init() {

    }
}
