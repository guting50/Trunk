package com.zhiluo.android.yunpu.statistics.rebate.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.rebate.bean.RebateDetailReportBean;

/**
 * 作者：罗咏哲 on 2017/9/5 17:46.
 * 邮箱：137615198@qq.com
 */

public class RebateDetailAdapter extends BaseAdapter {
    Context context;
    RebateDetailReportBean reportBean;

    public RebateDetailAdapter(Context context, RebateDetailReportBean reportBean) {
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
        RebateDetailReportBean.DataBean.DataListBean bean = reportBean.getData().getDataList().get(i);
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.item_rebate_layout, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (bean.getVIP_Name() != null) {
            holder.tvOrder.setText(bean.getVIP_Name());
        } else {
            holder.tvOrder.setText(bean.getVCH_Card());
        }
        holder.tvName.setText(bean.getReamrk());
        holder.tvValue1Name.setText("返利类型：");
        holder.tvValue2Name.setText("获得积分：");
        holder.tvValue1.setText(bean.getIdentifying());
        holder.tvValue2.setText(bean.getNumber());
        return view;
    }

    public void setParams(RebateDetailReportBean mReportBean) {
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
