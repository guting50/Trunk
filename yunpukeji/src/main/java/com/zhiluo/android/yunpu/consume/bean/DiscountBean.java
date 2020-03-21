package com.zhiluo.android.yunpu.consume.bean;

/**
 * 优惠活动bean
 * 作者：罗咏哲 on 2017/7/12 14:35.
 * 邮箱：137615198@qq.com
 */

public class DiscountBean {
    /**
     * 折扣金额
     */
    private double discountMoney;
    /**
     * 赠送金额
     */
    private double giveMoney;
    /**
     * 赠送积分
     */
    private double givePoint;

    public double getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(double discountMoney) {
        this.discountMoney = discountMoney;
    }

    public double getGiveMoney() {
        return giveMoney;
    }

    public void setGiveMoney(double giveMoney) {
        this.giveMoney = giveMoney;
    }

    public double getGivePoint() {
        return givePoint;
    }

    public void setGivePoint(double givePoint) {
        this.givePoint = givePoint;
    }

}
