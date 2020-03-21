// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.statistics.account.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MemberSignScreenActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.statistics.account.activity.MemberSignScreenActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298884, "field 'tvReportScreenBack'");
    target.tvReportScreenBack = finder.castView(view, 2131298884, "field 'tvReportScreenBack'");
    view = finder.findRequiredView(source, 2131298560, "field 'tvHeadTitle'");
    target.tvHeadTitle = finder.castView(view, 2131298560, "field 'tvHeadTitle'");
    view = finder.findRequiredView(source, 2131298885, "field 'tvReportScreenConfirm'");
    target.tvReportScreenConfirm = finder.castView(view, 2131298885, "field 'tvReportScreenConfirm'");
    view = finder.findRequiredView(source, 2131296924, "field 'etReportScreenSearch'");
    target.etReportScreenSearch = finder.castView(view, 2131296924, "field 'etReportScreenSearch'");
    view = finder.findRequiredView(source, 2131297373, "field 'llGoodsConsumeScan'");
    target.llGoodsConsumeScan = finder.castView(view, 2131297373, "field 'llGoodsConsumeScan'");
    view = finder.findRequiredView(source, 2131296932, "field 'etSearchByOrder'");
    target.etSearchByOrder = finder.castView(view, 2131296932, "field 'etSearchByOrder'");
    view = finder.findRequiredView(source, 2131298065, "field 'spMoneyStore'");
    target.spMoneyStore = finder.castView(view, 2131298065, "field 'spMoneyStore'");
    view = finder.findRequiredView(source, 2131296927, "field 'etSearchByCreator'");
    target.etSearchByCreator = finder.castView(view, 2131296927, "field 'etSearchByCreator'");
    view = finder.findRequiredView(source, 2131296422, "field 'btnReportScreenClean'");
    target.btnReportScreenClean = finder.castView(view, 2131296422, "field 'btnReportScreenClean'");
  }

  @Override public void unbind(T target) {
    target.tvReportScreenBack = null;
    target.tvHeadTitle = null;
    target.tvReportScreenConfirm = null;
    target.etReportScreenSearch = null;
    target.llGoodsConsumeScan = null;
    target.etSearchByOrder = null;
    target.spMoneyStore = null;
    target.etSearchByCreator = null;
    target.btnReportScreenClean = null;
  }
}
