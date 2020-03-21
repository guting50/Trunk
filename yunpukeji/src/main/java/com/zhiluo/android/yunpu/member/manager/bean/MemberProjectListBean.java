package com.zhiluo.android.yunpu.member.manager.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZPH on 2019-05-15.
 */

public class MemberProjectListBean implements Serializable{


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":20,"DataCount":1,"PageIndex":1,"DataList":[{"MCP_Times":1,"MCP_SpacingTime":1,"MCP_TotalTimes":2,"GID":"41f4fbe3-d705-4845-9476-286abfb6fce4","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","MCP_No":"1","MCP_Name":"早餐","MCP_Integral":1,"MCP_CreateTime":"2018-11-19 17:23:21","MCP_State":1,"MCP_Creator":"销售001","MCP_Remark":"","MCP_CheckInType":0,"SM_Name":"jll2"}],"StatisticsInfo":null,"TrendData":null}
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
         * DataCount : 1
         * PageIndex : 1
         * DataList : [{"MCP_Times":1,"MCP_SpacingTime":1,"MCP_TotalTimes":2,"GID":"41f4fbe3-d705-4845-9476-286abfb6fce4","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","MCP_No":"1","MCP_Name":"早餐","MCP_Integral":1,"MCP_CreateTime":"2018-11-19 17:23:21","MCP_State":1,"MCP_Creator":"销售001","MCP_Remark":"","MCP_CheckInType":0,"SM_Name":"jll2"}]
         * StatisticsInfo : null
         * TrendData : null
         */

        private int PageTotal;
        private int PageSize;
        private int DataCount;
        private int PageIndex;
        private Object StatisticsInfo;
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

        public Object getStatisticsInfo() {
            return StatisticsInfo;
        }

        public void setStatisticsInfo(Object StatisticsInfo) {
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

        public static class DataListBean implements Serializable {
            /**
             * MCP_Times : 1
             * MCP_SpacingTime : 1
             * MCP_TotalTimes : 2
             * GID : 41f4fbe3-d705-4845-9476-286abfb6fce4
             * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
             * SM_GID : 4a7b1141-8ca3-4bef-961f-ffd153357823
             * MCP_No : 1
             * MCP_Name : 早餐
             * MCP_Integral : 1.0
             * MCP_CreateTime : 2018-11-19 17:23:21
             * MCP_State : 1
             * MCP_Creator : 销售001
             * MCP_Remark :
             * MCP_CheckInType : 0
             * SM_Name : jll2
             */

            private int MCP_Times;
            private int MCP_SpacingTime;
            private int MCP_TotalTimes;
            private String GID;
            private String CY_GID;
            private String SM_GID;
            private String MCP_No;
            private String MCP_Name;
            private double MCP_Integral;
            private String MCP_CreateTime;
            private int MCP_State;
            private String MCP_Creator;
            private String MCP_Remark;
            private int MCP_CheckInType;
            private String SM_Name;

            public int getMCP_Times() {
                return MCP_Times;
            }

            public void setMCP_Times(int MCP_Times) {
                this.MCP_Times = MCP_Times;
            }

            public int getMCP_SpacingTime() {
                return MCP_SpacingTime;
            }

            public void setMCP_SpacingTime(int MCP_SpacingTime) {
                this.MCP_SpacingTime = MCP_SpacingTime;
            }

            public int getMCP_TotalTimes() {
                return MCP_TotalTimes;
            }

            public void setMCP_TotalTimes(int MCP_TotalTimes) {
                this.MCP_TotalTimes = MCP_TotalTimes;
            }

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
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

            public String getMCP_No() {
                return MCP_No;
            }

            public void setMCP_No(String MCP_No) {
                this.MCP_No = MCP_No;
            }

            public String getMCP_Name() {
                return MCP_Name;
            }

            public void setMCP_Name(String MCP_Name) {
                this.MCP_Name = MCP_Name;
            }

            public double getMCP_Integral() {
                return MCP_Integral;
            }

            public void setMCP_Integral(double MCP_Integral) {
                this.MCP_Integral = MCP_Integral;
            }

            public String getMCP_CreateTime() {
                return MCP_CreateTime;
            }

            public void setMCP_CreateTime(String MCP_CreateTime) {
                this.MCP_CreateTime = MCP_CreateTime;
            }

            public int getMCP_State() {
                return MCP_State;
            }

            public void setMCP_State(int MCP_State) {
                this.MCP_State = MCP_State;
            }

            public String getMCP_Creator() {
                return MCP_Creator;
            }

            public void setMCP_Creator(String MCP_Creator) {
                this.MCP_Creator = MCP_Creator;
            }

            public String getMCP_Remark() {
                return MCP_Remark;
            }

            public void setMCP_Remark(String MCP_Remark) {
                this.MCP_Remark = MCP_Remark;
            }

            public int getMCP_CheckInType() {
                return MCP_CheckInType;
            }

            public void setMCP_CheckInType(int MCP_CheckInType) {
                this.MCP_CheckInType = MCP_CheckInType;
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
