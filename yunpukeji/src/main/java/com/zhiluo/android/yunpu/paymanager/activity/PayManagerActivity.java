package com.zhiluo.android.yunpu.paymanager.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.paymanager.adapter.PayFragmentAdapter;
import com.zhiluo.android.yunpu.statistics.account.activity.DelayScreenActivity;
import com.zhiluo.android.yunpu.statistics.account.activity.MemberAccountActivity;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by ZPH on 2019-05-21.
 */

public class PayManagerActivity extends BaseActivity {


    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title_pay)
    TextView tvTitle;
    @Bind(R.id.rl_head)
    RelativeLayout rlHead;
    @Bind(R.id.tab_pay)
    TabLayout mTabLayout;
    @Bind(R.id.vp_pay)
    ViewPager mViewPager;
    @Bind(R.id.iv_title_screen)
    ImageView ivTitleScreen;
    @Bind(R.id.tv_sx)
    TextView tvSx;
    private PayFragmentAdapter mFragmentPagerAdapter;
    private String mTypeName, mTypeGid;
    private String vip,oneGid,twoGid;
    private static int mPositon = 1;
    private PayReceiver payReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_manager);
        ButterKnife.bind(this);

        initVariable();
        loadData();
        setListener();

        if (payReceiver == null) {
            payReceiver = new PayReceiver();
        }
        IntentFilter filer1 = new IntentFilter();
        filer1.addAction("android.intent.action.PAY_BROADCAST");
        registerReceiver(payReceiver, filer1);
    }

    private void initVariable() {
        mFragmentPagerAdapter = new PayFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setSelected(true);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        mPositon = 1;

                        break;
                    case 1:
                        mPositon = 2;

                        break;
                    case 2:
                        mPositon = 3;
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
        ivTitleScreen.setVisibility(View.GONE);
        tvSx.setVisibility(View.GONE);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                switch (i) {
                    case 0:
                        ivTitleScreen.setVisibility(View.GONE);
                        tvSx.setVisibility(View.GONE);
                        break;
                    case 1:
                        ivTitleScreen.setVisibility(View.VISIBLE);
                        tvSx.setVisibility(View.VISIBLE);
                        //条件筛选监听
                        ivTitleScreen.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(PayManagerActivity.this, PayDetailScreenActivity.class);
                                startActivityForResult(intent, 666);
                            }
                        });

                        tvSx.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(PayManagerActivity.this, PayDetailScreenActivity.class);

                                startActivityForResult(intent, 666);
                            }
                        });

                        break;

                    case 2:
                        ivTitleScreen.setVisibility(View.GONE);
                        tvSx.setVisibility(View.GONE);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private void loadData() {


    }

    private void setListener() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     * @param msg    ,,
     * @param bundle ,,
     */
    private void sendBundle(String msg, Bundle bundle) {
        Intent intent = null;
        if (mPositon == 1) {
            intent = new Intent("android.intent.action.INPUT_BROADCAST");
        } else if (mPositon == 3) {
            intent = new Intent("android.intent.action.SUM_BROADCAST");
        } else if (mPositon == 2) {
            intent = new Intent("android.intent.action.DETAIL_BROADCAST");
        }
        intent.putExtra(msg, bundle);
        sendBroadcast(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 55 && data != null) {
            mTypeName = data.getStringExtra("ET_Name");//选择的在支出类别
            mTypeGid = data.getStringExtra("ET_GID");

            Bundle bundle = new Bundle();
            bundle.putString("ET_Name", mTypeName);
            bundle.putString("ET_GID", mTypeGid);
            sendBundle("inputtype", bundle);
        }

        if (resultCode == 666 && data != null){
            vip = data.getStringExtra("vip");//选择的在支出类别
            oneGid = data.getStringExtra("One");
            twoGid = data.getStringExtra("Two");

            Bundle bundle = new Bundle();
            bundle.putString("vip", vip);
            bundle.putString("One", oneGid);
            bundle.putString("Two", twoGid);
            sendBundle("Detail", bundle);
        }

        if (resultCode == 222){
            Bundle bundle = new Bundle();
            sendBundle("updata", bundle);
        }
    }

    class PayReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getStringExtra("msg").equals("uploadimg")) {

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(payReceiver);
    }
}
