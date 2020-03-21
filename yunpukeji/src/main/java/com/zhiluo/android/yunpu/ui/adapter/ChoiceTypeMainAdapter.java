package com.zhiluo.android.yunpu.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.mvp.model.GoodsType;
import com.zhiluo.android.yunpu.paymanager.bean.ChoiceExpensesTypeBean;

import java.util.List;

/**
 * 选择商品类型主分类Adapter
 * 作者：罗咏哲 on 2017/11/29 15:45.
 * 邮箱：137615198@qq.com
 */

public class ChoiceTypeMainAdapter extends BaseAdapter {
    private Context mContext;
    private List<GoodsType.DataBean> mParentTypeList;
//    private ICbCheck mCbCheck;


    public ChoiceTypeMainAdapter(Context mContext, List<GoodsType.DataBean> mParentTypeList) {
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
        holder.tvName.setText(mParentTypeList.get(position).getPT_Name());
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

//    interface ICbCheck {
//        void check();
//    }
}
