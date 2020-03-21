// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.paymanager.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PayDetailScreenActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.paymanager.activity.PayDetailScreenActivity> implements ViewBinder<T> {
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
    view = finder.findRequiredView(source, 2131297313, "field 'lYout'");
    target.lYout = finder.castView(view, 2131297313, "field 'lYout'");
    view = finder.findRequiredView(source, 2131298074, "field 'spTypeOne'");
    target.spTypeOne = finder.castView(view, 2131298074, "field 'spTypeOne'");
    view = finder.findRequiredView(source, 2131298075, "field 'spTypeTwo'");
    target.spTypeTwo = finder.castView(view, 2131298075, "field 'spTypeTwo'");
    view = finder.findRequiredView(source, 2131296422, "field 'btnReportScreenClean'");
    target.btnReportScreenClean = finder.castView(view, 2131296422, "field 'btnReportScreenClean'");
  }

  @Override public void unbind(T target) {
    target.tvReportScreenBack = null;
    target.tvHeadTitle = null;
    target.tvReportScreenConfirm = null;
    target.etReportScreenSearch = null;
    target.llGoodsConsumeScan = null;
    target.lYout = null;
    target.spTypeOne = null;
    target.spTypeTwo = null;
    target.btnReportScreenClean = null;
  }
}
