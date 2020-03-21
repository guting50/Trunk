package com.zhiluo.android.yunpu.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.TCBean;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.util.List;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;

/**
 * 作者：罗咏哲 on 2018/1/26 13:34.
 * 邮箱：137615198@qq.com
 */

public class TCManagerAdapter extends BaseAdapter {
    private Context mContext;
    private List<TCBean> mList;

    public TCManagerAdapter(Context mContext, List<TCBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tc_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //设置图片
        if (mList.get(position).getPC_Image() != null) {
            if (mList.get(position).getPC_Image().contains("http")) {
                Glide.with(mContext).load(mList.get(position).getPC_Image()).into(holder.ivPicture);
            } else {
                String url = mList.get(position).getPC_Image();
                if (url.contains("../")) {
                    String[] s = url.split("\\.\\./");
                    url = s[2];
                }
                Glide.with(mContext).load(HttpAPI.API().MAIN_DOMAIN + "/" + url).error(R.drawable.ysl_goods).
                        into(holder.ivPicture);
            }
        } else {
            holder.ivPicture.setImageResource(R.drawable.ysl_goods);
        }
        holder.tvName.setText(mList.get(position).getPC_Name());
        holder.tvPrice.setText("¥"+Decima2KeeplUtil.stringToDecimal("" + mList.get(position).getPC_Price()));
        holder.tvCode.setText(mList.get(position).getPC_Code());
        return convertView;
    }

    class ViewHolder {
        ShapedImageView ivPicture;
        TextView tvName;
        TextView tvPrice;
        TextView tvCode;

        ViewHolder(View view) {
            ivPicture = (ShapedImageView) view.findViewById(R.id.iv_item_tc_picture);
            tvName = (TextView) view.findViewById(R.id.tv_item_tc_name);
            tvPrice = (TextView) view.findViewById(R.id.tv_item_tc_price);
            tvCode = (TextView) view.findViewById(R.id.tv_item_tc_code);
        }
    }
}
