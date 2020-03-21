// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GradeXianShiDetailActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.GradeXianShiDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298296, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298296, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131296600, "field 'edCishu'");
    target.edCishu = finder.castView(view, 2131296600, "field 'edCishu'");
    view = finder.findRequiredView(source, 2131296623, "field 'edShijian'");
    target.edShijian = finder.castView(view, 2131296623, "field 'edShijian'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.edCishu = null;
    target.edShijian = null;
  }
}
