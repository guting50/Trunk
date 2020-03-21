package com.zhiluo.android.yunpu.ui.activity.good.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${YSL} on 2018-05-18.
 */

public class InRecoderBean implements Serializable {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":20,"DataCount":6,"PageIndex":1,"DataList":[{"GID":"87845e85-4ff8-4576-baa3-bf35cea9ac21","SI_TrackingNo":"CG1805171825104803","SI_TotalPrice":5,"SI_Creator":"18381306468","SI_CreateTime":"2018-05-17 18:25:17","SI_PayType":"现金(5)","SI_InType":"1","SI_PayCode":"XJZF","SM_Name":"云上铺1号店","SI_ISReturn":0,"SL_GID":"ec1f6062-f0db-46ac-9550-195cea6c5a8f","SL_Name":"服装","SI_ISReturName":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SI_OtherMoney":0,"SI_Remark":""},{"GID":"1075b520-e315-4844-b549-b9e1f2616f81","SI_TrackingNo":"CG1805171824319902","SI_TotalPrice":5,"SI_Creator":"18381306468","SI_CreateTime":"2018-05-17 18:24:52","SI_PayType":"现金(5)","SI_InType":"1","SI_PayCode":"XJZF","SM_Name":"云上铺1号店","SI_ISReturn":0,"SL_GID":"ec1f6062-f0db-46ac-9550-195cea6c5a8f","SL_Name":"服装","SI_ISReturName":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SI_OtherMoney":0,"SI_Remark":""},{"GID":"2a0ca22b-a542-4687-9e48-c3922eed9fb2","SI_TrackingNo":"CG1805171816293076","SI_TotalPrice":5,"SI_Creator":"18381306468","SI_CreateTime":"2018-05-17 18:17:27","SI_PayType":null,"SI_InType":"1","SI_PayCode":"","SM_Name":"云上铺1号店","SI_ISReturn":0,"SL_GID":"ec1f6062-f0db-46ac-9550-195cea6c5a8f","SL_Name":"服装","SI_ISReturName":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SI_OtherMoney":0,"SI_Remark":""},{"GID":"3b144482-9902-4b54-a590-935324b928c1","SI_TrackingNo":"CG1805171815283811","SI_TotalPrice":30,"SI_Creator":"18381306468","SI_CreateTime":"2018-05-17 18:16:28","SI_PayType":null,"SI_InType":"1","SI_PayCode":"","SM_Name":"云上铺1号店","SI_ISReturn":0,"SL_GID":"","SL_Name":"","SI_ISReturName":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SI_OtherMoney":0,"SI_Remark":""},{"GID":"2cc608ca-0512-41d1-b734-02ba009bcf03","SI_TrackingNo":"CG1805171341446686","SI_TotalPrice":554,"SI_Creator":"18381306468","SI_CreateTime":"2018-05-17 13:42:17","SI_PayType":"现金(554)","SI_InType":"1","SI_PayCode":"XJZF","SM_Name":"云上铺1号店","SI_ISReturn":0,"SL_GID":"ec1f6062-f0db-46ac-9550-195cea6c5a8f","SL_Name":"服装","SI_ISReturName":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SI_OtherMoney":0,"SI_Remark":""},{"GID":"1586a296-fdf4-4331-8294-638d51868345","SI_TrackingNo":"CG1805171336188634","SI_TotalPrice":118,"SI_Creator":"18381306468","SI_CreateTime":"2018-05-17 13:38:16","SI_PayType":"现金(118)","SI_InType":"1","SI_PayCode":"XJZF","SM_Name":"云上铺1号店","SI_ISReturn":0,"SL_GID":"ec1f6062-f0db-46ac-9550-195cea6c5a8f","SL_Name":"服装","SI_ISReturName":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SI_OtherMoney":0,"SI_Remark":""}],"StatisticsInfo":null}
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
         * PageSize : 20
         * DataCount : 6
         * PageIndex : 1
         * DataList : [{"GID":"87845e85-4ff8-4576-baa3-bf35cea9ac21","SI_TrackingNo":"CG1805171825104803","SI_TotalPrice":5,"SI_Creator":"18381306468","SI_CreateTime":"2018-05-17 18:25:17","SI_PayType":"现金(5)","SI_InType":"1","SI_PayCode":"XJZF","SM_Name":"云上铺1号店","SI_ISReturn":0,"SL_GID":"ec1f6062-f0db-46ac-9550-195cea6c5a8f","SL_Name":"服装","SI_ISReturName":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SI_OtherMoney":0,"SI_Remark":""},{"GID":"1075b520-e315-4844-b549-b9e1f2616f81","SI_TrackingNo":"CG1805171824319902","SI_TotalPrice":5,"SI_Creator":"18381306468","SI_CreateTime":"2018-05-17 18:24:52","SI_PayType":"现金(5)","SI_InType":"1","SI_PayCode":"XJZF","SM_Name":"云上铺1号店","SI_ISReturn":0,"SL_GID":"ec1f6062-f0db-46ac-9550-195cea6c5a8f","SL_Name":"服装","SI_ISReturName":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SI_OtherMoney":0,"SI_Remark":""},{"GID":"2a0ca22b-a542-4687-9e48-c3922eed9fb2","SI_TrackingNo":"CG1805171816293076","SI_TotalPrice":5,"SI_Creator":"18381306468","SI_CreateTime":"2018-05-17 18:17:27","SI_PayType":null,"SI_InType":"1","SI_PayCode":"","SM_Name":"云上铺1号店","SI_ISReturn":0,"SL_GID":"ec1f6062-f0db-46ac-9550-195cea6c5a8f","SL_Name":"服装","SI_ISReturName":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SI_OtherMoney":0,"SI_Remark":""},{"GID":"3b144482-9902-4b54-a590-935324b928c1","SI_TrackingNo":"CG1805171815283811","SI_TotalPrice":30,"SI_Creator":"18381306468","SI_CreateTime":"2018-05-17 18:16:28","SI_PayType":null,"SI_InType":"1","SI_PayCode":"","SM_Name":"云上铺1号店","SI_ISReturn":0,"SL_GID":"","SL_Name":"","SI_ISReturName":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SI_OtherMoney":0,"SI_Remark":""},{"GID":"2cc608ca-0512-41d1-b734-02ba009bcf03","SI_TrackingNo":"CG1805171341446686","SI_TotalPrice":554,"SI_Creator":"18381306468","SI_CreateTime":"2018-05-17 13:42:17","SI_PayType":"现金(554)","SI_InType":"1","SI_PayCode":"XJZF","SM_Name":"云上铺1号店","SI_ISReturn":0,"SL_GID":"ec1f6062-f0db-46ac-9550-195cea6c5a8f","SL_Name":"服装","SI_ISReturName":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SI_OtherMoney":0,"SI_Remark":""},{"GID":"1586a296-fdf4-4331-8294-638d51868345","SI_TrackingNo":"CG1805171336188634","SI_TotalPrice":118,"SI_Creator":"18381306468","SI_CreateTime":"2018-05-17 13:38:16","SI_PayType":"现金(118)","SI_InType":"1","SI_PayCode":"XJZF","SM_Name":"云上铺1号店","SI_ISReturn":0,"SL_GID":"ec1f6062-f0db-46ac-9550-195cea6c5a8f","SL_Name":"服装","SI_ISReturName":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SI_OtherMoney":0,"SI_Remark":""}]
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
             * GID : 87845e85-4ff8-4576-baa3-bf35cea9ac21
             * SI_TrackingNo : CG1805171825104803
             * SI_TotalPrice : 5.0
             * SI_Creator : 18381306468
             * SI_CreateTime : 2018-05-17 18:25:17
             * SI_PayType : 现金(5)
             * SI_InType : 1
             * SI_PayCode : XJZF
             * SM_Name : 云上铺1号店
             * SI_ISReturn : 0
             * SL_GID : ec1f6062-f0db-46ac-9550-195cea6c5a8f
             * SL_Name : 服装
             * SI_ISReturName : null
             * SM_GID : 966d986b-0caa-465b-bbe1-8bd871bf8a0a
             * SI_OtherMoney : 0.0
             * SI_Remark :
             */

            private String GID;
            private String SI_TrackingNo;
            private double SI_TotalPrice;
            private String SI_Creator;
            private String SI_CreateTime;
            private String SI_PayType;
            private String SI_InType;
            private String SI_PayCode;
            private String SM_Name;
            private int SI_ISReturn;
            private String SL_GID;
            private String SL_Name;
            private Object SI_ISReturName;
            private String SM_GID;
            private double SI_OtherMoney;
            private String SI_Remark;

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public String getSI_TrackingNo() {
                return SI_TrackingNo;
            }

            public void setSI_TrackingNo(String SI_TrackingNo) {
                this.SI_TrackingNo = SI_TrackingNo;
            }

            public double getSI_TotalPrice() {
                return SI_TotalPrice;
            }

            public void setSI_TotalPrice(double SI_TotalPrice) {
                this.SI_TotalPrice = SI_TotalPrice;
            }

            public String getSI_Creator() {
                return SI_Creator;
            }

            public void setSI_Creator(String SI_Creator) {
                this.SI_Creator = SI_Creator;
            }

            public String getSI_CreateTime() {
                return SI_CreateTime;
            }

            public void setSI_CreateTime(String SI_CreateTime) {
                this.SI_CreateTime = SI_CreateTime;
            }

            public String getSI_PayType() {
                return SI_PayType;
            }

            public void setSI_PayType(String SI_PayType) {
                this.SI_PayType = SI_PayType;
            }

            public String getSI_InType() {
                return SI_InType;
            }

            public void setSI_InType(String SI_InType) {
                this.SI_InType = SI_InType;
            }

            public String getSI_PayCode() {
                return SI_PayCode;
            }

            public void setSI_PayCode(String SI_PayCode) {
                this.SI_PayCode = SI_PayCode;
            }

            public String getSM_Name() {
                return SM_Name;
            }

            public void setSM_Name(String SM_Name) {
                this.SM_Name = SM_Name;
            }

            public int getSI_ISReturn() {
                return SI_ISReturn;
            }

            public void setSI_ISReturn(int SI_ISReturn) {
                this.SI_ISReturn = SI_ISReturn;
            }

            public String getSL_GID() {
                return SL_GID;
            }

            public void setSL_GID(String SL_GID) {
                this.SL_GID = SL_GID;
            }

            public String getSL_Name() {
                return SL_Name;
            }

            public void setSL_Name(String SL_Name) {
                this.SL_Name = SL_Name;
            }

            public Object getSI_ISReturName() {
                return SI_ISReturName;
            }

            public void setSI_ISReturName(Object SI_ISReturName) {
                this.SI_ISReturName = SI_ISReturName;
            }

            public String getSM_GID() {
                return SM_GID;
            }

            public void setSM_GID(String SM_GID) {
                this.SM_GID = SM_GID;
            }

            public double getSI_OtherMoney() {
                return SI_OtherMoney;
            }

            public void setSI_OtherMoney(double SI_OtherMoney) {
                this.SI_OtherMoney = SI_OtherMoney;
            }

            public String getSI_Remark() {
                return SI_Remark;
            }

            public void setSI_Remark(String SI_Remark) {
                this.SI_Remark = SI_Remark;
            }
        }
    }
}
