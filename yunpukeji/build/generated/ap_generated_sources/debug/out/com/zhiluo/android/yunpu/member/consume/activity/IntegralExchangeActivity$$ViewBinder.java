// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.member.consume.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class IntegralExchangeActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.member.consume.activity.IntegralExchangeActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131299056, "field 'tvTitle'");
    target.tvTitle = finder.castView(view, 2131299056, "field 'tvTitle'");
  }

  @Override public void unbind(T target) {
    target.tvTitle = null;
  }
}
