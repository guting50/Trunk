// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ImageLoadAdapter$ViewHolder$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.adapter.ImageLoadAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131297203, "field 'mIvShop'");
    target.mIvShop = finder.castView(view, 2131297203, "field 'mIvShop'");
    view = finder.findRequiredView(source, 2131298975, "field 'tvStar'");
    target.tvStar = finder.castView(view, 2131298975, "field 'tvStar'");
    view = finder.findRequiredView(source, 2131298740, "field 'tvName'");
    target.tvName = finder.castView(view, 2131298740, "field 'tvName'");
  }

  @Override public void unbind(T target) {
    target.mIvShop = null;
    target.tvStar = null;
    target.tvName = null;
  }
}
