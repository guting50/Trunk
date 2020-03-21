

package com.zhiluo.android.yunpu.statistics.order.adapter;

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
import com.zhiluo.android.yunpu.statistics.order.bean.TimesGoodDetailBean;
import com.zhiluo.android.yunpu.statistics.rebate.bean.IntegralNewBean;
import com.zhiluo.android.yunpu.ui.activity.good.adapter.InRecoderDetailAdapter;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;


/**
 * Created by ${YSL} on 2018-06-02.
 */

public class IntegralRecoderInnerAdapter extends RecyclerView.Adapter<IntegralRecoderInnerAdapter.MyHolder> {
    private Context mContext;
    private IntegralNewBean.DataBean.DataListBean bean;

    public IntegralRecoderInnerAdapter(Context mContext, IntegralNewBean.DataBean.DataListBean bean) {
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
    public void onBindViewHolder(IntegralRecoderInnerAdapter.MyHolder holder, int position) {

        holder.tvGoodsName.setText(bean.getGiftsDetail().get(position).getGM_Name() == null ? "" : bean.getGiftsDetail().get(position).getGM_Name());
        holder.tvInCount.setText("x" + Decima2KeeplUtil.stringToDecimal(bean.getGiftsDetail().get(position).getGM_Acount()+""));
        holder.tvThisSinglePrice.setText( Decima2KeeplUtil.stringToDecimal(bean.getGiftsDetail().get(position).getEGD_Score() + ""));
        holder.tvgoodsreture.setVisibility(View.GONE);
        holder.tvgoodsprice.setText(Decima2KeeplUtil.stringToDecimal(bean.getGiftsDetail().get(position).getGM_Integral() + ""));

        holder.ivGoodsImg.setImageResource(R.drawable.ysl_goods);

    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.getGiftsDetail().size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView ivGoodsImg;//商品图标
        private TextView tvThisSinglePrice;
        private TextView tvInCount;
        private TextView tvGoodsName,tvgoodsprice,tvgoodsreture;

        public MyHolder(View itemView) {
            super(itemView);
            tvThisSinglePrice = itemView.findViewById(R.id.tv_this_single_price);
            tvInCount = itemView.findViewById(R.id.tv_in_count);
            ivGoodsImg = itemView.findViewById(R.id.iv_goods_img);
            tvGoodsName = itemView.findViewById(R.id.tv_goods_name);
            tvgoodsreture = itemView.findViewById(R.id.tv_goods_reture);
            tvgoodsprice = itemView.findViewById(R.id.tv_goods_price);

        }
    }
}
