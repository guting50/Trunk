// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.consume.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PayConfirmActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.consume.activity.PayConfirmActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296736, "field 'etAddMemberOverdueDate'");
    target.etAddMemberOverdueDate = finder.castView(view, 2131296736, "field 'etAddMemberOverdueDate'");
    view = finder.findRequiredView(source, 2131297151, "field 'ivAddMemberOverdueDate'");
    target.ivAddMemberOverdueDate = finder.castView(view, 2131297151, "field 'ivAddMemberOverdueDate'");
    view = finder.findRequiredView(source, 2131297450, "field 'lrOverTime'");
    target.lrOverTime = finder.castView(view, 2131297450, "field 'lrOverTime'");
    view = finder.findRequiredView(source, 2131296677, "field 'emRule'");
    target.emRule = finder.castView(view, 2131296677, "field 'emRule'");
    view = finder.findRequiredView(source, 2131298781, "field 'tvPayConfirmRule'");
    target.tvPayConfirmRule = finder.castView(view, 2131298781, "field 'tvPayConfirmRule'");
    view = finder.findRequiredView(source, 2131297934, "field 'rlPayConfirmRule'");
    target.rlPayConfirmRule = finder.castView(view, 2131297934, "field 'rlPayConfirmRule'");
  }

  @Override public void unbind(T target) {
    target.etAddMemberOverdueDate = null;
    target.ivAddMemberOverdueDate = null;
    target.lrOverTime = null;
    target.emRule = null;
    target.tvPayConfirmRule = null;
    target.rlPayConfirmRule = null;
  }
}
