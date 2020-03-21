package com.zhiluo.android.yunpu.member.manager.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.member.manager.bean.ConditionBean;

import java.util.List;

/**
 * Created by ${YSL} on 2018-05-02.
 */

public class LabAdapter extends RecyclerView.Adapter<LabAdapter.MyHolder> {

    private Context mContext;
    private List<ConditionBean> bean;
    private LabAdapter.LabItemListener mCheckListener;

    public LabAdapter(Context context, List<ConditionBean> bean, LabAdapter.LabItemListener checkItemListener) {
        mContext = context;
        this.bean = bean;
        mCheckListener = checkItemListener;
    }

    @Override
    public LabAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_condition, null);
        LabAdapter.MyHolder holder = new LabAdapter.MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final LabAdapter.MyHolder holder, int position) {
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
        holder.cbWeek.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
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
                    mCheckListener.labItemChecked(beans, holder.cbWeek.isChecked());
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bean.size();
    }

    public interface LabItemListener {
        void labItemChecked(ConditionBean conditionBean, boolean isChecked);
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private CheckBox cbWeek;

        public MyHolder(View itemView) {
            super(itemView);
            cbWeek = itemView.findViewById(R.id.cb_week_item);
        }
    }
}
