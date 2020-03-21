// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.member.manager.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MemberRecordActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.member.manager.activity.MemberRecordActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298696, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298696, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131298134, "field 'tabRecord'");
    target.tabRecord = finder.castView(view, 2131298134, "field 'tabRecord'");
    view = finder.findRequiredView(source, 2131299227, "field 'vpMemberRecord'");
    target.vpMemberRecord = finder.castView(view, 2131299227, "field 'vpMemberRecord'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.tabRecord = null;
    target.vpMemberRecord = null;
  }
}
