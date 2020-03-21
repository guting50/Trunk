package com.zhiluo.android.yunpu.goods.manager.bean;

/**
 * 提交库存盘点
 * 作者：罗咏哲 on 2017/9/29 15:34.
 * 邮箱：137615198@qq.com
 */

public class StockBean {
    private double num;
    private String remark;
    private String gid;

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }
}
