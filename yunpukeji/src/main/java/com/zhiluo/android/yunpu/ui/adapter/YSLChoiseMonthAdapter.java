package com.zhiluo.android.yunpu.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.member.manager.bean.MonthBean;
import com.zhiluo.android.yunpu.member.manager.bean.WeekBean;

import java.util.List;

/**
 * Created by ${YSL} on 2018-04-26.
 */

public class YSLChoiseMonthAdapter extends RecyclerView.Adapter<YSLChoiseMonthAdapter.MyHolder> {
    private Context mContext;
    private List<MonthBean> bean;
    private MOnthCheckItemListener mCheckListener;

    public YSLChoiseMonthAdapter(Context context, List<MonthBean> bean, MOnthCheckItemListener checkItemListener) {
        mContext = context;
        this.bean = bean;
        mCheckListener = checkItemListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pop_choise_week, null);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        final MonthBean beans = bean.get(position);
        holder.cbWeek.setText(this.bean.get(position).getWhichone());
        holder.cbWeek.setChecked(beans.isChecked());
        if (beans.isChecked()){
            holder.cbWeek.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
            holder.cbWeek.setTextColor(Color.parseColor("#FF8739"));
        }
        holder.cbWeek.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                beans.setChecked(!beans.isChecked());
                holder.cbWeek.setChecked(beans.isChecked());
                if (holder.cbWeek.isChecked()) {
                    holder.cbWeek.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    holder.cbWeek.setTextColor(Color.parseColor("#FF8739"));
                } else {
                    holder.cbWeek.setBackgroundResource(R.drawable.ysl_item_pop_choise_time);
                    holder.cbWeek.setTextColor(mContext.getResources().getColor(R.color.text_black));
                }
                if (null != mCheckListener) {
                    mCheckListener.monthitemChecked(beans, holder.cbWeek.isChecked());
                }
                //notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bean.size();
    }

    public interface MOnthCheckItemListener {
        void monthitemChecked(MonthBean monthBean, boolean isChecked);
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private CheckBox cbWeek;

        public MyHolder(View itemView) {
            super(itemView);
            cbWeek = itemView.findViewById(R.id.cb_week_item);
        }
    }
}
