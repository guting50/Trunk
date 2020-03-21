package com.zhiluo.android.yunpu.statistics.staff.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/8 16:36.
 * 邮箱：137615198@qq.com
 */

public class StaffRebateReportBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":20,"DataCount":17,"PageIndex":1,"DataList":[{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1708181618579265","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":12,"SC_Remark":"快速消费单号：KS1708181618579265","SC_UpdateTime":"2017-08-18 16:19:20","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1708181617112144","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":9.5,"SC_Remark":"快速消费单号：KS1708181617112144","SC_UpdateTime":"2017-08-18 16:17:51","TotalMoney":0},{"GID":"805b553d-6d9d-42df-86e5-d4955d62c6e5","CY_GID":null,"CO_GID":"KS1708031544368817","EM_GID":null,"EM_Name":"张明","SC_Type":40,"SC_TipMoney":12,"SC_Remark":"快速消费单号：KS1708031544368817","SC_UpdateTime":"2017-08-03 15:45:01","TotalMoney":0},{"GID":"9223bdb8-fa45-4cd1-9412-8759e7ccde18","CY_GID":null,"CO_GID":"KS1707131041296887","EM_GID":null,"EM_Name":"售后1号","SC_Type":40,"SC_TipMoney":19.1,"SC_Remark":"快速消费单号：KS1707131041296887","SC_UpdateTime":"2017-07-13 10:43:20","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1707121609389501","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":4.1,"SC_Remark":"快速消费单号：KS1707121609389501","SC_UpdateTime":"2017-07-12 16:09:57","TotalMoney":0},{"GID":"9223bdb8-fa45-4cd1-9412-8759e7ccde18","CY_GID":null,"CO_GID":"KS1707121335519677","EM_GID":null,"EM_Name":"售后1号","SC_Type":40,"SC_TipMoney":4.5,"SC_Remark":"快速消费单号：KS1707121335519677","SC_UpdateTime":"2017-07-12 13:36:23","TotalMoney":0},{"GID":"805b553d-6d9d-42df-86e5-d4955d62c6e5","CY_GID":null,"CO_GID":"KS1707121125586074","EM_GID":null,"EM_Name":"张明","SC_Type":40,"SC_TipMoney":2.2,"SC_Remark":"快速消费单号：KS1707121125586074","SC_UpdateTime":"2017-07-12 11:26:36","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1707121050309471","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":20,"SC_Remark":"快速消费单号：KS1707121050309471","SC_UpdateTime":"2017-07-12 10:51:08","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1707121049317785","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":9.5,"SC_Remark":"快速消费单号：KS1707121049317785","SC_UpdateTime":"2017-07-12 10:50:14","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"CZ1707111518118340","EM_GID":null,"EM_Name":"销售1号","SC_Type":20,"SC_TipMoney":50,"SC_Remark":"充值单号：CZ1707111518118340","SC_UpdateTime":"2017-07-11 15:22:21","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1707051544227945","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":6.75,"SC_Remark":"快速消费单号：KS1707051544227945","SC_UpdateTime":"2017-07-05 15:44:59","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1707051535522324","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":6.6,"SC_Remark":"快速消费单号：KS1707051535522324","SC_UpdateTime":"2017-07-05 15:36:56","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1706290935085863","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":12.25,"SC_Remark":"快速消费单号：KS1706290935085863","SC_UpdateTime":"2017-06-29 09:37:33","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1706241128201114","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":6.45,"SC_Remark":"快速消费单号：KS1706241128201114","SC_UpdateTime":"2017-06-24 11:30:02","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1706221539567097","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":0.05,"SC_Remark":"快速消费单号：KS1706221539567097","SC_UpdateTime":"2017-06-22 15:40:23","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"CZ1706211051152602","EM_GID":null,"EM_Name":"销售1号","SC_Type":20,"SC_TipMoney":49.9,"SC_Remark":"充值单号：CZ1706211051152602","SC_UpdateTime":"2017-06-21 10:51:25","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"SP1706151038518948","EM_GID":null,"EM_Name":"销售1号","SC_Type":50,"SC_TipMoney":6,"SC_Remark":"商品消费单号：SP1706151038518948。商品名称：沙滩裤","SC_UpdateTime":"2017-06-15 10:39:12","TotalMoney":0}],"StatisticsInfo":{"Monetary":230.9,"DiscountMoney":null,"AllNumber":null}}
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
         * PageSize : 20
         * DataCount : 17
         * PageIndex : 1
         * DataList : [{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1708181618579265","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":12,"SC_Remark":"快速消费单号：KS1708181618579265","SC_UpdateTime":"2017-08-18 16:19:20","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1708181617112144","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":9.5,"SC_Remark":"快速消费单号：KS1708181617112144","SC_UpdateTime":"2017-08-18 16:17:51","TotalMoney":0},{"GID":"805b553d-6d9d-42df-86e5-d4955d62c6e5","CY_GID":null,"CO_GID":"KS1708031544368817","EM_GID":null,"EM_Name":"张明","SC_Type":40,"SC_TipMoney":12,"SC_Remark":"快速消费单号：KS1708031544368817","SC_UpdateTime":"2017-08-03 15:45:01","TotalMoney":0},{"GID":"9223bdb8-fa45-4cd1-9412-8759e7ccde18","CY_GID":null,"CO_GID":"KS1707131041296887","EM_GID":null,"EM_Name":"售后1号","SC_Type":40,"SC_TipMoney":19.1,"SC_Remark":"快速消费单号：KS1707131041296887","SC_UpdateTime":"2017-07-13 10:43:20","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1707121609389501","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":4.1,"SC_Remark":"快速消费单号：KS1707121609389501","SC_UpdateTime":"2017-07-12 16:09:57","TotalMoney":0},{"GID":"9223bdb8-fa45-4cd1-9412-8759e7ccde18","CY_GID":null,"CO_GID":"KS1707121335519677","EM_GID":null,"EM_Name":"售后1号","SC_Type":40,"SC_TipMoney":4.5,"SC_Remark":"快速消费单号：KS1707121335519677","SC_UpdateTime":"2017-07-12 13:36:23","TotalMoney":0},{"GID":"805b553d-6d9d-42df-86e5-d4955d62c6e5","CY_GID":null,"CO_GID":"KS1707121125586074","EM_GID":null,"EM_Name":"张明","SC_Type":40,"SC_TipMoney":2.2,"SC_Remark":"快速消费单号：KS1707121125586074","SC_UpdateTime":"2017-07-12 11:26:36","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1707121050309471","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":20,"SC_Remark":"快速消费单号：KS1707121050309471","SC_UpdateTime":"2017-07-12 10:51:08","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1707121049317785","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":9.5,"SC_Remark":"快速消费单号：KS1707121049317785","SC_UpdateTime":"2017-07-12 10:50:14","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"CZ1707111518118340","EM_GID":null,"EM_Name":"销售1号","SC_Type":20,"SC_TipMoney":50,"SC_Remark":"充值单号：CZ1707111518118340","SC_UpdateTime":"2017-07-11 15:22:21","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1707051544227945","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":6.75,"SC_Remark":"快速消费单号：KS1707051544227945","SC_UpdateTime":"2017-07-05 15:44:59","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1707051535522324","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":6.6,"SC_Remark":"快速消费单号：KS1707051535522324","SC_UpdateTime":"2017-07-05 15:36:56","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1706290935085863","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":12.25,"SC_Remark":"快速消费单号：KS1706290935085863","SC_UpdateTime":"2017-06-29 09:37:33","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1706241128201114","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":6.45,"SC_Remark":"快速消费单号：KS1706241128201114","SC_UpdateTime":"2017-06-24 11:30:02","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"KS1706221539567097","EM_GID":null,"EM_Name":"销售1号","SC_Type":40,"SC_TipMoney":0.05,"SC_Remark":"快速消费单号：KS1706221539567097","SC_UpdateTime":"2017-06-22 15:40:23","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"CZ1706211051152602","EM_GID":null,"EM_Name":"销售1号","SC_Type":20,"SC_TipMoney":49.9,"SC_Remark":"充值单号：CZ1706211051152602","SC_UpdateTime":"2017-06-21 10:51:25","TotalMoney":0},{"GID":"e50406f9-f5d0-44a0-af6f-b399c4bbae20","CY_GID":null,"CO_GID":"SP1706151038518948","EM_GID":null,"EM_Name":"销售1号","SC_Type":50,"SC_TipMoney":6,"SC_Remark":"商品消费单号：SP1706151038518948。商品名称：沙滩裤","SC_UpdateTime":"2017-06-15 10:39:12","TotalMoney":0}]
         * StatisticsInfo : {"Monetary":230.9,"DiscountMoney":null,"AllNumber":null}
         */

        private int PageTotal;
        private int PageSize;
        private int DataCount;
        private int PageIndex;
        private StatisticsInfoBean StatisticsInfo;
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

        public List<DataListBean> getDataList() {
            return DataList;
        }

        public void setDataList(List<DataListBean> DataList) {
            this.DataList = DataList;
        }

        public static class StatisticsInfoBean {
            /**
             * Monetary : 230.9
             * DiscountMoney : null
             * AllNumber : null
             */

            private double Monetary;
            private Object DiscountMoney;
            private Object AllNumber;

            public double getMonetary() {
                return Monetary;
            }

            public void setMonetary(double Monetary) {
                this.Monetary = Monetary;
            }

            public Object getDiscountMoney() {
                return DiscountMoney;
            }

            public void setDiscountMoney(Object DiscountMoney) {
                this.DiscountMoney = DiscountMoney;
            }

            public Object getAllNumber() {
                return AllNumber;
            }

            public void setAllNumber(Object AllNumber) {
                this.AllNumber = AllNumber;
            }
        }

        public static class DataListBean implements Serializable{
            /**
             * GID : e50406f9-f5d0-44a0-af6f-b399c4bbae20
             * CY_GID : null
             * CO_GID : KS1708181618579265
             * EM_GID : null
             * EM_Name : 销售1号
             * SC_Type : 40
             * SC_TipMoney : 12.0
             * SC_Remark : 快速消费单号：KS1708181618579265
             * SC_UpdateTime : 2017-08-18 16:19:20
             * TotalMoney : 0.0
             */

            private String GID;
            private Object CY_GID;
            private String CO_GID;
            private Object EM_GID;
            private String EM_Name;
            private int SC_Type;
            private double SC_TipMoney;
            private String SC_Remark;
            private String SC_UpdateTime;
            private double TotalMoney;

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public Object getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(Object CY_GID) {
                this.CY_GID = CY_GID;
            }

            public String getCO_GID() {
                return CO_GID;
            }

            public void setCO_GID(String CO_GID) {
                this.CO_GID = CO_GID;
            }

            public Object getEM_GID() {
                return EM_GID;
            }

            public void setEM_GID(Object EM_GID) {
                this.EM_GID = EM_GID;
            }

            public String getEM_Name() {
                return EM_Name;
            }

            public void setEM_Name(String EM_Name) {
                this.EM_Name = EM_Name;
            }

            public int getSC_Type() {
                return SC_Type;
            }

            public void setSC_Type(int SC_Type) {
                this.SC_Type = SC_Type;
            }

            public double getSC_TipMoney() {
                return SC_TipMoney;
            }

            public void setSC_TipMoney(double SC_TipMoney) {
                this.SC_TipMoney = SC_TipMoney;
            }

            public String getSC_Remark() {
                return SC_Remark;
            }

            public void setSC_Remark(String SC_Remark) {
                this.SC_Remark = SC_Remark;
            }

            public String getSC_UpdateTime() {
                return SC_UpdateTime;
            }

            public void setSC_UpdateTime(String SC_UpdateTime) {
                this.SC_UpdateTime = SC_UpdateTime;
            }

            public double getTotalMoney() {
                return TotalMoney;
            }

            public void setTotalMoney(double TotalMoney) {
                this.TotalMoney = TotalMoney;
            }
        }
    }
}
