// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.jpush;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NoticeActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.jpush.NoticeActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBackActivity'");
    target.tvBackActivity = finder.castView(view, 2131298297, "field 'tvBackActivity'");
    view = finder.findRequiredView(source, 2131299056, "field 'tvTitle'");
    target.tvTitle = finder.castView(view, 2131299056, "field 'tvTitle'");
    view = finder.findRequiredView(source, 2131298370, "field 'tvContent'");
    target.tvContent = finder.castView(view, 2131298370, "field 'tvContent'");
  }

  @Override public void unbind(T target) {
    target.tvBackActivity = null;
    target.tvTitle = null;
    target.tvContent = null;
  }
}
