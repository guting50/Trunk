package com.zhiluo.android.yunpu.member.manager.bean;

/**
 * Created by YSL on 2018-06-29.
 * 筛选会员实体类
 */

public class ShaiXuanBean {
    /**
     * 会员等级
     */
    private String leve;
    /**
     * 会员标签
     */
    private String lab;
    /**
     * 会员状态
     */
    private String state;
    /**
     * 会员生日
     */
    private String birthday;
    /**
     * 开卡人员
     */
    private String opencard;

    public String getLeve() {
        return leve;
    }

    public void setLeve(String leve) {
        this.leve = leve;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getOpencard() {
        return opencard;
    }

    public void setOpencard(String opencard) {
        this.opencard = opencard;
    }
}
