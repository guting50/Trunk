package com.zhiluo.android.yunpu.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.mvp.model.GoodsType;
import com.zhiluo.android.yunpu.mvp.view.IBaseView;
import com.zhiluo.android.yunpu.mvp.view.IWareHousingView;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 商品入库
 * 作者：罗咏哲 on 2017/10/26 11:22.
 * 邮箱：137615198@qq.com
 */

public class WareHousingPresenter implements Presenter {
    private Context mContext;
    private IWareHousingView mView;
    private List<GoodsType.DataBean> mChildTypeList;
    private List<GoodsType.DataBean> mParentTypeList = new ArrayList<>();
    private Map<GoodsType.DataBean, List<GoodsType.DataBean>> map = new HashMap<>();

    public WareHousingPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate(String type) {
        getType(type);
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    @Override
    public void attachView(IBaseView view) {
        mView = (IWareHousingView) view;
    }
    public void detachView(IBaseView view){
        mView=null;
    }
    private void getType(final String s) {
        HttpHelper.post(mContext, HttpAPI.API().PRODUCTTYPE, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                GoodsType type = CommonFun.JsonToObj(responseString, GoodsType.class);
                GoodsType.DataBean bean = new GoodsType.DataBean();
                bean.setPT_Name("所有");
                bean.setGID("");
                mParentTypeList.add(bean);
                map.put(bean, new ArrayList<GoodsType.DataBean>());

                if (s != null && (s.equals("HYCC")||s.equals("SPXF"))) {
                    GoodsType.DataBean bean2 = new GoodsType.DataBean();
                    bean2.setPT_Name("套餐");
                    bean2.setGID("combo");
                    mParentTypeList.add(bean2);
                    map.put(bean2, new ArrayList<GoodsType.DataBean>());
                }

                //获取父类型
                for (int i = 0; i < type.getData().size(); i++) {
                    if (type.getData().get(i).getPT_Parent() == null || "".equals(type.getData().get(i).getPT_Parent())) {
                        mParentTypeList.add(type.getData().get(i));
                    }
                }
                //获取子类型
                for (int i = 0; i < mParentTypeList.size(); i++) {
                    mChildTypeList = new ArrayList<>();
                    for (int j = 0; j < type.getData().size(); j++) {
                        if (!"".equals(mParentTypeList.get(i).getGID()) &&
                                mParentTypeList.get(i).getGID().equals(type.getData().get(j).getPT_Parent())) {
                            mChildTypeList.add(type.getData().get(j));
                        }
                    }
                    if (mChildTypeList != null) {
                        map.put(mParentTypeList.get(i), mChildTypeList);
                        mChildTypeList = null;
                    }
                }
                mView.getTypeSuccess(mParentTypeList, map);
            }

            @Override
            public void onFailure(String msg) {
                mView.getTypeFail(msg);
            }
        });
    }

    public void getGoods(RequestParams params) {
        HttpHelper.post(mContext, HttpAPI.API().SERVICEGOODS, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                Li("商品列表----"+responseString);
                GoodsCheckResponseByType entity = CommonFun.JsonToObj(responseString, GoodsCheckResponseByType.class);
                mView.getGoodsSuccess(entity);
            }

            @Override
            public void onFailure(String msg) {
                mView.getGoodsFail(msg);
            }
        });
    }

    public void getcombo(RequestParams params) {
        getcombo(params,true);
    }

    public void getcombo(RequestParams params,boolean showLoading) {
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                GoodsCheckResponseByType entity = CommonFun.JsonToObj(responseString, GoodsCheckResponseByType.class);
                mView.getComboSuccess(entity);
            }

            @Override
            public void onFailure(String msg) {
                mView.getCombofail(msg);
            }
        };
        if(showLoading){
            callBack.setLoadingAnimation(mContext,"加载中...",false);
        }
        HttpHelper.post(mContext, HttpAPI.API().COMBOGOODS, params, callBack);
    }
}
