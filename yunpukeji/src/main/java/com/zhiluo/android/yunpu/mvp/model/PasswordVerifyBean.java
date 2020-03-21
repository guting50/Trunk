package com.zhiluo.android.yunpu.mvp.model;

/**
 * 密码验证
 * 作者：罗咏哲 on 2017/10/31 14:35.
 * 邮箱：137615198@qq.com
 */

public class PasswordVerifyBean {

    /**
     * success : false
     * code : null
     * msg : 执行失败
     * data : 1
     */

    private boolean success;
    private Object code;
    private String msg;
    private int data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
