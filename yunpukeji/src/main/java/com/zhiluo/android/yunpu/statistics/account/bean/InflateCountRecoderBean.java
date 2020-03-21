package com.zhiluo.android.yunpu.statistics.account.bean;

import java.util.List;

/**
 * Created by ${YSL} on 2018-05-22.
 */

public class InflateCountRecoderBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":null,"PM_GID":null,"PM_IsService":0,"PM_Code":"LL","PM_Name":"流量","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":null,"PT_Name":"，，","PM_UnitPrice":"10.00","PM_Number":"1.00","SumPrice":"10.00","DiscountPrice":"8.00","GOD_Integral":null,"State":"","GOD_EMName":""}]
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
         * GID : null
         * PM_GID : null
         * PM_IsService : 0
         * PM_Code : LL
         * PM_Name : 流量
         * PM_BigImg : /img/goods.png
         * PM_Modle :
         * PM_Metering : null
         * PT_Name : ，，
         * PM_UnitPrice : 10.00
         * PM_Number : 1.00
         * SumPrice : 10.00
         * DiscountPrice : 8.00
         * GOD_Integral : null
         * State :
         * GOD_EMName :
         */

        private Object GID;
        private Object PM_GID;
        private int PM_IsService;
        private String PM_Code;
        private String PM_Name;
        private String PM_BigImg;
        private String PM_Modle;
        private Object PM_Metering;
        private String PT_Name;
        private String PM_UnitPrice;
        private String PM_Number;
        private String SumPrice;
        private String DiscountPrice;
        private Object GOD_Integral;
        private String State;
        private String GOD_EMName;

        public Object getGID() {
            return GID;
        }

        public void setGID(Object GID) {
            this.GID = GID;
        }

        public Object getPM_GID() {
            return PM_GID;
        }

        public void setPM_GID(Object PM_GID) {
            this.PM_GID = PM_GID;
        }

        public int getPM_IsService() {
            return PM_IsService;
        }

        public void setPM_IsService(int PM_IsService) {
            this.PM_IsService = PM_IsService;
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

        public String getPM_BigImg() {
            return PM_BigImg;
        }

        public void setPM_BigImg(String PM_BigImg) {
            this.PM_BigImg = PM_BigImg;
        }

        public String getPM_Modle() {
            return PM_Modle;
        }

        public void setPM_Modle(String PM_Modle) {
            this.PM_Modle = PM_Modle;
        }

        public Object getPM_Metering() {
            return PM_Metering;
        }

        public void setPM_Metering(Object PM_Metering) {
            this.PM_Metering = PM_Metering;
        }

        public String getPT_Name() {
            return PT_Name;
        }

        public void setPT_Name(String PT_Name) {
            this.PT_Name = PT_Name;
        }

        public String getPM_UnitPrice() {
            return PM_UnitPrice;
        }

        public void setPM_UnitPrice(String PM_UnitPrice) {
            this.PM_UnitPrice = PM_UnitPrice;
        }

        public String getPM_Number() {
            return PM_Number;
        }

        public void setPM_Number(String PM_Number) {
            this.PM_Number = PM_Number;
        }

        public String getSumPrice() {
            return SumPrice;
        }

        public void setSumPrice(String SumPrice) {
            this.SumPrice = SumPrice;
        }

        public String getDiscountPrice() {
            return DiscountPrice;
        }

        public void setDiscountPrice(String DiscountPrice) {
            this.DiscountPrice = DiscountPrice;
        }

        public Object getGOD_Integral() {
            return GOD_Integral;
        }

        public void setGOD_Integral(Object GOD_Integral) {
            this.GOD_Integral = GOD_Integral;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getGOD_EMName() {
            return GOD_EMName;
        }

        public void setGOD_EMName(String GOD_EMName) {
            this.GOD_EMName = GOD_EMName;
        }
    }
}
