package com.zhiluo.android.yunpu.goods.manager.bean;

/**
 * 作者：罗咏哲 on 2017/6/26 14:31.
 * 邮箱：137615198@qq.com
 */

public class AddGoodsResponseBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"FildsId":null,"FildsValue":null,"PM_IsDiscount":1,"PM_IsPoint":1,"PM_IsService":0,"GID":"83f39901-e8a6-45bc-9d53-6ae2b7bb3128","PT_ID":"cb1f9920-d08f-4008-b372-e0d4a01ea2e8","PM_Code":"M01234","PM_Name":"shouji","PM_SimpleCode":"","PM_Metering":null,"PM_UnitPrice":1234,"PM_UpdateTime":"2017-06-26 14:18:07","PM_Remark":"","PM_Creator":"1540004275@qq.com","PM_BigImg":"../../img/goods.png","PM_SmallImg":null,"PM_Detail":"","PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","PM_SynType":0,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0,"PM_StockPoliceValue":0,"PT_Name":null,"SM_ID":null}
     */

    private boolean success;
    private Object code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * FildsId : null
         * FildsValue : null
         * PM_IsDiscount : 1
         * PM_IsPoint : 1
         * PM_IsService : 0
         * GID : 83f39901-e8a6-45bc-9d53-6ae2b7bb3128
         * PT_ID : cb1f9920-d08f-4008-b372-e0d4a01ea2e8
         * PM_Code : M01234
         * PM_Name : shouji
         * PM_SimpleCode :
         * PM_Metering : null
         * PM_UnitPrice : 1234.0
         * PM_UpdateTime : 2017-06-26 14:18:07
         * PM_Remark :
         * PM_Creator : 1540004275@qq.com
         * PM_BigImg : ../../img/goods.png
         * PM_SmallImg : null
         * PM_Detail :
         * PM_Description :
         * PM_Modle :
         * PM_Brand :
         * CY_GID : 348e79f2-ff35-4d62-8298-9d37f06fd5e0
         * PM_SynType : 0
         * PM_SpecialOfferValue : 0.0
         * PM_MinDisCountValue : 0.0
         * PM_FixedIntegralValue : 0.0
         * PM_StockPoliceValue : 0.0
         * PT_Name : null
         * SM_ID : null
         */

        private Object FildsId;
        private Object FildsValue;
        private int PM_IsDiscount;
        private int PM_IsPoint;
        private int PM_IsService;
        private String GID;
        private String PT_ID;
        private String PM_Code;
        private String PM_Name;
        private String PM_SimpleCode;
        private Object PM_Metering;
        private double PM_UnitPrice;
        private String PM_UpdateTime;
        private String PM_Remark;
        private String PM_Creator;
        private String PM_BigImg;
        private Object PM_SmallImg;
        private String PM_Detail;
        private String PM_Description;
        private String PM_Modle;
        private String PM_Brand;
        private String CY_GID;
        private int PM_SynType;
        private double PM_SpecialOfferValue;
        private double PM_MinDisCountValue;
        private double PM_FixedIntegralValue;
        private double PM_StockPoliceValue;
        private Object PT_Name;
        private Object SM_ID;

        public Object getFildsId() {
            return FildsId;
        }

        public void setFildsId(Object FildsId) {
            this.FildsId = FildsId;
        }

        public Object getFildsValue() {
            return FildsValue;
        }

        public void setFildsValue(Object FildsValue) {
            this.FildsValue = FildsValue;
        }

        public int getPM_IsDiscount() {
            return PM_IsDiscount;
        }

        public void setPM_IsDiscount(int PM_IsDiscount) {
            this.PM_IsDiscount = PM_IsDiscount;
        }

        public int getPM_IsPoint() {
            return PM_IsPoint;
        }

        public void setPM_IsPoint(int PM_IsPoint) {
            this.PM_IsPoint = PM_IsPoint;
        }

        public int getPM_IsService() {
            return PM_IsService;
        }

        public void setPM_IsService(int PM_IsService) {
            this.PM_IsService = PM_IsService;
        }

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getPT_ID() {
            return PT_ID;
        }

        public void setPT_ID(String PT_ID) {
            this.PT_ID = PT_ID;
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

        public Object getPM_Metering() {
            return PM_Metering;
        }

        public void setPM_Metering(Object PM_Metering) {
            this.PM_Metering = PM_Metering;
        }

        public double getPM_UnitPrice() {
            return PM_UnitPrice;
        }

        public void setPM_UnitPrice(double PM_UnitPrice) {
            this.PM_UnitPrice = PM_UnitPrice;
        }

        public String getPM_UpdateTime() {
            return PM_UpdateTime;
        }

        public void setPM_UpdateTime(String PM_UpdateTime) {
            this.PM_UpdateTime = PM_UpdateTime;
        }

        public String getPM_Remark() {
            return PM_Remark;
        }

        public void setPM_Remark(String PM_Remark) {
            this.PM_Remark = PM_Remark;
        }

        public String getPM_Creator() {
            return PM_Creator;
        }

        public void setPM_Creator(String PM_Creator) {
            this.PM_Creator = PM_Creator;
        }

        public String getPM_BigImg() {
            return PM_BigImg;
        }

        public void setPM_BigImg(String PM_BigImg) {
            this.PM_BigImg = PM_BigImg;
        }

        public Object getPM_SmallImg() {
            return PM_SmallImg;
        }

        public void setPM_SmallImg(Object PM_SmallImg) {
            this.PM_SmallImg = PM_SmallImg;
        }

        public String getPM_Detail() {
            return PM_Detail;
        }

        public void setPM_Detail(String PM_Detail) {
            this.PM_Detail = PM_Detail;
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

        public String getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(String CY_GID) {
            this.CY_GID = CY_GID;
        }

        public int getPM_SynType() {
            return PM_SynType;
        }

        public void setPM_SynType(int PM_SynType) {
            this.PM_SynType = PM_SynType;
        }

        public double getPM_SpecialOfferValue() {
            return PM_SpecialOfferValue;
        }

        public void setPM_SpecialOfferValue(double PM_SpecialOfferValue) {
            this.PM_SpecialOfferValue = PM_SpecialOfferValue;
        }

        public double getPM_MinDisCountValue() {
            return PM_MinDisCountValue;
        }

        public void setPM_MinDisCountValue(double PM_MinDisCountValue) {
            this.PM_MinDisCountValue = PM_MinDisCountValue;
        }

        public double getPM_FixedIntegralValue() {
            return PM_FixedIntegralValue;
        }

        public void setPM_FixedIntegralValue(double PM_FixedIntegralValue) {
            this.PM_FixedIntegralValue = PM_FixedIntegralValue;
        }

        public double getPM_StockPoliceValue() {
            return PM_StockPoliceValue;
        }

        public void setPM_StockPoliceValue(double PM_StockPoliceValue) {
            this.PM_StockPoliceValue = PM_StockPoliceValue;
        }

        public Object getPT_Name() {
            return PT_Name;
        }

        public void setPT_Name(Object PT_Name) {
            this.PT_Name = PT_Name;
        }

        public Object getSM_ID() {
            return SM_ID;
        }

        public void setSM_ID(Object SM_ID) {
            this.SM_ID = SM_ID;
        }
    }
}
