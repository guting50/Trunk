package com.zhiluo.android.yunpu.ui.activity.timesrule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.ui.adapter.MemberGradeAdapter;

import java.util.List;

/**
 * Created by ZPH on 2018-11-30.
 */

public class TimesRuleAdapter extends BaseAdapter {

    private Context mContext;
    private TimesRuleManagerBean.DataBean mList;

    public TimesRuleAdapter(Context mContext, TimesRuleManagerBean.DataBean mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.getDataList().size();
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
            view = LayoutInflater.from(mContext).inflate(R.layout.member_grade_item_layout, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(mList.getDataList().get(position).getWR_Name());
        return view;
    }

    public void setParam(TimesRuleManagerBean.DataBean mList) {
        this.mList = mList;
    }

    class ViewHolder {
        TextView tvName;

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_grade_item_name);
        }
    }
}
