package com.zhiluo.android.yunpu.gift.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.gift.bean.GiftListBean;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.util.List;

/**
 * 礼品管理子分类列表
 * Created by 70700 on 2016/10/31.
 */
public class GiftsManagementSubCategoryAdapter extends BaseAdapter {
    private Context context;
    List<GiftListBean.DataBean.DataListBean> list;

    public GiftsManagementSubCategoryAdapter(Context context, List<GiftListBean.DataBean.DataListBean> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.item_gifts_management_subcategory, null, false);
            viewHodler = new ViewHodler(view);
            view.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) view.getTag();
        }
        GiftListBean.DataBean.DataListBean bean = list.get(position);

        if (bean.getGM_PictureUrl() != null) {
            if (bean.getGM_PictureUrl().toString().contains("http")) {
                Glide.with(context).load(bean.getGM_PictureUrl()).into(viewHodler.ivPicture);
            } else {
                String url = bean.getGM_PictureUrl();
                if (url.contains("../")) {
                    String[] s = url.split("\\.\\./");
                    url = s[2];
                }
                Glide.with(context).load(HttpAPI.API().MAIN_DOMAIN + "/"+url).into(viewHodler.ivPicture);
            }
        } else {
            viewHodler.ivPicture.setImageResource(R.drawable.defalut_goods);
        }
        viewHodler.tvgiftname.setText(bean.getGM_Name());
        viewHodler.tvgiftnum.setText("礼品数量:" + bean.getGM_Amount());
        viewHodler.tvgiftintegral.setText(Decima2KeeplUtil.stringToDecimal("" + bean.getGM_Integral()));
        return view;
    }

    class ViewHodler {
        ShapedImageView ivPicture;
        TextView tvgiftname;
        TextView tvgiftnum;
        TextView tvgiftintegral;

        public ViewHodler(View view) {
            ivPicture = (ShapedImageView) view.findViewById(R.id.iv_gift);
            tvgiftname = (TextView) view.findViewById(R.id.tv_giftname_lv);
            tvgiftnum = (TextView) view.findViewById(R.id.tv_giftnum_lv);
            tvgiftintegral = (TextView) view.findViewById(R.id.tv_giftintegral_lv);
        }
    }
}
