package com.zhiluo.android.yunpu.goods.consume.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.consume.Interface.InterfaceBack;
import com.zhiluo.android.yunpu.goods.consume.bean.GoodsModelBean;

import java.util.List;

/**
 * Created by ZPH on 2019-04-16.
 */

public class NewShopRulesAdapter extends RecyclerView.Adapter<NewShopRulesAdapter.MyHolder> {
    private Context mContext;
    private ShopRuleItemAdapter mshopRuleItemAdapter;
    private List<List<GoodsModelBean>> modelList;
    private InterfaceBack mBack;


    public NewShopRulesAdapter(Context mContext, List<List<GoodsModelBean>> modelList, final InterfaceBack back) {
        this.mContext = mContext;
        this.modelList = modelList;
        this.mBack = back;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_sm_goods_rule_pop_item, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }


    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        List<GoodsModelBean> bean = modelList.get(position);
        //商品名字
        if (bean != null && bean.size() > 0) {
            holder.tvItemsName.setText(bean.get(0).getPM_Name());
        }
        holder.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 5));
        mshopRuleItemAdapter = new ShopRuleItemAdapter(bean,mBack,position);
        holder.recyclerView.setAdapter(mshopRuleItemAdapter);
        mshopRuleItemAdapter.setCategoryBeans(bean);
    }

    @Override
    public int getItemCount() {
        return modelList == null ? 0 : modelList.size();
    }

    public class ShopRuleItemAdapter extends RecyclerView.Adapter<ShopRuleItemAdapter.ItemHolder> {

        private List<GoodsModelBean> bean;
        private int mposition;
        private InterfaceBack mBack;


        public ShopRuleItemAdapter(List<GoodsModelBean> bean, final InterfaceBack back, int position) {
            this.bean = bean;
            this.mBack = back;
            this.mposition = position;
        }

        public void setCategoryBeans(List<GoodsModelBean> bean) {
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
            final GoodsModelBean beans = bean.get(position);
            holder.cbWeek.setText(beans.getPM_Properties());
            holder.cbWeek.setChecked(beans.isChecked());

            if (!beans.isEnable()) {
                holder.cbWeek.setBackgroundResource(R.drawable.shap_enable_not);

                holder.cbWeek.setEnabled(false);
            } else {
                holder.cbWeek.setEnabled(true);
                if (beans.isChecked()) {
                    holder.cbWeek.setBackgroundResource(R.drawable.lab_selected);

                } else {
                    holder.cbWeek.setBackgroundResource(R.drawable.ysl_lab_unselected);
                }
            }
            holder.cbWeek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!beans.isChecked()) {
                        beans.setChecked(!beans.isChecked());
                        holder.cbWeek.setChecked(beans.isChecked());
                        if (holder.cbWeek.isChecked()) {
                            holder.cbWeek.setBackgroundResource(R.drawable.lab_selected);

                        } else {
                            holder.cbWeek.setBackgroundResource(R.drawable.ysl_lab_unselected);

                        }
                        for (int j = 0; j < modelList.get(mposition).size(); j++) {
                            modelList.get(mposition).get(j).setChecked(false);
                        }
                        if (holder.cbWeek.isChecked()) {
                            beans.setChecked(true);
                        }

                        mBack.onResponse(modelList);
//                        if (null != mCheckListener) {
//                            mCheckListener.modelItemChecked(beans, holder.cbWeek.isChecked(),mposition);
//                        }
                        notifyDataSetChanged();
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return bean.size();
        }


        class ItemHolder extends RecyclerView.ViewHolder {
            private CheckBox cbWeek;

            public ItemHolder(View itemView) {
                super(itemView);
                cbWeek = (CheckBox) itemView.findViewById(R.id.cb_week_item);
            }
        }
    }

    public interface modelItemListener {
        void modelItemChecked(GoodsModelBean conditionBean, boolean isChecked, int position);
    }


    class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvItemsName;//商品名字
        private RecyclerView recyclerView;

        public MyHolder(View itemView) {
            super(itemView);
            tvItemsName = (TextView) itemView.findViewById(R.id.item_name);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.re_rule_level);
        }
    }
}
