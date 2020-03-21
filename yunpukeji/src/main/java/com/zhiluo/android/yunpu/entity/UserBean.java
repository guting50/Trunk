package com.zhiluo.android.yunpu.entity;

import java.io.Serializable;

/**
 * 用户
 * Created by dell on 2018/2/26.
 */

public class UserBean implements Serializable {
    public static final long UID = 1L;
    private Object UM_ThirdPartyOpenID;
    private Object UM_Unionid;
    private Object UM_OpenID;
    private Object UM_CallCode;
    private Object UM_RegSource;
    private Object UM_RegSourceParam;
    private Object UM_RegIP;
    private String GID;
    private String UM_Acount;
    private String UM_Pwd;
    private String UM_Name;
    private String UM_Contact;
    private Object UM_Right;
    private int UM_IsLock;
    private String UM_Remark;
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
    private String RoleName;
    private String RoleID;
    private Object MenuInfoList;
    private Object MenuResourceList;
    private Object EM_GID;
    private Object EM_Name;
    private String RM_Name;
    private String UM_ChatHead;
    private boolean checked;



    public String getUM_ChatHead() {
        return UM_ChatHead;
    }

    public void setUM_ChatHead(String UM_ChatHead) {
        this.UM_ChatHead = UM_ChatHead;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Object getUM_ThirdPartyOpenID() {
        return UM_ThirdPartyOpenID;
    }

    public void setUM_ThirdPartyOpenID(Object UM_ThirdPartyOpenID) {
        this.UM_ThirdPartyOpenID = UM_ThirdPartyOpenID;
    }

    public Object getUM_Unionid() {
        return UM_Unionid;
    }

    public void setUM_Unionid(Object UM_Unionid) {
        this.UM_Unionid = UM_Unionid;
    }

    public Object getUM_OpenID() {
        return UM_OpenID;
    }

    public void setUM_OpenID(Object UM_OpenID) {
        this.UM_OpenID = UM_OpenID;
    }

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

    public String getUM_Pwd() {
        return UM_Pwd;
    }

    public void setUM_Pwd(String UM_Pwd) {
        this.UM_Pwd = UM_Pwd;
    }

    public String getUM_Name() {
        return UM_Name;
    }

    public void setUM_Name(String UM_Name) {
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

    public String getUM_Remark() {
        return UM_Remark;
    }

    public void setUM_Remark(String UM_Remark) {
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

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String RoleName) {
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

    public Object getMenuResourceList() {
        return MenuResourceList;
    }

    public void setMenuResourceList(Object MenuResourceList) {
        this.MenuResourceList = MenuResourceList;
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
