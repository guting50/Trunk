package com.zhiluo.android.yunpu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.adapter.GradeDiscountSetAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GradeDiscountSetActivity extends AppCompatActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.tv_grade_discount_set_save)
    TextView tvSave;
    @Bind(R.id.rv_discount_set)
    RecyclerView mRecyclerView;
    private GradeDiscountSetAdapter mAdapter;
    private DataBean mSetBean;
    private String mGid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_discount_set);
        ButterKnife.bind(this);
        mGid = getIntent().getStringExtra("GID");
        mSetBean = (DataBean) getIntent().getSerializableExtra("ZK");
        if (mSetBean == null) {
            getData();
        } else {
            mAdapter = new GradeDiscountSetAdapter(mSetBean);
            LinearLayoutManager manager = new LinearLayoutManager(GradeDiscountSetActivity.this);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.setAdapter(mAdapter);
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
                intent.putExtra("discount_set", (Serializable) mSetBean);
                setResult(12, intent);
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
                mSetBean = CommonFun.JsonToObj(responseString, DataBean.class);
                mAdapter = new GradeDiscountSetAdapter(mSetBean);
                LinearLayoutManager manager = new LinearLayoutManager(GradeDiscountSetActivity.this);
                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(GradeDiscountSetActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
