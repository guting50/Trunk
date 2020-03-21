package com.zhiluo.android.yunpu.goods.manager.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.HttpAPI;

import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/27 10:20.
 * 邮箱：137615198@qq.com
 */

public class GoodsWarehousingAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mGoodsBean;
    private IClick itemOnclick;

    public GoodsWarehousingAdapter(Context context, List<GoodsCheckResponseByType.DataBean.DataListBean> mGoodsBean,
                                   IClick itemOnclick) {
        this.context = context;
        this.mGoodsBean = mGoodsBean;
        this.itemOnclick = itemOnclick;
    }

    @Override
    public int getCount() {
        return mGoodsBean == null ? 0 : mGoodsBean.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        GoodsCheckResponseByType.DataBean.DataListBean bean = mGoodsBean.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_warehousing_right, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        String goodsType = "";
        if (bean.getPM_IsService() == 0) {
            goodsType = "普-";
            viewHolder.tvName.setText(Html.fromHtml("<font color='#1E90FF'>" + "普" + "</font>-" + bean.getPM_Name()));
        } else if (bean.getPM_IsService() == 1) {
            goodsType = "服-";
            viewHolder.tvName.setText(Html.fromHtml("<font color='#00EE00'>" + "服" + "</font>-" + bean.getPM_Name()));
        } else if (bean.getPM_IsService() == 2) {
            goodsType = "礼-";
            viewHolder.tvName.setText(Html.fromHtml("<font color='#FF0000'>" + "礼" + "</font>-" + bean.getPM_Name()));
        } else if (bean.getPM_IsService() == 3) {
            goodsType = "套-";
            viewHolder.tvName.setText(Html.fromHtml("<font color='#1E90FF'>" + "套" + "</font>-" + bean.getPM_Name()));
        } else if (bean.getPM_IsService() == 4) {
            goodsType = "套-";
            viewHolder.tvName.setText(Html.fromHtml("<font color='#FF7F00'>" + "套" + "</font>-" + bean.getPM_Name()));
        }

//        viewHolder.tvName.setText(bean.getPM_Name() == null ? "" : bean.getPM_Name());
        viewHolder.tvStock.setText("库存：" + bean.getStock_Number());
        viewHolder.tvFormat.setText(bean.getPM_Modle() == null ? "" : bean.getPM_Modle());
        viewHolder.tvPrice.setText("" + bean.getPM_PurchasePrice());
        if (bean.getPM_BigImg() != null) {
            if (bean.getPM_BigImg().contains("http")) {
                Glide.with(context).load(bean.getPM_BigImg()).into(viewHolder.ivPicture);
            } else {
                String url = bean.getPM_BigImg();
                if (url.contains("../")) {
                    String[] s = url.split("\\.\\./");
                    url = s[2];
                }
                Glide.with(context).load(HttpAPI.API().MAIN_DOMAIN + "/" + url).into(viewHolder.ivPicture);
            }
        } else {
            viewHolder.ivPicture.setImageResource(R.drawable.defalut_goods);
        }


        //数量
        if (bean.getNum() > 0) {
            viewHolder.tvNum.setText((int) bean.getNum() + "");
            viewHolder.ivMove.setVisibility(View.VISIBLE);//数量不为0时显示移除图标
        } else {
            viewHolder.ivMove.setVisibility(View.INVISIBLE);//数量为0时隐藏移除图标
            viewHolder.tvNum.setText("0");
        }

        viewHolder.ivMove.setOnClickListener(this);
        viewHolder.ivAdd.setOnClickListener(this);
        viewHolder.ivMove.setTag(i);
        viewHolder.ivAdd.setTag(i);

        return view;
    }

    @Override
    public void onClick(View view) {
        itemOnclick.onClick(view);
    }

    public void setParm(List<GoodsCheckResponseByType.DataBean.DataListBean> mGoodList) {
        mGoodsBean = mGoodList;
    }


    class ViewHolder {
        TextView tvName;//名称
        TextView tvStock;//库存
        TextView tvFormat;//规格
        TextView tvPrice;//进价
        ShapedImageView ivPicture;
        TextView tvNum;//数量
        ImageView ivMove;//移除
        ImageView ivAdd;//增加

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_item_warehousing_name);
            tvStock = (TextView) view.findViewById(R.id.tv_item_stock_num);
            tvFormat = (TextView) view.findViewById(R.id.tv_item_stock_spl);
            tvPrice = (TextView) view.findViewById(R.id.tv_item_warehousing_price);
            ivPicture = (ShapedImageView) view.findViewById(R.id.iv_item_warehousing_image);
            ivMove = (ImageView) view.findViewById(R.id.iv_reduce);
            ivAdd = (ImageView) view.findViewById(R.id.iv_add);
            tvNum = (TextView) view.findViewById(R.id.tv_goods_num);
        }
    }

    public interface IClick {
        void onClick(View view);
    }

}
