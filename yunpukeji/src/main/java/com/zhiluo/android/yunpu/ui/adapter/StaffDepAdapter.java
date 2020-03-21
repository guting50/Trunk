package com.zhiluo.android.yunpu.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DepListBean;

/**
 * 员工部门Adapter
 * Created by dell on 2018/2/25.
 */

public class StaffDepAdapter extends BaseAdapter {
    private Context mContext;
    private DepListBean mData;

    public StaffDepAdapter(Context mContext, DepListBean mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.getData().size();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_choice_type_main, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(mData.getData().get(position).getDM_Name());
        if (mData.getData().get(position).isChecked()) {
            holder.tvName.setBackgroundColor(mContext.getResources().getColor(R.color.white));
           // holder.tvName.setTextColor(mContext.getResources().getColor(R.color.yunpu));
            holder.tvflags.setBackgroundColor(mContext.getResources().getColor(R.color.yunpu));
        } else {
            holder.tvName.setBackgroundColor(mContext.getResources().getColor(R.color.background));
            holder.tvName.setTextColor(mContext.getResources().getColor(R.color.color_main_text_black));
            holder.tvflags.setBackgroundColor(mContext.getResources().getColor(R.color.background));
        }
        return convertView;
    }

    class ViewHolder {
        TextView tvName,tvflags;

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_item_type_main);
            tvflags = (TextView) view.findViewById(R.id.left_flags);
        }
    }
}
