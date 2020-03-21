// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.member.manager.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RechargeRecordFragment$$ViewBinder<T extends com.zhiluo.android.yunpu.member.manager.fragment.RechargeRecordFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131297499, "field 'listView'");
    target.listView = finder.castView(view, 2131297499, "field 'listView'");
    view = finder.findRequiredView(source, 2131297805, "field 'refresh'");
    target.refresh = finder.castView(view, 2131297805, "field 'refresh'");
  }

  @Override public void unbind(T target) {
    target.listView = null;
    target.refresh = null;
  }
}
