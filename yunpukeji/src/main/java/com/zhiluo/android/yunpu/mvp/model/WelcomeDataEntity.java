package com.zhiluo.android.yunpu.mvp.model;

/**
 * 作者：罗咏哲 on 2017/10/24 11:28.
 * 邮箱：137615198@qq.com
 */

public class WelcomeDataEntity {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"totalprice":null,"orderdate":null,"month":null,"SA_AllMembersCount":"70.00","SA_ToDaySales":"0.00","SA_ThisMonthSales":"2337.50"}
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
         * totalprice : null
         * orderdate : null
         * month : null
         * SA_AllMembersCount : 70.00
         * SA_ToDaySales : 0.00
         * SA_ThisMonthSales : 2337.50
         */

        private Object totalprice;
        private Object orderdate;
        private Object month;
        private String SA_AllMembersCount;
        private String SA_ToDaySales;
        private String SA_ThisMonthSales;

        public Object getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(Object totalprice) {
            this.totalprice = totalprice;
        }

        public Object getOrderdate() {
            return orderdate;
        }

        public void setOrderdate(Object orderdate) {
            this.orderdate = orderdate;
        }

        public Object getMonth() {
            return month;
        }

        public void setMonth(Object month) {
            this.month = month;
        }

        public String getSA_AllMembersCount() {
            return SA_AllMembersCount;
        }

        public void setSA_AllMembersCount(String SA_AllMembersCount) {
            this.SA_AllMembersCount = SA_AllMembersCount;
        }

        public String getSA_ToDaySales() {
            return SA_ToDaySales;
        }

        public void setSA_ToDaySales(String SA_ToDaySales) {
            this.SA_ToDaySales = SA_ToDaySales;
        }

        public String getSA_ThisMonthSales() {
            return SA_ThisMonthSales;
        }

        public void setSA_ThisMonthSales(String SA_ThisMonthSales) {
            this.SA_ThisMonthSales = SA_ThisMonthSales;
        }
    }
}
