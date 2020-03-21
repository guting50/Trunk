package com.zhiluo.android.yunpu.goods.manager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.manager.bean.FastChoiceChargeBean;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.ChoiceInitServiceActivity;
import com.zhiluo.android.yunpu.ui.adapter.ChoiceServiceAdapter;
import com.zhiluo.android.yunpu.ui.adapter.FastChoicechargesAdapter;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ZPH on 2018-12-27.
 */

public class ChoiceFastchargesActivity extends BaseActivity {
    @Bind(R.id.tv_choice_service_back)
    TextView tvBack;
    @Bind(R.id.iv_choice_service_search)
    ImageView ivChoiceServiceSearch;
    @Bind(R.id.et_choice_service_condition)
    EditText etCondition;
    @Bind(R.id.lv_choice_service)
    BaseListView lvChoiceService;
    @Bind(R.id.refresh_choice_service)
    WaveSwipeRefreshLayout mRefresh;


    private String mSearchCondition = "";
    private GoodsCheckResponseByType mServiceBean;
    private FastChoicechargesAdapter mAdapter;
    private int mPageTotal;//数据总页数
    private boolean mIsLoadMore;
    private int refreshnum = 2;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mCheckedList = new ArrayList<>();//选中列表
    private boolean hasTimerDown = false;
    private InputHandler mInputHandler = new InputHandler();
    private long pretime;//第一次的时间
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mList;
    private GoodsCheckResponseByType.DataBean.DataListBean mServiceInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choicefastcharges);
        ButterKnife.bind(this);
        getService(1, 20);
        setListener();

    }


    private void getService(int index, int size) {
        RequestParams params = new RequestParams();

        params.put("PageIndex", index);
        params.put("PageSize", size);
        params.put("DataType", 4);
        params.put("SortType", 1);
        params.put("PT_GID", "");
        params.put("PM_CodeOrNameOrSimpleCode", mSearchCondition);
        HttpHelper.post(this, HttpAPI.API().SERVICEGOODS, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                if (mList != null && !mIsLoadMore) {
                    mList.clear();
                }
                List<GoodsCheckResponseByType.DataBean.DataListBean> list = new ArrayList<>();
                if (mList != null) {
                    list.addAll(mList);
                }
                mServiceBean = CommonFun.JsonToObj(responseString, GoodsCheckResponseByType.class);
                mList = mServiceBean.getData().getDataList();
                mPageTotal = mServiceBean.getData().getPageTotal();
                list.addAll(mList);
                mList = list;
                if (mAdapter ==null){
                    mAdapter = new FastChoicechargesAdapter(ChoiceFastchargesActivity.this,
                            mList);
                    lvChoiceService.setAdapter(mAdapter);
                }else {
                    mAdapter.setParam(mList);
                    mAdapter.notifyDataSetChanged();
                }
                mIsLoadMore = false;
                mRefresh.setRefreshing(false);
                mRefresh.setLoading(false);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(ChoiceFastchargesActivity.this, msg, Toast.LENGTH_SHORT).show();
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

        lvChoiceService.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mServiceInfo = mList.get(position);//获得被点击的Item的对象
                //回传给开启Activity的Activity会员信息实体
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("SEVIVCE", mServiceInfo);
                intent.putExtras(bundle);
                setResult(6666, intent);
                finish();
            }

        });

        etCondition.addTextChangedListener(new TextWatcher() {
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
                if (!TextUtils.isEmpty(editable)) {
                    mSearchCondition = editable.toString();
                } else {
                    mSearchCondition = "";
                }
            }
        });

        mRefresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getService(1, 20);
                refreshnum = 2;
            }

            @Override
            public void onLoad() {
                if (refreshnum <= mPageTotal) {
                    mIsLoadMore = true;
                    getService(refreshnum, 20);
                    refreshnum++;
                } else {
                    CustomToast.makeText(ChoiceFastchargesActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
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
            if (!TextUtils.isEmpty(etCondition.getText())) {
                getService(1,20);
                mCheckedList.clear();

            }
            hasTimerDown = false;
        } else {
            mInputHandler.sendEmptyMessageDelayed(1, 300);
        }
    }
}
