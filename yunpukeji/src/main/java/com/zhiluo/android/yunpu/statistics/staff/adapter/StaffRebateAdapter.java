package com.zhiluo.android.yunpu.statistics.staff.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.rebate.bean.RebateListReportBean;
import com.zhiluo.android.yunpu.statistics.staff.bean.StaffRebateReportBean;

/**
 * 作者：罗咏哲 on 2017/9/5 17:46.
 * 邮箱：137615198@qq.com
 */

public class StaffRebateAdapter extends BaseAdapter {
    Context context;
    StaffRebateReportBean reportBean;

    public StaffRebateAdapter(Context context, StaffRebateReportBean reportBean) {
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
        StaffRebateReportBean.DataBean.DataListBean bean = reportBean.getData().getDataList().get(i);
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.item_recharge_money_layout, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvOrder.setText(bean.getCO_GID());
        holder.tvName.setText(bean.getEM_Name());
        holder.tvValue1Name.setText("提成类型：");
        holder.tvValue2Name.setText("提成金额：");
        switch (bean.getSC_Type()){
            case 10:
                holder.tvValue1.setText("售卡提成");
                break;
            case 20:
                holder.tvValue1.setText("充值提成");
                break;
            case 30:
                holder.tvValue1.setText("充次提成");
                break;
            case 40:
                holder.tvValue1.setText("快速消费提成");
                break;
            case 50:
                holder.tvValue1.setText("商品消费提成");
                break;
            case 60:
                holder.tvValue1.setText("计次消费提成");
                break;
            case 70:
                holder.tvValue1.setText("套餐消费提成");
                break;

        }
        holder.tvValue2.setText(bean.getSC_TipMoney()+"");
        return view;
    }

    public void setParm(StaffRebateReportBean mReportBean) {
        reportBean = mReportBean;

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
