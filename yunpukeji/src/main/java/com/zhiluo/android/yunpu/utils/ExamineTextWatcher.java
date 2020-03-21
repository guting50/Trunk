package com.zhiluo.android.yunpu.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * 作者：罗咏哲 on 2017/9/18 16:08.
 * 邮箱：137615198@qq.com
 */

public class ExamineTextWatcher implements TextWatcher {
    /**
     * 输入框
     */
    private EditText mEditText;


    /**
     * 输入前的文本内容
     */
    private String beforeText;

    public ExamineTextWatcher(EditText mEditText) {
        this.mEditText = mEditText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        beforeText = charSequence.toString();
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // 输入后的字符
        String afterText = charSequence.toString();

        boolean isValid = true;
        if (!TextUtils.isEmpty(afterText)) {
            isValid = RegexUtil.isDiscount(afterText);
            if (!isValid) {
                // 用户现在输入的字符数减去之前输入的字符数，等于新增的字符数
                int differ = afterText.length() - beforeText.length();
                // 如果用户的输入不符合规范，则显示之前输入的文本
                mEditText.setText(beforeText);
                // 光标移动到文本末尾
                mEditText.setSelection(afterText.length() - differ);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
