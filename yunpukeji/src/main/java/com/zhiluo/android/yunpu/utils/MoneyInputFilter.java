package com.zhiluo.android.yunpu.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.Toast;

import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.ui.view.CustomToast;

/**
 * 金额过滤器
 * Created by dell on 2018/3/6.
 */

public class MoneyInputFilter implements InputFilter {
    private int mMaxLength;
    private boolean isNum;
    private String note;
    private String message = null;

    /**
     * @param mMaxLength 支持的最大长度
     * @param isNum      是不是数字
     * @param note       超过后的提示
     */
    public MoneyInputFilter(int mMaxLength, boolean isNum, String note) {
        this.mMaxLength = mMaxLength;
        this.isNum = isNum;
        this.note = note;
    }

    public MoneyInputFilter setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * @param source 输入的内容
     * @param start  开始位置
     * @param end    结束位置
     * @param dest   当前显示的内容
     * @param dstart 当前开始位置
     * @param dend   当前结束位置
     * @return
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        int keep = mMaxLength - (dest.length() - (dend - dstart));
        if (keep < (end - start)) {
            if (TextUtils.isEmpty(message)) {
                CustomToast.makeText(MyApplication.getmContext(), "最多只能输入"+mMaxLength+"位金额", Toast.LENGTH_SHORT).show();
            } else {
                CustomToast.makeText(MyApplication.getmContext(), message, Toast.LENGTH_SHORT).show();
            }
        }
        if (keep <= 0) {
            return "";
        } else if (keep >= end - start) {
            StringBuilder sb = new StringBuilder(dest.toString());
            sb.insert(dstart,source.toString());
            if (isNum) {
                int posDot = sb.toString().indexOf(".");
                if (start < end && posDot > 0 && (sb.length() - posDot) > 3) {
                    CustomToast.makeText(MyApplication.getmContext(), "小数点后只能保留两位", Toast.LENGTH_SHORT).show();
                    return "";
                }
                if (start < end && posDot > 1 && dest.toString().equals("0.") && source.equals("0")) {
                    CustomToast.makeText(MyApplication.getmContext(), "请输入正确的金额格式", Toast.LENGTH_SHORT).show();
                    return "";
                }
//                else if (start < end && posDot < 0 && dest.toString().equals("0") && !source.equals(".")) {
//                    CustomToast.makeText(MyApplication.getmContext(), "请输入正确的金额格式", Toast.LENGTH_SHORT).show();
//                    return "";
//                }
            } else {
                return null;
            }
        } else {
            return source.subSequence(start, start + keep);
        }
        return null;
    }
}
