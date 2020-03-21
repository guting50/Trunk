// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.member.manager.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MemberSignDetailActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.member.manager.activity.MemberSignDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298745, "field 'tvNoConfirmBack'");
    target.tvNoConfirmBack = finder.castView(view, 2131298745, "field 'tvNoConfirmBack'");
    view = finder.findRequiredView(source, 2131298746, "field 'tvNoConfirmTitle'");
    target.tvNoConfirmTitle = finder.castView(view, 2131298746, "field 'tvNoConfirmTitle'");
    view = finder.findRequiredView(source, 2131298824, "field 'tvProjectName'");
    target.tvProjectName = finder.castView(view, 2131298824, "field 'tvProjectName'");
    view = finder.findRequiredView(source, 2131298823, "field 'tvProjectMember'");
    target.tvProjectMember = finder.castView(view, 2131298823, "field 'tvProjectMember'");
    view = finder.findRequiredView(source, 2131298491, "field 'tvGoodsCard'");
    target.tvGoodsCard = finder.castView(view, 2131298491, "field 'tvGoodsCard'");
    view = finder.findRequiredView(source, 2131298826, "field 'tvProjectPhone'");
    target.tvProjectPhone = finder.castView(view, 2131298826, "field 'tvProjectPhone'");
    view = finder.findRequiredView(source, 2131298827, "field 'tvProjectPoint'");
    target.tvProjectPoint = finder.castView(view, 2131298827, "field 'tvProjectPoint'");
    view = finder.findRequiredView(source, 2131298830, "field 'tvProjectTime'");
    target.tvProjectTime = finder.castView(view, 2131298830, "field 'tvProjectTime'");
    view = finder.findRequiredView(source, 2131298821, "field 'tvProjectCreator'");
    target.tvProjectCreator = finder.castView(view, 2131298821, "field 'tvProjectCreator'");
  }

  @Override public void unbind(T target) {
    target.tvNoConfirmBack = null;
    target.tvNoConfirmTitle = null;
    target.tvProjectName = null;
    target.tvProjectMember = null;
    target.tvGoodsCard = null;
    target.tvProjectPhone = null;
    target.tvProjectPoint = null;
    target.tvProjectTime = null;
    target.tvProjectCreator = null;
  }
}
