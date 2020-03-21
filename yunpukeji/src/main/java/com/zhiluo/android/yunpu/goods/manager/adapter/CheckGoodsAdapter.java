package com.zhiluo.android.yunpu.goods.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;

/**
 * 作者：罗咏哲 on 2017/9/27 10:20.
 * 邮箱：137615198@qq.com
 */

public class CheckGoodsAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private GoodsCheckResponseByType mGoodsBean;
    private ItemCheck mItemCheck;

    public CheckGoodsAdapter(Context context, GoodsCheckResponseByType mGoodsBean, ItemCheck mItemCheck) {
        this.context = context;
        this.mGoodsBean = mGoodsBean;
        this.mItemCheck = mItemCheck;
    }

    @Override
    public int getCount() {
        return mGoodsBean.getData().getDataList() == null ? 0 : mGoodsBean.getData().getDataList().size();
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
        GoodsCheckResponseByType.DataBean.DataListBean bean = mGoodsBean.getData().getDataList().get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_check_goods_layout, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvName.setText(bean.getPM_Name() == null ? "" : bean.getPM_Name());
        viewHolder.tvCode.setText(bean.getPM_Code() == null ? "" : bean.getPM_Code());
        viewHolder.tvType.setText(bean.getPT_Name() == null ? "" : bean.getPT_Name());
        viewHolder.tvFormat.setText(bean.getPM_Modle() == null ? "" : bean.getPM_Modle());
        viewHolder.tvUnit.setText(bean.getPM_Metering() == null ? "" : bean.getPM_Metering());
        viewHolder.tvStockNum.setText(String.valueOf(bean.getStock_Number()));
        viewHolder.tvPrice.setText("" + bean.getPM_UnitPrice());
        if (bean.isChecked()) {
            viewHolder.cbCheck.setChecked(true);
        } else {
            viewHolder.cbCheck.setChecked(false);
        }
        viewHolder.cbCheck.setOnClickListener(this);
        viewHolder.cbCheck.setTag(i);
        return view;
    }

    @Override
    public void onClick(View v) {
        mItemCheck.itemClick(v);
    }

    class ViewHolder {
        TextView tvName;//名称
        TextView tvCode;//编码
        TextView tvType;//分类
        TextView tvFormat;//规格
        TextView tvUnit;//单位
        TextView tvStockNum;//库存
        TextView tvPrice;//单价
        CheckBox cbCheck;

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_item_check_goods_name);
            tvCode = (TextView) view.findViewById(R.id.tv_item_check_goods_code);
            tvType = (TextView) view.findViewById(R.id.tv_item_check_goods_type);
            tvFormat = (TextView) view.findViewById(R.id.tv_item_check_goods_format);
            tvUnit = (TextView) view.findViewById(R.id.tv_item_check_goods_unit);
            tvStockNum = (TextView) view.findViewById(R.id.tv_item_check_goods_stock);
            tvPrice = (TextView) view.findViewById(R.id.tv_item_check_goods_price);
            cbCheck = (CheckBox) view.findViewById(R.id.cb_item_check_good);
        }
    }

    public interface ItemCheck {
        void itemClick(View view);
    }

}
