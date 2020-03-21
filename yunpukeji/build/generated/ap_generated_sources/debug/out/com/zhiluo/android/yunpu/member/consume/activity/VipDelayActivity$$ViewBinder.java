// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.member.consume.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class VipDelayActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.member.consume.activity.VipDelayActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296608, "field 'edExtendTimes'");
    target.edExtendTimes = finder.castView(view, 2131296608, "field 'edExtendTimes'");
    view = finder.findRequiredView(source, 2131297893, "field 'rlExtendTimes'");
    target.rlExtendTimes = finder.castView(view, 2131297893, "field 'rlExtendTimes'");
  }

  @Override public void unbind(T target) {
    target.edExtendTimes = null;
    target.rlExtendTimes = null;
  }
}
