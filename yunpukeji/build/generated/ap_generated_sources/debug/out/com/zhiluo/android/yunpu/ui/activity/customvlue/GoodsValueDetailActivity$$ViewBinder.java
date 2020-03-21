// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.customvlue;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GoodsValueDetailActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.customvlue.GoodsValueDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298297, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131297190, "field 'ivMenu'");
    target.ivMenu = finder.castView(view, 2131297190, "field 'ivMenu'");
    view = finder.findRequiredView(source, 2131297904, "field 'rlHead'");
    target.rlHead = finder.castView(view, 2131297904, "field 'rlHead'");
    view = finder.findRequiredView(source, 2131298543, "field 'tvType'");
    target.tvType = finder.castView(view, 2131298543, "field 'tvType'");
    view = finder.findRequiredView(source, 2131298541, "field 'tvName'");
    target.tvName = finder.castView(view, 2131298541, "field 'tvName'");
    view = finder.findRequiredView(source, 2131298542, "field 'tvSort'");
    target.tvSort = finder.castView(view, 2131298542, "field 'tvSort'");
    view = finder.findRequiredView(source, 2131297740, "field 'rbNoEmpty'");
    target.rbNoEmpty = finder.castView(view, 2131297740, "field 'rbNoEmpty'");
    view = finder.findRequiredView(source, 2131297739, "field 'rbEmpty'");
    target.rbEmpty = finder.castView(view, 2131297739, "field 'rbEmpty'");
    view = finder.findRequiredView(source, 2131298540, "field 'tvData'");
    target.tvData = finder.castView(view, 2131298540, "field 'tvData'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.ivMenu = null;
    target.rlHead = null;
    target.tvType = null;
    target.tvName = null;
    target.tvSort = null;
    target.rbNoEmpty = null;
    target.rbEmpty = null;
    target.tvData = null;
  }
}
