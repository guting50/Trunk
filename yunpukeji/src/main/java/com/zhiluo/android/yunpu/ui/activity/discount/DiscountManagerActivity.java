package com.zhiluo.android.yunpu.ui.activity.discount;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.RechargeListBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.activity.recharge.AddRechargeActivity;
import com.zhiluo.android.yunpu.ui.activity.recharge.KRechargeDetailActivity;
import com.zhiluo.android.yunpu.ui.activity.recharge.RechargeManagerActivity;
import com.zhiluo.android.yunpu.ui.adapter.RechagreManagerAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 优惠活动列表
 */
public class DiscountManagerActivity extends AppCompatActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.lv_discount_activity)
    ListView mListView;
    @Bind(R.id.refresh_discount_manager)
    WaveSwipeRefreshLayout mRefresh;
    @Bind(R.id.fab)
    FloatingActionButton mFab;

    private RechargeListBean mRechargeListBean;
    private RechagreManagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_manager);
        ButterKnife.bind(this);
        getData();
        setListener();
    }
    private void getData() {
        RequestParams params = new RequestParams();
        params.put("RP_Type", 2);
        HttpHelper.post(this, HttpAPI.API().GET_DISCOUNT_TYPE, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mRechargeListBean = CommonFun.JsonToObj(responseString, RechargeListBean.class);
                mAdapter = new RechagreManagerAdapter(DiscountManagerActivity.this, mRechargeListBean.getData());
                mListView.setAdapter(mAdapter);
                mRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(DiscountManagerActivity.this, msg, Toast.LENGTH_SHORT).show();
                mRefresh.setRefreshing(false);
            }
        });
    }

    private void setListener() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiscountManagerActivity.this, AddDisActivity.class);
                startActivity(intent);
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DiscountManagerActivity.this, DisDetailActivity.class);
                intent.putExtra("Recharge", mRechargeListBean.getData().get(position));
                startActivity(intent);
            }
        });
        mRefresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }

            @Override
            public void onLoad() {

            }

            @Override
            public boolean canLoadMore() {
                return false;
            }

            @Override
            public boolean canRefresh() {
                return true;
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getData();
    }
}
