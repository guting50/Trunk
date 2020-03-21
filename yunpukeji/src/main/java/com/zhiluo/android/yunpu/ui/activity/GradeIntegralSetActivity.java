package com.zhiluo.android.yunpu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.GradeSetBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.manager.bean.MemberGrade;
import com.zhiluo.android.yunpu.ui.adapter.GradeIntegralSetAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date: 2018/1/15 15:22
 * Author: 罗咏哲
 * Description: 会员等级商品积分详情设置
 * Version:
 */
public class GradeIntegralSetActivity extends AppCompatActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.tv_grade_integral_set_save)
    TextView tvSave;
    @Bind(R.id.rv_integral_set)
    RecyclerView mRecyclerView;
    private GradeIntegralSetAdapter mSetAdapter;
    private GradeSetBean mSetBean;
    private String mGid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_integral_set);
        ButterKnife.bind(this);
        mGid = getIntent().getStringExtra("GID");
        mSetBean = (GradeSetBean) getIntent().getSerializableExtra("GD");
        if (mSetBean == null) {
            getData();
        } else {
            mSetAdapter = new GradeIntegralSetAdapter(mSetBean);
            LinearLayoutManager manager = new LinearLayoutManager(GradeIntegralSetActivity.this);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.setAdapter(mSetAdapter);
        }
        setListener();
    }

    private void setListener() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("integral_set", mSetBean);
                setResult(11, intent);
                finish();
            }
        });
    }

    private void getData() {
        RequestParams params = new RequestParams();
        params.put("GID", mGid);
        HttpHelper.post(this, "VIPGrade/GetPTRemoveParentList", params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSetBean = CommonFun.JsonToObj(responseString, GradeSetBean.class);
                mSetAdapter = new GradeIntegralSetAdapter(mSetBean);
                LinearLayoutManager manager = new LinearLayoutManager(GradeIntegralSetActivity.this);
                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.setAdapter(mSetAdapter);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(GradeIntegralSetActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
