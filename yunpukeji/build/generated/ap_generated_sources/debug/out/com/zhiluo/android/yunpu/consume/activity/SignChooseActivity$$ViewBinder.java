// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.consume.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SignChooseActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.consume.activity.SignChooseActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBackActivity'");
    target.tvBackActivity = finder.castView(view, 2131298297, "field 'tvBackActivity'");
    view = finder.findRequiredView(source, 2131298953, "field 'tvSignConfirm'");
    target.tvSignConfirm = finder.castView(view, 2131298953, "field 'tvSignConfirm'");
    view = finder.findRequiredView(source, 2131297507, "field 'lvSignlist'");
    target.lvSignlist = finder.castView(view, 2131297507, "field 'lvSignlist'");
  }

  @Override public void unbind(T target) {
    target.tvBackActivity = null;
    target.tvSignConfirm = null;
    target.lvSignlist = null;
  }
}
