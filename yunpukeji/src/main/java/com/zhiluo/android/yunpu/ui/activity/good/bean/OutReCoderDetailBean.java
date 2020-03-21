package com.zhiluo.android.yunpu.ui.activity.good.bean;

import java.util.List;

/**
 * Created by YSL on 2018-06-20.
 */

public class OutReCoderDetailBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":999,"DataCount":1,"PageIndex":1,"DataList":[{"SL_Name":null,"SO_TrackingNo":"CK1806201143167905","SOD_DiscountPrice":4,"GID":"d5c6a208-801e-4704-aac2-e1cd4207edb1","SO_GID":"c4a92e3d-9e73-4176-823c-62e88423a7f5","SOD_Amount":1,"SOD_TotalPrice":4,"SOD_CreateTime":"2018-06-20 11:44:01","SOD_Remark":null,"SOD_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","PM_Code":"123456","PM_Name":"礼品","PM_SimpleCode":null,"PM_Metering":"","PM_UnitPrice":4,"PM_Purchase":null,"PT_ID":"5a71e610-963b-4627-975c-aa3205f5bf37","PM_Description":null,"PM_Modle":"","PM_Brand":"","PM_GID":"91dfd4f4-a76e-4a68-91f6-ded9a9cc7497","PT_Name":"笔呦提否","PM_BigImg":"/img/goods.png"}],"StatisticsInfo":null}
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
         * PageTotal : 1
         * PageSize : 999
         * DataCount : 1
         * PageIndex : 1
         * DataList : [{"SL_Name":null,"SO_TrackingNo":"CK1806201143167905","SOD_DiscountPrice":4,"GID":"d5c6a208-801e-4704-aac2-e1cd4207edb1","SO_GID":"c4a92e3d-9e73-4176-823c-62e88423a7f5","SOD_Amount":1,"SOD_TotalPrice":4,"SOD_CreateTime":"2018-06-20 11:44:01","SOD_Remark":null,"SOD_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","PM_Code":"123456","PM_Name":"礼品","PM_SimpleCode":null,"PM_Metering":"","PM_UnitPrice":4,"PM_Purchase":null,"PT_ID":"5a71e610-963b-4627-975c-aa3205f5bf37","PM_Description":null,"PM_Modle":"","PM_Brand":"","PM_GID":"91dfd4f4-a76e-4a68-91f6-ded9a9cc7497","PT_Name":"笔呦提否","PM_BigImg":"/img/goods.png"}]
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

        public static class DataListBean {
            /**
             * SL_Name : null
             * SO_TrackingNo : CK1806201143167905
             * SOD_DiscountPrice : 4.0
             * GID : d5c6a208-801e-4704-aac2-e1cd4207edb1
             * SO_GID : c4a92e3d-9e73-4176-823c-62e88423a7f5
             * SOD_Amount : 1.0
             * SOD_TotalPrice : 4.0
             * SOD_CreateTime : 2018-06-20 11:44:01
             * SOD_Remark : null
             * SOD_Creator : 18381306468
             * CY_GID : null
             * SM_GID : 966d986b-0caa-465b-bbe1-8bd871bf8a0a
             * PM_Code : 123456
             * PM_Name : 礼品
             * PM_SimpleCode : null
             * PM_Metering :
             * PM_UnitPrice : 4.0
             * PM_Purchase : null
             * PT_ID : 5a71e610-963b-4627-975c-aa3205f5bf37
             * PM_Description : null
             * PM_Modle :
             * PM_Brand :
             * PM_GID : 91dfd4f4-a76e-4a68-91f6-ded9a9cc7497
             * PT_Name : 笔呦提否
             * PM_BigImg : /img/goods.png
             */

            private Object SL_Name;
            private String SO_TrackingNo;
            private double SOD_DiscountPrice;
            private String GID;
            private String SO_GID;
            private double SOD_Amount;
            private double SOD_TotalPrice;
            private String SOD_CreateTime;
            private Object SOD_Remark;
            private String SOD_Creator;
            private Object CY_GID;
            private String SM_GID;
            private String PM_Code;
            private String PM_Name;
            private Object PM_SimpleCode;
            private String PM_Metering;
            private double PM_UnitPrice;
            private Object PM_Purchase;
            private String PT_ID;
            private Object PM_Description;
            private String PM_Modle;
            private String PM_Brand;
            private String PM_GID;
            private String PT_Name;
            private String PM_BigImg;

            public double getPM_PurchasePrice() {
                return PM_PurchasePrice;
            }

            public void setPM_PurchasePrice(double PM_PurchasePrice) {
                this.PM_PurchasePrice = PM_PurchasePrice;
            }

            private double PM_PurchasePrice;

            public Object getSL_Name() {
                return SL_Name;
            }

            public void setSL_Name(Object SL_Name) {
                this.SL_Name = SL_Name;
            }

            public String getSO_TrackingNo() {
                return SO_TrackingNo;
            }

            public void setSO_TrackingNo(String SO_TrackingNo) {
                this.SO_TrackingNo = SO_TrackingNo;
            }

            public double getSOD_DiscountPrice() {
                return SOD_DiscountPrice;
            }

            public void setSOD_DiscountPrice(double SOD_DiscountPrice) {
                this.SOD_DiscountPrice = SOD_DiscountPrice;
            }

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public String getSO_GID() {
                return SO_GID;
            }

            public void setSO_GID(String SO_GID) {
                this.SO_GID = SO_GID;
            }

            public double getSOD_Amount() {
                return SOD_Amount;
            }

            public void setSOD_Amount(double SOD_Amount) {
                this.SOD_Amount = SOD_Amount;
            }

            public double getSOD_TotalPrice() {
                return SOD_TotalPrice;
            }

            public void setSOD_TotalPrice(double SOD_TotalPrice) {
                this.SOD_TotalPrice = SOD_TotalPrice;
            }

            public String getSOD_CreateTime() {
                return SOD_CreateTime;
            }

            public void setSOD_CreateTime(String SOD_CreateTime) {
                this.SOD_CreateTime = SOD_CreateTime;
            }

            public Object getSOD_Remark() {
                return SOD_Remark;
            }

            public void setSOD_Remark(Object SOD_Remark) {
                this.SOD_Remark = SOD_Remark;
            }

            public String getSOD_Creator() {
                return SOD_Creator;
            }

            public void setSOD_Creator(String SOD_Creator) {
                this.SOD_Creator = SOD_Creator;
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

            public Object getPM_SimpleCode() {
                return PM_SimpleCode;
            }

            public void setPM_SimpleCode(Object PM_SimpleCode) {
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

            public Object getPM_Purchase() {
                return PM_Purchase;
            }

            public void setPM_Purchase(Object PM_Purchase) {
                this.PM_Purchase = PM_Purchase;
            }

            public String getPT_ID() {
                return PT_ID;
            }

            public void setPT_ID(String PT_ID) {
                this.PT_ID = PT_ID;
            }

            public Object getPM_Description() {
                return PM_Description;
            }

            public void setPM_Description(Object PM_Description) {
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

            public String getPM_BigImg() {
                return PM_BigImg;
            }

            public void setPM_BigImg(String PM_BigImg) {
                this.PM_BigImg = PM_BigImg;
            }
        }
    }
}
