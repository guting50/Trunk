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

import com.google.gson.Gson;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.utils.RegexUtil;

import java.util.ArrayList;
import java.util.List;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;


/**
 * 作者：罗咏哲 on 2018/1/15 15:36.
 * 邮箱：137615198@qq.com
 */

public class GradeDiscountSetAdapter extends RecyclerView.Adapter<GradeDiscountSetAdapter.ViewHolder> {
    private DataBean mSetBean;

    public GradeDiscountSetAdapter(DataBean mSetBean) {
        this.mSetBean = mSetBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grade_discount_set,
                viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.etScale.setTag(i);
        viewHolder.tvName.setText(mSetBean.getSettings().get(i).getPT_Name());
        viewHolder.etScale.setText(mSetBean.getSettings().get(i).getPD_Discount() + "");
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int content = 100;
                if (!TextUtils.isEmpty(s)) {
                    if (RegexUtil.isDis(s.toString())) {
                        content = Integer.parseInt(s.toString());
                        mSetBean.getSettings().get((Integer) viewHolder.etScale.getTag()).setPD_Discount(content);
                    } else {
                        viewHolder.etScale.setText("请输入1-100");
                        mSetBean.getSettings().get(0);
//                        CustomToast.makeText(viewGroup.getContext(), "请输入1-100", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        viewHolder.etScale.addTextChangedListener(watcher);
    }

    @Override
    public int getItemCount() {
        return mSetBean.getSettings() == null ? 0 : mSetBean.getSettings().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        EditText etScale;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_item_grade_discount_name);
            etScale = (EditText) itemView.findViewById(R.id.et_item_grade_discount_scale);
        }
    }
}
