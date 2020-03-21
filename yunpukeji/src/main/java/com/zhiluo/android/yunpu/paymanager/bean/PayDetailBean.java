package com.zhiluo.android.yunpu.paymanager.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZPH on 2019-05-23.
 */

public class PayDetailBean implements Serializable {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":20,"DataCount":3,"PageIndex":1,"DataList":[{"ET_Name":"个人支出 \u2014 餐饮","GID":"0e1fe25d-5de3-4088-a33a-8ead6aa8633c","ET_GID":"8867635c-89e2-4dff-96c0-fefb91b96f14","ED_Money":20,"ED_Time":"2019-05-23 11:00:09","ED_Remark":"1","ED_Expendiator":"C位狙击吃鸡手","EM_GID":"0f87e1fc-d8a0-4681-88e6-64bf08b0b15a","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ED_Creator":"分号1","ED_CreateTime":"2019-05-23 11:00:28"},{"ET_Name":"个人支出2 \u2014 餐饮","GID":"dcc54277-c922-4327-908a-7cbbe7b3e8d6","ET_GID":"8867635c-89e2-4dff-96c0-fefb91b96f14","ED_Money":20,"ED_Time":"2019-05-22 17:13:01","ED_Remark":"","ED_Expendiator":"C位狙击吃鸡手","EM_GID":"0f87e1fc-d8a0-4681-88e6-64bf08b0b15a","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ED_Creator":"销售001","ED_CreateTime":"2019-05-22 17:13:12"},{"ET_Name":"个人支出 \u2014 餐饮","GID":"be4a0cbd-a217-4518-b25e-4269ac189378","ET_GID":"8867635c-89e2-4dff-96c0-fefb91b96f14","ED_Money":20,"ED_Time":"2019-05-22 10:39:39","ED_Remark":"","ED_Expendiator":"C位狙击吃鸡手","EM_GID":"0f87e1fc-d8a0-4681-88e6-64bf08b0b15a","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ED_Creator":"销售001","ED_CreateTime":"2019-05-22 10:39:59"}],"StatisticsInfo":{"DataCount":60},"TrendData":null}
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
         * DataCount : 3
         * PageIndex : 1
         * DataList : [{"ET_Name":"个人支出 \u2014 餐饮","GID":"0e1fe25d-5de3-4088-a33a-8ead6aa8633c","ET_GID":"8867635c-89e2-4dff-96c0-fefb91b96f14","ED_Money":20,"ED_Time":"2019-05-23 11:00:09","ED_Remark":"1","ED_Expendiator":"C位狙击吃鸡手","EM_GID":"0f87e1fc-d8a0-4681-88e6-64bf08b0b15a","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ED_Creator":"分号1","ED_CreateTime":"2019-05-23 11:00:28"},{"ET_Name":"个人支出2 \u2014 餐饮","GID":"dcc54277-c922-4327-908a-7cbbe7b3e8d6","ET_GID":"8867635c-89e2-4dff-96c0-fefb91b96f14","ED_Money":20,"ED_Time":"2019-05-22 17:13:01","ED_Remark":"","ED_Expendiator":"C位狙击吃鸡手","EM_GID":"0f87e1fc-d8a0-4681-88e6-64bf08b0b15a","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ED_Creator":"销售001","ED_CreateTime":"2019-05-22 17:13:12"},{"ET_Name":"个人支出 \u2014 餐饮","GID":"be4a0cbd-a217-4518-b25e-4269ac189378","ET_GID":"8867635c-89e2-4dff-96c0-fefb91b96f14","ED_Money":20,"ED_Time":"2019-05-22 10:39:39","ED_Remark":"","ED_Expendiator":"C位狙击吃鸡手","EM_GID":"0f87e1fc-d8a0-4681-88e6-64bf08b0b15a","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ED_Creator":"销售001","ED_CreateTime":"2019-05-22 10:39:59"}]
         * StatisticsInfo : {"DataCount":60}
         * TrendData : null
         */

        private int PageTotal;
        private int PageSize;
        private int DataCount;
        private int PageIndex;
        private StatisticsInfoBean StatisticsInfo;
        private Object TrendData;
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

        public StatisticsInfoBean getStatisticsInfo() {
            return StatisticsInfo;
        }

        public void setStatisticsInfo(StatisticsInfoBean StatisticsInfo) {
            this.StatisticsInfo = StatisticsInfo;
        }

        public Object getTrendData() {
            return TrendData;
        }

        public void setTrendData(Object TrendData) {
            this.TrendData = TrendData;
        }

        public List<DataListBean> getDataList() {
            return DataList;
        }

        public void setDataList(List<DataListBean> DataList) {
            this.DataList = DataList;
        }

        public static class StatisticsInfoBean {
            /**
             * DataCount : 60.0
             */

            private double DataCount;

            public double getDataCount() {
                return DataCount;
            }

            public void setDataCount(double DataCount) {
                this.DataCount = DataCount;
            }
        }

        public static class DataListBean implements Serializable{
            /**
             * ET_Name : 个人支出 — 餐饮
             * GID : 0e1fe25d-5de3-4088-a33a-8ead6aa8633c
             * ET_GID : 8867635c-89e2-4dff-96c0-fefb91b96f14
             * ED_Money : 20.0
             * ED_Time : 2019-05-23 11:00:09
             * ED_Remark : 1
             * ED_Expendiator : C位狙击吃鸡手
             * EM_GID : 0f87e1fc-d8a0-4681-88e6-64bf08b0b15a
             * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
             * SM_GID : 4a7b1141-8ca3-4bef-961f-ffd153357823
             * ED_Creator : 分号1
             * ED_CreateTime : 2019-05-23 11:00:28
             */

            private String ET_Name;
            private String GID;
            private String ET_GID;
            private double ED_Money;
            private String ED_Time;
            private String ED_Remark;
            private String ED_Expendiator;
            private String EM_GID;
            private String CY_GID;
            private String SM_GID;
            private String ED_Creator;
            private String ED_CreateTime;

            public String getET_Name() {
                return ET_Name;
            }

            public void setET_Name(String ET_Name) {
                this.ET_Name = ET_Name;
            }

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public String getET_GID() {
                return ET_GID;
            }

            public void setET_GID(String ET_GID) {
                this.ET_GID = ET_GID;
            }

            public double getED_Money() {
                return ED_Money;
            }

            public void setED_Money(double ED_Money) {
                this.ED_Money = ED_Money;
            }

            public String getED_Time() {
                return ED_Time;
            }

            public void setED_Time(String ED_Time) {
                this.ED_Time = ED_Time;
            }

            public String getED_Remark() {
                return ED_Remark;
            }

            public void setED_Remark(String ED_Remark) {
                this.ED_Remark = ED_Remark;
            }

            public String getED_Expendiator() {
                return ED_Expendiator;
            }

            public void setED_Expendiator(String ED_Expendiator) {
                this.ED_Expendiator = ED_Expendiator;
            }

            public String getEM_GID() {
                return EM_GID;
            }

            public void setEM_GID(String EM_GID) {
                this.EM_GID = EM_GID;
            }

            public String getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(String CY_GID) {
                this.CY_GID = CY_GID;
            }

            public String getSM_GID() {
                return SM_GID;
            }

            public void setSM_GID(String SM_GID) {
                this.SM_GID = SM_GID;
            }

            public String getED_Creator() {
                return ED_Creator;
            }

            public void setED_Creator(String ED_Creator) {
                this.ED_Creator = ED_Creator;
            }

            public String getED_CreateTime() {
                return ED_CreateTime;
            }

            public void setED_CreateTime(String ED_CreateTime) {
                this.ED_CreateTime = ED_CreateTime;
            }
        }
    }
}
