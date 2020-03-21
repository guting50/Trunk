package com.zhiluo.android.yunpu.goods.consume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.consume.bean.GoodsTypeNameBean;

import java.util.List;
import java.util.Map;

/**
 * 商品消费左边ListView的Adapter
 * 作者：罗咏哲 on 2017/8/1 14:00.
 * 邮箱：137615198@qq.com
 */

public class GoodsConsumeLeftAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private List<GoodsTypeNameBean> mDataList;
    private ItemOnClick mClick;
    private Map<Integer, Boolean> mMap;

    public GoodsConsumeLeftAdapter(Context mContext, List<GoodsTypeNameBean> mDataList,
                                   ItemOnClick mClick, Map<Integer, Boolean> mMap) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        this.mClick = mClick;
        this.mMap = mMap;
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
        View view = convertView;
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_goods_consume_left, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvTitle.setText(mDataList.get(position).getName());
        holder.layout.setOnClickListener(this);
        holder.layout.setTag(position);
        if (mMap.get(position) != null) {
            if (mMap.get(position)==true){
                holder.tvBg.setVisibility(View.VISIBLE);
                holder.tvTitle.setTextColor(mContext.getResources().getColor(R.color.yunpu));
                holder.tvTitle.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            }else {
                holder.tvBg.setVisibility(View.INVISIBLE);
                holder.tvTitle.setTextColor(mContext.getResources().getColor(R.color.black));
                holder.tvTitle.setBackgroundColor(mContext.getResources().getColor(R.color.background));
            }
        }else {
            holder.tvTitle.setBackgroundColor(mContext.getResources().getColor(R.color.background));
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        mClick.itemClick(view);
    }

    class ViewHolder {
        LinearLayout layout;
        TextView tvBg;
        TextView tvTitle;

        public ViewHolder(View view) {
            layout = (LinearLayout) view.findViewById(R.id.ll_goods_consume_left);
            tvBg = (TextView) view.findViewById(R.id.tv_goods_consume_left_bg);
            tvTitle = (TextView) view.findViewById(R.id.tv_goods_consume_left_title);
        }
    }

    public interface ItemOnClick {
        void itemClick(View view);
    }
}
