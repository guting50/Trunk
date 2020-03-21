package com.zhiluo.android.yunpu.mvp.view;

import com.zhiluo.android.yunpu.home.jsonbean.MessageCenterJsonBean;

/**
 * 作者：罗咏哲 on 2017/10/24 14:23.
 * 邮箱：137615198@qq.com
 */

public interface IMessageFragmentView extends IBaseView {
    void getMessageListSuccess(MessageCenterJsonBean bean);

    void getMessageListFail(String result);
    void markSuccess();
    void markFail(String result);
}
