// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.sms.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MsgNotice$$ViewBinder<T extends com.zhiluo.android.yunpu.sms.activity.MsgNotice> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298370, "field 'tvContent'");
    target.tvContent = finder.castView(view, 2131298370, "field 'tvContent'");
    view = finder.findRequiredView(source, 2131298297, "field 'tvBackActivity'");
    target.tvBackActivity = finder.castView(view, 2131298297, "field 'tvBackActivity'");
  }

  @Override public void unbind(T target) {
    target.tvContent = null;
    target.tvBackActivity = null;
  }
}
