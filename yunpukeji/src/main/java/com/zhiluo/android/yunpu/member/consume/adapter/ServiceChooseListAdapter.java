package com.zhiluo.android.yunpu.member.consume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.goods.manager.bean.ServiceResponseListBean;

import java.util.List;

/**
 * 服务选择适配器
 * Created by 70700 on 2016/10/28.
 */
public class ServiceChooseListAdapter extends BaseAdapter {
    private Context context;
    private List<ServiceResponseListBean.DataBean.DataListBean> dataListBeen;

    public ServiceChooseListAdapter(Context context, List<ServiceResponseListBean.DataBean.DataListBean> dataListBeen) {
        this.context = context;
        this.dataListBeen = dataListBeen;
    }

    @Override
    public int getCount() {
        return dataListBeen == null ? 0 : dataListBeen.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_service_choose_list, null, false);
            viewHodler = new ViewHodler(view);
            view.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) view.getTag();
        }
        ServiceResponseListBean.DataBean.DataListBean bean = dataListBeen.get(position);
        if (bean.getPM_BigImg() != null) {
            if (bean.getPM_BigImg().contains("http")) {
                Glide.with(context).load(bean.getPM_BigImg()).into(viewHodler.ivPicture);
            } else {
                Glide.with(context).load(HttpAPI.API().MAIN_DOMAIN + bean.getPM_BigImg()).into(viewHodler.ivPicture);
            }
        } else {
            viewHodler.ivPicture.setImageResource(R.drawable.defalut_goods);
        }
        viewHodler.tvgiftname.setText(bean.getPM_Name() + "");
        viewHodler.tvgiftnum.setText("" + bean.getPM_UnitPrice() + "");
        viewHodler.tvgifttype.setText("服务类别:" + bean.getPT_Name());
        return view;
    }

    public void setParm(List<ServiceResponseListBean.DataBean.DataListBean> mListBeans) {
        dataListBeen = mListBeans;
    }


    class ViewHodler {
        ShapedImageView ivPicture;
        TextView tvgiftname;
        TextView tvgiftnum;
        TextView tvgifttype;

        public ViewHodler(View view) {
            ivPicture = (ShapedImageView) view.findViewById(R.id.iv_gift);
            tvgiftname = (TextView) view.findViewById(R.id.tv_sername_lv);
            tvgiftnum = (TextView) view.findViewById(R.id.tv_sernum_lv);
            tvgifttype = (TextView) view.findViewById(R.id.tv_sertype_lv);
        }
    }
}