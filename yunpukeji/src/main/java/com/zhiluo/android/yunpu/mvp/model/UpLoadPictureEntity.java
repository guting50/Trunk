package com.zhiluo.android.yunpu.mvp.model;

/**
 * 上传图片返回
 * 作者：罗咏哲 on 2017/10/25 14:45.
 * 邮箱：137615198@qq.com
 */

public class UpLoadPictureEntity {
    private boolean success;
    private Object code;
    private Object msg;
    private String data;

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

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
