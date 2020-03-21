package com.zhiluo.android.yunpu.statistics.account.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.statistics.order.adapter.JiCiAdapter;
import com.zhiluo.android.yunpu.statistics.order.bean.JiCiBean;
import com.zhiluo.android.yunpu.utils.CommonFun;

/**
 * Created by YSL on 2018-06-08.
 */

public class JiCiActivity extends AppCompatActivity {
    private JiCiAdapter adapter;
    private RecyclerView recyclerView;
    private TextView tvBack,tvnull;
    private String vipCard;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jici);
        initViews();
        initDatas();
    }

    private void initDatas() {
        vipCard = getIntent().getStringExtra("vipcard");
        RequestParams params = new RequestParams();
        params.put("Card", vipCard);

        HttpHelper.post(this, HttpAPI.API().CHARGELIST, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                JiCiBean bean = CommonFun.JsonToObj(responseString, JiCiBean.class);
                if (bean.getData().size() >0){
                    tvnull.setVisibility(View.GONE);
                }else {
                    tvnull.setVisibility(View.VISIBLE);
                }
                adapter = new JiCiAdapter(JiCiActivity.this, bean);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler_view);
        tvBack = findViewById(R.id.tv_back);
        tvnull = findViewById(R.id.tv_null);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JiCiActivity.this.finish();
            }
        });
    }
}
