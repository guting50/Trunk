package com.zhiluo.android.yunpu.home.jsonbean;

/**
 * Created by YSL on 2018-07-11.
 */

public class VipMemberNumBean {


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : 10
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
