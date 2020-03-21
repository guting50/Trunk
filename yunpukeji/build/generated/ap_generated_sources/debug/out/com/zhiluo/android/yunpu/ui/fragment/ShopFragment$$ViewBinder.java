// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ShopFragment$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.fragment.ShopFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298139, "field 'mTabLayout'");
    target.mTabLayout = finder.castView(view, 2131298139, "field 'mTabLayout'");
    view = finder.findRequiredView(source, 2131299231, "field 'vpStore'");
    target.vpStore = finder.castView(view, 2131299231, "field 'vpStore'");
  }

  @Override public void unbind(T target) {
    target.mTabLayout = null;
    target.vpStore = null;
  }
}
