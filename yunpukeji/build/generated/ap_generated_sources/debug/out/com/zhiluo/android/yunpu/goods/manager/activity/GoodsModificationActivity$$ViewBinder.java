// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.goods.manager.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GoodsModificationActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.goods.manager.activity.GoodsModificationActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298539, "field 'tvGoodsTypeStart'");
    target.tvGoodsTypeStart = finder.castView(view, 2131298539, "field 'tvGoodsTypeStart'");
    view = finder.findRequiredView(source, 2131297365, "field 'llDiscount'");
    target.llDiscount = finder.castView(view, 2131297365, "field 'llDiscount'");
  }

  @Override public void unbind(T target) {
    target.tvGoodsTypeStart = null;
    target.llDiscount = null;
  }
}
