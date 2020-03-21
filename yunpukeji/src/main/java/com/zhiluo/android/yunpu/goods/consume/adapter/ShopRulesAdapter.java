package com.zhiluo.android.yunpu.goods.consume.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsModelListBean;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by ZPH on 2019-04-16.
 */

public class ShopRulesAdapter  extends RecyclerView.Adapter<ShopRulesAdapter.MyHolder>{
    private Context mContext;
    private ShopRuleItemAdapter mshopRuleItemAdapter;
    private List<List<GoodsModelListBean.DataBean>> modelList;
    private ShopRulesAdapter.modelItemListener mCheckListener;

    public ShopRulesAdapter(Context mContext, List<List<GoodsModelListBean.DataBean>> modelList,ShopRulesAdapter.modelItemListener  mCheckListener) {
        this.mContext = mContext;
        this.modelList = modelList;
        this.mCheckListener = mCheckListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_sm_goods_rule_pop_item,parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }


    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        List<GoodsModelListBean.DataBean> bean = modelList.get(position);
        //商品名字
        if (bean!=null&&bean.size()>0){
            holder.tvItemsName.setText(bean.get(0).getPM_Name());
        }
        holder.recyclerView.setLayoutManager(new GridLayoutManager(mContext,5));
        mshopRuleItemAdapter = new ShopRuleItemAdapter(bean,mCheckListener,position);
        mshopRuleItemAdapter.setCategoryBeans(bean);
        holder.recyclerView.setAdapter(mshopRuleItemAdapter);
    }

    @Override
    public int getItemCount() {
        return modelList == null ? 0 : modelList.size();
    }

    public  class ShopRuleItemAdapter extends RecyclerView.Adapter<ShopRuleItemAdapter.ItemHolder>{

        private List<GoodsModelListBean.DataBean> bean;
        private ShopRulesAdapter.modelItemListener mCheckListener;
        private int mposition;


        public ShopRuleItemAdapter( List<GoodsModelListBean.DataBean> bean, ShopRulesAdapter.modelItemListener checkItemListener, int position) {

            this.bean = bean;
            mCheckListener = checkItemListener;
            this.mposition = position;
        }

        public void setCategoryBeans(List<GoodsModelListBean.DataBean> bean) {
            this.bean = bean;
            notifyDataSetChanged();
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.rule_pop_item, parent,false);
            ItemHolder holder = new ItemHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final ItemHolder holder, int position) {
            final GoodsModelListBean.DataBean beans = bean.get(position);
        holder.cbWeek.setText(beans.getPM_Properties());
        holder.cbWeek.setChecked(beans.isChecked());

        if (!beans.isEnable()){
            holder.cbWeek.setBackgroundResource(R.color.lightgray);
            holder.cbWeek.setTextColor(mContext.getResources().getColor(R.color.text_black));
            holder.cbWeek.setEnabled(false);
        }else {
            holder.cbWeek.setEnabled(true);
            if (beans.isChecked()) {
                holder.cbWeek.setBackgroundResource(R.drawable.ysl_get_go);
                holder.cbWeek.setTextColor(mContext.getResources().getColor(R.color.white));
            }else {
                holder.cbWeek.setBackgroundResource(R.drawable.ysl_lab_unselected);
                holder.cbWeek.setTextColor(mContext.getResources().getColor(R.color.text_black));
            }
        }
            holder.cbWeek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!beans.isChecked()){
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
                            mCheckListener.modelItemChecked(beans, holder.cbWeek.isChecked(),mposition);
                        }
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
                cbWeek = itemView.findViewById(R.id.cb_week_item);
            }
        }
    }

    public interface modelItemListener {
        void modelItemChecked(GoodsModelListBean.DataBean conditionBean, boolean isChecked,int position);
    }


    class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvItemsName;//商品名字
        private RecyclerView recyclerView;

        public MyHolder(View itemView) {
            super(itemView);
            tvItemsName = itemView.findViewById(R.id.item_name);
            recyclerView = itemView.findViewById(R.id.re_rule_level);
        }
    }
}
