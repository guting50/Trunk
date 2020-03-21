package com.zhiluo.android.yunpu.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.CustomValueListBean;

/**
 * 自定义属性列表适配器
 * Created by dell on 2018/2/25.
 */

public class CustomValueListAdapter extends BaseAdapter {
    private Context mContext;
    private CustomValueListBean mData;

    public CustomValueListAdapter(Context mContext, CustomValueListBean mData) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_zidingyi_shuxing, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(mData.getData().get(position).getCF_FieldName());
        return convertView;
    }

    public void setParam(CustomValueListBean mValueListBean) {
        mData = mValueListBean;
    }

    class ViewHolder {
        TextView tvName;

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_grade_item_name);
        }
    }
}
