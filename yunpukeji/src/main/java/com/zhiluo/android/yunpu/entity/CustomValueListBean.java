package com.zhiluo.android.yunpu.entity;

import java.util.List;

/**
 * 自定义属性
 * Created by dell on 2018/2/28.
 */

public class CustomValueListBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"CF_GID":"666b52bf-a92b-4610-bfef-8581f38e6b91","CF_FieldName":"介绍人","CF_FieldType":"文本","CF_ItemsValue":"","CF_Value":"","CF_Required":"否","CY_GID":"01a5ab8f-46b1-41c4-b089-0a73230ff9da","VIP_GID":"1","CF_CreateUser":"云上铺","CF_CreateTime":"2018-02-28 11:28:01","CF_Order":5,"CF_IsShowVIP":1},{"CF_GID":"379423c7-7a82-4f6c-8ed8-ed99d5dd242c","CF_FieldName":"来源","CF_FieldType":"选项","CF_ItemsValue":"网页|APP|PC","CF_Value":"","CF_Required":"否","CY_GID":"01a5ab8f-46b1-41c4-b089-0a73230ff9da","VIP_GID":"1","CF_CreateUser":"云上铺","CF_CreateTime":"2018-02-28 14:47:31","CF_Order":1,"CF_IsShowVIP":1}]
     */

    private boolean success;
    private Object code;
    private String msg;
    private List<CustomValueBean> data;

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

    public List<CustomValueBean> getData() {
        return data;
    }

    public void setData(List<CustomValueBean> data) {
        this.data = data;
    }

}
