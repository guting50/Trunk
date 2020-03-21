package com.zhiluo.android.yunpu.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zhiluo.android.yunpu.R;

import com.zhiluo.android.yunpu.goods.manager.activity.FastChargesActivity;
import com.zhiluo.android.yunpu.goods.manager.activity.StockTakingActivity;

import com.zhiluo.android.yunpu.ui.activity.customvlue.CustomValueManagerActivity;
import com.zhiluo.android.yunpu.ui.activity.discount.DiscountManagerActivity;
import com.zhiluo.android.yunpu.ui.activity.good.GoodsWarehousingActivity;
import com.zhiluo.android.yunpu.ui.activity.handduty.HandDutyActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${YSL} on 2018-05-12.
 */

@SuppressLint("ValidFragment")
public class SurfaceTwoFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout mvip_manager, mgoods_manager, mjici_xiaofei, mvip_inflate_count,mfast_chongci;
    private RelativeLayout mjifen_change, mmore, mvip_inflate_money, mgift_manager,mhand_duty;
    private Intent mIntent;

    private Activity mActivity;

    /**
     * @param mActivity
     */
    @SuppressLint("ValidFragment")
    public SurfaceTwoFragment(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_surfacetwo, container, false);
        setRetainInstance(false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initLinsener();

    }


    private void initViews(View view) {
        mfast_chongci = view.findViewById(R.id.id_chongci_change);//快速充次
        mvip_manager = view.findViewById(R.id.id_vip_manager);//会员管理
        mgoods_manager = view.findViewById(R.id.id_goods_manager);//商品管理
        mjici_xiaofei = view.findViewById(R.id.id_jici_xiaofei);//计次消费
        mvip_inflate_count = view.findViewById(R.id.id_vip_inflate_count);//会员充次
        mvip_inflate_money = view.findViewById(R.id.id_vip_inflate_money);//会员充值
//         mgift_manager = view.findViewById(R.id.id_gift_manager);//礼品管理
        mjifen_change = view.findViewById(R.id.id_vip_inflate_count);//积分兑换
        mhand_duty = view.findViewById(R.id.id_hand_duty_manager);//交班
        mmore = view.findViewById(R.id.id_more);//更多
    }

    private void initLinsener() {
        mfast_chongci.setOnClickListener(this);
        mvip_manager.setOnClickListener(this);
        mgoods_manager.setOnClickListener(this);
        mjici_xiaofei.setOnClickListener(this);
        mvip_inflate_count.setOnClickListener(this);
        mvip_inflate_money.setOnClickListener(this);
//        mgift_manager.setOnClickListener(this);
        mjifen_change.setOnClickListener(this);
        mhand_duty.setOnClickListener(this);
        mmore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_chongci_change://快速充次
                if (YSLUtils.isOpenActivon("快速计次")) {
                    mIntent = new Intent(mActivity, FastChargesActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(getContext(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_vip_manager://库存盘点
                if (YSLUtils.isOpenActivon("库存盘点")) {
                    mIntent = new Intent(mActivity, StockTakingActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(getContext(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_goods_manager://商品出入库
                if (YSLUtils.isOpenActivon("商品入库")) {
                    mIntent = new Intent(mActivity, GoodsWarehousingActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(getContext(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            /*case R.id.id_jici_xiaofei://积分加减
                mIntent = new Intent(mActivity, PlusReduceIntegralActivity.class);
                startActivity(mIntent);
                break;*/
            case R.id.id_jici_xiaofei://优惠活动
                if (YSLUtils.isOpenActivon("优惠活动")) {
                    mIntent = new Intent(mActivity, DiscountManagerActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(getContext(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_vip_inflate_count://自定义属性
                if (YSLUtils.isOpenActivon("自定义属性")) {
                    mIntent = new Intent(mActivity, CustomValueManagerActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(getContext(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_hand_duty_manager://交班
//                if (YSLUtils.isOpenActivon("交班")) {
                    mIntent = new Intent(mActivity, HandDutyActivity.class);
                    startActivity(mIntent);
//                } else {
//                    CustomToast.makeText(getContext(), "没有该功能权限", Toast.LENGTH_SHORT).show();
//                }
                break;
            case R.id.id_vip_inflate_money:
                CustomToast.makeText(getContext(), "更多功能正在开发中，敬请期待！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_jifen_change:
                break;
            case R.id.id_more:
                break;
        }
    }

}
