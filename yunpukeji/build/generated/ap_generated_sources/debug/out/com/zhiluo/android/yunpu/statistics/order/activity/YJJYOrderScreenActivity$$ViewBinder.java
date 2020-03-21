// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.statistics.order.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class YJJYOrderScreenActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.statistics.order.activity.YJJYOrderScreenActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296929, "field 'etSearchByOilName'");
    target.etSearchByOilName = finder.castView(view, 2131296929, "field 'etSearchByOilName'");
  }

  @Override public void unbind(T target) {
    target.etSearchByOilName = null;
  }
}
