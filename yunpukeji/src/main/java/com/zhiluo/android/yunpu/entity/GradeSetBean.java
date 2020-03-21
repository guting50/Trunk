package com.zhiluo.android.yunpu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：罗咏哲 on 2018/1/17 15:19.
 * 邮箱：137615198@qq.com
 */

public class GradeSetBean implements Serializable {
    private static final long UID = 1L;
    private boolean success;
    private Object code;
    private String msg;
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

    public static class DataBean implements Serializable{
        /**
         * GID : 0be06d90-11c6-46f0-bbf1-7c7ca20f46df
         * PT_Name : 洗剪吹服务
         * PT_Type : 商品
         * SM_GID : af4b45fc-bab8-4af4-a3b8-83cbc5896d3c
         * SM_Name : 默认店铺
         * PT_SynType : 0
         */
        private static final long UID = 1L;
        private String GID;
        private String PT_Name;
        private String PT_Type;
        private String SM_GID;
        private String SM_Name;
        private String PT_SynType;
        private int PD_Discount;
        private double VS_CMoney;
        private double VS_Number;

        public int getPD_Discount() {
            return PD_Discount;
        }

        public void setPD_Discount(int PD_Discount) {
            this.PD_Discount = PD_Discount;
        }

        public double getVS_CMoney() {
            return VS_CMoney;
        }

        public void setVS_CMoney(double VS_CMoney) {
            this.VS_CMoney = VS_CMoney;
        }

        public double getVS_Number() {
            return VS_Number;
        }

        public void setVS_Number(double VS_Number) {
            this.VS_Number = VS_Number;
        }

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getPT_Name() {
            return PT_Name;
        }

        public void setPT_Name(String PT_Name) {
            this.PT_Name = PT_Name;
        }

        public String getPT_Type() {
            return PT_Type;
        }

        public void setPT_Type(String PT_Type) {
            this.PT_Type = PT_Type;
        }

        public String getSM_GID() {
            return SM_GID;
        }

        public void setSM_GID(String SM_GID) {
            this.SM_GID = SM_GID;
        }

        public String getSM_Name() {
            return SM_Name;
        }

        public void setSM_Name(String SM_Name) {
            this.SM_Name = SM_Name;
        }

        public String getPT_SynType() {
            return PT_SynType;
        }

        public void setPT_SynType(String PT_SynType) {
            this.PT_SynType = PT_SynType;
        }
    }
}
