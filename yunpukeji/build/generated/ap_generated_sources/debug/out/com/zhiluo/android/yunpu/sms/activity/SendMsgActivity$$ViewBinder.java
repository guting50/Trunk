// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.sms.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SendMsgActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.sms.activity.SendMsgActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131299067, "field 'tvTitleNotice'");
    target.tvTitleNotice = finder.castView(view, 2131299067, "field 'tvTitleNotice'");
    view = finder.findRequiredView(source, 2131298346, "field 'tvChooseSmsMember'");
    target.tvChooseSmsMember = finder.castView(view, 2131298346, "field 'tvChooseSmsMember'");
  }

  @Override public void unbind(T target) {
    target.tvTitleNotice = null;
    target.tvChooseSmsMember = null;
  }
}
