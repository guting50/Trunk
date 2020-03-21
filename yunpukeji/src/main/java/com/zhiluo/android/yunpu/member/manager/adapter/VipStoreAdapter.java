package com.zhiluo.android.yunpu.member.manager.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.member.manager.bean.ConditionBean;

import java.util.List;

/**
 * Created by YSL on 2018-11-13.
 */

public class VipStoreAdapter extends RecyclerView.Adapter<VipStoreAdapter.MyHolder> {
    private Context mContext;
    private List<ConditionBean> bean;
    private StoreItemListener mCheckListener;
    private boolean isvisiblestore;
    private int pos;

    public VipStoreAdapter(Context context, List<ConditionBean> bean, VipStoreAdapter.StoreItemListener checkItemListener, boolean isvisiblestore,int pos) {
        mContext = context;
        this.bean = bean;
        mCheckListener = checkItemListener;
        this.isvisiblestore = isvisiblestore;
        this.pos = pos;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_condition, null);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        final ConditionBean beans = bean.get(position);
        holder.cbWeek.setText(this.bean.get(position).getCondition());
        holder.cbWeek.setChecked(beans.isChecked());
        if (beans.isChecked()) {
            holder.cbWeek.setBackgroundResource(R.drawable.ysl_get_go);
            holder.cbWeek.setTextColor(mContext.getResources().getColor(R.color.white));
        }else {
            holder.cbWeek.setBackgroundResource(R.drawable.ysl_lab_unselected);
            holder.cbWeek.setTextColor(mContext.getResources().getColor(R.color.text_black));
        }
//        if (isvisiblestore &&issuperUser){
//
//        }
        if (isvisiblestore){

        } else {
            if (position ==pos) {
                holder.cbWeek.setClickable(true);
                holder.cbWeek.setEnabled(true);
            }else {
                holder.cbWeek.setClickable(false);
                holder.cbWeek.setEnabled(false);
            }
        }

        holder.cbWeek.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                if (!beans.isChecked()){
                    beans.setChecked(!beans.isChecked());
                    holder.cbWeek.setChecked(beans.isChecked());
                    if (holder.cbWeek.isChecked()) {
                        holder.cbWeek.setBackgroundResource(R.drawable.ysl_get_go);
                        holder.cbWeek.setTextColor(mContext.getResources().getColor(R.color.white));
                    } else {
                        holder.cbWeek.setBackgroundResource(R.drawable.ysl_lab_unselected);
                        holder.cbWeek.setTextColor(mContext.getResources().getColor(R.color.text_black));
                    }
                    if (null != mCheckListener) {
                        mCheckListener.storeItemChecked(beans, holder.cbWeek.isChecked());
                    }
                }

//                notifyDataSetChanged();
            }
        });



    }

    @Override
    public int getItemCount() {
        return bean.size();
    }

    public void setParam(boolean isvisiblestore) {
        this.isvisiblestore = isvisiblestore;
    }


    public interface StoreItemListener {
        void storeItemChecked(ConditionBean conditionBean, boolean isChecked);
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private CheckBox cbWeek;

        public MyHolder(View itemView) {
            super(itemView);
            cbWeek = itemView.findViewById(R.id.cb_week_item);
        }
    }
}
