// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.good.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class InWareHouseFragment$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.good.fragment.InWareHouseFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131297781, "field 'recyclerView'");
    target.recyclerView = finder.castView(view, 2131297781, "field 'recyclerView'");
    view = finder.findRequiredView(source, 2131296475, "field 'cbDownUp'");
    target.cbDownUp = finder.castView(view, 2131296475, "field 'cbDownUp'");
    view = finder.findRequiredView(source, 2131298376, "field 'tvCount'");
    target.tvCount = finder.castView(view, 2131298376, "field 'tvCount'");
    view = finder.findRequiredView(source, 2131297299, "field 'lMed'");
    target.lMed = finder.castView(view, 2131297299, "field 'lMed'");
  }

  @Override public void unbind(T target) {
    target.recyclerView = null;
    target.cbDownUp = null;
    target.tvCount = null;
    target.lMed = null;
  }
}
