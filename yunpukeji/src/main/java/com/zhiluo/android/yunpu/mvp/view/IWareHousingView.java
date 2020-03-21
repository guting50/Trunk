package com.zhiluo.android.yunpu.mvp.view;


import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.mvp.model.GoodsType;

import java.util.List;
import java.util.Map;

/**
 * 商品入库
 * 作者：罗咏哲 on 2017/10/26 11:24.
 * 邮箱：137615198@qq.com
 */

public interface IWareHousingView extends IBaseView{
    void getTypeSuccess( List<GoodsType.DataBean> parent,Map<GoodsType.DataBean, List<GoodsType.DataBean>> map);

    void getTypeFail(String result);

    void getGoodsSuccess(GoodsCheckResponseByType entity);

    void getGoodsFail(String result);

    void getComboSuccess(GoodsCheckResponseByType entity);

    void getCombofail(String result);
}
