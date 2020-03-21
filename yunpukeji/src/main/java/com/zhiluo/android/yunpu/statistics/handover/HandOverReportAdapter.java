package com.zhiluo.android.yunpu.statistics.handover;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;


/**
 * Created by ZPH on 2018-11-29.
 */

public class HandOverReportAdapter extends BaseAdapter{

    Context context;
    HandOverReportBean reportBean;

    public HandOverReportAdapter(Context context, HandOverReportBean reportBean) {
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
        HandOverReportBean.DataBean.DataListBean bean = reportBean.getData().getDataList().get(i);
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.item_handover_report, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(bean.getSA_ShiftName());
        if (bean.getSA_ShiftDateTime()!=null){
            holder.tvValue1.setText("交班时间："+bean.getSA_ShiftDateTime());
            holder.tvValue1.setVisibility(View.VISIBLE);
        }else {
            holder.tvValue1.setVisibility(View.GONE);
        }

        return view;
    }

    public void setParm(HandOverReportBean mReportBean) {
        reportBean = mReportBean;

    }

    class ViewHolder {
        TextView tvName;
        TextView tvValue1;

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.parent_vip_name);
            tvValue1 = (TextView) view.findViewById(R.id.tv_handover_time);

        }
    }
}
