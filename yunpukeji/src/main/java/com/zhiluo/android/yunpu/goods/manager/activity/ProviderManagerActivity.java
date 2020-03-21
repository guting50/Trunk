package com.zhiluo.android.yunpu.goods.manager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.goods.manager.adapter.ProviderManagerAdapter;
import com.zhiluo.android.yunpu.goods.manager.bean.ProviderBean;
import com.zhiluo.android.yunpu.member.manager.adapter.MemberListAdapter;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/9/27 17:32
 * Author: 罗咏哲
 * Description: 供应商管理
 * Version:
 */
public class ProviderManagerActivity extends BaseActivity {
    private EditText etSearch;
    private String mCondition;
    private WaveSwipeRefreshLayout mRefresh;
    private BaseListView mListView;
    private ProviderBean mProviderBean;
    private ProviderManagerAdapter mAdapter;
    private int mRefreshIndex = 2;
    private boolean mIsLoadMore;//加载更多
    private int mPageTotal;//数据总页数
    private int mCurrentPosition;//当前滑动位置

    private InputHandler mInputHandler = new InputHandler();
    private boolean hasTimerDown = false;
    private long pretime;//第一次的时间



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_manager);
        initView();
        initVariable();
        loadData();
        setListener();
    }

    private void initView() {
        etSearch = (EditText) findViewById(R.id.et_provider_manager_search);
        mRefresh = (WaveSwipeRefreshLayout) findViewById(R.id.provider_manager_refresh);
        mListView = (BaseListView) findViewById(R.id.lv_provider_manager);
    }

    private void initVariable() {

    }

    private void loadData() {
        getProvider(1, 10);
    }

    private void setListener() {
        findViewById(R.id.tv_provider_manager_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.fab_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProviderManagerActivity.this, AddProviderActivity.class);
                startActivityForResult(intent,1);
            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pretime = System.currentTimeMillis();
                if (!hasTimerDown) {
                    doQurry();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null) {
                    mCondition = editable.toString();
                } else {
                    mCondition = "";
                }
                //  getProvider(1, 10);
            }
        });
        mRefresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProvider(1, 10);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    getProvider(mRefreshIndex, 20);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(ProviderManagerActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
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

        //获取当前滑动位置
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                    mCurrentPosition = mListView.getFirstVisiblePosition();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ProviderManagerActivity.this, ModifyProviderActivity.class);
                intent.putExtra("provider", mProviderBean.getData().getDataList().get(i));
                startActivityForResult(intent,2);
            }
        });
    }

    /**
     * 获取供应商
     */
    private void getProvider(int index, int pageSize) {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        RequestParams params = new RequestParams();
        params.put("PageIndex", index);
        params.put("PageSize", pageSize);
        params.put("SL_Name", mCondition);
        client.setCookieStore(cookieStore);
        client.post(HttpAPI.API().PRIVADER, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                if (mProviderBean != null && !mIsLoadMore) {
                    mProviderBean = null;
                }
                List<ProviderBean.DataBean.DataListBean> list = new ArrayList<>();
                if (mProviderBean != null) {
                    list.addAll(mProviderBean.getData().getDataList());
                }
                mProviderBean = CommonFun.JsonToObj(responseString, ProviderBean.class);
                mPageTotal = mProviderBean.getData().getPageTotal();
                list.addAll(mProviderBean.getData().getDataList());
                mProviderBean.getData().setDataList(list);
                mIsLoadMore = false;
                mRefresh.setRefreshing(false);
                mRefresh.setLoading(false);
                mAdapter = new ProviderManagerAdapter(ProviderManagerActivity.this, mProviderBean);
                mListView.setAdapter(mAdapter);
                mListView.setSelection(mCurrentPosition);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(ProviderManagerActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class InputHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0://0正在输入
                    break;
                case 1://1输入完成
                    doQurry();
                    break;
            }
        }
    }

    private void doQurry() {
        hasTimerDown = true;
        if (System.currentTimeMillis() - pretime > 500) {
            getProvider(1, 10);
            hasTimerDown = false;
        } else {
            mInputHandler.sendEmptyMessageDelayed(1, 300);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1||requestCode==2){
            getProvider(1,10);
        }
    }
}
