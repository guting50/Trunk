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
import com.zhiluo.android.yunpu.entity.NewGradeBean;
import com.zhiluo.android.yunpu.utils.RegexUtil;


/**
 * 作者：罗咏哲 on 2018/1/15 15:36.
 * 邮箱：137615198@qq.com
 */

public class NewGradeDiscountSetAdapter extends RecyclerView.Adapter<NewGradeDiscountSetAdapter.ViewHolder> {
    private NewGradeBean mSetBean;

    public NewGradeDiscountSetAdapter(NewGradeBean mSetBean) {
        this.mSetBean = mSetBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grade_discount_set,
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
                int content = 100;
                if (!TextUtils.isEmpty(s)) {
                    if (RegexUtil.isDis(s.toString())) {
                        content = Integer.parseInt(s.toString());
                    } else {
                        holder.etScale.setText("100");
//                        CustomToast.makeText(viewGroup.getContext(), "请输入1-100", Toast.LENGTH_SHORT).show();
                    }
                }
                mSetBean.getData().get((Integer) holder.etScale.getTag()).setPD_Discount(content);
            }
        };
        holder.etScale.addTextChangedListener(watcher);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.etScale.setTag(i);
        viewHolder.tvName.setText(mSetBean.getData().get(i).getPT_Name());
        viewHolder.etScale.setText(mSetBean.getData().get(i).getPD_Discount() + "");
    }

    @Override
    public int getItemCount() {
        return mSetBean.getData()==null?0:mSetBean.getData().size();
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
