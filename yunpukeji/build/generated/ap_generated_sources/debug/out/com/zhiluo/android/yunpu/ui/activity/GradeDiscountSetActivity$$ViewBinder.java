// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GradeDiscountSetActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.GradeDiscountSetActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298297, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131298554, "field 'tvSave'");
    target.tvSave = finder.castView(view, 2131298554, "field 'tvSave'");
    view = finder.findRequiredView(source, 2131298002, "field 'mRecyclerView'");
    target.mRecyclerView = finder.castView(view, 2131298002, "field 'mRecyclerView'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.tvSave = null;
    target.mRecyclerView = null;
  }
}