package com.zhiluo.android.yunpu.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.TCGoodBean;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：罗咏哲 on 2018/1/26 17:55.
 * 邮箱：137615198@qq.com
 */

public class TCGoodsAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<TCGoodBean> mList;

    public TCGoodsAdapter(Context mContext, ArrayList<TCGoodBean> mList) {
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tc_good, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(mList.get(position).getPM_Name());
        if (mList.get(position).getPM_Number() != null) {
            holder.tvNum.setText("" + (int) Double.parseDouble(mList.get(position).getPM_Number()));
        } else {
            holder.tvNum.setText("0");
        }
        return convertView;
    }

    class ViewHolder {
        TextView tvName, tvNum;
        ImageView ivDelete;

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_item_init_name);
            tvNum = (TextView) view.findViewById(R.id.tv_item_init_num);
            ivDelete = (ImageView) view.findViewById(R.id.iv_item_init_delete);
        }
    }

}
