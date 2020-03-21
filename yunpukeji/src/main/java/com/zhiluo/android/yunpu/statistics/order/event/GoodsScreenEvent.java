package com.zhiluo.android.yunpu.statistics.order.event;

/**
 * 作者：罗咏哲 on 2017/9/11 11:36.
 * 邮箱：137615198@qq.com
 */

public class GoodsScreenEvent {
    private String startDate;
    private String endDate;
    private String vipCondition;
    private String order;
    private String payWayCode;
    private String device;
    private String big;
    private String small;
    private String phone;
    private String operation;
    private String gid;
    private String oilName;
    private String state;

    public String getOilName() {
        return oilName;
    }

    public void setOilName(String oilName) {
        this.oilName = oilName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOpreat() {
        return opreat;
    }

    public void setOpreat(String opreat) {
        this.opreat = opreat;
    }

    private String opreat;

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    private String opType;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getVipCondition() {
        return vipCondition;
    }

    public void setVipCondition(String vipCondition) {
        this.vipCondition = vipCondition;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getPayWayCode() {
        return payWayCode;
    }

    public void setPayWayCode(String payWayCode) {
        this.payWayCode = payWayCode;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
