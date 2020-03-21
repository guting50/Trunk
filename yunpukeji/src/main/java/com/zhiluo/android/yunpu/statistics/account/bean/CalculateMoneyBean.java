package com.zhiluo.android.yunpu.statistics.account.bean;

/**
 * Created by ${YSL} on 2018-05-31.
 */

public class CalculateMoneyBean {
    private int position;
    private double money;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
