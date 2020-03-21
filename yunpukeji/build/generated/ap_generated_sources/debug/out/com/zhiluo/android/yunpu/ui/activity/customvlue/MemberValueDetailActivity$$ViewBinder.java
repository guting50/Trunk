// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.customvlue;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MemberValueDetailActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.customvlue.MemberValueDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298297, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131297236, "field 'ivMenu'");
    target.ivMenu = finder.castView(view, 2131297236, "field 'ivMenu'");
    view = finder.findRequiredView(source, 2131298702, "field 'tvType'");
    target.tvType = finder.castView(view, 2131298702, "field 'tvType'");
    view = finder.findRequiredView(source, 2131298700, "field 'tvName'");
    target.tvName = finder.castView(view, 2131298700, "field 'tvName'");
    view = finder.findRequiredView(source, 2131298701, "field 'tvSort'");
    target.tvSort = finder.castView(view, 2131298701, "field 'tvSort'");
    view = finder.findRequiredView(source, 2131297748, "field 'rbNoEmpty'");
    target.rbNoEmpty = finder.castView(view, 2131297748, "field 'rbNoEmpty'");
    view = finder.findRequiredView(source, 2131297746, "field 'rbEmpty'");
    target.rbEmpty = finder.castView(view, 2131297746, "field 'rbEmpty'");
    view = finder.findRequiredView(source, 2131297749, "field 'rbShow'");
    target.rbShow = finder.castView(view, 2131297749, "field 'rbShow'");
    view = finder.findRequiredView(source, 2131297747, "field 'rbHide'");
    target.rbHide = finder.castView(view, 2131297747, "field 'rbHide'");
    view = finder.findRequiredView(source, 2131298699, "field 'tvData'");
    target.tvData = finder.castView(view, 2131298699, "field 'tvData'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.ivMenu = null;
    target.tvType = null;
    target.tvName = null;
    target.tvSort = null;
    target.rbNoEmpty = null;
    target.rbEmpty = null;
    target.rbShow = null;
    target.rbHide = null;
    target.tvData = null;
  }
}
