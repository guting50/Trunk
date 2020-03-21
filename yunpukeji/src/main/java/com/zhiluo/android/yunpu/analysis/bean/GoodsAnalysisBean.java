package com.zhiluo.android.yunpu.analysis.bean;

import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/22 16:58.
 * 邮箱：137615198@qq.com
 */

public class GoodsAnalysisBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"SA_SalesNumber":"7.00","SA_SalesMoney":"2838.00","PSList":[{"PM_Name":"小米5","Number":"3","Money":2700},{"PM_Name":"衣服","Number":"1","Money":100}]}
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
         * SA_SalesNumber : 7.00
         * SA_SalesMoney : 2838.00
         * PSList : [{"PM_Name":"小米5","Number":"3","Money":2700},{"PM_Name":"衣服","Number":"1","Money":100}]
         */

        private String SA_SalesNumber;
        private String SA_SalesMoney;
        private List<PSListBean> PSList;

        public String getSA_SalesNumber() {
            return SA_SalesNumber;
        }

        public void setSA_SalesNumber(String SA_SalesNumber) {
            this.SA_SalesNumber = SA_SalesNumber;
        }

        public String getSA_SalesMoney() {
            return SA_SalesMoney;
        }

        public void setSA_SalesMoney(String SA_SalesMoney) {
            this.SA_SalesMoney = SA_SalesMoney;
        }

        public List<PSListBean> getPSList() {
            return PSList;
        }

        public void setPSList(List<PSListBean> PSList) {
            this.PSList = PSList;
        }

        public static class PSListBean {
            /**
             * PM_Name : 小米5
             * Number : 3
             * Money : 2700.0
             */

            private String PM_Name;
            private String Number;
            private double Money;

            public String getPM_Name() {
                return PM_Name;
            }

            public void setPM_Name(String PM_Name) {
                this.PM_Name = PM_Name;
            }

            public String getNumber() {
                return Number;
            }

            public void setNumber(String Number) {
                this.Number = Number;
            }

            public double getMoney() {
                return Money;
            }

            public void setMoney(double Money) {
                this.Money = Money;
            }
        }
    }
}
