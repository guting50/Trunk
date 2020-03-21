package com.zhiluo.android.yunpu.consume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.consume.jsonbean.StaffCommissionBean;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;

import java.util.List;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 作者：罗咏哲 on 2017/7/6 18:00.
 * 邮箱：137615198@qq.com
 */

public class StaffCommAdapter extends BaseAdapter {
    private Context mContext;
    private List<ReportMessageBean.DataBean.EmplistBean> mStaffList;
    private ICallBack mICallBack;

    public StaffCommAdapter(Context mContext, List<ReportMessageBean.DataBean.EmplistBean> mStaffList, ICallBack mICallBack) {
        this.mContext = mContext;
        this.mStaffList = mStaffList;
        this.mICallBack = mICallBack;
    }

    @Override
    public int getCount() {
        Li("mStaffList--"+mStaffList.size());
        return mStaffList == null ? 0 : mStaffList.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_staff_commission, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(mStaffList.get(i).getEM_Name());
        if (mStaffList.get(i).isCheck()) {
            holder.ivCheck.setImageResource(R.drawable.check_box_circle_checked);
        } else {
            holder.ivCheck.setImageResource(R.drawable.shape_check_box_empty);
        }
        holder.ivCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mICallBack.click(v,i);
            }
        });
        holder.ivCheck.setTag(i);
        return view;
    }

    class ViewHolder {
        TextView tvName;
        ImageView ivCheck;

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_item_staff_name);
            ivCheck = (ImageView) view.findViewById(R.id.iv_item_staff);
        }
    }

    /**
     * 自定义接口，用于回掉item中的点击事件到Activity
     */
    public interface ICallBack {
        void click(View view,int postion);
    }
}
