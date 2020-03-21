package com.zhiluo.android.yunpu.paymanager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.zhiluo.android.yunpu.paymanager.fragment.PayDetailFragment;
import com.zhiluo.android.yunpu.paymanager.fragment.PayInputFragment;
import com.zhiluo.android.yunpu.paymanager.fragment.PaySummaryFragment;
import com.zhiluo.android.yunpu.statistics.order.fragment.FastConsumeFragment;
import com.zhiluo.android.yunpu.statistics.order.fragment.GoodConsumeFragment;
import com.zhiluo.android.yunpu.statistics.order.fragment.IntegralExchangeFragment;
import com.zhiluo.android.yunpu.statistics.order.fragment.LimitedConsumeFragment;
import com.zhiluo.android.yunpu.statistics.order.fragment.TimesConsumeFragment;

/**
 * Created by ZPH on 2019-05-21.
 */

public class PayFragmentAdapter   extends FragmentPagerAdapter {


    private String[] mTitles = new String[]{"支出录入", "支出明细"};
//    private Fragment mCurrentFragment;
    private int position;

    public PayFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
//        if (i == 1) {
//            position = 1;
//            return new PaySummaryFragment();
//        } else
            if (i == 1) {
            position = 1;
            return new PayDetailFragment();
        }
        position = 0;
        return new PayInputFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

//    @Override
//    public void setPrimaryItem(ViewGroup container, int position, Object object) {
//        mCurrentFragment = (Fragment) object;
//        super.setPrimaryItem(container, position, object);
//    }



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
