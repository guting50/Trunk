package com.zhiluo.android.yunpu.consume.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * 商品消费
 * iauthor：Cheng
 * date: 2017/6/6 11:50
 * email：jasoncheng9111@gmail.com
 */
public class QueryProductDiscountListJsonBean implements Serializable {

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
         * DataCount : 8
         * PageIndex : 1
         * DataList : [{"PM_IsDiscount":1,"PM_IsPoint":1,"PD_Discount":100,"VS_Number":0,"PTD_Type":null,"PT_GID":"0385858e-e4d8-4101-924b-6f3a088e5be7","PT_Name":"西点",
         * "PM_GID":"694856b9-3571-43d5-b7ae-c3afee9fbb22","PM_Name":"波士顿派","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"4444","PM_UnitPrice":121,"PM_Description":"",
         * "PM_Modle":"","PM_Brand":"","CY_GID":"79dbfdba-9e74-4303-b72d-992318bf46c4","VG_GID":null,"CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,
         * "PM_FixedIntegralValue":0},{"PM_IsDiscount":1,"PM_IsPoint":1,"PD_Discount":100,"VS_Number":0,"PTD_Type":null,"PT_GID":"64963879-1d6f-4501-afce-65661fa7e5f4",
         * "PT_Name":"哈","PM_GID":"52725ee7-5569-40d6-9fa2-96d31bf78edb","PM_Name":"马卡龙","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"233","PM_UnitPrice":22,
         * "PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"79dbfdba-9e74-4303-b72d-992318bf46c4","VG_GID":null,"CP_BarginPrice":null,"PM_SpecialOfferValue":0,
         * "PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"PM_IsDiscount":1,"PM_IsPoint":1,"PD_Discount":100,"VS_Number":0,"PTD_Type":null,
         * "PT_GID":"64963879-1d6f-4501-afce-65661fa7e5f4","PT_Name":"哈","PM_GID":"a5b3ee66-55d5-43ed-9d02-2a5d81775772","PM_Name":"ads112","PM_Metering":null,
         * "PM_SimpleCode":"","PM_Code":"88766","PM_UnitPrice":15,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"79dbfdba-9e74-4303-b72d-992318bf46c4","VG_GID":null,
         * "CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"PM_IsDiscount":1,"PM_IsPoint":1,"PD_Discount":100,"VS_Number":0,
         * "PTD_Type":null,"PT_GID":"0385858e-e4d8-4101-924b-6f3a088e5be7","PT_Name":"西点","PM_GID":"cf09fccd-308c-479f-b95e-b25fc4e5c3e6","PM_Name":"玛格丽特小饼干","PM_Metering":null,
         * "PM_SimpleCode":"","PM_Code":"444","PM_UnitPrice":12,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"79dbfdba-9e74-4303-b72d-992318bf46c4","VG_GID":null,
         * "CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"PM_IsDiscount":1,"PM_IsPoint":1,"PD_Discount":100,"VS_Number":0,
         * "PTD_Type":null,"PT_GID":"0385858e-e4d8-4101-924b-6f3a088e5be7","PT_Name":"西点","PM_GID":"45d43cee-6e9c-44ef-9b56-aecf3a6f82e1","PM_Name":"提拉米苏","PM_Metering":null,
         * "PM_SimpleCode":"","PM_Code":"11","PM_UnitPrice":11,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"79dbfdba-9e74-4303-b72d-992318bf46c4","VG_GID":null,
         * "CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"PM_IsDiscount":1,"PM_IsPoint":1,"PD_Discount":100,"VS_Number":0,
         * "PTD_Type":null,"PT_GID":"64963879-1d6f-4501-afce-65661fa7e5f4","PT_Name":"哈","PM_GID":"be1b5cd9-2478-4ba2-b38c-d7db929b0626","PM_Name":"123123","PM_Metering":null,
         * "PM_SimpleCode":"","PM_Code":"123123","PM_UnitPrice":11,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"79dbfdba-9e74-4303-b72d-992318bf46c4","VG_GID":null,
         * "CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"PM_IsDiscount":1,"PM_IsPoint":1,"PD_Discount":100,"VS_Number":0,
         * "PTD_Type":null,"PT_GID":"64963879-1d6f-4501-afce-65661fa7e5f4","PT_Name":"哈","PM_GID":"f3fbc727-f2ba-4076-aacd-c6e6c8293e43","PM_Name":"asdas","PM_Metering":null,
         * "PM_SimpleCode":"","PM_Code":"123","PM_UnitPrice":10,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"79dbfdba-9e74-4303-b72d-992318bf46c4","VG_GID":null,
         * "CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"PM_IsDiscount":1,"PM_IsPoint":1,"PD_Discount":100,"VS_Number":0,
         * "PTD_Type":null,"PT_GID":"64963879-1d6f-4501-afce-65661fa7e5f4","PT_Name":"哈","PM_GID":"78e3e279-451d-44ee-8c05-00af1316b6d9","PM_Name":"00111","PM_Metering":null,
         * "PM_SimpleCode":"","PM_Code":"0011","PM_UnitPrice":10,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"79dbfdba-9e74-4303-b72d-992318bf46c4","VG_GID":null,
         * "CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0}]
         */

        private int PageTotal;
        private int PageSize;
        private int DataCount;
        private int PageIndex;
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

        public List<DataListBean> getDataList() {
            return DataList;
        }

        public void setDataList(List<DataListBean> DataList) {
            this.DataList = DataList;
        }

        public static class DataListBean implements Serializable {
            /**
             * PM_IsDiscount : 1
             * PM_IsPoint : 1
             * PD_Discount : 100
             * VS_Number : 0
             * PTD_Type : null
             * PT_GID : 0385858e-e4d8-4101-924b-6f3a088e5be7
             * PT_Name : 西点
             * PM_GID : 694856b9-3571-43d5-b7ae-c3afee9fbb22
             * PM_Name : 波士顿派
             * PM_Metering : null
             * PM_SimpleCode :
             * PM_Code : 4444
             * PM_UnitPrice : 121
             * PM_Description :
             * PM_Modle :
             * PM_Brand :
             * CY_GID : 79dbfdba-9e74-4303-b72d-992318bf46c4
             * VG_GID : null
             * CP_BarginPrice : null
             * PM_SpecialOfferValue : 0
             * PM_MinDisCountValue : 0
             * PM_FixedIntegralValue : 0
             */

            private int PM_IsDiscount;
            private int PM_IsPoint;
            private String PD_Discount;
            private String VS_Number;
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
            private int PM_Number;

            public int getPM_Number() {
                return PM_Number;
            }

            public void setPM_Number(int PM_Number) {
                this.PM_Number = PM_Number;
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

            public String getPD_Discount() {
                return PD_Discount;
            }

            public void setPD_Discount(String PD_Discount) {
                this.PD_Discount = PD_Discount;
            }

            public String getVS_Number() {
                return VS_Number;
            }

            public void setVS_Number(String VS_Number) {
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

            public Double getPM_UnitPrice() {
                return PM_UnitPrice;
            }

            public void setPM_UnitPrice(int PM_UnitPrice) {
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

            public void setPM_SpecialOfferValue(int PM_SpecialOfferValue) {
                this.PM_SpecialOfferValue = PM_SpecialOfferValue;
            }

            public double getPM_MinDisCountValue() {
                return PM_MinDisCountValue;
            }

            public void setPM_MinDisCountValue(int PM_MinDisCountValue) {
                this.PM_MinDisCountValue = PM_MinDisCountValue;
            }

            public double getPM_FixedIntegralValue() {
                return PM_FixedIntegralValue;
            }

            public void setPM_FixedIntegralValue(int PM_FixedIntegralValue) {
                this.PM_FixedIntegralValue = PM_FixedIntegralValue;
            }
        }
    }
}
