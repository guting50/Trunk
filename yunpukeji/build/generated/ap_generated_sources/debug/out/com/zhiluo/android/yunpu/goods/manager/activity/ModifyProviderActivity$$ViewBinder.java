// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.goods.manager.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ModifyProviderActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.goods.manager.activity.ModifyProviderActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296420, "field 'btnProviderRestore'");
    target.btnProviderRestore = finder.castView(view, 2131296420, "field 'btnProviderRestore'");
    view = finder.findRequiredView(source, 2131296745, "field 'etBrand'");
    target.etBrand = finder.castView(view, 2131296745, "field 'etBrand'");
    view = finder.findRequiredView(source, 2131296751, "field 'etRemark'");
    target.etRemark = finder.castView(view, 2131296751, "field 'etRemark'");
    view = finder.findRequiredView(source, 2131297591, "field 'providerTitle'");
    target.providerTitle = finder.castView(view, 2131297591, "field 'providerTitle'");
  }

  @Override public void unbind(T target) {
    target.btnProviderRestore = null;
    target.etBrand = null;
    target.etRemark = null;
    target.providerTitle = null;
  }
}
