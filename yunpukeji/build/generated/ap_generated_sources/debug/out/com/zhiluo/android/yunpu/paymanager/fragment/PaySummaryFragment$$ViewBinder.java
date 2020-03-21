// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.paymanager.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PaySummaryFragment$$ViewBinder<T extends com.zhiluo.android.yunpu.paymanager.fragment.PaySummaryFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298784, "field 'tvPayDay'");
    target.tvPayDay = finder.castView(view, 2131298784, "field 'tvPayDay'");
    view = finder.findRequiredView(source, 2131298394, "field 'tvDaySummmary'");
    target.tvDaySummmary = finder.castView(view, 2131298394, "field 'tvDaySummmary'");
    view = finder.findRequiredView(source, 2131298789, "field 'tvPayMonth'");
    target.tvPayMonth = finder.castView(view, 2131298789, "field 'tvPayMonth'");
    view = finder.findRequiredView(source, 2131298736, "field 'tvMonthSummmary'");
    target.tvMonthSummmary = finder.castView(view, 2131298736, "field 'tvMonthSummmary'");
    view = finder.findRequiredView(source, 2131298796, "field 'tvPayYear'");
    target.tvPayYear = finder.castView(view, 2131298796, "field 'tvPayYear'");
    view = finder.findRequiredView(source, 2131299163, "field 'tvYearSummmary'");
    target.tvYearSummmary = finder.castView(view, 2131299163, "field 'tvYearSummmary'");
    view = finder.findRequiredView(source, 2131296321, "field 'beiSaierView'");
    target.beiSaierView = finder.castView(view, 2131296321, "field 'beiSaierView'");
    view = finder.findRequiredView(source, 2131297937, "field 'rlPaySummary'");
    target.rlPaySummary = finder.castView(view, 2131297937, "field 'rlPaySummary'");
  }

  @Override public void unbind(T target) {
    target.tvPayDay = null;
    target.tvDaySummmary = null;
    target.tvPayMonth = null;
    target.tvMonthSummmary = null;
    target.tvPayYear = null;
    target.tvYearSummmary = null;
    target.beiSaierView = null;
    target.rlPaySummary = null;
  }
}
