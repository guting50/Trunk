// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.paymanager.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PayDetailActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.paymanager.activity.PayDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298745, "field 'tvNoConfirmBack'");
    target.tvNoConfirmBack = finder.castView(view, 2131298745, "field 'tvNoConfirmBack'");
    view = finder.findRequiredView(source, 2131298746, "field 'tvNoConfirmTitle'");
    target.tvNoConfirmTitle = finder.castView(view, 2131298746, "field 'tvNoConfirmTitle'");
    view = finder.findRequiredView(source, 2131298794, "field 'tvPayType'");
    target.tvPayType = finder.castView(view, 2131298794, "field 'tvPayType'");
    view = finder.findRequiredView(source, 2131298788, "field 'tvPayMoney'");
    target.tvPayMoney = finder.castView(view, 2131298788, "field 'tvPayMoney'");
    view = finder.findRequiredView(source, 2131298793, "field 'tvPayTime'");
    target.tvPayTime = finder.castView(view, 2131298793, "field 'tvPayTime'");
    view = finder.findRequiredView(source, 2131298791, "field 'tvPayPerson'");
    target.tvPayPerson = finder.castView(view, 2131298791, "field 'tvPayPerson'");
    view = finder.findRequiredView(source, 2131298792, "field 'tvPayRemark'");
    target.tvPayRemark = finder.castView(view, 2131298792, "field 'tvPayRemark'");
    view = finder.findRequiredView(source, 2131298783, "field 'tvPayCreater'");
    target.tvPayCreater = finder.castView(view, 2131298783, "field 'tvPayCreater'");
    view = finder.findRequiredView(source, 2131297240, "field 'ivMore'");
    target.ivMore = finder.castView(view, 2131297240, "field 'ivMore'");
  }

  @Override public void unbind(T target) {
    target.tvNoConfirmBack = null;
    target.tvNoConfirmTitle = null;
    target.tvPayType = null;
    target.tvPayMoney = null;
    target.tvPayTime = null;
    target.tvPayPerson = null;
    target.tvPayRemark = null;
    target.tvPayCreater = null;
    target.ivMore = null;
  }
}
