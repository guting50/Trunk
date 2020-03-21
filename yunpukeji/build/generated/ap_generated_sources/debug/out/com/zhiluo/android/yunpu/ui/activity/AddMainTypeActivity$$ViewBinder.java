// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AddMainTypeActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.AddMainTypeActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131297756, "field 'rbSyncNo'");
    target.rbSyncNo = finder.castView(view, 2131297756, "field 'rbSyncNo'");
    view = finder.findRequiredView(source, 2131297757, "field 'rbSyncYes'");
    target.rbSyncYes = finder.castView(view, 2131297757, "field 'rbSyncYes'");
    view = finder.findRequiredView(source, 2131297826, "field 'rgAddMainType'");
    target.rgAddMainType = finder.castView(view, 2131297826, "field 'rgAddMainType'");
    view = finder.findRequiredView(source, 2131296723, "field 'etAddMainTypeName'");
    target.etAddMainTypeName = finder.castView(view, 2131296723, "field 'etAddMainTypeName'");
    view = finder.findRequiredView(source, 2131298237, "field 'tvAddMainTypeCancel'");
    target.tvAddMainTypeCancel = finder.castView(view, 2131298237, "field 'tvAddMainTypeCancel'");
    view = finder.findRequiredView(source, 2131298238, "field 'tvAddMainTypeConfirm'");
    target.tvAddMainTypeConfirm = finder.castView(view, 2131298238, "field 'tvAddMainTypeConfirm'");
  }

  @Override public void unbind(T target) {
    target.rbSyncNo = null;
    target.rbSyncYes = null;
    target.rgAddMainType = null;
    target.etAddMainTypeName = null;
    target.tvAddMainTypeCancel = null;
    target.tvAddMainTypeConfirm = null;
  }
}
