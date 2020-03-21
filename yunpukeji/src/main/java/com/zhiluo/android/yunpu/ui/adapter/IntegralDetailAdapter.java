package com.zhiluo.android.yunpu.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberGrade;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;

/**
 * 作者：罗咏哲 on 2018/1/18 15:15.
 * 邮箱：137615198@qq.com
 */

public class IntegralDetailAdapter extends BaseAdapter {
    private Context mContext;
    private DataBean mBean;

    public IntegralDetailAdapter(Context mContext, DataBean mBean) {
        this.mContext = mContext;
        this.mBean = mBean;
    }

    @Override
    public int getCount() {
        return mBean.getSettings().size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_grade_integral_detail,
                    parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvScale.setTag(position);
        holder.tvName.setText(mBean.getSettings().get(position).getPT_Name());
        double scale = 0;
        if (mBean.getSettings().get(position).getVS_Number() > 0) {
            scale = DoubleMathUtil.div(mBean.getSettings().get(position).getVS_CMoney(),
                    mBean.getSettings().get(position).getVS_Number(), 2);
        }
        holder.tvScale.setText(scale + "");
        return convertView;
    }

    class ViewHolder {
        TextView tvName, tvScale;

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_item_grade_integral_name);
            tvScale = (TextView) view.findViewById(R.id.tv_item_grade_integral_scale);
        }
    }
}
