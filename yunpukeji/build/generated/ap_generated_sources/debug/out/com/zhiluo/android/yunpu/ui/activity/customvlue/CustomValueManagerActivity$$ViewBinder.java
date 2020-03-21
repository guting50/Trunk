// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.customvlue;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CustomValueManagerActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.customvlue.CustomValueManagerActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298297, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131298132, "field 'mTabLayout'");
    target.mTabLayout = finder.castView(view, 2131298132, "field 'mTabLayout'");
    view = finder.findRequiredView(source, 2131299226, "field 'mViewPager'");
    target.mViewPager = finder.castView(view, 2131299226, "field 'mViewPager'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.mTabLayout = null;
    target.mViewPager = null;
  }
}
