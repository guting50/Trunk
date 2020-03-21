package com.zhiluo.android.yunpu.goods.consume.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsDataBean;
import com.zhiluo.android.yunpu.http.HttpAPI;

import org.litepal.crud.DataSupport;

import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

    private Context mContext;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mDataList;
    private ItemClickEvent mItemClickEvent;

    public interface ItemClickEvent {
        void refreshClick();
    }

    public ShoppingCartAdapter(Context mContext, List<GoodsCheckResponseByType.DataBean.DataListBean> mDataList, ItemClickEvent clickEvent) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        this.mItemClickEvent = clickEvent;
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_cart, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GoodsCheckResponseByType.DataBean.DataListBean bean = mDataList.get(position);

        //头像
        if (bean.getPM_BigImg() != null) {
            if (bean.getPM_BigImg().contains("http")) {
                Glide.with(mContext).load(bean.getPM_BigImg()).into(holder.iv_image);
            } else {
                String url = bean.getPM_BigImg();
                if (url.contains("../")) {
                    String[] s = url.split("\\.\\./");
                    url = s[2];
                }
                Glide.with(mContext).load(HttpAPI.API().MAIN_DOMAIN + "/" + url).into(holder.iv_image);
            }
        } else {

            Glide.with(mContext).load(R.drawable.defalut_goods).into(holder.iv_image);
        }


        holder.tv_shop_name.setText(bean.getPM_Name());
        holder.tv_shop_info.setText(bean.getPM_Modle());

        /**取购物车列表*/
        List<GoodsDataBean> beanLitePal = DataSupport.where("gid==?", bean.getGID()).find(GoodsDataBean.class);
        final GoodsDataBean dataLite = beanLitePal.get(0);
        String money = "￥" + dataLite.getNum() * bean.getPM_UnitPrice();
        holder.tv_shop_money.setText(money);
        holder.tv_goods_num.setText(dataLite.getNum() + "");

        holder.iv_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(holder.tv_goods_num.getText().toString());
                List<GoodsDataBean> beanLitePal = DataSupport.where("gid==?", bean.getGID()).find(GoodsDataBean.class);
                GoodsDataBean dataFlag = beanLitePal.get(0);
                if (num > 1) {
                    dataFlag.setNum(dataFlag.getNum() - 1);
                    dataFlag.save();
                    bean.setNum(dataFlag.getNum());
                } else {
                    mDataList.remove(position);
                    DataSupport.deleteAll(GoodsDataBean.class, "gid = ?", bean.getGID());
                }
                notifyDataSetChanged();
                mItemClickEvent.refreshClick();
            }
        });
        holder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<GoodsDataBean> beanLitePal = DataSupport.where("gid==?", bean.getGID()).find(GoodsDataBean.class);
                GoodsDataBean dataFlag = beanLitePal.get(0);
                dataFlag.setNum(dataFlag.getNum() + 1);
                dataFlag.save();
                bean.setNum(dataFlag.getNum());
                notifyDataSetChanged();
                mItemClickEvent.refreshClick();
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //商品图片
        ImageView iv_image;
        //商品名称
        TextView tv_shop_name;
        //商品信息
        TextView tv_shop_info;
        //单价
        TextView tv_shop_money;
        //数量
        TextView tv_goods_num;
        //减
        ImageView iv_reduce;
        //加
        ImageView iv_add;

        public ViewHolder(View view) {
            super(view);
            iv_image = (ImageView) view.findViewById(R.id.iv_image);
            tv_shop_name = (TextView) view.findViewById(R.id.tv_shop_name);
            tv_shop_info = (TextView) view.findViewById(R.id.tv_shop_info);
            tv_shop_money = (TextView) view.findViewById(R.id.tv_shop_money);
            tv_goods_num = (TextView) view.findViewById(R.id.tv_goods_num);
            iv_reduce = (ImageView) view.findViewById(R.id.iv_reduce);
            iv_add = (ImageView) view.findViewById(R.id.iv_add);
        }
    }
}
