// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.member.consume.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NFCCard$$ViewBinder<T extends com.zhiluo.android.yunpu.member.consume.activity.NFCCard> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296879, "field 'etSearch'");
    target.etSearch = finder.castView(view, 2131296879, "field 'etSearch'");
  }

  @Override public void unbind(T target) {
    target.etSearch = null;
  }
}
