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

public class PowerListAdapter extends RecyclerView.Adapter<PowerListAdapter.MyHolders> {
    private Context mContext;
    private List<String> mList;

    public PowerListAdapter(Context ctx, List<String> datas) {
        this.mContext = ctx;
        this.mList = datas;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public MyHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_power_list, null);
        MyHolders viewHolder = new MyHolders(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyHolders holder, int position) {
        holder.tv_name.setText(mList.get(position));
    }

    class MyHolders extends ViewHolder {
        TextView tv_name;

        public MyHolders(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
