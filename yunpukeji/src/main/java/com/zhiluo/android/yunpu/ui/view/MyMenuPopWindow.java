package com.zhiluo.android.yunpu.ui.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;

/**
 * 自定义菜单PopWindow
 * 作者：罗咏哲 on 2017/7/28 18:01.
 * 邮箱：137615198@qq.com
 */

public class MyMenuPopWindow extends PopupWindow implements View.OnClickListener {
    private View mView;//PopWindow菜单布局
    private OnItemClickListener mListener;
    private TextView tvFirst, tvSecond, tvThird, tvFourth;
    private LinearLayout llFirst, llSecond, llThird, llFourth;

    /**
     * 第一列显示文本，第二列显示文本，第三列显示文本
     */
    private String mFirstText, mSecondText, mThirdText, mFourthText;

    public MyMenuPopWindow(Context context, String mFirstText, String mSecondText, String mThirdText) {
        super(context);
        this.mFirstText = mFirstText;
        this.mSecondText = mSecondText;
        this.mThirdText = mThirdText;
        init(context);
        setMenuPopWindow();
        tvFirst.setOnClickListener(this);
        tvSecond.setOnClickListener(this);
        tvThird.setOnClickListener(this);
    }

    public MyMenuPopWindow(Context context, String mFirstText, String mSecondText, String mThirdText, String mFourthText) {
        super(context);
        this.mFirstText = mFirstText;
        this.mSecondText = mSecondText;
        this.mThirdText = mThirdText;
        this.mFourthText = mFourthText;
        init(context);
        setMenuPopWindow();
        tvFirst.setOnClickListener(this);
        tvSecond.setOnClickListener(this);
        tvThird.setOnClickListener(this);
        tvFourth.setOnClickListener(this);
    }

    /**
     * 初始化布局
     *
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mView = inflater.inflate(R.layout.menu_pop_window_layout, null);
        tvFirst = (TextView) mView.findViewById(R.id.tv_menu_pop_first);
        tvSecond = (TextView) mView.findViewById(R.id.tv_menu_pop_second);
        tvThird = (TextView) mView.findViewById(R.id.tv_menu_pop_third);
        tvFourth = (TextView) mView.findViewById(R.id.tv_menu_pop_fourth);
        llFirst = (LinearLayout) mView.findViewById(R.id.ll_pop_menu_first);
        llSecond = (LinearLayout) mView.findViewById(R.id.ll_pop_menu_second);
        llThird = (LinearLayout) mView.findViewById(R.id.ll_pop_menu_third);
        llFourth = (LinearLayout) mView.findViewById(R.id.ll_pop_menu_fourth);

        TextView tvLine = (TextView) mView.findViewById(R.id.tv_line);
        if (mFirstText == null) {
            llFirst.setVisibility(View.GONE);
//            tvFirst.setVisibility(View.GONE);
        } else {
            tvFirst.setText(mFirstText);
        }
        if (mSecondText == null) {
            llSecond.setVisibility(View.GONE);
//            tvSecond.setVisibility(View.GONE);
        } else {
            tvSecond.setText(mSecondText);
        }
        if (mThirdText == null) {
            llThird.setVisibility(View.GONE);
//            tvThird.setVisibility(View.GONE);
//            tvLine.setVisibility(View.GONE);
        } else {
            tvThird.setText(mThirdText);
        }
        if (mFourthText == null) {
            llFourth.setVisibility(View.GONE);
        } else {
            tvFourth.setText(mFourthText);
        }
    }

    private void setMenuPopWindow() {
        this.setContentView(mView);
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(RadioGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);//设置允许弹出窗口
        this.setAnimationStyle(R.style.PopWindowAnimStyle);//设置弹出动画
        this.setBackgroundDrawable(new ColorDrawable(0xffffffff));//设置背景透明
        mView.setOnTouchListener(new View.OnTouchListener() {//设置触摸位置在窗口外则销毁
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int height = mView.findViewById(R.id.menu_pop_window).getTop();
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

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.setOnItemClick(view);
        }
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    public interface OnItemClickListener {
        void setOnItemClick(View v);
    }

}
