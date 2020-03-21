package com.zhiluo.android.yunpu.gift.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.gift.bean.GiftListBean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public class GiftListAdapter extends BaseAdapter {
    private Context context;
     List<GiftListBean.DataBean.DataListBean>list;

    public GiftListAdapter(Context context, List<GiftListBean.DataBean.DataListBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list ==null? 0: list.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_giftlist_lv, null,false);
            viewHodler = new ViewHodler(view);
            view.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) view.getTag();
        }
        GiftListBean.DataBean.DataListBean bean=list.get(position);
        viewHodler.tvgiftname.setText("礼品名称:"+bean.getGM_Name());
        viewHodler.tvgiftnum.setText("礼品数量:"+bean.getGM_Amount());
        viewHodler.tvgiftintegral.setText("所需积分:"+bean.getGM_Integral());
        viewHodler.tvgifttype.setText("礼品分类:"+bean.getGT_Name());
           return view;
    }



    class ViewHodler{
        public TextView tvgiftname;
        public TextView tvgiftnum;
        public TextView tvgiftintegral;
        public TextView tvgifttype;
        public ViewHodler(View view) {
            tvgiftname= (TextView) view.findViewById(R.id.tv_giftname_lv);
            tvgiftnum= (TextView) view.findViewById(R.id.tv_giftnum_lv);
            tvgiftintegral= (TextView) view.findViewById(R.id.tv_giftintegral_lv);
            tvgifttype= (TextView) view.findViewById(R.id.tv_gifttype_lv);
        }
    }
}
