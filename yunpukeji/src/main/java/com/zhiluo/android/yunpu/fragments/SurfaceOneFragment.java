package com.zhiluo.android.yunpu.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.consume.activity.GoodsConsumeActivity;
import com.zhiluo.android.yunpu.goods.manager.activity.GoodsManagerActivity;
import com.zhiluo.android.yunpu.member.consume.activity.IntegralExchangeActivity;
import com.zhiluo.android.yunpu.member.consume.activity.MemberChargeManagementActivity;
import com.zhiluo.android.yunpu.member.consume.activity.MemberRechargeActivity;
import com.zhiluo.android.yunpu.member.consume.activity.PlusReduceIntegralActivity;
import com.zhiluo.android.yunpu.member.manager.activity.AddMemberActivity;
import com.zhiluo.android.yunpu.member.manager.activity.MemberListActivity;

import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;



/**
 * Created by ${YSL} on 2018-05-12.
 */

@SuppressLint("ValidFragment")
public class SurfaceOneFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout mvip_manager, mgoods_manager, mjici_xiaofei, mvip_inflate_count;
    private RelativeLayout mjifen_change, mmore, mvip_inflate_money, mgift_manager;
    private Intent mIntent;

    private Activity mActivity;

    /**
     * @param mActivity
     */
    @SuppressLint("ValidFragment")
    public SurfaceOneFragment(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_surfaceone, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initLinsener();

    }


    private void initViews(View view) {
        mvip_manager = view.findViewById(R.id.id_vip_manager);//会员管理
        mgoods_manager = view.findViewById(R.id.id_goods_manager);//商品管理
        mjici_xiaofei = view.findViewById(R.id.id_jici_xiaofei);//计次消费
        mvip_inflate_count = view.findViewById(R.id.id_vip_inflate_count);//会员充次
        mvip_inflate_money = view.findViewById(R.id.id_vip_inflate_money);//会员充值
        mgift_manager = view.findViewById(R.id.id_gift_manager);//礼品管理
        mjifen_change = view.findViewById(R.id.id_jifen_change);//积分兑换
        mmore = view.findViewById(R.id.id_more);//更多
    }

    private void initLinsener() {
        mvip_manager.setOnClickListener(this);
        mgoods_manager.setOnClickListener(this);
        mjici_xiaofei.setOnClickListener(this);
        mvip_inflate_count.setOnClickListener(this);
        mvip_inflate_money.setOnClickListener(this);
        mgift_manager.setOnClickListener(this);
        mjifen_change.setOnClickListener(this);
        mmore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_vip_manager://会员管理
                if (YSLUtils.isOpenActivon("会员管理")) {
                    mIntent = new Intent(mActivity, MemberListActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(getContext(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_goods_manager://商品管理
                if (YSLUtils.isOpenActivon("商品管理")) {
                    mIntent = new Intent(mActivity, GoodsManagerActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(getContext(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_jici_xiaofei://计次消费
                if (YSLUtils.isOpenActivon("计次消费")) {
                    mIntent = new Intent(mActivity, MemberChargeManagementActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(getContext(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_vip_inflate_count://会员充次
                if (YSLUtils.isOpenActivon("会员充次")) {
                    mIntent = new Intent(mActivity, GoodsConsumeActivity.class);
                    mIntent.putExtra("type","HYCC");
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(getContext(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_vip_inflate_money://会员充值
                if (YSLUtils.isOpenActivon("会员充值")) {
                    mIntent = new Intent(mActivity, MemberRechargeActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(getContext(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_gift_manager://积分加减
                if (YSLUtils.isOpenActivon("积分变动")) {
                    mIntent = new Intent(mActivity, PlusReduceIntegralActivity.class); //原来的礼品管理： GiftsManagementListActivity
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(getContext(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_jifen_change://积分兑换
                if (YSLUtils.isOpenActivon("积分兑换")) {
                    mIntent = new Intent(mActivity, IntegralExchangeActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(getContext(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_more://套餐消费
                if (YSLUtils.isOpenActivon("新增会员")) {
                    mIntent = new Intent(mActivity, AddMemberActivity.class);
                    mIntent.putExtra("intype","1");
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(getContext(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

}
