package com.zhiluo.android.yunpu.consume.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.consume.jsonbean.DiscountTypeBean;

import java.util.List;

/**
 * 作者：罗咏哲 on 2017/6/24 16:18.
 * 邮箱：137615198@qq.com
 */

public class DiscountTypeAdapter extends BaseAdapter {
    private List<DiscountTypeBean.DataBean> mDataList;
    private Context mContext;

    public DiscountTypeAdapter(List<DiscountTypeBean.DataBean> mDataList, Context mContext) {
        this.mDataList = mDataList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0:mDataList.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder = null;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_discount_type_layout,null);
            holder = new Holder();
            holder.tvName = (TextView) view.findViewById(R.id.tv_item_discount_type);
            view.setTag(holder);
        }else {
            holder = (Holder) view.getTag();
        }
        Log.i("lyz---",mDataList.get(i).getRP_Name());
        holder.tvName.setText(mDataList.get(i).getRP_Name());
        return view;
    }
    class Holder{
        TextView tvName;
    }
}
