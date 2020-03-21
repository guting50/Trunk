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
import com.zhiluo.android.yunpu.member.manager.bean.LabelBean;

import java.util.List;

/**
 * Created by ${YSL} on 2018-05-02.
 */

public class AddVipLabAdapter extends RecyclerView.Adapter<AddVipLabAdapter.MyHolder> {

    private Context mContext;
    private List<LabelBean> bean;
    private AddVipLabAdapter.LabItemListener mCheckListener;

    public AddVipLabAdapter(Context context, List<LabelBean> bean, AddVipLabAdapter.LabItemListener checkItemListener) {
        mContext = context;
        this.bean = bean;
        mCheckListener = checkItemListener;
    }

    @Override
    public AddVipLabAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_condition, null);
        AddVipLabAdapter.MyHolder holder = new AddVipLabAdapter.MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final AddVipLabAdapter.MyHolder holder, final int position) {
        final LabelBean beans = bean.get(position);
        holder.cbWeek.setText(this.bean.get(position).getItemName());
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
                    mCheckListener.labItemChecked(beans, holder.cbWeek.isChecked(),position);
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
        void labItemChecked(LabelBean labelBean, boolean isChecked,int position);
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private CheckBox cbWeek;

        public MyHolder(View itemView) {
            super(itemView);
            cbWeek = itemView.findViewById(R.id.cb_week_item);
        }
    }
}
