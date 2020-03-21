// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GradeIntegralDetailActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.GradeIntegralDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298297, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131298003, "field 'rvIntegralDetail'");
    target.rvIntegralDetail = finder.castView(view, 2131298003, "field 'rvIntegralDetail'");
    view = finder.findRequiredView(source, 2131296903, "field 'edOriginIntergal'");
    target.edOriginIntergal = finder.castView(view, 2131296903, "field 'edOriginIntergal'");
    view = finder.findRequiredView(source, 2131296902, "field 'etOnekeyOil'");
    target.etOnekeyOil = finder.castView(view, 2131296902, "field 'etOnekeyOil'");
    view = finder.findRequiredView(source, 2131297386, "field 'llOil'");
    target.llOil = finder.castView(view, 2131297386, "field 'llOil'");
    view = finder.findRequiredView(source, 2131299200, "field 'vOil'");
    target.vOil = view;
    view = finder.findRequiredView(source, 2131296830, "field 'etFastShop'");
    target.etFastShop = finder.castView(view, 2131296830, "field 'etFastShop'");
    view = finder.findRequiredView(source, 2131296962, "field 'edVipRechange'");
    target.edVipRechange = finder.castView(view, 2131296962, "field 'edVipRechange'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.rvIntegralDetail = null;
    target.edOriginIntergal = null;
    target.etOnekeyOil = null;
    target.llOil = null;
    target.vOil = null;
    target.etFastShop = null;
    target.edVipRechange = null;
  }
}
