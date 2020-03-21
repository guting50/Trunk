package com.zhiluo.android.yunpu.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jauker.widget.BadgeView;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.home.jsonbean.MessageCenterJsonBean;

import java.util.List;

/**
 * 消息中心适配器
 * iauthor：Cheng
 * date: 2017/5/11 15:50
 * email：jasoncheng9111@gmail.com
 */
public class MessageCenterAdapter extends BaseAdapter {
    private Context mContext;
    private MessageCenterJsonBean mMessageBean;

    public MessageCenterAdapter(Context mContext, MessageCenterJsonBean mMessageBean) {
        this.mContext = mContext;
        this.mMessageBean = mMessageBean;
    }

    @Override
    public int getCount() {
        return mMessageBean.getData().getDataList().size() > 0 ? mMessageBean.getData().getDataList().size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        List<MessageCenterJsonBean.DataBean.DataListBean> mMessageList = mMessageBean.getData().getDataList();
        View view;
        ViewHolder holder = null;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_message_center, viewGroup,
                    false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        if (mMessageList.get(i).getPopState() == 1) {
            holder.tvPoint.setVisibility(View.GONE);
        } else {
         //   holder.tvPoint.setVisibility(View.VISIBLE);
            holder.tvTitle.setTextColor(Color.parseColor("#000000"));
        }
        holder.tvTitle.setText(mMessageList.get(i).getTitle().trim());
        if (mMessageList.get(i).getCreateTime() != null && mMessageList.get(i).getCreateTime().length() > 10) {
            holder.tvDate.setText(mMessageList.get(i).getCreateTime().substring(0, 10));
        }
        return view;
    }

    class ViewHolder {
        TextView tvPoint;
        TextView tvTitle;
        TextView tvDate;
        TextView tvAbstract;

        public ViewHolder(View view) {
            tvPoint = (TextView) view.findViewById(R.id.item_message_center_point);
            tvTitle = (TextView) view.findViewById(R.id.item_message_center_title);
            tvDate = (TextView) view.findViewById(R.id.tv_message_center_date);
            tvAbstract = (TextView) view.findViewById(R.id.tv_message_center_abstract);
        }
    }
}
