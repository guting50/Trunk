package com.zhiluo.android.yunpu.statistics.account.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZPH on 2019-05-17.
 */

public class LimitedConsumeReportBean implements Serializable{


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":20,"DataCount":11,"PageIndex":1,"DataList":[{"VIP_Phone":"","VIP_FaceNumber":"66984131312133","Is_Sms":false,"GID":"c0aa9c0f-ddff-4d4e-8d05-ecfbb3e300c6","VIP_GID":"0d096078-d838-4423-a81d-678f4d241f16","VIP_Card":"5497498651651","VIP_Name":"ASDASAS","TS_SurplusTimes":3,"TS_OverTime":"2020-05-09 10:44:17","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":1,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"销售001","TS_UpdateTime":"2019-05-17 11:53:10","TS_Remark":""},{"VIP_Phone":"13994948797","VIP_FaceNumber":"还是明","Is_Sms":false,"GID":"1ac02e70-2520-4fa3-a1a4-5834917c7d38","VIP_GID":"cf74f057-daa2-4112-82fc-178eff77807a","VIP_Card":"13994948797","VIP_Name":"给你MSN","TS_SurplusTimes":0,"TS_OverTime":"2020-03-13 11:15:54","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":0,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"分号1","TS_UpdateTime":"2019-05-17 11:28:58","TS_Remark":"1"},{"VIP_Phone":"18667559757","VIP_FaceNumber":"萝莉控","Is_Sms":false,"GID":"b333008c-70af-48ef-8f17-3f6a1d3dc52d","VIP_GID":"061e3cbd-87ad-4ddd-9531-cd1cc1fb2dd4","VIP_Card":"18667559757","VIP_Name":"是这种","TS_SurplusTimes":0,"TS_OverTime":"2020-03-13 11:26:14","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":0,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"分号1","TS_UpdateTime":"2019-05-17 11:28:41","TS_Remark":"1"},{"VIP_Phone":"","VIP_FaceNumber":"66984131312133","Is_Sms":false,"GID":"b5b1d208-4eae-47a0-9905-2abecdce83b6","VIP_GID":"0d096078-d838-4423-a81d-678f4d241f16","VIP_Card":"5497498651651","VIP_Name":"ASDASAS","TS_SurplusTimes":4,"TS_OverTime":"2020-05-09 10:44:17","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":0,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"分号1","TS_UpdateTime":"2019-05-17 11:28:10","TS_Remark":"1"},{"VIP_Phone":"","VIP_FaceNumber":"66984131312133","Is_Sms":false,"GID":"2e7bceeb-b4b2-43c8-a3d8-c33b5a9724cf","VIP_GID":"0d096078-d838-4423-a81d-678f4d241f16","VIP_Card":"5497498651651","VIP_Name":"ASDASAS","TS_SurplusTimes":5,"TS_OverTime":"2020-05-09 10:44:17","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":0,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"分号1","TS_UpdateTime":"2019-05-17 11:28:02","TS_Remark":"1"},{"VIP_Phone":"","VIP_FaceNumber":"66984131312133","Is_Sms":false,"GID":"efe168af-5b05-48a5-9579-e05a36e8d8c0","VIP_GID":"0d096078-d838-4423-a81d-678f4d241f16","VIP_Card":"5497498651651","VIP_Name":"ASDASAS","TS_SurplusTimes":6,"TS_OverTime":"2020-05-09 10:44:17","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":1,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"分号1","TS_UpdateTime":"2019-05-17 11:08:40","TS_Remark":""},{"VIP_Phone":"","VIP_FaceNumber":"66984131312133","Is_Sms":false,"GID":"66312369-eed9-43e8-89c2-c442a5dda816","VIP_GID":"0d096078-d838-4423-a81d-678f4d241f16","VIP_Card":"5497498651651","VIP_Name":"ASDASAS","TS_SurplusTimes":7,"TS_OverTime":"2020-05-09 10:44:17","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":1,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"销售001","TS_UpdateTime":"2019-05-16 15:56:41","TS_Remark":""},{"VIP_Phone":"","VIP_FaceNumber":"66984131312133","Is_Sms":false,"GID":"bfe0955e-dc57-4d43-be15-7eca6ed3565e","VIP_GID":"0d096078-d838-4423-a81d-678f4d241f16","VIP_Card":"5497498651651","VIP_Name":"ASDASAS","TS_SurplusTimes":8,"TS_OverTime":"2020-05-09 10:44:17","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":1,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"销售001","TS_UpdateTime":"2019-05-16 15:54:34","TS_Remark":""},{"VIP_Phone":"","VIP_FaceNumber":"66984131312133","Is_Sms":false,"GID":"62287a0d-a0f6-45e4-869d-4bbd31fc34c7","VIP_GID":"0d096078-d838-4423-a81d-678f4d241f16","VIP_Card":"5497498651651","VIP_Name":"ASDASAS","TS_SurplusTimes":9,"TS_OverTime":"2020-05-09 10:44:17","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":1,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"销售001","TS_UpdateTime":"2019-05-16 15:53:45","TS_Remark":""},{"VIP_Phone":"","VIP_FaceNumber":"66984131312133","Is_Sms":false,"GID":"d27d28d5-7150-430e-bf15-2f053c88b1e0","VIP_GID":"0d096078-d838-4423-a81d-678f4d241f16","VIP_Card":"5497498651651","VIP_Name":"ASDASAS","TS_SurplusTimes":10,"TS_OverTime":"2020-05-09 10:44:17","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":1,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"销售001","TS_UpdateTime":"2019-05-09 10:44:50","TS_Remark":""},{"VIP_Phone":"","VIP_FaceNumber":"66984131312133","Is_Sms":false,"GID":"2d17341f-a27c-4c5f-8e37-e4319fcd480f","VIP_GID":"0d096078-d838-4423-a81d-678f4d241f16","VIP_Card":"5497498651651","VIP_Name":"ASDASAS","TS_SurplusTimes":11,"TS_OverTime":"2020-05-09 10:44:17","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":1,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"销售001","TS_UpdateTime":"2019-05-09 10:44:39","TS_Remark":""}],"StatisticsInfo":{"Times":11},"TrendData":null}
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
         * DataCount : 11
         * PageIndex : 1
         * DataList : [{"VIP_Phone":"","VIP_FaceNumber":"66984131312133","Is_Sms":false,"GID":"c0aa9c0f-ddff-4d4e-8d05-ecfbb3e300c6","VIP_GID":"0d096078-d838-4423-a81d-678f4d241f16","VIP_Card":"5497498651651","VIP_Name":"ASDASAS","TS_SurplusTimes":3,"TS_OverTime":"2020-05-09 10:44:17","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":1,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"销售001","TS_UpdateTime":"2019-05-17 11:53:10","TS_Remark":""},{"VIP_Phone":"13994948797","VIP_FaceNumber":"还是明","Is_Sms":false,"GID":"1ac02e70-2520-4fa3-a1a4-5834917c7d38","VIP_GID":"cf74f057-daa2-4112-82fc-178eff77807a","VIP_Card":"13994948797","VIP_Name":"给你MSN","TS_SurplusTimes":0,"TS_OverTime":"2020-03-13 11:15:54","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":0,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"分号1","TS_UpdateTime":"2019-05-17 11:28:58","TS_Remark":"1"},{"VIP_Phone":"18667559757","VIP_FaceNumber":"萝莉控","Is_Sms":false,"GID":"b333008c-70af-48ef-8f17-3f6a1d3dc52d","VIP_GID":"061e3cbd-87ad-4ddd-9531-cd1cc1fb2dd4","VIP_Card":"18667559757","VIP_Name":"是这种","TS_SurplusTimes":0,"TS_OverTime":"2020-03-13 11:26:14","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":0,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"分号1","TS_UpdateTime":"2019-05-17 11:28:41","TS_Remark":"1"},{"VIP_Phone":"","VIP_FaceNumber":"66984131312133","Is_Sms":false,"GID":"b5b1d208-4eae-47a0-9905-2abecdce83b6","VIP_GID":"0d096078-d838-4423-a81d-678f4d241f16","VIP_Card":"5497498651651","VIP_Name":"ASDASAS","TS_SurplusTimes":4,"TS_OverTime":"2020-05-09 10:44:17","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":0,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"分号1","TS_UpdateTime":"2019-05-17 11:28:10","TS_Remark":"1"},{"VIP_Phone":"","VIP_FaceNumber":"66984131312133","Is_Sms":false,"GID":"2e7bceeb-b4b2-43c8-a3d8-c33b5a9724cf","VIP_GID":"0d096078-d838-4423-a81d-678f4d241f16","VIP_Card":"5497498651651","VIP_Name":"ASDASAS","TS_SurplusTimes":5,"TS_OverTime":"2020-05-09 10:44:17","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":0,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"分号1","TS_UpdateTime":"2019-05-17 11:28:02","TS_Remark":"1"},{"VIP_Phone":"","VIP_FaceNumber":"66984131312133","Is_Sms":false,"GID":"efe168af-5b05-48a5-9579-e05a36e8d8c0","VIP_GID":"0d096078-d838-4423-a81d-678f4d241f16","VIP_Card":"5497498651651","VIP_Name":"ASDASAS","TS_SurplusTimes":6,"TS_OverTime":"2020-05-09 10:44:17","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":1,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"分号1","TS_UpdateTime":"2019-05-17 11:08:40","TS_Remark":""},{"VIP_Phone":"","VIP_FaceNumber":"66984131312133","Is_Sms":false,"GID":"66312369-eed9-43e8-89c2-c442a5dda816","VIP_GID":"0d096078-d838-4423-a81d-678f4d241f16","VIP_Card":"5497498651651","VIP_Name":"ASDASAS","TS_SurplusTimes":7,"TS_OverTime":"2020-05-09 10:44:17","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":1,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"销售001","TS_UpdateTime":"2019-05-16 15:56:41","TS_Remark":""},{"VIP_Phone":"","VIP_FaceNumber":"66984131312133","Is_Sms":false,"GID":"bfe0955e-dc57-4d43-be15-7eca6ed3565e","VIP_GID":"0d096078-d838-4423-a81d-678f4d241f16","VIP_Card":"5497498651651","VIP_Name":"ASDASAS","TS_SurplusTimes":8,"TS_OverTime":"2020-05-09 10:44:17","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":1,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"销售001","TS_UpdateTime":"2019-05-16 15:54:34","TS_Remark":""},{"VIP_Phone":"","VIP_FaceNumber":"66984131312133","Is_Sms":false,"GID":"62287a0d-a0f6-45e4-869d-4bbd31fc34c7","VIP_GID":"0d096078-d838-4423-a81d-678f4d241f16","VIP_Card":"5497498651651","VIP_Name":"ASDASAS","TS_SurplusTimes":9,"TS_OverTime":"2020-05-09 10:44:17","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":1,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"销售001","TS_UpdateTime":"2019-05-16 15:53:45","TS_Remark":""},{"VIP_Phone":"","VIP_FaceNumber":"66984131312133","Is_Sms":false,"GID":"d27d28d5-7150-430e-bf15-2f053c88b1e0","VIP_GID":"0d096078-d838-4423-a81d-678f4d241f16","VIP_Card":"5497498651651","VIP_Name":"ASDASAS","TS_SurplusTimes":10,"TS_OverTime":"2020-05-09 10:44:17","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":1,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"销售001","TS_UpdateTime":"2019-05-09 10:44:50","TS_Remark":""},{"VIP_Phone":"","VIP_FaceNumber":"66984131312133","Is_Sms":false,"GID":"2d17341f-a27c-4c5f-8e37-e4319fcd480f","VIP_GID":"0d096078-d838-4423-a81d-678f4d241f16","VIP_Card":"5497498651651","VIP_Name":"ASDASAS","TS_SurplusTimes":11,"TS_OverTime":"2020-05-09 10:44:17","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","TS_MinusTimes":1,"TS_Device":1,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TS_Creator":"销售001","TS_UpdateTime":"2019-05-09 10:44:39","TS_Remark":""}]
         * StatisticsInfo : {"Times":11}
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
             * Times : 11
             */

            private int Times;

            public int getTimes() {
                return Times;
            }

            public void setTimes(int Times) {
                this.Times = Times;
            }
        }

        public static class DataListBean implements Serializable{
            /**
             * VIP_Phone :
             * VIP_FaceNumber : 66984131312133
             * Is_Sms : false
             * GID : c0aa9c0f-ddff-4d4e-8d05-ecfbb3e300c6
             * VIP_GID : 0d096078-d838-4423-a81d-678f4d241f16
             * VIP_Card : 5497498651651
             * VIP_Name : ASDASAS
             * TS_SurplusTimes : 3
             * TS_OverTime : 2020-05-09 10:44:17
             * SM_GID : 4a7b1141-8ca3-4bef-961f-ffd153357823
             * SM_Name : jll2
             * TS_MinusTimes : 1
             * TS_Device : 1
             * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
             * TS_Creator : 销售001
             * TS_UpdateTime : 2019-05-17 11:53:10
             * TS_Remark :
             */

            private String VIP_Phone;
            private String VIP_FaceNumber;
            private boolean Is_Sms;
            private String GID;
            private String VIP_GID;
            private String VIP_Card;
            private String VIP_Name;
            private String TS_SurplusTimes;
            private String TS_OverTime;
            private String SM_GID;
            private String SM_Name;
            private int TS_MinusTimes;
            private int TS_Device;
            private String CY_GID;
            private String TS_Creator;
            private String TS_UpdateTime;
            private String TS_Remark;

            public String getVIP_Phone() {
                return VIP_Phone;
            }

            public void setVIP_Phone(String VIP_Phone) {
                this.VIP_Phone = VIP_Phone;
            }

            public String getVIP_FaceNumber() {
                return VIP_FaceNumber;
            }

            public void setVIP_FaceNumber(String VIP_FaceNumber) {
                this.VIP_FaceNumber = VIP_FaceNumber;
            }

            public boolean isIs_Sms() {
                return Is_Sms;
            }

            public void setIs_Sms(boolean Is_Sms) {
                this.Is_Sms = Is_Sms;
            }

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public String getVIP_GID() {
                return VIP_GID;
            }

            public void setVIP_GID(String VIP_GID) {
                this.VIP_GID = VIP_GID;
            }

            public String getVIP_Card() {
                return VIP_Card;
            }

            public void setVIP_Card(String VIP_Card) {
                this.VIP_Card = VIP_Card;
            }

            public String getVIP_Name() {
                return VIP_Name;
            }

            public void setVIP_Name(String VIP_Name) {
                this.VIP_Name = VIP_Name;
            }

            public String getTS_SurplusTimes() {
                return TS_SurplusTimes;
            }

            public void setTS_SurplusTimes(String TS_SurplusTimes) {
                this.TS_SurplusTimes = TS_SurplusTimes;
            }

            public String getTS_OverTime() {
                return TS_OverTime;
            }

            public void setTS_OverTime(String TS_OverTime) {
                this.TS_OverTime = TS_OverTime;
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

            public int getTS_MinusTimes() {
                return TS_MinusTimes;
            }

            public void setTS_MinusTimes(int TS_MinusTimes) {
                this.TS_MinusTimes = TS_MinusTimes;
            }

            public int getTS_Device() {
                return TS_Device;
            }

            public void setTS_Device(int TS_Device) {
                this.TS_Device = TS_Device;
            }

            public String getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(String CY_GID) {
                this.CY_GID = CY_GID;
            }

            public String getTS_Creator() {
                return TS_Creator;
            }

            public void setTS_Creator(String TS_Creator) {
                this.TS_Creator = TS_Creator;
            }

            public String getTS_UpdateTime() {
                return TS_UpdateTime;
            }

            public void setTS_UpdateTime(String TS_UpdateTime) {
                this.TS_UpdateTime = TS_UpdateTime;
            }

            public String getTS_Remark() {
                return TS_Remark;
            }

            public void setTS_Remark(String TS_Remark) {
                this.TS_Remark = TS_Remark;
            }
        }
    }
}
