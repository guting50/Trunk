package com.zhiluo.android.yunpu.goods.consume.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.consume.GoodsSpecsListEvent;
import com.zhiluo.android.yunpu.goods.consume.NotifyShopCarEvent;
import com.zhiluo.android.yunpu.goods.consume.adapter.GoodsSprcsAdater;
import com.zhiluo.android.yunpu.goods.consume.bean.GoodsSpecsListBean;
import com.zhiluo.android.yunpu.goods.manager.activity.GoodsDetailActivity;
import com.zhiluo.android.yunpu.goods.manager.activity.GoodsManagerActivity;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static com.zhiluo.android.yunpu.goods.consume.activity.GoodsConsumeActivity.mHaveChoosedGoodList;

public class GoodsSpecsActivity extends AppCompatActivity implements GoodsSprcsAdater.ItemViewClick {

    private GoodsSprcsAdater mGoodsSprcsAdater;
    private BaseListView mGoodsListView;//商品
    private List<GoodsSpecsListBean.DataBean> mGoodsSpecsList;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mGoodsList = new ArrayList<>();//商品信息
    //已选商品信息
    private String mGroupGID;
    private TextView mTvBack, mTvDetermine;
    private String load = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_specs);


        Intent intent = getIntent();
        mGroupGID = intent.getStringExtra("groupGID");
        load = intent.getStringExtra("tancan");
        EventBus.getDefault().register(this);
        Log.d("55555555", load + "--------");
        initView();
        setListener();
        if (TextUtils.isEmpty(load)) {
            loadData();
        } else {
            setAdapter();
        }
    }

    private void initView() {

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//进入页面关闭软键盘
        mGoodsListView = (BaseListView) findViewById(R.id.lv_goods_specs);
        mTvBack = (TextView) findViewById(R.id.tv_goods_specs_back);
        mTvDetermine = (TextView) findViewById(R.id.tv_goods_specs_determine);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotifyShopCarEvent notifyShopCarEvent = new NotifyShopCarEvent();
                notifyShopCarEvent.setMsg("goods_consume_shop_car_refresh");
                EventBus.getDefault().post(notifyShopCarEvent);
                finish();
            }
        });
        mTvDetermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHaveChoosedGoodList.size() == 0) {
                    CustomToast.makeText(GoodsSpecsActivity.this, "未选择任何商品，请选择商品", Toast.LENGTH_SHORT).show();
                    return;
                }
                NotifyShopCarEvent notifyShopCarEvent = new NotifyShopCarEvent();
                notifyShopCarEvent.setMsg("goods_consume_shop_car_refresh");
                EventBus.getDefault().post(notifyShopCarEvent);
                finish();
            }
        });
    }

    /**
     * 加载数据
     */
    private void loadData() {

        RequestParams params = new RequestParams();
        params.put("GroupGID", mGroupGID);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                GoodsSpecsListBean entity = CommonFun.JsonToObj(responseString, GoodsSpecsListBean.class);
                if (entity != null) {
                    mGoodsSpecsList = entity.getData();
                    for (int i = 0; i < mGoodsSpecsList.size(); i++) {
                        String data = new Gson().toJson(mGoodsSpecsList.get(i)) + "";
                        GoodsCheckResponseByType.DataBean.DataListBean bean = new Gson().fromJson(data, GoodsCheckResponseByType.DataBean.DataListBean.class);
                        mGoodsList.add(bean);
                    }
                }
//                if (mGoodsList.size() != 0 && mHaveChoosedGoodList.size() != 0){
//                    for (int i = 0;i < mHaveChoosedGoodList.size();i++){
//                        for (int j = 0;j < mGoodsList.size();j++){
//                            if (mHaveChoosedGoodList.get(i).getGID().equals(mGoodsList.get(j).getGID())){
//                                mGoodsList.get(j).setNum(mHaveChoosedGoodList.get(i).getNum());
//                            }
//                        }
//                    }
//                }
                setAdapter();
            }

            @Override
            public void onFailure(String msg) {

            }
        };
        callBack.setLoadingAnimation(this, "加载中...", false);
        HttpHelper.post(this, HttpAPI.API().GROUPGOODSLIST, params, callBack);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEventMemberValueRefresh(GoodsSpecsListEvent goodsSpecsListEvent) {
        if (goodsSpecsListEvent.getMsg().equals("taocan_list") && load.equals("taocan")) {
            mGoodsList.add(goodsSpecsListEvent.getmGoodsList());
        }
    }

    /**
     * 设置ListViewAdapter
     */
    private void setAdapter() {
        if (mGoodsSprcsAdater == null) {
            mGoodsSprcsAdater = new GoodsSprcsAdater(GoodsSpecsActivity.this,
                    mGoodsList, GoodsSpecsActivity.this);
            mGoodsListView.setAdapter(mGoodsSprcsAdater);
        } else {
            mGoodsSprcsAdater.setParm(mGoodsList);
            mGoodsSprcsAdater.notifyDataSetChanged();
        }

        mGoodsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GoodsCheckResponseByType.DataBean.DataListBean bean = mGoodsList.get(i);
                Intent intent = new Intent(GoodsSpecsActivity.this, GoodsDetailActivity.class);
                intent.putExtra("Goods", bean);
                //多规格
                intent.putExtra("extra_many", "many");
                startActivity(intent);
            }
        });
    }

    @Override
    public void click(View view) {
        int position = (int) view.getTag();
        GoodsCheckResponseByType.DataBean.DataListBean bean = mGoodsList.get(position);
        String gid = bean.getGID();
        if (!mHaveChoosedGoodList.isEmpty()) {
            for (int i = 0; i < mHaveChoosedGoodList.size(); i++) {
                if (mHaveChoosedGoodList.get(i).getGID().equals(gid)) {
                    mHaveChoosedGoodList.remove(i);
                }
            }
        }
        double num;
        num = bean.getNum();
        switch (view.getId()) {
            //移除
            case R.id.iv_reduce:
                if (num > 0) {
                    num--;
                } else {
                    num = 0;
                }
                bean.setNum(num);
                if (num > 0) {
                    mHaveChoosedGoodList.add(bean);
                }
//                countGoodsNum();
                mGoodsSprcsAdater.notifyDataSetChanged();
                break;
            //增加
            case R.id.iv_add:
//                setAddGoodsAnimation(view);
                num++;
                bean.setNum(num);
                mHaveChoosedGoodList.add(bean);
//                countGoodsNum();
                mGoodsSprcsAdater.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
