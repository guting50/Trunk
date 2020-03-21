package com.zhiluo.android.yunpu.statistics.order.bean;

import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/13 13:40.
 * 邮箱：137615198@qq.com
 */

public class TimesGoodDetailBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":9999,"DataCount":1,"PageIndex":1,"DataList":[{"EM_GIDList":null,"GID":"d5b5bd6e-4cf8-42b1-a0d7-347028c9201c","WO_GID":"20bf4028-8102-47ae-bef4-0ea41b334dac","WOD_UseNumber":2,"WOD_ResidueDegree":4,"SG_GID":"6653516f-4b82-4826-9be4-f62f1394171c","SG_Code":"123456","SGC_Name":"洗澡","SG_Name":"洗澡","SG_Price":20,"SG_Abstract":null,"SG_Detail":null,"CY_GID":null,"WOD_Crator":"1540004275@qq.com","WOD_UpdateTime":"2017-09-11 17:18:12"}],"StatisticsInfo":null}
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
         * PageSize : 9999
         * DataCount : 1
         * PageIndex : 1
         * DataList : [{"EM_GIDList":null,"GID":"d5b5bd6e-4cf8-42b1-a0d7-347028c9201c","WO_GID":"20bf4028-8102-47ae-bef4-0ea41b334dac","WOD_UseNumber":2,"WOD_ResidueDegree":4,"SG_GID":"6653516f-4b82-4826-9be4-f62f1394171c","SG_Code":"123456","SGC_Name":"洗澡","SG_Name":"洗澡","SG_Price":20,"SG_Abstract":null,"SG_Detail":null,"CY_GID":null,"WOD_Crator":"1540004275@qq.com","WOD_UpdateTime":"2017-09-11 17:18:12"}]
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
             * EM_GIDList : null
             * GID : d5b5bd6e-4cf8-42b1-a0d7-347028c9201c
             * WO_GID : 20bf4028-8102-47ae-bef4-0ea41b334dac
             * WOD_UseNumber : 2
             * WOD_ResidueDegree : 4
             * SG_GID : 6653516f-4b82-4826-9be4-f62f1394171c
             * SG_Code : 123456
             * SGC_Name : 洗澡
             * SG_Name : 洗澡
             * SG_Price : 20.0
             * SG_Abstract : null
             * SG_Detail : null
             * CY_GID : null
             * WOD_Crator : 1540004275@qq.com
             * WOD_UpdateTime : 2017-09-11 17:18:12
             */

            private Object EM_GIDList;
            private String GID;
            private String WO_GID;
            private int WOD_UseNumber;
            private int WOD_ResidueDegree;
            private String SG_GID;
            private String SG_Code;
            private String SGC_Name;
            private String SG_Name;
            private double SG_Price;
            private Object SG_Abstract;
            private Object SG_Detail;
            private Object CY_GID;
            private String WOD_Crator;
            private String WOD_UpdateTime;

            public Object getEM_GIDList() {
                return EM_GIDList;
            }

            public void setEM_GIDList(Object EM_GIDList) {
                this.EM_GIDList = EM_GIDList;
            }

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public String getWO_GID() {
                return WO_GID;
            }

            public void setWO_GID(String WO_GID) {
                this.WO_GID = WO_GID;
            }

            public int getWOD_UseNumber() {
                return WOD_UseNumber;
            }

            public void setWOD_UseNumber(int WOD_UseNumber) {
                this.WOD_UseNumber = WOD_UseNumber;
            }

            public int getWOD_ResidueDegree() {
                return WOD_ResidueDegree;
            }

            public void setWOD_ResidueDegree(int WOD_ResidueDegree) {
                this.WOD_ResidueDegree = WOD_ResidueDegree;
            }

            public String getSG_GID() {
                return SG_GID;
            }

            public void setSG_GID(String SG_GID) {
                this.SG_GID = SG_GID;
            }

            public String getSG_Code() {
                return SG_Code;
            }

            public void setSG_Code(String SG_Code) {
                this.SG_Code = SG_Code;
            }

            public String getSGC_Name() {
                return SGC_Name;
            }

            public void setSGC_Name(String SGC_Name) {
                this.SGC_Name = SGC_Name;
            }

            public String getSG_Name() {
                return SG_Name;
            }

            public void setSG_Name(String SG_Name) {
                this.SG_Name = SG_Name;
            }

            public double getSG_Price() {
                return SG_Price;
            }

            public void setSG_Price(double SG_Price) {
                this.SG_Price = SG_Price;
            }

            public Object getSG_Abstract() {
                return SG_Abstract;
            }

            public void setSG_Abstract(Object SG_Abstract) {
                this.SG_Abstract = SG_Abstract;
            }

            public Object getSG_Detail() {
                return SG_Detail;
            }

            public void setSG_Detail(Object SG_Detail) {
                this.SG_Detail = SG_Detail;
            }

            public Object getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(Object CY_GID) {
                this.CY_GID = CY_GID;
            }

            public String getWOD_Crator() {
                return WOD_Crator;
            }

            public void setWOD_Crator(String WOD_Crator) {
                this.WOD_Crator = WOD_Crator;
            }

            public String getWOD_UpdateTime() {
                return WOD_UpdateTime;
            }

            public void setWOD_UpdateTime(String WOD_UpdateTime) {
                this.WOD_UpdateTime = WOD_UpdateTime;
            }
        }
    }
}
