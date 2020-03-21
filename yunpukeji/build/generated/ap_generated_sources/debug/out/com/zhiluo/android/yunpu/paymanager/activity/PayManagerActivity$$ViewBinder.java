// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.paymanager.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PayManagerActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.paymanager.activity.PayManagerActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298296, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298296, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131299068, "field 'tvTitle'");
    target.tvTitle = finder.castView(view, 2131299068, "field 'tvTitle'");
    view = finder.findRequiredView(source, 2131297910, "field 'rlHead'");
    target.rlHead = finder.castView(view, 2131297910, "field 'rlHead'");
    view = finder.findRequiredView(source, 2131298136, "field 'mTabLayout'");
    target.mTabLayout = finder.castView(view, 2131298136, "field 'mTabLayout'");
    view = finder.findRequiredView(source, 2131299229, "field 'mViewPager'");
    target.mViewPager = finder.castView(view, 2131299229, "field 'mViewPager'");
    view = finder.findRequiredView(source, 2131297274, "field 'ivTitleScreen'");
    target.ivTitleScreen = finder.castView(view, 2131297274, "field 'ivTitleScreen'");
    view = finder.findRequiredView(source, 2131299019, "field 'tvSx'");
    target.tvSx = finder.castView(view, 2131299019, "field 'tvSx'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.tvTitle = null;
    target.rlHead = null;
    target.mTabLayout = null;
    target.mViewPager = null;
    target.ivTitleScreen = null;
    target.tvSx = null;
  }
}
