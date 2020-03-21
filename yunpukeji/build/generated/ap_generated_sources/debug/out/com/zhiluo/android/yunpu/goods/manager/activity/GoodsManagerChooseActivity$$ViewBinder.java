// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.goods.manager.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GoodsManagerChooseActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.goods.manager.activity.GoodsManagerChooseActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298884, "field 'ScreenBack'");
    target.ScreenBack = finder.castView(view, 2131298884, "field 'ScreenBack'");
    view = finder.findRequiredView(source, 2131298885, "field 'ScreenConfirm'");
    target.ScreenConfirm = finder.castView(view, 2131298885, "field 'ScreenConfirm'");
    view = finder.findRequiredView(source, 2131298059, "field 'spGoodsOrderType'");
    target.spGoodsOrderType = finder.castView(view, 2131298059, "field 'spGoodsOrderType'");
    view = finder.findRequiredView(source, 2131296787, "field 'etConsumeDayu'");
    target.etConsumeDayu = finder.castView(view, 2131296787, "field 'etConsumeDayu'");
    view = finder.findRequiredView(source, 2131296788, "field 'etConsumeXiaoyu'");
    target.etConsumeXiaoyu = finder.castView(view, 2131296788, "field 'etConsumeXiaoyu'");
    view = finder.findRequiredView(source, 2131296422, "field 'ScreenClean'");
    target.ScreenClean = finder.castView(view, 2131296422, "field 'ScreenClean'");
    view = finder.findRequiredView(source, 2131298057, "field 'spGoodsOrder'");
    target.spGoodsOrder = finder.castView(view, 2131298057, "field 'spGoodsOrder'");
  }

  @Override public void unbind(T target) {
    target.ScreenBack = null;
    target.ScreenConfirm = null;
    target.spGoodsOrderType = null;
    target.etConsumeDayu = null;
    target.etConsumeXiaoyu = null;
    target.ScreenClean = null;
    target.spGoodsOrder = null;
  }
}
