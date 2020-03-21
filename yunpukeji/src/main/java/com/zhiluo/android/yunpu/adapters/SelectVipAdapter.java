package com.zhiluo.android.yunpu.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.zhiluo.android.yunpu.R;
import java.util.List;

/**
 * Created by ${YSL} on 2018-04-08.
 */

public class SelectVipAdapter extends RecyclerView.Adapter<SelectVipAdapter.MyHolders> {
    private Context mContext;
    private List<String> mDatas;

    public SelectVipAdapter(Context ctx, List<String> datas) {
        this.mContext = ctx;
        this.mDatas = datas;
    }

    @Override
    public MyHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_shaixuan_adapter, null);
        MyHolders viewHolder = new MyHolders(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyHolders holder, int position) {
        holder.mNanme.setText(mDatas.get(position));

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyHolders extends ViewHolder {
        TextView mNanme;

        public MyHolders(View itemView) {
            super(itemView);
            mNanme = itemView.findViewById(R.id.operator_name);
        }
    }
}
