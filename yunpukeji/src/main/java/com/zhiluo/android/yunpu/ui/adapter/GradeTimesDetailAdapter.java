package com.zhiluo.android.yunpu.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.gift.bean.ChargeListBean;
import com.zhiluo.android.yunpu.member.consume.adapter.JiCiConsumeAdapter;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;

import java.util.List;

/**
 * Created by ZPH on 2019-01-15.
 */

public class GradeTimesDetailAdapter extends BaseAdapter {
    private Context mContext;
    private DataBean mList;

    public GradeTimesDetailAdapter(Context mContext, DataBean list){
        this.mContext = mContext;
        this.mList = list;
    }


    @Override
    public int getCount() {
        return mList.getInitServiceList().size();
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
            view = LayoutInflater.from(mContext).inflate(R.layout.grade_times_detail_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();

        }
        StringBuilder mContent = new StringBuilder("");

        DataBean.InitServiceListBean bean = mList.getInitServiceList().get(position);
        holder.tvGoodsName.setText(bean.getSG_Name());//商品名称
        mContent.append(bean.getSR_Number() + "次");
        if (bean.getSR_Timer() > 0) {
            mContent.append("限时");
            switch (bean.getSR_TimeUnit()) {
                case 1:
                    mContent.append(bean.getSR_Timer() + "天");
                    break;
                case 2:
                    mContent.append(bean.getSR_Timer() + "月");
                    break;
                case 3:
                    mContent.append(bean.getSR_Timer() + "年");
                    break;
            }
        }
        holder.tvLeftCounts.setText(mContent.toString());


        return view;
    }

    class ViewHolder{

        private TextView tvLeftCounts,  tvGoodsName;

        public ViewHolder (View view){
            tvLeftCounts = view.findViewById(R.id.tv_left_counts);
            tvGoodsName = view.findViewById(R.id.tv_type);



        }
    }
}
