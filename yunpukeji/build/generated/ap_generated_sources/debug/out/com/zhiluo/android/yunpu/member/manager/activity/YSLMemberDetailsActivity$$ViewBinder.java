// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.member.manager.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class YSLMemberDetailsActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.member.manager.activity.YSLMemberDetailsActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131297032, "field 'idVipInflateMoney'");
    target.idVipInflateMoney = finder.castView(view, 2131297032, "field 'idVipInflateMoney'");
    view = finder.findRequiredView(source, 2131297031, "field 'idVipInflateCount'");
    target.idVipInflateCount = finder.castView(view, 2131297031, "field 'idVipInflateCount'");
    view = finder.findRequiredView(source, 2131297023, "field 'idGoodsSale'");
    target.idGoodsSale = finder.castView(view, 2131297023, "field 'idGoodsSale'");
    view = finder.findRequiredView(source, 2131297026, "field 'idJiciXiaofei'");
    target.idJiciXiaofei = finder.castView(view, 2131297026, "field 'idJiciXiaofei'");
  }

  @Override public void unbind(T target) {
    target.idVipInflateMoney = null;
    target.idVipInflateCount = null;
    target.idGoodsSale = null;
    target.idJiciXiaofei = null;
  }
}
