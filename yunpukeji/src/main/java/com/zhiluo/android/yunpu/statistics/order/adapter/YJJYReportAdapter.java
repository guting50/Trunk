package com.zhiluo.android.yunpu.statistics.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.order.bean.GoodsOrderReportBean;
import com.zhiluo.android.yunpu.statistics.order.bean.YJJYOrderDetailBean;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

/**
 * Created by ZPH on 2019-07-03.
 */

public class YJJYReportAdapter extends RecyclerView.Adapter<YJJYReportAdapter.Myholder>{

    private Context mContext;
    private YJJYOrderDetailBean bean;
    //private double money = 0;
    private OnItemClickListener itemClickListener;
    private String name = "",cardnum= "";


    public YJJYReportAdapter(Context mContext, YJJYOrderDetailBean bean, OnItemClickListener itemClick) {
        this.mContext = mContext;
        this.bean = bean;
        this.itemClickListener = itemClick;
    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_vip_count_detail, null);
        Myholder myholder = new Myholder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(Myholder holder, final int position) {
        String time = bean.getData().getDataList().get(position).getCO_CreateTime();//时间
        String timea = time.substring(0, 10);
        String timeb = time.substring(11, time.length());
        holder.tvTimeA.setText(timea);
        holder.tvTimeB.setText(timeb);

        holder.tvDiscountMoney.setText(Decima2KeeplUtil.stringToDecimal(bean.getData().getDataList().get(position).getCO_OrderAmount() + ""));
        name = bean.getData().getDataList().get(position).getVIP_Name();
        cardnum = bean.getData().getDataList().get(position).getVIP_Card();
        holder.tvVipName.setText(name==null?cardnum ==null?"散客":cardnum: name);
        if (name!=null&&!name.equals("散客")){
            holder.tvVipCard.setText(cardnum==null ? "" : cardnum + "");
        }else {
            holder.tvVipCard.setText("");
        }
        // holder.setIsRecyclable(false);
        if (position == 0) {
            holder.rlTime.setVisibility(View.VISIBLE);
        } else {
            if (isVisiliable(bean, position)) {
                holder.rlTime.setVisibility(View.VISIBLE);
            } else {
                holder.rlTime.setVisibility(View.GONE);

            }

        }
        holder.tvTotalMoney.setText("");

        //设置图标
        if (position < bean.getData().getDataList().size()&& bean.getData().getDataList().get(position).getCO_PayWay()!=null) {
            if (bean.getData().getDataList().get(position).getCO_PayWay().contains("),")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_unio_pay);
            } else if (bean.getData().getDataList().get(position).getCO_PayWay().contains("现金")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_cash_pay);
            } else if (bean.getData().getDataList().get(position).getCO_PayWay().contains("余额")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_yue_pay);
            } else if (bean.getData().getDataList().get(position).getCO_PayWay().contains("银联")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_union_pay);
            } else if (bean.getData().getDataList().get(position).getCO_PayWay().contains("微信")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_wei_pay);
            } else if (bean.getData().getDataList().get(position).getCO_PayWay().contains("支付宝")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_zhiufubao_pay);
            }else if (bean.getData().getDataList().get(position).getCO_PayWay().contains("积分")){
                holder.ivPayWay.setImageResource(R.mipmap.ysl_jifen_pay);
            }else if (bean.getData().getDataList().get(position).getCO_PayWay().contains("其它")){
                holder.ivPayWay.setImageResource(R.mipmap.ysl_vip_koukuan_in);
            }else if (bean.getData().getDataList().get(position).getCO_PayWay().contains("扫码")){
                holder.ivPayWay.setImageResource(R.mipmap.ysl_saoma_pay);
            }else {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_cash_pay);
            }
//            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    itemClickListener.itemClick(position);
//                }
//            });
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.itemClick(position);
            }
        });
    }


    private boolean isVisiliable(YJJYOrderDetailBean bean, int position) {
        boolean isvisiliable;
        String timelast;
        String timenow;
        timenow = bean.getData().getDataList().get(position).getCO_CreateTime();
        timelast = bean.getData().getDataList().get(position - 1).getCO_CreateTime();
        if (timenow.substring(0, 10).equals(timelast.substring(0, 10))) {
            isvisiliable = false;
        } else {
            isvisiliable = true;
        }
        return isvisiliable;
    }

    /**
     * @param bean     ,
     * @param position ,
     * @return true, 代表同一天，flase,代表不是同一天
     */

    private boolean calculateTimeAndMoney(GoodsOrderReportBean bean, int position) {
        boolean flags = false;
        if (position >= bean.getData().getDataList().size()) {
            return flags;
        } else {
            String timenow = bean.getData().getDataList().get(position).getCO_UpdateTime();
            String timenext = bean.getData().getDataList().get(position - 1).getCO_UpdateTime();
            if (bean.getData().getDataList().get(position + 1) != null) {
                if (timenow.substring(0, 10).equals(timenext.substring(0, 10))) {
                    flags = true;
                } else {
                    flags = false;
                }
            }
        }
        return flags;
    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.getData().getDataList().size();
    }

    public void setParm(YJJYOrderDetailBean mReportBean) {
        bean =mReportBean ;
    }


    public interface OnItemClickListener {
        void itemClick(int position);
    }


    class Myholder extends RecyclerView.ViewHolder {
        private TextView tvTimeA, tvTimeB;
        private TextView tvTotalMoney, tvDiscountMoney;
        private TextView tvVipName, tvVipCard;
        private ImageView ivPayWay;
        private RelativeLayout rlTime;
        private LinearLayout linearLayout;

        public Myholder(View itemView) {
            super(itemView);
            tvTimeA = itemView.findViewById(R.id.tv_time_a);
            tvTimeB = itemView.findViewById(R.id.tv_time_b);
            tvTotalMoney = itemView.findViewById(R.id.tv_total_money);
            tvDiscountMoney = itemView.findViewById(R.id.tv_discount_money);
            tvVipCard = itemView.findViewById(R.id.tv_vip_card);
            tvVipName = itemView.findViewById(R.id.tv_vip_name);
            ivPayWay = itemView.findViewById(R.id.iv_pay_way);
            rlTime = itemView.findViewById(R.id.ly_time);
            linearLayout = itemView.findViewById(R.id.ly_item_click);
        }
    }
}