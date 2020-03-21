package com.zhiluo.android.yunpu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 用户列表
 * Created by dell on 2018/2/26.
 */

public class UserListBean implements Serializable {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"UM_ThirdPartyOpenID":null,"UM_Unionid":null,"UM_OpenID":null,"UM_CallCode":null,"UM_RegSource":null,"UM_RegSourceParam":null,"UM_RegIP":null,"GID":"705feb1b-dfe8-49fb-ac0d-06fe9aa47148","UM_Acount":"18328334521","UM_Pwd":null,"UM_Name":"feng","UM_Contact":"18242321639","UM_Right":null,"UM_IsLock":0,"UM_Remark":null,"UM_IP":null,"UM_LoginTime":null,"UM_Creator":null,"UM_UpdateTime":null,"UM_IsAmin":1,"CY_GID":null,"ShopList":null,"UM_CreateTime":null,"UM_UpdateState":null,"ShopID":"a75cbe54-a1c2-44a1-a0bc-274b2771b9ba","SM_Name":"总店","RoleName":null,"RoleID":"56122492-be65-4119-87a4-88c0223e0a37","MenuInfoList":null,"MenuResourceList":null,"EM_GID":null,"EM_Name":null,"RM_Name":"超级管理员"},{"UM_ThirdPartyOpenID":null,"UM_Unionid":null,"UM_OpenID":null,"UM_CallCode":null,"UM_RegSource":null,"UM_RegSourceParam":null,"UM_RegIP":null,"GID":"70019dae-7c42-4222-b9b2-12328682d6c3","UM_Acount":"18328334522","UM_Pwd":null,"UM_Name":"分店管理","UM_Contact":"","UM_Right":null,"UM_IsLock":0,"UM_Remark":"","UM_IP":null,"UM_LoginTime":null,"UM_Creator":null,"UM_UpdateTime":null,"UM_IsAmin":0,"CY_GID":null,"ShopList":null,"UM_CreateTime":null,"UM_UpdateState":null,"ShopID":"a75cbe54-a1c2-44a1-a0bc-274b2771b9ba","SM_Name":"总店","RoleName":null,"RoleID":"52d90b76-84da-4f9b-8cfa-c5e5c9f42a77","MenuInfoList":null,"MenuResourceList":null,"EM_GID":null,"EM_Name":null,"RM_Name":"分店管理员"},{"UM_ThirdPartyOpenID":null,"UM_Unionid":null,"UM_OpenID":null,"UM_CallCode":null,"UM_RegSource":null,"UM_RegSourceParam":null,"UM_RegIP":null,"GID":"c5b02727-acca-4ecb-b315-7ef1ffb42988","UM_Acount":"18328334523","UM_Pwd":null,"UM_Name":"111222","UM_Contact":"","UM_Right":null,"UM_IsLock":0,"UM_Remark":"","UM_IP":null,"UM_LoginTime":null,"UM_Creator":null,"UM_UpdateTime":null,"UM_IsAmin":0,"CY_GID":null,"ShopList":null,"UM_CreateTime":null,"UM_UpdateState":null,"ShopID":"a75cbe54-a1c2-44a1-a0bc-274b2771b9ba","SM_Name":"总店","RoleName":null,"RoleID":"36721ada-ae7a-4c9c-b5ea-eace78dc238a","MenuInfoList":null,"MenuResourceList":null,"EM_GID":null,"EM_Name":null,"RM_Name":"app权限测试"}]
     */
    public static final long UID = 1L;
    private boolean success;
    private Object code;
    private String msg;
    private List<UserBean> data;

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

    public List<UserBean> getData() {
        return data;
    }

    public void setData(List<UserBean> data) {
        this.data = data;
    }


}
