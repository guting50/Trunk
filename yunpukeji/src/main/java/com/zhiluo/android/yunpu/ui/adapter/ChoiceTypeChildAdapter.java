package com.zhiluo.android.yunpu.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.mvp.model.GoodsType;

import java.util.List;
import java.util.Map;

/**
 * 选择商品类型主分类Adapter
 * 作者：罗咏哲 on 2017/11/29 15:45.
 * 邮箱：137615198@qq.com
 */

public class ChoiceTypeChildAdapter extends BaseAdapter {
    private Context mContext;
    private List<GoodsType.DataBean> mChild;

    public ChoiceTypeChildAdapter(Context mContext, List<GoodsType.DataBean> mChild) {
        this.mContext = mContext;
        this.mChild = mChild;
    }

    @Override
    public int getCount() {
        return mChild.size();
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_choice_type_child, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(mChild.get(position).getPT_Name());
        if (mChild.get(position).isChecked()) {
            holder.tvName.setBackgroundColor(mContext.getResources().getColor(R.color.white));

        } else {
            holder.tvName.setBackgroundColor(mContext.getResources().getColor(R.color.background));

        }
        return view;
    }

    class ViewHolder {
        TextView tvName;

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_item_type_child);

        }
    }

}
