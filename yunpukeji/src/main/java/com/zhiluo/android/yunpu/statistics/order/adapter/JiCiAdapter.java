package com.zhiluo.android.yunpu.statistics.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.statistics.order.bean.JiCiBean;


/**
 * Created by YSL on 2018-06-08.
 */

public class JiCiAdapter extends RecyclerView.Adapter<JiCiAdapter.Myholder> {
    private Context mContext;
    private JiCiBean bean;

    public JiCiAdapter(Context mContext, JiCiBean bean) {
        this.mContext = mContext;
        this.bean = bean;
    }

    @Override
    public JiCiAdapter.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LinearLayout.inflate(mContext, R.layout.item_jici_adapter, null);
        Myholder myholder = new Myholder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(JiCiAdapter.Myholder holder, int position) {
        //设置图片
        if (bean.getData().get(position).getPM_BigImg() != null) {
            if (bean.getData().get(position).getPM_BigImg().contains("http")) {
                Glide.with(mContext).load(bean.getData().get(position).getPM_BigImg()).into(holder.goodsImg);
            } else {
                String url = bean.getData().get(position).getPM_BigImg();
                if (url.contains("../")) {
                    String[] s = url.split("\\.\\./");
                    url = s[2];
                }
                Glide.with(mContext).load(HttpAPI.API().MAIN_DOMAIN + "/" + url).
                        into(holder.goodsImg);
            }
        } else {
            holder.goodsImg.setImageResource(R.drawable.ysl_goods);
        }


//        holder.goodsImg.setImageResource(R.drawable.ysl_goods);
        holder.goodsName.setText(bean.getData().get(position).getSG_Name());
        holder.leftTimes.setText("剩余次数：" + bean.getData().get(position).getMCA_HowMany());
        holder.totalTimes.setText("总次数：" + bean.getData().get(position).getMCA_TotalCharge());
    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.getData().size();
    }

    class Myholder extends RecyclerView.ViewHolder {
        private ImageView goodsImg;
        private TextView goodsName;
        private TextView leftTimes;
        private TextView totalTimes;

        public Myholder(View itemView) {
            super(itemView);
            goodsImg = itemView.findViewById(R.id.iv_goods_img);
            goodsName = itemView.findViewById(R.id.tv_goods_name);
            leftTimes = itemView.findViewById(R.id.tv_left_times);
            totalTimes = itemView.findViewById(R.id.tv_total_times);
        }
    }
}
