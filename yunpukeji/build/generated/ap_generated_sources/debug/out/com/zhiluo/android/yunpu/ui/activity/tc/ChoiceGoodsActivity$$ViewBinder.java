// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.tc;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChoiceGoodsActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.tc.ChoiceGoodsActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298336, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298336, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131298337, "field 'tvConfirm'");
    target.tvConfirm = finder.castView(view, 2131298337, "field 'tvConfirm'");
    view = finder.findRequiredView(source, 2131297166, "field 'ivSearch'");
    target.ivSearch = finder.castView(view, 2131297166, "field 'ivSearch'");
    view = finder.findRequiredView(source, 2131296782, "field 'etCondition'");
    target.etCondition = finder.castView(view, 2131296782, "field 'etCondition'");
    view = finder.findRequiredView(source, 2131297459, "field 'lvType'");
    target.lvType = finder.castView(view, 2131297459, "field 'lvType'");
    view = finder.findRequiredView(source, 2131297460, "field 'lvGoods'");
    target.lvGoods = finder.castView(view, 2131297460, "field 'lvGoods'");
    view = finder.findRequiredView(source, 2131297790, "field 'mRefresh'");
    target.mRefresh = finder.castView(view, 2131297790, "field 'mRefresh'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.tvConfirm = null;
    target.ivSearch = null;
    target.etCondition = null;
    target.lvType = null;
    target.lvGoods = null;
    target.mRefresh = null;
  }
}
