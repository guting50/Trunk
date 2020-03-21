package com.zhiluo.android.yunpu.statistics.account.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.account.bean.RechargeMoneyReportBean;

/**
 * 作者：罗咏哲 on 2017/9/5 17:46.
 * 邮箱：137615198@qq.com
 */

public class RechargeMoneyAdapter extends BaseAdapter {
    Context context;
    RechargeMoneyReportBean reportBean;

    public RechargeMoneyAdapter(Context context, RechargeMoneyReportBean reportBean) {
        this.context = context;
        this.reportBean = reportBean;
    }

    @Override
    public int getCount() {
        return reportBean.getData().getDataList() == null ? 0 : reportBean.getData().getDataList().size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        RechargeMoneyReportBean.DataBean.DataListBean bean = reportBean.getData().getDataList().get(i);
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.item_recharge_money_layout, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvOrder.setText(bean.getMR_Order());
        if (bean.getVIP_Name()!=null){
            holder.tvName.setText(bean.getVIP_Name());
        }else {
            holder.tvName.setText(bean.getVCH_Card());
        }
        holder.tvValue1Name.setText("充值金额：");
        holder.tvValue2Name.setText("赠送金额");
        holder.tvValue1.setText(bean.getMR_Amount()+"");
        holder.tvValue2.setText(bean.getMR_GivenAmount()+"");
        return view;
    }

    class ViewHolder {
        TextView tvOrder;
        TextView tvName;
        TextView tvValue1Name;
        TextView tvValue2Name;
        TextView tvValue1;
        TextView tvValue2;

        public ViewHolder(View view) {
            tvOrder = (TextView) view.findViewById(R.id.tv_item_order);
            tvName = (TextView) view.findViewById(R.id.tv_item_name);
            tvValue1Name = (TextView) view.findViewById(R.id.tv_item_name1);
            tvValue2Name = (TextView) view.findViewById(R.id.tv_item_name2);
            tvValue1 = (TextView) view.findViewById(R.id.tv_item_value1);
            tvValue2 = (TextView) view.findViewById(R.id.tv_item_value2);
        }
    }
}