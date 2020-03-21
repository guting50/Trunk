package com.zhiluo.android.yunpu.consume.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.goods.manager.bean.OilListBean;


/**
 * Created by ZPH on 2019-05-20.
 */

public class OneKeyRefuelingadapter extends RecyclerView.Adapter<OneKeyRefuelingadapter.ItemHolder> {

    private Context mContext;
    private OilListBean bean;//商品列表
    private modelItemListener mCheckListener;


    public OneKeyRefuelingadapter(Context mContext,OilListBean bean, modelItemListener checkItemListener) {
        this.mContext = mContext;
        this.bean = bean;
        mCheckListener = checkItemListener;
    }

    public void setCategoryBeans(OilListBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rule_pop_item, parent, false);
        ItemHolder holder = new ItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {
        final OilListBean.DataBean beans = bean.getData().get(position);
        holder.cbWeek.setText(beans.getOM_Name());
        holder.cbWeek.setChecked(beans.isChecked());


        holder.cbWeek.setEnabled(true);
        if (beans.isChecked()) {
            holder.cbWeek.setBackgroundResource(R.drawable.ysl_get_go);
            holder.cbWeek.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            holder.cbWeek.setBackgroundResource(R.drawable.ysl_lab_unselected);
            holder.cbWeek.setTextColor(mContext.getResources().getColor(R.color.text_black));
        }

        holder.cbWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!beans.isChecked()) {
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
                        mCheckListener.modelItemChecked(beans, holder.cbWeek.isChecked(), position);
                    }
                    notifyDataSetChanged();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return bean.getData().size();
    }


    class ItemHolder extends RecyclerView.ViewHolder {
        private CheckBox cbWeek;

        public ItemHolder(View itemView) {
            super(itemView);
            cbWeek = itemView.findViewById(R.id.cb_week_item);
        }
    }

    public interface modelItemListener {
        void modelItemChecked(OilListBean.DataBean conditionBean, boolean isChecked, int position);
    }


}
