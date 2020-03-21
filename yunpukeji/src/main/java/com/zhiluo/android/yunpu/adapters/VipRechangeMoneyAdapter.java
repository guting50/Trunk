package com.zhiluo.android.yunpu.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.account.bean.RechargeMoneyReportBean;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;

/**
 * Created by ${YSL} on 2018-03-30.
 * 会员充值记录页面:
 * class VipRechangeMoney
 * xml   activity_ysl_vip_rechange_recoder
 */

public class VipRechangeMoneyAdapter extends BaseExpandableListAdapter {
    private RechargeMoneyReportBean.DataBean mDatas;//充值记录条数
    private Context context;
    private printClick mprintClick;
    private cancelClick mCancelClick;


    public VipRechangeMoneyAdapter(RechargeMoneyReportBean.DataBean mData, Context context,printClick mprintClick,cancelClick mCancelClick) {
        this.mDatas = mData;
        this.context = context;
        this.mprintClick = mprintClick;
        this.mCancelClick = mCancelClick;
    }
    public VipRechangeMoneyAdapter(RechargeMoneyReportBean.DataBean mData, Context context) {
        this.mDatas = mData;
        this.context = context;
    }

    /**
     * @return 父项数量
     */
    @Override
    public int getGroupCount() {
        return mDatas == null ? 0 : mDatas.getDataList().size();
    }

    /**
     * @param groupPosition 某个父项
     * @return 获取某个父项的子项数量
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return mDatas == null ? 0 : 1;
    }

    /**
     * @param groupPosition 某个父项当前位置
     * @return 获得某个父项
     */
    @Override
    public Object getGroup(int groupPosition) {
        return mDatas.getDataList().get(groupPosition);
    }

    /**
     * @param groupPosition 某个父项
     * @param childPosition 某个子项
     * @return 获得某个父项的某个子项
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mDatas.getDataList().get(groupPosition);
    }

    /**
     * @param groupPosition 某个父项当前位置
     * @return 获得某个父项的id
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * @param groupPosition 某个父项
     * @param childPosition 某个子项
     * @return 获得某个父项的某个子项的id
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * @return 按函数的名字来理解应该是是否具有稳定的id，这个函数目前一直都是返回false，没有去改动过
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_vipchangmoney_parent, null);
        }
        //  view.setTag(R.id.parent, groupPosition);
        //view.setTag(R.layout.item_vipchangmoney_parent, -1);
        TextView mTime = view.findViewById(R.id.tv_time);
        TextView mOrder = view.findViewById(R.id.tv_roder_number);
        TextView mCount = view.findViewById(R.id.tv_rehange_count);
        TextView mVipName = view.findViewById(R.id.parent_vip_name);
        TextView mVipCard = view.findViewById(R.id.parent_vip_card);
        mTime.setText(mDatas.getDataList().get(groupPosition).getMR_UpdateTime());
        mCount.setText(Decima2KeeplUtil.stringToDecimal(mDatas.getDataList().get(groupPosition).getMR_Amount() + ""));
        mOrder.setText(mDatas.getDataList().get(groupPosition).getMR_Order() == null ? "单号：" : "单号：" + mDatas.getDataList().get(groupPosition).getMR_Order());
        mVipName.setText(mDatas.getDataList().get(groupPosition).getVIP_Name());
        mVipCard.setText(mDatas.getDataList().get(groupPosition).getVCH_Card());

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_vipchangemoney_child, null);
        }
        childViewHolder = new ChildViewHolder();
        view.setTag(R.id.parent, groupPosition);
        view.setTag(R.layout.item_vipchangemoney_child, childPosition);
        childViewHolder.mDevice = view.findViewById(R.id.tv_device);
        childViewHolder.mGiveMoney = view.findViewById(R.id.tv_give_money);
        childViewHolder.mGetIntegral = view.findViewById(R.id.tv_get_integral);
        childViewHolder.mMoneyAfterInflate = view.findViewById(R.id.tv_money_after_inflate);
        childViewHolder.mTvPayWway = view.findViewById(R.id.tv_pay_way);
        childViewHolder.mOperator = view.findViewById(R.id.tv_operator);
        childViewHolder.mShopRemakes = view.findViewById(R.id.tv_shop_remakers);
        childViewHolder.mImgPayWay = view.findViewById(R.id.img_pay_way);
        childViewHolder.mVipName = view.findViewById(R.id.tv_vip_name);
        childViewHolder.mVipCard = view.findViewById(R.id.tv_vip_card);
        childViewHolder.mRechangemoney = view.findViewById(R.id.tv_rechange_money);
        childViewHolder.mprint = view.findViewById(R.id.tv_print);
        childViewHolder.mCancel = view.findViewById(R.id.tv_cancel_order);
        Le(mDatas.getDataList().get(groupPosition).getMR_Way() + "--LLLL");
        if (mDatas.getDataList().get(groupPosition).getMR_Device() == 2) {
            childViewHolder.mDevice.setText("安卓手机");
        }else if (mDatas.getDataList().get(groupPosition).getMR_Device() == 4) {
            childViewHolder.mDevice.setText("IOS");
        } else  {
            childViewHolder.mDevice.setText("浏览器");
        }
        if (mDatas.getDataList().get(groupPosition).getMR_Way().contains(",")) {
            childViewHolder.mImgPayWay.setImageResource(R.mipmap.ysl_unio_pay);
            childViewHolder.mTvPayWway.setText(mDatas.getDataList().get(groupPosition).getMR_Way());
        } else if (mDatas.getDataList().get(groupPosition).getMR_Way().contains("微信")) {
            childViewHolder.mImgPayWay.setImageResource(R.mipmap.ysl_wei_pay);
            childViewHolder.mTvPayWway.setText("微信记账");
        } else if (mDatas.getDataList().get(groupPosition).getMR_Way().contains("扫码")) {
            childViewHolder.mImgPayWay.setImageResource(R.mipmap.ysl_saoma_pay);
            childViewHolder.mTvPayWway.setText("扫码支付");
        } else if (mDatas.getDataList().get(groupPosition).getMR_Way().contains("银联")) {
            childViewHolder.mImgPayWay.setImageResource(R.mipmap.ysl_union_pay);
            childViewHolder.mTvPayWway.setText("银联支付");
        } else if (mDatas.getDataList().get(groupPosition).getMR_Way().contains("余额")) {
            childViewHolder.mImgPayWay.setImageResource(R.mipmap.ysl_yue_pay);
            childViewHolder.mTvPayWway.setText("余额支付");
        } else if (mDatas.getDataList().get(groupPosition).getMR_Way().contains("支付宝")) {
            childViewHolder.mImgPayWay.setImageResource(R.mipmap.ysl_zhiufubao_pay);
            childViewHolder.mTvPayWway.setText("支付宝记账");
        }else if (mDatas.getDataList().get(groupPosition).getMR_Way().contains("其他")) {
            childViewHolder.mImgPayWay.setImageResource(R.mipmap.ysl_vip_koukuan_in);
            childViewHolder.mTvPayWway.setText("其他支付");
        } else {
            childViewHolder.mImgPayWay.setImageResource(R.mipmap.ysl_cash_pay);
            childViewHolder.mTvPayWway.setText("现金支付");
        }
        childViewHolder.mShopRemakes.setText(mDatas.getDataList().get(groupPosition).getMR_Remark() == null ? "" : mDatas.getDataList().get(groupPosition).getMR_Remark());
        childViewHolder.mGiveMoney.setText(Decima2KeeplUtil.stringToDecimal(Decima2KeeplUtil.stringToDecimal(mDatas.getDataList().get(groupPosition).getMR_GivenAmount() + "")));//赠送金额
        childViewHolder.mGetIntegral.setText(mDatas.getDataList().get(groupPosition).getMR_Integral() + "");//获得积分
        childViewHolder.mMoneyAfterInflate.setText(Decima2KeeplUtil.stringToDecimal(Decima2KeeplUtil.stringToDecimal(mDatas.getDataList().get(groupPosition).getMA_AvailableBalance() + "")));//充值后金额
        childViewHolder.mVipName.setText(mDatas.getDataList().get(groupPosition).getVIP_Name());//会员姓名
        childViewHolder.mVipCard.setText(mDatas.getDataList().get(groupPosition).getVCH_Card());//会员卡号
        childViewHolder.mRechangemoney.setText(Decima2KeeplUtil.stringToDecimal(mDatas.getDataList().get(groupPosition).getMR_Amount() + ""));//c充值金额
        childViewHolder.mOperator.setText(mDatas.getDataList().get(groupPosition).getMR_Creator() == null ? "" : mDatas.getDataList().get(groupPosition).getMR_Creator());//会员卡号

        childViewHolder.mprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mprintClick.pClick(v);
            }
        });
        childViewHolder.mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCancelClick.cClick(v);
            }
        });
        childViewHolder.mprint.setTag(groupPosition);
        childViewHolder.mCancel.setTag(groupPosition);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setParams(RechargeMoneyReportBean.DataBean mReportBean) {
        mDatas = mReportBean;
    }

    public class ChildViewHolder {
        void ChildViewHolder() {
        }

        TextView mGiveMoney;
        TextView mGetIntegral;
        TextView mMoneyAfterInflate;
        TextView mTvPayWway;
        TextView mDevice;
        TextView mOperator;
        TextView mShopRemakes;
        TextView mVipName;
        TextView mVipCard;
        TextView mRechangemoney;
        ImageView mImgPayWay;
        TextView mprint;
        TextView mCancel;
    }

    public interface printClick{
        void pClick(View view);
    }

    public interface cancelClick{
        void cClick(View view);
    }
}
