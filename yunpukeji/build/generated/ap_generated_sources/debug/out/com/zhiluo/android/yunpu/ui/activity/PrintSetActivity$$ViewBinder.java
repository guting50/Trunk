// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PrintSetActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.PrintSetActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296915, "field 'etPrintSetOnekey'");
    target.etPrintSetOnekey = finder.castView(view, 2131296915, "field 'etPrintSetOnekey'");
    view = finder.findRequiredView(source, 2131297388, "field 'llOnekey'");
    target.llOnekey = finder.castView(view, 2131297388, "field 'llOnekey'");
  }

  @Override public void unbind(T target) {
    target.etPrintSetOnekey = null;
    target.llOnekey = null;
  }
}
