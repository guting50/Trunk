package com.zhiluo.android.yunpu.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DrawerData;

import java.util.List;

/**
 * 我的工具
 */
public class MyUtilAdapter extends BaseAdapter {
    private Context context;
    List<DrawerData> list;
    private int flag;
    private MyUtilItemClick mItemClick;

    public MyUtilAdapter(Context context, List<DrawerData> list, int flag, MyUtilItemClick mItemClick) {
        this.context = context;
        this.list = list;
        this.flag = flag;
        this.mItemClick = mItemClick;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
        View view = convertView;
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_more_gridview, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.ivIcon.setImageResource(list.get(position).getImage());
        holder.textView.setText(list.get(position).getItem());
        if (flag == 1) {
            holder.ivMove.setVisibility(View.VISIBLE);
            holder.ivMove.setImageResource(R.drawable.more_move);
        } else {
            holder.ivMove.setVisibility(View.INVISIBLE);
        }
        holder.ivMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClick.myClick(v);
            }
        });
        holder.ivMove.setTag(position);
        return view;
    }

    class ViewHolder {
        public ImageView ivIcon, ivMove;
        public TextView textView;

        public ViewHolder(View view) {
            ivIcon = (ImageView) view.findViewById(R.id.item_homepage_iv);
            textView = (TextView) view.findViewById(R.id.item_homepage_tv);
            ivMove = (ImageView) view.findViewById(R.id.iv_item_move);
        }
    }

    public interface MyUtilItemClick {
        void myClick(View view);
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }
}
