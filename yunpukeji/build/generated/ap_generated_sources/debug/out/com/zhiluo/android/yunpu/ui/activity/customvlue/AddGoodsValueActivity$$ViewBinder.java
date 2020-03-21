// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.customvlue;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AddGoodsValueActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.customvlue.AddGoodsValueActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298297, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131298235, "field 'tvSave'");
    target.tvSave = finder.castView(view, 2131298235, "field 'tvSave'");
    view = finder.findRequiredView(source, 2131296713, "field 'etName'");
    target.etName = finder.castView(view, 2131296713, "field 'etName'");
    view = finder.findRequiredView(source, 2131296714, "field 'etSort'");
    target.etSort = finder.castView(view, 2131296714, "field 'etSort'");
    view = finder.findRequiredView(source, 2131297700, "field 'rbNoEmpty'");
    target.rbNoEmpty = finder.castView(view, 2131297700, "field 'rbNoEmpty'");
    view = finder.findRequiredView(source, 2131297699, "field 'rbEmpty'");
    target.rbEmpty = finder.castView(view, 2131297699, "field 'rbEmpty'");
    view = finder.findRequiredView(source, 2131296712, "field 'etData'");
    target.etData = finder.castView(view, 2131296712, "field 'etData'");
    view = finder.findRequiredView(source, 2131298342, "field 'tvChoiseType'");
    target.tvChoiseType = finder.castView(view, 2131298342, "field 'tvChoiseType'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.tvSave = null;
    target.etName = null;
    target.etSort = null;
    target.rbNoEmpty = null;
    target.rbEmpty = null;
    target.etData = null;
    target.tvChoiseType = null;
  }
}
