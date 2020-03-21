package com.zhiluo.android.yunpu.member.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.member.jsonbean.EmployeeJsonBean;

import java.util.ArrayList;
import java.util.List;

/**
 * iauthor：Cheng
 * date: 2017/6/10 16:05
 * email：jasoncheng9111@gmail.com
 */
public class EmployeeListAdapter extends BaseAdapter
{

    private Context mContext;
    private List<EmployeeJsonBean.DataBean> list_employee = new ArrayList<>();

    public EmployeeListAdapter(Context context, List<EmployeeJsonBean.DataBean> list_employee)
    {
        this.mContext = context;
        this.list_employee = list_employee;
    }

    @Override
    public int getCount()
    {
        return list_employee.size();
    }

    @Override
    public Object getItem(int i)
    {
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ViewHolder viewHolder = null;
        if (view == null)
        {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_employee,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.tv_employee_name = (TextView) view.findViewById(R.id.tv_employee_name);
            viewHolder.tv_employee_number = (TextView) view.findViewById(R.id.tv_employee_number);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tv_employee_number.setText(list_employee.get(i).getEM_Code().toString());
        viewHolder.tv_employee_name.setText(list_employee.get(i).getEM_Name().toString());
        return view;
    }

    class ViewHolder
    {
        TextView tv_employee_number;
        TextView tv_employee_name;
    }
}
