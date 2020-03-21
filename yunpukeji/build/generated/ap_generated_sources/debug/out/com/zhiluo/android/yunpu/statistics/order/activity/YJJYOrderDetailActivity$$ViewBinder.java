// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.statistics.order.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class YJJYOrderDetailActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.statistics.order.activity.YJJYOrderDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298745, "field 'tvNoConfirmBack'");
    target.tvNoConfirmBack = finder.castView(view, 2131298745, "field 'tvNoConfirmBack'");
    view = finder.findRequiredView(source, 2131298746, "field 'tvNoConfirmTitle'");
    target.tvNoConfirmTitle = finder.castView(view, 2131298746, "field 'tvNoConfirmTitle'");
    view = finder.findRequiredView(source, 2131297240, "field 'ivMore'");
    target.ivMore = finder.castView(view, 2131297240, "field 'ivMore'");
    view = finder.findRequiredView(source, 2131298517, "field 'tvGoodsOrderNum'");
    target.tvGoodsOrderNum = finder.castView(view, 2131298517, "field 'tvGoodsOrderNum'");
    view = finder.findRequiredView(source, 2131299167, "field 'tvYjjyOrderOilName'");
    target.tvYjjyOrderOilName = finder.castView(view, 2131299167, "field 'tvYjjyOrderOilName'");
    view = finder.findRequiredView(source, 2131299168, "field 'tvYjjyOrderOilNumber'");
    target.tvYjjyOrderOilNumber = finder.castView(view, 2131299168, "field 'tvYjjyOrderOilNumber'");
  }

  @Override public void unbind(T target) {
    target.tvNoConfirmBack = null;
    target.tvNoConfirmTitle = null;
    target.ivMore = null;
    target.tvGoodsOrderNum = null;
    target.tvYjjyOrderOilName = null;
    target.tvYjjyOrderOilNumber = null;
  }
}
