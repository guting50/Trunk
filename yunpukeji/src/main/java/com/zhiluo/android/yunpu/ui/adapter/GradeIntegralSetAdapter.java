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
import com.zhiluo.android.yunpu.entity.GradeSetBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberGrade;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;

import java.util.List;


/**
 * 作者：罗咏哲 on 2018/1/15 15:36.
 * 邮箱：137615198@qq.com
 */

public class GradeIntegralSetAdapter extends RecyclerView.Adapter<GradeIntegralSetAdapter.ViewHolder> {
    private GradeSetBean mSetBean;

    public GradeIntegralSetAdapter(GradeSetBean mSetBean) {
        this.mSetBean = mSetBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grade_integral_set,
                viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                double content = 0;
                if (!TextUtils.isEmpty(s)) {
                    content = Double.parseDouble(s.toString());
                }
                mSetBean.getData().get((Integer) holder.etScale.getTag()).setVS_CMoney(content);
                mSetBean.getData().get((Integer) holder.etScale.getTag()).setVS_Number(1);
            }
        };
        holder.etScale.addTextChangedListener(watcher);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.etScale.setTag(i);
        viewHolder.tvName.setText(mSetBean.getData().get(i).getPT_Name());
        double scale = 0;
        if (mSetBean.getData().get(i).getVS_Number() > 0) {
            scale = DoubleMathUtil.div(mSetBean.getData().get(i).getVS_CMoney(), mSetBean.getData().get(i).getVS_Number(), 2);
        }
        viewHolder.etScale.setText(Decima2KeeplUtil.stringToDecimal(scale + ""));
    }

    @Override
    public int getItemCount() {
        return mSetBean.getData().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        EditText etScale;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_item_grade_integral_name);
            etScale = (EditText) itemView.findViewById(R.id.et_item_grade_integral_scale);
        }
    }
}
