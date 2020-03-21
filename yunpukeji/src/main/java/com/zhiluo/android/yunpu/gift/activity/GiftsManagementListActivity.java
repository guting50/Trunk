package com.zhiluo.android.yunpu.gift.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.manager.activity.GoodsManagerActivity;
import com.zhiluo.android.yunpu.goods.manager.adapter.GoodsInfoAdapter;
import com.zhiluo.android.yunpu.goods.manager.adapter.GoodsTypeExpandAdapter;

import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.mvp.model.GoodsType;
import com.zhiluo.android.yunpu.mvp.presenter.WareHousingPresenter;
import com.zhiluo.android.yunpu.mvp.view.IWareHousingView;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.HomeActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 礼物列表界面用于展示礼物
 */
public class GiftsManagementListActivity extends BaseActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.iv_gift_manager_add)
    ImageView ivGiftManagerAdd;
    @Bind(R.id.elv_gift_manager)
    ExpandableListView elvGiftManager;
    @Bind(R.id.lv_gift_manager)
    BaseListView lvGiftManager;
    @Bind(R.id.wv_gift_manager)
    WaveSwipeRefreshLayout wvGiftManager;
    private WareHousingPresenter mPresenter;
    private IWareHousingView mView;
    private Map<GoodsType.DataBean, List<GoodsType.DataBean>> mChild;
    private List<GoodsType.DataBean> mParentGoodsType;//父类型
    private GoodsTypeExpandAdapter mLeftAdapter;//左边Adapter
    private int mLastParentPos;
    int expandFlag = -1;//控制列表的展开
    private int mPageTotal;//数据总页数
    private int mCurrentClickPosition = 0;//当前点击位置
    private int mCurrentPosition;//ListView当前滑动位置
    private boolean mIsLoadMore;
    private int mRefreshIndex = 2;
    private GoodsCheckResponseByType mGoodsInfo;//商品信息
    private GoodsInfoAdapter mRightAdapter;//右边ListView Adapter
    private String mGid;//商品类型GID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifts_management_list);
        ButterKnife.bind(this);
        init();
        initVariable();
        setListener();
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        mParentGoodsType = new ArrayList<>();
        mChild = new HashMap<>();
        getGoods(1, 20);
    }

    private void getGoods(int index, int size) {
        RequestParams params = new RequestParams();
        params.put("PT_GID", mGid);
        params.put("PageIndex", index);
        params.put("PageSize", size);
        params.put("PM_IsService", 2);
        params.put("PM_CodeOrNameOrSimpleCode", "");
        mPresenter.getGoods(params);
    }

    private void init() {
        mPresenter = new WareHousingPresenter(this);
        mPresenter.onCreate("LP");
        mView = new IWareHousingView() {
            @Override
            public void getTypeSuccess(List<GoodsType.DataBean> parent, Map<GoodsType.DataBean, List<GoodsType.DataBean>> map) {
                mChild = map;
                mParentGoodsType = parent;
                mLeftAdapter = new GoodsTypeExpandAdapter(GiftsManagementListActivity.this,
                        parent, map);
                elvGiftManager.setGroupIndicator(null);
                elvGiftManager.setVerticalScrollBarEnabled(false);
                mParentGoodsType.get(0).setChecked(true);
                mLastParentPos = 0;
                expandFlag = 0;
                elvGiftManager.setAdapter(mLeftAdapter);
                elvGiftManager.expandGroup(0);
            }

            @Override
            public void getTypeFail(String result) {

            }

            @Override
            public void getGoodsSuccess(GoodsCheckResponseByType entity) {
                mPageTotal = entity.getData().getPageTotal();
                if (mIsLoadMore && mGoodsInfo != null) {
                    mGoodsInfo.getData().getDataList().addAll(entity.getData().getDataList());
                } else {
                    mGoodsInfo = entity;
                }
                if (mGoodsInfo != null) {
                    mRightAdapter = new GoodsInfoAdapter(GiftsManagementListActivity.this, mGoodsInfo);
                    lvGiftManager.setAdapter(mRightAdapter);
                    lvGiftManager.setSelection(mCurrentPosition);
                }
                mIsLoadMore = false;
                wvGiftManager.setRefreshing(false);
                wvGiftManager.setLoading(false);
                lvGiftManager.setSelection(mCurrentPosition);
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

//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
////        if (keyCode == KeyEvent.KEYCODE_BACK) {
////            Intent intent = new Intent(GiftsManagementListActivity.this, HomeActivity.class);
////            startActivity(intent);
////        }
//        finish();
//        return true;
//    }

    private void setListener() {

        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(GiftsManagementListActivity.this, HomeActivity.class);
//                startActivity(intent);
                finish();
            }
        });
        ivGiftManagerAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiftsManagementListActivity.this, AddGiftsActivity.class);
                startActivity(intent);
            }
        });
        //礼品点击修改删除
        lvGiftManager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GiftsManagementListActivity.this, GiftsDetailActivity.class);
                intent.putExtra("giftdetail", mGoodsInfo.getData().getDataList().get(position));
                startActivity(intent);
            }
        });

        //滑动刷新监听
        wvGiftManager.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
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
                    CustomToast.makeText(GiftsManagementListActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    wvGiftManager.setLoading(false);
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
        //获取当前ListView滑动位置
        lvGiftManager.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                    mCurrentPosition = lvGiftManager.getFirstVisiblePosition();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
        elvGiftManager.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                mChild.get(mParentGoodsType.get(groupPosition)).get(childPosition).setChecked(true);
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
        elvGiftManager.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (expandFlag == -1) {
                    elvGiftManager.expandGroup(groupPosition);
                    elvGiftManager.setSelectedGroup(groupPosition);
                    expandFlag = groupPosition;
                } else if (expandFlag == groupPosition) {
                    elvGiftManager.collapseGroup(expandFlag);
                    expandFlag = -1;
                } else {
                    elvGiftManager.collapseGroup(expandFlag);
                    elvGiftManager.expandGroup(groupPosition);
                    elvGiftManager.setSelectedGroup(groupPosition);
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
                getGoods(1, 20);
                mLeftAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
