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

import java.text.DecimalFormat;
import java.util.List;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ${YSL} on 2018-05-18.
 */

public class InRecoderAdapter extends RecyclerView.Adapter<InRecoderAdapter.MyHolder> {
    private Context mContext;
    private List<InRecoderBean.DataBean.DataListBean> inRecoderBean;
    private OnItemClick mItemClick;

    public InRecoderAdapter(Context mContext, List<InRecoderBean.DataBean.DataListBean> inRecoderBean, OnItemClick itemClick) {
        this.mContext = mContext;
        this.inRecoderBean = inRecoderBean;
        this.mItemClick = itemClick;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_inrecoder, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.tvTime.setText(inRecoderBean.get(position).getSI_CreateTime());
        holder.tvRecoder.setText(inRecoderBean.get(position).getSI_TrackingNo());

        String priceStr = String.valueOf(inRecoderBean.get(position).getSI_TotalPrice());
        if (priceStr.equals("0.0")) {
            holder.tvTotalPrice.setText("0.00");
        } else {
            DecimalFormat df = new DecimalFormat("#.00");
            if (df.format(inRecoderBean.get(position).getSI_TotalPrice()).startsWith(".")) {
                holder.tvTotalPrice.setText("0" + df.format(inRecoderBean.get(position).getSI_TotalPrice()));
            } else {
                holder.tvTotalPrice.setText(df.format(inRecoderBean.get(position).getSI_TotalPrice()));
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
        return inRecoderBean == null ? 0 : inRecoderBean.size();
    }

    public void setParm(List<InRecoderBean.DataBean.DataListBean> inBean) {
        inRecoderBean = inBean;
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
