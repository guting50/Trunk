package com.zhiluo.android.yunpu.mvp.view;

import com.zhiluo.android.yunpu.member.manager.bean.MemberInfoBean;

/**
 * Created by ZPH on 2019-02-15.
 */

public interface IPostVipView extends IBaseView {


  void getvipsuccesss(MemberInfoBean bean);
  void getvipfail(String result);


}
