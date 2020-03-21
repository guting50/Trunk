// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AddChildTypeActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.AddChildTypeActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131297756, "field 'rbSyncNo'");
    target.rbSyncNo = finder.castView(view, 2131297756, "field 'rbSyncNo'");
    view = finder.findRequiredView(source, 2131297757, "field 'rbSyncYes'");
    target.rbSyncYes = finder.castView(view, 2131297757, "field 'rbSyncYes'");
    view = finder.findRequiredView(source, 2131297823, "field 'rgAddChildType'");
    target.rgAddChildType = finder.castView(view, 2131297823, "field 'rgAddChildType'");
    view = finder.findRequiredView(source, 2131296691, "field 'etAddChildTypeMain'");
    target.etAddChildTypeMain = finder.castView(view, 2131296691, "field 'etAddChildTypeMain'");
    view = finder.findRequiredView(source, 2131296690, "field 'etAddChildTypeChild'");
    target.etAddChildTypeChild = finder.castView(view, 2131296690, "field 'etAddChildTypeChild'");
    view = finder.findRequiredView(source, 2131298229, "field 'tvAddChildTypeCancel'");
    target.tvAddChildTypeCancel = finder.castView(view, 2131298229, "field 'tvAddChildTypeCancel'");
    view = finder.findRequiredView(source, 2131298230, "field 'tvAddChildTypeConfirm'");
    target.tvAddChildTypeConfirm = finder.castView(view, 2131298230, "field 'tvAddChildTypeConfirm'");
  }

  @Override public void unbind(T target) {
    target.rbSyncNo = null;
    target.rbSyncYes = null;
    target.rgAddChildType = null;
    target.etAddChildTypeMain = null;
    target.etAddChildTypeChild = null;
    target.tvAddChildTypeCancel = null;
    target.tvAddChildTypeConfirm = null;
  }
}
