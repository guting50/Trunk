package com.zhiluo.android.yunpu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 部门列表
 * Created by dell on 2018/2/25.
 */

public class DepListBean implements Serializable {
    public static final long UID = 1L;
    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":"df5e1a29-a050-4313-9df3-074680da9fcb","CY_GID":null,"DM_Name":"前台","DM_Remark":"","DM_UpdateTime":"2017-07-06 17:11:43","DM_Creator":"137615198@qq.com"},{"GID":"8e0468c9-eff7-4013-960c-85328afaa934","CY_GID":null,"DM_Name":"导购","DM_Remark":"","DM_UpdateTime":"2017-07-06 17:11:49","DM_Creator":"137615198@qq.com"}]
     */

    private boolean success;
    private Object code;
    private String msg;
    private List<DepBean> data;

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

    public List<DepBean> getData() {
        return data;
    }

    public void setData(List<DepBean> data) {
        this.data = data;
    }
}
