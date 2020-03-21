package com.zhiluo.android.yunpu.entity;

/**
 * 编辑用户成功返回
 * Created by dell on 2018/2/27.
 */

public class EditUserBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"UM_ThirdPartyOpenID":null,"UM_Unionid":null,"UM_OpenID":null,"UM_CallCode":null,"UM_RegSource":null,"UM_RegSourceParam":null,"UM_RegIP":null,"GID":"70019dae-7c42-4222-b9b2-12328682d6c3","UM_Acount":"18328334522","UM_Pwd":"123456","UM_Name":"分店管理","UM_Contact":"","UM_Right":null,"UM_IsLock":0,"UM_Remark":"","UM_IP":"118.112.73.122","UM_LoginTime":null,"UM_Creator":"feng","UM_UpdateTime":"2018-02-27 11:28:02","UM_IsAmin":0,"CY_GID":"61ec52b1-6da8-415a-b66a-9cec063bda5d","ShopList":null,"UM_CreateTime":"2018-01-03 11:18:03","UM_UpdateState":1,"ShopID":"a75cbe54-a1c2-44a1-a0bc-274b2771b9ba","SM_Name":"总店","RoleName":null,"RoleID":"52d90b76-84da-4f9b-8cfa-c5e5c9f42a77","MenuInfoList":null,"MenuResourceList":null,"EM_GID":null,"EM_Name":null,"RM_Name":"分店管理员"}
     */

    private boolean success;
    private Object code;
    private String msg;
    private UserBean data;

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

    public UserBean getData() {
        return data;
    }

    public void setData(UserBean data) {
        this.data = data;
    }

}
