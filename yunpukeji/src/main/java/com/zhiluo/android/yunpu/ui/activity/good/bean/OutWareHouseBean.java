package com.zhiluo.android.yunpu.ui.activity.good.bean;

/**
 * Created by ZPH on 2019-04-20.
 */

public class OutWareHouseBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : c3d81fb5-d8f4-4b6d-af2a-ac91b7bbcba3
     */

    private boolean success;
    private Object code;
    private String msg;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
