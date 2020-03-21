package com.zhiluo.android.yunpu.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.entity.GradeSetBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberGrade;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;


/**
 * 作者：罗咏哲 on 2018/1/15 15:36.
 * 邮箱：137615198@qq.com
 */

public class GradeIntegralDetailAdapter extends RecyclerView.Adapter<GradeIntegralDetailAdapter.ViewHolder> {
    private DataBean mBean;

    public GradeIntegralDetailAdapter(DataBean mBean) {
        this.mBean = mBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grade_integral_detail,
                viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.etScale.setTag(i);
        viewHolder.tvName.setText(mBean.getSettings().get(i).getPT_Name());
        double scale = 0;
        if (mBean.getSettings().get(i).getVS_Number() > 0) {
            scale = DoubleMathUtil.div(mBean.getSettings().get(i).getVS_CMoney(), mBean.getSettings().get(i).getVS_Number(), 2);
        }
        viewHolder.etScale.setText(Decima2KeeplUtil.stringToDecimal(scale + ""));
    }

    @Override
    public int getItemCount() {
        return mBean.getSettings().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, etScale;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_item_grade_integral_name);
            etScale = (TextView) itemView.findViewById(R.id.tv_item_grade_integral_scale);
        }
    }
}
