package com.zhiluo.android.yunpu.ui.activity.good.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${YSL} on 2018-05-18.
 */

public class OutRecoderBean implements Serializable{

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":3,"PageSize":20,"DataCount":46,"PageIndex":1,"DataList":[{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"9315681c-2c87-4a82-9d90-7f0e65c83a39","SO_TrackingNo":"CK1805181035347654","SO_Amount":1,"SO_TotalPrice":5,"SO_Creator":"18381306468","SO_Remark":"","SO_CreateTime":"2018-05-18 10:36:25","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"2","SO_PayType":"现金(5)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"7f823628-da13-4366-8846-d7d6bec1e591","SO_TrackingNo":"CK1805181024425138","SO_Amount":1,"SO_TotalPrice":5,"SO_Creator":"18381306468","SO_Remark":"","SO_CreateTime":"2018-05-18 10:25:23","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"2","SO_PayType":"现金(5)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"247d1855-af4c-4744-a74f-8a94473c04b7","SO_TrackingNo":"CK1805171757201553","SO_Amount":1,"SO_TotalPrice":5,"SO_Creator":"18381306468","SO_Remark":"","SO_CreateTime":"2018-05-17 17:58:06","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"2","SO_PayType":"现金(5)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"997a5fd2-7a3e-44d3-8159-1d2d9fde938e","SO_TrackingNo":"CK1805171342247691","SO_Amount":2,"SO_TotalPrice":70,"SO_Creator":"18381306468","SO_Remark":"","SO_CreateTime":"2018-05-17 13:42:50","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"2","SO_PayType":"现金(70)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"d8f7a35c-363e-49b8-ba13-4b28ed004777","SO_TrackingNo":"CK1805161843138535","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 18:43:13","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(121.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"6b90c506-bb00-40d8-ae2e-c94383d2f2dc","SO_TrackingNo":"CK1805161814182169","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 18:14:19","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(855.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"3ba8b225-5e49-4a46-8853-dc495212e0cd","SO_TrackingNo":"CK1805161814081991","SO_Amount":3,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 18:14:08","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(0.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"168ea06f-84e8-4650-94d1-c125829bd8f3","SO_TrackingNo":"CK1805161814068978","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 18:14:07","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(0.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"336bed36-517a-407c-9dfa-49b3e2f8b44e","SO_TrackingNo":"CK1805161742416067","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 17:42:41","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(69855.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"23bd5c22-2804-40f1-a3d8-7cadc75bbbab","SO_TrackingNo":"CK1805161742282718","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 17:42:29","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(70740.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"0694f837-4ff9-42d2-b3a8-5e4b1ae0f145","SO_TrackingNo":"CK1805161742271462","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 17:42:28","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(70740.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"07415876-202e-4a3f-bd8f-3b9cd6773ff9","SO_TrackingNo":"CK1805161742268420","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 17:42:26","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(70740.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"b1826719-e075-4ddc-b4b2-dbd84626c959","SO_TrackingNo":"CK1805161741501575","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 17:41:51","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(139740.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"d7c6f749-6083-4476-bb91-523b577d3e07","SO_TrackingNo":"CK1805161741498533","SO_Amount":2,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 17:41:49","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(139740.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"9a2af808-b502-4ff9-953e-9bea1ea2b018","SO_TrackingNo":"CK1805161602119736","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 16:02:11","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"积分(86)","SO_PayCode":"JFZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"9208e86b-e185-4295-8c2a-77c3095b3994","SO_TrackingNo":"CK1805161156469402","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 11:56:47","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"积分(86)","SO_PayCode":"JFZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"e3cddb97-6e6d-4ec1-9080-d608c18d394f","SO_TrackingNo":"CK1805161154594668","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 11:54:59","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"积分(86)","SO_PayCode":"JFZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"343d8fcb-9c9b-47f1-a782-f57d687a0eb7","SO_TrackingNo":"CK1805161148128727","SO_Amount":2,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 11:48:12","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"积分(172)","SO_PayCode":"JFZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"9109e925-cf71-4212-8e6b-2674b6ba4bf3","SO_TrackingNo":"CK1805161147399852","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 11:47:40","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"积分(86)","SO_PayCode":"JFZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"cb3b8fc8-709c-44cb-894b-c3138948b4ca","SO_TrackingNo":"CK1805161146178555","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 11:46:17","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"积分(86)","SO_PayCode":"JFZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null}],"StatisticsInfo":null}
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

    public static class DataBean implements Serializable{
        /**
         * PageTotal : 3
         * PageSize : 20
         * DataCount : 46
         * PageIndex : 1
         * DataList : [{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"9315681c-2c87-4a82-9d90-7f0e65c83a39","SO_TrackingNo":"CK1805181035347654","SO_Amount":1,"SO_TotalPrice":5,"SO_Creator":"18381306468","SO_Remark":"","SO_CreateTime":"2018-05-18 10:36:25","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"2","SO_PayType":"现金(5)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"7f823628-da13-4366-8846-d7d6bec1e591","SO_TrackingNo":"CK1805181024425138","SO_Amount":1,"SO_TotalPrice":5,"SO_Creator":"18381306468","SO_Remark":"","SO_CreateTime":"2018-05-18 10:25:23","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"2","SO_PayType":"现金(5)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"247d1855-af4c-4744-a74f-8a94473c04b7","SO_TrackingNo":"CK1805171757201553","SO_Amount":1,"SO_TotalPrice":5,"SO_Creator":"18381306468","SO_Remark":"","SO_CreateTime":"2018-05-17 17:58:06","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"2","SO_PayType":"现金(5)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"997a5fd2-7a3e-44d3-8159-1d2d9fde938e","SO_TrackingNo":"CK1805171342247691","SO_Amount":2,"SO_TotalPrice":70,"SO_Creator":"18381306468","SO_Remark":"","SO_CreateTime":"2018-05-17 13:42:50","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"2","SO_PayType":"现金(70)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"d8f7a35c-363e-49b8-ba13-4b28ed004777","SO_TrackingNo":"CK1805161843138535","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 18:43:13","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(121.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"6b90c506-bb00-40d8-ae2e-c94383d2f2dc","SO_TrackingNo":"CK1805161814182169","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 18:14:19","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(855.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"3ba8b225-5e49-4a46-8853-dc495212e0cd","SO_TrackingNo":"CK1805161814081991","SO_Amount":3,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 18:14:08","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(0.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"168ea06f-84e8-4650-94d1-c125829bd8f3","SO_TrackingNo":"CK1805161814068978","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 18:14:07","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(0.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"336bed36-517a-407c-9dfa-49b3e2f8b44e","SO_TrackingNo":"CK1805161742416067","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 17:42:41","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(69855.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"23bd5c22-2804-40f1-a3d8-7cadc75bbbab","SO_TrackingNo":"CK1805161742282718","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 17:42:29","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(70740.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"0694f837-4ff9-42d2-b3a8-5e4b1ae0f145","SO_TrackingNo":"CK1805161742271462","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 17:42:28","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(70740.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"07415876-202e-4a3f-bd8f-3b9cd6773ff9","SO_TrackingNo":"CK1805161742268420","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 17:42:26","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(70740.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"b1826719-e075-4ddc-b4b2-dbd84626c959","SO_TrackingNo":"CK1805161741501575","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 17:41:51","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(139740.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"d7c6f749-6083-4476-bb91-523b577d3e07","SO_TrackingNo":"CK1805161741498533","SO_Amount":2,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 17:41:49","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"现金(139740.00)","SO_PayCode":"XJZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"9a2af808-b502-4ff9-953e-9bea1ea2b018","SO_TrackingNo":"CK1805161602119736","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 16:02:11","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"积分(86)","SO_PayCode":"JFZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"9208e86b-e185-4295-8c2a-77c3095b3994","SO_TrackingNo":"CK1805161156469402","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 11:56:47","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"积分(86)","SO_PayCode":"JFZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"e3cddb97-6e6d-4ec1-9080-d608c18d394f","SO_TrackingNo":"CK1805161154594668","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 11:54:59","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"积分(86)","SO_PayCode":"JFZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"343d8fcb-9c9b-47f1-a782-f57d687a0eb7","SO_TrackingNo":"CK1805161148128727","SO_Amount":2,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 11:48:12","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"积分(172)","SO_PayCode":"JFZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"9109e925-cf71-4212-8e6b-2674b6ba4bf3","SO_TrackingNo":"CK1805161147399852","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 11:47:40","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"积分(86)","SO_PayCode":"JFZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null},{"SO_Hander":null,"SO_OtherMoney":0,"InGID":null,"GID":"cb3b8fc8-709c-44cb-894b-c3138948b4ca","SO_TrackingNo":"CK1805161146178555","SO_Amount":1,"SO_TotalPrice":0,"SO_Creator":"18381306468","SO_Remark":null,"SO_CreateTime":"2018-05-16 11:46:17","CY_GID":"8d72205d-d282-4b32-a9fb-af87fb5d5b8e","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SO_OutType":"1","SO_PayType":"积分(86)","SO_PayCode":"JFZF","SM_Name":"云上铺1号店","Detaillist":null,"SOTotalPrice":null}]
         * StatisticsInfo : null
         */

        private int PageTotal;
        private int PageSize;
        private int DataCount;
        private int PageIndex;
        private Object StatisticsInfo;
        private List<DataListBean> DataList;

        public int getPageTotal() {
            return PageTotal;
        }

        public void setPageTotal(int PageTotal) {
            this.PageTotal = PageTotal;
        }

        public int getPageSize() {
            return PageSize;
        }

        public void setPageSize(int PageSize) {
            this.PageSize = PageSize;
        }

        public int getDataCount() {
            return DataCount;
        }

        public void setDataCount(int DataCount) {
            this.DataCount = DataCount;
        }

        public int getPageIndex() {
            return PageIndex;
        }

        public void setPageIndex(int PageIndex) {
            this.PageIndex = PageIndex;
        }

        public Object getStatisticsInfo() {
            return StatisticsInfo;
        }

        public void setStatisticsInfo(Object StatisticsInfo) {
            this.StatisticsInfo = StatisticsInfo;
        }

        public List<DataListBean> getDataList() {
            return DataList;
        }

        public void setDataList(List<DataListBean> DataList) {
            this.DataList = DataList;
        }

        public static class DataListBean implements Serializable{
            /**
             * SO_Hander : null
             * SO_OtherMoney : 0.0
             * InGID : null
             * GID : 9315681c-2c87-4a82-9d90-7f0e65c83a39
             * SO_TrackingNo : CK1805181035347654
             * SO_Amount : 1.0
             * SO_TotalPrice : 5.0
             * SO_Creator : 18381306468
             * SO_Remark :
             * SO_CreateTime : 2018-05-18 10:36:25
             * CY_GID : 8d72205d-d282-4b32-a9fb-af87fb5d5b8e
             * SM_GID : 966d986b-0caa-465b-bbe1-8bd871bf8a0a
             * SO_OutType : 2
             * SO_PayType : 现金(5)
             * SO_PayCode : XJZF
             * SM_Name : 云上铺1号店
             * Detaillist : null
             * SOTotalPrice : null
             */

            private Object SO_Hander;
            private double SO_OtherMoney;
            private Object InGID;
            private String GID;
            private String SO_TrackingNo;
            private double SO_Amount;
            private double SO_TotalPrice;
            private String SO_Creator;
            private String SO_Remark;
            private String SO_CreateTime;
            private String CY_GID;
            private String SM_GID;
            private String SO_OutType;
            private String SO_PayType;
            private String SO_PayCode;
            private String SM_Name;
            private Object Detaillist;
            private Object SOTotalPrice;

            public Object getSO_Hander() {
                return SO_Hander;
            }

            public void setSO_Hander(Object SO_Hander) {
                this.SO_Hander = SO_Hander;
            }

            public double getSO_OtherMoney() {
                return SO_OtherMoney;
            }

            public void setSO_OtherMoney(double SO_OtherMoney) {
                this.SO_OtherMoney = SO_OtherMoney;
            }

            public Object getInGID() {
                return InGID;
            }

            public void setInGID(Object InGID) {
                this.InGID = InGID;
            }

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public String getSO_TrackingNo() {
                return SO_TrackingNo;
            }

            public void setSO_TrackingNo(String SO_TrackingNo) {
                this.SO_TrackingNo = SO_TrackingNo;
            }

            public double getSO_Amount() {
                return SO_Amount;
            }

            public void setSO_Amount(double SO_Amount) {
                this.SO_Amount = SO_Amount;
            }

            public double getSO_TotalPrice() {
                return SO_TotalPrice;
            }

            public void setSO_TotalPrice(double SO_TotalPrice) {
                this.SO_TotalPrice = SO_TotalPrice;
            }

            public String getSO_Creator() {
                return SO_Creator;
            }

            public void setSO_Creator(String SO_Creator) {
                this.SO_Creator = SO_Creator;
            }

            public String getSO_Remark() {
                return SO_Remark;
            }

            public void setSO_Remark(String SO_Remark) {
                this.SO_Remark = SO_Remark;
            }

            public String getSO_CreateTime() {
                return SO_CreateTime;
            }

            public void setSO_CreateTime(String SO_CreateTime) {
                this.SO_CreateTime = SO_CreateTime;
            }

            public String getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(String CY_GID) {
                this.CY_GID = CY_GID;
            }

            public String getSM_GID() {
                return SM_GID;
            }

            public void setSM_GID(String SM_GID) {
                this.SM_GID = SM_GID;
            }

            public String getSO_OutType() {
                return SO_OutType;
            }

            public void setSO_OutType(String SO_OutType) {
                this.SO_OutType = SO_OutType;
            }

            public String getSO_PayType() {
                return SO_PayType;
            }

            public void setSO_PayType(String SO_PayType) {
                this.SO_PayType = SO_PayType;
            }

            public String getSO_PayCode() {
                return SO_PayCode;
            }

            public void setSO_PayCode(String SO_PayCode) {
                this.SO_PayCode = SO_PayCode;
            }

            public String getSM_Name() {
                return SM_Name;
            }

            public void setSM_Name(String SM_Name) {
                this.SM_Name = SM_Name;
            }

            public Object getDetaillist() {
                return Detaillist;
            }

            public void setDetaillist(Object Detaillist) {
                this.Detaillist = Detaillist;
            }

            public Object getSOTotalPrice() {
                return SOTotalPrice;
            }

            public void setSOTotalPrice(Object SOTotalPrice) {
                this.SOTotalPrice = SOTotalPrice;
            }
        }
    }
}
