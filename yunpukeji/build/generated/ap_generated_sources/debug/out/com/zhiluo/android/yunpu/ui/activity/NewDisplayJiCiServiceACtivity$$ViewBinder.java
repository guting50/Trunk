// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NewDisplayJiCiServiceACtivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.NewDisplayJiCiServiceACtivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298296, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298296, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131297078, "field 'mSave'");
    target.mSave = finder.castView(view, 2131297078, "field 'mSave'");
    view = finder.findRequiredView(source, 2131296470, "field 'cbChoiceAll'");
    target.cbChoiceAll = finder.castView(view, 2131296470, "field 'cbChoiceAll'");
    view = finder.findRequiredView(source, 2131297461, "field 'lvChoiceService'");
    target.lvChoiceService = finder.castView(view, 2131297461, "field 'lvChoiceService'");
    view = finder.findRequiredView(source, 2131298921, "field 'tvServerCount'");
    target.tvServerCount = finder.castView(view, 2131298921, "field 'tvServerCount'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.mSave = null;
    target.cbChoiceAll = null;
    target.lvChoiceService = null;
    target.tvServerCount = null;
  }
}
