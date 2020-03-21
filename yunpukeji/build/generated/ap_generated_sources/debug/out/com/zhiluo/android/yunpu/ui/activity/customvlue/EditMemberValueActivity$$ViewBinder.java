// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.customvlue;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class EditMemberValueActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.customvlue.EditMemberValueActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298297, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131298426, "field 'tvSave'");
    target.tvSave = finder.castView(view, 2131298426, "field 'tvSave'");
    view = finder.findRequiredView(source, 2131298427, "field 'tvType'");
    target.tvType = finder.castView(view, 2131298427, "field 'tvType'");
    view = finder.findRequiredView(source, 2131296807, "field 'etName'");
    target.etName = finder.castView(view, 2131296807, "field 'etName'");
    view = finder.findRequiredView(source, 2131296808, "field 'etSort'");
    target.etSort = finder.castView(view, 2131296808, "field 'etSort'");
    view = finder.findRequiredView(source, 2131297715, "field 'rbNoEmpty'");
    target.rbNoEmpty = finder.castView(view, 2131297715, "field 'rbNoEmpty'");
    view = finder.findRequiredView(source, 2131297713, "field 'rbEmpty'");
    target.rbEmpty = finder.castView(view, 2131297713, "field 'rbEmpty'");
    view = finder.findRequiredView(source, 2131297716, "field 'rbShow'");
    target.rbShow = finder.castView(view, 2131297716, "field 'rbShow'");
    view = finder.findRequiredView(source, 2131297714, "field 'rbHide'");
    target.rbHide = finder.castView(view, 2131297714, "field 'rbHide'");
    view = finder.findRequiredView(source, 2131296806, "field 'etData'");
    target.etData = finder.castView(view, 2131296806, "field 'etData'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.tvSave = null;
    target.tvType = null;
    target.etName = null;
    target.etSort = null;
    target.rbNoEmpty = null;
    target.rbEmpty = null;
    target.rbShow = null;
    target.rbHide = null;
    target.etData = null;
  }
}
