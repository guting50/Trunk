package com.zhiluo.android.yunpu.goods.consume;

import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;

import java.util.List;

public class GoodsSpecsListEvent {

    private String msg;
    private GoodsCheckResponseByType.DataBean.DataListBean mGoodsList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public GoodsCheckResponseByType.DataBean.DataListBean getmGoodsList() {
        return mGoodsList;
    }

    public void setmGoodsList(GoodsCheckResponseByType.DataBean.DataListBean mGoodsList) {
        this.mGoodsList = mGoodsList;
    }
}
