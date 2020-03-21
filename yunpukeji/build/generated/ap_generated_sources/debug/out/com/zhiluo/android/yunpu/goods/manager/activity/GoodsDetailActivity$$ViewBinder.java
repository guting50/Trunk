// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.goods.manager.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GoodsDetailActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.goods.manager.activity.GoodsDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296998, "field 'goodsDetailSl'");
    target.goodsDetailSl = finder.castView(view, 2131296998, "field 'goodsDetailSl'");
    view = finder.findRequiredView(source, 2131298151, "field 'teSave'");
    target.teSave = finder.castView(view, 2131298151, "field 'teSave'");
    view = finder.findRequiredView(source, 2131297875, "field 'rlBottom'");
    target.rlBottom = finder.castView(view, 2131297875, "field 'rlBottom'");
  }

  @Override public void unbind(T target) {
    target.goodsDetailSl = null;
    target.teSave = null;
    target.rlBottom = null;
  }
}
