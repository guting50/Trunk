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

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ${YSL} on 2018-05-18.
 */

public class InRecoderDetailAdapter extends RecyclerView.Adapter<InRecoderDetailAdapter.MyHolder> {
    private Context mContext;
    private InReCoderDetailBean inReCoderDetailBean;
    private String imgurl;

    public InRecoderDetailAdapter(Context mContext, InReCoderDetailBean inReCoderDetailBean) {
        this.mContext = mContext;
        this.inReCoderDetailBean = inReCoderDetailBean;
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
        cont = (inReCoderDetailBean.getData().get(position).getSID_Amount() + "");
        cont1=cont.substring(cont.length()-2,cont.length());
        holder.tvGoodsName.setText(inReCoderDetailBean.getData().get(position).getPM_Name());
        holder.tvInCount.setText("x" + (cont1.equals(".0") ? cont.substring(0, cont.length() - 2) : cont));
        holder.tvThisSinglePrice.setText(inReCoderDetailBean.getData().get(position).getSID_TotalPrice() + "");
        holder.tvGoodsPrice.setText(inReCoderDetailBean.getData().get(position).getPM_UnitPrice() + "");
        holder.tvGoodsReture.setVisibility(View.GONE);
        imgurl = inReCoderDetailBean.getData().get(position).getPM_BigImg();

         if (imgurl!=null &&!imgurl.contains("http")) {
            imgurl = MyApplication.IMAGE_URL + inReCoderDetailBean.getData().get(position).getPM_BigImg();
        }
        Glide.with(mContext).load(imgurl).into(holder.ivGoodsImg);
    }

    @Override
    public int getItemCount() {
        return inReCoderDetailBean == null ? 0 : inReCoderDetailBean.getData().size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView ivGoodsImg;//商品图标
        private TextView tvThisSinglePrice,tvGoodsPrice;
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
            tvGoodsPrice = itemView.findViewById(R.id.tv_goods_price);
        }
    }
}
