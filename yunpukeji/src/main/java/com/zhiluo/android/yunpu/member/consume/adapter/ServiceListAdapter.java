package com.zhiluo.android.yunpu.member.consume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.gift.bean.ChargeListBean;

import java.util.List;

/**
 * 作者：罗咏哲 on 2017/8/23 15:18.
 * 邮箱：137615198@qq.com
 */

public class ServiceListAdapter extends BaseAdapter {
    private Context context;
    List<ChargeListBean.DataBean> list;

    public ServiceListAdapter(Context context, List<ChargeListBean.DataBean> list) {
        this.context = context;
        this.list = list;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHodler viewHodler = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_userchargelist_lv, null, false);
            viewHodler = new ViewHodler(view);
            view.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) view.getTag();
        }
        ChargeListBean.DataBean bean = list.get(position);
        viewHodler.ivPicture.setImageResource(R.drawable.defalut_goods);
        viewHodler.tvgiftname.setText(bean.getSG_Name());
        viewHodler.tvgiftnum.setText(bean.getMCA_HowMany() + "");
        viewHodler.tvgiftintegral.setText("总次数:" + bean.getMCA_TotalCharge() + "");
        viewHodler.tvgifttype.setText("服务类别:" + bean.getSGC_ClasName());
        return view;
    }

    public void setdata(List<ChargeListBean.DataBean> chargeList) {
        this.list = chargeList;
    }


    class ViewHodler {
        ShapedImageView ivPicture;
        public TextView tvgiftname;
        public TextView tvgiftnum;
        public TextView tvgiftintegral;
        public TextView tvgifttype;
        public TextView tvselnum;

        public ViewHodler(View view) {
            ivPicture = (ShapedImageView) view.findViewById(R.id.iv_gift);
            tvgiftname = (TextView) view.findViewById(R.id.tv_giftname_lv);
            tvgiftnum = (TextView) view.findViewById(R.id.tv_giftnum_lv);
            tvgiftintegral = (TextView) view.findViewById(R.id.tv_giftintegral_lv);
            tvgifttype = (TextView) view.findViewById(R.id.tv_gifttype_lv);
        }
    }
}
