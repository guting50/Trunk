package com.zhiluo.android.yunpu.ui.activity.good;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.ui.activity.good.adapter.InOutAdapter;
import com.zhiluo.android.yunpu.ui.activity.good.fragment.InWareHouseFragment;
import com.zhiluo.android.yunpu.ui.activity.good.fragment.OutWareHouseFragment;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ${YSL} on 2018-05-17.
 */

public class InOutWareHouseActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvBack, tvRecoder;
    private TabLayout tabLayout;
    private ViewPager pager;
    private TextView tvTitle;
    private InOutAdapter myInOutAdapter;
    private ArrayList<Fragment> fragments;
    private InWareHouseFragment inWareHouseFragment;
    private OutWareHouseFragment outWareHouseFragment;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mGoodList;
    private Intent mIntent;
    private Bundle bundle;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    EventBus.getDefault().post(mGoodList);
                    break;
                case 2:
                    EventBus.getDefault().post(mGoodList);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inoutwarehouse);
        initViews();
        initData();
    }


    private void initData() {
        bundle = getIntent().getBundleExtra("good");
        mGoodList = (List<GoodsCheckResponseByType.DataBean.DataListBean>) bundle.getSerializable("good");


        myInOutAdapter = new InOutAdapter(getSupportFragmentManager());
        inWareHouseFragment = new InWareHouseFragment(handler);
        outWareHouseFragment = new OutWareHouseFragment(handler);
        fragments = new ArrayList<>();
        fragments.add(inWareHouseFragment);
        fragments.add(outWareHouseFragment);
        myInOutAdapter.setFragments(fragments);
        pager.setAdapter(myInOutAdapter);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setupWithViewPager(pager);
        tvRecoder.setText("入库记录");
        tabLayout.getTabAt(0).setText("商品入库");
        tabLayout.getTabAt(1).setText("商品出库");
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tvTitle.setText("商品入库");
                        tvRecoder.setText("入库记录");
                        EventBus.getDefault().post(mGoodList);
                        break;
                    case 1:
                        tvTitle.setText("商品出库");
                        tvRecoder.setText("出库记录");
                        EventBus.getDefault().post(mGoodList);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void initViews() {
        tvBack = findViewById(R.id.tv_back);
        tvRecoder = findViewById(R.id.tv_recoder);
        tabLayout = findViewById(R.id.tab_inout_warehouse);
        pager = findViewById(R.id.view_pager);
        tvTitle = findViewById(R.id.tv_titel);
        tvBack.setOnClickListener(this);
        tvRecoder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                InOutWareHouseActivity.this.finish();
                break;
            case R.id.tv_recoder:
                if (tvRecoder.getText().toString().equals("入库记录")) {
                    Intent intent = new Intent(InOutWareHouseActivity.this, InRecoderActivity.class);
                    intent.putExtra("SM_GID", mGoodList.get(0).getSM_ID());
                    startActivity(intent);
                } else if (tvRecoder.getText().toString().equals("出库记录")) {
                    Intent intent = new Intent(InOutWareHouseActivity.this, OutRecoderActivity.class);
                    intent.putExtra("SM_GID", mGoodList.get(0).getSM_ID());
                    startActivity(intent);
                }
                break;
        }
    }

}
