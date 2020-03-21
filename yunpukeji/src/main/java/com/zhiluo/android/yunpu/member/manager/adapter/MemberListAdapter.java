package com.zhiluo.android.yunpu.member.manager.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.util.List;

/**
 * 会员列表适配器
 * Created by Administrator on 2016/9/6.
 */
public class MemberListAdapter extends BaseAdapter {
    private Context context;
    List<AllMemberListBean.DataBean.DataListBean> list;
    ImageClick mImageClick;

    public MemberListAdapter(Context context, List<AllMemberListBean.DataBean.DataListBean> list, ImageClick mImageClick) {
        this.context = context;
        this.list = list;
        this.mImageClick = mImageClick;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_memberlistactivity, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if (list.size() > 0) {
            if (list.get(position).getVIP_HeadImg() != null) {
                String uri;
                if (list.get(position).getVIP_HeadImg().contains("http")) {
                    uri = list.get(position).getVIP_HeadImg();
                } else {
                    String url = list.get(position).getVIP_HeadImg();
                    if (url.contains("../")) {
                        String[] s = url.split("\\.\\./");
                        url = s[2];
                    }
                    uri = HttpAPI.API().MAIN_DOMAIN + "/" + url;
                }
                Glide.with(context).load(Uri.parse(uri)).into(viewHolder.ivHeadPhoto);
            } else {
                viewHolder.ivHeadPhoto.setImageResource(R.drawable.member_head_nohead);
            }
            if (list.get(position).getVIP_Name() != null && !list.get(position).getVIP_Name().isEmpty()) {
                viewHolder.tvName.setText(list.get(position).getVIP_Name());
            } else {
                viewHolder.tvName.setText(list.get(position).getVCH_Card());
            }
            viewHolder.tvIntegral.setText(Decima2KeeplUtil.stringToDecimal(list.get(position).getMA_AvailableIntegral() + ""));
            viewHolder.tvBalance.setText(Decima2KeeplUtil.stringToDecimal(list.get(position).getMA_AvailableBalance() + ""));
            viewHolder.tvCellPhone.setText(list.get(position).getVIP_CellPhone());
            viewHolder.ivChecked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mImageClick.click(v);
                }
            });
//            //设置左边选择按钮可见
//            if (list.get(position).getLongClick()) {
//                viewHolder.ivChecked.setVisibility(View.VISIBLE);
//            }
            if (list.get(position).isChecked()) {
                viewHolder.ivChecked.setImageResource(R.drawable.check_box_circle_checked);
            } else {
                viewHolder.ivChecked.setImageResource(R.drawable.shape_check_box_empty);
            }
            viewHolder.ivChecked.setTag(position);
        }

        return view;
    }

    public void setParams(List<AllMemberListBean.DataBean.DataListBean> mMemberList) {
        list = mMemberList;

    }

    class ViewHolder {
        private ImageView ivHeadPhoto;//头像
        public TextView tvName;
        private TextView tvCellPhone;
        private TextView tvBalance;
        private TextView tvIntegral;
        private ImageView ivChecked;

        public ViewHolder(View view) {
            ivHeadPhoto = view.findViewById(R.id.iv_vip_photo);
            tvName = (TextView) view.findViewById(R.id.tv_vipname);
            tvCellPhone = (TextView) view.findViewById(R.id.tv_vipcellphone);
            tvBalance = (TextView) view.findViewById(R.id.tv_vipyue);
            tvIntegral = (TextView) view.findViewById(R.id.tv_vipjifen);
            ivChecked = (ImageView) view.findViewById(R.id.iv_checked);
        }
    }


    public interface ImageClick {
        void click(View v);
    }
}
