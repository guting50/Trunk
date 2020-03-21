package com.zhiluo.android.yunpu.analysis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.analysis.bean.GoodsAnalysisBean;
import com.zhiluo.android.yunpu.analysis.bean.MemberAnalysisBean;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.util.List;


/**
 * 作者：罗咏哲 on 2017/9/22 15:52.
 * 邮箱：137615198@qq.com
 */

public class AnalysisGoodsAdapter extends BaseAdapter {
    private Context context;
    private List<GoodsAnalysisBean.DataBean.PSListBean> list;

    public AnalysisGoodsAdapter(Context context, List<GoodsAnalysisBean.DataBean.PSListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_analysis, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvOrder.setText(String.valueOf(i + 1));
        holder.tvName.setText(list.get(i).getPM_Name() == null ? "" : list.get(i).getPM_Name());
        holder.tvNum.setText(list.get(i).getNumber());
        holder.tvMoney.setText(Decima2KeeplUtil.stringToDecimal(list.get(i).getMoney()+""));

        return view;
    }

    class ViewHolder {
        TextView tvOrder, tvName, tvNum, tvMoney;

        ViewHolder(View view) {
            tvOrder = (TextView) view.findViewById(R.id.tv_item_order);
            tvName = (TextView) view.findViewById(R.id.tv_item_member_name);
            tvNum = (TextView) view.findViewById(R.id.tv_item_consume_num);
            tvMoney = (TextView) view.findViewById(R.id.tv_item_consume_money);
        }
    }
}
