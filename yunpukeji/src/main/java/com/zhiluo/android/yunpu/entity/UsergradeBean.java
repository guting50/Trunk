package com.zhiluo.android.yunpu.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/9/22.
 */
public class UsergradeBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"VIP_Remark":"","GID":"28ed6e06-ec4d-4c05-9db4-f0b32bf9f3d5","CY_GID":null,"US_GID":null,"US_Value":100,"VG_Name":"黄金会员","VG_Code":null,"US_Name":"积分达到","US_Code":"IntegerFulfil","PT_Name":null,"RS_Value":0,"DS_Value":100,"VS_Value":1,"VIP_Creator":null,"Settings":[{"GID":"28ed6e06-ec4d-4c05-9db4-f0b32bf9f3d5","PT_ID":"0d95736b-c4f1-4850-9dc7-40fe76cd3778","PD_Discount":"100","VS_Number":"1.00","PT_Name":"s","CY_GID":null,"SM_Name":"尹尚勇的店铺","PTD_Type":"0","SM_ID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6"},{"GID":"28ed6e06-ec4d-4c05-9db4-f0b32bf9f3d5","PT_ID":"4db86e86-f15b-4ea5-8156-e670728fd2f1","PD_Discount":"100","VS_Number":"1.00","PT_Name":"xinxin","CY_GID":null,"SM_Name":"尹尚勇的店铺","PTD_Type":"1","SM_ID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6"}],"VG_IsLock":null},{"VIP_Remark":null,"GID":"43b0789e-1026-450f-8f57-9222efb03bce","CY_GID":null,"US_GID":null,"US_Value":0,"VG_Name":"默认等级","VG_Code":"默认等级","US_Name":"积分达到","US_Code":"IntegerFulfil","PT_Name":null,"RS_Value":0,"DS_Value":100,"VS_Value":0,"VIP_Creator":null,"Settings":[],"VG_IsLock":null}]
     */

    private boolean success;
    private Object code;
    private String msg;
    /**
     * VIP_Remark :
     * GID : 28ed6e06-ec4d-4c05-9db4-f0b32bf9f3d5
     * CY_GID : null
     * US_GID : null
     * US_Value : 100
     * VG_Name : 黄金会员
     * VG_Code : null
     * US_Name : 积分达到
     * US_Code : IntegerFulfil
     * PT_Name : null
     * RS_Value : 0
     * DS_Value : 100
     * VS_Value : 1
     * VIP_Creator : null
     * Settings : [{"GID":"28ed6e06-ec4d-4c05-9db4-f0b32bf9f3d5","PT_ID":"0d95736b-c4f1-4850-9dc7-40fe76cd3778","PD_Discount":"100","VS_Number":"1.00","PT_Name":"s","CY_GID":null,"SM_Name":"尹尚勇的店铺","PTD_Type":"0","SM_ID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6"},{"GID":"28ed6e06-ec4d-4c05-9db4-f0b32bf9f3d5","PT_ID":"4db86e86-f15b-4ea5-8156-e670728fd2f1","PD_Discount":"100","VS_Number":"1.00","PT_Name":"xinxin","CY_GID":null,"SM_Name":"尹尚勇的店铺","PTD_Type":"1","SM_ID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6"}]
     * VG_IsLock : null
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
        private String VIP_Remark;
        private String GID;
        private Object CY_GID;
        private Object US_GID;
        private double US_Value;
        private String VG_Name;
        private Object VG_Code;
        private String US_Name;
        private String US_Code;
        private Object PT_Name;
        private double RS_Value;
        private double DS_Value;
        private double VS_Value;
        private Object VIP_Creator;
        private Object VG_IsLock;
        /**
         * GID : 28ed6e06-ec4d-4c05-9db4-f0b32bf9f3d5
         * PT_ID : 0d95736b-c4f1-4850-9dc7-40fe76cd3778
         * PD_Discount : 100
         * VS_Number : 1.00
         * PT_Name : s
         * CY_GID : null
         * SM_Name : 尹尚勇的店铺
         * PTD_Type : 0
         * SM_ID : a07d8272-4c2e-4590-aca6-e92abc46e1a6
         */

        private List<SettingsBean> Settings;

        public String getVIP_Remark() {
            return VIP_Remark;
        }

        public void setVIP_Remark(String VIP_Remark) {
            this.VIP_Remark = VIP_Remark;
        }

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public Object getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(Object CY_GID) {
            this.CY_GID = CY_GID;
        }

        public Object getUS_GID() {
            return US_GID;
        }

        public void setUS_GID(Object US_GID) {
            this.US_GID = US_GID;
        }

        public double getUS_Value() {
            return US_Value;
        }

        public void setUS_Value(int US_Value) {
            this.US_Value = US_Value;
        }

        public String getVG_Name() {
            return VG_Name;
        }

        public void setVG_Name(String VG_Name) {
            this.VG_Name = VG_Name;
        }

        public Object getVG_Code() {
            return VG_Code;
        }

        public void setVG_Code(Object VG_Code) {
            this.VG_Code = VG_Code;
        }

        public String getUS_Name() {
            return US_Name;
        }

        public void setUS_Name(String US_Name) {
            this.US_Name = US_Name;
        }

        public String getUS_Code() {
            return US_Code;
        }

        public void setUS_Code(String US_Code) {
            this.US_Code = US_Code;
        }

        public Object getPT_Name() {
            return PT_Name;
        }

        public void setPT_Name(Object PT_Name) {
            this.PT_Name = PT_Name;
        }

        public double getRS_Value() {
            return RS_Value;
        }

        public void setRS_Value(int RS_Value) {
            this.RS_Value = RS_Value;
        }

        public double getDS_Value() {
            return DS_Value;
        }

        public void setDS_Value(int DS_Value) {
            this.DS_Value = DS_Value;
        }

        public double getVS_Value() {
            return VS_Value;
        }

        public void setVS_Value(int VS_Value) {
            this.VS_Value = VS_Value;
        }

        public Object getVIP_Creator() {
            return VIP_Creator;
        }

        public void setVIP_Creator(Object VIP_Creator) {
            this.VIP_Creator = VIP_Creator;
        }

        public Object getVG_IsLock() {
            return VG_IsLock;
        }

        public void setVG_IsLock(Object VG_IsLock) {
            this.VG_IsLock = VG_IsLock;
        }

        public List<SettingsBean> getSettings() {
            return Settings;
        }

        public void setSettings(List<SettingsBean> Settings) {
            this.Settings = Settings;
        }

        public static class SettingsBean {
            private String GID;
            private String PT_ID;
            private String PD_Discount;
            private String VS_Number;
            private String PT_Name;
            private Object CY_GID;
            private String SM_Name;
            private String PTD_Type;
            private String SM_ID;

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public String getPT_ID() {
                return PT_ID;
            }

            public void setPT_ID(String PT_ID) {
                this.PT_ID = PT_ID;
            }

            public String getPD_Discount() {
                return PD_Discount;
            }

            public void setPD_Discount(String PD_Discount) {
                this.PD_Discount = PD_Discount;
            }

            public String getVS_Number() {
                return VS_Number;
            }

            public void setVS_Number(String VS_Number) {
                this.VS_Number = VS_Number;
            }

            public String getPT_Name() {
                return PT_Name;
            }

            public void setPT_Name(String PT_Name) {
                this.PT_Name = PT_Name;
            }

            public Object getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(Object CY_GID) {
                this.CY_GID = CY_GID;
            }

            public String getSM_Name() {
                return SM_Name;
            }

            public void setSM_Name(String SM_Name) {
                this.SM_Name = SM_Name;
            }

            public String getPTD_Type() {
                return PTD_Type;
            }

            public void setPTD_Type(String PTD_Type) {
                this.PTD_Type = PTD_Type;
            }

            public String getSM_ID() {
                return SM_ID;
            }

            public void setSM_ID(String SM_ID) {
                this.SM_ID = SM_ID;
            }
        }
    }
}
