package com.zhiluo.android.yunpu.ui.activity.good;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.manager.activity.AddProviderActivity;
import com.zhiluo.android.yunpu.goods.manager.activity.ModifyProviderActivity;
import com.zhiluo.android.yunpu.goods.manager.activity.ProviderManagerActivity;
import com.zhiluo.android.yunpu.goods.manager.adapter.ProviderManagerAdapter;
import com.zhiluo.android.yunpu.goods.manager.bean.ProviderBean;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ZPH on 2019-03-05.
 */

public class ChoiceProviderActivity extends AppCompatActivity {


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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_provider);
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
                    CustomToast.makeText(ChoiceProviderActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("Provider", mProviderBean.getData().getDataList().get(i));
                intent.putExtras(bundle);
                setResult(2222, intent);
                finish();
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
                mAdapter = new ProviderManagerAdapter(ChoiceProviderActivity.this, mProviderBean);
                mListView.setAdapter(mAdapter);
                mListView.setSelection(mCurrentPosition);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(ChoiceProviderActivity.this, msg, Toast.LENGTH_SHORT).show();
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


}
