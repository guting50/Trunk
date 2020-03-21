package com.zhiluo.android.yunpu.member.consume.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.gift.bean.ChargeListBean;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.member.manager.adapter.MemberListAdapter;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;

import java.util.List;

/**
 * Created by ${YSL} on 2018-05-15.
 */

public class JiCiConsumeAdapter extends BaseAdapter{
    private Context mContext;
    private List<ChargeListBean.DataBean> list;
    private Reduce reduce;
    private Add add;

    public JiCiConsumeAdapter(Context mContext, List<ChargeListBean.DataBean> list, Reduce reduce, Add add) {
        this.mContext = mContext;
        this.list = list;
        this.reduce = reduce;
        this.add = add;
    }

////    @Override
//    public MyAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.jiciconsumeitem, parent, false);
//        MyAdapter myAdapter = new MyAdapter(view);
//        return myAdapter;
//    }
//
//    @Override
//    public void onBindViewHolder(MyAdapter holder, final int position) {
//        ChargeListBean.DataBean bean = list.get(position);
//        holder.tvGoodsName.setText(bean.getSG_Name());//商品名称
//        holder.tvLeftCounts.setText("剩余次数：" + bean.getMCA_HowMany() + "");//剩余次数
//        holder.tvTotalCounts.setText("总次数：" + bean.getMCA_TotalCharge() + "");//总次数
//        if (bean.getSelected_Count() > 0) {
//            holder.ivReduce.setVisibility(View.VISIBLE);
//            holder.tvGoodsCounts.setText(bean.getSelected_Count() + "");//已选择的商品数量
//        } else {
//            holder.ivReduce.setVisibility(View.INVISIBLE);
//            holder.tvGoodsCounts.setText("0");//已选择的商品数量
//        }
//        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                add.addClick(v, position);
//            }
//        });
//        holder.ivReduce.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                reduce.reduceClick(v, position);
//            }
//        });
//    }


    @Override
    public int getCount() {
        return  list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        View view = convertView;
        MyAdapter holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.jiciconsumeitem, null);
            holder = new MyAdapter(view);
            view.setTag(holder);
        } else {
            holder = (MyAdapter) view.getTag();
        }
        ChargeListBean.DataBean bean = list.get(position);
        holder.tvGoodsName.setText(bean.getSG_Name());//商品名称
        holder.tvLeftCounts.setText("剩余次数：" + bean.getMCA_HowMany() + "");//剩余次数
        holder.tvTotalCounts.setText("总次数：" + bean.getMCA_TotalCharge() + "");//总次数
        if (bean.getMCA_OverTime()!=null){
            holder.tvgoodsovertime.setText("到期时间："+DateTimeUtil.formatTime(bean.getMCA_OverTime()));
        }else {
            holder.tvgoodsovertime.setText("到期时间：永久有效");
        }
        if (bean.getWR_Name()!=null&& !bean.getWR_Name().equals("")){
            holder.tvtimesrules.setText("计次规则："+bean.getWR_Name());
        }else {
            holder.tvtimesrules.setText("计次规则：无");
        }

        if (bean.getCount() > 0) {
            holder.ivReduce.setVisibility(View.VISIBLE);
            holder.tvGoodsCounts.setText(bean.getCount() + "");//已选择的商品数量
        } else {
            holder.ivReduce.setVisibility(View.INVISIBLE);
            holder.tvGoodsCounts.setText("0");//已选择的商品数量
        }
        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add.addClick(v, position);
            }
        });
        holder.ivReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reduce.reduceClick(v, position);
            }
        });

        //设置图片
        if (bean.getPM_BigImg() != null) {
            if (bean.getPM_BigImg().contains("http")){
                Glide.with(mContext).load(bean.getPM_BigImg()).into(holder.ivGoodsImage);
            }else {
                String url = bean.getPM_BigImg();
                if (url.contains("../")) {
                    String[] s = url.split("\\.\\./");
                    url = s[2];
                }
                Glide.with(mContext).load(HttpAPI.API().MAIN_DOMAIN + "/"+url).
                        into(holder.ivGoodsImage);
            }
        } else {
            holder.ivGoodsImage.setImageResource(R.drawable.ysl_add_goods);
        }


        return view;
    }

    public void setdata(List<ChargeListBean.DataBean> chargeList) {
        this.list = chargeList;
    }

    /**
     * 减少
     */
    public interface Reduce {
        void reduceClick(View view, int position);
    }

    /**
     * 增加
     */
    public interface Add {
        void addClick(View view, int position);
    }

    class MyAdapter{
        private ShapedImageView ivGoodsImage;//图片
        private ImageView ivReduce, ivAdd;
        private TextView tvLeftCounts, tvTotalCounts, tvGoodsCounts, tvGoodsName,tvgoodsovertime,tvtimesrules;

        public MyAdapter(View itemView) {
            ivGoodsImage = itemView.findViewById(R.id.img_goods_img);
            tvLeftCounts = itemView.findViewById(R.id.tv_left_counts);
            tvTotalCounts = itemView.findViewById(R.id.tv_total_counts);
            ivReduce = itemView.findViewById(R.id.iv_reduce);
            ivAdd = itemView.findViewById(R.id.iv_add);
            tvGoodsCounts = itemView.findViewById(R.id.tv_goods_num);
            tvGoodsName = itemView.findViewById(R.id.tv_type);
            tvgoodsovertime = itemView.findViewById(R.id.tv_left_overtime);
            tvtimesrules = itemView.findViewById(R.id.tv_right_times_rules);
        }
    }
//    class MyAdapter extends RecyclerView.ViewHolder{
//        private ShapedImageView ivGoodsImage;//图片
//        private ImageView ivReduce, ivAdd;
//        private TextView tvLeftCounts, tvTotalCounts, tvGoodsCounts, tvGoodsName;
//
//        public MyAdapter(View itemView) {
//            super(itemView);
//            ivGoodsImage = itemView.findViewById(R.id.img_goods_img);
//            tvLeftCounts = itemView.findViewById(R.id.tv_left_counts);
//            tvTotalCounts = itemView.findViewById(R.id.tv_total_counts);
//            ivReduce = itemView.findViewById(R.id.iv_reduce);
//            ivAdd = itemView.findViewById(R.id.iv_add);
//            tvGoodsCounts = itemView.findViewById(R.id.tv_goods_num);
//            tvGoodsName = itemView.findViewById(R.id.tv_type);
//        }
//    }
}
