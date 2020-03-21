// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.member.manager.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ModifyMemberActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.member.manager.activity.ModifyMemberActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298228, "field 'tvAddCardNum'");
    target.tvAddCardNum = finder.castView(view, 2131298228, "field 'tvAddCardNum'");
    view = finder.findRequiredView(source, 2131296689, "field 'etAddCardNum'");
    target.etAddCardNum = finder.castView(view, 2131296689, "field 'etAddCardNum'");
    view = finder.findRequiredView(source, 2131298257, "field 'tvAddMemberTel'");
    target.tvAddMemberTel = finder.castView(view, 2131298257, "field 'tvAddMemberTel'");
    view = finder.findRequiredView(source, 2131296741, "field 'etAddMemberTel'");
    target.etAddMemberTel = finder.castView(view, 2131296741, "field 'etAddMemberTel'");
    view = finder.findRequiredView(source, 2131298753, "field 'tvNumStart'");
    target.tvNumStart = finder.castView(view, 2131298753, "field 'tvNumStart'");
    view = finder.findRequiredView(source, 2131297850, "field 'rlAddCardNum'");
    target.rlAddCardNum = finder.castView(view, 2131297850, "field 'rlAddCardNum'");
  }

  @Override public void unbind(T target) {
    target.tvAddCardNum = null;
    target.etAddCardNum = null;
    target.tvAddMemberTel = null;
    target.etAddMemberTel = null;
    target.tvNumStart = null;
    target.rlAddCardNum = null;
  }
}
