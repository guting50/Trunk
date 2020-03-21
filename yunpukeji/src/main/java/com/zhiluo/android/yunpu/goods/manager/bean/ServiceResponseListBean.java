package com.zhiluo.android.yunpu.goods.manager.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 获取服务返回实体
 * 作者：罗咏哲 on 2017/6/29 16:45.
 * 邮箱：137615198@qq.com
 */

public class ServiceResponseListBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":10,"DataCount":10,"PageIndex":1,"DataList":[{"Stock_Number":-1,"PM_BigImg":null,"PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":1,"PD_Discount":100,"VS_Number":1,"PTD_Type":0,"PT_GID":"dc578f78-1bc7-4b7c-97ee-050e8107f39d","PT_Name":"华为","PM_GID":"942d6a50-80ba-4f58-8865-62e97677388b","PM_Name":"天啦噜","PM_Metering":"元","PM_SimpleCode":"2","PM_Code":"0025555","PM_UnitPrice":5566,"PM_Description":"","PM_Modle":"5","PM_Brand":"你空间里","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"Stock_Number":-1,"PM_BigImg":null,"PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":1,"PD_Discount":100,"VS_Number":1,"PTD_Type":0,"PT_GID":"cb1f9920-d08f-4008-b372-e0d4a01ea2e8","PT_Name":"小米","PM_GID":"210377b2-49bc-44f1-a086-05a37ab4c928","PM_Name":"添加服务测试","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"s005","PM_UnitPrice":1223,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"Stock_Number":0,"PM_BigImg":null,"PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":1,"PD_Discount":100,"VS_Number":1,"PTD_Type":0,"PT_GID":"cb1f9920-d08f-4008-b372-e0d4a01ea2e8","PT_Name":"小米","PM_GID":"de8829d4-06a0-4e5e-bbdd-0f317a0d1003","PM_Name":"添加服务测试","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"s005","PM_UnitPrice":1223,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"Stock_Number":-10,"PM_BigImg":"../../img/goods.png","PM_IsDiscount":1,"PM_IsPoint":1,"PM_IsService":1,"PD_Discount":100,"VS_Number":1,"PTD_Type":0,"PT_GID":"f76742b8-170e-42e0-b732-f8903a682b4d","PT_Name":"英语口语辅导","PM_GID":"d7f7bf3b-f957-4f4f-a3f3-e983d244a3c4","PM_Name":"英语口语练习","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"E028","PM_UnitPrice":199,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"Stock_Number":-1,"PM_BigImg":null,"PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":1,"PD_Discount":100,"VS_Number":1,"PTD_Type":0,"PT_GID":"5bf9a96b-7c7b-47c8-bf27-b2a01afcf4c0","PT_Name":"修","PM_GID":"f0ea0f7a-3ad6-4546-861e-dcc0d8dc7d8f","PM_Name":"服务测试","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"456","PM_UnitPrice":80,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"Stock_Number":-1,"PM_BigImg":null,"PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":1,"PD_Discount":100,"VS_Number":1,"PTD_Type":0,"PT_GID":"f76742b8-170e-42e0-b732-f8903a682b4d","PT_Name":"英语口语辅导","PM_GID":"df76751e-fe52-4eb9-a021-00eb3c5d31c4","PM_Name":"英语培训","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"568","PM_UnitPrice":80,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"Stock_Number":-1,"PM_BigImg":null,"PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":1,"PD_Discount":100,"VS_Number":1,"PTD_Type":0,"PT_GID":"f76742b8-170e-42e0-b732-f8903a682b4d","PT_Name":"英语口语辅导","PM_GID":"4c6eb8a0-d7f9-4054-a54d-b6f739d73049","PM_Name":"服务服务","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"5689","PM_UnitPrice":23,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"Stock_Number":-3,"PM_BigImg":"/img/goods.png","PM_IsDiscount":1,"PM_IsPoint":1,"PM_IsService":1,"PD_Discount":100,"VS_Number":1,"PTD_Type":0,"PT_GID":"5bf9a96b-7c7b-47c8-bf27-b2a01afcf4c0","PT_Name":"修","PM_GID":"0be37f3f-4f1f-4da6-84ce-29ba140da5c5","PM_Name":"维修测试","PM_Metering":"y","PM_SimpleCode":"S","PM_Code":"S0121","PM_UnitPrice":20,"PM_Description":"","PM_Modle":"gg","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"Stock_Number":0,"PM_BigImg":"/FileUpload/131479474920130794.png","PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":1,"PD_Discount":100,"VS_Number":0,"PTD_Type":0,"PT_GID":"ac2a3dde-61e4-49d4-8462-719fa37accca","PT_Name":"娱乐","PM_GID":"6653516f-4b82-4826-9be4-f62f1394171c","PM_Name":"洗澡","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"123456","PM_UnitPrice":20,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"Stock_Number":-3,"PM_BigImg":"/img/goods.png","PM_IsDiscount":1,"PM_IsPoint":1,"PM_IsService":1,"PD_Discount":100,"VS_Number":1,"PTD_Type":0,"PT_GID":"78f1f272-6998-4310-9900-81ad54e3e455","PT_Name":"运动","PM_GID":"3c1eb4b2-9b53-4220-8abd-d2ee96d4d3b0","PM_Name":"维修","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"000234","PM_UnitPrice":10,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0}],"StatisticsInfo":null}
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
         * PageSize : 10
         * DataCount : 10
         * PageIndex : 1
         * DataList : [{"Stock_Number":-1,"PM_BigImg":null,"PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":1,"PD_Discount":100,"VS_Number":1,"PTD_Type":0,"PT_GID":"dc578f78-1bc7-4b7c-97ee-050e8107f39d","PT_Name":"华为","PM_GID":"942d6a50-80ba-4f58-8865-62e97677388b","PM_Name":"天啦噜","PM_Metering":"元","PM_SimpleCode":"2","PM_Code":"0025555","PM_UnitPrice":5566,"PM_Description":"","PM_Modle":"5","PM_Brand":"你空间里","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"Stock_Number":-1,"PM_BigImg":null,"PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":1,"PD_Discount":100,"VS_Number":1,"PTD_Type":0,"PT_GID":"cb1f9920-d08f-4008-b372-e0d4a01ea2e8","PT_Name":"小米","PM_GID":"210377b2-49bc-44f1-a086-05a37ab4c928","PM_Name":"添加服务测试","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"s005","PM_UnitPrice":1223,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"Stock_Number":0,"PM_BigImg":null,"PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":1,"PD_Discount":100,"VS_Number":1,"PTD_Type":0,"PT_GID":"cb1f9920-d08f-4008-b372-e0d4a01ea2e8","PT_Name":"小米","PM_GID":"de8829d4-06a0-4e5e-bbdd-0f317a0d1003","PM_Name":"添加服务测试","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"s005","PM_UnitPrice":1223,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"Stock_Number":-10,"PM_BigImg":"../../img/goods.png","PM_IsDiscount":1,"PM_IsPoint":1,"PM_IsService":1,"PD_Discount":100,"VS_Number":1,"PTD_Type":0,"PT_GID":"f76742b8-170e-42e0-b732-f8903a682b4d","PT_Name":"英语口语辅导","PM_GID":"d7f7bf3b-f957-4f4f-a3f3-e983d244a3c4","PM_Name":"英语口语练习","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"E028","PM_UnitPrice":199,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"Stock_Number":-1,"PM_BigImg":null,"PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":1,"PD_Discount":100,"VS_Number":1,"PTD_Type":0,"PT_GID":"5bf9a96b-7c7b-47c8-bf27-b2a01afcf4c0","PT_Name":"修","PM_GID":"f0ea0f7a-3ad6-4546-861e-dcc0d8dc7d8f","PM_Name":"服务测试","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"456","PM_UnitPrice":80,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"Stock_Number":-1,"PM_BigImg":null,"PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":1,"PD_Discount":100,"VS_Number":1,"PTD_Type":0,"PT_GID":"f76742b8-170e-42e0-b732-f8903a682b4d","PT_Name":"英语口语辅导","PM_GID":"df76751e-fe52-4eb9-a021-00eb3c5d31c4","PM_Name":"英语培训","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"568","PM_UnitPrice":80,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"Stock_Number":-1,"PM_BigImg":null,"PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":1,"PD_Discount":100,"VS_Number":1,"PTD_Type":0,"PT_GID":"f76742b8-170e-42e0-b732-f8903a682b4d","PT_Name":"英语口语辅导","PM_GID":"4c6eb8a0-d7f9-4054-a54d-b6f739d73049","PM_Name":"服务服务","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"5689","PM_UnitPrice":23,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"Stock_Number":-3,"PM_BigImg":"/img/goods.png","PM_IsDiscount":1,"PM_IsPoint":1,"PM_IsService":1,"PD_Discount":100,"VS_Number":1,"PTD_Type":0,"PT_GID":"5bf9a96b-7c7b-47c8-bf27-b2a01afcf4c0","PT_Name":"修","PM_GID":"0be37f3f-4f1f-4da6-84ce-29ba140da5c5","PM_Name":"维修测试","PM_Metering":"y","PM_SimpleCode":"S","PM_Code":"S0121","PM_UnitPrice":20,"PM_Description":"","PM_Modle":"gg","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"Stock_Number":0,"PM_BigImg":"/FileUpload/131479474920130794.png","PM_IsDiscount":0,"PM_IsPoint":0,"PM_IsService":1,"PD_Discount":100,"VS_Number":0,"PTD_Type":0,"PT_GID":"ac2a3dde-61e4-49d4-8462-719fa37accca","PT_Name":"娱乐","PM_GID":"6653516f-4b82-4826-9be4-f62f1394171c","PM_Name":"洗澡","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"123456","PM_UnitPrice":20,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0},{"Stock_Number":-3,"PM_BigImg":"/img/goods.png","PM_IsDiscount":1,"PM_IsPoint":1,"PM_IsService":1,"PD_Discount":100,"VS_Number":1,"PTD_Type":0,"PT_GID":"78f1f272-6998-4310-9900-81ad54e3e455","PT_Name":"运动","PM_GID":"3c1eb4b2-9b53-4220-8abd-d2ee96d4d3b0","PM_Name":"维修","PM_Metering":null,"PM_SimpleCode":"","PM_Code":"000234","PM_UnitPrice":10,"PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","CP_BarginPrice":null,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0}]
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
             * Stock_Number : -1
             * PM_BigImg : null
             * PM_IsDiscount : 0
             * PM_IsPoint : 0
             * PM_IsService : 1
             * PD_Discount : 100.0
             * VS_Number : 1.0
             * PTD_Type : 0
             * PT_GID : dc578f78-1bc7-4b7c-97ee-050e8107f39d
             * PT_Name : 华为
             * PM_GID : 942d6a50-80ba-4f58-8865-62e97677388b
             * PM_Name : 天啦噜
             * PM_Metering : 元
             * PM_SimpleCode : 2
             * PM_Code : 0025555
             * PM_UnitPrice : 5566.0
             * PM_Description :
             * PM_Modle : 5
             * PM_Brand : 你空间里
             * CY_GID : 348e79f2-ff35-4d62-8298-9d37f06fd5e0
             * VG_GID : b6a603d4-ec07-4b85-8252-87bfa8d79697
             * CP_BarginPrice : null
             * PM_SpecialOfferValue : 0.0
             * PM_MinDisCountValue : 0.0
             * PM_FixedIntegralValue : 0.0
             */

            private double Stock_Number;
            private String PM_BigImg;
            private int PM_IsDiscount;
            private int PM_IsPoint;
            private int PM_IsService;
            private double PD_Discount;
            private double VS_Number;
            private int PTD_Type;
            private String PT_ID;
            private String PT_Name;
            private String GID;
            private String PM_Name;
            private String PM_Metering;
            private String PM_SimpleCode;
            private String PM_Code;
            private double PM_UnitPrice;
            private String PM_Description;
            private String PM_Modle;
            private String PM_Brand;
            private String CY_GID;
            private String VG_GID;
            private Object CP_BarginPrice;
            private double PM_SpecialOfferValue;
            private double PM_MinDisCountValue;
            private double PM_FixedIntegralValue;
            private double PM_MemPrice;

            public double getPM_MemPrice() {
                return PM_MemPrice;
            }

            public void setPM_MemPrice(double PM_MemPrice) {
                this.PM_MemPrice = PM_MemPrice;
            }

            public double getStock_Number() {
                return Stock_Number;
            }

            public void setStock_Number(double Stock_Number) {
                this.Stock_Number = Stock_Number;
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

            public int getPTD_Type() {
                return PTD_Type;
            }

            public void setPTD_Type(int PTD_Type) {
                this.PTD_Type = PTD_Type;
            }

            public String getPT_GID() {
                return PT_ID;
            }

            public void setPT_GID(String PT_GID) {
                this.PT_ID = PT_GID;
            }

            public String getPT_Name() {
                return PT_Name;
            }

            public void setPT_Name(String PT_Name) {
                this.PT_Name = PT_Name;
            }

            public String getPM_GID() {
                return GID;
            }

            public void setPM_GID(String PM_GID) {
                this.GID = PM_GID;
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

            public String getVG_GID() {
                return VG_GID;
            }

            public void setVG_GID(String VG_GID) {
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
