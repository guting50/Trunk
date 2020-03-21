// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.member.consume.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class VerifyPasswordActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.member.consume.activity.VerifyPasswordActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296904, "field 'etPasswordInput'");
    target.etPasswordInput = finder.castView(view, 2131296904, "field 'etPasswordInput'");
    view = finder.findRequiredView(source, 2131298771, "field 'tvPasswordConfirm'");
    target.tvPasswordConfirm = finder.castView(view, 2131298771, "field 'tvPasswordConfirm'");
  }

  @Override public void unbind(T target) {
    target.etPasswordInput = null;
    target.tvPasswordConfirm = null;
  }
}
