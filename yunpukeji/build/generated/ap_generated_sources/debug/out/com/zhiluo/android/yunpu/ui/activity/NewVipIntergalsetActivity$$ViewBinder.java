// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NewVipIntergalsetActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.NewVipIntergalsetActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296902, "field 'etOnekeyOil'");
    target.etOnekeyOil = finder.castView(view, 2131296902, "field 'etOnekeyOil'");
    view = finder.findRequiredView(source, 2131297386, "field 'llOil'");
    target.llOil = finder.castView(view, 2131297386, "field 'llOil'");
    view = finder.findRequiredView(source, 2131299200, "field 'vOil'");
    target.vOil = view;
  }

  @Override public void unbind(T target) {
    target.etOnekeyOil = null;
    target.llOil = null;
    target.vOil = null;
  }
}
