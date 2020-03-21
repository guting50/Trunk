// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.member.manager.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class addGoodsfieldsAdapter$ViewHolder2$$ViewBinder<T extends com.zhiluo.android.yunpu.member.manager.adapter.addGoodsfieldsAdapter.ViewHolder2> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296700, "field 'etAddFieldTitle'");
    target.etAddFieldTitle = finder.castView(view, 2131296700, "field 'etAddFieldTitle'");
    view = finder.findRequiredView(source, 2131297111, "field 'isFill'");
    target.isFill = finder.castView(view, 2131297111, "field 'isFill'");
    view = finder.findRequiredView(source, 2131296698, "field 'etAddFieldNumber'");
    target.etAddFieldNumber = finder.castView(view, 2131296698, "field 'etAddFieldNumber'");
  }

  @Override public void unbind(T target) {
    target.etAddFieldTitle = null;
    target.isFill = null;
    target.etAddFieldNumber = null;
  }
}
