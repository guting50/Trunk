// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.label;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class EditLabelActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.label.EditLabelActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298297, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131298425, "field 'tvSave'");
    target.tvSave = finder.castView(view, 2131298425, "field 'tvSave'");
    view = finder.findRequiredView(source, 2131296804, "field 'etName'");
    target.etName = finder.castView(view, 2131296804, "field 'etName'");
    view = finder.findRequiredView(source, 2131296805, "field 'etRemark'");
    target.etRemark = finder.castView(view, 2131296805, "field 'etRemark'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.tvSave = null;
    target.etName = null;
    target.etRemark = null;
  }
}
