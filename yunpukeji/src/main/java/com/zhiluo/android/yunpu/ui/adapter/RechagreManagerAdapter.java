package com.zhiluo.android.yunpu.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.RechargeBean;
import com.zhiluo.android.yunpu.yslutils.DataUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 作者：罗咏哲 on 2018/1/17 10:27.
 * 邮箱：137615198@qq.com
 */

public class RechagreManagerAdapter extends BaseAdapter {
    private Context mContext;
    private List<RechargeBean> mList;

    public RechagreManagerAdapter(Context mContext, List<RechargeBean> mList) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.recharge_manager_item_layout, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(mList.get(position).getRP_Name());
        switch (mList.get(position).getRP_ValidType()) {//0 永久有效；1 固定时间；2 一周的某一天或几天；3 每月某一天或几天；4 会员生日
            case 0://0 永久有效
                holder.tvOverdueFlag.setVisibility(View.INVISIBLE);
                break;
            //固定时间段有效
            case 1:
                if (!DataUtils.isOverTime(mList.get(position).getRP_ValidStartTime())&&DataUtils.isOverTime(mList.get(position).getRP_ValidEndTime())){
                    holder.tvOverdueFlag.setVisibility(View.INVISIBLE);
                } else {
                    holder.tvOverdueFlag.setVisibility(View.VISIBLE);
                }
                break;
            case 2://一周的某一天或几天有效
                if (mList.get(position).getRP_ValidWeekMonth().contains(DataUtils.getW())) {
                    holder.tvOverdueFlag.setVisibility(View.INVISIBLE);
                } else {
                    holder.tvOverdueFlag.setVisibility(View.VISIBLE);
                }
                break;
            case 3://每月某一天或几天有效
                String str = DataUtils.getD();
                if (mList.get(position).getRP_ValidWeekMonth().contains(str)) {
                    holder.tvOverdueFlag.setVisibility(View.INVISIBLE);
                } else {
                    holder.tvOverdueFlag.setVisibility(View.VISIBLE);
                }
                break;
            case 4://会员生日
                //暂时没得，就先默认有效
                break;
        }
        return view;
    }

    class ViewHolder {
        TextView tvName, tvOverdueFlag;

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_grade_item_name);
            tvOverdueFlag = (TextView) view.findViewById(R.id.tv_overdue_flag);
        }
    }

    /**
     * 判断时间是否有效
     *
     * @param lastDate
     * @return
     */
    private boolean compareDate(String lastDate) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        try {
            Date date1 = sf.parse(lastDate);
            if (date1.getTime() > curDate.getTime()) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
