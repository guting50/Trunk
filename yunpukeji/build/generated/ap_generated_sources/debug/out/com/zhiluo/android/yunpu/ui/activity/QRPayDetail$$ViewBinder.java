// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class QRPayDetail$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.QRPayDetail> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298764, "field 'tvOpenQrpay'");
    target.tvOpenQrpay = finder.castView(view, 2131298764, "field 'tvOpenQrpay'");
    view = finder.findRequiredView(source, 2131298294, "field 'tvAsktoCustomer'");
    target.tvAsktoCustomer = finder.castView(view, 2131298294, "field 'tvAsktoCustomer'");
    view = finder.findRequiredView(source, 2131298297, "field 'tvBackActivity'");
    target.tvBackActivity = finder.castView(view, 2131298297, "field 'tvBackActivity'");
  }

  @Override public void unbind(T target) {
    target.tvOpenQrpay = null;
    target.tvAsktoCustomer = null;
    target.tvBackActivity = null;
  }
}
