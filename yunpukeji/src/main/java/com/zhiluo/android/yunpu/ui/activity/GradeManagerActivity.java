package com.zhiluo.android.yunpu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberGrade;
import com.zhiluo.android.yunpu.ui.adapter.MemberGradeAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date: 2018/1/8 16:02
 * Author: 罗咏哲
 * Description: 会员等级管理
 * Version:
 */
public class GradeManagerActivity extends BaseActivity {
    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.lv_member_grade)
    ListView mListView;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.refresh_grade_manager)
    WaveSwipeRefreshLayout mRefrsh;
    private String mUrl = "file:///android_asset/html/GradeManagerList.html";
    private String mResponse;//返回数据
    private MemberGrade mMemberGrade;
    private MemberGradeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_manager);
        ButterKnife.bind(this);
        getMemberGrade();//获取会员等级
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataBean bean = mMemberGrade.getData().get(position);
                Intent intent = new Intent(GradeManagerActivity.this, GradeDetailActivity.class);
                intent.putExtra("Grade", bean);
                startActivity(intent);
            }
        });
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GradeManagerActivity.this, YSLAddNewVipLevelActivity.class);
                startActivity(intent);
            }
        });
        mRefrsh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMemberGrade();
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

    private void getMemberGrade() {
        RequestParams params = new RequestParams();
        params.put("GID", "");
        HttpHelper.post(this, HttpAPI.API().USERGRADE, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mResponse = responseString;
                mMemberGrade = CommonFun.JsonToObj(responseString, MemberGrade.class);
                mAdapter = new MemberGradeAdapter(GradeManagerActivity.this, mMemberGrade.getData());
                mListView.setAdapter(mAdapter);
                mRefrsh.setRefreshing(false);
                getAllMessage();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(GradeManagerActivity.this, msg, Toast.LENGTH_SHORT).show();
                mRefrsh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMemberGrade();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMemberGrade();
    }

    /***
     *
     * 更新会员等级缓存
     *
     * **/

    private void getAllMessage() {
        HttpHelper.post(this, HttpAPI.API().PRE_LOAD, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ReportMessageBean reportbean = CommonFun.JsonToObj(responseString, ReportMessageBean.class);
                if (reportbean != null) {
                    List<ReportMessageBean.DataBean.VIPGradeListBean> gradebean = reportbean.getData().getVIPGradeList();
                    CacheData.saveObject("grade", gradebean);//缓存等级列表到本地
                }

            }

            @Override
            public void onFailure(String msg) {
            }
        });
    }
}
