// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.goods.manager.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChoiceFastchargesActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.goods.manager.activity.ChoiceFastchargesActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298338, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298338, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131297167, "field 'ivChoiceServiceSearch'");
    target.ivChoiceServiceSearch = finder.castView(view, 2131297167, "field 'ivChoiceServiceSearch'");
    view = finder.findRequiredView(source, 2131296783, "field 'etCondition'");
    target.etCondition = finder.castView(view, 2131296783, "field 'etCondition'");
    view = finder.findRequiredView(source, 2131297461, "field 'lvChoiceService'");
    target.lvChoiceService = finder.castView(view, 2131297461, "field 'lvChoiceService'");
    view = finder.findRequiredView(source, 2131297791, "field 'mRefresh'");
    target.mRefresh = finder.castView(view, 2131297791, "field 'mRefresh'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.ivChoiceServiceSearch = null;
    target.etCondition = null;
    target.lvChoiceService = null;
    target.mRefresh = null;
  }
}
