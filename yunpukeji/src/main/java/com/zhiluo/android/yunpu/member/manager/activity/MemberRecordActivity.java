package com.zhiluo.android.yunpu.member.manager.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.member.manager.adapter.RecordPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date: 2017/12/1 11:51
 * Author: 罗咏哲
 * Description: 会员记录
 * Version:
 */
public class MemberRecordActivity extends AppCompatActivity {

    @Bind(R.id.tv_member_record_back)
    TextView tvBack;
    @Bind(R.id.tab_member_record)
    TabLayout tabRecord;
    @Bind(R.id.vp_member_record)
    ViewPager vpMemberRecord;
    private String mVipCard;
    private RecordPagerAdapter mPagerAdapter;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_record);
        ButterKnife.bind(this);
        mVipCard = getIntent().getStringExtra("Card");
        init();
        loadData();
        setListener();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
    }

    private void init() {
        mPagerAdapter = new RecordPagerAdapter(getSupportFragmentManager());
        vpMemberRecord.setAdapter(mPagerAdapter);
        tabRecord.setupWithViewPager(vpMemberRecord);
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

    public String getmVipCard() {
        return mVipCard;
    }
}
