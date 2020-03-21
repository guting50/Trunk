package com.zhiluo.android.yunpu.consume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DeptBean;
import com.zhiluo.android.yunpu.entity.RuleBean;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;

import java.util.List;

/**
 * 作者：罗咏哲 on 2018/1/23 11:47.
 * 邮箱：137615198@qq.com
 */

public class RuleAdapter extends BaseAdapter {
    private Context mContext;
    private List<ReportMessageBean.DataBean.DepartmentListBean> mRuleBean;

    public RuleAdapter(Context mContext, List<ReportMessageBean.DataBean.DepartmentListBean> mRuleBean) {
        this.mContext = mContext;
        this.mRuleBean = mRuleBean;
    }

    @Override
    public int getCount() {
        return mRuleBean == null ? 0 : mRuleBean.size();
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.rule_item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(mRuleBean.get(position).getDM_Name());
        holder.tvName.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        if (mRuleBean.get(position).isCheck()) {
        //    holder.tvName.setTextColor(mContext.getResources().getColor(R.color.yunpu));
            holder.tvFlags.setBackgroundColor(mContext.getResources().getColor(R.color.yunpu));
        } else {
          //  holder.tvName.setTextColor(mContext.getResources().getColor(R.color.color_main_text_black));
         //   holder.tvName.setBackgroundColor(mContext.getResources().getColor(R.color.background));
            holder.tvFlags.setBackgroundColor(mContext.getResources().getColor(R.color.background));
        }
        return convertView;
    }

    class ViewHolder {
        TextView tvName,tvFlags;

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_item_rule_name);
            tvFlags = (TextView) view.findViewById(R.id.tv_flags);
        }
    }

}
