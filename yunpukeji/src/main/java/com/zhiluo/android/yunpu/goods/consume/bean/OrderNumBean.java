package com.zhiluo.android.yunpu.goods.consume.bean;

/**
 * 作者：罗咏哲 on 2017/8/5 11:17.
 * 邮箱：137615198@qq.com
 */

public class OrderNumBean {
    /**
     * success : true
     * code : null
     * msg : 生成订单编号成功
     * data : SP1708051028381976
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
