package com.zhiluo.android.yunpu.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;

import java.util.List;

/**
 * 作者：罗咏哲 on 2018/1/26 17:55.
 * 邮箱：137615198@qq.com
 */

public class InitTCAdapter extends BaseAdapter implements View.OnClickListener{
    private Context mContext;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mList;
    private IItemClick itemClick;

    public InitTCAdapter(Context mContext, List<GoodsCheckResponseByType.DataBean.DataListBean> mList,
                         IItemClick itemClick) {
        this.mContext = mContext;
        this.mList = mList;
        this.itemClick = itemClick;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_init_goods, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //数量
        if (mList.get(position).getNum() > 0) {
            holder.tvNum.setText((int)mList.get(position).getNum() + "");
            holder.ivMove.setVisibility(View.VISIBLE);//数量不为0时显示移除图标
        } else {
            holder.ivMove.setVisibility(View.INVISIBLE);//数量为0时隐藏移除图标
            holder.tvNum.setText("0");
        }
        holder.tvName.setText(mList.get(position).getPM_Name());
        holder.tvNum.setText("" + (int)mList.get(position).getNum());
//        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                itemClick.itemClick(v);
//            }
//        });
        holder.ivDelete.setOnClickListener(this);
        holder.ivDelete.setTag(position);

        holder.ivMove.setOnClickListener(this);
        holder.ivAdd.setOnClickListener(this);
        holder.ivMove.setTag(position);
        holder.ivAdd.setTag(position);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        itemClick.itemClick(v);
    }

    class ViewHolder {
        TextView tvName, tvNum;
        ImageView ivDelete;
        ImageView ivMove;//移除
        ImageView ivAdd;//增加

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_item_init_name);
            tvNum = (TextView) view.findViewById(R.id.tv_item_init_num);
            ivDelete = (ImageView) view.findViewById(R.id.iv_item_init_delete);
            ivMove = (ImageView) view.findViewById(R.id.iv_reduce);
            ivAdd = (ImageView) view.findViewById(R.id.iv_add);
        }
    }

    public interface IItemClick {
        void itemClick(View view);
    }
}
