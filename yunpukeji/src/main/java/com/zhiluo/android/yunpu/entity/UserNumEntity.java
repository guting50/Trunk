package com.zhiluo.android.yunpu.entity;

import java.util.List;

/**
 * 作者：罗咏哲 on 2017/11/13 15:03.
 * 邮箱：137615198@qq.com
 */

public class UserNumEntity {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"UM_CallCode":null,"UM_RegSource":null,"UM_RegSourceParam":null,"UM_RegIP":null,"GID":"8929a335-73f4-4d1b-95dd-66065c554370","UM_Acount":"137615198@qq.com","UM_Pwd":null,"UMNew_Pwd":null,"UM_Name":null,"UM_Contact":"137615198@qq.com","UM_Right":null,"UM_IsLock":0,"UM_Remark":null,"UM_IP":null,"UM_LoginTime":null,"UM_Creator":null,"UM_UpdateTime":null,"UM_IsAmin":1,"CY_GID":null,"ShopList":null,"UM_CreateTime":null,"UM_UpdateState":null,"ShopID":"fbec9c01-c91f-4041-b675-0cf574996578","SM_Name":"默认店铺","RoleName":null,"RoleID":"e6b66dd3-d5a9-49d4-a0d8-ffc5f52f0306","MenuInfoList":null,"MenuRoleList":null,"EM_GID":null,"EM_Name":null,"RM_Name":"超级管理员"}]
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
         * UM_CallCode : null
         * UM_RegSource : null
         * UM_RegSourceParam : null
         * UM_RegIP : null
         * GID : 8929a335-73f4-4d1b-95dd-66065c554370
         * UM_Acount : 137615198@qq.com
         * UM_Pwd : null
         * UMNew_Pwd : null
         * UM_Name : null
         * UM_Contact : 137615198@qq.com
         * UM_Right : null
         * UM_IsLock : 0
         * UM_Remark : null
         * UM_IP : null
         * UM_LoginTime : null
         * UM_Creator : null
         * UM_UpdateTime : null
         * UM_IsAmin : 1
         * CY_GID : null
         * ShopList : null
         * UM_CreateTime : null
         * UM_UpdateState : null
         * ShopID : fbec9c01-c91f-4041-b675-0cf574996578
         * SM_Name : 默认店铺
         * RoleName : null
         * RoleID : e6b66dd3-d5a9-49d4-a0d8-ffc5f52f0306
         * MenuInfoList : null
         * MenuRoleList : null
         * EM_GID : null
         * EM_Name : null
         * RM_Name : 超级管理员
         */

        private Object UM_CallCode;
        private Object UM_RegSource;
        private Object UM_RegSourceParam;
        private Object UM_RegIP;
        private String GID;
        private String UM_Acount;
        private Object UM_Pwd;
        private Object UMNew_Pwd;
        private Object UM_Name;
        private String UM_Contact;
        private Object UM_Right;
        private int UM_IsLock;
        private Object UM_Remark;
        private Object UM_IP;
        private Object UM_LoginTime;
        private Object UM_Creator;
        private Object UM_UpdateTime;
        private int UM_IsAmin;
        private Object CY_GID;
        private Object ShopList;
        private Object UM_CreateTime;
        private Object UM_UpdateState;
        private String ShopID;
        private String SM_Name;
        private Object RoleName;
        private String RoleID;
        private Object MenuInfoList;
        private Object MenuRoleList;
        private Object EM_GID;
        private Object EM_Name;
        private String RM_Name;

        public Object getUM_CallCode() {
            return UM_CallCode;
        }

        public void setUM_CallCode(Object UM_CallCode) {
            this.UM_CallCode = UM_CallCode;
        }

        public Object getUM_RegSource() {
            return UM_RegSource;
        }

        public void setUM_RegSource(Object UM_RegSource) {
            this.UM_RegSource = UM_RegSource;
        }

        public Object getUM_RegSourceParam() {
            return UM_RegSourceParam;
        }

        public void setUM_RegSourceParam(Object UM_RegSourceParam) {
            this.UM_RegSourceParam = UM_RegSourceParam;
        }

        public Object getUM_RegIP() {
            return UM_RegIP;
        }

        public void setUM_RegIP(Object UM_RegIP) {
            this.UM_RegIP = UM_RegIP;
        }

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getUM_Acount() {
            return UM_Acount;
        }

        public void setUM_Acount(String UM_Acount) {
            this.UM_Acount = UM_Acount;
        }

        public Object getUM_Pwd() {
            return UM_Pwd;
        }

        public void setUM_Pwd(Object UM_Pwd) {
            this.UM_Pwd = UM_Pwd;
        }

        public Object getUMNew_Pwd() {
            return UMNew_Pwd;
        }

        public void setUMNew_Pwd(Object UMNew_Pwd) {
            this.UMNew_Pwd = UMNew_Pwd;
        }

        public Object getUM_Name() {
            return UM_Name;
        }

        public void setUM_Name(Object UM_Name) {
            this.UM_Name = UM_Name;
        }

        public String getUM_Contact() {
            return UM_Contact;
        }

        public void setUM_Contact(String UM_Contact) {
            this.UM_Contact = UM_Contact;
        }

        public Object getUM_Right() {
            return UM_Right;
        }

        public void setUM_Right(Object UM_Right) {
            this.UM_Right = UM_Right;
        }

        public int getUM_IsLock() {
            return UM_IsLock;
        }

        public void setUM_IsLock(int UM_IsLock) {
            this.UM_IsLock = UM_IsLock;
        }

        public Object getUM_Remark() {
            return UM_Remark;
        }

        public void setUM_Remark(Object UM_Remark) {
            this.UM_Remark = UM_Remark;
        }

        public Object getUM_IP() {
            return UM_IP;
        }

        public void setUM_IP(Object UM_IP) {
            this.UM_IP = UM_IP;
        }

        public Object getUM_LoginTime() {
            return UM_LoginTime;
        }

        public void setUM_LoginTime(Object UM_LoginTime) {
            this.UM_LoginTime = UM_LoginTime;
        }

        public Object getUM_Creator() {
            return UM_Creator;
        }

        public void setUM_Creator(Object UM_Creator) {
            this.UM_Creator = UM_Creator;
        }

        public Object getUM_UpdateTime() {
            return UM_UpdateTime;
        }

        public void setUM_UpdateTime(Object UM_UpdateTime) {
            this.UM_UpdateTime = UM_UpdateTime;
        }

        public int getUM_IsAmin() {
            return UM_IsAmin;
        }

        public void setUM_IsAmin(int UM_IsAmin) {
            this.UM_IsAmin = UM_IsAmin;
        }

        public Object getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(Object CY_GID) {
            this.CY_GID = CY_GID;
        }

        public Object getShopList() {
            return ShopList;
        }

        public void setShopList(Object ShopList) {
            this.ShopList = ShopList;
        }

        public Object getUM_CreateTime() {
            return UM_CreateTime;
        }

        public void setUM_CreateTime(Object UM_CreateTime) {
            this.UM_CreateTime = UM_CreateTime;
        }

        public Object getUM_UpdateState() {
            return UM_UpdateState;
        }

        public void setUM_UpdateState(Object UM_UpdateState) {
            this.UM_UpdateState = UM_UpdateState;
        }

        public String getShopID() {
            return ShopID;
        }

        public void setShopID(String ShopID) {
            this.ShopID = ShopID;
        }

        public String getSM_Name() {
            return SM_Name;
        }

        public void setSM_Name(String SM_Name) {
            this.SM_Name = SM_Name;
        }

        public Object getRoleName() {
            return RoleName;
        }

        public void setRoleName(Object RoleName) {
            this.RoleName = RoleName;
        }

        public String getRoleID() {
            return RoleID;
        }

        public void setRoleID(String RoleID) {
            this.RoleID = RoleID;
        }

        public Object getMenuInfoList() {
            return MenuInfoList;
        }

        public void setMenuInfoList(Object MenuInfoList) {
            this.MenuInfoList = MenuInfoList;
        }

        public Object getMenuRoleList() {
            return MenuRoleList;
        }

        public void setMenuRoleList(Object MenuRoleList) {
            this.MenuRoleList = MenuRoleList;
        }

        public Object getEM_GID() {
            return EM_GID;
        }

        public void setEM_GID(Object EM_GID) {
            this.EM_GID = EM_GID;
        }

        public Object getEM_Name() {
            return EM_Name;
        }

        public void setEM_Name(Object EM_Name) {
            this.EM_Name = EM_Name;
        }

        public String getRM_Name() {
            return RM_Name;
        }

        public void setRM_Name(String RM_Name) {
            this.RM_Name = RM_Name;
        }
    }
}
