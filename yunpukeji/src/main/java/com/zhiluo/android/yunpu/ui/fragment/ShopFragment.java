package com.zhiluo.android.yunpu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.ui.adapter.StorePagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 商城
 * 作者：罗咏哲 on 2017/10/23 18:17.
 * 邮箱：137615198@qq.com
 */

public class ShopFragment extends Fragment {

    @Bind(R.id.tab_store)
    TabLayout mTabLayout;
    @Bind(R.id.vp_store)
    ViewPager vpStore;
    private StorePagerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage_shope, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mAdapter = new StorePagerAdapter(getChildFragmentManager());
        vpStore.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(vpStore);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
