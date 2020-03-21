package com.zhiluo.android.yunpu.member.manager.bean;

/**
 * Created by ${YSL} on 2018-05-02.
 */

public class ConditionBean {
    private String Condition;
    private boolean checked;
    private String GID;

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean check) {
        this.checked = check;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }


}
