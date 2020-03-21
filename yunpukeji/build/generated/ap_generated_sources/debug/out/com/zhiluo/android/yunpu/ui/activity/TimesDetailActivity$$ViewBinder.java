// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TimesDetailActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.TimesDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298296, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298296, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131299072, "field 'tvTitleTimes'");
    target.tvTitleTimes = finder.castView(view, 2131299072, "field 'tvTitleTimes'");
    view = finder.findRequiredView(source, 2131297638, "field 'rLayout'");
    target.rLayout = finder.castView(view, 2131297638, "field 'rLayout'");
    view = finder.findRequiredView(source, 2131297781, "field 'recyclerView'");
    target.recyclerView = finder.castView(view, 2131297781, "field 'recyclerView'");
    view = finder.findRequiredView(source, 2131298751, "field 'tvNull'");
    target.tvNull = finder.castView(view, 2131298751, "field 'tvNull'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.tvTitleTimes = null;
    target.rLayout = null;
    target.recyclerView = null;
    target.tvNull = null;
  }
}
