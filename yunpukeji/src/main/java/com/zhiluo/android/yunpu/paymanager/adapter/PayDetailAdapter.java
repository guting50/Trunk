package com.zhiluo.android.yunpu.paymanager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.paymanager.bean.PayDetailBean;


/**
 * Created by ZPH on 2019-05-23.
 */

public class PayDetailAdapter extends BaseAdapter{

    private Context context;
    private PayDetailBean reportBean;

    public PayDetailAdapter(Context context, PayDetailBean reportBean) {
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
        PayDetailBean.DataBean.DataListBean bean = reportBean.getData().getDataList().get(i);
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.item_member_sign_report, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvTime.setText(bean.getED_Time());
        holder.tvName.setText(bean.getED_Money()+"");

        return view;
    }

    public void setParm(PayDetailBean mReportBean) {
        reportBean = mReportBean;

    }

    class ViewHolder {
        private TextView tvTime, tvName;
        private RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            tvTime = itemView.findViewById(R.id.tv_item_check_time);
            tvName = itemView.findViewById(R.id.tv_item_check_name);
            relativeLayout = itemView.findViewById(R.id.rl_item_click);

        }
    }
}
