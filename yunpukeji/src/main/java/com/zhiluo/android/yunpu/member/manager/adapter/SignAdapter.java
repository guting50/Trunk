package com.zhiluo.android.yunpu.member.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.consume.adapter.StaffCommAdapter;
import com.zhiluo.android.yunpu.member.manager.bean.LabelBean;

import java.util.List;

/**
 * Created by YSL on 2018-10-18.
 */

public class SignAdapter extends BaseAdapter {
    private Context context;
    private List<LabelBean> mLabList;
    private ICallBack mICallBack;

    public SignAdapter(Context context,List<LabelBean> mLabList,ICallBack mICallBack){

        this.context = context;
        this.mLabList = mLabList;
        this.mICallBack = mICallBack;

    }
    @Override
    public int getCount() {
        return mLabList==null?0:mLabList.size();
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
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_staff_commission, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tvName.setText(mLabList.get(position).getItemName());
        if (mLabList.get(position).isChecked()) {
            holder.ivCheck.setImageResource(R.drawable.check_box_circle_checked);
        } else {
            holder.ivCheck.setImageResource(R.drawable.shape_check_box_empty);
        }
        holder.ivCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mICallBack.click(v);
            }
        });
        holder.ivCheck.setTag(position);


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
        void click(View view);
    }


}
