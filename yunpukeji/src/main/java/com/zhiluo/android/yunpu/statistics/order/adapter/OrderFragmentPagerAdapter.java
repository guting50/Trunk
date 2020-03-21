package com.zhiluo.android.yunpu.statistics.order.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.zhiluo.android.yunpu.statistics.account.fragment.DelayFragment;
import com.zhiluo.android.yunpu.statistics.account.fragment.IntegralDetailFragment;
import com.zhiluo.android.yunpu.statistics.account.fragment.MoneyDetailFragment;
import com.zhiluo.android.yunpu.statistics.account.fragment.RechargeMoneyFragment;
import com.zhiluo.android.yunpu.statistics.account.fragment.RechargeTimesFragment;
import com.zhiluo.android.yunpu.statistics.order.fragment.FastConsumeFragment;
import com.zhiluo.android.yunpu.statistics.order.fragment.GoodConsumeFragment;
import com.zhiluo.android.yunpu.statistics.order.fragment.IntegralExchangeFragment;
import com.zhiluo.android.yunpu.statistics.order.fragment.LimitedConsumeFragment;
import com.zhiluo.android.yunpu.statistics.order.fragment.OneKeyFragment;
import com.zhiluo.android.yunpu.statistics.order.fragment.TimesConsumeFragment;

/**
 * 作者：罗咏哲 on 2017/9/4 15:57.
 * 邮箱：137615198@qq.com
 */

public class OrderFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles;
    private int position;

    public OrderFragmentPagerAdapter(FragmentManager fm, String[] mTitles) {
        super(fm);
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int i) {

        if (mTitles.length == 5){
            if (i == 1) {
                position = 1;
                return new FastConsumeFragment();
            } else if (i == 2) {
                position = 2;
                return new TimesConsumeFragment();
            } else if (i == 3) {
                position = 3;
                return new IntegralExchangeFragment();
            } else if (i == 4) {
                position = 4;
                return new LimitedConsumeFragment();
            }
        }else if (mTitles.length ==6){
            if (i == 1) {
                position = 1;
                return new OneKeyFragment();
            }else if (i == 2) {
                position = 2;
                return new FastConsumeFragment();
            } else if (i == 3) {
                position = 3;
                return new TimesConsumeFragment();
            } else if (i == 4) {
                position = 4;
                return new IntegralExchangeFragment();
            } else if (i == 5) {
                position = 5;
                return new LimitedConsumeFragment();
            }
        }

        position = 0;
        return new GoodConsumeFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    public int getPosition() {
        return position;
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
