// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.consume.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class YouHuiQuanActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.consume.activity.YouHuiQuanActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBackActivity'");
    target.tvBackActivity = finder.castView(view, 2131298297, "field 'tvBackActivity'");
    view = finder.findRequiredView(source, 2131298341, "field 'tvChoiceYhqConfirm'");
    target.tvChoiceYhqConfirm = finder.castView(view, 2131298341, "field 'tvChoiceYhqConfirm'");
    view = finder.findRequiredView(source, 2131297168, "field 'ivChoiceYhjSearch'");
    target.ivChoiceYhjSearch = finder.castView(view, 2131297168, "field 'ivChoiceYhjSearch'");
    view = finder.findRequiredView(source, 2131296784, "field 'etChoiceYhjCondition'");
    target.etChoiceYhjCondition = finder.castView(view, 2131296784, "field 'etChoiceYhjCondition'");
    view = finder.findRequiredView(source, 2131297521, "field 'lvYhq'");
    target.lvYhq = finder.castView(view, 2131297521, "field 'lvYhq'");
  }

  @Override public void unbind(T target) {
    target.tvBackActivity = null;
    target.tvChoiceYhqConfirm = null;
    target.ivChoiceYhjSearch = null;
    target.etChoiceYhjCondition = null;
    target.lvYhq = null;
  }
}
