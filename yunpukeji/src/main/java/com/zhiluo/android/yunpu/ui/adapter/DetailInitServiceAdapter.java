package com.zhiluo.android.yunpu.ui.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberGrade;

import java.util.List;

/**
 * 作者：罗咏哲 on 2018/1/13 11:16.
 * 邮箱：137615198@qq.com
 */

public class DetailInitServiceAdapter extends BaseAdapter {
    private Context mContext;
    private List<DataBean.InitServiceListBean> mList;

    public DetailInitServiceAdapter(Context mContext, List<DataBean.InitServiceListBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_init_service_detail, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(mList.get(position).getSG_Name());
        holder.etCount.setText( mList.get(position).getSR_Number()+"次");
        switch (mList.get(position).getSR_TimeUnit()) {
            case 1:
                holder.etTime.setText(mList.get(position).getSR_Timer()+"天");
                break;
            case 2:
                holder.etTime.setText(mList.get(position).getSR_Timer()+"月");
                break;
            case 3:
                holder.etTime.setText(mList.get(position).getSR_Timer()+"年");
                break;
        }
        if (mList.get(position).getSR_Timer() > 0) {
            holder.tvLimit.setText("限时");
        }
        return view;
    }

    class ViewHolder {
        TextView tvName,tvLimit;
        TextView etCount, etTime;

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_item_init_service_name);
            etCount = (TextView) view.findViewById(R.id.et_item_init_service_count);
            etTime = (TextView) view.findViewById(R.id.tv_item_init_service_time);
            tvLimit= (TextView) view.findViewById(R.id.et_item_init_service_limit);
        }
    }

}
