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
 * Created by Administrator on 2016/9/6.
 */
public class HomePageGridViewAdpter extends BaseAdapter {
    private Context context;
    List<DrawerData> list;

    public HomePageGridViewAdpter(Context context, List<DrawerData> list) {
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
        ViewHodler viewHodler = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_homepage_gridview, null);
            viewHodler = new ViewHodler(view);
            view.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) view.getTag();
        }
        viewHodler.imageView1.setImageResource(list.get(position).getImage());
        viewHodler.textView.setText(list.get(position).getItem());
        return view;
    }

    class ViewHodler {
        public ImageView imageView1;
        public TextView textView;

        public ViewHodler(View view) {
            imageView1 = (ImageView) view.findViewById(R.id.item_homepage_iv);
            textView = (TextView) view.findViewById(R.id.item_homepage_tv);
        }
    }
}
