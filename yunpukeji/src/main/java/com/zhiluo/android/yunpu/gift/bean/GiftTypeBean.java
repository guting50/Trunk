package com.zhiluo.android.yunpu.gift.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class GiftTypeBean {
    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":"9f678590-4aa9-464e-b4cc-2189d817d099","GT_Name":"ssss","GT_Creator":"yinshangyong93@163.com","GT_CreateTime":"2016-10-07 10:37:06","GT_Remark":"","CY_GID":null,"GT_IsModify":0,"GT_SynType":0}]
     */

    private boolean success;
    private Object code;
    private String msg;
    /**
     * GID : 9f678590-4aa9-464e-b4cc-2189d817d099
     * GT_Name : ssss
     * GT_Creator : yinshangyong93@163.com
     * GT_CreateTime : 2016-10-07 10:37:06
     * GT_Remark :
     * CY_GID : null
     * GT_IsModify : 0
     * GT_SynType : 0
     */

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
        private String GID;
        private String GT_Name;
        private String GT_Creator;
        private String GT_CreateTime;
        private String GT_Remark;
        private Object CY_GID;
        private int GT_IsModify;
        private int GT_SynType;

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getGT_Name() {
            return GT_Name;
        }

        public void setGT_Name(String GT_Name) {
            this.GT_Name = GT_Name;
        }

        public String getGT_Creator() {
            return GT_Creator;
        }

        public void setGT_Creator(String GT_Creator) {
            this.GT_Creator = GT_Creator;
        }

        public String getGT_CreateTime() {
            return GT_CreateTime;
        }

        public void setGT_CreateTime(String GT_CreateTime) {
            this.GT_CreateTime = GT_CreateTime;
        }

        public String getGT_Remark() {
            return GT_Remark;
        }

        public void setGT_Remark(String GT_Remark) {
            this.GT_Remark = GT_Remark;
        }

        public Object getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(Object CY_GID) {
            this.CY_GID = CY_GID;
        }

        public int getGT_IsModify() {
            return GT_IsModify;
        }

        public void setGT_IsModify(int GT_IsModify) {
            this.GT_IsModify = GT_IsModify;
        }

        public int getGT_SynType() {
            return GT_SynType;
        }

        public void setGT_SynType(int GT_SynType) {
            this.GT_SynType = GT_SynType;
        }
    }
}
