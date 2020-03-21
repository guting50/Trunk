package com.zhiluo.android.yunpu.statistics.account.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.account.bean.RechargeServiceDetailBean;
import com.zhiluo.android.yunpu.ui.activity.good.adapter.InRecoderDetailAdapter;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;

/**
 * Created by ${YSL} on 2018-05-22.
 */

public class InflateCountsDetailAdapter extends RecyclerView.Adapter<InflateCountsDetailAdapter.MyHolder>{
    private Context mContext;
    private RechargeServiceDetailBean bean;

    public InflateCountsDetailAdapter(Context mContext, RechargeServiceDetailBean bean) {
        this.mContext = mContext;
        this.bean = bean;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_inwaredetail, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tvGoodsName.setText(bean.getData().get(position).getPM_Name());
        holder.tvInCount.setText("x"+Decima2KeeplUtil.stringToDecimal(bean.getData().get(position).getPM_Number()+""));
        holder.tvThisSinglePrice.setText(Decima2KeeplUtil.stringToDecimal(bean.getData().get(position).getDiscountPrice()/bean.getData().get(position).getPM_Number()+"")+"");
        holder.ivGoodsImg.setImageResource(R.drawable.ysl_goods);
        holder.tvGoodsreture.setVisibility(View.GONE);
        holder.tvGoodsPrice.setText(Decima2KeeplUtil.stringToDecimal(bean.getData().get(position).getPM_UnitPrice()+""));

    }

    @Override
    public int getItemCount() {
        return bean==null?0:bean.getData().size();
    }
    class MyHolder extends RecyclerView.ViewHolder{
        private ImageView ivGoodsImg;//商品图标
        private TextView tvThisSinglePrice;
        private TextView tvInCount;
        private TextView tvGoodsName,tvGoodsreture,tvGoodsPrice;

        public MyHolder(View itemView) {
            super(itemView);
            tvThisSinglePrice = itemView.findViewById(R.id.tv_this_single_price);
            tvInCount = itemView.findViewById(R.id.tv_in_count);
            ivGoodsImg = itemView.findViewById(R.id.iv_goods_img);
            tvGoodsName = itemView.findViewById(R.id.tv_goods_name);
            tvGoodsreture = itemView.findViewById(R.id.tv_goods_reture);
            tvGoodsPrice = itemView.findViewById(R.id.tv_goods_price);


        }
    }
}
