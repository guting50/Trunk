package com.zhiluo.android.yunpu.goods.manager.bean;

import org.litepal.crud.DataSupport;

/**
 * 存购物车商品到LitePal数据库
 */
public class GoodsDataBean extends DataSupport {

    private int num;
    private String gid;
    private String groupId;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
