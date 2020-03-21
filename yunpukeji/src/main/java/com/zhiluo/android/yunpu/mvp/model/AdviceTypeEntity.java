package com.zhiluo.android.yunpu.mvp.model;

import java.util.List;

/**
 * 作者：罗咏哲 on 2017/10/28 10:35.
 * 邮箱：137615198@qq.com
 */

public class AdviceTypeEntity {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"QT_GID":"3bf45b91-89a7-42f4-8ec1-b6070cddfef2","QT_Name":"我想有这个功能","QT_Remark":"我想有这个功能","QT_CreateTime":"2016-03-22 15:33:58","QT_Creator":"b3b63c18-f327-4804-af12-c61503281126"},{"QT_GID":"3fbf5427-035d-4c14-85e9-e29a442111d0","QT_Name":"功能不会使用","QT_Remark":"","QT_CreateTime":"2016-01-30 17:51:19","QT_Creator":"f5cc75b6-7545-4c6f-a684-e7ababbb0b45"},{"QT_GID":"493eeb93-f657-4987-859f-af3298c7f9e2","QT_Name":"功能或页面打不开","QT_Remark":"","QT_CreateTime":"2016-01-30 17:51:18","QT_Creator":"f5cc75b6-7545-4c6f-a684-e7ababbb0b45"},{"QT_GID":"945c6010-892c-41bd-a456-54d47e1c9ab6","QT_Name":"其他问题","QT_Remark":"","QT_CreateTime":"2016-01-30 17:51:16","QT_Creator":"f5cc75b6-7545-4c6f-a684-e7ababbb0b45"},{"QT_GID":"d7424f2e-1f0b-433c-a554-4168912832af","QT_Name":"系统出现错误","QT_Remark":"","QT_CreateTime":"2016-01-30 17:51:17","QT_Creator":"f5cc75b6-7545-4c6f-a684-e7ababbb0b45"}]
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
         * QT_GID : 3bf45b91-89a7-42f4-8ec1-b6070cddfef2
         * QT_Name : 我想有这个功能
         * QT_Remark : 我想有这个功能
         * QT_CreateTime : 2016-03-22 15:33:58
         * QT_Creator : b3b63c18-f327-4804-af12-c61503281126
         */

        private String QT_GID;
        private String QT_Name;
        private String QT_Remark;
        private String QT_CreateTime;
        private String QT_Creator;

        public String getQT_GID() {
            return QT_GID;
        }

        public void setQT_GID(String QT_GID) {
            this.QT_GID = QT_GID;
        }

        public String getQT_Name() {
            return QT_Name;
        }

        public void setQT_Name(String QT_Name) {
            this.QT_Name = QT_Name;
        }

        public String getQT_Remark() {
            return QT_Remark;
        }

        public void setQT_Remark(String QT_Remark) {
            this.QT_Remark = QT_Remark;
        }

        public String getQT_CreateTime() {
            return QT_CreateTime;
        }

        public void setQT_CreateTime(String QT_CreateTime) {
            this.QT_CreateTime = QT_CreateTime;
        }

        public String getQT_Creator() {
            return QT_Creator;
        }

        public void setQT_Creator(String QT_Creator) {
            this.QT_Creator = QT_Creator;
        }
    }
}
