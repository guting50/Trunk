package com.zhiluo.android.yunpu.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DrawerData;

import java.util.List;

/**
 * 我的工具
 */
public class SurFaceeUtilAdapter extends BaseAdapter {
    private Context context;
    List<DrawerData> list;

    public  SurFaceeUtilAdapter(Context context, List<DrawerData> list) {
        this.context = context;
        this.list = list;

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
            view = LayoutInflater.from(context).inflate(R.layout.item_surface_gridview, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.ivIcon.setImageResource(list.get(position).getImage());
        holder.textView.setText(list.get(position).getItem());

        return view;
    }

    class ViewHolder {
        public ImageView ivIcon ;
        public TextView textView;

        public ViewHolder(View view) {
            ivIcon = (ImageView) view.findViewById(R.id.item_homepage_iv);
            textView = (TextView) view.findViewById(R.id.item_homepage_tv);

        }
    }

}
