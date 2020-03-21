package com.zhiluo.android.yunpu.ui.fragment;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.CustomValueListBean;
import com.zhiluo.android.yunpu.entity.eventbus.MessageEvent;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.statistics.account.fragment.LazyLoadFragment;
import com.zhiluo.android.yunpu.ui.activity.customvlue.AddGoodsValueActivity;
import com.zhiluo.android.yunpu.ui.activity.customvlue.GoodsValueDetailActivity;
import com.zhiluo.android.yunpu.ui.adapter.CustomValueListAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 商品自定义属性
 */
public class GoodsValueFragment extends LazyLoadFragment {
    private ListView mListView;
    private CustomValueListBean mValueListBean;
    private FloatingActionButton mFab;
    private WaveSwipeRefreshLayout mRefresh;
    private CustomValueListAdapter adapter;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_goods_value;
    }

    @Override
    protected void initView(View view) {
        mListView = view.findViewById(R.id.lv_custom_goods_value);
        mFab = view.findViewById(R.id.fab_goods_value);
        mRefresh = view.findViewById(R.id.refresh_goods_value);
        setListener();
        EventBus.getDefault().register(this);
    }

    private void setListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //商品自定义属性详情
                Intent intent = new Intent(getContext(), GoodsValueDetailActivity.class);
                intent.putExtra("Value", mValueListBean.getData().get(position));
                startActivity(intent);
            }
        });
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新增商品自定义属性
                Intent intent = new Intent(getContext(), AddGoodsValueActivity.class);
                startActivity(intent);
            }
        });
        mRefresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }

            @Override
            public void onLoad() {

            }

            @Override
            public boolean canLoadMore() {
                return false;
            }

            @Override
            public boolean canRefresh() {
                return true;
            }
        });
    }

    @Override
    protected void lazyLoad() {
        getData();
    }

    private void getData() {
        RequestParams params = new RequestParams();
        params.put("PM_GID", 1);
        HttpHelper.post(getContext(), "CustomFields/GetCustomFields", params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mValueListBean = CommonFun.JsonToObj(responseString, CustomValueListBean.class);
                if (adapter == null){
                    adapter= new CustomValueListAdapter(getContext(), mValueListBean);
                    mListView.setAdapter(adapter);
                }else {
                    adapter.setParam(mValueListBean);
                    adapter.notifyDataSetChanged();
                }
                mRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                mRefresh.setRefreshing(false);
            }
        });
    }

    /**
     * 刷新列表 20191019 cy
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMemberValueRefresh(MessageEvent messageEvent) {
        if(messageEvent.getType().equals("goodes_value_fragment_refresh")){
            getData();
        }
    }
    /************************  20191019 cy */
}
