// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class YSLAddNewVipLevelActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.YSLAddNewVipLevelActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298895, "field 'tvRiseFallGrade'");
    target.tvRiseFallGrade = finder.castView(view, 2131298895, "field 'tvRiseFallGrade'");
    view = finder.findRequiredView(source, 2131297661, "field 'rRiseFallGrade'");
    target.rRiseFallGrade = finder.castView(view, 2131297661, "field 'rRiseFallGrade'");
    view = finder.findRequiredView(source, 2131298192, "field 'titleRiseFallGrade'");
    target.titleRiseFallGrade = finder.castView(view, 2131298192, "field 'titleRiseFallGrade'");
  }

  @Override public void unbind(T target) {
    target.tvRiseFallGrade = null;
    target.rRiseFallGrade = null;
    target.titleRiseFallGrade = null;
  }
}
