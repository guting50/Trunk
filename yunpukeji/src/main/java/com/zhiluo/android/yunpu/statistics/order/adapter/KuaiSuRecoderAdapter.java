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
import com.zhiluo.android.yunpu.statistics.order.bean.FastConsumeReportBean;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

/**
 * Created by YSL on 2018-06-08.
 */
public class KuaiSuRecoderAdapter extends RecyclerView.Adapter<KuaiSuRecoderAdapter.Myholder> {
    private Context mContext;
    private FastConsumeReportBean bean;
    //private double money = 0;
    private OnItemClickListener itemClickListener;
    private String name = "",cardnum ="";


    public KuaiSuRecoderAdapter(Context mContext, FastConsumeReportBean bean, OnItemClickListener itemClick) {
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
        String time = bean.getData().getDataList().get(position).getCO_UpdateTime();//时间
        String timea = time.substring(0, 10);
        String timeb = time.substring(11, time.length());
        holder.tvTimeA.setText(timea);
        holder.tvTimeB.setText(timeb);
        double eachmoney = 0;
        double dayMoney = 0;
//        for (int i = 0; i < bean.getData().getDataList().size(); i++) {
            eachmoney = bean.getData().getDataList().get(position).getCO_TotalPrice();
//        }
         holder.tvDiscountMoney.setText(Decima2KeeplUtil.stringToDecimal(eachmoney + ""));
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
            bean.getData().getDataList().get(position).setVisiable(true);
         /*   double money = bean.getData().getDataList().get(position).getMC_AmountDis();
            holder.tvTotalMoney.setText(getMoney(position) + "");*/
        } else {
            if (isVisiliable(bean, position)) {
                bean.getData().getDataList().get(position).setVisiable(true);
                holder.rlTime.setVisibility(View.VISIBLE);
            } else {
                holder.rlTime.setVisibility(View.GONE);
                bean.getData().getDataList().get(position).setVisiable(false);
            }

        }
        holder.tvTotalMoney.setText("");

        //设置图标
        if (position < bean.getData().getDataList().size()) {
            if (bean.getData().getDataList().get(position).getCO_ConsumeWay().contains("),")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_unio_pay);
            } else if (bean.getData().getDataList().get(position).getCO_ConsumeWay().contains("现金")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_cash_pay);
            } else if (bean.getData().getDataList().get(position).getCO_ConsumeWay().contains("余额")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_yue_pay);
            } else if (bean.getData().getDataList().get(position).getCO_ConsumeWay().contains("银联")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_union_pay);
            } else if (bean.getData().getDataList().get(position).getCO_ConsumeWay().contains("微信")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_wei_pay);
            } else if (bean.getData().getDataList().get(position).getCO_ConsumeWay().contains("积分")){
                holder.ivPayWay.setImageResource(R.mipmap.ysl_jifen_pay);
            } else if (bean.getData().getDataList().get(position).getCO_ConsumeWay().contains("支付宝")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_zhiufubao_pay);
            }else if (bean.getData().getDataList().get(position).getCO_ConsumeWay().contains("扫码")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_saoma_pay);
            }else if (bean.getData().getDataList().get(position).getCO_ConsumeWay().contains("其他")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_vip_koukuan_in);
            }
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.itemClick(position);
                }
            });
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.itemClick(position);
            }
        });
    }


    private boolean isVisiliable(FastConsumeReportBean bean, int position) {
        boolean isvisiliable;
        String timelast;
        String timenow;
        timenow = bean.getData().getDataList().get(position).getCO_UpdateTime();
        timelast = bean.getData().getDataList().get(position - 1).getCO_UpdateTime();
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

    private boolean calculateTimeAndMoney(FastConsumeReportBean bean, int position) {
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

    public void setParam(FastConsumeReportBean mReportBean) {
        bean = mReportBean;
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
