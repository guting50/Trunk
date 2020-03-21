// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.timesrule;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TimesRuleDetail$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.timesrule.TimesRuleDetail> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBackActivity'");
    target.tvBackActivity = finder.castView(view, 2131298297, "field 'tvBackActivity'");
    view = finder.findRequiredView(source, 2131297250, "field 'ivRechargeDetailMenu'");
    target.ivRechargeDetailMenu = finder.castView(view, 2131297250, "field 'ivRechargeDetailMenu'");
    view = finder.findRequiredView(source, 2131297946, "field 'rlRechargeDetailHead'");
    target.rlRechargeDetailHead = finder.castView(view, 2131297946, "field 'rlRechargeDetailHead'");
    view = finder.findRequiredView(source, 2131298866, "field 'tvRechargeDetailName'");
    target.tvRechargeDetailName = finder.castView(view, 2131298866, "field 'tvRechargeDetailName'");
    view = finder.findRequiredView(source, 2131298865, "field 'tvRechargeDetailMoney'");
    target.tvRechargeDetailMoney = finder.castView(view, 2131298865, "field 'tvRechargeDetailMoney'");
    view = finder.findRequiredView(source, 2131298863, "field 'tvRechargeDetailGiveIntegral'");
    target.tvRechargeDetailGiveIntegral = finder.castView(view, 2131298863, "field 'tvRechargeDetailGiveIntegral'");
  }

  @Override public void unbind(T target) {
    target.tvBackActivity = null;
    target.ivRechargeDetailMenu = null;
    target.rlRechargeDetailHead = null;
    target.tvRechargeDetailName = null;
    target.tvRechargeDetailMoney = null;
    target.tvRechargeDetailGiveIntegral = null;
  }
}
