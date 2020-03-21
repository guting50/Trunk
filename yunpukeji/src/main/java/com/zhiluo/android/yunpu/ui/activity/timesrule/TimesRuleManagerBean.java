package com.zhiluo.android.yunpu.ui.activity.timesrule;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZPH on 2018-11-30.
 */

public class TimesRuleManagerBean implements Serializable{


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":20,"DataCount":2,"PageIndex":1,"DataList":[{"GID":"71ada9b6-cb22-4a0d-bc0d-89c8d0a38d2a","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","WR_Name":"每天消费3次","WR_ConsumeRule":"3","WR_RegularUnit":2,"WR_Creator":"销售001","WR_CreateTime":"2018-11-23 13:57:38"},{"GID":"04e9ede7-70ea-4ebc-a7fe-e01cce5d974c","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","WR_Name":"是","WR_ConsumeRule":"1","WR_RegularUnit":1,"WR_Creator":"销售001","WR_CreateTime":"2018-11-19 17:07:26"}],"StatisticsInfo":null}
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
         * DataCount : 2
         * PageIndex : 1
         * DataList : [{"GID":"71ada9b6-cb22-4a0d-bc0d-89c8d0a38d2a","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","WR_Name":"每天消费3次","WR_ConsumeRule":"3","WR_RegularUnit":2,"WR_Creator":"销售001","WR_CreateTime":"2018-11-23 13:57:38"},{"GID":"04e9ede7-70ea-4ebc-a7fe-e01cce5d974c","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","WR_Name":"是","WR_ConsumeRule":"1","WR_RegularUnit":1,"WR_Creator":"销售001","WR_CreateTime":"2018-11-19 17:07:26"}]
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
             * GID : 71ada9b6-cb22-4a0d-bc0d-89c8d0a38d2a
             * SM_GID : 4a7b1141-8ca3-4bef-961f-ffd153357823
             * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
             * WR_Name : 每天消费3次
             * WR_ConsumeRule : 3
             * WR_RegularUnit : 2
             * WR_Creator : 销售001
             * WR_CreateTime : 2018-11-23 13:57:38
             */

            private String GID;
            private String SM_GID;
            private String CY_GID;
            private String WR_Name;
            private String WR_ConsumeRule;
            private int WR_RegularUnit;
            private String WR_Creator;
            private String WR_CreateTime;

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public String getSM_GID() {
                return SM_GID;
            }

            public void setSM_GID(String SM_GID) {
                this.SM_GID = SM_GID;
            }

            public String getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(String CY_GID) {
                this.CY_GID = CY_GID;
            }

            public String getWR_Name() {
                return WR_Name;
            }

            public void setWR_Name(String WR_Name) {
                this.WR_Name = WR_Name;
            }

            public String getWR_ConsumeRule() {
                return WR_ConsumeRule;
            }

            public void setWR_ConsumeRule(String WR_ConsumeRule) {
                this.WR_ConsumeRule = WR_ConsumeRule;
            }

            public int getWR_RegularUnit() {
                return WR_RegularUnit;
            }

            public void setWR_RegularUnit(int WR_RegularUnit) {
                this.WR_RegularUnit = WR_RegularUnit;
            }

            public String getWR_Creator() {
                return WR_Creator;
            }

            public void setWR_Creator(String WR_Creator) {
                this.WR_Creator = WR_Creator;
            }

            public String getWR_CreateTime() {
                return WR_CreateTime;
            }

            public void setWR_CreateTime(String WR_CreateTime) {
                this.WR_CreateTime = WR_CreateTime;
            }
        }
    }
}
