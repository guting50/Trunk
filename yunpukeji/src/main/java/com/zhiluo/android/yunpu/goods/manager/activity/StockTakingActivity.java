package com.zhiluo.android.yunpu.goods.manager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.entity.SuccessBean;
import com.zhiluo.android.yunpu.goods.consume.bean.GoodsTypeNameBean;
import com.zhiluo.android.yunpu.goods.manager.adapter.GoodsTypeAdapter;
import com.zhiluo.android.yunpu.goods.manager.adapter.StockTakingRightAdapter;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.goods.manager.bean.StockBean;
import com.zhiluo.android.yunpu.goods.manager.bean.StockTakingBean;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.mvp.model.GoodsType;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.CreateOrder;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Date: 2017/9/26 14:34
 * Author: 罗咏哲
 * Description: 库存盘点
 * Version:
 */
public class StockTakingActivity extends BaseActivity implements GoodsTypeAdapter.ItemOnClick,
        StockTakingRightAdapter.ItemViewClick {
    private BaseListView mLvLeft, mLvRight;
    private EditText mEtSearch;
    private GoodsType mGoodsType;//商品类型
    private Handler mHandler;
    private List<GoodsTypeNameBean> mLeftListViewData;//左边ListView显示的数据
    private List<GoodsType.DataBean> mParentGoodsType;//父类型
    private Map<Integer, Boolean> mLeftMap;
    private GoodsTypeAdapter mLeftAdapter;//左边Adapter
    private StockTakingRightAdapter mRightAdapter;
    private String mGid;//商品类型GID
    private GoodsCheckResponseByType mStock;//商品信息
    private int mPageTotal;//数据总页数
    private int mCurrentClickPosition = 0;//当前点击位置
    private int mCurrentPosition;//ListView当前滑动位置
    private boolean mIsLoadMore;
    private int mRefreshIndex = 2;
    private String mCondition;//查询条件
    private boolean isCache;//是否缓存数据
    private WaveSwipeRefreshLayout mRefreshLayout;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mStockBeanList = new ArrayList<>();
    private boolean mShow = true;
    private TextView mTvStockNum;
    private SweetAlertDialog mAlertDialog;
    private InputHandler mInputHandler = new InputHandler();
    private boolean hasTimerDown = false;
    private long pretime;//第一次的时间
    private boolean isPD;
    private String myuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_taking);
        initView();
        initVariable();
        loadData();
        setListener();
    }

    private void initView() {
        mLvLeft = (BaseListView) findViewById(R.id.lv_stock_taking_left);
        mLvRight = (BaseListView) findViewById(R.id.lv_stock_taking_right);
        mEtSearch = (EditText) findViewById(R.id.et_stock_taking_search);
        mRefreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.refresh_stock_taking);
        mTvStockNum = (TextView) findViewById(R.id.tv_stock_taking_num);
    }

    private void initVariable() {
        mLeftMap = new HashMap<>();
        mParentGoodsType = new ArrayList<>();
        mLeftListViewData = new ArrayList<>();
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        setLeftAdapter();
                        break;
                    case 2:
                        setRightAdapter();
                        mRefreshLayout.setRefreshing(false);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void loadData() {
        myuser = (String) uSharedPreferencesUtiles.get(StockTakingActivity.this,"mAcountname","默认操作员");
        mGoodsType = (GoodsType) CacheData.restoreObject("goods_type");
        if (mGoodsType == null) {
            loadGoodsTypeData();//获取商品类型数据
        } else {
            setParentData();
            groupTypeData();
            mHandler.sendEmptyMessage(1);
        }
        mStock = (GoodsCheckResponseByType) CacheData.restoreObject("stock");
        if (mStock == null) {
            checkGoodsByType(mGid, 1, 10, mCondition);
        } else {
            mPageTotal = mStock.getData().getPageTotal();
            mHandler.sendEmptyMessage(2);
        }

    }

    private void setListener() {
        findViewById(R.id.tv_stock_taking_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.iv_stock_taking_scan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(StockTakingActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });
        mRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkGoodsByType(mGid, 1, 10, mCondition);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    checkGoodsByType(mGid, mRefreshIndex, 10, mCondition);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(StockTakingActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
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
        //搜索
        mEtSearch.addTextChangedListener(new TextWatcher() {
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
                if (!editable.toString().isEmpty()) {
                    mCondition = editable.toString();
                   // checkGoodsByType(mGid, 1, 10, mCondition);
                }
            }
        });

        //保存
        findViewById(R.id.btn_stock_taking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPD = true;
                if (mStockBeanList != null && mStockBeanList.size() > 0) {
                        stockTaking();
                }else {
                    CustomToast.makeText(StockTakingActivity.this,"请先选择要盘点商品",Toast.LENGTH_SHORT).show();
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
    }

    /**
     * 获取商品类型数据
     */
    private void loadGoodsTypeData() {
        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        AsyncHttpClient mClient = new AsyncHttpClient();
        mClient.setCookieStore(cookieStore);
        mClient.post(HttpAPI.API().PRODUCTTYPE, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mGoodsType = CommonFun.JsonToObj(responseString, GoodsType.class);
                CacheData.saveObject("goods_type", mGoodsType);
                setParentData();
                groupTypeData();
                mHandler.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    /**
     * 组装父类型数据
     */
    private void setParentData() {
        if (mGoodsType != null) {
            List<GoodsType.DataBean> bean = mGoodsType.getData();
            for (int i = 0; i < bean.size(); i++) {
                if ("".equals(bean.get(i).getPT_Parent())) {
                    mParentGoodsType.add(bean.get(i));
                }
            }
        }
    }

    /**
     * 组合商品类型数据
     */
    private void groupTypeData() {

        if (!mLeftListViewData.isEmpty()) {
            mLeftListViewData.clear();
        }
        //添加一个“所有”分类
        GoodsTypeNameBean all = new GoodsTypeNameBean();
        all.setGid("");
        all.setName("全部商品");
        mLeftListViewData.add(all);
        for (int i = 0; i < mGoodsType.getData().size(); i++) {
            String gid = "";
            boolean tag = true;
            GoodsType.DataBean childType = mGoodsType.getData().get(i);
            if (!"".equals(childType.getPT_Parent())) {
                GoodsTypeNameBean bean = new GoodsTypeNameBean();
                for (int j = 0; j < mGoodsType.getData().size(); j++) {
                    GoodsType.DataBean groupType = mGoodsType.getData().get(j);
                    if (childType.getPT_Parent() != null && childType.getPT_Parent().equals(groupType.getGID())) {
                        bean.setName(groupType.getPT_Name() + "-" + childType.getPT_Name());
                        bean.setGid(childType.getGID());
                        mLeftListViewData.add(bean);//添加有父类的子类
                        gid = groupType.getGID();
                        tag = false;
                    }
                }
            }
            if ((childType.getPT_Parent() == null ||"".equals(childType.getPT_Parent()))&& tag) {
                GoodsTypeNameBean bean = new GoodsTypeNameBean();
                bean.setName(mGoodsType.getData().get(i).getPT_Name());
                bean.setGid(mGoodsType.getData().get(i).getGID());
                mLeftListViewData.add(bean);//添加没有父类的子类
            }
            for (int j = 0; j < mLeftListViewData.size(); j++) {
                if (mLeftListViewData.get(j).getGid().equals(gid) && !gid.equals("")) {
                    mLeftListViewData.remove(j);//删除有子类的父类
                }
            }
        }

        for (int i = 0; i < mLeftListViewData.size(); i++) {
            mLeftMap.put(0, true);
            mLeftMap.put(i, false);
        }

    }

    /**
     * 设置左边Adapter
     */
    private void setLeftAdapter() {
        if (mLeftAdapter != null) {
            mLeftAdapter.notifyDataSetChanged();
        } else {
            mLeftAdapter = new GoodsTypeAdapter(StockTakingActivity.this, mLeftListViewData,
                    StockTakingActivity.this, mLeftMap);
            mLvLeft.setAdapter(mLeftAdapter);
        }
    }

    /**
     * 设置右边Adapter
     */
    private void setRightAdapter() {
        if (mRightAdapter !=null){
            mRightAdapter.setParm(mStock.getData());
            mRightAdapter.notifyDataSetChanged();
        }else {
            mRightAdapter = new StockTakingRightAdapter(StockTakingActivity.this, mStock.getData(),
                    StockTakingActivity.this);
            mLvRight.setAdapter(mRightAdapter);
//            mLvRight.setSelection(mCurrentPosition);
        }
    }

    @Override
    public void itemClick(View view) {
        if (view.getId() == R.id.ll_goods_consume_left) {
            int position = (int) view.getTag();
            //将上一个点击位置是否显示布局状态致为false，
            mLeftMap.put(mCurrentClickPosition, false);
            //将当前点击位置是否显示布局状态置为true
            mLeftMap.put(position, true);
            //刷新当前点击位置
            mCurrentClickPosition = position;
            //刷新界面
            mLeftAdapter.notifyDataSetChanged();
            mGid = mLeftListViewData.get(position).getGid();
            //右边相应刷新数据
            checkGoodsByType(mGid, 1, 10, mCondition);
            mRefreshIndex = 2;
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
        AsyncHttpClient mClient = new AsyncHttpClient();
        mClient.setCookieStore(cookieStore);
        RequestParams params = new RequestParams();
//        params.put("ProType", type);
//        params.put("CodeName", condition);
        params.put("PageIndex", index);
        params.put("PageSize", size);
        params.put("DataType", 3);
        params.put("SortType", 1);
        params.put("PT_GID", type);
        params.put("PM_CodeOrNameOrSimpleCode", condition);

        mClient.post(HttpAPI.API().STOCK_MANY, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                SuccessBean successBean = CommonFun.JsonToObj(responseString, SuccessBean.class);
                if (successBean.isSuccess()) {
                    if (mStock != null && !mIsLoadMore) {
                        mStock = null;
                    }
                    List<GoodsCheckResponseByType.DataBean.DataListBean> list = new ArrayList<>();
                    if (mStock != null) {
                        list.addAll(mStock.getData().getDataList());
                    }
                    mStock = CommonFun.JsonToObj(responseString, GoodsCheckResponseByType.class);
                    mPageTotal = mStock.getData().getPageTotal();
                    list.addAll(mStock.getData().getDataList());
                    mStock.getData().setDataList(list);
                }
                for (int i = 0; i < mStock.getData().getDataList().size(); i++) {
                    mStock.getData().getDataList().get(i).setNow_Stock(mStock.getData().getDataList().get(i).getStock_Number());
                }
                mIsLoadMore = false;
                mRefreshLayout.setRefreshing(false);
                mRefreshLayout.setLoading(false);
                //保存到本地
                if (isCache) {
                    CacheData.saveObject("stock", mStock);
                    isCache = false;
                }
                mHandler.sendEmptyMessage(2);
            }

            @Override
            public void onFailure(String msg) {

            }
        });

    }

    @Override
    public void click(View view) {
        int i = (int) view.getTag();

        GoodsCheckResponseByType.DataBean.DataListBean bean = mStock.getData().getDataList().get(i);
        String gid = bean.getGID();
        double jinjia = bean.getPM_PurchasePrice();
        for (int j = 0; j < mStockBeanList.size(); j++) {
            if (gid.equals(mStockBeanList.get(j).getGID()) && jinjia == mStockBeanList.get(j).getPM_PurchasePrice()) {
                mStockBeanList.remove(j);
                break;
            }
        }

        double stock = bean.getNow_Stock();
        switch (view.getId()) {
            case R.id.iv_add:
                stock += 1;
                bean.setNow_Stock(stock);
                mRightAdapter.notifyDataSetChanged();
                mStockBeanList.add(bean);
                break;
            case R.id.iv_reduce:
                stock -= 1;
                bean.setNow_Stock(stock);
                mRightAdapter.notifyDataSetChanged();
                mStockBeanList.add(bean);
//                mTvStockNum.setText(mStockBeanList.size()+"种");
                break;
            case R.id.iv_stock_pandian:
                bean.setFlag(true);
                mRightAdapter.notifyDataSetChanged();
                mStockBeanList.add(bean);
                mTvStockNum.setText(mStockBeanList.size()+"种");

                break;
        }
    }

    private void stockTaking() {
        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        AsyncHttpClient mClient = new AsyncHttpClient();
        mClient.setCookieStore(cookieStore);
        RequestParams params = new RequestParams();

        double totalLoss = 0;
        double totalGaiin = 0;
        StringBuilder NameList = new StringBuilder("");
        for (int i = 0;i<mStockBeanList.size();i++){
            GoodsCheckResponseByType.DataBean.DataListBean bean = mStockBeanList.get(i);
            double money = (bean.getNow_Stock() -bean.getStock_Number())*bean.getPM_PurchasePrice();
            double loss = 0;
            double gain = 0;
            double total = 0;
            if (money>0){
                gain = money;
                total = money;
            }else {
                loss = 0 -money;
                total = 0 -money;
            }
            totalLoss += loss;
            totalGaiin += gain;
            params.put("OrderDetail["+i+"][PM_GID]", bean.getGID());
            params.put("OrderDetail["+i+"][PM_Code]", bean.getPM_Code());
            params.put("OrderDetail["+i+"][PM_Name]", bean.getPM_Name());
            params.put("OrderDetail["+i+"][PM_Metering]", bean.getPM_Metering());
            params.put("OrderDetail["+i+"][PM_Modle]", bean.getPM_Modle());
            params.put("OrderDetail["+i+"][RealityNumber]", bean.getStock_Number());
            params.put("OrderDetail["+i+"][ActuallyNumber]", bean.getNow_Stock());
            params.put("OrderDetail["+i+"][ReferencePrice]", bean.getPM_PurchasePrice());//参考进价
            params.put("OrderDetail["+i+"][LossMoney]", loss);
            params.put("OrderDetail["+i+"][GainMoney]", gain);
            params.put("OrderDetail["+i+"][TotalMoney]", total);
            params.put("OrderDetail["+i+"][SP_GID]", bean.getSP_GID());
            if (NameList.toString().equals("")){
                NameList.append(bean.getPM_Name());
            }else {
                NameList.append(",");
                NameList.append(bean.getPM_Name());
            }
        }

        params.put("SI_TrackingNo", CreateOrder.createOrder("PD"));
        params.put("Hander", myuser);
        params.put("GainTotalMoney", totalGaiin);
        params.put("LossTotalMoney", totalLoss);
        params.put("NameList", NameList.toString());
        params.put("UpDate", DateTimeUtil.getReallyTimeNow());

        mClient.post(HttpAPI.API().STOCK_MUCH, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                checkGoodsByType(mGid, 1, 10, mCondition);
                mTvStockNum.setText("0种");
                mStockBeanList.clear();
                if (isPD){
                    CustomToast.makeText(StockTakingActivity.this, "盘点成功！", Toast.LENGTH_SHORT).show();
                    isPD = false;
                }

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(StockTakingActivity.this, msg, Toast.LENGTH_SHORT).show();
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
            checkGoodsByType(mGid, 1, 10, mCondition);
            hasTimerDown = false;
        } else {
            mInputHandler.sendEmptyMessageDelayed(1, 300);
        }
    }
}
