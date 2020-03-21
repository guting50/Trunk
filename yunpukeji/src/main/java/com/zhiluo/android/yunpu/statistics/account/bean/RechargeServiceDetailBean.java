package com.zhiluo.android.yunpu.statistics.account.bean;

import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/13 15:15.
 * 邮箱：137615198@qq.com
 */

public class RechargeServiceDetailBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"PM_Code":"S0121","PT_Name":"修","PM_Name":"维修测试","PM_SimpleCode":"S","PM_UnitPrice":"20.00","PM_Modle":"gg","PM_Brand":"","PM_Number":"2","DiscountPrice":"40.00","SumPrice":"40.00","State":""},{"PM_Code":"5689","PT_Name":"英语口语辅导","PM_Name":"服务服务","PM_SimpleCode":"","PM_UnitPrice":"23.00","PM_Modle":"","PM_Brand":"","PM_Number":"2","DiscountPrice":"46.00","SumPrice":"46.00","State":""}]
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
         * PM_Code : S0121
         * PT_Name : 修
         * PM_Name : 维修测试
         * PM_SimpleCode : S
         * PM_UnitPrice : 20.00
         * PM_Modle : gg
         * PM_Brand :
         * PM_Number : 2
         * DiscountPrice : 40.00
         * SumPrice : 40.00
         * State :
         */

        private String PM_Code;
        private String PT_Name;
        private String PM_Name;
        private String PM_SimpleCode;
        private String PM_UnitPrice;
        private String PM_Modle;
        private String PM_Brand;
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

        public String getPM_Modle() {
            return PM_Modle;
        }

        public void setPM_Modle(String PM_Modle) {
            this.PM_Modle = PM_Modle;
        }

        public String getPM_Brand() {
            return PM_Brand;
        }

        public void setPM_Brand(String PM_Brand) {
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
