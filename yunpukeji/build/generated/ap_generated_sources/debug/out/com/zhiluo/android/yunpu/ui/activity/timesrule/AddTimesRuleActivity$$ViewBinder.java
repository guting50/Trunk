// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.timesrule;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AddTimesRuleActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.timesrule.AddTimesRuleActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBackActivity'");
    target.tvBackActivity = finder.castView(view, 2131298297, "field 'tvBackActivity'");
    view = finder.findRequiredView(source, 2131298273, "field 'tvAddRechargeSave'");
    target.tvAddRechargeSave = finder.castView(view, 2131298273, "field 'tvAddRechargeSave'");
    view = finder.findRequiredView(source, 2131296756, "field 'etAddRechargeName'");
    target.etAddRechargeName = finder.castView(view, 2131296756, "field 'etAddRechargeName'");
    view = finder.findRequiredView(source, 2131296755, "field 'etAddRechargeMoney'");
    target.etAddRechargeMoney = finder.castView(view, 2131296755, "field 'etAddRechargeMoney'");
    view = finder.findRequiredView(source, 2131296473, "field 'cbDay'");
    target.cbDay = finder.castView(view, 2131296473, "field 'cbDay'");
    view = finder.findRequiredView(source, 2131296541, "field 'cbWeek'");
    target.cbWeek = finder.castView(view, 2131296541, "field 'cbWeek'");
    view = finder.findRequiredView(source, 2131296494, "field 'cbMonth'");
    target.cbMonth = finder.castView(view, 2131296494, "field 'cbMonth'");
    view = finder.findRequiredView(source, 2131296544, "field 'cbYear'");
    target.cbYear = finder.castView(view, 2131296544, "field 'cbYear'");
  }

  @Override public void unbind(T target) {
    target.tvBackActivity = null;
    target.tvAddRechargeSave = null;
    target.etAddRechargeName = null;
    target.etAddRechargeMoney = null;
    target.cbDay = null;
    target.cbWeek = null;
    target.cbMonth = null;
    target.cbYear = null;
  }
}
