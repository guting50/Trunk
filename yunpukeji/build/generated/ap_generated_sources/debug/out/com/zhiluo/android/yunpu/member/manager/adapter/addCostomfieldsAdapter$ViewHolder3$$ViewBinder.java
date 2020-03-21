// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.member.manager.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class addCostomfieldsAdapter$ViewHolder3$$ViewBinder<T extends com.zhiluo.android.yunpu.member.manager.adapter.addCostomfieldsAdapter.ViewHolder3> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131299056, "field 'tvTitle'");
    target.tvTitle = finder.castView(view, 2131299056, "field 'tvTitle'");
    view = finder.findRequiredView(source, 2131297111, "field 'isFill'");
    target.isFill = finder.castView(view, 2131297111, "field 'isFill'");
    view = finder.findRequiredView(source, 2131298343, "field 'tvChoose'");
    target.tvChoose = finder.castView(view, 2131298343, "field 'tvChoose'");
  }

  @Override public void unbind(T target) {
    target.tvTitle = null;
    target.isFill = null;
    target.tvChoose = null;
  }
}
