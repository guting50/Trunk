package com.zhiluo.android.yunpu.ui.activity.tc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.TCBean;
import com.zhiluo.android.yunpu.entity.TCListBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.manager.activity.MemberListActivity;
import com.zhiluo.android.yunpu.ui.adapter.TCManagerAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TCManagerActivity extends AppCompatActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.lv_member_tc)
    ListView mListView;
    @Bind(R.id.refresh_tc_manager)
    WaveSwipeRefreshLayout mRefresh;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    private TCListBean mTcListBean;
    private TCManagerAdapter mAdapter;
    private List<TCBean> mList;
    private int mCurrentPosition;//ListView当前滑动位置
    private boolean mIsLoadMore;
    private int mRefreshIndex = 2;
    private int mPageTotal;//数据总页数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(TCManagerActivity.this);
        setContentView(R.layout.activity_tcmanager);
        ButterKnife.bind(this);
        getData(1, 20);
        setListener();
    }

    private void getData(int index, int pageSize) {
        RequestParams params = new RequestParams();
        params.put("PageIndex", index);
        params.put("PageSize", pageSize);
        params.put("PC_SynState", "");
        params.put("PC_CodeOrName", "");
        HttpHelper.post(TCManagerActivity.this, "ProductCombo/QueryProductComboDataList", params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mTcListBean = CommonFun.JsonToObj(responseString, TCListBean.class);
                mPageTotal = mTcListBean.getData().getPageTotal();
                if (mList != null && mIsLoadMore) {
                    mList.addAll(mTcListBean.getData().getDataList());
                } else {
                    mList = mTcListBean.getData().getDataList();
                }
                mAdapter = new TCManagerAdapter(TCManagerActivity.this, mList);
                mListView.setAdapter(mAdapter);
                mRefresh.setRefreshing(false);
                mRefresh.setLoading(false);
                mIsLoadMore = false;
                mListView.setSelection(mCurrentPosition);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(TCManagerActivity.this, msg, Toast.LENGTH_SHORT).show();
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
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TCManagerActivity.this, TCDetailActivity.class);
                intent.putExtra("TC", mList.get(position));
                startActivity(intent);
            }
        });
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TCManagerActivity.this, AddTCActivity.class);
                startActivity(intent);
            }
        });
        mRefresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(1, 20);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    getData(mRefreshIndex, 20);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(TCManagerActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    mRefresh.setLoading(false);
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
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                    mCurrentPosition = mListView.getFirstVisiblePosition();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getData(1, 20);
    }
}
