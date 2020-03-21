// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.discount;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DisDetailActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.discount.DisDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298297, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131297174, "field 'ivMenu'");
    target.ivMenu = finder.castView(view, 2131297174, "field 'ivMenu'");
    view = finder.findRequiredView(source, 2131298414, "field 'tvName'");
    target.tvName = finder.castView(view, 2131298414, "field 'tvName'");
    view = finder.findRequiredView(source, 2131298413, "field 'tvMoney'");
    target.tvMoney = finder.castView(view, 2131298413, "field 'tvMoney'");
    view = finder.findRequiredView(source, 2131298416, "field 'tvTypeName'");
    target.tvTypeName = finder.castView(view, 2131298416, "field 'tvTypeName'");
    view = finder.findRequiredView(source, 2131298411, "field 'tvGiveMoney'");
    target.tvGiveMoney = finder.castView(view, 2131298411, "field 'tvGiveMoney'");
    view = finder.findRequiredView(source, 2131298410, "field 'tvGiveIntegral'");
    target.tvGiveIntegral = finder.castView(view, 2131298410, "field 'tvGiveIntegral'");
    view = finder.findRequiredView(source, 2131298412, "field 'tvIsDouble'");
    target.tvIsDouble = finder.castView(view, 2131298412, "field 'tvIsDouble'");
    view = finder.findRequiredView(source, 2131298415, "field 'tvTime'");
    target.tvTime = finder.castView(view, 2131298415, "field 'tvTime'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.ivMenu = null;
    target.tvName = null;
    target.tvMoney = null;
    target.tvTypeName = null;
    target.tvGiveMoney = null;
    target.tvGiveIntegral = null;
    target.tvIsDouble = null;
    target.tvTime = null;
  }
}
