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
import com.zhiluo.android.yunpu.statistics.account.bean.MemberSignReportBean;
import com.zhiluo.android.yunpu.statistics.account.bean.RechargeTimesReportBean;
import com.zhiluo.android.yunpu.statistics.account.fragment.RechargeTimesFragment;

import java.util.ArrayList;
import java.util.List;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ZPH on 2019-05-16.
 */

public class MemberSignAdapter extends RecyclerView.Adapter<MemberSignAdapter.Myholder>{

    private Context mContext;
    private MemberSignReportBean bean;
    private OnItemClickListener itemClickListener;


    public MemberSignAdapter(Context mContext, MemberSignReportBean bean, OnItemClickListener itemClick) {
        this.mContext = mContext;
        this.bean = bean;
        this.itemClickListener = itemClick;
    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_member_sign_report, null);
        Myholder myholder = new Myholder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(Myholder holder, final int position) {

        holder.tvTime.setText(bean.getData().getDataList().get(position).getMCI_UpdateTime() + "");
        if (bean.getData().getDataList().get(position).getVIP_Name() != null) {
            holder.tvName.setText(bean.getData().getDataList().get(position).getVIP_Name() + "");
        } else {
            holder.tvName.setText(bean.getData().getDataList().get(position).getVCH_Card() + "");
        }


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.itemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.getData().getDataList().size();
    }

    public void setParams(MemberSignReportBean mReportBean) {
        bean = mReportBean;
    }


    public interface OnItemClickListener {
        void itemClick(int position);
    }


    class Myholder extends RecyclerView.ViewHolder {
        private TextView tvTime, tvName;
        private RelativeLayout relativeLayout;


        public Myholder(View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_item_check_time);
            tvName = itemView.findViewById(R.id.tv_item_check_name);
            relativeLayout = itemView.findViewById(R.id.rl_item_click);
        }
    }
}
