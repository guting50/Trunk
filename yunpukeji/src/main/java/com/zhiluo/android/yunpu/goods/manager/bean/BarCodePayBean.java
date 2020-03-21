package com.zhiluo.android.yunpu.goods.manager.bean;

import java.io.Serializable;

/**
 * Created by YSL on 2018-10-31.
 */

public class BarCodePayBean implements Serializable{


    /**
     * success : false
     * code : 410004
     * msg : 执行失败
     * data : {"GID":"53cbd8a3-3a70-4714-847f-3054b85bac26","PR_PayType":"010","PR_MerchantName":"深圳市智络科技有限公司成都分公司成都总店","PR_MerchantNo":"865100111000018","PR_TerminalId":"10397335","PR_TerminalTrace":"SP1906151133045367","PR_TerminalTime":"20190615113351","PR_TotalFee":"1","PR_EndTime":"20190615113351","PR_OutTradeNo":"103973356821119061511335111237","PR_ChannelTradeNo":"","PR_ChannelOrderNo":"","PR_UserId":"","Order_GID":"07710b34-594d-4441-acc2-301226d5bc1b","Order_Type":"10","CY_GID":"24ce8461-125e-4683-9032-b208e7c2f995","Pay_State":"03","OrderPayJson":"{\"PayTypeList\":[{\"PayCode\":\"SMZF\",\"PayName\":\"扫码支付\",\"PayMoney\":0.01,\"PayPoint\":null,\"GID\":null}],\"GiveChange\":0,\"PayTotalMoney\":0.01,\"DisMoney\":0.01,\"IsEraseZero\":false}"}
     */

    private boolean success;
    private String code;
    private String msg;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * GID : 53cbd8a3-3a70-4714-847f-3054b85bac26
         * PR_PayType : 010
         * PR_MerchantName : 深圳市智络科技有限公司成都分公司成都总店
         * PR_MerchantNo : 865100111000018
         * PR_TerminalId : 10397335
         * PR_TerminalTrace : SP1906151133045367
         * PR_TerminalTime : 20190615113351
         * PR_TotalFee : 1
         * PR_EndTime : 20190615113351
         * PR_OutTradeNo : 103973356821119061511335111237
         * PR_ChannelTradeNo :
         * PR_ChannelOrderNo :
         * PR_UserId :
         * Order_GID : 07710b34-594d-4441-acc2-301226d5bc1b
         * Order_Type : 10
         * CY_GID : 24ce8461-125e-4683-9032-b208e7c2f995
         * Pay_State : 03
         * OrderPayJson : {"PayTypeList":[{"PayCode":"SMZF","PayName":"扫码支付","PayMoney":0.01,"PayPoint":null,"GID":null}],"GiveChange":0,"PayTotalMoney":0.01,"DisMoney":0.01,"IsEraseZero":false}
         */

        private String GID;
        private String PR_PayType;
        private String PR_MerchantName;
        private String PR_MerchantNo;
        private String PR_TerminalId;
        private String PR_TerminalTrace;
        private String PR_TerminalTime;
        private String PR_TotalFee;
        private String PR_EndTime;
        private String PR_OutTradeNo;
        private String PR_ChannelTradeNo;
        private String PR_ChannelOrderNo;
        private String PR_UserId;
        private String Order_GID;
        private String Order_Type;
        private String CY_GID;
        private String Pay_State;
        private String OrderPayJson;

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getPR_PayType() {
            return PR_PayType;
        }

        public void setPR_PayType(String PR_PayType) {
            this.PR_PayType = PR_PayType;
        }

        public String getPR_MerchantName() {
            return PR_MerchantName;
        }

        public void setPR_MerchantName(String PR_MerchantName) {
            this.PR_MerchantName = PR_MerchantName;
        }

        public String getPR_MerchantNo() {
            return PR_MerchantNo;
        }

        public void setPR_MerchantNo(String PR_MerchantNo) {
            this.PR_MerchantNo = PR_MerchantNo;
        }

        public String getPR_TerminalId() {
            return PR_TerminalId;
        }

        public void setPR_TerminalId(String PR_TerminalId) {
            this.PR_TerminalId = PR_TerminalId;
        }

        public String getPR_TerminalTrace() {
            return PR_TerminalTrace;
        }

        public void setPR_TerminalTrace(String PR_TerminalTrace) {
            this.PR_TerminalTrace = PR_TerminalTrace;
        }

        public String getPR_TerminalTime() {
            return PR_TerminalTime;
        }

        public void setPR_TerminalTime(String PR_TerminalTime) {
            this.PR_TerminalTime = PR_TerminalTime;
        }

        public String getPR_TotalFee() {
            return PR_TotalFee;
        }

        public void setPR_TotalFee(String PR_TotalFee) {
            this.PR_TotalFee = PR_TotalFee;
        }

        public String getPR_EndTime() {
            return PR_EndTime;
        }

        public void setPR_EndTime(String PR_EndTime) {
            this.PR_EndTime = PR_EndTime;
        }

        public String getPR_OutTradeNo() {
            return PR_OutTradeNo;
        }

        public void setPR_OutTradeNo(String PR_OutTradeNo) {
            this.PR_OutTradeNo = PR_OutTradeNo;
        }

        public String getPR_ChannelTradeNo() {
            return PR_ChannelTradeNo;
        }

        public void setPR_ChannelTradeNo(String PR_ChannelTradeNo) {
            this.PR_ChannelTradeNo = PR_ChannelTradeNo;
        }

        public String getPR_ChannelOrderNo() {
            return PR_ChannelOrderNo;
        }

        public void setPR_ChannelOrderNo(String PR_ChannelOrderNo) {
            this.PR_ChannelOrderNo = PR_ChannelOrderNo;
        }

        public String getPR_UserId() {
            return PR_UserId;
        }

        public void setPR_UserId(String PR_UserId) {
            this.PR_UserId = PR_UserId;
        }

        public String getOrder_GID() {
            return Order_GID;
        }

        public void setOrder_GID(String Order_GID) {
            this.Order_GID = Order_GID;
        }

        public String getOrder_Type() {
            return Order_Type;
        }

        public void setOrder_Type(String Order_Type) {
            this.Order_Type = Order_Type;
        }

        public String getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(String CY_GID) {
            this.CY_GID = CY_GID;
        }

        public String getPay_State() {
            return Pay_State;
        }

        public void setPay_State(String Pay_State) {
            this.Pay_State = Pay_State;
        }

        public String getOrderPayJson() {
            return OrderPayJson;
        }

        public void setOrderPayJson(String OrderPayJson) {
            this.OrderPayJson = OrderPayJson;
        }
    }
}
