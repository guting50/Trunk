package com.zhiluo.android.yunpu.mvp.view;

import com.zhiluo.android.yunpu.home.jsonbean.SingleShopInfoJsonBean;
import com.zhiluo.android.yunpu.mvp.model.UpLoadPictureEntity;

/**
 * 作者：罗咏哲 on 2017/10/25 11:31.
 * 邮箱：137615198@qq.com
 */

public interface IMyView extends IBaseView {
    void getCurrentStoreSuccess(SingleShopInfoJsonBean bean);

    void getCurrentStoreFail(String result);

    void upStorePictureSuccess(UpLoadPictureEntity entity);

    void upStorePictureFail(String result);
}
