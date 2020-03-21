// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.good;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class InWareRetureActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.good.InWareRetureActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298296, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298296, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131299055, "field 'tvTitel'");
    target.tvTitel = finder.castView(view, 2131299055, "field 'tvTitel'");
    view = finder.findRequiredView(source, 2131297977, "field 'rlTitle'");
    target.rlTitle = finder.castView(view, 2131297977, "field 'rlTitle'");
    view = finder.findRequiredView(source, 2131298756, "field 'tvOderNumber'");
    target.tvOderNumber = finder.castView(view, 2131298756, "field 'tvOderNumber'");
    view = finder.findRequiredView(source, 2131299009, "field 'tvSupplierName'");
    target.tvSupplierName = finder.castView(view, 2131299009, "field 'tvSupplierName'");
    view = finder.findRequiredView(source, 2131298794, "field 'tvPayType'");
    target.tvPayType = finder.castView(view, 2131298794, "field 'tvPayType'");
    view = finder.findRequiredView(source, 2131299085, "field 'tvTotalPrice'");
    target.tvTotalPrice = finder.castView(view, 2131299085, "field 'tvTotalPrice'");
    view = finder.findRequiredView(source, 2131298359, "field 'tvComfirm'");
    target.tvComfirm = finder.castView(view, 2131298359, "field 'tvComfirm'");
    view = finder.findRequiredView(source, 2131297490, "field 'lvInwareReture'");
    target.lvInwareReture = finder.castView(view, 2131297490, "field 'lvInwareReture'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.tvTitel = null;
    target.rlTitle = null;
    target.tvOderNumber = null;
    target.tvSupplierName = null;
    target.tvPayType = null;
    target.tvTotalPrice = null;
    target.tvComfirm = null;
    target.lvInwareReture = null;
  }
}
