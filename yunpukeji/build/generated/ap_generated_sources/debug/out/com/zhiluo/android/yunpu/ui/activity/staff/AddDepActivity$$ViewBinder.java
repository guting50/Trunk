// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.staff;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AddDepActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.staff.AddDepActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296692, "field 'etAddDepName'");
    target.etAddDepName = finder.castView(view, 2131296692, "field 'etAddDepName'");
    view = finder.findRequiredView(source, 2131296693, "field 'etAddDepRemark'");
    target.etAddDepRemark = finder.castView(view, 2131296693, "field 'etAddDepRemark'");
    view = finder.findRequiredView(source, 2131296340, "field 'btnAddDepConfirm'");
    target.btnAddDepConfirm = finder.castView(view, 2131296340, "field 'btnAddDepConfirm'");
    view = finder.findRequiredView(source, 2131296339, "field 'btnAddDepCancel'");
    target.btnAddDepCancel = finder.castView(view, 2131296339, "field 'btnAddDepCancel'");
  }

  @Override public void unbind(T target) {
    target.etAddDepName = null;
    target.etAddDepRemark = null;
    target.btnAddDepConfirm = null;
    target.btnAddDepCancel = null;
  }
}
