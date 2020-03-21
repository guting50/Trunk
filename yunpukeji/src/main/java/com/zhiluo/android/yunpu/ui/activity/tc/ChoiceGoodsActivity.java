package com.zhiluo.android.yunpu.ui.activity.tc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.consume.adapter.GoodsConsumeRightAdapter;
import com.zhiluo.android.yunpu.goods.manager.adapter.GoodsTypeExpandAdapter;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.mvp.model.GoodsType;
import com.zhiluo.android.yunpu.mvp.presenter.WareHousingPresenter;
import com.zhiluo.android.yunpu.mvp.view.IWareHousingView;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date: 2018/1/26 16:18
 * Author: 罗咏哲
 * Description: 选择商品
 * Version:
 */
public class ChoiceGoodsActivity extends AppCompatActivity implements GoodsConsumeRightAdapter.ItemViewClick {

    @Bind(R.id.tv_choice_goods_back)
    TextView tvBack;
    @Bind(R.id.tv_choice_goods_confirm)
    TextView tvConfirm;
    @Bind(R.id.iv_choice_goods_search)
    ImageView ivSearch;
    @Bind(R.id.et_choice_goods_condition)
    EditText etCondition;
    @Bind(R.id.lv_choice_goods_left)
    ExpandableListView lvType;
    @Bind(R.id.lv_choice_goods_right)
    BaseListView lvGoods;
    @Bind(R.id.refresh_choice_goods)
    WaveSwipeRefreshLayout mRefresh;

    private WareHousingPresenter mPresenter;
    private IWareHousingView mView;
    private GoodsTypeExpandAdapter mLeftAdapter;//左边Adapter
    private Map<GoodsType.DataBean, List<GoodsType.DataBean>> mChild;
    private List<GoodsType.DataBean> mParentGoodsType;//父类型
    int expandFlag = -1;//控制列表的展开
    private int mLastParentPos;
    private int mCurrentPosition;//ListView当前滑动位置
    private boolean mIsLoadMore;
    private int mRefreshIndex = 2;
    private GoodsCheckResponseByType mGoodsInfo;//商品信息
    private int mPageTotal;//数据总页数
    private String mGid;//商品类型GID
    private GoodsConsumeRightAdapter mRightAdapter;//右边ListView的Adapter
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mHaveChoicedGoodList;//已选商品信息
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mHaveChoicedGoodList1;
     private int mTcType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_goods);
        ButterKnife.bind(this);
        initView();
        loadData();
        setListener();
    }

    private void initView() {
        
        mTcType = getIntent().getIntExtra("TCTYPE",0);
        mParentGoodsType = new ArrayList<>();
        mChild = new HashMap<>();
        mHaveChoicedGoodList = new ArrayList<>();
        mHaveChoicedGoodList1 = (List<GoodsCheckResponseByType.DataBean.DataListBean>) getIntent().getSerializableExtra("mHaveChoicedGoodList");
        if (mHaveChoicedGoodList1 != null){
            for (int i = 0;i< mHaveChoicedGoodList1.size();i++){
                mHaveChoicedGoodList.add(mHaveChoicedGoodList1.get(i));
            }
        }
        mPresenter = new WareHousingPresenter(this);
        mPresenter.onCreate("");
        mView = new IWareHousingView() {
            @Override
            public void getTypeSuccess(List<GoodsType.DataBean> parent, Map<GoodsType.DataBean, List<GoodsType.DataBean>> map) {
                mChild = map;
                mParentGoodsType = parent;
                mLeftAdapter = new GoodsTypeExpandAdapter(ChoiceGoodsActivity.this,
                        parent, map);
                lvType.setGroupIndicator(null);
                lvType.setVerticalScrollBarEnabled(false);
                mParentGoodsType.get(0).setChecked(true);
                mLastParentPos = 0;
                expandFlag = 0;
                lvType.setAdapter(mLeftAdapter);
                lvType.expandGroup(0);
            }

            @Override
            public void getTypeFail(String result) {

            }

            @Override
            public void getGoodsSuccess(GoodsCheckResponseByType entity) {
                mPageTotal = entity.getData().getPageTotal();
                if (mHaveChoicedGoodList !=null && mHaveChoicedGoodList.size() > 0) {
                    for (int i = 0; i < mHaveChoicedGoodList.size(); i++) {
                        for (int j = 0; j < entity.getData().getDataList().size(); j++) {
                            if (entity.getData().getDataList().get(j).getGID()
                                    .equals(mHaveChoicedGoodList.get(i).getGID())) {
                                entity.getData().getDataList().get(j).setNum(mHaveChoicedGoodList.get(i).getNum());
                            }
                        }
                    }
                }
                if (mGoodsInfo != null && mIsLoadMore) {
                    mGoodsInfo.getData().getDataList().addAll(entity.getData().getDataList());
                } else {
                    mGoodsInfo = entity;
                }
                setRightAdapter();
                mIsLoadMore = false;
                mRefresh.setRefreshing(false);
                mRefresh.setLoading(false);
                lvGoods.setSelection(mCurrentPosition);
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

    /**
     * 设置右边ListViewAdapter
     */
    private void setRightAdapter() {
        if (mRightAdapter == null) {
            mRightAdapter = new GoodsConsumeRightAdapter(this, mGoodsInfo.getData().getDataList(), this);
            lvGoods.setAdapter(mRightAdapter);
//            lvGoods.setSelection(mCurrentPosition);
        }else {
            mRightAdapter.setParm(mGoodsInfo.getData().getDataList());
            mRightAdapter.notifyDataSetChanged();
        }
    }

    private void loadData() {
        getGoods(1, 20);
    }

    private void getGoods(int index, int size) {
        RequestParams params = new RequestParams();
        params.put("PageIndex", index);
        params.put("PageSize", size);
        params.put("PT_GID", mGid);
        params.put("PM_IsService", "");
        params.put("PM_UnitPriceMin","");
        params.put("PM_UnitPriceMax","");
      
        params.put("PM_CodeOrNameOrSimpleCode", etCondition.getText().toString());
       
        params.put("DataType", mTcType ==0?2:4);
        mPresenter.getGoods(params);
    }

    private void setListener() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHaveChoicedGoodList.size() > 0) {
                    Intent intent = new Intent();
                    intent.putExtra("GOODS", (Serializable) mHaveChoicedGoodList);
                    setResult(200, intent);
                    finish();
                } else {
                    CustomToast.makeText(ChoiceGoodsActivity.this, "请选择商品", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //设置下拉刷新
        mRefresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {

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
                    CustomToast.makeText(ChoiceGoodsActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
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
        //获取当前ListView滑动位置
        lvGoods.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                    mCurrentPosition = lvGoods.getFirstVisiblePosition();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
        lvType.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
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
        lvType.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (expandFlag == -1) {
                    lvType.expandGroup(groupPosition);
                    lvType.setSelectedGroup(groupPosition);
                    expandFlag = groupPosition;
                } else if (expandFlag == groupPosition) {
                    lvType.collapseGroup(expandFlag);
                    expandFlag = -1;
                } else {
                    lvType.collapseGroup(expandFlag);
                    lvType.expandGroup(groupPosition);
                    lvType.setSelectedGroup(groupPosition);
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
                mRefreshIndex = 2;
                mLeftAdapter.notifyDataSetChanged();
                return true;
            }
        });
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGoods(1, 20);
            }
        });
    }


    @Override
    public void click(View view) {
        int position = (int) view.getTag();
        GoodsCheckResponseByType.DataBean.DataListBean bean = mGoodsInfo.getData().getDataList().get(position);
        String gid = bean.getGID();
        if (mHaveChoicedGoodList !=null && mHaveChoicedGoodList.size()>0) {
            for (int i = 0; i < mHaveChoicedGoodList.size(); i++) {
                if (mHaveChoicedGoodList.get(i).getGID().equals(gid)) {
                    mHaveChoicedGoodList.remove(i);
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
                    mHaveChoicedGoodList.add(bean);
                }
                break;
            //增加
            case R.id.iv_add:
                num++;
                bean.setNum(num);
                mHaveChoicedGoodList.add(bean);
                break;
        }
        if (mRightAdapter != null) {
            mRightAdapter.notifyDataSetChanged();
        }
    }
}
