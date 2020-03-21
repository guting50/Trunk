package com.zhiluo.android.yunpu.consume.bean;

/**
 * 支付方式Javabean
 * 作者：罗咏哲 on 2017/7/12 15:54.
 * 邮箱：137615198@qq.com
 */

public class PayWayBean {
    /**
     * 支付名称
     */
    private String payName;
    /**
     * 支付代码
     */
    private String payCode;
    /**
     * 支付金额
     */
    private String payMoney;
    /**
     *
     */
    private String[] GID;

    /**
     * 支付的积分
     */
    private double payPoint;

    public double getPayPoint() {
        return payPoint;
    }

    public void setPayPoint(double payPoint) {
        this.payPoint = payPoint;
    }

    public String[] getGID() {
        return GID;
    }

    public void setGID(String[] GID) {
        this.GID = GID;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }
}
