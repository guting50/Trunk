package com.zhiluo.android.yunpu.home.jsonbean;

/**
 * Created by Administrator on 2016/9/22.
 */
public class SteCurentShop
{

    /**
     * success : false
     * code : LoginTimeout
     * msg : 当前用户未登录或登录超时！
     * data : 1
     */

    private boolean success;
    private String code;
    private String msg;
    private int data;

    public boolean isSuccess() { return success;}

    public void setSuccess(boolean success) { this.success = success;}

    public String getCode() { return code;}

    public void setCode(String code) { this.code = code;}

    public String getMsg() { return msg;}

    public void setMsg(String msg) { this.msg = msg;}

    public int getData() { return data;}

    public void setData(int data) { this.data = data;}
}
