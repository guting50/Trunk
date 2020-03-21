package com.zhiluo.android.yunpu.member.consume.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.consume.jsonbean.DiscountTypeBean;
import com.zhiluo.android.yunpu.utils.RegexUtil;

import java.util.List;

/**
 * 作者：罗咏哲 on 2017/12/25 14:43.
 * 邮箱：137615198@qq.com
 */

public class RechargeTypeAdapter extends BaseAdapter {
    private List<DiscountTypeBean.DataBean> mBean;
    private Context mContext;


    public RechargeTypeAdapter(List<DiscountTypeBean.DataBean> mBean, Context mContext) {
        this.mBean = mBean;
        this.mContext = mContext;

    }

    @Override
    public int getCount() {
        return mBean == null ? 0 : mBean.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (position < mBean.size() - 1) {
            TextView tvMoney, tvGiveMoney;
            LinearLayout llBackground;
            view = LayoutInflater.from(mContext).inflate(R.layout.item_recharge_type, null);
            tvMoney = (TextView) view.findViewById(R.id.tv_item_recharge_money);
            tvGiveMoney = (TextView) view.findViewById(R.id.tv_item_recharge_give);
            llBackground = (LinearLayout) view.findViewById(R.id.item_recharge_bg);

            tvMoney.setText((int) mBean.get(position).getRP_RechargeMoney() + "元");
            tvGiveMoney.setText("赠送" + String.valueOf((int) mBean.get(position).getRP_GiveMoney()) + "元");
            if (mBean.get(position).isChecked()) {
                llBackground.setBackgroundResource(R.drawable.shape_more_item_check);
            } else {
                llBackground.setBackgroundResource(R.drawable.shape_more_item_bg);
            }
        } else if (position == mBean.size() - 1) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_recharge_type_empty, null);
            LinearLayout ll = (LinearLayout) view.findViewById(R.id.item_recharge_other);
            final TextView tvMoney = (TextView) view.findViewById(R.id.tv_item_recharge);
            TextView tvTotast = (TextView) view.findViewById(R.id.tv_item_tishi);
            if (mBean.get(position).getRP_RechargeMoney() <= 0.0) {
                if (mBean.get(position).isShow()) {
                    tvMoney.setText(mBean.get(position).getStr());
                } else {
                    tvMoney.setText("");
                }
                tvTotast.setVisibility(View.VISIBLE);
            } else {
                tvTotast.setVisibility(View.GONE);
                if (mBean.get(position).isFlag()) {
                    String str = String.valueOf(mBean.get(position).getRP_RechargeMoney());
                    tvMoney.setText(str);
                } else {
                    String str = String.valueOf((int) mBean.get(position).getRP_RechargeMoney());
                    tvMoney.setText(str);
                }
            }
            if (mBean.get(position).isChecked()) {
                ll.setBackgroundResource(R.drawable.shape_more_item_check);
            } else {
                ll.setBackgroundResource(R.drawable.shape_more_item_bg);
            }
        }
        return view;
    }


    static class ViewHolder {
        TextView tvMoney, tvGiveMoney;
        LinearLayout llBackground;

        public ViewHolder(View itemView) {
            tvMoney = (TextView) itemView.findViewById(R.id.tv_item_recharge_money);
            tvGiveMoney = (TextView) itemView.findViewById(R.id.tv_item_recharge_give);
            llBackground = (LinearLayout) itemView.findViewById(R.id.item_recharge_bg);
        }
    }

}
