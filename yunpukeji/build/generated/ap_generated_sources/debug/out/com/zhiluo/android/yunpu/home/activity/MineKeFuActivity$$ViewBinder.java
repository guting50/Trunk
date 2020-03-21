// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.home.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MineKeFuActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.home.activity.MineKeFuActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131297619, "field 'rAboutus'");
    target.rAboutus = finder.castView(view, 2131297619, "field 'rAboutus'");
    view = finder.findRequiredView(source, 2131297674, "field 'rUserAgree'");
    target.rUserAgree = finder.castView(view, 2131297674, "field 'rUserAgree'");
  }

  @Override public void unbind(T target) {
    target.rAboutus = null;
    target.rUserAgree = null;
  }
}
