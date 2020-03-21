package com.zhiluo.android.yunpu.goods.manager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.SuccessBean;
import com.zhiluo.android.yunpu.goods.manager.adapter.CheckGoodsAdapter;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckGoodsActivity extends BaseActivity implements CheckGoodsAdapter.ItemCheck {
    private BaseListView mListView;
    private EditText mEtSearch;
    private GoodsCheckResponseByType mGoodsBean;
    private WaveSwipeRefreshLayout mRefresh;
    private int mPageTotal;//数据总页数
    private int mCurrentClickPosition = 0;//当前点击位置
    private int mCurrentPosition;//ListView当前滑动位置
    private boolean mIsLoadMore;
    private int mRefreshIndex = 2;
    private boolean isCache;//是否缓存数据
    private String mCondition;
    private CheckGoodsAdapter mAdapter;
    private Handler mHandler;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mCheckList = new ArrayList<>();//选中商品列表
    private TextView mTvConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_goods);
        initView();
        initVariable();
        loadData();
        setListener();
    }

    private void initView() {
        mListView = (BaseListView) findViewById(R.id.lv_check_goods);
        mEtSearch = (EditText) findViewById(R.id.et_check_goods_search);
        mRefresh = (WaveSwipeRefreshLayout) findViewById(R.id.refresh_check_goods);
        mTvConfirm = (TextView) findViewById(R.id.tv_check_goods_confirm);
    }

    private void initVariable() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == 1) {
                    mAdapter = new CheckGoodsAdapter(CheckGoodsActivity.this, mGoodsBean, CheckGoodsActivity.this);
                    mListView.setAdapter(mAdapter);
                    mListView.setSelection(mCurrentPosition);
                }
                return false;
            }
        });
    }

    private void loadData() {
//        mGoodsBean = (GoodsCheckResponseByType) CacheData.restoreObject("stock");
        if (mGoodsBean == null) {
            isCache = true;
            checkGoodsByType("", 1, 10, mCondition);
        } else {
            mPageTotal = mGoodsBean.getData().getPageTotal();
            mAdapter = new CheckGoodsAdapter(CheckGoodsActivity.this, mGoodsBean, CheckGoodsActivity.this);
            mListView.setAdapter(mAdapter);
        }
    }

    private void setListener() {
        findViewById(R.id.tv_check_goods_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.iv_check_goods_scan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(CheckGoodsActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });
        //搜索
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()) {
                    mCondition = editable.toString();
                    checkGoodsByType("", 1, 10, mCondition);
                }
            }
        });

        mRefresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkGoodsByType("", 1, 10, mCondition);
                mRefreshIndex = 2;
                mCheckList.clear();
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    checkGoodsByType("", mRefreshIndex, 20, mCondition);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(CheckGoodsActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
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
        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("good", (Serializable) mCheckList);
                intent.putExtra("good", bundle);
                setResult(55, intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            mEtSearch.setText(scanResult);
        }
    }

    /**
     * 根据商品类型查询商品信息
     *
     * @param type  商品类型
     * @param index 页码
     * @param size  每页数据条数
     */
    private void checkGoodsByType(String type, int index, int size, String condition) {
        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        AsyncHttpClient client = new AsyncHttpClient();
        client.setCookieStore(cookieStore);
        RequestParams params = new RequestParams();
        params.put("PT_GID", type);
        params.put("PageIndex", index);
        params.put("PageSize", size);
        params.put("PM_Code", condition);
        client.post(HttpAPI.API().SERVICEGOODS, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                System.out.println(responseString);
                SuccessBean successBean = CommonFun.JsonToObj(responseString, SuccessBean.class);
                if (successBean.isSuccess()) {
                    if (mGoodsBean != null && !mIsLoadMore) {
                        mGoodsBean = null;
                    }
                    List<GoodsCheckResponseByType.DataBean.DataListBean> list = new ArrayList<>();
                    if (mGoodsBean != null) {
                        list.addAll(mGoodsBean.getData().getDataList());
                    }
                    mGoodsBean = CommonFun.JsonToObj(responseString, GoodsCheckResponseByType.class);
                    mPageTotal = mGoodsBean.getData().getPageTotal();
                    list.addAll(mGoodsBean.getData().getDataList());
                    mGoodsBean.getData().setDataList(list);
                }
//                for (int i = 0; i < mGoodsBean.getData().getDataList().size(); i++) {
//                    mGoodsBean.getData().getDataList().get(i).setPM_PurchasePrice(0);
//                    mGoodsBean.getData().getDataList().get(i).setNum(0);
//                }
                mIsLoadMore = false;
                mRefresh.setRefreshing(false);
                mRefresh.setLoading(false);
                //保存到本地
                if (isCache) {
                    CacheData.saveObject("stock", mGoodsBean);
                    isCache = false;
                }
                mHandler.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(CheckGoodsActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void itemClick(View view) {
        int position = (int) view.getTag();
        if (mGoodsBean.getData().getDataList().get(position).isChecked()) {
            for (int i = 0; i < mCheckList.size(); i++) {
                if (mCheckList.get(i).getGID().equals(mGoodsBean.getData().getDataList().get(position).getGID())) {
                    mCheckList.remove(i);
                    break;
                }
            }
            mGoodsBean.getData().getDataList().get(position).setChecked(false);
        } else {
            mGoodsBean.getData().getDataList().get(position).setChecked(true);
            mCheckList.add(mGoodsBean.getData().getDataList().get(position));
        }
        mAdapter.notifyDataSetChanged();
        Log.i(TAG, "check: " + mCheckList.size());
    }
}
