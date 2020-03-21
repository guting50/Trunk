// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.label;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AddLabelActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.label.AddLabelActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296721, "field 'etName'");
    target.etName = finder.castView(view, 2131296721, "field 'etName'");
    view = finder.findRequiredView(source, 2131296722, "field 'etRemark'");
    target.etRemark = finder.castView(view, 2131296722, "field 'etRemark'");
    view = finder.findRequiredView(source, 2131296342, "field 'btnConfirm'");
    target.btnConfirm = finder.castView(view, 2131296342, "field 'btnConfirm'");
    view = finder.findRequiredView(source, 2131296341, "field 'btnCancel'");
    target.btnCancel = finder.castView(view, 2131296341, "field 'btnCancel'");
  }

  @Override public void unbind(T target) {
    target.etName = null;
    target.etRemark = null;
    target.btnConfirm = null;
    target.btnCancel = null;
  }
}
