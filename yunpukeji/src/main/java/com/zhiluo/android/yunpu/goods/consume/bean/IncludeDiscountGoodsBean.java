package com.zhiluo.android.yunpu.goods.consume.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 包含会员折扣信息的商品信息JavaBean
 * 作者：罗咏哲 on 2017/8/2 15:12.
 * 邮箱：137615198@qq.com
 */

public class IncludeDiscountGoodsBean implements Serializable{

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":50,"DataCount":3,"PageIndex":1,"DataList":[{"PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":0,"PD_Discount":100,"VS_Number":0,"PTD_Type":null,"PT_GID":"e0dece01-336c-4a55-9de2-666cd7953141","PT_Name":"蔬菜","PM_GID":"3b91e68a-ef87-4a04-b8cd-e7ff1a27197e","PM_Name":"很大hi","PM_Metering":null,"PM_SimpleCode":"HDhi","PM_Code":"788998","PM_UnitPrice":23,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":null,"CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":0,"PD_Discount":100,"VS_Number":0,"PTD_Type":null,"PT_GID":"e0dece01-336c-4a55-9de2-666cd7953141","PT_Name":"蔬菜","PM_GID":"06d71d08-eee0-42fe-9703-5611e12a9c8c","PM_Name":"青萝卜","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"8287","PM_UnitPrice":5,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":null,"CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":0,"PD_Discount":100,"VS_Number":0,"PTD_Type":null,"PT_GID":"e0dece01-336c-4a55-9de2-666cd7953141","PT_Name":"蔬菜","PM_GID":"f7393739-8cf8-4276-a360-c144191ae748","PM_Name":"小青菜","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"58464","PM_UnitPrice":2,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":null,"CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0}],"StatisticsInfo":null}
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

    public static class DataBean implements Serializable{
        /**
         * PageTotal : 1
         * PageSize : 50
         * DataCount : 3
         * PageIndex : 1
         * DataList : [{"PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":0,"PD_Discount":100,"VS_Number":0,"PTD_Type":null,"PT_GID":"e0dece01-336c-4a55-9de2-666cd7953141","PT_Name":"蔬菜","PM_GID":"3b91e68a-ef87-4a04-b8cd-e7ff1a27197e","PM_Name":"很大hi","PM_Metering":null,"PM_SimpleCode":"HDhi","PM_Code":"788998","PM_UnitPrice":23,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":null,"CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":0,"PD_Discount":100,"VS_Number":0,"PTD_Type":null,"PT_GID":"e0dece01-336c-4a55-9de2-666cd7953141","PT_Name":"蔬菜","PM_GID":"06d71d08-eee0-42fe-9703-5611e12a9c8c","PM_Name":"青萝卜","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"8287","PM_UnitPrice":5,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":null,"CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":0,"PD_Discount":100,"VS_Number":0,"PTD_Type":null,"PT_GID":"e0dece01-336c-4a55-9de2-666cd7953141","PT_Name":"蔬菜","PM_GID":"f7393739-8cf8-4276-a360-c144191ae748","PM_Name":"小青菜","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"58464","PM_UnitPrice":2,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":null,"CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0}]
         * StatisticsInfo : null
         */

        private int PageTotal;
        private int PageSize;
        private int DataCount;
        private int PageIndex;
        private Object StatisticsInfo;
        private List<DataListBean> DataList;

        public int getPageTotal() {
            return PageTotal;
        }

        public void setPageTotal(int PageTotal) {
            this.PageTotal = PageTotal;
        }

        public int getPageSize() {
            return PageSize;
        }

        public void setPageSize(int PageSize) {
            this.PageSize = PageSize;
        }

        public int getDataCount() {
            return DataCount;
        }

        public void setDataCount(int DataCount) {
            this.DataCount = DataCount;
        }

        public int getPageIndex() {
            return PageIndex;
        }

        public void setPageIndex(int PageIndex) {
            this.PageIndex = PageIndex;
        }

        public Object getStatisticsInfo() {
            return StatisticsInfo;
        }

        public void setStatisticsInfo(Object StatisticsInfo) {
            this.StatisticsInfo = StatisticsInfo;
        }

        public List<DataListBean> getDataList() {
            return DataList;
        }

        public void setDataList(List<DataListBean> DataList) {
            this.DataList = DataList;
        }

        public static class DataListBean implements Serializable{
            /**
             * PM_IsDiscount : 0
             * PM_IsPoint : 0
             * PM_IsService : 0
             * PD_Discount : 100.0
             * VS_Number : 0.0
             * PTD_Type : null
             * PT_GID : e0dece01-336c-4a55-9de2-666cd7953141
             * PT_Name : 蔬菜
             * PM_GID : 3b91e68a-ef87-4a04-b8cd-e7ff1a27197e
             * PM_Name : 很大hi
             * PM_Metering : null
             * PM_SimpleCode : HDhi
             * PM_Code : 788998
             * PM_UnitPrice : 23.0
             * PM_Description :
             * PM_Modle :
             * PM_Brand :
             * CY_GID : 348e79f2-ff35-4d62-8298-9d37f06fd5e0
             * VG_GID : null
             * CP_BarginPrice : null
             * PM_SpecialOfferValue : 0.0
             * PM_MinDisCountValue : 0.0
             * PM_FixedIntegralValue : 0.0
             */

            private int PM_IsDiscount;
            private int PM_IsPoint;
            private int PM_IsService;
            private double PD_Discount;
            private double VS_Number;
            private Object PTD_Type;
            private String PT_GID;
            private String PT_Name;
            private String PM_GID;
            private String PM_Name;
            private String PM_Metering;
            private String PM_SimpleCode;
            private String PM_Code;
            private double PM_UnitPrice;
            private String PM_Description;
            private String PM_Modle;
            private String PM_Brand;
            private String CY_GID;
            private Object VG_GID;
            private Object CP_BarginPrice;
            private double PM_SpecialOfferValue;
            private double PM_MinDisCountValue;
            private double PM_FixedIntegralValue;
            private String PM_BigImg;
            private int Stock_Number;
            private int PM_Number;
            private double PM_MemPrice;
            private double PM_PurchasePrice;

            public double getPM_MemPrice() {
                return PM_MemPrice;
            }

            public void setPM_MemPrice(double PM_MemPrice) {
                this.PM_MemPrice = PM_MemPrice;
            }

            public double getPM_PurchasePrice() {
                return PM_PurchasePrice;
            }

            public void setPM_PurchasePrice(double PM_PurchasePrice) {
                this.PM_PurchasePrice = PM_PurchasePrice;
            }

            public int getPM_Number() {
                return PM_Number;
            }

            public void setPM_Number(int PM_Number) {
                this.PM_Number = PM_Number;
            }

            public int getStock_Number() {
                return Stock_Number;
            }

            public void setStock_Number(int stock_Number) {
                Stock_Number = stock_Number;
            }

            public String getPM_BigImg() {
                return PM_BigImg;
            }

            public void setPM_BigImg(String PM_BigImg) {
                this.PM_BigImg = PM_BigImg;
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

            public double getPD_Discount() {
                return PD_Discount;
            }

            public void setPD_Discount(double PD_Discount) {
                this.PD_Discount = PD_Discount;
            }

            public double getVS_Number() {
                return VS_Number;
            }

            public void setVS_Number(double VS_Number) {
                this.VS_Number = VS_Number;
            }

            public Object getPTD_Type() {
                return PTD_Type;
            }

            public void setPTD_Type(Object PTD_Type) {
                this.PTD_Type = PTD_Type;
            }

            public String getPT_GID() {
                return PT_GID;
            }

            public void setPT_GID(String PT_GID) {
                this.PT_GID = PT_GID;
            }

            public String getPT_Name() {
                return PT_Name;
            }

            public void setPT_Name(String PT_Name) {
                this.PT_Name = PT_Name;
            }

            public String getPM_GID() {
                return PM_GID;
            }

            public void setPM_GID(String PM_GID) {
                this.PM_GID = PM_GID;
            }

            public String getPM_Name() {
                return PM_Name;
            }

            public void setPM_Name(String PM_Name) {
                this.PM_Name = PM_Name;
            }

            public String getPM_Metering() {
                return PM_Metering;
            }

            public void setPM_Metering(String PM_Metering) {
                this.PM_Metering = PM_Metering;
            }

            public String getPM_SimpleCode() {
                return PM_SimpleCode;
            }

            public void setPM_SimpleCode(String PM_SimpleCode) {
                this.PM_SimpleCode = PM_SimpleCode;
            }

            public String getPM_Code() {
                return PM_Code;
            }

            public void setPM_Code(String PM_Code) {
                this.PM_Code = PM_Code;
            }

            public double getPM_UnitPrice() {
                return PM_UnitPrice;
            }

            public void setPM_UnitPrice(double PM_UnitPrice) {
                this.PM_UnitPrice = PM_UnitPrice;
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

            public Object getVG_GID() {
                return VG_GID;
            }

            public void setVG_GID(Object VG_GID) {
                this.VG_GID = VG_GID;
            }

            public Object getCP_BarginPrice() {
                return CP_BarginPrice;
            }

            public void setCP_BarginPrice(Object CP_BarginPrice) {
                this.CP_BarginPrice = CP_BarginPrice;
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
        }
    }
}
