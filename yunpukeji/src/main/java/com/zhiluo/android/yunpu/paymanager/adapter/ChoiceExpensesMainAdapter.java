package com.zhiluo.android.yunpu.paymanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.mvp.model.GoodsType;
import com.zhiluo.android.yunpu.paymanager.bean.ChoiceExpensesTypeBean;
import com.zhiluo.android.yunpu.ui.adapter.ChoiceTypeMainAdapter;

import java.util.List;

/**
 * Created by ZPH on 2019-05-22.
 */

public class ChoiceExpensesMainAdapter extends BaseAdapter {


    private Context mContext;
    private List<ChoiceExpensesTypeBean.DataBean> mParentTypeList;
//    private ICbCheck mCbCheck;


    public ChoiceExpensesMainAdapter(Context mContext, List<ChoiceExpensesTypeBean.DataBean> mParentTypeList) {
        this.mContext = mContext;
        this.mParentTypeList = mParentTypeList;
    }

    @Override
    public int getCount() {
        return mParentTypeList.size();
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_choice_type_main, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(mParentTypeList.get(position).getET_Name());
        if (mParentTypeList.get(position).isChecked()) {
            holder.tvName.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            // holder.tvName.setTextColor(mContext.getResources().getColor(R.color.yunpu));
            holder.tvFlags.setVisibility(View.VISIBLE);
        } else {
            holder.tvName.setBackgroundColor(mContext.getResources().getColor(R.color.background));
            holder.tvName.setTextColor(mContext.getResources().getColor(R.color.color_main_text_black));
            holder.tvFlags.setVisibility(View.INVISIBLE);

        }
        return view;
    }


    class ViewHolder {
        TextView tvName, tvFlags;

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_item_type_main);
            tvFlags = (TextView) view.findViewById(R.id.left_flags);
        }
    }
}
