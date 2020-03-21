package com.zhiluo.android.yunpu.goods.manager.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.HttpAPI;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 作者：罗咏哲 on 2017/7/31 13:38.
 * 邮箱：137615198@qq.com
 */

public class GoodsInfoAdapter extends BaseAdapter {
    private Context mContext;
    private GoodsCheckResponseByType mDataList;

    public GoodsInfoAdapter(Context mContext, GoodsCheckResponseByType mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }

    @Override
    public int getCount() {
        return mDataList.getData().getDataList() == null ? 0 : mDataList.getData().getDataList().size();
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
        GoodsCheckResponseByType.DataBean.DataListBean bean = mDataList.getData().getDataList().get(position);
        Li("购物车列表=============random:" + new Gson().toJson(bean));


        View view = convertView;
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_goods_type_layout, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        //设置图片
        if (bean.getPM_BigImg() != null) {
            if (bean.getPM_BigImg().contains("http")) {
                Glide.with(mContext).load(bean.getPM_BigImg()).into(holder.ivGoods);
            } else {
                String url = bean.getPM_BigImg();
                if (url.contains("../")) {
                    String[] s = url.split("\\.\\./");
                    url = s[2];
                }
                Glide.with(mContext).load(HttpAPI.API().MAIN_DOMAIN + "/" + url).
                        into(holder.ivGoods);
            }
        } else {
            holder.ivGoods.setImageResource(R.drawable.defalut_goods);
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

//        holder.tvName.setText(bean.getPM_Name());
        holder.tvPrice.setText("¥ " + bean.getPM_UnitPrice());
        if (bean.getPM_IsService() == 1) {
            holder.tvStock.setText("无");
        } else {
            holder.tvStock.setText(bean.getStock_Number() + "件");
        }
        if (bean.getPM_Modle() != null && !bean.getPM_Modle().equals("null") && !bean.getPM_Modle().equals("")) {
            holder.tvStyle.setText(bean.getPM_Modle());
        } else {
            holder.tvStyle.setText("无");
        }
        /**多规格判断,大于1就是多规格*/
        if (bean.getGroupCount() > 1) {
            holder.tvStyle.setText(Html.fromHtml("<font color='#FF0000'>" + "多规格" + "</font>"));
        }
        /**************************/
        return view;
    }

    public void setParm(GoodsCheckResponseByType mGoodsInfo) {
        mDataList = mGoodsInfo;
    }

    class ViewHolder {
        ShapedImageView ivGoods;
        TextView tvName;
        TextView tvPrice;
        TextView tvStock;
        TextView tvStyle;

        ViewHolder(View view) {
            ivGoods = (ShapedImageView) view.findViewById(R.id.iv_item_goods);
            tvName = (TextView) view.findViewById(R.id.tv_item_goods_name);
            tvPrice = (TextView) view.findViewById(R.id.tv_item_goods_price);
            tvStock = (TextView) view.findViewById(R.id.tv_item_goods_stock);
            tvStyle = (TextView) view.findViewById(R.id.tv_item_goods_style);
        }
    }
}
