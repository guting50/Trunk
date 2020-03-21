// Generated code from Butter Knife. Do not modify!
package com.zhiluo.android.yunpu.ui.activity.user;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class EditUserActivity$$ViewBinder<T extends com.zhiluo.android.yunpu.ui.activity.user.EditUserActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131298297, "field 'tvBack'");
    target.tvBack = finder.castView(view, 2131298297, "field 'tvBack'");
    view = finder.findRequiredView(source, 2131298435, "field 'tvSave'");
    target.tvSave = finder.castView(view, 2131298435, "field 'tvSave'");
    view = finder.findRequiredView(source, 2131296767, "field 'etAccount'");
    target.etAccount = finder.castView(view, 2131296767, "field 'etAccount'");
    view = finder.findRequiredView(source, 2131296825, "field 'etName'");
    target.etName = finder.castView(view, 2131296825, "field 'etName'");
    view = finder.findRequiredView(source, 2131296826, "field 'etPhone'");
    target.etPhone = finder.castView(view, 2131296826, "field 'etPhone'");
    view = finder.findRequiredView(source, 2131296827, "field 'etRemark'");
    target.etRemark = finder.castView(view, 2131296827, "field 'etRemark'");
    view = finder.findRequiredView(source, 2131298901, "field 'tvRole'");
    target.tvRole = finder.castView(view, 2131298901, "field 'tvRole'");
    view = finder.findRequiredView(source, 2131298931, "field 'tvShop'");
    target.tvShop = finder.castView(view, 2131298931, "field 'tvShop'");
    view = finder.findRequiredView(source, 2131296562, "field 'civAddMemberHead'");
    target.civAddMemberHead = finder.castView(view, 2131296562, "field 'civAddMemberHead'");
    view = finder.findRequiredView(source, 2131296772, "field 'etAddUserNum'");
    target.etAddUserNum = finder.castView(view, 2131296772, "field 'etAddUserNum'");
    view = finder.findRequiredView(source, 2131298581, "field 'tvIssuoding'");
    target.tvIssuoding = finder.castView(view, 2131298581, "field 'tvIssuoding'");
  }

  @Override public void unbind(T target) {
    target.tvBack = null;
    target.tvSave = null;
    target.etAccount = null;
    target.etName = null;
    target.etPhone = null;
    target.etRemark = null;
    target.tvRole = null;
    target.tvShop = null;
    target.civAddMemberHead = null;
    target.etAddUserNum = null;
    target.tvIssuoding = null;
  }
}
