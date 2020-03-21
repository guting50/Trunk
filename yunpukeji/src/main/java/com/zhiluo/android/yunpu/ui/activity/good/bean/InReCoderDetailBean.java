package com.zhiluo.android.yunpu.ui.activity.good.bean;

import java.util.List;

/**
 * Created by ${YSL} on 2018-05-18.
 */

public class InReCoderDetailBean {
    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"SI_PayType":null,"SI_TrackingNo":null,"SL_Name":null,"GID":"11e9051a-b1c0-4801-9f12-183428bea57c","PM_BigImg":"http://img.yunvip123.com/CommonImg/125c6798-63b7-429f-a8e7-7b4f135cc95d.png","SI_GID":"43ef87db-0b07-440d-ad15-12858325ac36","SID_Amount":4,"SID_TotalPrice":216,"SID_CreateTime":"2018-05-18 16:06:26","SID_Remark":null,"SID_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","PM_Code":"5425","PM_Name":"655","PM_SimpleCode":"","PM_Metering":"","PM_UnitPrice":54,"PM_Purchase":"ec1f6062-f0db-46ac-9550-195cea6c5a8f","PT_ID":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","PM_Description":"","PM_Modle":"","PM_Brand":"","PM_GID":"c9f62839-8754-483d-814a-97ca7e5ebce2","PT_Name":"，，"},{"SI_PayType":null,"SI_TrackingNo":null,"SL_Name":null,"GID":"ab4f427b-5f79-44a9-a430-d17f25c043c2","PM_BigImg":"http://img.yunvip123.com/CommonImg/e8711b3b-aae1-49f5-9e59-81882ef0b540.png","SI_GID":"43ef87db-0b07-440d-ad15-12858325ac36","SID_Amount":8,"SID_TotalPrice":40,"SID_CreateTime":"2018-05-18 16:06:26","SID_Remark":null,"SID_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","PM_Code":"1836","PM_Name":"555","PM_SimpleCode":"","PM_Metering":"","PM_UnitPrice":5,"PM_Purchase":"ec1f6062-f0db-46ac-9550-195cea6c5a8f","PT_ID":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","PM_Description":"","PM_Modle":"","PM_Brand":"","PM_GID":"54fe4132-f0c0-40cf-8b67-25b77de3d468","PT_Name":"，，"}]
     */

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

    public static class DataBean {
        /**
         * SI_PayType : null
         * SI_TrackingNo : null
         * SL_Name : null
         * GID : 11e9051a-b1c0-4801-9f12-183428bea57c
         * PM_BigImg : http://img.yunvip123.com/CommonImg/125c6798-63b7-429f-a8e7-7b4f135cc95d.png
         * SI_GID : 43ef87db-0b07-440d-ad15-12858325ac36
         * SID_Amount : 4.0
         * SID_TotalPrice : 216.0
         * SID_CreateTime : 2018-05-18 16:06:26
         * SID_Remark : null
         * SID_Creator : 18381306468
         * CY_GID : null
         * SM_GID : 966d986b-0caa-465b-bbe1-8bd871bf8a0a
         * PM_Code : 5425
         * PM_Name : 655
         * PM_SimpleCode :
         * PM_Metering :
         * PM_UnitPrice : 54.0
         * PM_Purchase : ec1f6062-f0db-46ac-9550-195cea6c5a8f
         * PT_ID : b9fe359f-e29c-4bf9-9344-b0b9cfaa713f
         * PM_Description :
         * PM_Modle :
         * PM_Brand :
         * PM_GID : c9f62839-8754-483d-814a-97ca7e5ebce2
         * PT_Name : ，，
         */

        private Object SI_PayType;
        private Object SI_TrackingNo;
        private Object SL_Name;
        private String GID;
        private String PM_BigImg;
        private String SI_GID;
        private double SID_Amount;
        private double SID_TotalPrice;
        private String SID_CreateTime;
        private Object SID_Remark;
        private String SID_Creator;
        private Object CY_GID;
        private String SM_GID;
        private String PM_Code;
        private String PM_Name;
        private String PM_SimpleCode;
        private String PM_Metering;
        private double PM_UnitPrice;
        private String PM_Purchase;
        private String PT_ID;
        private String PM_Description;
        private String PM_Modle;
        private String PM_Brand;
        private String PM_GID;
        private String PT_Name;

        public double getNowAccount() {
            return NowAccount;
        }

        public void setNowAccount(double nowAccount) {
            NowAccount = nowAccount;
        }

        private double NowAccount;

        public Object getSI_PayType() {
            return SI_PayType;
        }

        public void setSI_PayType(Object SI_PayType) {
            this.SI_PayType = SI_PayType;
        }

        public Object getSI_TrackingNo() {
            return SI_TrackingNo;
        }

        public void setSI_TrackingNo(Object SI_TrackingNo) {
            this.SI_TrackingNo = SI_TrackingNo;
        }

        public Object getSL_Name() {
            return SL_Name;
        }

        public void setSL_Name(Object SL_Name) {
            this.SL_Name = SL_Name;
        }

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getPM_BigImg() {
            return PM_BigImg;
        }

        public void setPM_BigImg(String PM_BigImg) {
            this.PM_BigImg = PM_BigImg;
        }

        public String getSI_GID() {
            return SI_GID;
        }

        public void setSI_GID(String SI_GID) {
            this.SI_GID = SI_GID;
        }

        public double getSID_Amount() {
            return SID_Amount;
        }

        public void setSID_Amount(double SID_Amount) {
            this.SID_Amount = SID_Amount;
        }

        public double getSID_TotalPrice() {
            return SID_TotalPrice;
        }

        public void setSID_TotalPrice(double SID_TotalPrice) {
            this.SID_TotalPrice = SID_TotalPrice;
        }

        public String getSID_CreateTime() {
            return SID_CreateTime;
        }

        public void setSID_CreateTime(String SID_CreateTime) {
            this.SID_CreateTime = SID_CreateTime;
        }

        public Object getSID_Remark() {
            return SID_Remark;
        }

        public void setSID_Remark(Object SID_Remark) {
            this.SID_Remark = SID_Remark;
        }

        public String getSID_Creator() {
            return SID_Creator;
        }

        public void setSID_Creator(String SID_Creator) {
            this.SID_Creator = SID_Creator;
        }

        public Object getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(Object CY_GID) {
            this.CY_GID = CY_GID;
        }

        public String getSM_GID() {
            return SM_GID;
        }

        public void setSM_GID(String SM_GID) {
            this.SM_GID = SM_GID;
        }

        public String getPM_Code() {
            return PM_Code;
        }

        public void setPM_Code(String PM_Code) {
            this.PM_Code = PM_Code;
        }

        public String getPM_Name() {
            return PM_Name;
        }

        public void setPM_Name(String PM_Name) {
            this.PM_Name = PM_Name;
        }

        public String getPM_SimpleCode() {
            return PM_SimpleCode;
        }

        public void setPM_SimpleCode(String PM_SimpleCode) {
            this.PM_SimpleCode = PM_SimpleCode;
        }

        public String getPM_Metering() {
            return PM_Metering;
        }

        public void setPM_Metering(String PM_Metering) {
            this.PM_Metering = PM_Metering;
        }

        public double getPM_UnitPrice() {
            return PM_UnitPrice;
        }

        public void setPM_UnitPrice(double PM_UnitPrice) {
            this.PM_UnitPrice = PM_UnitPrice;
        }

        public String getPM_Purchase() {
            return PM_Purchase;
        }

        public void setPM_Purchase(String PM_Purchase) {
            this.PM_Purchase = PM_Purchase;
        }

        public String getPT_ID() {
            return PT_ID;
        }

        public void setPT_ID(String PT_ID) {
            this.PT_ID = PT_ID;
        }

        public String getPM_Description() {
            return PM_Description;
        }

        public void setPM_Description(String PM_Description) {
            this.PM_Description = PM_Description;
        }

        public String getPM_Modle() {
            return PM_Modle;
        }

        public void setPM_Modle(String PM_Modle) {
            this.PM_Modle = PM_Modle;
        }

        public String getPM_Brand() {
            return PM_Brand;
        }

        public void setPM_Brand(String PM_Brand) {
            this.PM_Brand = PM_Brand;
        }

        public String getPM_GID() {
            return PM_GID;
        }

        public void setPM_GID(String PM_GID) {
            this.PM_GID = PM_GID;
        }

        public String getPT_Name() {
            return PT_Name;
        }

        public void setPT_Name(String PT_Name) {
            this.PT_Name = PT_Name;
        }
    }
}
