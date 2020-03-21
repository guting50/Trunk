// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChoiceInitServiceActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.ChoiceInitServiceActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298338, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298338, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131298339, "field 'tvConfirm'");
    target.tvConfirm = finder.castView(view, 2131298339, "field 'tvConfirm'");
    view = finder.findRequiredView(source, 2131297167, "field 'ivSearch'");
    target.ivSearch = finder.castView(view, 2131297167, "field 'ivSearch'");
    view = finder.findRequiredView(source, 2131296783, "field 'etCondition'");
    target.etCondition = finder.castView(view, 2131296783, "field 'etCondition'");
    view = finder.findRequiredView(source, 2131296470, "field 'cbChoiceAll'");
    target.cbChoiceAll = finder.castView(view, 2131296470, "field 'cbChoiceAll'");
    view = finder.findRequiredView(source, 2131297461, "field 'lvChoiceService'");
    target.lvChoiceService = finder.castView(view, 2131297461, "field 'lvChoiceService'");
    view = finder.findRequiredView(source, 2131297791, "field 'mRefresh'");
    target.mRefresh = finder.castView(view, 2131297791, "field 'mRefresh'");
    view = finder.findRequiredView(source, 2131298921, "field 'tvServerCount'");
    target.tvServerCount = finder.castView(view, 2131298921, "field 'tvServerCount'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.tvConfirm = null;
    target.ivSearch = null;
    target.etCondition = null;
    target.cbChoiceAll = null;
    target.lvChoiceService = null;
    target.mRefresh = null;
    target.tvServerCount = null;
  }
}
