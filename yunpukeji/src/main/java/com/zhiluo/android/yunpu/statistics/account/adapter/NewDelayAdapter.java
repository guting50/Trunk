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
import com.zhiluo.android.yunpu.statistics.account.bean.DelayReportBean;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

/**
 * Created by ${YSL} on 2018-03-30.
 * 会员延期记录页面:
 * class VipRechangeMoney
 * xml   activity_ysl_vip_rechange_recoder
 */

public class NewDelayAdapter extends BaseExpandableListAdapter {
    private DelayReportBean reportBean;//充值记录条数
    private Context context;


    public NewDelayAdapter(DelayReportBean reportBean, Context context) {
        this.reportBean = reportBean;
        this.context = context;
    }

    /**
     * @return 父项数量
     */
    @Override
    public int getGroupCount() {
        return reportBean.getData().getDataList().size();
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
        return reportBean.getData().getDataList().get(groupPosition);
    }

    /**
     * @param groupPosition 某个父项
     * @param childPosition 某个子项
     * @return 获得某个父项的某个子项
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return reportBean.getData().getDataList().get(groupPosition);
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
        mTime.setText(reportBean.getData().getDataList().get(groupPosition).getMDR_UpdateTime());
        mCount.setText(Decima2KeeplUtil.stringToDecimal(reportBean.getData().getDataList().get(groupPosition).getMDR_Money() + ""));
        mOrder.setText(reportBean.getData().getDataList().get(groupPosition).getMDR_Order() == null ? "单号：" : "单号：" + reportBean.getData().getDataList().get(groupPosition).getMDR_Order());
        mVipName.setText(reportBean.getData().getDataList().get(groupPosition).getVIP_Name());
        mVipCard.setText(reportBean.getData().getDataList().get(groupPosition).getVIP_Card());

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_newdelay_child_item, null);
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
        if(reportBean.getData().getDataList().get(groupPosition).getMDR_PayType() !=null){
            childViewHolder.mImgPayWay.setVisibility(View.VISIBLE);
            if (reportBean.getData().getDataList().get(groupPosition).getMDR_PayType().contains(",")) {
                childViewHolder.mImgPayWay.setImageResource(R.mipmap.ysl_unio_pay);
                childViewHolder.mTvPayWway.setText(reportBean.getData().getDataList().get(groupPosition).getMDR_PayType());
            } else if (reportBean.getData().getDataList().get(groupPosition).getMDR_PayType().contains("微信")) {
                childViewHolder.mImgPayWay.setImageResource(R.mipmap.ysl_wei_pay);
                childViewHolder.mTvPayWway.setText(reportBean.getData().getDataList().get(groupPosition).getMDR_PayType());
            } else if (reportBean.getData().getDataList().get(groupPosition).getMDR_PayType().contains("扫码")) {
                childViewHolder.mImgPayWay.setImageResource(R.mipmap.ysl_saoma_pay);
                childViewHolder.mTvPayWway.setText(reportBean.getData().getDataList().get(groupPosition).getMDR_PayType());
            } else if (reportBean.getData().getDataList().get(groupPosition).getMDR_PayType().contains("银联")) {
                childViewHolder.mImgPayWay.setImageResource(R.mipmap.ysl_union_pay);
                childViewHolder.mTvPayWway.setText(reportBean.getData().getDataList().get(groupPosition).getMDR_PayType());
            } else if (reportBean.getData().getDataList().get(groupPosition).getMDR_PayType().contains("余额")) {
                childViewHolder.mImgPayWay.setImageResource(R.mipmap.ysl_yue_pay);
                childViewHolder.mTvPayWway.setText(reportBean.getData().getDataList().get(groupPosition).getMDR_PayType());
            } else if (reportBean.getData().getDataList().get(groupPosition).getMDR_PayType().contains("支付宝")) {
                childViewHolder.mImgPayWay.setImageResource(R.mipmap.ysl_zhiufubao_pay);
                childViewHolder.mTvPayWway.setText(reportBean.getData().getDataList().get(groupPosition).getMDR_PayType());
            }else if (reportBean.getData().getDataList().get(groupPosition).getMDR_PayType().contains("其他")) {
                childViewHolder.mImgPayWay.setImageResource(R.mipmap.ysl_vip_koukuan_in);
                childViewHolder.mTvPayWway.setText(reportBean.getData().getDataList().get(groupPosition).getMDR_PayType());
            } else {
                childViewHolder.mImgPayWay.setImageResource(R.mipmap.ysl_cash_pay);
                childViewHolder.mTvPayWway.setText(reportBean.getData().getDataList().get(groupPosition).getMDR_PayType());
            }
        }else {
            childViewHolder.mImgPayWay.setVisibility(View.GONE);
            childViewHolder.mTvPayWway.setText("");
        }
        childViewHolder.mShopRemakes.setText(reportBean.getData().getDataList().get(groupPosition).getMDR_DelayRemark() == null ? "" : reportBean.getData().getDataList().get(groupPosition).getMDR_DelayRemark());
        childViewHolder.mGiveMoney.setText(reportBean.getData().getDataList().get(groupPosition).getMDR_Time());//延期时长
        childViewHolder.mGetIntegral.setText(reportBean.getData().getDataList().get(groupPosition).getMDR_Order() + "");//延期单号
        childViewHolder.mMoneyAfterInflate.setText(reportBean.getData().getDataList().get(childPosition).getMDR_UpdateTime() + "");//延期时间
        childViewHolder.mVipName.setText(reportBean.getData().getDataList().get(groupPosition).getVIP_Name());//会员姓名
        childViewHolder.mVipCard.setText(reportBean.getData().getDataList().get(groupPosition).getVIP_Card());//会员卡号
        childViewHolder.mRechangemoney.setText(Decima2KeeplUtil.stringToDecimal(reportBean.getData().getDataList().get(groupPosition).getMDR_Money() + ""));//延期费用
        childViewHolder.mOperator.setText(reportBean.getData().getDataList().get(groupPosition).getMDR_Creator() == null ? "" : reportBean.getData().getDataList().get(childPosition).getMDR_Creator());//操作员

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setParams(DelayReportBean ReportBean) {
        reportBean = ReportBean;
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
