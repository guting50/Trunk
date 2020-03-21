// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.member.consume.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MemberChargePayActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.member.consume.activity.MemberChargePayActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBackActivity'");
    target.tvBackActivity = finder.castView(view, 2131298297, "field 'tvBackActivity'");
    view = finder.findRequiredView(source, 2131298778, "field 'tvPayConfirmOrderName'");
    target.tvPayConfirmOrderName = finder.castView(view, 2131298778, "field 'tvPayConfirmOrderName'");
    view = finder.findRequiredView(source, 2131298777, "field 'tvPayConfirmOrder'");
    target.tvPayConfirmOrder = finder.castView(view, 2131298777, "field 'tvPayConfirmOrder'");
    view = finder.findRequiredView(source, 2131298780, "field 'tvPayConfirmReceivable'");
    target.tvPayConfirmReceivable = finder.castView(view, 2131298780, "field 'tvPayConfirmReceivable'");
    view = finder.findRequiredView(source, 2131297781, "field 'recyclerView'");
    target.recyclerView = finder.castView(view, 2131297781, "field 'recyclerView'");
    view = finder.findRequiredView(source, 2131296511, "field 'cbShortMessage'");
    target.cbShortMessage = finder.castView(view, 2131296511, "field 'cbShortMessage'");
    view = finder.findRequiredView(source, 2131296497, "field 'cbPrint'");
    target.cbPrint = finder.castView(view, 2131296497, "field 'cbPrint'");
    view = finder.findRequiredView(source, 2131296418, "field 'btnPayConfirmSubmit'");
    target.btnPayConfirmSubmit = finder.castView(view, 2131296418, "field 'btnPayConfirmSubmit'");
    view = finder.findRequiredView(source, 2131296786, "field 'etConstomPayDetail'");
    target.etConstomPayDetail = finder.castView(view, 2131296786, "field 'etConstomPayDetail'");
    view = finder.findRequiredView(source, 2131296735, "field 'tvOrderTime'");
    target.tvOrderTime = finder.castView(view, 2131296735, "field 'tvOrderTime'");
    view = finder.findRequiredView(source, 2131297150, "field 'igOrderTime'");
    target.igOrderTime = finder.castView(view, 2131297150, "field 'igOrderTime'");
    view = finder.findRequiredView(source, 2131297449, "field 'lrOrderTime'");
    target.lrOrderTime = finder.castView(view, 2131297449, "field 'lrOrderTime'");
  }

  @Override public void unbind(T target) {
    target.tvBackActivity = null;
    target.tvPayConfirmOrderName = null;
    target.tvPayConfirmOrder = null;
    target.tvPayConfirmReceivable = null;
    target.recyclerView = null;
    target.cbShortMessage = null;
    target.cbPrint = null;
    target.btnPayConfirmSubmit = null;
    target.etConstomPayDetail = null;
    target.tvOrderTime = null;
    target.igOrderTime = null;
    target.lrOrderTime = null;
  }
}
