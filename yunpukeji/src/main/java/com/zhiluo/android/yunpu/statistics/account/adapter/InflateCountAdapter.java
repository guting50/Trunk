package com.zhiluo.android.yunpu.statistics.account.adapter;

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
import com.zhiluo.android.yunpu.statistics.account.bean.CalculateMoneyBean;
import com.zhiluo.android.yunpu.statistics.account.bean.RechargeTimesReportBean;

import java.util.ArrayList;
import java.util.List;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;


/**
 * Created by ${YSL} on 2018-05-22.
 */

public class InflateCountAdapter extends RecyclerView.Adapter<InflateCountAdapter.Myholder> {
    private Context mContext;
    private RechargeTimesReportBean bean;
    //private double money = 0;
    private OnItemClickListener itemClickListener;
    private int length = 0;//一天中记录条数
    private double money = 0;//一天的总钱数
    private int flags = 0;//游标


    public InflateCountAdapter(Context mContext, RechargeTimesReportBean bean, OnItemClickListener itemClick) {
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
        String time = bean.getData().getDataList().get(position).getMC_ChargeTime();
        String timea = time.substring(0, 10);
        String timeb = time.substring(11, time.length());
        holder.tvTimeA.setText(timea);
        holder.tvTimeB.setText(timeb);
        holder.tvDiscountMoney.setText(bean.getData().getDataList().get(position).getMC_AmountDis() + "");
        if (bean.getData().getDataList().get(position).getVIP_Name()!=null){
            holder.tvVipName.setText(bean.getData().getDataList().get(position).getVIP_Name() + "");
            holder.tvVipCard.setText(bean.getData().getDataList().get(position).getVCH_Card() + "");
        }else {
            holder.tvVipName.setText(bean.getData().getDataList().get(position).getVCH_Card() + "");
            holder.tvVipCard.setText("");
        }


        // holder.setIsRecyclable(false);
        //设置每一天
        if (position == 0) {
            holder.rlTime.setVisibility(View.VISIBLE);
            bean.getData().getDataList().get(position).setVisiable(true);
            //   double money = bean.getData().getDataList().get(position).getMC_AmountDis();
            //   holder.tvTotalMoney.setText(getMoney(position) + "");
        } else {
            if (isVisiliable(bean, position)) {
                bean.getData().getDataList().get(position).setVisiable(true);
                holder.rlTime.setVisibility(View.VISIBLE);
            } else {
                holder.rlTime.setVisibility(View.GONE);
                bean.getData().getDataList().get(position).setVisiable(false);
            }
            // holder.tvTotalMoney.setText(getMoney(position) + "");
        }
        //设置金额

        Li("-----------长度=-----"+getDayMoneyList().size());
        for (int k = 0; k < getDayMoneyList().size(); k++) {
            if (bean.getData().getDataList().get(position).getMC_ChargeTime().equals(getDayMoneyList().get(k).getTime())) {
                Li("position---" + bean.getData().getDataList().get(position).getMC_ChargeTime());
                Li("k-------" + getDayMoneyList().get(k).getTime());
                holder.tvTotalMoney.setText(getDayMoneyList().get(k).getMoney() + "");
            }


        }
        //设置图标
        if (position < bean.getData().getDataList().size() &&bean.getData().getDataList().get(position).getMC_Way()!=null) {
            if (bean.getData().getDataList().get(position).getMC_Way().contains("),")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_unio_pay);
            } else if (bean.getData().getDataList().get(position).getMC_Way().contains("现金")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_cash_pay);
            } else if (bean.getData().getDataList().get(position).getMC_Way().contains("余额")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_yue_pay);
            } else if (bean.getData().getDataList().get(position).getMC_Way().contains("银联")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_union_pay);
            } else if (bean.getData().getDataList().get(position).getMC_Way().contains("微信")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_wei_pay);
            } else if (bean.getData().getDataList().get(position).getMC_Way().contains("支付宝")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_zhiufubao_pay);
            }else if (bean.getData().getDataList().get(position).getMC_Way().contains("扫码")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_saoma_pay);
            }else if (bean.getData().getDataList().get(position).getMC_Way().contains("其他")) {
                holder.ivPayWay.setImageResource(R.mipmap.ysl_vip_koukuan_in);
            }

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.itemClick(position);
                }
            });
        }
    }

    /**
     * @return , 返回一天的第一条数据的位置的集合；
     */
    private List<CalculateMoneyBean> getDayMoneyList() {
        List<CalculateMoneyBean> list = new ArrayList<>();
        for (int i = 0; i < bean.getData().getDataList().size(); i++) {
            CalculateMoneyBean calculateMoneyBean = new CalculateMoneyBean();
            try {
                money = bean.getData().getDataList().get(i).getMC_AmountDis();
                if (bean.getData().getDataList().get(i).getMC_ChargeTime().equals(bean.getData().getDataList().get(i + 1).getMC_ChargeTime())) {
                    money += bean.getData().getDataList().get(i + 1).getMC_AmountDis();
                } else {
                    calculateMoneyBean.setMoney(money);
                    calculateMoneyBean.setTime(bean.getData().getDataList().get(i).getMC_ChargeTime());
                    money = 0;
                }
            } catch (Exception e) {

            }
        }
        return list;
    }

    private double getMoney(int position) {
        for (int i = 0; i < bean.getData().getDataList().size(); i++) {
            if (position == bean.getData().getDataList().size() - 1) {

            } else {
                if (!bean.getData().getDataList().get(position + 1).isVisiable()) {
                    money += bean.getData().getDataList().get(position).getMC_AmountDis();
                } else {

                    return money;
                }
            }
        }
        return money;
    }

    private boolean isVisiliable(RechargeTimesReportBean bean, int position) {
        boolean isvisiliable;
        String timelast;
        String timenow;
        timenow = bean.getData().getDataList().get(position).getMC_ChargeTime();
        timelast = bean.getData().getDataList().get(position - 1).getMC_ChargeTime();
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

    private boolean calculateTimeAndMoney(RechargeTimesReportBean bean, int position) {
        boolean flags = false;
        if (position >= bean.getData().getDataList().size()) {
            return flags;
        } else {
            String timenow = bean.getData().getDataList().get(position).getMC_ChargeTime();
            String timenext = bean.getData().getDataList().get(position + 1).getMC_ChargeTime();
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

    public void setParams(RechargeTimesReportBean mReportBean) {
        bean = mReportBean ;
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
