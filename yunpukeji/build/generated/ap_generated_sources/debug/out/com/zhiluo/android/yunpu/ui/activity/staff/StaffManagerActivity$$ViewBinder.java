// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.staff;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class StaffManagerActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.staff.StaffManagerActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298297, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131297509, "field 'mListView'");
    target.mListView = finder.castView(view, 2131297509, "field 'mListView'");
    view = finder.findRequiredView(source, 2131297807, "field 'mRefresh'");
    target.mRefresh = finder.castView(view, 2131297807, "field 'mRefresh'");
    view = finder.findRequiredView(source, 2131297511, "field 'mDepListView'");
    target.mDepListView = finder.castView(view, 2131297511, "field 'mDepListView'");
    view = finder.findRequiredView(source, 2131296303, "field 'lAddPart'");
    target.lAddPart = finder.castView(view, 2131296303, "field 'lAddPart'");
    view = finder.findRequiredView(source, 2131296306, "field 'lAddStaff'");
    target.lAddStaff = finder.castView(view, 2131296306, "field 'lAddStaff'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.mListView = null;
    target.mRefresh = null;
    target.mDepListView = null;
    target.lAddPart = null;
    target.lAddStaff = null;
  }
}
