// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.member.manager.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class VipBaseMessage$$ViewBinder<T extends com.zhiluo.android.yunpu.member.manager.activity.VipBaseMessage> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296562, "field 'civAddMemberHead'");
    target.civAddMemberHead = finder.castView(view, 2131296562, "field 'civAddMemberHead'");
    view = finder.findRequiredView(source, 2131296731, "field 'etAddMemberLab'");
    target.etAddMemberLab = finder.castView(view, 2131296731, "field 'etAddMemberLab'");
    view = finder.findRequiredView(source, 2131297855, "field 'rlFaceNumber'");
    target.rlFaceNumber = finder.castView(view, 2131297855, "field 'rlFaceNumber'");
  }

  @Override public void unbind(T target) {
    target.civAddMemberHead = null;
    target.etAddMemberLab = null;
    target.rlFaceNumber = null;
  }
}
