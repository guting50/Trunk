package com.zhiluo.android.yunpu.statistics.account.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZPH on 2019-05-16.
 */

public class MemberSignReportBean implements Serializable{


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":20,"DataCount":18,"PageIndex":1,"DataList":[{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"13669879846","VIP_Name":"555uu","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 17:58:38","MCI_Creator":"分号1","MCI_Remark":null,"VIP_CellPhone":"13669879846","VIP_FaceNumber":"","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"1335965646512","VIP_Name":"zhangsan","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 17:58:23","MCI_Creator":"分号1","MCI_Remark":null,"VIP_CellPhone":"13359656465","VIP_FaceNumber":"","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"13235645464","VIP_Name":"sds","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 17:46:22","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"13235645464","VIP_FaceNumber":"","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"266","VIP_Name":"32分","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 17:46:13","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"18328578324","VIP_FaceNumber":"0069945","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"13269894654","VIP_Name":"默认积分","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 16:53:27","MCI_Creator":"分号1","MCI_Remark":null,"VIP_CellPhone":"13269894654","VIP_FaceNumber":"123456784242","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"18526555555","VIP_Name":"就来了哈哈哈","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 16:13:25","MCI_Creator":"分号1","MCI_Remark":null,"VIP_CellPhone":"18526555555","VIP_FaceNumber":"就来了","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"13664656454","VIP_Name":"65和GV就","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 16:11:48","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"13664656454","VIP_FaceNumber":"454564646","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"13566546464","VIP_Name":"6是多少","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 16:10:07","MCI_Creator":"分号1","MCI_Remark":null,"VIP_CellPhone":"13566546464","VIP_FaceNumber":"616565","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"13586766466","VIP_Name":"咯momo","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 16:09:57","MCI_Creator":"分号1","MCI_Remark":null,"VIP_CellPhone":"13586766466","VIP_FaceNumber":"5943751645","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"13565465464","VIP_Name":"asd66516","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 14:50:53","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"13565465464","VIP_FaceNumber":"5362556","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"16464694949","VIP_Name":"各种。","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 14:47:43","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"16464694949","VIP_FaceNumber":"16466","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"13855555555","VIP_Name":"扣卡","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 14:23:28","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"13855555555","VIP_FaceNumber":"冷哭了","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":2,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"18338258258","VIP_Name":"来了","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 14:17:30","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"18338258258","VIP_FaceNumber":"他特踏踏","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"16598446456","VIP_Name":"3551866从v","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 14:16:20","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"16598446456","VIP_FaceNumber":"216566231","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"314654656132","VIP_Name":"达到","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 14:14:48","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"","VIP_FaceNumber":"351944656163","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":3,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"5497498651651","VIP_Name":"ASDASAS","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 14:14:10","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"","VIP_FaceNumber":"66984131312133","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":2,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"5497498651651","VIP_Name":"ASDASAS","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-13 10:13:17","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"","VIP_FaceNumber":"66984131312133","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"5497498651651","VIP_Name":"ASDASAS","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-09 15:09:11","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"","VIP_FaceNumber":"66984131312133","SM_Name":"jll2"}],"StatisticsInfo":{"Peoples":18,"Integral":18},"TrendData":null}
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
         * DataCount : 18
         * PageIndex : 1
         * DataList : [{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"13669879846","VIP_Name":"555uu","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 17:58:38","MCI_Creator":"分号1","MCI_Remark":null,"VIP_CellPhone":"13669879846","VIP_FaceNumber":"","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"1335965646512","VIP_Name":"zhangsan","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 17:58:23","MCI_Creator":"分号1","MCI_Remark":null,"VIP_CellPhone":"13359656465","VIP_FaceNumber":"","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"13235645464","VIP_Name":"sds","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 17:46:22","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"13235645464","VIP_FaceNumber":"","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"266","VIP_Name":"32分","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 17:46:13","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"18328578324","VIP_FaceNumber":"0069945","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"13269894654","VIP_Name":"默认积分","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 16:53:27","MCI_Creator":"分号1","MCI_Remark":null,"VIP_CellPhone":"13269894654","VIP_FaceNumber":"123456784242","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"18526555555","VIP_Name":"就来了哈哈哈","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 16:13:25","MCI_Creator":"分号1","MCI_Remark":null,"VIP_CellPhone":"18526555555","VIP_FaceNumber":"就来了","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"13664656454","VIP_Name":"65和GV就","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 16:11:48","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"13664656454","VIP_FaceNumber":"454564646","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"13566546464","VIP_Name":"6是多少","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 16:10:07","MCI_Creator":"分号1","MCI_Remark":null,"VIP_CellPhone":"13566546464","VIP_FaceNumber":"616565","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"13586766466","VIP_Name":"咯momo","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 16:09:57","MCI_Creator":"分号1","MCI_Remark":null,"VIP_CellPhone":"13586766466","VIP_FaceNumber":"5943751645","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"13565465464","VIP_Name":"asd66516","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 14:50:53","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"13565465464","VIP_FaceNumber":"5362556","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"16464694949","VIP_Name":"各种。","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 14:47:43","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"16464694949","VIP_FaceNumber":"16466","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"13855555555","VIP_Name":"扣卡","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 14:23:28","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"13855555555","VIP_FaceNumber":"冷哭了","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":2,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"18338258258","VIP_Name":"来了","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 14:17:30","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"18338258258","VIP_FaceNumber":"他特踏踏","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"16598446456","VIP_Name":"3551866从v","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 14:16:20","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"16598446456","VIP_FaceNumber":"216566231","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"314654656132","VIP_Name":"达到","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 14:14:48","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"","VIP_FaceNumber":"351944656163","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":3,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"5497498651651","VIP_Name":"ASDASAS","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 14:14:10","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"","VIP_FaceNumber":"66984131312133","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":2,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"5497498651651","VIP_Name":"ASDASAS","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-13 10:13:17","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"","VIP_FaceNumber":"66984131312133","SM_Name":"jll2"},{"MCP_GID":null,"VM_GID":null,"MCP_TotalTimes":1,"GID":null,"CY_GID":null,"SM_GID":null,"VCH_Card":"5497498651651","VIP_Name":"ASDASAS","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-09 15:09:11","MCI_Creator":"销售001","MCI_Remark":null,"VIP_CellPhone":"","VIP_FaceNumber":"66984131312133","SM_Name":"jll2"}]
         * StatisticsInfo : {"Peoples":18,"Integral":18}
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
             * Peoples : 18
             * Integral : 18.0
             */

            private int Peoples;
            private double Integral;

            public int getPeoples() {
                return Peoples;
            }

            public void setPeoples(int Peoples) {
                this.Peoples = Peoples;
            }

            public double getIntegral() {
                return Integral;
            }

            public void setIntegral(double Integral) {
                this.Integral = Integral;
            }
        }

        public static class DataListBean implements Serializable{
            /**
             * MCP_GID : null
             * VM_GID : null
             * MCP_TotalTimes : 1
             * GID : null
             * CY_GID : null
             * SM_GID : null
             * VCH_Card : 13669879846
             * VIP_Name : 555uu
             * MCI_Integral : 1.0
             * MCP_Name : 早餐
             * MCI_UpdateTime : 2019-05-15 17:58:38
             * MCI_Creator : 分号1
             * MCI_Remark : null
             * VIP_CellPhone : 13669879846
             * VIP_FaceNumber :
             * SM_Name : jll2
             */

            private Object MCP_GID;
            private Object VM_GID;
            private int MCP_TotalTimes;
            private Object GID;
            private Object CY_GID;
            private Object SM_GID;
            private String VCH_Card;
            private String VIP_Name;
            private double MCI_Integral;
            private String MCP_Name;
            private String MCI_UpdateTime;
            private String MCI_Creator;
            private Object MCI_Remark;
            private String VIP_CellPhone;
            private String VIP_FaceNumber;
            private String SM_Name;

            public Object getMCP_GID() {
                return MCP_GID;
            }

            public void setMCP_GID(Object MCP_GID) {
                this.MCP_GID = MCP_GID;
            }

            public Object getVM_GID() {
                return VM_GID;
            }

            public void setVM_GID(Object VM_GID) {
                this.VM_GID = VM_GID;
            }

            public int getMCP_TotalTimes() {
                return MCP_TotalTimes;
            }

            public void setMCP_TotalTimes(int MCP_TotalTimes) {
                this.MCP_TotalTimes = MCP_TotalTimes;
            }

            public Object getGID() {
                return GID;
            }

            public void setGID(Object GID) {
                this.GID = GID;
            }

            public Object getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(Object CY_GID) {
                this.CY_GID = CY_GID;
            }

            public Object getSM_GID() {
                return SM_GID;
            }

            public void setSM_GID(Object SM_GID) {
                this.SM_GID = SM_GID;
            }

            public String getVCH_Card() {
                return VCH_Card;
            }

            public void setVCH_Card(String VCH_Card) {
                this.VCH_Card = VCH_Card;
            }

            public String getVIP_Name() {
                return VIP_Name;
            }

            public void setVIP_Name(String VIP_Name) {
                this.VIP_Name = VIP_Name;
            }

            public double getMCI_Integral() {
                return MCI_Integral;
            }

            public void setMCI_Integral(double MCI_Integral) {
                this.MCI_Integral = MCI_Integral;
            }

            public String getMCP_Name() {
                return MCP_Name;
            }

            public void setMCP_Name(String MCP_Name) {
                this.MCP_Name = MCP_Name;
            }

            public String getMCI_UpdateTime() {
                return MCI_UpdateTime;
            }

            public void setMCI_UpdateTime(String MCI_UpdateTime) {
                this.MCI_UpdateTime = MCI_UpdateTime;
            }

            public String getMCI_Creator() {
                return MCI_Creator;
            }

            public void setMCI_Creator(String MCI_Creator) {
                this.MCI_Creator = MCI_Creator;
            }

            public Object getMCI_Remark() {
                return MCI_Remark;
            }

            public void setMCI_Remark(Object MCI_Remark) {
                this.MCI_Remark = MCI_Remark;
            }

            public String getVIP_CellPhone() {
                return VIP_CellPhone;
            }

            public void setVIP_CellPhone(String VIP_CellPhone) {
                this.VIP_CellPhone = VIP_CellPhone;
            }

            public String getVIP_FaceNumber() {
                return VIP_FaceNumber;
            }

            public void setVIP_FaceNumber(String VIP_FaceNumber) {
                this.VIP_FaceNumber = VIP_FaceNumber;
            }

            public String getSM_Name() {
                return SM_Name;
            }

            public void setSM_Name(String SM_Name) {
                this.SM_Name = SM_Name;
            }
        }
    }
}
