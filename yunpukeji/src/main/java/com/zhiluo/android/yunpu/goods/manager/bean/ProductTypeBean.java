package com.zhiluo.android.yunpu.goods.manager.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */
public class ProductTypeBean {
    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"SM_ID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","SM_Name":"尹尚勇的店铺","GID":"0a1a885a-a7ec-4d4a-8669-a42841656bc8","PT_Name":"saw","PT_Parent":"e85a8bc7-26b3-4ebc-9161-a6afd10d459d","PT_Remark":"","PT_Creator":"yinshangyong93@163.com","PT_UpdateTime":"2016-10-10 10:10:51","CY_GID":null,"PT_SynType":0},{"SM_ID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","SM_Name":"尹尚勇的店铺","GID":"0d95736b-c4f1-4850-9dc7-40fe76cd3778","PT_Name":"s","PT_Parent":"","PT_Remark":"s","PT_Creator":"yinshangyong93@163.com","PT_UpdateTime":"2016-09-07 17:07:02","CY_GID":null,"PT_SynType":0},{"SM_ID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","SM_Name":"尹尚勇的店铺","GID":"402556fa-c113-4ea9-b586-68634f103235","PT_Name":"yin","PT_Parent":"e85a8bc7-26b3-4ebc-9161-a6afd10d459d","PT_Remark":"","PT_Creator":"yinshangyong93@163.com","PT_UpdateTime":"2016-10-10 10:27:39","CY_GID":null,"PT_SynType":0},{"SM_ID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","SM_Name":"尹尚勇的店铺","GID":"638380cc-e356-4d36-bbd0-db0e1e05c552","PT_Name":"azx","PT_Parent":"e85a8bc7-26b3-4ebc-9161-a6afd10d459d","PT_Remark":"","PT_Creator":"yinshangyong93@163.com","PT_UpdateTime":"2016-10-10 10:29:20","CY_GID":null,"PT_SynType":0},{"SM_ID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","SM_Name":"尹尚勇的店铺","GID":"e85a8bc7-26b3-4ebc-9161-a6afd10d459d","PT_Name":"食品","PT_Parent":"","PT_Remark":"","PT_Creator":"yinshangyong93@163.com","PT_UpdateTime":"2016-10-07 10:35:11","CY_GID":null,"PT_SynType":0}]
     */

    private boolean success;
    private Object code;
    private String msg;
    /**
     * SM_ID : a07d8272-4c2e-4590-aca6-e92abc46e1a6
     * SM_Name : 尹尚勇的店铺
     * GID : 0a1a885a-a7ec-4d4a-8669-a42841656bc8
     * PT_Name : saw
     * PT_Parent : e85a8bc7-26b3-4ebc-9161-a6afd10d459d
     * PT_Remark :
     * PT_Creator : yinshangyong93@163.com
     * PT_UpdateTime : 2016-10-10 10:10:51
     * CY_GID : null
     * PT_SynType : 0
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
        private String SM_ID;
        private String SM_Name;
        private String GID;
        private String PT_Name;
        private String PT_Parent;
        private String PT_Remark;
        private String PT_Creator;
        private String PT_UpdateTime;
        private Object CY_GID;
        private int PT_SynType;

        public String getSM_ID() {
            return SM_ID;
        }

        public void setSM_ID(String SM_ID) {
            this.SM_ID = SM_ID;
        }

        public String getSM_Name() {
            return SM_Name;
        }

        public void setSM_Name(String SM_Name) {
            this.SM_Name = SM_Name;
        }

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

        public String getPT_Parent() {
            return PT_Parent;
        }

        public void setPT_Parent(String PT_Parent) {
            this.PT_Parent = PT_Parent;
        }

        public String getPT_Remark() {
            return PT_Remark;
        }

        public void setPT_Remark(String PT_Remark) {
            this.PT_Remark = PT_Remark;
        }

        public String getPT_Creator() {
            return PT_Creator;
        }

        public void setPT_Creator(String PT_Creator) {
            this.PT_Creator = PT_Creator;
        }

        public String getPT_UpdateTime() {
            return PT_UpdateTime;
        }

        public void setPT_UpdateTime(String PT_UpdateTime) {
            this.PT_UpdateTime = PT_UpdateTime;
        }

        public Object getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(Object CY_GID) {
            this.CY_GID = CY_GID;
        }

        public int getPT_SynType() {
            return PT_SynType;
        }

        public void setPT_SynType(int PT_SynType) {
            this.PT_SynType = PT_SynType;
        }
    }
}
