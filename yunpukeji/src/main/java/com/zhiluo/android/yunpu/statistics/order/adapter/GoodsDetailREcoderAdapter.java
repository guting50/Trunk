package com.zhiluo.android.yunpu.statistics.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.order.bean.GoodsOrderReportBean;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;


/**
 * Created by ${YSL} on 2018-06-02.
 */

public class GoodsDetailREcoderAdapter extends RecyclerView.Adapter<GoodsDetailREcoderAdapter.MyHolder> {
    private Context mContext;
    private GoodsOrderReportBean.DataBean.DataListBean beans;
    private double number;
    private retureClick retureClick;
    private boolean isYJJY;

    public GoodsDetailREcoderAdapter(Context mContext, GoodsOrderReportBean.DataBean.DataListBean bean, boolean isYJJY, retureClick retureClick) {
        this.mContext = mContext;
        this.beans = bean;
        this.isYJJY = isYJJY;
        this.retureClick = retureClick;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_inwaredetail, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(GoodsDetailREcoderAdapter.MyHolder holder, int position) {
        GoodsOrderReportBean.DataBean.DataListBean.ViewGoodsDetailBean bean = beans.getViewGoodsDetail().get(position);
        number = bean.getPM_Number();
//        holder.tvgoodsnum.setText(bean.getPM_Code()+"");
        holder.tvInCount.setText("x" + Decima2KeeplUtil.stringToDecimal(number + ""));
        holder.tvgoodsprice.setText(Decima2KeeplUtil.stringToDecimal(bean.getPM_UnitPrice() + ""));
        if (bean.getState() != null && bean.getState().equals("退货")) {
            holder.tvGoodsName.setText("(退)" + (bean.getPM_Name() == null ? "" : bean.getPM_Name()));
            holder.tvGoodsName.setTextColor(mContext.getResources().getColor(R.color.red));
            holder.tvGoodsReture.setVisibility(View.GONE);
        } else {
            holder.tvGoodsName.setText(bean.getPM_Name() == null ? "" : bean.getPM_Name());
            holder.tvGoodsName.setTextColor(mContext.getResources().getColor(R.color.name));
            holder.tvGoodsReture.setVisibility(View.VISIBLE);
        }

        if (isYJJY) {
            holder.tvGoodsReture.setVisibility(View.GONE);
        }

        //小计
        holder.tvThisSinglePrice.setText(Decima2KeeplUtil.stringToDecimal(bean.getDiscountPrice() + ""));
        holder.ivGoodsImg.setImageResource(R.drawable.ysl_goods);

        if (beans.getCO_Identifying().equals("全部退货")) {
            holder.tvGoodsReture.setText("已退货");
            holder.tvGoodsReture.setTextColor(mContext.getResources().getColor(R.color.color_item_text_gray));
        } else {
            if (bean.getPM_IsService() == 1) {
                holder.tvGoodsReture.setText("服务不能单品退货");
                holder.tvGoodsReture.setTextColor(mContext.getResources().getColor(R.color.color_item_text_gray));
            } else {
                if (bean.getState() != null && bean.getState().equals("已退货")) {
                    holder.tvGoodsReture.setText("已退货");
                    holder.tvGoodsReture.setTextColor(mContext.getResources().getColor(R.color.color_item_text_gray));
                } else {
                    holder.tvGoodsReture.setTextColor(mContext.getResources().getColor(R.color.ysl_text_bule));
                    holder.tvGoodsReture.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            retureClick.reClick(v);
                        }
                    });
                    holder.tvGoodsReture.setTag(position);
                }

            }
        }

        //挂单就不显示退货
        if (beans.getCO_Identifying() != null && (beans.getCO_Identifying().equals("挂单") || beans.getCO_Identifying().equals("挂账"))) {
            holder.tvGoodsReture.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return beans.getViewGoodsDetail() == null ? 0 : beans.getViewGoodsDetail().size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView ivGoodsImg;//商品图标
        private TextView tvThisSinglePrice;
        private TextView tvInCount;
        private TextView tvGoodsName, tvgoodsnum, tvgoodsprice, tvGoodsReture;

        public MyHolder(View itemView) {
            super(itemView);
            tvThisSinglePrice = itemView.findViewById(R.id.tv_this_single_price);
            tvInCount = itemView.findViewById(R.id.tv_in_count);
            ivGoodsImg = itemView.findViewById(R.id.iv_goods_img);
            tvGoodsName = itemView.findViewById(R.id.tv_goods_name);
//            tvgoodsnum = itemView.findViewById(R.id.tv_goods_num);
            tvgoodsprice = itemView.findViewById(R.id.tv_goods_price);
            tvGoodsReture = itemView.findViewById(R.id.tv_goods_reture);

        }
    }

    public interface retureClick {
        void reClick(View view);
    }


}
