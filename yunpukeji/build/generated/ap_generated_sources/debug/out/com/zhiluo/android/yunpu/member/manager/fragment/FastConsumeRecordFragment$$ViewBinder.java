// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.member.manager.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FastConsumeRecordFragment$$ViewBinder<T extends com.zhiluo.android.yunpu.member.manager.fragment.FastConsumeRecordFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131297471, "field 'mListView'");
    target.mListView = finder.castView(view, 2131297471, "field 'mListView'");
    view = finder.findRequiredView(source, 2131297795, "field 'mRefreshLayout'");
    target.mRefreshLayout = finder.castView(view, 2131297795, "field 'mRefreshLayout'");
  }

  @Override public void unbind(T target) {
    target.mListView = null;
    target.mRefreshLayout = null;
  }
}