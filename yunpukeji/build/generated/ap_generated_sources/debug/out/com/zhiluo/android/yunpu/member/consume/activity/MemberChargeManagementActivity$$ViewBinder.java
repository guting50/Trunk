// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.member.consume.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MemberChargeManagementActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.member.consume.activity.MemberChargeManagementActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131299056, "field 'tvTitle'");
    target.tvTitle = finder.castView(view, 2131299056, "field 'tvTitle'");
    view = finder.findRequiredView(source, 2131297404, "field 'llScan'");
    target.llScan = finder.castView(view, 2131297404, "field 'llScan'");
  }

  @Override public void unbind(T target) {
    target.tvTitle = null;
    target.llScan = null;
  }
}
