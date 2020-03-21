package com.zhiluo.android.yunpu.member.jsonbean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 */
public class VipGd {
    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"DS_Value":90,"VS_Value":1,"CY_GID":"0b6c2615-c5c8-458b-99dc-8643cf602453","VG_GID":"28ed6e06-ec4d-4c05-9db4-f0b32bf9f3d5"}]
     */

    private boolean success;
    private Object code;
    private String msg;
    /**
     * DS_Value : 90
     * VS_Value : 1
     * CY_GID : 0b6c2615-c5c8-458b-99dc-8643cf602453
     * VG_GID : 28ed6e06-ec4d-4c05-9db4-f0b32bf9f3d5
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int DS_Value;
        private double VS_Value;
        private String CY_GID;
        private String VG_GID;

        public int getDS_Value() {
            return DS_Value;
        }

        public void setDS_Value(int DS_Value) {
            this.DS_Value = DS_Value;
        }

        public double getVS_Value() {
            return VS_Value;
        }

        public void setVS_Value(double VS_Value) {
            this.VS_Value = VS_Value;
        }

        public String getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(String CY_GID) {
            this.CY_GID = CY_GID;
        }

        public String getVG_GID() {
            return VG_GID;
        }

        public void setVG_GID(String VG_GID) {
            this.VG_GID = VG_GID;
        }
    }
    /*
    * 用于解析vip折扣
    *
    * */

}
