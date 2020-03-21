package com.zhiluo.android.yunpu.statistics.order.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.order.bean.PointExchangeDetailBean;
import com.zhiluo.android.yunpu.statistics.order.bean.TimesGoodDetailBean;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

/**
 * 作者：罗咏哲 on 2017/9/5 17:46.
 * 邮箱：137615198@qq.com
 */

public class ExchangeDetailAdapter extends BaseAdapter {
    Context context;
    PointExchangeDetailBean reportBean;

    public ExchangeDetailAdapter(Context context, PointExchangeDetailBean reportBean) {
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
        PointExchangeDetailBean.DataBean bean = reportBean.getData().get(i);
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.item_report_exchange_detail_layout, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tvCode.setText(bean.getGM_Code() == null ? "" : bean.getGM_Code());
        holder.tvName.setText(bean.getGM_Name() == null ? "" : bean.getGM_Name());
        holder.tvType.setText(bean.getGT_Name() == null ? "" : bean.getGT_Name());
        holder.tvNeedPoint.setText(bean.getGM_Integral() + "");
        holder.tvExchangeNum.setText(bean.getGM_Acount()+"");
        holder.tvSpendPoint.setText(bean.getEGD_Score() + "");

        return view;
    }

    class ViewHolder {
        TextView tvCode;//编号
        TextView tvName;
        TextView tvType;
        TextView tvNeedPoint;
        TextView tvExchangeNum;
        TextView tvSpendPoint;

        public ViewHolder(View view) {
            tvCode = (TextView) view.findViewById(R.id.tv_item_report_name);
            tvName = (TextView) view.findViewById(R.id.tv_item_report_code);
            tvType = (TextView) view.findViewById(R.id.tv_item_report_type);
            tvNeedPoint = (TextView) view.findViewById(R.id.tv_item_report_price);
            tvExchangeNum = (TextView) view.findViewById(R.id.tv_item_report_num);
            tvSpendPoint = (TextView) view.findViewById(R.id.tv_item_report_total);
        }
    }
}
