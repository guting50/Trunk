package com.zhiluo.android.yunpu.goods.manager.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.goods.consume.activity.GoodsSpecsActivity;
import com.zhiluo.android.yunpu.goods.manager.adapter.GoodsInfoAdapter;
import com.zhiluo.android.yunpu.goods.manager.adapter.GoodsTypeExpandAdapter;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.mvp.model.GoodsType;
import com.zhiluo.android.yunpu.mvp.presenter.WareHousingPresenter;
import com.zhiluo.android.yunpu.mvp.view.IWareHousingView;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.ByteArrayToHexString;
import com.zhiluo.android.yunpu.utils.CardOperationUtils;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;
import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 商品管理
 * 作者：罗咏哲 on 2017/7/29 10:37.
 * 邮箱：137615198@qq.com
 */
public class GoodsManagerActivity extends BaseActivity {
    private TextView mTvBack;//返回
    private EditText mEtSearch;//搜索商品
    private BaseListView mRightListView;//右边ListView
    private ExpandableListView mLeftExpandListView;//商品分类布局
    //    private BaseListView mLeftListView;//商品分类布局
    private WaveSwipeRefreshLayout mRefreshLayout;//刷新布局
    private AsyncHttpClient mClient;
    private List<GoodsType.DataBean> mParentGoodsType;//父类型
    private GoodsTypeExpandAdapter mLeftAdapter;//左边Adapter
    private Map<GoodsType.DataBean, List<GoodsType.DataBean>> mChild;
    private GoodsCheckResponseByType mGoodsInfo;//商品信息
    private GoodsInfoAdapter mRightAdapter;//右边ListView Adapter
    private String mGid;//商品类型GID
    private Map<Integer, Boolean> mLeftMap;
    private int mPageTotal;//数据总页数
    private int mCurrentClickPosition = 0;//当前点击位置
    //    private int mCurrentPosition;//ListView当前滑动位置
    private boolean mIsLoadMore;
    private int mRefreshIndex = 2;
    private int type = 0;
    private String mCondition;//查询条件
    private ImageView mIvScan;//扫描
    private FloatingActionButton mFab;
    private boolean isCache;//是否缓存数据
    private WareHousingPresenter mPresenter;
    private IWareHousingView mView;
    int expandFlag = -1;//控制列表的展开
    private int mLastParentPos;
    private InputHandler mInputHandler = new InputHandler();
    private boolean hasTimerDown = false;
    private long pretime;//第一次的时间
    private TextView tvAdd;//添加商品
    private NfcAdapter mNFCAdapter;
    private PendingIntent mPendingIntent;

    private String goodsType, SortType = "0";//排序
    private String maxPrice;
    private String minPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(GoodsManagerActivity.this);
        setContentView(R.layout.activity_goods_manager);
        initView();
        initVariable();
        setListener();
        //V1S刷卡
        if (MyApplication.IS_SUNMI_POS_V1S_DEVICE) {
            // 获取默认的NFC控制器
            mNFCAdapter = NfcAdapter.getDefaultAdapter(this);
            Intent nfcIntent = new Intent(this, getClass());
            nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            mPendingIntent =
                    PendingIntent.getActivity(this, 0, nfcIntent, 0);
        }
    }

    //获取系统隐式启动的
    @Override
    public void onNewIntent(Intent intent) {
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        String CardId = ByteArrayToHexString.ByteArrayToHex(tagFromIntent.getId());
        if (null != CardId) {
            MyApplication.VIP_CARD = CardId;
            while (CardId.length() < 10) {
                CardId = "0" + CardId;
            }
            mEtSearch.setText(CardId);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mNFCAdapter != null) {
            //隐式启动
            mNFCAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
        /**
         * lkl、sunmi 、basewin的检卡 读卡操作
         */

        if (!MyApplication.IS_SUNMI_POS_V1S_DEVICE) {
            new CardOperationUtils(this, mEtSearch);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        //终止检卡
        new CardOperationUtils().close();

        Log.d("TAG", "onStop: ");
    }

    /**
     * 初始化控件
     */
    private void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//进入页面关闭软键盘
        mTvBack = (TextView) findViewById(R.id.tv_goods_manager_back);
        tvAdd = findViewById(R.id.tv_goods_manager_add);
        mEtSearch = (EditText) findViewById(R.id.et_goods_manager_search);
        mLeftExpandListView = (ExpandableListView) findViewById(R.id.lv_goods_manager_left);
//        mLeftListView = (BaseListView) findViewById(R.id.lv_goods_manager_left);
        mRightListView = (BaseListView) findViewById(R.id.lv_goods_manager_right);
        mRefreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.refresh_goods_manager);
        mIvScan = (ImageView) findViewById(R.id.iv_scan);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mPresenter = new WareHousingPresenter(this);
        mPresenter.onCreate("SPGL");
        mView = new IWareHousingView() {
            @Override
            public void getTypeSuccess(List<GoodsType.DataBean> parent, Map<GoodsType.DataBean, List<GoodsType.DataBean>> map) {
                mChild = map;
                mParentGoodsType = parent;
                mLeftAdapter = new GoodsTypeExpandAdapter(GoodsManagerActivity.this,
                        parent, map);
                mLeftExpandListView.setGroupIndicator(null);
                mLeftExpandListView.setVerticalScrollBarEnabled(false);
                mParentGoodsType.get(0).setChecked(true);
                mLastParentPos = 0;
                expandFlag = 0;
                mLeftExpandListView.setAdapter(mLeftAdapter);
                mLeftExpandListView.expandGroup(0);
            }

            @Override
            public void getTypeFail(String result) {

            }

            @Override
            public void getGoodsSuccess(GoodsCheckResponseByType entity) {

            }

            @Override
            public void getGoodsFail(String result) {

            }

            @Override
            public void getComboSuccess(GoodsCheckResponseByType entity) {
                mPageTotal = entity.getData().getPageTotal();
                if (mIsLoadMore && mGoodsInfo != null) {
                    mGoodsInfo.getData().getDataList().addAll(entity.getData().getDataList());
                } else {
                    mGoodsInfo = entity;
                }
                setRightAdapter();
                mIsLoadMore = false;
                mRefreshLayout.setRefreshing(false);
                mRefreshLayout.setLoading(false);
//                mRightListView.setSelection(mCurrentPosition);
            }


            @Override
            public void getCombofail(String result) {

            }
        };
        mPresenter.attachView(mView);
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        mCondition = "";
        mClient = new AsyncHttpClient();
        mParentGoodsType = new ArrayList<>();
        mChild = new HashMap<>();
        getGoods(1, 20);
    }

    /**
     * 设置右边ListViewAdapter
     */
    private void setRightAdapter() {
        if (mRightAdapter == null || type == 1) {
            mRightAdapter = new GoodsInfoAdapter(this, mGoodsInfo);
            mRightListView.setAdapter(mRightAdapter);
            type = 0;
//            mRightListView.setSelection(mCurrentPosition);//界面刷新后再跳到之前的位置，位子不精准，界面会晃动
        } else {
            mRightAdapter.setParm(mGoodsInfo);
            mRightAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 设置监听
     */
    private void setListener() {
        //返回
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(GoodsManagerActivity.this, HomeActivity.class);
//                startActivity(intent);
                finish();
            }
        });
        //添加商品
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoodsManagerActivity.this, GoodsManagerChooseActivity.class);
                intent.putExtra("goodstype", goodsType);
                intent.putExtra("MAX", maxPrice);
                intent.putExtra("MIN", minPrice);
                intent.putExtra("SortType", SortType);

                startActivityForResult(intent, 9999);

            }
        });
        //搜索
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pretime = System.currentTimeMillis();
                //已经调用了一次就不在调用
                if (!hasTimerDown) {
                    doQurry();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()) {
                    mCondition = editable.toString();
                    //getGoods(1, 20);
                }
            }
        });
        //扫描
        mIvScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(GoodsManagerActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });

        //设置ListView item点击事件
        mRightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GoodsCheckResponseByType.DataBean.DataListBean goods = mGoodsInfo.getData().getDataList().get(i);
                if (goods.getGroupCount() <= 1) {
                    Intent intent = new Intent(GoodsManagerActivity.this, GoodsDetailActivity.class);
                    intent.putExtra("Goods", goods);
                    //多规格
                    intent.putExtra("extra_many", "not_many");
                    startActivity(intent);
                } else {
                    /**多规格判断,大于1就是多规格*/
                    Intent intent = new Intent(GoodsManagerActivity.this, GoodsSpecsActivity.class);
                    intent.putExtra("groupGID", goods.getPM_GroupGID());
                    startActivity(intent);
                }

            }
        });
        //设置下拉刷新
        mRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                getGoods(1, 20);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                Li("mRefreshIndex==" + mRefreshIndex + "++++++mPageTotal" + mPageTotal);
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    getGoods(mRefreshIndex, 20);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(GoodsManagerActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    mRefreshLayout.setLoading(false);
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
//        //获取当前ListView滑动位置
//        mRightListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
//                if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
//                    mCurrentPosition = mRightListView.getFirstVisiblePosition();
//                }
//            }
//
//            @Override
//            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//
//            }
//        });
        mLeftExpandListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                mChild.get(mParentGoodsType.get(groupPosition)).get(childPosition).setChecked(true);
                mRightAdapter.setParm(mGoodsInfo);
                mLeftAdapter.notifyDataSetChanged();
                for (int i = 0; i < mChild.get(mParentGoodsType.get(groupPosition)).size(); i++) {
                    if (i != childPosition) {
                        mChild.get(mParentGoodsType.get(groupPosition)).get(i).setChecked(false);
                    }
                }
                mGid = mChild.get(mParentGoodsType.get(groupPosition)).get(childPosition).getGID();
                getGoods(1, 20);
                mLeftAdapter.notifyDataSetChanged();
                return false;
            }
        });
        mLeftExpandListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (expandFlag == -1) {
                    mLeftExpandListView.expandGroup(groupPosition);
                    mLeftExpandListView.setSelectedGroup(groupPosition);
                    expandFlag = groupPosition;
                } else if (expandFlag == groupPosition) {
                    mLeftExpandListView.collapseGroup(expandFlag);
                    expandFlag = -1;
                } else {
                    mLeftExpandListView.collapseGroup(expandFlag);
                    mLeftExpandListView.expandGroup(groupPosition);
                    mLeftExpandListView.setSelectedGroup(groupPosition);
                    expandFlag = groupPosition;
                }
                mParentGoodsType.get(groupPosition).setChecked(true);
                for (int i = 0; i < mParentGoodsType.size(); i++) {
                    if (groupPosition != i) {
                        mParentGoodsType.get(i).setChecked(false);
                    }
                }
                if (mLastParentPos != groupPosition) {
                    for (int i = 0; i < mChild.get(mParentGoodsType.get(mLastParentPos)).size(); i++) {
                        mChild.get(mParentGoodsType.get(mLastParentPos)).get(i).setChecked(false);
                    }
                    mLastParentPos = groupPosition;
                }
                mGid = mParentGoodsType.get(groupPosition).getGID();
                Li("商品GID-=----" + mGid);
                type = 1;
                getGoods(1, 20);
//                getGoodsNoType(1, 20);
                mRefreshIndex = 2;
                mLeftAdapter.notifyDataSetChanged();
                return true;
            }
        });

        /**
         * 添加商品
         */
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (YSLUtils.isOpenActivon("添加商品")) {
                    Intent intent = new Intent(GoodsManagerActivity.this, YSLAddGoodsActivity.class);
                    startActivity(intent);
                } else {
                    CustomToast.makeText(GoodsManagerActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
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

        if (resultCode == 666) {
            if (data != null) {
                goodsType = data.getStringExtra("goodstype");
                maxPrice = data.getStringExtra("MAX");
                minPrice = data.getStringExtra("MIN");
                SortType = data.getStringExtra("SortType");
                getGoods(1, 20);
            }
        }
    }

    private void getGoods(int index, int size) {
//        RequestParams params = new RequestParams();
//        params.put("PageIndex", index);
//        params.put("PageSize", size);
//        params.put("PT_GID", mGid);
//        params.put("PM_IsService", goodsType);
//        params.put("SortType", SortType);
//        params.put("ShowProductByGroup", 1);
//        params.put("PM_UnitPriceMin", maxPrice);
//        params.put("PM_UnitPriceMax", minPrice);
//        params.put("PM_CodeOrNameOrSimpleCode", mCondition);
//
//        mPresenter.getGoods(params);
        RequestParams params = new RequestParams();
        params.put("pageIndex", index);
        params.put("pageSize", size);
        params.put("DataType", 2);
        params.put("PT_GID", mGid);
        params.put("PM_CodeOrNameOrSimpleCode", mCondition);
        mPresenter.getcombo(params);
    }

    private void getGoodsNoType(int index, int size) {
        RequestParams params = new RequestParams();
        params.put("PT_GID", mGid);
        params.put("PageIndex", index);
        params.put("PageSize", size);
        params.put("PM_IsService", "");
        params.put("PM_UnitPriceMin", "");
        params.put("PM_UnitPriceMax", "");
        params.put("SortType", "");
        params.put("PM_CodeOrNameOrSimpleCode", "");
        Li("提交表单---" + params.toString());
        HttpHelper.post(GoodsManagerActivity.this, HttpAPI.API().SERVICEGOODS, params, new CallBack() {
            @Override
            public void onSuccess(String strResponse, Gson gson) {
                Li("商品列表----" + strResponse);
                GoodsCheckResponseByType entity = CommonFun.JsonToObj(strResponse, GoodsCheckResponseByType.class);
                mView.getGoodsSuccess(entity);
            }

            @Override
            public void onFailure(String msg) {
                mView.getGoodsFail(msg);
            }
        });

    }

    private void getGoodsNoCondition(int index, int size) {
        RequestParams params = new RequestParams();

        params.put("PageIndex", index);
        params.put("PageSize", size);
        params.put("PT_GID", "");
        params.put("PM_IsService", "");
        params.put("PM_UnitPriceMin", "");
        params.put("PM_UnitPriceMax", "");
        params.put("SortType", 0);
        params.put("ShowProductByGroup", 1);
        params.put("PM_CodeOrNameOrSimpleCode", "");
        mPresenter.getGoods(params);
        Le(params.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mClient != null) {
            mClient.cancelAllRequests(true);
        }
    }

    private class InputHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1://1输入完成
                    doQurry();
                    break;
            }
        }
    }

    /**
     * 查询商品
     */
    private void doQurry() {
        hasTimerDown = true;
        if (System.currentTimeMillis() - pretime > 500) {
            getGoods(1, 20);
            hasTimerDown = false;
        } else {
            mInputHandler.sendEmptyMessageDelayed(1, 300);
        }
    }
}
