package com.zhiluo.android.yunpu.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.zhiluo.android.yunpu.member.manager.fragment.FastConsumeRecordFragment;
import com.zhiluo.android.yunpu.member.manager.fragment.GoodsConsumeRecordFragment;
import com.zhiluo.android.yunpu.member.manager.fragment.RechargeRecordFragment;
import com.zhiluo.android.yunpu.member.manager.fragment.RechargeTimesRecordFragment;
import com.zhiluo.android.yunpu.ui.fragment.ChainFragment;
import com.zhiluo.android.yunpu.ui.fragment.GaoJiFragment;
import com.zhiluo.android.yunpu.ui.fragment.HardwareFragment;
import com.zhiluo.android.yunpu.ui.fragment.MessageStoreFragment;

/**
 * 作者：罗咏哲 on 2017/9/4 15:57.
 * 邮箱：137615198@qq.com
 */

public class StorePagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"高级版", "连锁版", "硬件商城", "短信"};

    public StorePagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int i) {
        if (i == 1) {
            return new ChainFragment();
        } else if (i == 2) {
            return new HardwareFragment();
        } else if (i == 3) {
            return new MessageStoreFragment();
        }
        return new GaoJiFragment();
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
