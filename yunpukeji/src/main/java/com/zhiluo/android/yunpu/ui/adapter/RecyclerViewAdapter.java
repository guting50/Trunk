package com.zhiluo.android.yunpu.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.mvp.model.GoodsType;

import java.util.List;
import drawthink.expandablerecyclerview.adapter.BaseRecyclerViewAdapter;
import drawthink.expandablerecyclerview.bean.RecyclerViewData;
import drawthink.expandablerecyclerview.holder.BaseViewHolder;

/**
 *
 * 作者：罗咏哲 on 2017/10/27 16:51.
 * 邮箱：137615198@qq.com
 */

public class RecyclerViewAdapter extends BaseRecyclerViewAdapter<String,String, RecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;

    public RecyclerViewAdapter(Context ctx, List<RecyclerViewData> datas) {
        super(ctx, datas);
        mInflater = LayoutInflater.from(ctx);
        mContext = ctx;
    }

    @Override
    public View getGroupView(ViewGroup viewGroup) {
        return mInflater.inflate(R.layout.recyclerview_group_item_layout, viewGroup, false);
    }

    @Override
    public View getChildView(ViewGroup viewGroup) {
        return mInflater.inflate(R.layout.recyclerview_child_item_layout, viewGroup, false);
    }

    @Override
    public ViewHolder createRealViewHolder(Context context, View view, int i) {
        return new ViewHolder(context, view, i);
    }

    @Override
    public void onBindGroupHolder(ViewHolder viewHolder, int i, int i1, String data) {
        viewHolder.tvGroup.setText(data);
    }

    @Override
    public void onBindChildpHolder(ViewHolder viewHolder, int i, int i1, int i2, String data) {
        viewHolder.tvChild.setText(data);
    }

    class ViewHolder extends BaseViewHolder {
        public TextView tvGroup;
        public TextView tvChild;

        public ViewHolder(Context ctx, View itemView, int viewType) {
            super(ctx, itemView, viewType);
            tvGroup = (TextView) itemView.findViewById(R.id.tv_recly_item_group);
            tvChild = (TextView) itemView.findViewById(R.id.tv_recly_item_child);
        }

        @Override
        public int getChildViewResId() {
            return R.id.item_child;
        }

        @Override
        public int getGroupViewResId() {
            return R.id.item_group;
        }
    }

    @Override
    public boolean canExpandAll() {
        return false;
    }
}
