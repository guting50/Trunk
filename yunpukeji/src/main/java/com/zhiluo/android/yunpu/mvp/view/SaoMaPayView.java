package com.zhiluo.android.yunpu.mvp.view;

import com.zhiluo.android.yunpu.goods.manager.bean.BarCodePayBean;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.goods.manager.bean.QuerPayBean;

/**
 * Created by YSL on 2018-10-31.
 */

public interface SaoMaPayView extends IBaseView {

    void barCodeSuccess(BarCodePayBean entity);

    void barCodeFail(BarCodePayBean entity);

    void barCodeFailString(String responseString);
    void querPaySuccess(QuerPayBean entity);

    void querPayFail(QuerPayBean entity);
}
