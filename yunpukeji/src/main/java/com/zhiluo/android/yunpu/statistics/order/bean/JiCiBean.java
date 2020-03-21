package com.zhiluo.android.yunpu.statistics.order.bean;

import java.util.List;

/**
 * Created by YSL on 2018-06-08.
 */

public class JiCiBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"MCA_InitTimes":0,"MCA_OverTime":null,"MCA_TotalCharge":3,"MCA_HowMany":3,"SG_GID":"b2055051-f053-43b9-be6d-616f115a8d55","SG_Name":"特价折扣0.9","SGC_ClasName":"笔呦提否","SG_Code":"89066","SG_Price":100,"PM_BigImg":"/img/goods.png"},{"MCA_InitTimes":0,"MCA_OverTime":null,"MCA_TotalCharge":6,"MCA_HowMany":5,"SG_GID":"cf012b5b-b9d5-4e67-afc8-7ec8148551a7","SG_Name":"鞋子","SGC_ClasName":"，","SG_Code":"222","SG_Price":10,"PM_BigImg":"/img/goods.png"},{"MCA_InitTimes":0,"MCA_OverTime":null,"MCA_TotalCharge":13,"MCA_HowMany":3,"SG_GID":"9434a51d-964f-4b59-b4ac-6dfc250531b8","SG_Name":"流量","SGC_ClasName":"，，","SG_Code":"LL","SG_Price":10,"PM_BigImg":"/img/goods.png"},{"MCA_InitTimes":0,"MCA_OverTime":null,"MCA_TotalCharge":2,"MCA_HowMany":2,"SG_GID":"51add75d-a219-46f4-9144-29bab25a0e39","SG_Name":"钢笔","SGC_ClasName":"笔呦提否","SG_Code":"SP002","SG_Price":100,"PM_BigImg":"/img/product.png"},{"MCA_InitTimes":0,"MCA_OverTime":null,"MCA_TotalCharge":62,"MCA_HowMany":60,"SG_GID":"3f04a010-309b-40ed-b44e-eba97f4e1f8a","SG_Name":"共和国","SGC_ClasName":"！？","SG_Code":"9936","SG_Price":9,"PM_BigImg":"http://img.yunvip123.com/CommonImg/588e7e0a-93b0-4416-984a-500d8660ff72.png"}]
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
         * MCA_InitTimes : 0
         * MCA_OverTime : null
         * MCA_TotalCharge : 3
         * MCA_HowMany : 3
         * SG_GID : b2055051-f053-43b9-be6d-616f115a8d55
         * SG_Name : 特价折扣0.9
         * SGC_ClasName : 笔呦提否
         * SG_Code : 89066
         * SG_Price : 100.0
         * PM_BigImg : /img/goods.png
         */

        private int MCA_InitTimes;
        private Object MCA_OverTime;
        private int MCA_TotalCharge;
        private int MCA_HowMany;
        private String SG_GID;
        private String SG_Name;
        private String SGC_ClasName;
        private String SG_Code;
        private double SG_Price;
        private String PM_BigImg;

        public int getMCA_InitTimes() {
            return MCA_InitTimes;
        }

        public void setMCA_InitTimes(int MCA_InitTimes) {
            this.MCA_InitTimes = MCA_InitTimes;
        }

        public Object getMCA_OverTime() {
            return MCA_OverTime;
        }

        public void setMCA_OverTime(Object MCA_OverTime) {
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
    }
}
