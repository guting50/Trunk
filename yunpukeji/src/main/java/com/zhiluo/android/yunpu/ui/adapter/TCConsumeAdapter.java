package com.zhiluo.android.yunpu.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.TCBean;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.util.List;

/**
 * 套餐消费Adapter
 * 作者：罗咏哲 on 2018/1/27 15:24.
 * 邮箱：137615198@qq.com
 */

public class TCConsumeAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private List<TCBean> mDataList;
    private ItemViewClick mItemViewClick;

    public TCConsumeAdapter(Context mContext, List<TCBean> mDataList, ItemViewClick mItemViewClick) {
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
        TCBean bean = mDataList.get(position);
        View view = convertView;
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_tc_consume, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        //头像
        if (bean.getPC_Image() != null) {
            if (bean.getPC_Image().contains("http")) {
                Glide.with(mContext).load(bean.getPC_Image()).into(holder.ivGoodsImage);
            } else {
                String url = bean.getPC_Image();
                if (url.contains("../")) {
                    String[] s = url.split("\\.\\./");
                    url = s[2];
                }
                Glide.with(mContext).load(HttpAPI.API().MAIN_DOMAIN + "/"+url).into(holder.ivGoodsImage);
            }
        } else {
            holder.ivGoodsImage.setImageResource(R.drawable.defalut_goods);
        }
        //名称
        holder.tvName.setText(bean.getPC_Name());
        //价格
        holder.tvPrice.setText("¥ " + Decima2KeeplUtil.stringToDecimal(bean.getPC_Price() + ""));
        //数量
        if (bean.getPC_Num() > 0) {
            holder.tvNum.setText(bean.getPC_Num() + "");
            holder.ivMove.setVisibility(View.VISIBLE);//数量不为0时显示移除图标
        } else {
            holder.ivMove.setVisibility(View.INVISIBLE);//数量为0时隐藏移除图标
            holder.tvNum.setText("0");
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

    public void setParm(List<TCBean> mList) {
        mDataList = mList;

    }

    class ViewHolder {
        ShapedImageView ivGoodsImage;//图片
        TextView tvName;//名称
        TextView tvPrice;//单价
        TextView tvModel;//库存
        TextView tvNum;//数量
        ImageView ivMove;//移除
        ImageView ivAdd;//增加

        public ViewHolder(View view) {
            ivGoodsImage = (ShapedImageView) view.findViewById(R.id.iv_item_goods);
            ivMove = (ImageView) view.findViewById(R.id.iv_reduce);
            ivAdd = (ImageView) view.findViewById(R.id.iv_add);
            tvName = (TextView) view.findViewById(R.id.tv_item_goods_name);
            tvPrice = (TextView) view.findViewById(R.id.tv_item_goods_price);
            tvModel = (TextView) view.findViewById(R.id.tv_item_goods_stock);
            tvNum = (TextView) view.findViewById(R.id.tv_goods_num);
        }
    }

    public interface ItemViewClick {
        void click(View view);
    }


}
