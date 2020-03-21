package com.zhiluo.android.yunpu.ui.activity.good.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.ui.activity.good.bean.InRecoderBean;
import com.zhiluo.android.yunpu.ui.activity.good.bean.OutRecoderBean;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by ${YSL} on 2018-05-18.
 */

public class OutRecoderAdapter extends RecyclerView.Adapter<OutRecoderAdapter.MyHolder> {
    private Context mContext;
    private List<OutRecoderBean.DataBean.DataListBean> outRecoderBean;
    private OnItemClick mItemClick;

    public OutRecoderAdapter(Context mContext, List<OutRecoderBean.DataBean.DataListBean> outRecoderBean, OnItemClick itemClick) {
        this.mContext = mContext;
        this.outRecoderBean = outRecoderBean;
        this.mItemClick = itemClick;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_out_recoder, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.tvTime.setText(outRecoderBean.get(position).getSO_CreateTime());
        holder.tvRecoder.setText(outRecoderBean.get(position).getSO_TrackingNo());

        String priceStr= String.valueOf(outRecoderBean.get(position).getSO_TotalPrice());
        if(priceStr.equals("0.0")){
            holder.tvTotalPrice.setText("0.00");
        }else {
            DecimalFormat df = new DecimalFormat("#.00");
            if (df.format(outRecoderBean.get(position).getSO_TotalPrice()).startsWith(".")) {
                holder.tvTotalPrice.setText("0" + df.format(outRecoderBean.get(position).getSO_TotalPrice()));
            } else {
                holder.tvTotalPrice.setText(df.format(outRecoderBean.get(position).getSO_TotalPrice()));
            }
        }
        holder.rLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClick.itemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return outRecoderBean == null ? 0 : outRecoderBean.size();
    }

    public void setParm(List<OutRecoderBean.DataBean.DataListBean> outBean) {
        outRecoderBean = outBean;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvTime, tvRecoder, tvTotalPrice;
        private RelativeLayout rLayout;

        public MyHolder(View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvRecoder = itemView.findViewById(R.id.tv_oder);
            tvTotalPrice = itemView.findViewById(R.id.tv_total_price);
            rLayout = itemView.findViewById(R.id.r_layout);
        }
    }

    public interface OnItemClick {
        void itemClick(View view, int position);
    }
}
