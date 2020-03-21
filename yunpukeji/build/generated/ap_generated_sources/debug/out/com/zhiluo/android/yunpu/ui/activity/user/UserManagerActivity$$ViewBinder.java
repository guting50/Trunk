// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.user;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class UserManagerActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.user.UserManagerActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298297, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131297518, "field 'listViewRole'");
    target.listViewRole = finder.castView(view, 2131297518, "field 'listViewRole'");
    view = finder.findRequiredView(source, 2131297517, "field 'listViewUser'");
    target.listViewUser = finder.castView(view, 2131297517, "field 'listViewUser'");
    view = finder.findRequiredView(source, 2131297810, "field 'mRefresh'");
    target.mRefresh = finder.castView(view, 2131297810, "field 'mRefresh'");
    view = finder.findRequiredView(source, 2131296304, "field 'lAddRole'");
    target.lAddRole = finder.castView(view, 2131296304, "field 'lAddRole'");
    view = finder.findRequiredView(source, 2131296307, "field 'lAddUSer'");
    target.lAddUSer = finder.castView(view, 2131296307, "field 'lAddUSer'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.listViewRole = null;
    target.listViewUser = null;
    target.mRefresh = null;
    target.lAddRole = null;
    target.lAddUSer = null;
  }
}
