package com.zhiluo.android.yunpu.statistics.rebate.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.zhiluo.android.yunpu.statistics.account.event.DateEvent;
import com.zhiluo.android.yunpu.statistics.account.fragment.DelayFragment;
import com.zhiluo.android.yunpu.statistics.account.fragment.IntegralDetailFragment;
import com.zhiluo.android.yunpu.statistics.account.fragment.MoneyDetailFragment;
import com.zhiluo.android.yunpu.statistics.account.fragment.RechargeMoneyFragment;
import com.zhiluo.android.yunpu.statistics.account.fragment.RechargeTimesFragment;
import com.zhiluo.android.yunpu.statistics.rebate.fragment.RebateDetailFragment;
import com.zhiluo.android.yunpu.statistics.rebate.fragment.RebateListFragment;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * 作者：罗咏哲 on 2017/9/4 15:57.
 * 邮箱：137615198@qq.com
 */

public class RebateFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"推荐列表", "返利明细"};
    private Fragment mCurrentFragment;

    public RebateFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        if (i == 1) {
            return new RebateDetailFragment();
        }
        return new RebateListFragment();
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
        mCurrentFragment = (Fragment) object;
        super.setPrimaryItem(container, position, object);
    }

    public Fragment getmCurrentFragment() {
        return mCurrentFragment;
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
