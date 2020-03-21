package com.zhiluo.android.yunpu.member.manager.bean;

import java.io.Serializable;

/**
 * Created by ${YSL} on 2018-04-26.
 */

public class MonthBean implements Serializable{
    private String whichone;
    private boolean isChecked;

    public String getWhichone() {
        return whichone;
    }

    public void setWhichone(String whichone) {
        this.whichone = whichone;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
