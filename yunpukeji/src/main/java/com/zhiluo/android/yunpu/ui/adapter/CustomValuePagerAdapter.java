package com.zhiluo.android.yunpu.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.zhiluo.android.yunpu.ui.fragment.ChainFragment;
import com.zhiluo.android.yunpu.ui.fragment.GaoJiFragment;
import com.zhiluo.android.yunpu.ui.fragment.GoodsValueFragment;
import com.zhiluo.android.yunpu.ui.fragment.HardwareFragment;
import com.zhiluo.android.yunpu.ui.fragment.MemberValueFragment;
import com.zhiluo.android.yunpu.ui.fragment.MessageStoreFragment;

/**
 * 作者：罗咏哲 on 2017/9/4 15:57.
 * 邮箱：137615198@qq.com
 */

public class CustomValuePagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"会员自定义属性", "商品自定义属性"};

    public CustomValuePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 1) {
            return new GoodsValueFragment();
        }
        return new MemberValueFragment();
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
