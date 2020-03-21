package com.zhiluo.android.yunpu.goods.manager.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.goods.manager.bean.StockTakingBean;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

/**
 * 商品消费右边ListView的Adapter
 * 作者：罗咏哲 on 2017/8/1 15:24.
 * 邮箱：137615198@qq.com
 */

public class StockTakingRightAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private GoodsCheckResponseByType.DataBean mDataList;
    private ItemViewClick mItemViewClick;

    public StockTakingRightAdapter(Context mContext, GoodsCheckResponseByType.DataBean mDataList,
                                   ItemViewClick mItemViewClick) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        this.mItemViewClick = mItemViewClick;
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.getDataList().size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        GoodsCheckResponseByType.DataBean.DataListBean bean = mDataList.getDataList().get(position);
        View view = convertView;
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_stock_taking_right, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        //头像
        holder.ivGoodsImage.setImageResource(R.drawable.defalut_goods);
        if (bean.getPM_BigImg() != null) {
            if (bean.getPM_BigImg().contains("http")) {
                Glide.with(mContext).load(bean.getPM_BigImg()).into(holder.ivGoodsImage);
            } else {
                String url = bean.getPM_BigImg();
                if (url.contains("../")) {
                    String[] s = url.split("\\.\\./");
                    url = s[2];
                }
                Glide.with(mContext).load(HttpAPI.API().MAIN_DOMAIN + "/"+url).into(holder.ivGoodsImage);
            }
        } else {
            holder.ivGoodsImage.setImageResource(R.drawable.defalut_goods);
        }
        if (bean.isFlag()){
            holder.ivStock.setVisibility(View.GONE);
            holder.rlShow.setVisibility(View.VISIBLE);
        }else {
            holder.ivStock.setVisibility(View.VISIBLE);
            holder.rlShow.setVisibility(View.GONE);
        }

        String goodsType = "";
        if (bean.getPM_IsService() == 0) {
            goodsType = "普-";
            holder.tvName.setText(Html.fromHtml("<font color='#1E90FF'>" + "普" + "</font>-" + bean.getPM_Name()));
        } else if (bean.getPM_IsService() == 1) {
            goodsType = "服-";
            holder.tvName.setText(Html.fromHtml("<font color='#00EE00'>" + "服" + "</font>-" + bean.getPM_Name()));
        } else if (bean.getPM_IsService() == 2) {
            goodsType = "礼-";
            holder.tvName.setText(Html.fromHtml("<font color='#FF0000'>" + "礼" + "</font>-" + bean.getPM_Name()));
        } else if (bean.getPM_IsService() == 3) {
            goodsType = "套-";
            holder.tvName.setText(Html.fromHtml("<font color='#1E90FF'>" + "套" + "</font>-" + bean.getPM_Name()));
        } else if (bean.getPM_IsService() == 4) {
            goodsType = "套-";
            holder.tvName.setText(Html.fromHtml("<font color='#FF7F00'>" + "套" + "</font>-" + bean.getPM_Name()));
        }

        //名称
//        holder.tvName.setText(bean.getPM_Name());
        holder.tvStock.setText("账面数量：" + Decima2KeeplUtil.stringToDecimal(bean.getStock_Number()+"") + "件");
        holder.tvStockIn.setText("进价："+ Decima2KeeplUtil.stringToDecimal(bean.getPM_PurchasePrice()+""));
        holder.tvNowStork.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(bean.getNow_Stock())) + "件");
        holder.tvNum.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(bean.getNow_Stock())));
        holder.ivMove.setOnClickListener(this);
        holder.ivAdd.setOnClickListener(this);
        holder.ivStock.setOnClickListener(this);
        holder.ivMove.setTag(position);
        holder.ivAdd.setTag(position);
        holder.ivStock.setTag(position);
        return view;
    }

    @Override
    public void onClick(View view) {
        mItemViewClick.click(view);
    }

    public void setParm(GoodsCheckResponseByType.DataBean data) {
        mDataList = data;
    }

    class ViewHolder {
        ShapedImageView ivGoodsImage;//图片
        TextView tvName;//名称
        TextView tvStock;//原库存
        TextView tvNowStork;//现库存
        TextView tvNum;//现库存
        TextView tvStockIn;//进价
        ImageView ivMove;//移除
        ImageView ivAdd;//增加
        ImageView ivStock;
        RelativeLayout rlShow;

        public ViewHolder(View view) {
            ivGoodsImage = (ShapedImageView) view.findViewById(R.id.iv_item_stock_image);
            ivMove = (ImageView) view.findViewById(R.id.iv_reduce);
            ivAdd = (ImageView) view.findViewById(R.id.iv_add);
            tvName = (TextView) view.findViewById(R.id.tv_item_stock_name);
            tvStock = (TextView) view.findViewById(R.id.tv_item_stock_num);
            tvNowStork = (TextView) view.findViewById(R.id.tv_item_stock_now_num);
            tvNum = (TextView) view.findViewById(R.id.tv_stock_taking_num);
            ivStock = (ImageView) view.findViewById(R.id.iv_stock_pandian);
            rlShow = (RelativeLayout) view.findViewById(R.id.rl_stock_show);
            tvStockIn = view.findViewById(R.id.tv__stock_in);
        }
    }

    public interface ItemViewClick {
        void click(View view);
    }


}
