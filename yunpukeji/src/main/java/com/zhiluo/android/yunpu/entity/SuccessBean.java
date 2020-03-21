package com.zhiluo.android.yunpu.entity;

/**
 * 获取数据是否成功bean
 * 作者：罗咏哲 on 2017/7/18 14:49.
 * 邮箱：137615198@qq.com
 */

public class SuccessBean {
    private boolean success;
    private Object code;
    private String msg;

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
}
