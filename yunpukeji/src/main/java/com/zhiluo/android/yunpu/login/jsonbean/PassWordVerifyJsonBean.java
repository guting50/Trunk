package com.zhiluo.android.yunpu.login.jsonbean;

/**
 * Created by Cheng on 2017/4/1.
 */

public class PassWordVerifyJsonBean
{

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : 1
     */

    private boolean success;
    private Object code;
    private String msg;
    private int data;

    public boolean isSuccess() { return success;}

    public void setSuccess(boolean success) { this.success = success;}

    public Object getCode() { return code;}

    public void setCode(Object code) { this.code = code;}

    public String getMsg() { return msg;}

    public void setMsg(String msg) { this.msg = msg;}

    public int getData() { return data;}

    public void setData(int data) { this.data = data;}
}
