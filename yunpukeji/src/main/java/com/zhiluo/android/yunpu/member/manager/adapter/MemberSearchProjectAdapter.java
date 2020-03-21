package com.zhiluo.android.yunpu.member.manager.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.member.manager.bean.MemberProjectListBean;


/**
 * Created by ZPH on 2019-05-15.
 */

public class MemberSearchProjectAdapter extends BaseAdapter {

    private Context mContext;
    private MemberProjectListBean mList;

    public MemberSearchProjectAdapter(Context mContext, MemberProjectListBean memberProjectList) {
        this.mContext = mContext;
        this.mList = memberProjectList;
    }

    @Override
    public int getCount() {
        return mList.getData().getDataList() == null ? 0 : mList.getData().getDataList().size();
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_check_project, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        MemberProjectListBean.DataBean.DataListBean bean = mList.getData().getDataList().get(position);

        //项目名
        holder.tvName.setText(bean.getMCP_Name() + "");
        //签到积分
        holder.tvPoints.setText("签到积分："+bean.getMCP_Integral());


        return view;
    }

    public void setParam(MemberProjectListBean memberProjectList) {
        this.mList = memberProjectList;
    }

    class ViewHolder {

        TextView tvName;//姓名
        TextView tvPoints;//积分


        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_item_check_project_name);
            tvPoints = (TextView) view.findViewById(R.id.tv_item_check_project_point);
        }
    }
}
