// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.sms.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChooseSMSTemplatesActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.sms.activity.ChooseSMSTemplatesActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298961, "field 'tvSmsAdd'");
    target.tvSmsAdd = finder.castView(view, 2131298961, "field 'tvSmsAdd'");
  }

  @Override public void unbind(T target) {
    target.tvSmsAdd = null;
  }
}
