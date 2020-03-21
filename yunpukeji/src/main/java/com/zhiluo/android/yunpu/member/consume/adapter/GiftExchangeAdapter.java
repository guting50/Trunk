package com.zhiluo.android.yunpu.member.consume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.gift.bean.GiftListBean;
import com.zhiluo.android.yunpu.http.HttpAPI;

import java.util.List;

/**
 * 积分兑换ListView的Adapter
 * 作者：罗咏哲 on 2017/8/1 15:24.
 * 邮箱：137615198@qq.com
 */

public class GiftExchangeAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private List<GiftListBean.DataBean.DataListBean> mDataList;
    private ItemViewClick mItemViewClick;

    public GiftExchangeAdapter(Context mContext, List<GiftListBean.DataBean.DataListBean> mDataList,
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
        GiftListBean.DataBean.DataListBean bean = mDataList.get(position);
        View view = convertView;
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_gift_exchange, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        //头像
        if (bean.getGM_PictureUrl() != null) {
            if (bean.getGM_PictureUrl().contains("http")) {
                Glide.with(mContext).load(bean.getGM_PictureUrl()).into(holder.ivGoodsImage);
            } else {
                Glide.with(mContext).load(HttpAPI.API().MAIN_DOMAIN + bean.getGM_PictureUrl()).into(holder.ivGoodsImage);
            }
        } else {
            holder.ivGoodsImage.setImageResource(R.drawable.defalut_goods);
        }
        //名称
        holder.tvName.setText(bean.getGM_Name());
        //库存
        if (bean.getGM_Amount() > 0) {
            holder.tvStork.setText("礼品数量:" + bean.getGM_Amount());
        } else {
            holder.tvStork.setText("礼品数量:" + "0");
        }
        //数量
        if (bean.getGM_Num() > 0) {
            holder.tvNum.setText(bean.getGM_Num() + "");
            holder.ivMove.setVisibility(View.VISIBLE);//数量不为0时显示移除图标
        } else {
            holder.ivMove.setVisibility(View.INVISIBLE);//数量为0时隐藏移除图标
            holder.tvNum.setText("0");
        }
        holder.tvNeedIntegral.setText(bean.getGM_Integral() + "");
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

    class ViewHolder {
        ShapedImageView ivGoodsImage;//图片
        TextView tvName;//名称
        TextView tvStork;//库存
        TextView tvNeedIntegral;//所需积分
        TextView tvNum;//数量
        ImageView ivMove;//移除
        ImageView ivAdd;//增加

        public ViewHolder(View view) {
            ivGoodsImage = (ShapedImageView) view.findViewById(R.id.iv_gift_exchange_picture);
            ivMove = (ImageView) view.findViewById(R.id.iv_reduce);
            ivAdd = (ImageView) view.findViewById(R.id.iv_add);
            tvName = (TextView) view.findViewById(R.id.tv_gift_exchange_name);
            tvStork = (TextView) view.findViewById(R.id.tv_gift_exchange_storage);
            tvNeedIntegral = (TextView) view.findViewById(R.id.tv_gift_exchange_integral);
            tvNum = (TextView) view.findViewById(R.id.tv_gift_exchange_number);
        }
    }

    public interface ItemViewClick {
        void click(View view);
    }
}
