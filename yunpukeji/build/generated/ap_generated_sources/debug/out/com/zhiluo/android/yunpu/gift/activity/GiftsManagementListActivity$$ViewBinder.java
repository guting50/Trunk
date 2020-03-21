// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.gift.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GiftsManagementListActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.gift.activity.GiftsManagementListActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBackActivity'");
    target.tvBackActivity = finder.castView(view, 2131298297, "field 'tvBackActivity'");
    view = finder.findRequiredView(source, 2131297184, "field 'ivGiftManagerAdd'");
    target.ivGiftManagerAdd = finder.castView(view, 2131297184, "field 'ivGiftManagerAdd'");
    view = finder.findRequiredView(source, 2131296674, "field 'elvGiftManager'");
    target.elvGiftManager = finder.castView(view, 2131296674, "field 'elvGiftManager'");
    view = finder.findRequiredView(source, 2131297472, "field 'lvGiftManager'");
    target.lvGiftManager = finder.castView(view, 2131297472, "field 'lvGiftManager'");
    view = finder.findRequiredView(source, 2131299251, "field 'wvGiftManager'");
    target.wvGiftManager = finder.castView(view, 2131299251, "field 'wvGiftManager'");
  }

  @Override public void unbind(T target) {
    target.tvBackActivity = null;
    target.ivGiftManagerAdd = null;
    target.elvGiftManager = null;
    target.lvGiftManager = null;
    target.wvGiftManager = null;
  }
}
