package com.zhiluo.android.yunpu.statistics.order.bean;

import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/13 14:25.
 * 邮箱：137615198@qq.com
 */

public class PointExchangeDetailBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":"0fdca6da-1609-4045-977c-83b318744776","IEG_GID":"231b1bc1-ad7b-47e4-b9ec-e0a6df768dd2","EGD_DateTime":"2017-09-11 18:45:34","GT_Name":"生活日常","GM_Name":"鸡蛋","GM_Code":"585","GM_Acount":1,"GM_Integral":20,"GM_PictureUrl":null,"EGD_Score":20,"EGD_Creator":"1540004275@qq.com","CY_GID":null,"EGD_UpdateTime":"2017-09-11 18:45:34","GM_GID":null},{"GID":"a911def3-2716-4fc3-86a6-70321df10d19","IEG_GID":"231b1bc1-ad7b-47e4-b9ec-e0a6df768dd2","EGD_DateTime":"2017-09-11 18:45:34","GT_Name":"零食","GM_Name":"茶叶","GM_Code":"8572","GM_Acount":1,"GM_Integral":50,"GM_PictureUrl":null,"EGD_Score":50,"EGD_Creator":"1540004275@qq.com","CY_GID":null,"EGD_UpdateTime":"2017-09-11 18:45:34","GM_GID":null}]
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
         * GID : 0fdca6da-1609-4045-977c-83b318744776
         * IEG_GID : 231b1bc1-ad7b-47e4-b9ec-e0a6df768dd2
         * EGD_DateTime : 2017-09-11 18:45:34
         * GT_Name : 生活日常
         * GM_Name : 鸡蛋
         * GM_Code : 585
         * GM_Acount : 1
         * GM_Integral : 20.0
         * GM_PictureUrl : null
         * EGD_Score : 20.0
         * EGD_Creator : 1540004275@qq.com
         * CY_GID : null
         * EGD_UpdateTime : 2017-09-11 18:45:34
         * GM_GID : null
         */

        private String GID;
        private String IEG_GID;
        private String EGD_DateTime;
        private String GT_Name;
        private String GM_Name;
        private String GM_Code;
        private int GM_Acount;
        private double GM_Integral;
        private Object GM_PictureUrl;
        private double EGD_Score;
        private String EGD_Creator;
        private Object CY_GID;
        private String EGD_UpdateTime;
        private Object GM_GID;

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getIEG_GID() {
            return IEG_GID;
        }

        public void setIEG_GID(String IEG_GID) {
            this.IEG_GID = IEG_GID;
        }

        public String getEGD_DateTime() {
            return EGD_DateTime;
        }

        public void setEGD_DateTime(String EGD_DateTime) {
            this.EGD_DateTime = EGD_DateTime;
        }

        public String getGT_Name() {
            return GT_Name;
        }

        public void setGT_Name(String GT_Name) {
            this.GT_Name = GT_Name;
        }

        public String getGM_Name() {
            return GM_Name;
        }

        public void setGM_Name(String GM_Name) {
            this.GM_Name = GM_Name;
        }

        public String getGM_Code() {
            return GM_Code;
        }

        public void setGM_Code(String GM_Code) {
            this.GM_Code = GM_Code;
        }

        public int getGM_Acount() {
            return GM_Acount;
        }

        public void setGM_Acount(int GM_Acount) {
            this.GM_Acount = GM_Acount;
        }

        public double getGM_Integral() {
            return GM_Integral;
        }

        public void setGM_Integral(double GM_Integral) {
            this.GM_Integral = GM_Integral;
        }

        public Object getGM_PictureUrl() {
            return GM_PictureUrl;
        }

        public void setGM_PictureUrl(Object GM_PictureUrl) {
            this.GM_PictureUrl = GM_PictureUrl;
        }

        public double getEGD_Score() {
            return EGD_Score;
        }

        public void setEGD_Score(double EGD_Score) {
            this.EGD_Score = EGD_Score;
        }

        public String getEGD_Creator() {
            return EGD_Creator;
        }

        public void setEGD_Creator(String EGD_Creator) {
            this.EGD_Creator = EGD_Creator;
        }

        public Object getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(Object CY_GID) {
            this.CY_GID = CY_GID;
        }

        public String getEGD_UpdateTime() {
            return EGD_UpdateTime;
        }

        public void setEGD_UpdateTime(String EGD_UpdateTime) {
            this.EGD_UpdateTime = EGD_UpdateTime;
        }

        public Object getGM_GID() {
            return GM_GID;
        }

        public void setGM_GID(Object GM_GID) {
            this.GM_GID = GM_GID;
        }
    }
}
