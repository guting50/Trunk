package com.zhiluo.android.yunpu.member.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.member.manager.bean.MemberFastCheckInBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberProjectListBean;

import java.util.List;

/**
 * Created by ZPH on 2019-05-15.
 */

public class MemberCheckInListAdapter extends BaseAdapter {

    private Context mContext;
    private List<MemberFastCheckInBean.DataBean.DataListBean> mList;

    public MemberCheckInListAdapter(Context mContext, List<MemberFastCheckInBean.DataBean.DataListBean> memberchecklist) {
        this.mContext = mContext;
        this.mList = memberchecklist;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_fast_checkin, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        MemberFastCheckInBean.DataBean.DataListBean bean = mList.get(mList.size()-1-position);

        //签到时间
        holder.tvTime.setText(bean.getMCI_UpdateTime() + "");
        //会员姓名
        if (bean.getVIP_Name()!=null&&!bean.getVIP_Name().equals("")){
            holder.tvmember.setText(""+bean.getVIP_Name()+"");
        }else {
            holder.tvmember.setText(""+bean.getVCH_Card()+"");
        }

        return view;
    }


    class ViewHolder {

        TextView tvTime;//签到时间
        TextView tvmember;//会员姓名


        public ViewHolder(View view) {
            tvTime = (TextView) view.findViewById(R.id.tv_item_check_time);
            tvmember = (TextView) view.findViewById(R.id.tv_item_check_name);
        }
    }
}
