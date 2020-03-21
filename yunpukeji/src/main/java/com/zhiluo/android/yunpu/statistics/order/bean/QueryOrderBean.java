package com.zhiluo.android.yunpu.statistics.order.bean;

public class QueryOrderBean {


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"DataCount":0,"Monetary":1553,"DiscountMoney":1461,"AllNumber":21,"FavorableMoney":0,"GrossMargin":0,"CostMoney":0}
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
         * DataCount : 0
         * Monetary : 1553.0
         * DiscountMoney : 1461.0
         * AllNumber : 21.0
         * FavorableMoney : 0.0
         * GrossMargin : 0.0
         * CostMoney : 0.0
         */

        private int DataCount;
        private double Monetary;
        private double DiscountMoney;
        private double AllNumber;
        private double FavorableMoney;
        private double GrossMargin;
        private double CostMoney;

        public int getDataCount() {
            return DataCount;
        }

        public void setDataCount(int DataCount) {
            this.DataCount = DataCount;
        }

        public double getMonetary() {
            return Monetary;
        }

        public void setMonetary(double Monetary) {
            this.Monetary = Monetary;
        }

        public double getDiscountMoney() {
            return DiscountMoney;
        }

        public void setDiscountMoney(double DiscountMoney) {
            this.DiscountMoney = DiscountMoney;
        }

        public double getAllNumber() {
            return AllNumber;
        }

        public void setAllNumber(double AllNumber) {
            this.AllNumber = AllNumber;
        }

        public double getFavorableMoney() {
            return FavorableMoney;
        }

        public void setFavorableMoney(double FavorableMoney) {
            this.FavorableMoney = FavorableMoney;
        }

        public double getGrossMargin() {
            return GrossMargin;
        }

        public void setGrossMargin(double GrossMargin) {
            this.GrossMargin = GrossMargin;
        }

        public double getCostMoney() {
            return CostMoney;
        }

        public void setCostMoney(double CostMoney) {
            this.CostMoney = CostMoney;
        }
    }
}
