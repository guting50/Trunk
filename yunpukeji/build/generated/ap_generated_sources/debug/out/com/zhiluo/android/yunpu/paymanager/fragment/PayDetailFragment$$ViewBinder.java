// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.paymanager.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PayDetailFragment$$ViewBinder<T extends com.zhiluo.android.yunpu.paymanager.fragment.PayDetailFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298980, "field 'mTvStartDate'");
    target.mTvStartDate = finder.castView(view, 2131298980, "field 'mTvStartDate'");
    view = finder.findRequiredView(source, 2131298443, "field 'mTvEndDate'");
    target.mTvEndDate = finder.castView(view, 2131298443, "field 'mTvEndDate'");
    view = finder.findRequiredView(source, 2131296370, "field 'btnDateConfirm'");
    target.btnDateConfirm = finder.castView(view, 2131296370, "field 'btnDateConfirm'");
    view = finder.findRequiredView(source, 2131297377, "field 'llHandoverDateSelector'");
    target.llHandoverDateSelector = finder.castView(view, 2131297377, "field 'llHandoverDateSelector'");
    view = finder.findRequiredView(source, 2131298751, "field 'tvNull'");
    target.tvNull = finder.castView(view, 2131298751, "field 'tvNull'");
    view = finder.findRequiredView(source, 2131297338, "field 'listView'");
    target.listView = finder.castView(view, 2131297338, "field 'listView'");
    view = finder.findRequiredView(source, 2131297787, "field 'refresh'");
    target.refresh = finder.castView(view, 2131297787, "field 'refresh'");
  }

  @Override public void unbind(T target) {
    target.mTvStartDate = null;
    target.mTvEndDate = null;
    target.btnDateConfirm = null;
    target.llHandoverDateSelector = null;
    target.tvNull = null;
    target.listView = null;
    target.refresh = null;
  }
}
