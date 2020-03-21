package com.zhiluo.android.yunpu.mvp.view;

import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.goods.manager.bean.ProviderBean;

import java.util.List;

/**
 * 作者：罗咏哲 on 2017/10/26 16:40.
 * 邮箱：137615198@qq.com
 */

public interface IWareHousingConfirmView extends IBaseView {
    void init(String order, double total, List<GoodsCheckResponseByType.DataBean.DataListBean> mGoodList);

    void getProviderSuccess(ProviderBean bean);

    void getProviderFail(String result);

    void inputStockSuccess();

    void inputStockFail(String result);
}
