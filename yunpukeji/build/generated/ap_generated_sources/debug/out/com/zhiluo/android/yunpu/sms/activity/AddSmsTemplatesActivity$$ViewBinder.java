// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.sms.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AddSmsTemplatesActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.sms.activity.AddSmsTemplatesActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBackActivity'");
    target.tvBackActivity = finder.castView(view, 2131298297, "field 'tvBackActivity'");
    view = finder.findRequiredView(source, 2131299071, "field 'tvTitleSure'");
    target.tvTitleSure = finder.castView(view, 2131299071, "field 'tvTitleSure'");
    view = finder.findRequiredView(source, 2131299013, "field 'tvSurplusSmsTitle'");
    target.tvSurplusSmsTitle = finder.castView(view, 2131299013, "field 'tvSurplusSmsTitle'");
    view = finder.findRequiredView(source, 2131296899, "field 'etMsgContentTitle'");
    target.etMsgContentTitle = finder.castView(view, 2131296899, "field 'etMsgContentTitle'");
    view = finder.findRequiredView(source, 2131296898, "field 'etMsgContentName'");
    target.etMsgContentName = finder.castView(view, 2131296898, "field 'etMsgContentName'");
    view = finder.findRequiredView(source, 2131298070, "field 'spSmsWildcard'");
    target.spSmsWildcard = finder.castView(view, 2131298070, "field 'spSmsWildcard'");
    view = finder.findRequiredView(source, 2131296294, "field 'activitySendMsg'");
    target.activitySendMsg = finder.castView(view, 2131296294, "field 'activitySendMsg'");
  }

  @Override public void unbind(T target) {
    target.tvBackActivity = null;
    target.tvTitleSure = null;
    target.tvSurplusSmsTitle = null;
    target.etMsgContentTitle = null;
    target.etMsgContentName = null;
    target.spSmsWildcard = null;
    target.activitySendMsg = null;
  }
}
