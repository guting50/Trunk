package com.zhiluo.android.yunpu.goods.consume.adapter;

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
import com.google.gson.Gson;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsDataBean;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import org.litepal.crud.DataSupport;

import java.util.List;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

public class GoodsConsumeNewAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mDataList;
    private ItemViewClick mItemViewClick;

    public GoodsConsumeNewAdapter(Context mContext, List<GoodsCheckResponseByType.DataBean.DataListBean> mDataList,
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        GoodsCheckResponseByType.DataBean.DataListBean bean = mDataList.get(position);
        Li("=====getView=======random:" + new Gson().toJson(bean));
        View view = convertView;
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_goods_consume_new, null);
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
                Glide.with(mContext).load(HttpAPI.API().MAIN_DOMAIN + "/" + url).into(holder.ivGoodsImage);
            }
        } else {
            holder.ivGoodsImage.setImageResource(R.drawable.defalut_goods);
        }

        String goodsType = "";
        if (bean.getPM_IsService() == 0) {
            goodsType = "普-";
            holder.rlKucun.setVisibility(View.VISIBLE);
            holder.tvName.setText(Html.fromHtml("<font color='#1E90FF'>" + "普" + "</font>-" + bean.getPM_Name()));
        } else if (bean.getPM_IsService() == 1) {
            goodsType = "服-";
            holder.rlKucun.setVisibility(View.VISIBLE);
            holder.tvName.setText(Html.fromHtml("<font color='#00EE00'>" + "服" + "</font>-" + bean.getPM_Name()));
        } else if (bean.getPM_IsService() == 2) {
            goodsType = "礼-";
            holder.rlKucun.setVisibility(View.VISIBLE);
            holder.tvName.setText(Html.fromHtml("<font color='#FF0000'>" + "礼" + "</font>-" + bean.getPM_Name()));
        } else if (bean.getPM_IsService() == 3) {
            goodsType = "套-";
            holder.rlKucun.setVisibility(View.GONE);
            holder.tvName.setText(Html.fromHtml("<font color='#1E90FF'>" + "套" + "</font>-" + bean.getPM_Name()));
        } else if (bean.getPM_IsService() == 4) {
            goodsType = "套-";
            holder.rlKucun.setVisibility(View.GONE);
            holder.tvName.setText(Html.fromHtml("<font color='#FF7F00'>" + "套" + "</font>-" + bean.getPM_Name()));
        }
        //名称
//        holder.tvName.setText(goodsType+bean.getPM_Name());
//        holder.tvName.setText(bean.getPM_Name());
        //价格
        holder.tvPrice.setText("¥  " + Decima2KeeplUtil.stringToDecimal(bean.getPM_UnitPrice() + ""));
        //库存
        if (bean.getPM_IsService() == 1) {
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
//        if (bean.getNum() > 0) {
//            holder.tvNum.setText((int) bean.getNum() + "");
//            holder.ivMove.setVisibility(View.VISIBLE);//数量不为0时显示移除图标
//        } else {
//            holder.ivMove.setVisibility(View.INVISIBLE);//数量为0时隐藏移除图标
//            holder.tvNum.setText("0");
//        }

        /**取购物车列表*/
        List<GoodsDataBean> beanLitePal = DataSupport.where("gid==?", bean.getGID()).find(GoodsDataBean.class);
        if (beanLitePal != null && beanLitePal.size() > 0) {
            holder.tvNum.setText(beanLitePal.get(0).getNum() + "");
            holder.ivMove.setVisibility(View.VISIBLE);//数量不为0时显示移除图标
        } else {
            holder.ivMove.setVisibility(View.INVISIBLE);//数量为0时隐藏移除图标
            holder.tvNum.setText("0");
        }
        /*****************/


        /**多规格判断,大于1就是多规格*/
        if (bean.getGroupCount() > 1) {
            holder.rl_plus.setVisibility(View.GONE);
            holder.tv_many_norms.setVisibility(View.VISIBLE);
        } else {
            holder.rl_plus.setVisibility(View.VISIBLE);
            holder.tv_many_norms.setVisibility(View.GONE);
        }
        /**************************/

//        if (bean.getPM_Modle() != null && !bean.getPM_Modle().equals("null")&& !bean.getPM_Modle().equals("")) {
//            holder.tvStyle.setText(bean.getPM_Modle());
//        }else {
//            holder.tvStyle.setText("无");
//        }
        holder.ivMove.setOnClickListener(this);
        holder.ivAdd.setOnClickListener(this);
        holder.ivMove.setTag(position);
        holder.ivAdd.setTag(position);
        holder.tv_many_norms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemViewClick.manyNormsClick(position);
            }
        });
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
        TextView tv_many_norms;//选规格
        TextView tvName;//名称
        TextView tvPrice;//单价
        TextView tvStork;//库存
        TextView tvStyle;//规格
        TextView tvNum;//数量
        ImageView ivMove;//移除
        ImageView ivAdd;//增加
        RelativeLayout rlKucun;
        RelativeLayout rl_plus;

        public ViewHolder(View view) {
            ivGoodsImage = (ShapedImageView) view.findViewById(R.id.iv_item_goods);
            ivMove = (ImageView) view.findViewById(R.id.iv_reduce);
            ivAdd = (ImageView) view.findViewById(R.id.iv_add);
            tvName = (TextView) view.findViewById(R.id.tv_item_goods_name);
            tvPrice = (TextView) view.findViewById(R.id.tv_item_goods_price);
            tvStork = (TextView) view.findViewById(R.id.tv_item_goods_stock);
            tvStyle = (TextView) view.findViewById(R.id.tv_item_goods_style);
            tvNum = (TextView) view.findViewById(R.id.tv_goods_num);
            rlKucun = (RelativeLayout) view.findViewById(R.id.rl_kucun);
            rl_plus = (RelativeLayout) view.findViewById(R.id.rl_plus);
            tv_many_norms = (TextView) view.findViewById(R.id.tv_many_norms);
        }
    }

    public interface ItemViewClick {
        void click(View view);

        /**
         * 选规格
         * */
        void manyNormsClick(int position);
    }


}
