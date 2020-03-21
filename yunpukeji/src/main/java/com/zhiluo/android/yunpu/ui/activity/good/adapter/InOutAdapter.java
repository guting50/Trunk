package com.zhiluo.android.yunpu.ui.activity.good.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${YSL} on 2018-05-17.
 */

public class InOutAdapter extends FragmentPagerAdapter {


    private List<Fragment> mFragmentList;

    public void setFragments(ArrayList<Fragment> fragments) {
        mFragmentList = fragments;
    }

    public InOutAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = mFragmentList.get(position);

        return fragment;
    }

    @Override
    public int getCount() {

        return mFragmentList.size();
    }
}
