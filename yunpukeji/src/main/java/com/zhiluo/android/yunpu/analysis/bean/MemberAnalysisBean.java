package com.zhiluo.android.yunpu.analysis.bean;

import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/22 14:48.
 * 邮箱：137615198@qq.com
 */

public class MemberAnalysisBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"SA_SKSalesNumber":"1.00","SA_SKSalesMoney":1000,"SA_MBSalesNumber":"1.00","SA_MBSalesMoney":"1770.00","MberList":[{"VIP_Name":"散客","Money":"78466.01","Number":"142","VIP_ID":"6e8aff9c-f83a-4f0f-82da-2706b7b82b1b"},{"VIP_Name":"余","Money":"23349.30","Number":"7","VIP_ID":"fa49cb1b-7b46-49c6-a015-80fa77e5172e"},{"VIP_Name":"赵三","Money":"13132.00","Number":"49","VIP_ID":"0be2551e-53a7-45f2-aa03-a2373edf65b0"},{"VIP_Name":"Adele","Money":"12551.60","Number":"14","VIP_ID":"dd411089-755c-409c-9b27-a6e5b7f65c4d"},{"VIP_Name":"哈哈哈","Money":"7852.40","Number":"20","VIP_ID":"3fa6a15a-698e-4f10-b6ad-0c062ac1f875"},{"VIP_Name":"酷狗","Money":"3929.11","Number":"13","VIP_ID":"d223b91c-54e8-4127-b4c5-2f645102a6f5"},{"VIP_Name":"悟空","Money":"3427.00","Number":"5","VIP_ID":"568f3856-d558-457b-a012-ea07cf46cda8"},{"VIP_Name":"阿发","Money":"3111.00","Number":"4","VIP_ID":"9395b618-dde1-4307-b9da-7ec5097f0100"},{"VIP_Name":"李斯","Money":"3019.00","Number":"11","VIP_ID":"6362cbfc-d264-4a6e-850b-b764548ad197"},{"VIP_Name":"于蓝","Money":"2869.00","Number":"20","VIP_ID":"5f39714d-774b-4364-ba30-381c760c6fad"}]}
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
         * SA_SKSalesNumber : 1.00
         * SA_SKSalesMoney : 1000.0
         * SA_MBSalesNumber : 1.00
         * SA_MBSalesMoney : 1770.00
         * MberList : [{"VIP_Name":"散客","Money":"78466.01","Number":"142","VIP_ID":"6e8aff9c-f83a-4f0f-82da-2706b7b82b1b"},{"VIP_Name":"余","Money":"23349.30","Number":"7","VIP_ID":"fa49cb1b-7b46-49c6-a015-80fa77e5172e"},{"VIP_Name":"赵三","Money":"13132.00","Number":"49","VIP_ID":"0be2551e-53a7-45f2-aa03-a2373edf65b0"},{"VIP_Name":"Adele","Money":"12551.60","Number":"14","VIP_ID":"dd411089-755c-409c-9b27-a6e5b7f65c4d"},{"VIP_Name":"哈哈哈","Money":"7852.40","Number":"20","VIP_ID":"3fa6a15a-698e-4f10-b6ad-0c062ac1f875"},{"VIP_Name":"酷狗","Money":"3929.11","Number":"13","VIP_ID":"d223b91c-54e8-4127-b4c5-2f645102a6f5"},{"VIP_Name":"悟空","Money":"3427.00","Number":"5","VIP_ID":"568f3856-d558-457b-a012-ea07cf46cda8"},{"VIP_Name":"阿发","Money":"3111.00","Number":"4","VIP_ID":"9395b618-dde1-4307-b9da-7ec5097f0100"},{"VIP_Name":"李斯","Money":"3019.00","Number":"11","VIP_ID":"6362cbfc-d264-4a6e-850b-b764548ad197"},{"VIP_Name":"于蓝","Money":"2869.00","Number":"20","VIP_ID":"5f39714d-774b-4364-ba30-381c760c6fad"}]
         */

        private String SA_SKSalesNumber;
        private double SA_SKSalesMoney;
        private String SA_MBSalesNumber;
        private double SA_MBSalesMoney;
        private String SA_NewMemberNumber;
        private List<MberListBean> MberList;

        public String getSA_NewMemberNumber() {
            return SA_NewMemberNumber;
        }

        public void setSA_NewMemberNumber(String SA_NewMemberNumber) {
            this.SA_NewMemberNumber = SA_NewMemberNumber;
        }

        public String getSA_SKSalesNumber() {
            return SA_SKSalesNumber;
        }

        public void setSA_SKSalesNumber(String SA_SKSalesNumber) {
            this.SA_SKSalesNumber = SA_SKSalesNumber;
        }

        public double getSA_SKSalesMoney() {
            return SA_SKSalesMoney;
        }

        public void setSA_SKSalesMoney(double SA_SKSalesMoney) {
            this.SA_SKSalesMoney = SA_SKSalesMoney;
        }

        public String getSA_MBSalesNumber() {
            return SA_MBSalesNumber;
        }

        public void setSA_MBSalesNumber(String SA_MBSalesNumber) {
            this.SA_MBSalesNumber = SA_MBSalesNumber;
        }

        public double getSA_MBSalesMoney() {
            return SA_MBSalesMoney;
        }

        public void setSA_MBSalesMoney(double SA_MBSalesMoney) {
            this.SA_MBSalesMoney = SA_MBSalesMoney;
        }

        public List<MberListBean> getMberList() {
            return MberList;
        }

        public void setMberList(List<MberListBean> MberList) {
            this.MberList = MberList;
        }

        public static class MberListBean {
            /**
             * VIP_Name : 散客
             * Money : 78466.01
             * Number : 142
             * VIP_ID : 6e8aff9c-f83a-4f0f-82da-2706b7b82b1b
             */

            private String VIP_Name;
            private String Money;
            private String Number;
            private String VIP_ID;

            public String getVIP_Name() {
                return VIP_Name;
            }

            public void setVIP_Name(String VIP_Name) {
                this.VIP_Name = VIP_Name;
            }

            public String getMoney() {
                return Money;
            }

            public void setMoney(String Money) {
                this.Money = Money;
            }

            public String getNumber() {
                return Number;
            }

            public void setNumber(String Number) {
                this.Number = Number;
            }

            public String getVIP_ID() {
                return VIP_ID;
            }

            public void setVIP_ID(String VIP_ID) {
                this.VIP_ID = VIP_ID;
            }
        }
    }
}
