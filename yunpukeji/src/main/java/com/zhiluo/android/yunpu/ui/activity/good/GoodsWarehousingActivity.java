package com.zhiluo.android.yunpu.ui.activity.good;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.goods.manager.adapter.GoodsTypeExpandAdapter;
import com.zhiluo.android.yunpu.goods.manager.adapter.GoodsWarehousingAdapter;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.mvp.model.GoodsType;
import com.zhiluo.android.yunpu.mvp.presenter.WareHousingPresenter;
import com.zhiluo.android.yunpu.mvp.view.IWareHousingView;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Date: 2017/9/26 16:22
 * Author: 罗咏哲
 * Description: 商品入库
 * Version:
 */
public class GoodsWarehousingActivity extends BaseActivity implements GoodsWarehousingAdapter.IClick {
    private EditText mEtGoodsCode;
    private double mTotal,mTotalnum;
    private String mGoodCode;//商品编码
    private WareHousingPresenter mPresenter;
    private IWareHousingView mView;
    private GoodsWarehousingAdapter mAdapter;
    private ListView mListView;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mGoodList = new ArrayList<>();
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mStockGoodList = new ArrayList<>();//入库商品列表
    private TextView  mTvTotalnum, mBtnStock;
    private ExpandableListView mExpandableListView;
    int expandFlag = -1;//控制列表的展开
    private Map<GoodsType.DataBean, List<GoodsType.DataBean>> mMap;
    private List<GoodsType.DataBean> mParent;
    GoodsTypeExpandAdapter adapter;
    private String mTypeGid, mEditTextContent;//类型Gid，搜索框内容
    private int mLastParentPos;
    private WaveSwipeRefreshLayout mRefreshLayout;
    private int mRefreshIndex = 2;
    private boolean mIsLoadMore;//加载更多
    private int mPageTotal;//数据总页数
    private InputHandler mInputHandler = new InputHandler();
    private boolean hasTimerDown = false;
    private long pretime;//第一次的时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_goods_warehousing);
        initView();
        loadData();
        setListener();
    }


    private void initView() {

        mRefreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.refresh_warehousing);
        mEtGoodsCode = (EditText) findViewById(R.id.et_stock_taking_search);
        mListView = (ListView) findViewById(R.id.lv_goods_warehousing_goods);
//        mRecyclerView = (RecyclerView) findViewById(R.id.rv_warehousing_type);
        mTvTotalnum = (TextView) findViewById(R.id.tv_goods_warehousing_total);
        mBtnStock = (TextView) findViewById(R.id.btn_goods_warehousing_stock);

        mExpandableListView = (ExpandableListView) findViewById(R.id.expandable_list_view);

        mPresenter = new WareHousingPresenter(this);
        mPresenter.onCreate("");

        mView = new IWareHousingView() {

            @Override
            public void getTypeSuccess(List<GoodsType.DataBean> parent, Map<GoodsType.DataBean, List<GoodsType.DataBean>> map) {
                mMap = map;
                mParent = parent;
                adapter = new GoodsTypeExpandAdapter(GoodsWarehousingActivity.this,
                        parent, map);
                mExpandableListView.setGroupIndicator(null);
                mExpandableListView.setVerticalScrollBarEnabled(false);
                mParent.get(0).setChecked(true);
                mLastParentPos = 0;
                expandFlag = 0;
                mExpandableListView.setAdapter(adapter);
                mExpandableListView.expandGroup(0);
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

            }


            @Override
            public void getCombofail(String result) {

            }
        };
        mPresenter.attachView(mView);
    }

    private void loadData() {
        mGoodCode = getIntent().getStringExtra("GOOD_CODE");
        if (mGoodCode != null) {
            mEditTextContent = mGoodCode;
            mEtGoodsCode.setText(mGoodCode);
        }
        getGoods(1, 20);
    }

//    private void getGoods(int index, int size) {
//        RequestParams params = new RequestParams();
//        params.put("PT_GID", mTypeGid);
//        params.put("PageIndex", index);
//        params.put("PageSize", size);
//        params.put("PM_IsService", "0,2");
//        params.put("PM_CodeOrNameOrSimpleCode", mEditTextContent);
//        mPresenter.getGoods(params);
//    }


    public void getGoods(int index, int size) {

        RequestParams params = new RequestParams();
        params.put("PT_GID", mTypeGid);
        params.put("PageIndex", index);
        params.put("PageSize", size);
        params.put("DataType", 3);
        params.put("SortType", 1);
//        params.put("PM_IsService", "0,2");
        params.put("PM_CodeOrNameOrSimpleCode", mEditTextContent);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                GoodsCheckResponseByType entity = CommonFun.JsonToObj(responseString, GoodsCheckResponseByType.class);
                mPageTotal = entity.getData().getPageTotal();
                if (mStockGoodList.size() > 0) {
                    for (int i = 0; i < mStockGoodList.size(); i++) {
                        for (int j = 0; j < entity.getData().getDataList().size(); j++) {
                            if (entity.getData().getDataList().get(j).getGID().equals(mStockGoodList.get(i).getGID())
                                    && entity.getData().getDataList().get(j).getPM_PurchasePrice() == mStockGoodList.get(i).getPM_PurchasePrice()) {
                                entity.getData().getDataList().get(j).setNum(mStockGoodList.get(i).getNum());
//                                entity.getData().getDataList().get(j).setPM_PurchasePrice(mStockGoodList.get(i).getPM_PurchasePrice());
                            }
                        }
                    }
                }
                if (mGoodList != null && mIsLoadMore) {
                    mGoodList.addAll(entity.getData().getDataList());
                } else {
                    mGoodList = entity.getData().getDataList();
                }
                if (mAdapter == null){
                    mAdapter = new GoodsWarehousingAdapter(GoodsWarehousingActivity.this, mGoodList,
                            GoodsWarehousingActivity.this);
                    mListView.setAdapter(mAdapter);
                }else {
                    mAdapter.setParm(mGoodList);
                    mAdapter.notifyDataSetChanged();
                }
                mRefreshLayout.setLoading(false);
                mRefreshLayout.setRefreshing(false);
                mIsLoadMore = false;

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(GoodsWarehousingActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this,"加载中...",false);
        HttpHelper.post(this, HttpAPI.API().GOODS_HOUSE, params, callBack);
    }

    private void setListener() {
        findViewById(R.id.iv_stock_taking_scan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(GoodsWarehousingActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });

        findViewById(R.id.tv_stock_taking_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //出入库确定
        findViewById(R.id.btn_goods_warehousing_stock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mStockGoodList.isEmpty()) {
                    Intent intent = new Intent(GoodsWarehousingActivity.this, InOutWareHouseActivity.class);
                    intent.putExtra("total", mTotal);
                    intent.putExtra("totalnum", mTotalnum);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("good", (Serializable) mStockGoodList);
                    intent.putExtra("good", bundle);
                    startActivity(intent);
                } else {
                    CustomToast.makeText(GoodsWarehousingActivity.this, "出入库商品不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (YSLUtils.isFastClick()) {//防止卡顿多次点击
//                    //  showNumDialog(position);
//                    Intent intent = new Intent(GoodsWarehousingActivity.this, InOutWareHouseActivity.class);
//                    Bundle bundle = new Bundle();
//                    if (mStockGoodList!=null){
//                        mStockGoodList.clear();
//                    }
//                    mStockGoodList.add(mGoodList.get(position));
//                    bundle.putSerializable("good", (Serializable) mStockGoodList);
//                    intent.putExtra("good", bundle);
//                    startActivityForResult(intent,1008);
//                }
//            }
//        });

        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (expandFlag == -1) {
                    mExpandableListView.expandGroup(groupPosition);
                    mExpandableListView.setSelectedGroup(groupPosition);
                    expandFlag = groupPosition;
                } else if (expandFlag == groupPosition) {
                    mExpandableListView.collapseGroup(expandFlag);
                    expandFlag = -1;
                } else {
                    mExpandableListView.collapseGroup(expandFlag);
                    mExpandableListView.expandGroup(groupPosition);
                    mExpandableListView.setSelectedGroup(groupPosition);
                    expandFlag = groupPosition;
                }
                mParent.get(groupPosition).setChecked(true);
                for (int i = 0; i < mParent.size(); i++) {
                    if (groupPosition != i) {
                        mParent.get(i).setChecked(false);
                    }
                }
                if (mLastParentPos != groupPosition) {
                    for (int i = 0; i < mMap.get(mParent.get(mLastParentPos)).size(); i++) {
                        mMap.get(mParent.get(mLastParentPos)).get(i).setChecked(false);
                    }
                    mLastParentPos = groupPosition;
                }
                mTypeGid = mParent.get(groupPosition).getGID();
                getGoods(1, 20);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                mMap.get(mParent.get(groupPosition)).get(childPosition).setChecked(true);
                adapter.notifyDataSetChanged();
                for (int i = 0; i < mMap.get(mParent.get(groupPosition)).size(); i++) {
                    if (i != childPosition) {
                        mMap.get(mParent.get(groupPosition)).get(i).setChecked(false);
                    }
                }
                mTypeGid = mMap.get(mParent.get(groupPosition)).get(childPosition).getGID();
                getGoods(1, 20);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        mEtGoodsCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pretime = System.currentTimeMillis();
                if (!hasTimerDown) {
                    doQurry();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    mEditTextContent = s.toString();
                } else {
                    mEditTextContent = "";
                }
                //getGoods(1, 20);
            }
        });

        mRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mStockGoodList.clear();
                updateView();
                getGoods(1, 20);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    getGoods(mRefreshIndex, 20);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(GoodsWarehousingActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
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
    }

    /**
     * 显示修改数量对话框
     */
    private void showNumDialog(final int position) {
        LayoutInflater inflater = getLayoutInflater();
        final View dialog = inflater.inflate(R.layout.dialog_keybord, (ViewGroup) findViewById(R.id.ll_keyboard_dialog));
        final EditText etNum = (EditText) dialog.findViewById(R.id.et_keyboard_num);
        final EditText etPrice = (EditText) dialog.findViewById(R.id.et_keyboard_price);
        etPrice.setText("" + mGoodList.get(position).getPM_PurchasePrice());
        Button btnConfirm = (Button) dialog.findViewById(R.id.btn_keyboard_confirm);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_keyboard_cancel);
        AlertDialog.Builder builder = new AlertDialog.Builder(GoodsWarehousingActivity.this);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setContentView(dialog);
        alertDialog.setView(dialog);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etPrice.getText())) {
                    mGoodList.get(position).setPM_PurchasePrice(Double.parseDouble(etPrice.getText().toString()));
                } else {
                    mGoodList.get(position).setPM_PurchasePrice(0);
                }
                if (!TextUtils.isEmpty(etNum.getText())) {
                    mGoodList.get(position).setNum(Double.parseDouble(etNum.getText().toString()));
                } else {
                    mGoodList.get(position).setNum(0);
                }
                boolean flag = true;
                if (!mStockGoodList.isEmpty()) {
                    for (int i = 0; i < mStockGoodList.size(); i++) {
                        if (mGoodList.get(position).getGID().equals(mStockGoodList.get(i).getGID())) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        mStockGoodList.add(mGoodList.get(position));
                    }
                } else {
                    mStockGoodList.add(mGoodList.get(position));
                }
                updateView();
                mAdapter.notifyDataSetChanged();
                alertDialog.cancel();
            }
        });
        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            mEtGoodsCode.setText(scanResult);
        }
        if (requestCode==1008){
            doQurry();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();

        GoodsCheckResponseByType.DataBean.DataListBean bean = mGoodList.get(position);
        String gid = bean.getGID();
        double jinjia = bean.getPM_PurchasePrice();
        if (!mStockGoodList.isEmpty()) {
            for (int i = 0; i < mStockGoodList.size(); i++) {
                if (mStockGoodList.get(i).getGID().equals(gid) && mStockGoodList.get(i).getPM_PurchasePrice() == jinjia) {
                    mStockGoodList.remove(i);
                }
            }
        }
        double num;
        num = bean.getNum();
        switch (view.getId()) {
            //移除
            case R.id.iv_reduce:
                if (num > 0) {
                    num--;
                } else {
                    num = 0;
                }
                bean.setNum(num);
                if (num > 0) {
                    mStockGoodList.add(bean);
                }
                updateView();
//                countGoodsNum();
                break;
            //增加
            case R.id.iv_add:
                num++;
                bean.setNum(num);
                mStockGoodList.add(bean);
                updateView();
//                countGoodsNum();
                break;
        }
    }

    /**
     * 更新下方显示
     */
    private void updateView() {
        mTotal = 0;
        mTotalnum = 0;
        if (mStockGoodList.size() > 0) {
            for (int i = 0; i < mStockGoodList.size(); i++) {
                mTotal += DoubleMathUtil.mul(mStockGoodList.get(i).getNum(),mStockGoodList.get(i).getPM_PurchasePrice());
                mTotalnum += mStockGoodList.get(i).getNum();
            }
            mTvTotalnum.setText(mTotalnum + "");
//            mTvTotalnum.setText(mTotal + "");
//            mTvNum.setText(mStockGoodList.size() + "");
        } else {
            mTvTotalnum.setText("0");
//            mTvNum.setText("0");
        }

        if (mAdapter!=null){
            mAdapter.notifyDataSetChanged();
        }

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
            getGoods(1, 20);
            hasTimerDown = false;
        } else {
            mInputHandler.sendEmptyMessageDelayed(1, 300);
        }
    }
}
