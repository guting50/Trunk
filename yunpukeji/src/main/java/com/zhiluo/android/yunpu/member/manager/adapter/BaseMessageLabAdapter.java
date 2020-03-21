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

public class BaseMessageLabAdapter extends RecyclerView.Adapter<BaseMessageLabAdapter.MyHolder> {

    private Context mContext;
    private List<LabelBean> bean;

    public BaseMessageLabAdapter(Context context, List<LabelBean> bean) {
        mContext = context;
        this.bean = bean;

    }

    @Override
    public BaseMessageLabAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_condition, null);
        BaseMessageLabAdapter.MyHolder holder = new BaseMessageLabAdapter.MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final BaseMessageLabAdapter.MyHolder holder, final int position) {
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
        holder.cbWeek.setClickable(false);
        holder.cbWeek.setEnabled(false);

    }

    @Override
    public int getItemCount() {
        return bean.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private CheckBox cbWeek;

        public MyHolder(View itemView) {
            super(itemView);
            cbWeek = itemView.findViewById(R.id.cb_week_item);
        }
    }
}
