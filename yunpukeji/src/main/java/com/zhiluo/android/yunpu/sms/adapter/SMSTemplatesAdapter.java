package com.zhiluo.android.yunpu.sms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.sms.jsonbean.SMSJsonBean;

import java.util.List;

/**
 * 短信模板列表适配器
 * Created by Cheng on 2017/2/14.
 */
public class SMSTemplatesAdapter extends BaseAdapter
{

    private Context mContext;
    private List<SMSJsonBean.DataBean.TempClassListBean> mTempClassList;

    public SMSTemplatesAdapter(Context context, List<SMSJsonBean.DataBean.TempClassListBean> tempClassList)
    {
        this.mContext = context;
        this.mTempClassList = tempClassList;
    }

    @Override
    public int getCount()
    {
        return mTempClassList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sms_templates_adapter, parent,false);
            holder = new ViewHolder();
            holder.tv_templates_list_name_member = (TextView) convertView.findViewById(R.id.tv_templates_list_name_member);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_templates_list_name_member.setText(mTempClassList.get(position).getTC_ClassName());

        return convertView;
    }

    class ViewHolder
    {
        TextView tv_templates_list_name_member;
    }
}
