package com.zhiluo.android.yunpu.ui.activity.handduty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.util.List;

/**
 * Created by YSL on 2018-11-26.
 */

public class HandDutyAdapter extends BaseExpandableListAdapter {

    private HandDutyBean mDatas;
    private Context context;


    public HandDutyAdapter(HandDutyBean mData, Context context){
        this.mDatas = mData;
        this.context = context;

    }

    @Override
    public int getGroupCount() {
        return mDatas == null ? 0 : mDatas.getData().getDataList().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return  mDatas == null ? 0 : 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mDatas.getData().getDataList().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mDatas.getData().getDataList().get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_hand_duty_item, null);
        }
        TextView mCount = view.findViewById(R.id.tv_rehange_count);
        TextView mVipName = view.findViewById(R.id.parent_vip_name);


        mCount.setText(Decima2KeeplUtil.stringToDecimal(mDatas.getData().getDataList().get(groupPosition).getTotal() + ""));
        mVipName.setText(mDatas.getData().getDataList().get(groupPosition).getName());

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_hand_over_duty_detail, null);
        }
        childViewHolder = new ChildViewHolder();
        view.setTag(R.id.parent, groupPosition);
        view.setTag(R.layout.item_vipchangemoney_child, childPosition);
        childViewHolder.xj = view.findViewById(R.id.tv_cash);
        childViewHolder.yue = view.findViewById(R.id.tv_yue);
        childViewHolder.union = view.findViewById(R.id.tv_union);
        childViewHolder.wx = view.findViewById(R.id.tv_wx);
        childViewHolder.zfb = view.findViewById(R.id.zfb);
        childViewHolder.jf = view.findViewById(R.id.jf);
        childViewHolder.yhj = view.findViewById(R.id.yhj);
        childViewHolder.sm = view.findViewById(R.id.sm);
        childViewHolder.other = view.findViewById(R.id.other);
        childViewHolder.rlyue = view.findViewById(R.id.rl_yue);
        childViewHolder.rljf = view.findViewById(R.id.rl_jf);
        childViewHolder.rlyhj = view.findViewById(R.id.rl_yhj);
        childViewHolder.moling = view.findViewById(R.id.moling);
        childViewHolder.tuihuo = view.findViewById(R.id.tv_tuihuo);
        childViewHolder.rltuihuo = view.findViewById(R.id.rl_tuihuo);



        if (mDatas.getData().getDataList().get(groupPosition).getName().equals("合计") || mDatas.getData().getDataList().get(groupPosition).getName().equals("退货+撤单")){
            childViewHolder.rltuihuo.setVisibility(View.GONE);

        }else {
            childViewHolder.rltuihuo.setVisibility(View.VISIBLE);

        }
        childViewHolder.xj.setText(Decima2KeeplUtil.stringToDecimal(mDatas.getData().getDataList().get(groupPosition).getMAI_CashChange()+""));
        childViewHolder.yue.setText(Decima2KeeplUtil.stringToDecimal(mDatas.getData().getDataList().get(groupPosition).getMAI_BalanceChange()+""));
        childViewHolder.union.setText(Decima2KeeplUtil.stringToDecimal(mDatas.getData().getDataList().get(groupPosition).getMAI_UnionChange()+""));
        childViewHolder.wx.setText(Decima2KeeplUtil.stringToDecimal(mDatas.getData().getDataList().get(groupPosition).getMAI_WeChatPayChange()+""));
        childViewHolder.zfb.setText(Decima2KeeplUtil.stringToDecimal(mDatas.getData().getDataList().get(groupPosition).getMAI_AlipayPayChange()+""));
        childViewHolder.jf.setText(Decima2KeeplUtil.stringToDecimal(mDatas.getData().getDataList().get(groupPosition).getMAI_IntegralChange()+""));
        childViewHolder.yhj.setText(Decima2KeeplUtil.stringToDecimal(mDatas.getData().getDataList().get(groupPosition).getMAI_CouponPayChange()+""));
        childViewHolder.sm.setText(Decima2KeeplUtil.stringToDecimal(mDatas.getData().getDataList().get(groupPosition).getMAI_BarCodePayChange()+""));
        childViewHolder.other.setText(Decima2KeeplUtil.stringToDecimal(mDatas.getData().getDataList().get(groupPosition).getMAI_OtherChange()+""));
        childViewHolder.moling.setText(Decima2KeeplUtil.stringToDecimal(mDatas.getData().getDataList().get(groupPosition).getMAI_EraseZeroChange()+""));
        childViewHolder.tuihuo.setText(Decima2KeeplUtil.stringToDecimal(mDatas.getData().getDataList().get(groupPosition).getMAI_ReturnGoods()+""));


        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public class ChildViewHolder {
        void ChildViewHolder() {
        }

        TextView xj;
        TextView yue;
        TextView union;
        TextView jf;
        TextView wx;
        TextView zfb;
        TextView yhj;
        TextView sm;
        TextView other;
        TextView moling;
        TextView tuihuo;
        RelativeLayout rlyue,rljf,rlyhj,rltuihuo;
    }


}
