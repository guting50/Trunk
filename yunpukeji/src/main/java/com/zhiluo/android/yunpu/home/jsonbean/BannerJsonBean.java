package com.zhiluo.android.yunpu.home.jsonbean;

import java.util.List;

/**
 * iauthor：Cheng
 * date: 2017/5/17 14:27
 * email：jasoncheng9111@gmail.com
 */
public class BannerJsonBean
{

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":"46752fa6-9bdd-4a5c-8801-e90677f89428","AD_Type":2,"AD_Text":"2","AD_Link":"","AD_ImgUrl":"/Upload/AdvertImage/911060596.jpg","AD_CreateTime":"2017-05-17
     * 16:32:17","AD_Remark":"2","AD_Sort":2,"AD_ClickNumber":0,"AD_DownLoadNumber":0,"AD_ExposureNumber":0,"AD_Link_Color":"","AD_EndTime":"2027-05-17 16:32:17",
     * "AD_Position":"","AD_CustomerType":"","AD_ProductID":"手机App","AD_DealNumber":0,"AD_Link_Real":"http://m.yunvip123.com"},{"GID":"a6617708-18cb-4b40-9042-55d17b70f958",
     * "AD_Type":2,"AD_Text":"1","AD_Link":"","AD_ImgUrl":"/Upload/AdvertImage/927501914.jpg","AD_CreateTime":"2017-05-17 16:31:57","AD_Remark":"1","AD_Sort":1,
     * "AD_ClickNumber":0,"AD_DownLoadNumber":0,"AD_ExposureNumber":0,"AD_Link_Color":"","AD_EndTime":"2027-05-17 16:31:57","AD_Position":"","AD_CustomerType":"",
     * "AD_ProductID":"手机App","AD_DealNumber":0,"AD_Link_Real":"http://m.yunvip123.com"},{"GID":"ffac1957-5a99-4d1a-8346-cea9036675ca","AD_Type":2,"AD_Text":"3","AD_Link":"",
     * "AD_ImgUrl":"/Upload/AdvertImage/905959800.jpg","AD_CreateTime":"2017-05-17 16:32:35","AD_Remark":"3","AD_Sort":3,"AD_ClickNumber":0,"AD_DownLoadNumber":0,
     * "AD_ExposureNumber":0,"AD_Link_Color":"","AD_EndTime":"2027-05-17 16:32:35","AD_Position":"","AD_CustomerType":"","AD_ProductID":"手机App","AD_DealNumber":0,
     * "AD_Link_Real":"http://m.yunvip123.com"}]
     */

    private boolean success;
    private Object code;
    private String msg;
    private List<DataBean> data;

    public boolean isSuccess() { return success;}

    public void setSuccess(boolean success) { this.success = success;}

    public Object getCode() { return code;}

    public void setCode(Object code) { this.code = code;}

    public String getMsg() { return msg;}

    public void setMsg(String msg) { this.msg = msg;}

    public List<DataBean> getData() { return data;}

    public void setData(List<DataBean> data) { this.data = data;}

    public static class DataBean
    {
        /**
         * GID : 46752fa6-9bdd-4a5c-8801-e90677f89428
         * AD_Type : 2
         * AD_Text : 2
         * AD_Link :
         * AD_ImgUrl : /Upload/AdvertImage/911060596.jpg
         * AD_CreateTime : 2017-05-17 16:32:17
         * AD_Remark : 2
         * AD_Sort : 2
         * AD_ClickNumber : 0
         * AD_DownLoadNumber : 0
         * AD_ExposureNumber : 0
         * AD_Link_Color :
         * AD_EndTime : 2027-05-17 16:32:17
         * AD_Position :
         * AD_CustomerType :
         * AD_ProductID : 手机App
         * AD_DealNumber : 0
         * AD_Link_Real : http://m.yunvip123.com
         */

        private String GID;
        private int AD_Type;
        private String AD_Text;
        private String AD_Link;
        private String AD_ImgUrl;
        private String AD_CreateTime;
        private String AD_Remark;
        private int AD_Sort;
        private int AD_ClickNumber;
        private int AD_DownLoadNumber;
        private int AD_ExposureNumber;
        private String AD_Link_Color;
        private String AD_EndTime;
        private String AD_Position;
        private String AD_CustomerType;
        private String AD_ProductID;
        private int AD_DealNumber;
        private String AD_Link_Real;

        public String getGID() { return GID;}

        public void setGID(String GID) { this.GID = GID;}

        public int getAD_Type() { return AD_Type;}

        public void setAD_Type(int AD_Type) { this.AD_Type = AD_Type;}

        public String getAD_Text() { return AD_Text;}

        public void setAD_Text(String AD_Text) { this.AD_Text = AD_Text;}

        public String getAD_Link() { return AD_Link;}

        public void setAD_Link(String AD_Link) { this.AD_Link = AD_Link;}

        public String getAD_ImgUrl() { return AD_ImgUrl;}

        public void setAD_ImgUrl(String AD_ImgUrl) { this.AD_ImgUrl = AD_ImgUrl;}

        public String getAD_CreateTime() { return AD_CreateTime;}

        public void setAD_CreateTime(String AD_CreateTime) { this.AD_CreateTime = AD_CreateTime;}

        public String getAD_Remark() { return AD_Remark;}

        public void setAD_Remark(String AD_Remark) { this.AD_Remark = AD_Remark;}

        public int getAD_Sort() { return AD_Sort;}

        public void setAD_Sort(int AD_Sort) { this.AD_Sort = AD_Sort;}

        public int getAD_ClickNumber() { return AD_ClickNumber;}

        public void setAD_ClickNumber(int AD_ClickNumber) { this.AD_ClickNumber = AD_ClickNumber;}

        public int getAD_DownLoadNumber() { return AD_DownLoadNumber;}

        public void setAD_DownLoadNumber(int AD_DownLoadNumber) { this.AD_DownLoadNumber = AD_DownLoadNumber;}

        public int getAD_ExposureNumber() { return AD_ExposureNumber;}

        public void setAD_ExposureNumber(int AD_ExposureNumber) { this.AD_ExposureNumber = AD_ExposureNumber;}

        public String getAD_Link_Color() { return AD_Link_Color;}

        public void setAD_Link_Color(String AD_Link_Color) { this.AD_Link_Color = AD_Link_Color;}

        public String getAD_EndTime() { return AD_EndTime;}

        public void setAD_EndTime(String AD_EndTime) { this.AD_EndTime = AD_EndTime;}

        public String getAD_Position() { return AD_Position;}

        public void setAD_Position(String AD_Position) { this.AD_Position = AD_Position;}

        public String getAD_CustomerType() { return AD_CustomerType;}

        public void setAD_CustomerType(String AD_CustomerType) { this.AD_CustomerType = AD_CustomerType;}

        public String getAD_ProductID() { return AD_ProductID;}

        public void setAD_ProductID(String AD_ProductID) { this.AD_ProductID = AD_ProductID;}

        public int getAD_DealNumber() { return AD_DealNumber;}

        public void setAD_DealNumber(int AD_DealNumber) { this.AD_DealNumber = AD_DealNumber;}

        public String getAD_Link_Real() { return AD_Link_Real;}

        public void setAD_Link_Real(String AD_Link_Real) { this.AD_Link_Real = AD_Link_Real;}
    }
}
