package com.zhiluo.android.yunpu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 角色
 * Created by dell on 2018/2/26.
 */

public class RoleBean implements Serializable {
    public static final long UID = 1L;
    private int RM_IsAdmin;
    private String GID;
    private String RM_Name;
    private String RM_Remark;
    private String RM_Creator;
    private String RM_UpdateTime;
    private Object CY_GID;
    private List<String> RR_GIDList;
    private List<String> RS_GIDList;
    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getRM_IsAdmin() {
        return RM_IsAdmin;
    }

    public void setRM_IsAdmin(int RM_IsAdmin) {
        this.RM_IsAdmin = RM_IsAdmin;
    }

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public String getRM_Name() {
        return RM_Name;
    }

    public void setRM_Name(String RM_Name) {
        this.RM_Name = RM_Name;
    }

    public String getRM_Remark() {
        return RM_Remark;
    }

    public void setRM_Remark(String RM_Remark) {
        this.RM_Remark = RM_Remark;
    }

    public String getRM_Creator() {
        return RM_Creator;
    }

    public void setRM_Creator(String RM_Creator) {
        this.RM_Creator = RM_Creator;
    }

    public String getRM_UpdateTime() {
        return RM_UpdateTime;
    }

    public void setRM_UpdateTime(String RM_UpdateTime) {
        this.RM_UpdateTime = RM_UpdateTime;
    }

    public Object getCY_GID() {
        return CY_GID;
    }

    public void setCY_GID(Object CY_GID) {
        this.CY_GID = CY_GID;
    }

    public List<String> getRR_GIDList() {
        return RR_GIDList;
    }

    public void setRR_GIDList(List<String> RR_GIDList) {
        this.RR_GIDList = RR_GIDList;
    }

    public List<String> getRS_GIDList() {
        return RS_GIDList;
    }

    public void setRS_GIDList(List<String> RS_GIDList) {
        this.RS_GIDList = RS_GIDList;
    }
}
