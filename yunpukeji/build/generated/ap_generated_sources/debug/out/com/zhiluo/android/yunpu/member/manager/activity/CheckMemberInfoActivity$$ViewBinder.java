// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.member.manager.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CheckMemberInfoActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.member.manager.activity.CheckMemberInfoActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131297355, "field 'llCheckMemberSearch' and method 'onViewClicked'");
    target.llCheckMemberSearch = finder.castView(view, 2131297355, "field 'llCheckMemberSearch'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked();
        }
      });
    view = finder.findRequiredView(source, 2131298908, "field 'tvSave'");
    target.tvSave = finder.castView(view, 2131298908, "field 'tvSave'");
    view = finder.findRequiredView(source, 2131297353, "field 'llBottom'");
    target.llBottom = finder.castView(view, 2131297353, "field 'llBottom'");
  }

  @Override public void unbind(T target) {
    target.llCheckMemberSearch = null;
    target.tvSave = null;
    target.llBottom = null;
  }
}
