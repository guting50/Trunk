package com.zhiluo.android.yunpu.statistics.account.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.zhiluo.android.yunpu.statistics.account.bean.RechargeMoneyReportBean;
import com.zhiluo.android.yunpu.statistics.account.fragment.DelayFragment;
import com.zhiluo.android.yunpu.statistics.account.fragment.IntegralDetailFragment;
import com.zhiluo.android.yunpu.statistics.account.fragment.MemberSignFragment;
import com.zhiluo.android.yunpu.statistics.account.fragment.MoneyDetailFragment;
import com.zhiluo.android.yunpu.statistics.account.fragment.RechargeMoneyFragment;
import com.zhiluo.android.yunpu.statistics.account.fragment.RechargeTimesFragment;

/**
 * 作者：罗咏哲 on 2017/9/4 15:57.
 * 邮箱：137615198@qq.com
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"充值记录", "充次记录", "延期记录", "积分明细", "金额明细","签到记录"};
    private Fragment mCurrentFragment;

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 1) {
            return new RechargeTimesFragment();
        } else if (i == 2) {
            return new DelayFragment();
        } else if (i == 3) {
            return new IntegralDetailFragment();
        } else if (i == 4) {
            return new MoneyDetailFragment();
        }else if (i == 5) {
            return new MemberSignFragment();
        }
        return new RechargeMoneyFragment();
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
