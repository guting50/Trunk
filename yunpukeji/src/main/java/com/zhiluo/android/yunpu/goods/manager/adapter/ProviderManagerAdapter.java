package com.zhiluo.android.yunpu.goods.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.goods.manager.bean.ProviderBean;

/**
 * 作者：罗咏哲 on 2017/9/27 10:20.
 * 邮箱：137615198@qq.com
 */

public class ProviderManagerAdapter extends BaseAdapter {
    private Context context;
    private ProviderBean mProviderBean;

    public ProviderManagerAdapter(Context context, ProviderBean mProviderBean) {
        this.context = context;
        this.mProviderBean = mProviderBean;
    }

    @Override
    public int getCount() {
        return mProviderBean.getData().getDataList() == null ? 0 : mProviderBean.getData().getDataList().size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ProviderBean.DataBean.DataListBean bean = mProviderBean.getData().getDataList().get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_provider_manager_layout, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
       /* viewHolder.tvName.setText(bean.getSL_Name() == null ? "" : bean.getSL_Name());
        viewHolder.tvCode.setText(bean.getSL_DefaultName() == null ? "" : bean.getSL_DefaultName());
        viewHolder.tvType.setText(bean.getSL_DefaultPhone() == null ? "" : bean.getSL_DefaultPhone());
        viewHolder.tvFormat.setText(bean.getSL_Creator() == null ? "" : bean.getSL_Creator());
        viewHolder.tvUnit.setText(bean.getSL_CreateTime() == null ? "" : bean.getSL_CreateTime());
*/
        viewHolder.tvCompanName.setText(bean.getSL_Name() == null ? "供应商未设置" : bean.getSL_Name());
        viewHolder.tvSupplierName.setText(bean.getSL_DefaultName() == null ? "联系未设置" : bean.getSL_DefaultName());
        viewHolder.tvPhone.setText(bean.getSL_DefaultPhone() == null ? "联系电话未设置" : bean.getSL_DefaultPhone());
        return view;
    }


    class ViewHolder {
        TextView tvName;//名称
        TextView tvCode;//编码
        TextView tvType;//分类
        TextView tvFormat;//规格
        TextView tvUnit;//单位
        TextView tvPhone;//电话
        TextView tvCompanName;//公司吗名称
        TextView tvSupplierName;//供应商名称
        ImageView imgHeadImg;//头像

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_item_check_goods_name);
            tvCode = (TextView) view.findViewById(R.id.tv_item_check_goods_code);
            tvType = (TextView) view.findViewById(R.id.tv_item_check_goods_type);
            tvFormat = (TextView) view.findViewById(R.id.tv_item_check_goods_format);
            tvUnit = (TextView) view.findViewById(R.id.tv_item_check_goods_unit);
            tvPhone = (TextView) view.findViewById(R.id.tv_phone);
            tvCompanName = (TextView) view.findViewById(R.id.tv_company_name);
            tvSupplierName = (TextView) view.findViewById(R.id.tv_supplier_name);
            imgHeadImg = (ImageView) view.findViewById(R.id.img_head);

        }
    }

}
