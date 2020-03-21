// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DiscountDetailActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.DiscountDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298297, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131298001, "field 'rvDiscountDetail'");
    target.rvDiscountDetail = finder.castView(view, 2131298001, "field 'rvDiscountDetail'");
    view = finder.findRequiredView(source, 2131296830, "field 'etFastShop'");
    target.etFastShop = finder.castView(view, 2131296830, "field 'etFastShop'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.rvDiscountDetail = null;
    target.etFastShop = null;
  }
}
