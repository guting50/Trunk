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

import java.util.List;

/**
 * 作者：罗咏哲 on 2018/1/17 10:27.
 * 邮箱：137615198@qq.com
 */

public class MemberGradeAdapter extends BaseAdapter {
    private Context mContext;
    private List<DataBean> mList;

    public MemberGradeAdapter(Context mContext, List<DataBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
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
        holder.tvName.setText(mList.get(position).getVG_Name());
        return view;
    }

    class ViewHolder {
        TextView tvName;

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_grade_item_name);
        }
    }
}
