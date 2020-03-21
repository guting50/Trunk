package com.zhiluo.android.yunpu.goods.consume.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.util.List;

/**
 * Created by ${YSL} on 2018-05-09.
 */

public class ShopeCarAdapter extends RecyclerView.Adapter<ShopeCarAdapter.MyHolder> {
    private Context mContext;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mDataList;

    public ShopeCarAdapter(Context mContext, List<GoodsCheckResponseByType.DataBean.DataListBean> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shop_car_adapter, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        GoodsCheckResponseByType.DataBean.DataListBean bean = mDataList.get(position);
        //商品名字
        holder.tvGoodsName.setText(bean.getPM_Name());
        //价格
        holder.tvPrice.setText( Decima2KeeplUtil.stringToDecimal(bean.getPM_UnitPrice() + ""));
        //数量
        if (bean.getNum() > 0) {
            holder.tvCount.setText("x" + (int) bean.getNum());
        } else {
            holder.tvCount.setText("x" + 0);
        }
        holder.tvSpecs.setText(bean.getPM_Modle());
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvGoodsName;//商品名字
        private TextView tvPrice;//商品单价
        private TextView tvCount;//商品数量
        private TextView tvSpecs;//商品规格

        public MyHolder(View itemView) {
            super(itemView);
            tvGoodsName = itemView.findViewById(R.id.tv_goods_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvCount = itemView.findViewById(R.id.tv_count);
            tvSpecs = itemView.findViewById(R.id.tv_goods_specs);
        }
    }
}
