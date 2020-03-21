package com.zhiluo.android.yunpu.mvp.view;

/**
 * 作者：罗咏哲 on 2017/10/23 17:33.
 * 邮箱：137615198@qq.com
 */

public interface IHomePageView extends IBaseView {
    void loadDataSuccess();

    void loadDataFail(String msg);
}
