package com.zhiluo.android.yunpu.ui.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;

/**
 * 作者：罗咏哲 on 2017/6/18 10:30.
 * 邮箱：137615198@qq.com
 */

public class CustomPopWindow extends PopupWindow implements View.OnClickListener {
    private View mView;//PopWindow菜单布局
    private OnItemClickListener mListener;
    private TextView tvSelect, tvPhoto, tvCancel;

    public CustomPopWindow(Context context) {
        super(context);
        init(context);
        setPopWindow();
        tvPhoto.setOnClickListener(this);
        tvSelect.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mView = inflater.inflate(R.layout.custom_popwindow_layout, null);
        tvPhoto = (TextView) mView.findViewById(R.id.tv_popwindow_photo);
        tvSelect = (TextView) mView.findViewById(R.id.tv_popwindow_select);
        tvCancel = (TextView) mView.findViewById(R.id.tv_popwindow_cancel);
    }

    private void setPopWindow() {
        this.setContentView(mView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(RadioGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);//设置允许弹出窗口
        this.setAnimationStyle(R.style.PhotoSelectorPopWindowStyle);//设置弹出动画
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景透明
        mView.setOnTouchListener(new View.OnTouchListener() {//设置触摸位置在窗口外则销毁
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int height = mView.findViewById(R.id.pop_layout).getTop();
                int y = (int) motionEvent.getY();
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    public interface OnItemClickListener {
        void setOnItemClick(View v);
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.setOnItemClick(view);
        }
    }
}
