package com.zhiluo.android.yunpu.member.manager.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZPH on 2019-05-15.
 */

public class MemberFastCheckInBean implements Serializable{


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":0,"PageSize":20,"DataCount":1,"PageIndex":1,"DataList":[{"MCP_GID":"41f4fbe3-d705-4845-9476-286abfb6fce4","VM_GID":"b0cf1ae3-1b5b-4135-8f4a-f85cb591b37c","MCP_TotalTimes":1,"GID":"56b9ca81-9edf-452b-920d-392204ade723","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Card":"16598446456","VIP_Name":"3551866从v","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 14:16:19","MCI_Creator":"销售001","MCI_Remark":"","VIP_CellPhone":"16598446456","VIP_FaceNumber":"216566231","SM_Name":"jll2"}],"StatisticsInfo":null,"TrendData":null}
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
         * PageTotal : 0
         * PageSize : 20
         * DataCount : 1
         * PageIndex : 1
         * DataList : [{"MCP_GID":"41f4fbe3-d705-4845-9476-286abfb6fce4","VM_GID":"b0cf1ae3-1b5b-4135-8f4a-f85cb591b37c","MCP_TotalTimes":1,"GID":"56b9ca81-9edf-452b-920d-392204ade723","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Card":"16598446456","VIP_Name":"3551866从v","MCI_Integral":1,"MCP_Name":"早餐","MCI_UpdateTime":"2019-05-15 14:16:19","MCI_Creator":"销售001","MCI_Remark":"","VIP_CellPhone":"16598446456","VIP_FaceNumber":"216566231","SM_Name":"jll2"}]
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

        public static class DataListBean implements Serializable{
            /**
             * MCP_GID : 41f4fbe3-d705-4845-9476-286abfb6fce4
             * VM_GID : b0cf1ae3-1b5b-4135-8f4a-f85cb591b37c
             * MCP_TotalTimes : 1
             * GID : 56b9ca81-9edf-452b-920d-392204ade723
             * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
             * SM_GID : 4a7b1141-8ca3-4bef-961f-ffd153357823
             * VCH_Card : 16598446456
             * VIP_Name : 3551866从v
             * MCI_Integral : 1.0
             * MCP_Name : 早餐
             * MCI_UpdateTime : 2019-05-15 14:16:19
             * MCI_Creator : 销售001
             * MCI_Remark :
             * VIP_CellPhone : 16598446456
             * VIP_FaceNumber : 216566231
             * SM_Name : jll2
             */

            private String MCP_GID;
            private String VM_GID;
            private int MCP_TotalTimes;
            private String GID;
            private String CY_GID;
            private String SM_GID;
            private String VCH_Card;
            private String VIP_Name;
            private double MCI_Integral;
            private String MCP_Name;
            private String MCI_UpdateTime;
            private String MCI_Creator;
            private String MCI_Remark;
            private String VIP_CellPhone;
            private String VIP_FaceNumber;
            private String SM_Name;

            public String getMCP_GID() {
                return MCP_GID;
            }

            public void setMCP_GID(String MCP_GID) {
                this.MCP_GID = MCP_GID;
            }

            public String getVM_GID() {
                return VM_GID;
            }

            public void setVM_GID(String VM_GID) {
                this.VM_GID = VM_GID;
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

            public String getMCI_Remark() {
                return MCI_Remark;
            }

            public void setMCI_Remark(String MCI_Remark) {
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
