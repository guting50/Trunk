package com.zhiluo.android.yunpu.consume.jsonbean;

/**
 * Created by 70700 on 2016/11/1.
 */
public class AddOrderBean  {
    /**
     * success : true
     * code : null
     * msg : 生成兑换单号成功
     * data : LP1611011138464709
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
