package com.zhiluo.android.yunpu.member.manager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.zhiluo.android.yunpu.member.manager.fragment.FastConsumeRecordFragment;
import com.zhiluo.android.yunpu.member.manager.fragment.GoodsConsumeRecordFragment;
import com.zhiluo.android.yunpu.member.manager.fragment.RechargeRecordFragment;
import com.zhiluo.android.yunpu.member.manager.fragment.RechargeTimesRecordFragment;
import com.zhiluo.android.yunpu.statistics.account.fragment.DelayFragment;
import com.zhiluo.android.yunpu.statistics.account.fragment.IntegralDetailFragment;
import com.zhiluo.android.yunpu.statistics.account.fragment.MoneyDetailFragment;
import com.zhiluo.android.yunpu.statistics.account.fragment.RechargeMoneyFragment;
import com.zhiluo.android.yunpu.statistics.account.fragment.RechargeTimesFragment;

/**
 * 作者：罗咏哲 on 2017/9/4 15:57.
 * 邮箱：137615198@qq.com
 */

public class RecordPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"会员充值", "会员充次", "商品消费", "快速消费"};

    public RecordPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 1) {
            return new RechargeTimesRecordFragment();
        } else if (i == 2) {
            return new GoodsConsumeRecordFragment();
        } else if (i == 3) {
            return new FastConsumeRecordFragment();
        }
        return new RechargeRecordFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        try {
            super.finishUpdate(container);
        } catch (Exception e) {
            container = null;
        }

    }
}
