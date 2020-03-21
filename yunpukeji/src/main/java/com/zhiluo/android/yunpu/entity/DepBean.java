package com.zhiluo.android.yunpu.entity;

import java.io.Serializable;

/**
 * 部门
 * Created by dell on 2018/2/25.
 */

public class DepBean implements Serializable {
    public static final long UID = 1L;
    /**
     * GID : df5e1a29-a050-4313-9df3-074680da9fcb
     * CY_GID : null
     * DM_Name : 前台
     * DM_Remark :
     * DM_UpdateTime : 2017-07-06 17:11:43
     * DM_Creator : 137615198@qq.com
     */

    private String GID;
    private Object CY_GID;
    private String DM_Name;
    private String DM_Remark;
    private String DM_UpdateTime;
    private String DM_Creator;
    private boolean checked;

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

    public String getDM_Name() {
        return DM_Name;
    }

    public void setDM_Name(String DM_Name) {
        this.DM_Name = DM_Name;
    }

    public String getDM_Remark() {
        return DM_Remark;
    }

    public void setDM_Remark(String DM_Remark) {
        this.DM_Remark = DM_Remark;
    }

    public String getDM_UpdateTime() {
        return DM_UpdateTime;
    }

    public void setDM_UpdateTime(String DM_UpdateTime) {
        this.DM_UpdateTime = DM_UpdateTime;
    }

    public String getDM_Creator() {
        return DM_Creator;
    }

    public void setDM_Creator(String DM_Creator) {
        this.DM_Creator = DM_Creator;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
