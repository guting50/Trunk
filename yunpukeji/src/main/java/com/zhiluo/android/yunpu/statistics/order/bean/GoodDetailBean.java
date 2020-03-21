package com.zhiluo.android.yunpu.statistics.order.bean;

import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/13 10:31.
 * 邮箱：137615198@qq.com
 */

public class GoodDetailBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"PM_Code":"987667","PT_Name":"服装","PM_Name":"短裤","PM_SimpleCode":"DK","PM_UnitPrice":"100.00","PM_Modle":null,"PM_Brand":null,"PM_Number":"1","DiscountPrice":"100.00","SumPrice":"100.00","State":""},{"PM_Code":"B002","PT_Name":"小米","PM_Name":"魅族","PM_SimpleCode":null,"PM_UnitPrice":"100.00","PM_Modle":null,"PM_Brand":null,"PM_Number":"1","DiscountPrice":"100.00","SumPrice":"100.00","State":""},{"PM_Code":"98762","PT_Name":"服装","PM_Name":"衣服","PM_SimpleCode":"YF","PM_UnitPrice":"100.00","PM_Modle":null,"PM_Brand":null,"PM_Number":"1","DiscountPrice":"100.00","SumPrice":"100.00","State":""}]
     */

    private boolean success;
    private Object code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * PM_Code : 987667
         * PT_Name : 服装
         * PM_Name : 短裤
         * PM_SimpleCode : DK
         * PM_UnitPrice : 100.00
         * PM_Modle : null
         * PM_Brand : null
         * PM_Number : 1
         * DiscountPrice : 100.00
         * SumPrice : 100.00
         * State :
         */

        private String PM_Code;
        private String PT_Name;
        private String PM_Name;
        private String PM_SimpleCode;
        private String PM_UnitPrice;
        private Object PM_Modle;
        private Object PM_Brand;
        private double PM_Number;
        private double DiscountPrice;
        private String SumPrice;
        private String State;

        public String getPM_Code() {
            return PM_Code;
        }

        public void setPM_Code(String PM_Code) {
            this.PM_Code = PM_Code;
        }

        public String getPT_Name() {
            return PT_Name;
        }

        public void setPT_Name(String PT_Name) {
            this.PT_Name = PT_Name;
        }

        public String getPM_Name() {
            return PM_Name;
        }

        public void setPM_Name(String PM_Name) {
            this.PM_Name = PM_Name;
        }

        public String getPM_SimpleCode() {
            return PM_SimpleCode;
        }

        public void setPM_SimpleCode(String PM_SimpleCode) {
            this.PM_SimpleCode = PM_SimpleCode;
        }

        public String getPM_UnitPrice() {
            return PM_UnitPrice;
        }

        public void setPM_UnitPrice(String PM_UnitPrice) {
            this.PM_UnitPrice = PM_UnitPrice;
        }

        public Object getPM_Modle() {
            return PM_Modle;
        }

        public void setPM_Modle(Object PM_Modle) {
            this.PM_Modle = PM_Modle;
        }

        public Object getPM_Brand() {
            return PM_Brand;
        }

        public void setPM_Brand(Object PM_Brand) {
            this.PM_Brand = PM_Brand;
        }

        public double getPM_Number() {
            return PM_Number;
        }

        public void setPM_Number(double PM_Number) {
            this.PM_Number = PM_Number;
        }

        public double getDiscountPrice() {
            return DiscountPrice;
        }

        public void setDiscountPrice(double DiscountPrice) {
            this.DiscountPrice = DiscountPrice;
        }

        public String getSumPrice() {
            return SumPrice;
        }

        public void setSumPrice(String SumPrice) {
            this.SumPrice = SumPrice;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }
    }
}
