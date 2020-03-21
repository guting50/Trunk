package com.zhiluo.android.yunpu.gift.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 70700 on 2016/10/27.
 */
public class ChargeListBean{


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":"c3e2a5ba-9832-4ed4-acb0-62aa8a0dc6da","MCA_InitTimes":0,"MCA_OverTime":null,"MCA_TotalCharge":1,"MCA_HowMany":1,"SG_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","SG_Name":"1010","SGC_ClasName":"牙刷","SG_Code":"10","SG_Price":10,"PM_BigImg":"/img/goods.png","WR_GID":"71ada9b6-cb22-4a0d-bc0d-89c8d0a38d2a","WR_Name":"每天消费3次","VIP_GID":"75fda64f-234f-4eae-981c-43ed3a52ec77","RegularResiduals":3},{"GID":"36f94004-7dee-4248-bc2b-cd3575b94d04","MCA_InitTimes":0,"MCA_OverTime":null,"MCA_TotalCharge":2,"MCA_HowMany":2,"SG_GID":"378c63e4-2a68-4f90-a2ee-916a95d3fc63","SG_Name":"SPA","SGC_ClasName":"跑腿","SG_Code":"154233347518437","SG_Price":30,"PM_BigImg":"/img/product.png","WR_GID":"71ada9b6-cb22-4a0d-bc0d-89c8d0a38d2a","WR_Name":"每天消费3次","VIP_GID":"75fda64f-234f-4eae-981c-43ed3a52ec77","RegularResiduals":3},{"GID":"5184010c-9e4f-48fa-8607-0d881437405f","MCA_InitTimes":0,"MCA_OverTime":null,"MCA_TotalCharge":6,"MCA_HowMany":5,"SG_GID":"378c63e4-2a68-4f90-a2ee-916a95d3fc63","SG_Name":"SPA","SGC_ClasName":"跑腿","SG_Code":"154233347518437","SG_Price":30,"PM_BigImg":"/img/product.png","WR_GID":"04e9ede7-70ea-4ebc-a7fe-e01cce5d974c","WR_Name":"是","VIP_GID":"75fda64f-234f-4eae-981c-43ed3a52ec77","RegularResiduals":1},{"GID":"59dfa7fc-05d5-4cb4-bfc4-2442e8075836","MCA_InitTimes":0,"MCA_OverTime":null,"MCA_TotalCharge":6,"MCA_HowMany":6,"SG_GID":"378c63e4-2a68-4f90-a2ee-916a95d3fc63","SG_Name":"SPA","SGC_ClasName":"跑腿","SG_Code":"154233347518437","SG_Price":30,"PM_BigImg":"/img/product.png","WR_GID":null,"WR_Name":"","VIP_GID":"75fda64f-234f-4eae-981c-43ed3a52ec77","RegularResiduals":6},{"GID":"502fb7d9-d429-4b06-bc38-ce88bfe09421","MCA_InitTimes":0,"MCA_OverTime":null,"MCA_TotalCharge":10,"MCA_HowMany":10,"SG_GID":"2b641154-14e5-40f1-875e-7fb8a71ecc3d","SG_Name":"服务商品","SGC_ClasName":"足疗","SG_Code":"45646513","SG_Price":128,"PM_BigImg":"/img/goods.png","WR_GID":null,"WR_Name":"","VIP_GID":"75fda64f-234f-4eae-981c-43ed3a52ec77","RegularResiduals":10},{"GID":"7c5a0fab-9d8d-40f4-87b1-56d724a49a4d","MCA_InitTimes":0,"MCA_OverTime":null,"MCA_TotalCharge":1,"MCA_HowMany":1,"SG_GID":"ab05706d-3879-44dd-9893-5df2f326e684","SG_Name":"服务商品007","SGC_ClasName":"跑腿","SG_Code":"5668977834","SG_Price":10,"PM_BigImg":"/img/goods.png","WR_GID":null,"WR_Name":"","VIP_GID":"75fda64f-234f-4eae-981c-43ed3a52ec77","RegularResiduals":1},{"GID":"977c487c-8c77-4ee4-ad42-56b0b1b8728e","MCA_InitTimes":0,"MCA_OverTime":null,"MCA_TotalCharge":1,"MCA_HowMany":1,"SG_GID":"f809d722-18b5-4f7f-9ac7-fa0d7ad9a0ff","SG_Name":"服务商品007","SGC_ClasName":"跑腿","SG_Code":"566897783","SG_Price":10,"PM_BigImg":"/img/goods.png","WR_GID":"71ada9b6-cb22-4a0d-bc0d-89c8d0a38d2a","WR_Name":"每天消费3次","VIP_GID":"75fda64f-234f-4eae-981c-43ed3a52ec77","RegularResiduals":3},{"GID":"e14d7f56-38f7-4132-b43c-540308edd90c","MCA_InitTimes":0,"MCA_OverTime":null,"MCA_TotalCharge":1,"MCA_HowMany":1,"SG_GID":"2efb8c23-0aed-41a2-a8b2-9c2bc0cedd72","SG_Name":"礼品商品004","SGC_ClasName":"帕子","SG_Code":"153233482589059","SG_Price":1200,"PM_BigImg":"/img/product.png","WR_GID":"71ada9b6-cb22-4a0d-bc0d-89c8d0a38d2a","WR_Name":"每天消费3次","VIP_GID":"75fda64f-234f-4eae-981c-43ed3a52ec77","RegularResiduals":3}]
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

    public static class DataBean implements Serializable{
        /**
         * GID : c3e2a5ba-9832-4ed4-acb0-62aa8a0dc6da
         * MCA_InitTimes : 0
         * MCA_OverTime : null
         * MCA_TotalCharge : 1
         * MCA_HowMany : 1
         * SG_GID : 7ace77f3-219b-4680-993f-ea1fc5e543bc
         * SG_Name : 1010
         * SGC_ClasName : 牙刷
         * SG_Code : 10
         * SG_Price : 10.0
         * PM_BigImg : /img/goods.png
         * WR_GID : 71ada9b6-cb22-4a0d-bc0d-89c8d0a38d2a
         * WR_Name : 每天消费3次
         * VIP_GID : 75fda64f-234f-4eae-981c-43ed3a52ec77
         * RegularResiduals : 3
         */

        private String GID;
        private int MCA_InitTimes;
        private String MCA_OverTime;
        private int MCA_TotalCharge;
        private int MCA_HowMany;
        private String SG_GID;
        private String SG_Name;
        private String SGC_ClasName;
        private String SG_Code;
        private double SG_Price;
        private String PM_BigImg;
        private String WR_GID;
        private String WR_Name;
        private String VIP_GID;
        private int RegularResiduals;

        public String getEmployeeValue() {
            return EmployeeValue;
        }

        public void setEmployeeValue(String employeeValue) {
            EmployeeValue = employeeValue;
        }

        private String EmployeeValue;

        public int getCount() {
            return Count;
        }

        public void setCount(int count) {
            Count = count;
        }

        private int Count;

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public int getMCA_InitTimes() {
            return MCA_InitTimes;
        }

        public void setMCA_InitTimes(int MCA_InitTimes) {
            this.MCA_InitTimes = MCA_InitTimes;
        }

        public String getMCA_OverTime() {
            return MCA_OverTime;
        }

        public void setMCA_OverTime(String MCA_OverTime) {
            this.MCA_OverTime = MCA_OverTime;
        }

        public int getMCA_TotalCharge() {
            return MCA_TotalCharge;
        }

        public void setMCA_TotalCharge(int MCA_TotalCharge) {
            this.MCA_TotalCharge = MCA_TotalCharge;
        }

        public int getMCA_HowMany() {
            return MCA_HowMany;
        }

        public void setMCA_HowMany(int MCA_HowMany) {
            this.MCA_HowMany = MCA_HowMany;
        }

        public String getSG_GID() {
            return SG_GID;
        }

        public void setSG_GID(String SG_GID) {
            this.SG_GID = SG_GID;
        }

        public String getSG_Name() {
            return SG_Name;
        }

        public void setSG_Name(String SG_Name) {
            this.SG_Name = SG_Name;
        }

        public String getSGC_ClasName() {
            return SGC_ClasName;
        }

        public void setSGC_ClasName(String SGC_ClasName) {
            this.SGC_ClasName = SGC_ClasName;
        }

        public String getSG_Code() {
            return SG_Code;
        }

        public void setSG_Code(String SG_Code) {
            this.SG_Code = SG_Code;
        }

        public double getSG_Price() {
            return SG_Price;
        }

        public void setSG_Price(double SG_Price) {
            this.SG_Price = SG_Price;
        }

        public String getPM_BigImg() {
            return PM_BigImg;
        }

        public void setPM_BigImg(String PM_BigImg) {
            this.PM_BigImg = PM_BigImg;
        }

        public String getWR_GID() {
            return WR_GID;
        }

        public void setWR_GID(String WR_GID) {
            this.WR_GID = WR_GID;
        }

        public String getWR_Name() {
            return WR_Name;
        }

        public void setWR_Name(String WR_Name) {
            this.WR_Name = WR_Name;
        }

        public String getVIP_GID() {
            return VIP_GID;
        }

        public void setVIP_GID(String VIP_GID) {
            this.VIP_GID = VIP_GID;
        }

        public int getRegularResiduals() {
            return RegularResiduals;
        }

        public void setRegularResiduals(int RegularResiduals) {
            this.RegularResiduals = RegularResiduals;
        }
    }
}
