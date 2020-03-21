package com.zhiluo.android.yunpu.ui.activity.good.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.ui.activity.good.bean.InReCoderDetailBean;
import com.zhiluo.android.yunpu.ui.activity.good.bean.OutReCoderDetailBean;

/**
 * Created by ${YSL} on 2018-05-18.
 */

public class OutRecoderDetailAdapter extends RecyclerView.Adapter<OutRecoderDetailAdapter.MyHolder> {
    private Context mContext;
    private OutReCoderDetailBean outReCoderDetailBean;
    private String imgurl;

    public OutRecoderDetailAdapter(Context mContext, OutReCoderDetailBean outReCoderDetailBean) {
        this.mContext = mContext;
        this.outReCoderDetailBean = outReCoderDetailBean;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_inwaredetail, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        String cont, cont1;
        cont = (outReCoderDetailBean.getData().getDataList().get(position).getSOD_Amount() + "");
        cont1=cont.substring(cont.length()-2,cont.length());
        holder.tvGoodsName.setText(outReCoderDetailBean.getData().getDataList().get(position).getPM_Name());
        holder.tvGoodsprice.setText(outReCoderDetailBean.getData().getDataList().get(position).getPM_PurchasePrice() +"");

        holder.tvInCount.setText("x" + (cont1.equals(".0") ? cont.substring(0, cont.length() - 2) : cont));
        holder.tvThisSinglePrice.setText(outReCoderDetailBean.getData().getDataList().get(position).getSOD_DiscountPrice()+"");//小计
        imgurl = outReCoderDetailBean.getData().getDataList().get(position).getPM_BigImg();
        if (imgurl!=null){
            if (!imgurl.contains("http")) {
                imgurl = MyApplication.IMAGE_URL + outReCoderDetailBean.getData().getDataList().get(position).getPM_BigImg();
            }
            Glide.with(mContext).load(imgurl).into(holder.ivGoodsImg);
        }
        holder.tvGoodsReture.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return outReCoderDetailBean == null ? 0 : outReCoderDetailBean.getData().getDataList().size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView ivGoodsImg;//商品图标
        private TextView tvThisSinglePrice;
        private TextView tvGoodsprice;
        private TextView tvInCount;
        private TextView tvGoodsName;
        private TextView tvGoodsReture;

        public MyHolder(View itemView) {
            super(itemView);
            tvThisSinglePrice = itemView.findViewById(R.id.tv_this_single_price);
            tvInCount = itemView.findViewById(R.id.tv_in_count);
            ivGoodsImg = itemView.findViewById(R.id.iv_goods_img);
            tvGoodsName = itemView.findViewById(R.id.tv_goods_name);
            tvGoodsReture = itemView.findViewById(R.id.tv_goods_reture);
            tvGoodsprice = itemView.findViewById(R.id.tv_goods_price);

        }
    }
}
