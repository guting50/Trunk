// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.paymanager.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChoiceExpensesActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.paymanager.activity.ChoiceExpensesActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBackActivity'");
    target.tvBackActivity = finder.castView(view, 2131298297, "field 'tvBackActivity'");
    view = finder.findRequiredView(source, 2131299075, "field 'tvTitlergActivity'");
    target.tvTitlergActivity = finder.castView(view, 2131299075, "field 'tvTitlergActivity'");
    view = finder.findRequiredView(source, 2131298340, "field 'tvChoiceTypeConfirm'");
    target.tvChoiceTypeConfirm = finder.castView(view, 2131298340, "field 'tvChoiceTypeConfirm'");
    view = finder.findRequiredView(source, 2131297463, "field 'lvChoiceTypeMain'");
    target.lvChoiceTypeMain = finder.castView(view, 2131297463, "field 'lvChoiceTypeMain'");
    view = finder.findRequiredView(source, 2131297793, "field 'refreshChoiceTypeMain'");
    target.refreshChoiceTypeMain = finder.castView(view, 2131297793, "field 'refreshChoiceTypeMain'");
    view = finder.findRequiredView(source, 2131297462, "field 'lvChoiceTypeChild'");
    target.lvChoiceTypeChild = finder.castView(view, 2131297462, "field 'lvChoiceTypeChild'");
    view = finder.findRequiredView(source, 2131297792, "field 'refreshChoiceTypeChild'");
    target.refreshChoiceTypeChild = finder.castView(view, 2131297792, "field 'refreshChoiceTypeChild'");
    view = finder.findRequiredView(source, 2131296301, "field 'addmaintypebutton'");
    target.addmaintypebutton = finder.castView(view, 2131296301, "field 'addmaintypebutton'");
    view = finder.findRequiredView(source, 2131296298, "field 'addchildtypebutton'");
    target.addchildtypebutton = finder.castView(view, 2131296298, "field 'addchildtypebutton'");
  }

  @Override public void unbind(T target) {
    target.tvBackActivity = null;
    target.tvTitlergActivity = null;
    target.tvChoiceTypeConfirm = null;
    target.lvChoiceTypeMain = null;
    target.refreshChoiceTypeMain = null;
    target.lvChoiceTypeChild = null;
    target.refreshChoiceTypeChild = null;
    target.addmaintypebutton = null;
    target.addchildtypebutton = null;
  }
}
