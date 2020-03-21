package com.zhiluo.android.yunpu.ui.view;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 * 自定义edittext用于解决listview嵌套edittext时的问题
 */
public class MyEdittextt extends android.support.v7.widget.AppCompatEditText {
    private List<TextWatcher> watchers = new ArrayList<>();

    public MyEdittextt(Context context) {
        super(context);

    }

    public MyEdittextt(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEdittextt(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void addTextChangedListener(TextWatcher watcher) {
        watchers.add(watcher);
        super.addTextChangedListener(watcher);
    }

    public void removeTextChangedListener() {
        for (int i = 0; i < watchers.size(); i++) {
            removeTextChangedListener(watchers.get(i));

        }
    }
}
