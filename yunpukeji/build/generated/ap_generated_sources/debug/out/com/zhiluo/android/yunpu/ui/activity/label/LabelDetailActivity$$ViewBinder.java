// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.label;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LabelDetailActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.label.LabelDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298297, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131297279, "field 'ivMenu'");
    target.ivMenu = finder.castView(view, 2131297279, "field 'ivMenu'");
    view = finder.findRequiredView(source, 2131297921, "field 'rlHead'");
    target.rlHead = finder.castView(view, 2131297921, "field 'rlHead'");
    view = finder.findRequiredView(source, 2131298670, "field 'tvName'");
    target.tvName = finder.castView(view, 2131298670, "field 'tvName'");
    view = finder.findRequiredView(source, 2131298671, "field 'tvRemark'");
    target.tvRemark = finder.castView(view, 2131298671, "field 'tvRemark'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.ivMenu = null;
    target.rlHead = null;
    target.tvName = null;
    target.tvRemark = null;
  }
}
