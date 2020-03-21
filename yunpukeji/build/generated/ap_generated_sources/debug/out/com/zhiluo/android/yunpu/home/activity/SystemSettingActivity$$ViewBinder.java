// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.home.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SystemSettingActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.home.activity.SystemSettingActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131299024, "field 'tvVersionCode'");
    target.tvVersionCode = finder.castView(view, 2131299024, "field 'tvVersionCode'");
    view = finder.findRequiredView(source, 2131297974, "field 'rlCheckUpdate'");
    target.rlCheckUpdate = finder.castView(view, 2131297974, "field 'rlCheckUpdate'");
  }

  @Override public void unbind(T target) {
    target.tvVersionCode = null;
    target.rlCheckUpdate = null;
  }
}
