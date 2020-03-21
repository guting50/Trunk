package com.zhiluo.android.yunpu.sms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.member.manager.activity.MemberListActivity;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.sms.activity.ChooseSMSContentsActivity;
import com.zhiluo.android.yunpu.sms.activity.ChooseSMSTemplatesActivity;
import com.zhiluo.android.yunpu.sms.activity.SendMsgActivity;
import com.zhiluo.android.yunpu.utils.ActivityManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhiluo.android.yunpu.R.id.btn_send_smscontentsadapter;

/**
 * 短信内容列表适配器
 * Created by Cheng on 2017/2/14.
 */
public class SMSContentsAdapter extends BaseAdapter
{

    private Context mContext;
    private List<String> mContentMsgs;
    private Map<String, Integer> mClickButtonTag = new HashMap<>();
    private List<String> mPlist;
    private List<AllMemberListBean.DataBean.DataListBean> namelist;
    public SMSContentsAdapter(Context context, List<String> contents, ArrayList<String> plist,List<AllMemberListBean.DataBean.DataListBean> namelist)
    {
        this.mContext = context;
        this.mContentMsgs = contents;
        this.mPlist = plist;
        this.namelist = namelist;

    }

    @Override
    public int getCount()
    {
        return mContentMsgs.size();
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
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sms_contents_adapter, parent, false);
            holder = new ViewHolder();
            holder.tv_contents_smscontentsadapter = (TextView) convertView.findViewById(R.id.tv_contents_smscontentsadapter);
            holder.btn_send_smscontentsadapter = (Button) convertView.findViewById(btn_send_smscontentsadapter);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        if (mContentMsgs.get(position) != null)
        {
            //显示短信内容
            holder.tv_contents_smscontentsadapter.setText("    " + mContentMsgs.get(position));
            //给发送按钮添加对应Item的Tag标记
            holder.btn_send_smscontentsadapter.setTag(position);
            mClickButtonTag.put(String.valueOf(holder.btn_send_smscontentsadapter.getTag()), position);
        }

        //点击发送按钮将短信内容传递到发送短信界面
        final ViewHolder finalHolder = holder;
        holder.btn_send_smscontentsadapter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int position = mClickButtonTag.get(String.valueOf(finalHolder.btn_send_smscontentsadapter.getTag()));
                SendMsgActivity.fromSMSContentsAdapter(mContext,mContentMsgs.get(position), (ArrayList<String>) mPlist,namelist);
                mClickButtonTag.clear();
                ActivityManager.getInstance().exit();


            }
        });
        return convertView;
    }

    class ViewHolder
    {
        TextView tv_contents_smscontentsadapter;
        Button btn_send_smscontentsadapter;
    }
}
