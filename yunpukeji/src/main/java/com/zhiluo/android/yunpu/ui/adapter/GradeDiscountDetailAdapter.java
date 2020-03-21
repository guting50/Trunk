package com.zhiluo.android.yunpu.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;



/**
 * 作者：罗咏哲 on 2018/1/15 15:36.
 * 邮箱：137615198@qq.com
 */

public class GradeDiscountDetailAdapter extends RecyclerView.Adapter<GradeDiscountDetailAdapter.ViewHolder> {
    private DataBean mSetBean;

    public GradeDiscountDetailAdapter(DataBean mSetBean) {
        this.mSetBean = mSetBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grade_discount_detail,
                viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.etScale.setTag(i);
        viewHolder.tvName.setText(mSetBean.getSettings().get(i).getPT_Name());
        viewHolder.etScale.setText(mSetBean.getSettings().get(i).getPD_Discount() + "");
    }

    @Override
    public int getItemCount() {
        return mSetBean.getSettings().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,etScale;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_item_grade_discount_name);
            etScale = (TextView) itemView.findViewById(R.id.et_item_grade_discount_scale);
        }
    }
}
