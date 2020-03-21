// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.member.manager.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MemberSearchProjectActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.member.manager.activity.MemberSearchProjectActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298332, "field 'tvCheckMemberBack'");
    target.tvCheckMemberBack = finder.castView(view, 2131298332, "field 'tvCheckMemberBack'");
    view = finder.findRequiredView(source, 2131297880, "field 'rlCheckMemberTitle'");
    target.rlCheckMemberTitle = finder.castView(view, 2131297880, "field 'rlCheckMemberTitle'");
    view = finder.findRequiredView(source, 2131296781, "field 'etCheckMemberSearch'");
    target.etCheckMemberSearch = finder.castView(view, 2131296781, "field 'etCheckMemberSearch'");
    view = finder.findRequiredView(source, 2131297355, "field 'llCheckMemberSearch'");
    target.llCheckMemberSearch = finder.castView(view, 2131297355, "field 'llCheckMemberSearch'");
    view = finder.findRequiredView(source, 2131297458, "field 'lvCheckMemberInfo'");
    target.lvCheckMemberInfo = finder.castView(view, 2131297458, "field 'lvCheckMemberInfo'");
    view = finder.findRequiredView(source, 2131297789, "field 'refreshCheckMember'");
    target.refreshCheckMember = finder.castView(view, 2131297789, "field 'refreshCheckMember'");
  }

  @Override public void unbind(T target) {
    target.tvCheckMemberBack = null;
    target.rlCheckMemberTitle = null;
    target.etCheckMemberSearch = null;
    target.llCheckMemberSearch = null;
    target.lvCheckMemberInfo = null;
    target.refreshCheckMember = null;
  }
}
