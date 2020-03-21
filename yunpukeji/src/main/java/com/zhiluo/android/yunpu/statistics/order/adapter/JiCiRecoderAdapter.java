package com.zhiluo.android.yunpu.statistics.order.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.order.bean.TimesConsumeOrderBean;
import com.zhiluo.android.yunpu.statistics.order.bean.TimesGoodDetailBean;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

/**
 * Created by ${YSL} on 2018-06-02.
 */

public class JiCiRecoderAdapter extends RecyclerView.Adapter<JiCiRecoderAdapter.MyHolder> {
    private Context mContext;
    private TimesConsumeOrderBean.DataBean.DataListBean bean;

    public JiCiRecoderAdapter(Context mContext, TimesConsumeOrderBean.DataBean.DataListBean bean) {
        this.mContext = mContext;
        this.bean = bean;
    }

    @Override
    public JiCiRecoderAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_inwaredetail_jici, null);
        JiCiRecoderAdapter.MyHolder myHolder = new JiCiRecoderAdapter.MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(JiCiRecoderAdapter.MyHolder holder, int position) {

        holder.tvGoodsName.setText(bean.getWouldOrderDetail().get(position).getSG_Name() == null ? "" : bean.getWouldOrderDetail().get(position).getSG_Name());
        holder.tvInCount.setText("" + bean.getWouldOrderDetail().get(position).getWOD_UseNumber());
        holder.tvThisSinglePrice.setText(Decima2KeeplUtil.stringToDecimal((bean.getWouldOrderDetail().get(position).getWOD_ResidueDegree() + "")));
        holder.ivGoodsImg.setImageResource(R.drawable.ysl_goods);
    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.getWouldOrderDetail().size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView ivGoodsImg;//商品图标
        private TextView tvThisSinglePrice;
        private TextView tvInCount;
        private TextView tvGoodsName;

        public MyHolder(View itemView) {
            super(itemView);
            tvThisSinglePrice = itemView.findViewById(R.id.tv_this_single_price);
            tvInCount = itemView.findViewById(R.id.tv_in_count);
            ivGoodsImg = itemView.findViewById(R.id.iv_goods_img);
            tvGoodsName = itemView.findViewById(R.id.tv_goods_name);
        }
    }
}
