package com.zhiluo.android.yunpu.entity;

import java.util.List;

/**
 * Created by YSL on 2018-08-29.
 */

public class LevelDataBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":"054dbd3c-67ba-4109-9c5c-137b7f4596db","PT_Name":"1","PT_Type":"商品","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","PT_SynType":"0"},{"GID":"071014a5-c89b-409f-a31c-7b0b3dbb7b68","PT_Name":"牙刷","PT_Type":"商品","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","PT_SynType":"0"},{"GID":"0fa82ed2-b3d9-4dda-9929-c9221458edff","PT_Name":"花果","PT_Type":"商品","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","PT_SynType":"0"},{"GID":"1827050f-221b-4797-941c-87dc0eb36872","PT_Name":"理发","PT_Type":"商品","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","PT_SynType":"0"},{"GID":"1a77a45c-6a7c-4ab3-b865-5d4370e38bc7","PT_Name":"足疗","PT_Type":"商品","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","PT_SynType":"0"},{"GID":"2882bd96-202f-4f6b-9596-d7a64059ef76","PT_Name":"橘","PT_Type":"商品","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","PT_SynType":"0"},{"GID":"2dbddf40-bb15-44b2-880e-cfa9b70eb6a3","PT_Name":"跑腿","PT_Type":"商品","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","PT_SynType":"0"},{"GID":"638d56bd-d394-4b95-bbaa-3060d4a4e68f","PT_Name":"33333","PT_Type":"商品","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","PT_SynType":"0"},{"GID":"764dcc5c-74ff-4bc3-90c3-3cf3152bfd0c","PT_Name":"积分","PT_Type":"商品","SM_GID":null,"SM_Name":null,"PT_SynType":"1"},{"GID":"7cd7235c-4b5d-4859-b2a9-ef9557ef1b9d","PT_Name":"粽子礼盒","PT_Type":"商品","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","PT_SynType":"0"},{"GID":"7eed0611-de8b-49b8-a25e-b67a723b6d5d","PT_Name":"牙膏","PT_Type":"商品","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","PT_SynType":"0"},{"GID":"819d58ba-e338-4944-8c18-4152806ebe24","PT_Name":"月饼礼盒","PT_Type":"商品","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","PT_SynType":"0"},{"GID":"865db4a8-e5d8-4457-af2d-f0288eb22802","PT_Name":"111","PT_Type":"商品","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","PT_SynType":"0"},{"GID":"ae26522f-d0e8-48f9-a9a7-1d6d41077eaf","PT_Name":"帕子","PT_Type":"商品","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","PT_SynType":"0"},{"GID":"c545dd9b-0665-42b3-8fa1-5fd4b18060bb","PT_Name":"2222","PT_Type":"商品","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","PT_SynType":"0"},{"GID":"ca0c4564-5a78-424f-919a-06137fbee4a9","PT_Name":"2","PT_Type":"商品","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","PT_SynType":"0"},{"GID":"d4132653-5d26-4275-bd88-6c8c15c47b64","PT_Name":"瓜","PT_Type":"商品","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","PT_SynType":"0"}]
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
         * GID : 054dbd3c-67ba-4109-9c5c-137b7f4596db
         * PT_Name : 1
         * PT_Type : 商品
         * SM_GID : 4a7b1141-8ca3-4bef-961f-ffd153357823
         * SM_Name : 默认店铺
         * PT_SynType : 0
         */

        private String GID;
        private String PT_Name;
        private String PT_Type;
        private String SM_GID;
        private String SM_Name;
        private String PT_SynType;

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getPT_Name() {
            return PT_Name;
        }

        public void setPT_Name(String PT_Name) {
            this.PT_Name = PT_Name;
        }

        public String getPT_Type() {
            return PT_Type;
        }

        public void setPT_Type(String PT_Type) {
            this.PT_Type = PT_Type;
        }

        public String getSM_GID() {
            return SM_GID;
        }

        public void setSM_GID(String SM_GID) {
            this.SM_GID = SM_GID;
        }

        public String getSM_Name() {
            return SM_Name;
        }

        public void setSM_Name(String SM_Name) {
            this.SM_Name = SM_Name;
        }

        public String getPT_SynType() {
            return PT_SynType;
        }

        public void setPT_SynType(String PT_SynType) {
            this.PT_SynType = PT_SynType;
        }
    }
}
