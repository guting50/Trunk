package com.zhiluo.android.yunpu.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.ui.bean.LoadImageBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by songxiaotao on 2017/8/16.
 */

public class ImageLoadAdapter extends BaseAdapter {
    private List<LoadImageBean> list;
    private Context context;
    private LayoutInflater inflater;

    public ImageLoadAdapter(Context context, List<LoadImageBean> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null) {
            view = inflater.inflate(R.layout.adapter_image, null);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        LoadImageBean ts = list.get(i);


//        if (ts.getImageAddress().contains("http")) {
//            Glide.with(context).load(ts.getImageAddress()).into(vh.mIvShop);
//        } else {
//            String url = ts.getImageAddress();
//            if (url.contains("../")) {
//                String[] s = url.split("\\.\\./");
//                url = s[2];
//            }
//
//            Glide.with(context).load(HttpAPI.API().MAIN_DOMAIN + "/" + url).into(vh.mIvShop);
//        }
        try {
            if (ts.getImageAddress().equals("")){
                Glide.with(context).load(R.drawable.add_img).into(vh.mIvShop);
            }else {
                Glide.with(context).load(ts.getImageAddress()).error(R.drawable.add_img).into(vh.mIvShop);
            }

        } catch (Exception e) {
            Glide.with(context).load(R.drawable.add_img).into(vh.mIvShop);
        }

        if (ts.isStar()){
            vh.tvStar.setVisibility(View.VISIBLE);
        }else {
            vh.tvStar.setVisibility(View.GONE);
        }

        vh.tvName.setText(ts.getNamme());
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.iv_image)
        ImageView mIvShop;
        @Bind(R.id.tv_star)
        TextView tvStar;
        @Bind(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
