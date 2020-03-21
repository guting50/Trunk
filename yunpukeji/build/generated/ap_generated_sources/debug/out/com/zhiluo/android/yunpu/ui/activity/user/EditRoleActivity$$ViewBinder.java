// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.user;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class EditRoleActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.user.EditRoleActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298297, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131298431, "field 'tvSave'");
    target.tvSave = finder.castView(view, 2131298431, "field 'tvSave'");
    view = finder.findRequiredView(source, 2131296815, "field 'etName'");
    target.etName = finder.castView(view, 2131296815, "field 'etName'");
    view = finder.findRequiredView(source, 2131296816, "field 'etRemark'");
    target.etRemark = finder.castView(view, 2131296816, "field 'etRemark'");
    view = finder.findRequiredView(source, 2131298005, "field 'mRecyclerView'");
    target.mRecyclerView = finder.castView(view, 2131298005, "field 'mRecyclerView'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.tvSave = null;
    target.etName = null;
    target.etRemark = null;
    target.mRecyclerView = null;
  }
}
