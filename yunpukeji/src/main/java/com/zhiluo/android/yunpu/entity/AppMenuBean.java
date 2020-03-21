package com.zhiluo.android.yunpu.entity;

import java.io.Serializable;

/**
 * App菜单
 * Created by dell on 2018/2/26.
 */

public class AppMenuBean implements Serializable {
    /**
     * GID : 0000
     * AM_Name : 公共资源
     * AM_Remark : null
     * AM_UpdateTime : 2018-01-07 11:32:14
     * AM_Sort : 0
     * AR_IsCheck : null
     */
    public static final long UID = 1L;
    private String GID;
    private String AM_Name;
    private Object AM_Remark;
    private String AM_UpdateTime;
    private int AM_Sort;
    private String AR_IsCheck;
    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public String getAM_Name() {
        return AM_Name;
    }

    public void setAM_Name(String AM_Name) {
        this.AM_Name = AM_Name;
    }

    public Object getAM_Remark() {
        return AM_Remark;
    }

    public void setAM_Remark(Object AM_Remark) {
        this.AM_Remark = AM_Remark;
    }

    public String getAM_UpdateTime() {
        return AM_UpdateTime;
    }

    public void setAM_UpdateTime(String AM_UpdateTime) {
        this.AM_UpdateTime = AM_UpdateTime;
    }

    public int getAM_Sort() {
        return AM_Sort;
    }

    public void setAM_Sort(int AM_Sort) {
        this.AM_Sort = AM_Sort;
    }

    public String getAR_IsCheck() {
        return AR_IsCheck;
    }

    public void setAR_IsCheck(String AR_IsCheck) {
        this.AR_IsCheck = AR_IsCheck;
    }
}
