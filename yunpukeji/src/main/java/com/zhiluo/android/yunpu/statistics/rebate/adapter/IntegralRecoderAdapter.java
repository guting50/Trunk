
package com.zhiluo.android.yunpu.statistics.rebate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.statistics.order.bean.PointExchangeReportBean;
import com.zhiluo.android.yunpu.statistics.order.bean.TimesConsumeOrderBean;
import com.zhiluo.android.yunpu.statistics.rebate.bean.IntegralNewBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

/**
 * Created by ${YSL} on 2018-06-02.
 */

public class IntegralRecoderAdapter extends RecyclerView.Adapter<IntegralRecoderAdapter.Myholder> {
    private Context mContext;
    private IntegralNewBean bean;
    //private double money = 0;
    private IntegralRecoderAdapter.OnItemClickListener itemClickListener;
    private String name = "",cardnum = "";


    public IntegralRecoderAdapter(Context mContext,IntegralNewBean bean, IntegralRecoderAdapter.OnItemClickListener itemClick) {
        this.mContext = mContext;
        this.bean = bean;
        this.itemClickListener = itemClick;
    }

    @Override
    public IntegralRecoderAdapter.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_vip_count_detail, null);
        IntegralRecoderAdapter.Myholder myholder = new IntegralRecoderAdapter.Myholder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(IntegralRecoderAdapter.Myholder holder, final int position) {
        String time = bean.getData().getDataList().get(position).getEGD_DateTime();//时间
        String timea = time.substring(0, 10);
        String timeb = time.substring(11, time.length());
        holder.tvTimeA.setText(timea);
        holder.tvTimeB.setText(timeb);
        double eachmoney = 0;
        double dayMoney = 0;
        for (int i = 0; i < bean.getData().getDataList().size(); i++) {
            eachmoney += bean.getData().getDataList().get(i).getIEG_Score();
        }
        holder.tvDiscountMoney.setText(Decima2KeeplUtil.stringToDecimal(bean.getData().getDataList().get(position).getIEG_Score()+ ""));

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
            holder.tvTotalMoney.setText("");
        }
        holder.ivPayWay.setImageResource(R.mipmap.acount32);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.itemClick(position);
            }
        });
    }

    private double getMoney(int position) {
        String timelast;
        String timenow;
        double daymoney = 0.00;
        timenow = bean.getData().getDataList().get(position).getIEG_UpdateTime();
        timelast = bean.getData().getDataList().get(position - 1).getIEG_UpdateTime();
        if (timenow.substring(0, 10).equals(timelast.substring(0, 10))) {

        } else {
        }
        return daymoney;
    }




    private boolean isVisiliable(IntegralNewBean bean, int position) {
        boolean isvisiliable;
        String timelast;
        String timenow;
        timenow = bean.getData().getDataList().get(position).getIEG_UpdateTime();
        timelast = bean.getData().getDataList().get(position - 1).getIEG_UpdateTime();
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

    private boolean calculateTimeAndMoney(TimesConsumeOrderBean bean, int position) {
        boolean flags = false;
        if (position >= bean.getData().getDataList().size()) {
            return flags;
        } else {
            String timenow = bean.getData().getDataList().get(position).getWO_UpdateTime();
            String timenext = bean.getData().getDataList().get(position - 1).getWO_UpdateTime();
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

    public void setParam(IntegralNewBean mReportBean) {
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
