package com.zhiluo.android.yunpu.goods.consume.adapter;

import android.content.Context;
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
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.util.List;

/**
 * 商品消费右边ListView的Adapter
 * 作者：罗咏哲 on 2017/8/1 15:24.
 * 邮箱：137615198@qq.com
 */

public class GoodsConsumeRightAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mDataList;
    private ItemViewClick mItemViewClick;

    public GoodsConsumeRightAdapter(Context mContext, List<GoodsCheckResponseByType.DataBean.DataListBean> mDataList,
                                    ItemViewClick mItemViewClick) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        this.mItemViewClick = mItemViewClick;
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
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
        GoodsCheckResponseByType.DataBean.DataListBean bean = mDataList.get(position);
        View view = convertView;
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_goods_consume_right, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        //头像
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

        String goodsType = "";
        if (bean.getPM_IsService()==0){
            goodsType = "普-";
            holder.rlKucun.setVisibility(View.VISIBLE);
        }else if(bean.getPM_IsService()==1){
            goodsType = "服-";
            holder.rlKucun.setVisibility(View.VISIBLE);
        }else if(bean.getPM_IsService()==2){
            goodsType = "礼-";
            holder.rlKucun.setVisibility(View.VISIBLE);
        }else if(bean.getPM_IsService()==3){
            goodsType = "套-";
            holder.rlKucun.setVisibility(View.GONE);
        }
        //名称
        holder.tvName.setText(goodsType+bean.getPM_Name());
//        holder.tvName.setText(bean.getPM_Name());
        //价格
        holder.tvPrice.setText("¥  " + Decima2KeeplUtil.stringToDecimal(bean.getPM_UnitPrice() + ""));
        //库存
        if (bean.getPM_IsService()==1) {
//            holder.rlKucun.setVisibility(View.GONE);
            holder.tvStork.setText("无");
        } else {
            if (bean.getPM_Metering() != null) {
                holder.tvStork.setText(String.valueOf(bean.getStock_Number()) + bean.getPM_Metering());
            } else {
                holder.tvStork.setText(bean.getStock_Number() + "");
            }
        }
        //数量
        if (bean.getNum() > 0) {
            holder.tvNum.setText((int) bean.getNum() + "");
            holder.ivMove.setVisibility(View.VISIBLE);//数量不为0时显示移除图标
        } else {
            holder.ivMove.setVisibility(View.INVISIBLE);//数量为0时隐藏移除图标
            holder.tvNum.setText("0");
        }
        if (bean.getPM_Modle() != null && !bean.getPM_Modle().equals("null")&& !bean.getPM_Modle().equals("")) {
            holder.tvStyle.setText(bean.getPM_Modle());
        }else {
            holder.tvStyle.setText("无");
        }
        holder.ivMove.setOnClickListener(this);
        holder.ivAdd.setOnClickListener(this);
        holder.ivMove.setTag(position);
        holder.ivAdd.setTag(position);
        return view;
    }

    @Override
    public void onClick(View view) {
        mItemViewClick.click(view);
    }

    public void setParm(List<GoodsCheckResponseByType.DataBean.DataListBean> mGoodsList) {
        mDataList = mGoodsList;
    }

    class ViewHolder {
        ShapedImageView ivGoodsImage;//图片
        TextView tvName;//名称
        TextView tvPrice;//单价
        TextView tvStork;//库存
        TextView tvStyle;//规格
        TextView tvNum;//数量
        ImageView ivMove;//移除
        ImageView ivAdd;//增加
        RelativeLayout rlKucun;

        public ViewHolder(View view) {
            ivGoodsImage = (ShapedImageView) view.findViewById(R.id.iv_item_goods);
            ivMove = (ImageView) view.findViewById(R.id.iv_reduce);
            ivAdd = (ImageView) view.findViewById(R.id.iv_add);
            tvName = (TextView) view.findViewById(R.id.tv_item_goods_name);
            tvPrice = (TextView) view.findViewById(R.id.tv_item_goods_price);
            tvStork = (TextView) view.findViewById(R.id.tv_item_goods_stock);
            tvStyle = (TextView) view.findViewById(R.id.tv_item_goods_style);
            tvNum = (TextView) view.findViewById(R.id.tv_goods_num);
            rlKucun =(RelativeLayout)view.findViewById(R.id.rl_kucun);
        }
    }

    public interface ItemViewClick {
        void click(View view);
    }


}
