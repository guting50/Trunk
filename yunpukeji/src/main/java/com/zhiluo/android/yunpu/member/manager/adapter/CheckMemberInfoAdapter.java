package com.zhiluo.android.yunpu.member.manager.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 会员列表Adapter
 * 作者：罗咏哲 on 2017/7/18 15:00.
 * 邮箱：137615198@qq.com
 */

public class CheckMemberInfoAdapter extends BaseAdapter {
    private Context mContext;
    private List<AllMemberListBean.DataBean.DataListBean> mMemberList;

    public CheckMemberInfoAdapter(Context mContext, List<AllMemberListBean.DataBean.DataListBean> mMemberList) {
        this.mContext = mContext;
        this.mMemberList = mMemberList;
    }

    @Override
    public int getCount() {
        return mMemberList == null ? 0 : mMemberList.size();
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_check_member, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        //会员头像
        if (mMemberList.get(position).getVIP_HeadImg() != null) {
            String uri = null;
            if (mMemberList.get(position).getVIP_HeadImg().contains("http")) {
                uri = mMemberList.get(position).getVIP_HeadImg();
            } else {
                uri = HttpAPI.API().MAIN_DOMAIN + mMemberList.get(position).getVIP_HeadImg();
            }
            Glide.with(mContext).load(Uri.parse(uri)).into(holder.ivHeadImage);
        } else {
            holder.ivHeadImage.setImageResource(R.drawable.member_head_nohead);
        }
        //姓名
        if (mMemberList.get(position).getVIP_Name() != null) {
            holder.tvName.setText(mMemberList.get(position).getVIP_Name());
        } else {
            holder.tvName.setText(mMemberList.get(position).getVCH_Card());
        }
        //电话
        if (mMemberList.get(position).getVIP_CellPhone() != null) {
            holder.tvPhone.setText(mMemberList.get(position).getVIP_CellPhone());
        }
        //余额
        holder.tvSurplusMoney.setText(Decima2KeeplUtil.stringToDecimal(mMemberList.get(position)
                .getMA_AvailableBalance() + ""));
        //积分
        holder.tvPoints.setText(Decima2KeeplUtil.stringToDecimal(mMemberList.get(position)
                .getMA_AvailableIntegral() + ""));
        return view;
    }

    class ViewHolder {
        ShapedImageView ivHeadImage;//头像
        TextView tvName;//姓名
        TextView tvPhone;//电话
        TextView tvSurplusMoney;//余额
        TextView tvPoints;//积分

        public ViewHolder(View view) {
            ivHeadImage = (ShapedImageView) view.findViewById(R.id.civ_item_check_member_head);
            tvName = (TextView) view.findViewById(R.id.tv_item_check_member_name);
            tvPhone = (TextView) view.findViewById(R.id.tv_item_check_member_phone);
            tvSurplusMoney = (TextView) view.findViewById(R.id.tv_item_check_member_surplus);
            tvPoints = (TextView) view.findViewById(R.id.tv_item_check_member_point);
        }
    }
}
