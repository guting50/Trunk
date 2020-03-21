// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.home.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MoreActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.home.activity.MoreActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298737, "field 'tvMoreBack'");
    target.tvMoreBack = finder.castView(view, 2131298737, "field 'tvMoreBack'");
    view = finder.findRequiredView(source, 2131298746, "field 'tvNoConfirmTitle'");
    target.tvNoConfirmTitle = finder.castView(view, 2131298746, "field 'tvNoConfirmTitle'");
    view = finder.findRequiredView(source, 2131298738, "field 'tvMoreManage'");
    target.tvMoreManage = finder.castView(view, 2131298738, "field 'tvMoreManage'");
    view = finder.findRequiredView(source, 2131297926, "field 'rlMoreHead'");
    target.rlMoreHead = finder.castView(view, 2131297926, "field 'rlMoreHead'");
    view = finder.findRequiredView(source, 2131297004, "field 'gvMyUtil'");
    target.gvMyUtil = finder.castView(view, 2131297004, "field 'gvMyUtil'");
    view = finder.findRequiredView(source, 2131297003, "field 'gvMoreUtil'");
    target.gvMoreUtil = finder.castView(view, 2131297003, "field 'gvMoreUtil'");
  }

  @Override public void unbind(T target) {
    target.tvMoreBack = null;
    target.tvNoConfirmTitle = null;
    target.tvMoreManage = null;
    target.rlMoreHead = null;
    target.gvMyUtil = null;
    target.gvMoreUtil = null;
  }
}
