package com.zhiluo.android.yunpu.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.member.manager.bean.MemberLabel;


/**
 * 作者：罗咏哲 on 2018/1/17 10:27.
 * 邮箱：137615198@qq.com
 */

public class LabelListAdapter extends BaseAdapter {
    private Context mContext;
    private MemberLabel data;

    public LabelListAdapter(Context mContext, MemberLabel data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.getData().size();
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
            view = LayoutInflater.from(mContext).inflate(R.layout.member_grade_item_layout, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
//        LinkedTreeMap tm = (LinkedTreeMap)data;
//        Iterator it = tm.keySet().iterator();
//        while (it.hasNext()) {
//            String key = (String) it.next();
//            String value = (String)tm.get(key);
//        }

        holder.tvName.setText(data.getData().get(position).getML_Name());
        return view;
    }

    class ViewHolder {
        TextView tvName;

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_grade_item_name);
        }
    }
}
