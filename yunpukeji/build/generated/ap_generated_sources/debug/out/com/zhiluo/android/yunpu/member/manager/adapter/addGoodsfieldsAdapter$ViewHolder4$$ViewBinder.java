// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.member.manager.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class addGoodsfieldsAdapter$ViewHolder4$$ViewBinder<T extends com.zhiluo.android.yunpu.member.manager.adapter.addGoodsfieldsAdapter.ViewHolder4> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131299056, "field 'tvTitle'");
    target.tvTitle = finder.castView(view, 2131299056, "field 'tvTitle'");
    view = finder.findRequiredView(source, 2131297111, "field 'isFill'");
    target.isFill = finder.castView(view, 2131297111, "field 'isFill'");
    view = finder.findRequiredView(source, 2131298343, "field 'tvChoose'");
    target.tvChoose = finder.castView(view, 2131298343, "field 'tvChoose'");
    view = finder.findRequiredView(source, 2131297047, "field 'imDate'");
    target.imDate = finder.castView(view, 2131297047, "field 'imDate'");
  }

  @Override public void unbind(T target) {
    target.tvTitle = null;
    target.isFill = null;
    target.tvChoose = null;
    target.imDate = null;
  }
}
