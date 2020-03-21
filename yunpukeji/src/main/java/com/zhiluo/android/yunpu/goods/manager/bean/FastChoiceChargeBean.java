package com.zhiluo.android.yunpu.goods.manager.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZPH on 2018-12-27.
 */

public class FastChoiceChargeBean implements Serializable{


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":20,"DataCount":1,"PageIndex":1,"DataList":[{"SGC_ClasName":"跑腿","PD_Discount":"100","VS_Number":"0.50","SM_Name":null,"GID":"41633c1f-216a-4c34-b211-fbcf3c3f38c9","SGC_GID":"2dbddf40-bb15-44b2-880e-cfa9b70eb6a3","SG_Code":"154233376206588","SG_Name":"2133","SG_Price":30,"SG_Picture":null,"SG_Abstract":null,"SG_Detail":null,"SG_State":null,"SG_IsSync":null,"SG_Remark":null,"SG_Creator":null,"SG_UpdateTime":"2018-11-16 10:02:58","CY_GID":null,"SG_Metering":""}],"StatisticsInfo":null}
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
         * DataList : [{"SGC_ClasName":"跑腿","PD_Discount":"100","VS_Number":"0.50","SM_Name":null,"GID":"41633c1f-216a-4c34-b211-fbcf3c3f38c9","SGC_GID":"2dbddf40-bb15-44b2-880e-cfa9b70eb6a3","SG_Code":"154233376206588","SG_Name":"2133","SG_Price":30,"SG_Picture":null,"SG_Abstract":null,"SG_Detail":null,"SG_State":null,"SG_IsSync":null,"SG_Remark":null,"SG_Creator":null,"SG_UpdateTime":"2018-11-16 10:02:58","CY_GID":null,"SG_Metering":""}]
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
             * SGC_ClasName : 跑腿
             * PD_Discount : 100
             * VS_Number : 0.50
             * SM_Name : null
             * GID : 41633c1f-216a-4c34-b211-fbcf3c3f38c9
             * SGC_GID : 2dbddf40-bb15-44b2-880e-cfa9b70eb6a3
             * SG_Code : 154233376206588
             * SG_Name : 2133
             * SG_Price : 30.0
             * SG_Picture : null
             * SG_Abstract : null
             * SG_Detail : null
             * SG_State : null
             * SG_IsSync : null
             * SG_Remark : null
             * SG_Creator : null
             * SG_UpdateTime : 2018-11-16 10:02:58
             * CY_GID : null
             * SG_Metering :
             */

            private String SGC_ClasName;
            private String PD_Discount;
            private String VS_Number;
            private Object SM_Name;
            private String GID;
            private String SGC_GID;
            private String SG_Code;
            private String SG_Name;
            private double SG_Price;
            private String SG_Picture;
            private Object SG_Abstract;
            private Object SG_Detail;
            private Object SG_State;
            private Object SG_IsSync;
            private Object SG_Remark;
            private Object SG_Creator;
            private String SG_UpdateTime;
            private Object CY_GID;
            private String SG_Metering;

            public String getSGC_ClasName() {
                return SGC_ClasName;
            }

            public void setSGC_ClasName(String SGC_ClasName) {
                this.SGC_ClasName = SGC_ClasName;
            }

            public String getPD_Discount() {
                return PD_Discount;
            }

            public void setPD_Discount(String PD_Discount) {
                this.PD_Discount = PD_Discount;
            }

            public String getVS_Number() {
                return VS_Number;
            }

            public void setVS_Number(String VS_Number) {
                this.VS_Number = VS_Number;
            }

            public Object getSM_Name() {
                return SM_Name;
            }

            public void setSM_Name(Object SM_Name) {
                this.SM_Name = SM_Name;
            }

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public String getSGC_GID() {
                return SGC_GID;
            }

            public void setSGC_GID(String SGC_GID) {
                this.SGC_GID = SGC_GID;
            }

            public String getSG_Code() {
                return SG_Code;
            }

            public void setSG_Code(String SG_Code) {
                this.SG_Code = SG_Code;
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

            public String getSG_Picture() {
                return SG_Picture;
            }

            public void setSG_Picture(String SG_Picture) {
                this.SG_Picture = SG_Picture;
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

            public Object getSG_State() {
                return SG_State;
            }

            public void setSG_State(Object SG_State) {
                this.SG_State = SG_State;
            }

            public Object getSG_IsSync() {
                return SG_IsSync;
            }

            public void setSG_IsSync(Object SG_IsSync) {
                this.SG_IsSync = SG_IsSync;
            }

            public Object getSG_Remark() {
                return SG_Remark;
            }

            public void setSG_Remark(Object SG_Remark) {
                this.SG_Remark = SG_Remark;
            }

            public Object getSG_Creator() {
                return SG_Creator;
            }

            public void setSG_Creator(Object SG_Creator) {
                this.SG_Creator = SG_Creator;
            }

            public String getSG_UpdateTime() {
                return SG_UpdateTime;
            }

            public void setSG_UpdateTime(String SG_UpdateTime) {
                this.SG_UpdateTime = SG_UpdateTime;
            }

            public Object getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(Object CY_GID) {
                this.CY_GID = CY_GID;
            }

            public String getSG_Metering() {
                return SG_Metering;
            }

            public void setSG_Metering(String SG_Metering) {
                this.SG_Metering = SG_Metering;
            }
        }
    }
}
