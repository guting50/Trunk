package com.zhiluo.android.yunpu.ui.activity.timesrule;

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
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.GradeDetailActivity;
import com.zhiluo.android.yunpu.ui.activity.YSLAddNewVipLevelActivity;
import com.zhiluo.android.yunpu.ui.activity.tc.TCManagerActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ZPH on 2018-11-30.
 */

public class TimesRuleManagerActivity extends BaseActivity {


    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.lv_member_grade)
    ListView mListView;
    @Bind(R.id.refresh_grade_manager)
    WaveSwipeRefreshLayout mRefrsh;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    private TimesRuleManagerBean rulebean;
    private TimesRuleAdapter mAdapter;
    private boolean mIsLoadMore;
    private int mRefreshIndex = 2;
    private int mPageTotal;//数据总页数
    private TimesRuleManagerBean.DataBean mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_tims_rule_manager);
        ButterKnife.bind(this);

        getrules(1,20);//获取会员等级
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TimesRuleManagerBean.DataBean.DataListBean bean = mList.getDataList().get(position);
                Intent intent = new Intent(TimesRuleManagerActivity.this, TimesRuleDetail.class);
                intent.putExtra("rule", bean);
                startActivity(intent);
            }
        });
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimesRuleManagerActivity.this, AddTimesRuleActivity.class);
                startActivity(intent);
            }
        });
        mRefrsh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getrules(1,20);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    getrules(mRefreshIndex, 20);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(TimesRuleManagerActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    mRefrsh.setLoading(false);
                }
            }

            @Override
            public boolean canLoadMore() {
                return true;
            }

            @Override
            public boolean canRefresh() {
                return true;
            }
        });

    }

    private void getrules(int index, int pageSize) {
        RequestParams params = new RequestParams();
        params.put("PageIndex",index);
        params.put("PageSize",pageSize);
        params.put("WR_Name", "");
        HttpHelper.post(this, HttpAPI.API().LIST_RULES, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                rulebean = CommonFun.JsonToObj(responseString, TimesRuleManagerBean.class);

                mPageTotal = rulebean.getData().getPageTotal();
                if (mList != null && mIsLoadMore) {
                    mList.getDataList().addAll(rulebean.getData().getDataList());
                } else {
                    mList = rulebean.getData();
                }
                setmAdapter();
                mRefrsh.setRefreshing(false);
                mRefrsh.setLoading(false);
                mIsLoadMore = false;

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(TimesRuleManagerActivity.this, msg, Toast.LENGTH_SHORT).show();
                mRefrsh.setRefreshing(false);
                mIsLoadMore = false;
            }
        });
    }

    private void setmAdapter(){
        if (mAdapter ==null){
            mAdapter = new TimesRuleAdapter(TimesRuleManagerActivity.this, mList);
            mListView.setAdapter(mAdapter);
        }else {
            mAdapter.setParam(mList);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getrules(1,20);
    }
}
