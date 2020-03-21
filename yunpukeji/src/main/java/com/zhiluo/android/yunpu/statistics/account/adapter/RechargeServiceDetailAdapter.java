package com.zhiluo.android.yunpu.statistics.account.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.account.bean.RechargeServiceDetailBean;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

/**
 * 作者：罗咏哲 on 2017/9/5 17:46.
 * 邮箱：137615198@qq.com
 */

public class RechargeServiceDetailAdapter extends BaseAdapter {
    Context context;
    RechargeServiceDetailBean reportBean;

    public RechargeServiceDetailAdapter(Context context, RechargeServiceDetailBean reportBean) {
        this.context = context;
        this.reportBean = reportBean;
    }

    @Override
    public int getCount() {
        return reportBean.getData() == null ? 0 : reportBean.getData().size();
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
        RechargeServiceDetailBean.DataBean bean = reportBean.getData().get(i);
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.item_report_times_detail_layout, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tvCode.setText(bean.getPM_Code() == null ? "" : bean.getPM_Code());
        holder.tvName.setText(bean.getPM_Name() == null ? "" : bean.getPM_Name());
        holder.tvType.setText(bean.getPT_Name() == null ? "" : bean.getPT_Name());
        holder.tvUseNum.setText(bean.getPM_Number()+"");
        holder.tvPrice.setText("¥" + Decima2KeeplUtil.stringToDecimal(bean.getPM_UnitPrice() + ""));
        holder.tvLeftNum.setText("¥" + Decima2KeeplUtil.stringToDecimal(bean.getSumPrice() + ""));
        holder.tvCreateDate.setText("¥" + Decima2KeeplUtil.stringToDecimal(bean.getDiscountPrice() + ""));
        return view;
    }

    class ViewHolder {
        TextView tvCode;//编号
        TextView tvName;
        TextView tvType;
        TextView tvPrice;
        TextView tvUseNum;
        TextView tvLeftNum;
        TextView tvCreateDate;

        public ViewHolder(View view) {
            tvCode = (TextView) view.findViewById(R.id.tv_item_report_code);
            tvName = (TextView) view.findViewById(R.id.tv_item_report_name);
            tvType = (TextView) view.findViewById(R.id.tv_item_report_type);
            tvPrice = (TextView) view.findViewById(R.id.tv_item_report_price);
            tvUseNum = (TextView) view.findViewById(R.id.tv_item_report_num);
            tvLeftNum = (TextView) view.findViewById(R.id.tv_item_report_total);
            tvCreateDate = (TextView) view.findViewById(R.id.tv_item_report_discount_total);
        }
    }
}
