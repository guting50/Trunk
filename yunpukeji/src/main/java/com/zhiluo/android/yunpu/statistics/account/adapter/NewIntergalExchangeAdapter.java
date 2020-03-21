package com.zhiluo.android.yunpu.statistics.account.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.account.bean.MoneyDetailReportBean;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

/**
 * Created by ${YSL} on 2018-03-30.
 * 积分兑换:
 * class VipRechangeMoney
 * xml   activity_ysl_vip_rechange_recoder
 */

public class NewIntergalExchangeAdapter extends BaseExpandableListAdapter {
    private MoneyDetailReportBean mReportBean;
    private Context context;


    public NewIntergalExchangeAdapter(MoneyDetailReportBean reportBean, Context context) {
        this.mReportBean = reportBean;
        this.context = context;
    }

    /**
     * @return 父项数量
     */
    @Override
    public int getGroupCount() {
        return mReportBean.getData().getDataList().size();
    }

    /**
     * @param groupPosition 某个父项
     * @return 获取某个父项的子项数量
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    /**
     * @param groupPosition 某个父项当前位置
     * @return 获得某个父项
     */
    @Override
    public Object getGroup(int groupPosition) {
        return mReportBean.getData().getDataList().get(groupPosition);
    }

    /**
     * @param groupPosition 某个父项
     * @param childPosition 某个子项
     * @return 获得某个父项的某个子项
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mReportBean.getData().getDataList().get(groupPosition);
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
        TextView mTime = view.findViewById(R.id.tv_time);
        TextView mOrder = view.findViewById(R.id.tv_roder_number);
        TextView mCount = view.findViewById(R.id.tv_rehange_count);
        TextView mVipName = view.findViewById(R.id.parent_vip_name);
        TextView mVipCard = view.findViewById(R.id.parent_vip_card);
        mTime.setText(mReportBean.getData().getDataList().get(groupPosition).getTime());
        mCount.setText(Decima2KeeplUtil.stringToDecimal(mReportBean.getData().getDataList().get(groupPosition).getMoney() + ""));
        mOrder.setText(mReportBean.getData().getDataList().get(groupPosition).getOrderCode() == null ? "单号：" : "单号：" + mReportBean.getData().getDataList().get(groupPosition).getOrderCode());
        if (mReportBean.getData().getDataList().get(groupPosition).getVIP_Name()==null&&mReportBean.getData().getDataList().get(groupPosition).getVCH_Card()==null){
            mVipName.setText(mReportBean.getData().getDataList().get(groupPosition).getIdentifying() + "");
            mVipCard.setText("");
        } else if (mReportBean.getData().getDataList().get(groupPosition).getVIP_Name()==null&&mReportBean.getData().getDataList().get(groupPosition).getVCH_Card()!=null){
            mVipName.setText(mReportBean.getData().getDataList().get(groupPosition).getVCH_Card() + "");
            mVipCard.setText("");
        } else if (mReportBean.getData().getDataList().get(groupPosition).getVIP_Name().equals("散客")){
            mVipName.setText(mReportBean.getData().getDataList().get(groupPosition).getVIP_Name() + "");
            mVipCard.setText("");
        }else {
            mVipName.setText(mReportBean.getData().getDataList().get(groupPosition).getVIP_Name());//会员姓名
            mVipCard.setText(mReportBean.getData().getDataList().get(groupPosition).getVCH_Card());//会员卡号
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_newintergalexchange_child_item, null);
        }
        childViewHolder = new ChildViewHolder();
        view.setTag(R.id.parent, groupPosition);
        view.setTag(R.layout.item_vipchangemoney_child, childPosition);


        childViewHolder.mGiveMoney = view.findViewById(R.id.tv_give_money);
        childViewHolder.mGetIntegral = view.findViewById(R.id.tv_get_integral);
        childViewHolder.mVipName = view.findViewById(R.id.tv_vip_name);
        childViewHolder.mVipCard = view.findViewById(R.id.tv_vip_card);
        childViewHolder.mRechangemoney = view.findViewById(R.id.tv_rechange_money);


        childViewHolder.mTvpayXJ = view.findViewById(R.id.tv_xianjin);
        childViewHolder.mTvPayYE = view.findViewById(R.id.tv_yue);
        childViewHolder.mTvYL = view.findViewById(R.id.tv_yinlian);
        childViewHolder.mTvJF = view.findViewById(R.id.tv_pay_jifen);
        childViewHolder.mTvWX = view.findViewById(R.id.tv_wx);
        childViewHolder.mTvZFB = view.findViewById(R.id.tv_zfb);
        childViewHolder.mTvSM = view.findViewById(R.id.tv_sm);
        childViewHolder.mTvQT = view.findViewById(R.id.tv_qt);
        childViewHolder.mTvYHQ = view.findViewById(R.id.tv_yhq);
        childViewHolder.mTvML = view.findViewById(R.id.tv_moling);
        childViewHolder.mTvShuoMing = view.findViewById(R.id.tv_jifen);
        childViewHolder.mTvTime = view.findViewById(R.id.tv_pay_time);

        childViewHolder.mRlcash = view.findViewById(R.id.rl_cash);
        childViewHolder.mRLblance = view.findViewById(R.id.rl_balance);
        childViewHolder.mRlunion = view.findViewById(R.id.rl_union);
        childViewHolder.mRlintegral = view.findViewById(R.id.rl_integral);
        childViewHolder.mRlWX = view.findViewById(R.id.rl_wx);
        childViewHolder.mRlAli = view.findViewById(R.id.rl_zfb);
        childViewHolder.mRlSM = view.findViewById(R.id.rl_sm);
        childViewHolder.mRlQT = view.findViewById(R.id.rl_qt);
        childViewHolder.mRlYHQ = view.findViewById(R.id.rl_yhq);
        childViewHolder.mRlML = view.findViewById(R.id.rl_moling);


        childViewHolder.mGetIntegral.setText(mReportBean.getData().getDataList().get(groupPosition).getIdentifying() + "");//金额类型
        childViewHolder.mRechangemoney.setText(mReportBean.getData().getDataList().get(groupPosition).getMoney() + "");//支付合计
        childViewHolder.mGiveMoney.setText(mReportBean.getData().getDataList().get(groupPosition).getBalanceAfterChange());//余额
        if (Double.parseDouble(mReportBean.getData().getDataList().get(groupPosition).getCashChange())>0.00){
            childViewHolder.mTvpayXJ.setText(Decima2KeeplUtil.stringToDecimal(mReportBean.getData().getDataList().get(groupPosition).getCashChange()));//现金支付
            childViewHolder.mRlcash.setVisibility(View.VISIBLE);
        }else {
            childViewHolder.mRlcash.setVisibility(View.GONE);
        }
        if (Double.parseDouble(mReportBean.getData().getDataList().get(groupPosition).getBalanceChange())>0.00){
            childViewHolder.mTvPayYE.setText(Decima2KeeplUtil.stringToDecimal(mReportBean.getData().getDataList().get(groupPosition).getBalanceChange()));//余额支付
            childViewHolder.mRLblance.setVisibility(View.VISIBLE);
        }else {
            childViewHolder.mRLblance.setVisibility(View.GONE);
        }

        if (Double.parseDouble(mReportBean.getData().getDataList().get(groupPosition).getUnionChange())>0.00){
            childViewHolder.mTvYL.setText(Decima2KeeplUtil.stringToDecimal(mReportBean.getData().getDataList().get(groupPosition).getUnionChange()));//银联支付
            childViewHolder.mRlunion.setVisibility(View.VISIBLE);
        }else {
            childViewHolder.mRlunion.setVisibility(View.GONE);
        }


        if (Double.parseDouble(mReportBean.getData().getDataList().get(groupPosition).getWeChatChange())>0.00){
            childViewHolder.mTvWX.setText(Decima2KeeplUtil.stringToDecimal(mReportBean.getData().getDataList().get(groupPosition).getWeChatChange()));//微信支付
            childViewHolder.mRlWX.setVisibility(View.VISIBLE);
        }else {
            childViewHolder.mRlWX.setVisibility(View.GONE);
        }
        if (Double.parseDouble(mReportBean.getData().getDataList().get(groupPosition).getAlipayChange())>0.00){
            childViewHolder.mTvZFB.setText(Decima2KeeplUtil.stringToDecimal(mReportBean.getData().getDataList().get(groupPosition).getAlipayChange()));
            childViewHolder.mRlAli.setVisibility(View.VISIBLE);
        }else {
            childViewHolder.mRlAli.setVisibility(View.GONE);
        }
        if (Double.parseDouble(mReportBean.getData().getDataList().get(groupPosition).getBarCodeChange())>0.00){
            childViewHolder.mTvSM.setText(Decima2KeeplUtil.stringToDecimal(mReportBean.getData().getDataList().get(groupPosition).getBarCodeChange()));
            childViewHolder.mRlSM.setVisibility(View.VISIBLE);
        }else {
            childViewHolder.mRlSM.setVisibility(View.GONE);
        }
        if (Double.parseDouble(mReportBean.getData().getDataList().get(groupPosition).getOtherChange())>0.00){
            childViewHolder.mTvQT.setText(Decima2KeeplUtil.stringToDecimal(mReportBean.getData().getDataList().get(groupPosition).getOtherChange()));
            childViewHolder.mRlQT.setVisibility(View.VISIBLE);
        }else {
            childViewHolder.mRlQT.setVisibility(View.GONE);
        }

        if (Double.parseDouble(mReportBean.getData().getDataList().get(groupPosition).getIntegralChange())>0.00){
            childViewHolder.mTvJF.setText(Decima2KeeplUtil.stringToDecimal(mReportBean.getData().getDataList().get(groupPosition).getIntegralChange()));//积分支付
            childViewHolder.mRlintegral.setVisibility(View.VISIBLE);
        }else {
            childViewHolder.mRlintegral.setVisibility(View.GONE);
        }

        if (Double.parseDouble(mReportBean.getData().getDataList().get(groupPosition).getCouponChange())>0.00){
            childViewHolder.mTvYHQ.setText(Decima2KeeplUtil.stringToDecimal(mReportBean.getData().getDataList().get(groupPosition).getCouponChange()));//积分支付
            childViewHolder.mRlYHQ.setVisibility(View.VISIBLE);
        }else {
            childViewHolder.mRlYHQ.setVisibility(View.GONE);
        }

        if (Double.parseDouble(mReportBean.getData().getDataList().get(groupPosition).getEraseZeroChange())>0.00){
            childViewHolder.mTvML.setText(Decima2KeeplUtil.stringToDecimal(mReportBean.getData().getDataList().get(groupPosition).getEraseZeroChange()));//积分支付
            childViewHolder.mRlML.setVisibility(View.VISIBLE);
        }else {
            childViewHolder.mRlML.setVisibility(View.GONE);
        }


//        childViewHolder.mTvPayYE.setText(Decima2KeeplUtil.stringToDecimal(mReportBean.getData().getDataList().get(groupPosition).getBalanceChange()));//余额支付
//        childViewHolder.mTvYL.setText(Decima2KeeplUtil.stringToDecimal(mReportBean.getData().getDataList().get(groupPosition).getUnionChange()));//银联支付
//        childViewHolder.mTvJF.setText(Decima2KeeplUtil.stringToDecimal(mReportBean.getData().getDataList().get(groupPosition).getIntegralChange()));//积分支付
        childViewHolder.mTvShuoMing.setText(mReportBean.getData().getDataList().get(groupPosition).getInstruction() == null ? "" : mReportBean.getData().getDataList().get(groupPosition).getInstruction());//说明
        childViewHolder.mTvTime.setText(mReportBean.getData().getDataList().get(groupPosition).getTime());//支付时间
        childViewHolder.mVipName.setText(mReportBean.getData().getDataList().get(groupPosition).getVIP_Name());//会员姓名
        childViewHolder.mVipCard.setText(mReportBean.getData().getDataList().get(groupPosition).getVCH_Card());//会员卡号


        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setParams(MoneyDetailReportBean ReportBean) {
        mReportBean = ReportBean;
    }

    public class ChildViewHolder {
        void ChildViewHolder() {
        }

        TextView mGiveMoney;
        TextView mGetIntegral;
        TextView mMoneyAfterInflate;
        TextView mTvpayXJ;
        TextView mTvPayYE;
        TextView mTvYL;
        TextView mTvWX;
        TextView mTvZFB;
        TextView mTvSM;
        TextView mTvQT;
        TextView mTvJF;
        TextView mTvYHQ;
        TextView mTvML;
        TextView mTvShuoMing;
        TextView mTvTime;
        TextView mOperator;
        TextView mShopRemakes;
        TextView mVipName;
        TextView mVipCard;
        TextView mRechangemoney;
        RelativeLayout mRlcash;
        RelativeLayout mRLblance;
        RelativeLayout mRlunion;
        RelativeLayout mRlintegral;
        RelativeLayout mRlWX;
        RelativeLayout mRlAli;
        RelativeLayout mRlSM;
        RelativeLayout mRlQT;
        RelativeLayout mRlYHQ;
        RelativeLayout mRlML;





    }
}
