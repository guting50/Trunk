// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HardwareFragment$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.fragment.HardwareFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131297192, "field 'ivCard'");
    target.ivCard = finder.castView(view, 2131297192, "field 'ivCard'");
    view = finder.findRequiredView(source, 2131297194, "field 'ivFlush'");
    target.ivFlush = finder.castView(view, 2131297194, "field 'ivFlush'");
    view = finder.findRequiredView(source, 2131297200, "field 'ivSunmi'");
    target.ivSunmi = finder.castView(view, 2131297200, "field 'ivSunmi'");
    view = finder.findRequiredView(source, 2131297198, "field 'ivPrint'");
    target.ivPrint = finder.castView(view, 2131297198, "field 'ivPrint'");
    view = finder.findRequiredView(source, 2131297197, "field 'ivPaper'");
    target.ivPaper = finder.castView(view, 2131297197, "field 'ivPaper'");
    view = finder.findRequiredView(source, 2131297199, "field 'ivScan'");
    target.ivScan = finder.castView(view, 2131297199, "field 'ivScan'");
    view = finder.findRequiredView(source, 2131297195, "field 'ivKeyboard'");
    target.ivKeyboard = finder.castView(view, 2131297195, "field 'ivKeyboard'");
    view = finder.findRequiredView(source, 2131297196, "field 'ivMoneyBox'");
    target.ivMoneyBox = finder.castView(view, 2131297196, "field 'ivMoneyBox'");
    view = finder.findRequiredView(source, 2131297193, "field 'ivDisplay'");
    target.ivDisplay = finder.castView(view, 2131297193, "field 'ivDisplay'");
    view = finder.findRequiredView(source, 2131297201, "field 'ivVoiceBox'");
    target.ivVoiceBox = finder.castView(view, 2131297201, "field 'ivVoiceBox'");
  }

  @Override public void unbind(T target) {
    target.ivCard = null;
    target.ivFlush = null;
    target.ivSunmi = null;
    target.ivPrint = null;
    target.ivPaper = null;
    target.ivScan = null;
    target.ivKeyboard = null;
    target.ivMoneyBox = null;
    target.ivDisplay = null;
    target.ivVoiceBox = null;
  }
}
