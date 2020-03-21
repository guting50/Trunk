package com.zhiluo.android.yunpu.statistics.account.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.account.bean.IntegralDetailReportBean;

/**
 * Created by ${YSL} on 2018-03-30.
 * 积分明细记录:
 * class VipRechangeMoney
 * xml   activity_ysl_vip_rechange_recoder
 */

public class NewIntegralDetailAdapter extends BaseExpandableListAdapter {
    private IntegralDetailReportBean mReportBean;
    private Context context;


    public NewIntegralDetailAdapter(IntegralDetailReportBean reportBean, Context context) {
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
        TextView mVipName= view.findViewById(R.id.parent_vip_name);
        TextView mVipCard= view.findViewById(R.id.parent_vip_card);
        mTime.setText(mReportBean.getData().getDataList().get(groupPosition).getIntegralTime());//积分时间
        mCount.setText(mReportBean.getData().getDataList().get(groupPosition).getNumber() + "");//积分数量
        mOrder.setVisibility(View.GONE);
//        mOrder.setText(mReportBean.getData().getDataList().get(groupPosition).get() == null ? "单号：" : "单号：" + mReportBean.getData().getDataList().get(groupPosition).getMDR_Order());
        mVipName.setText(mReportBean.getData().getDataList().get(groupPosition).getVIP_Name());
        mVipCard.setText(mReportBean.getData().getDataList().get(groupPosition).getVCH_Card());

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_newintergal_child_item, null);
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
        /*******/
        childViewHolder.mShopRemakes.setText(mReportBean.getData().getDataList().get(groupPosition).getIntegralTime() == null ? "" : mReportBean.getData().getDataList().get(groupPosition).getIntegralTime());
       // childViewHolder.mGiveMoney.setText(mReportBean.getData().getDataList().get(groupPosition).getMDR_Time());
        childViewHolder.mGetIntegral.setText(mReportBean.getData().getDataList().get(groupPosition).getReamrk() + "");//积分说明
        childViewHolder.mMoneyAfterInflate.setText(mReportBean.getData().getDataList().get(groupPosition).getIdentifying() + "");//积分途径
        childViewHolder.mVipName.setText(mReportBean.getData().getDataList().get(groupPosition).getVIP_Name());//会员姓名
        childViewHolder.mVipCard.setText(mReportBean.getData().getDataList().get(groupPosition).getVCH_Card());//会员卡号
        childViewHolder.mRechangemoney.setText(mReportBean.getData().getDataList().get(groupPosition).getNumber() + "");//积分数量
        childViewHolder.mOperator.setText(mReportBean.getData().getDataList().get(groupPosition).getCreator() == null ? "" : mReportBean.getData().getDataList().get(groupPosition).getCreator());//操作员
        if ("1".equals(mReportBean.getData().getDataList().get(groupPosition).getType())) {//积分类型
            childViewHolder.mGiveMoney.setText("增加");
        } else if ("0".equals(mReportBean.getData().getDataList().get(groupPosition).getType())) {
            childViewHolder.mGiveMoney.setText("减少");
        } else {
            childViewHolder.mGiveMoney.setText("");
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setParams(IntegralDetailReportBean ReportBean) {

        mReportBean  = ReportBean;
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
    }
}
