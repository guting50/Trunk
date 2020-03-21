package com.zhiluo.android.yunpu.entity;

import java.io.Serializable;

/**
 * 编辑员工成功返回
 * Created by dell on 2018/2/26.
 */

public class EditStaffBean implements Serializable {
    public static final long UID = 1L;

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"GID":"6b060dc1-f488-49ed-b176-3c43c389450b","SM_GID":"","CY_GID":null,"DM_GID":"df5e1a29-a050-4313-9df3-074680da9fcb","EM_Code":"001","EM_Name":"小刘","EM_Sex":0,"EM_Phone":"18839929299","EM_Addr":"好的基督教","EM_Remark":"ddd","EM_UpdateTime":"2018-02-26 09:56:23","EM_Creator":"137615198@qq.com","EM_Birthday":null,"SM_Name":null,"DM_Name":"前台","EM_TipCard":1,"EM_TipRecharge":1,"EM_TipChargeTime":1,"EM_TipGoodsConsume":1,"EM_TipFastConsume":1,"EM_TipTimesConsume":1,"EM_TipComboConsume":0}
     */

    private boolean success;
    private Object code;
    private String msg;
    private StaffBean data;

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

    public StaffBean getData() {
        return data;
    }

    public void setData(StaffBean data) {
        this.data = data;
    }


}
