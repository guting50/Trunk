// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GradeManagerActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.GradeManagerActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298297, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131297493, "field 'mListView'");
    target.mListView = finder.castView(view, 2131297493, "field 'mListView'");
    view = finder.findRequiredView(source, 2131296968, "field 'mFab'");
    target.mFab = finder.castView(view, 2131296968, "field 'mFab'");
    view = finder.findRequiredView(source, 2131297800, "field 'mRefrsh'");
    target.mRefrsh = finder.castView(view, 2131297800, "field 'mRefrsh'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.mListView = null;
    target.mFab = null;
    target.mRefrsh = null;
  }
}