// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.recharge;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class KRechargeDetailActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.recharge.KRechargeDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298297, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131297250, "field 'ivMenu'");
    target.ivMenu = finder.castView(view, 2131297250, "field 'ivMenu'");
    view = finder.findRequiredView(source, 2131298866, "field 'tvName'");
    target.tvName = finder.castView(view, 2131298866, "field 'tvName'");
    view = finder.findRequiredView(source, 2131298865, "field 'tvMoney'");
    target.tvMoney = finder.castView(view, 2131298865, "field 'tvMoney'");
    view = finder.findRequiredView(source, 2131298864, "field 'tvGiveMoney'");
    target.tvGiveMoney = finder.castView(view, 2131298864, "field 'tvGiveMoney'");
    view = finder.findRequiredView(source, 2131298863, "field 'tvGiveIntegral'");
    target.tvGiveIntegral = finder.castView(view, 2131298863, "field 'tvGiveIntegral'");
    view = finder.findRequiredView(source, 2131298867, "field 'tvTime'");
    target.tvTime = finder.castView(view, 2131298867, "field 'tvTime'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.ivMenu = null;
    target.tvName = null;
    target.tvMoney = null;
    target.tvGiveMoney = null;
    target.tvGiveIntegral = null;
    target.tvTime = null;
  }
}
