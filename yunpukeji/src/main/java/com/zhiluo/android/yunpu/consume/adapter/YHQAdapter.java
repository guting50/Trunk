package com.zhiluo.android.yunpu.consume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.consume.bean.YHQBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberInfoBean;
import com.zhiluo.android.yunpu.ui.adapter.ChoiceServiceAdapter;

import java.util.List;

/**
 * 作者：罗咏哲 on 2018/1/4 14:38.
 * 邮箱：137615198@qq.com
 */

public class YHQAdapter extends BaseAdapter {
    private List<YHQBean.DataBean> yhqList;
    private Context mContext;
    ImageClick mImageClick;

    public YHQAdapter(List<YHQBean.DataBean> yhqList, Context mContext, ImageClick mImageClick) {
        this.yhqList = yhqList;
        this.mContext = mContext;
        this.mImageClick = mImageClick;
    }

    @Override
    public int getCount() {
        return yhqList == null ? 0 : yhqList.size();
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
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_yhq_layout, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        YHQBean.DataBean bean = yhqList.get(position);

        holder.tvConsumeMoney.setText("满" + bean.getEC_Denomination() + "元可用");

        holder.tvname.setText("" + bean.getEC_Name());
        if (bean.getSM_Name()==null||bean.getSM_Name().equals("")){
            holder.tvdianpu.setText("所有店铺可用");
        }else {
            holder.tvdianpu.setText("" + bean.getSM_Name());
        }
        if (bean.getEC_IsOverlay() == 0) {
            holder.tvdiejia.setText("不可叠加使用");
        } else if (bean.getEC_IsOverlay() == 1) {
            holder.tvdiejia.setText("可叠加使用");
        }

        if (bean.getEC_DiscountType() == 1) {
            holder.tvType.setText("代金券");
            holder.tvYHMoney.setText("¥" + bean.getEC_Discount());
        } else if (bean.getEC_DiscountType() == 2) {
            holder.tvType.setText("折扣券");
            holder.tvYHMoney.setText((bean.getEC_Discount())/10+"折");
        }

        switch (bean.getVCR_IsForver()) {
            case 0:
                holder.tvYouXiaoQi.setText(bean.getVCR_StatrTime()+"--"+bean.getVCR_EndTime()+"有效");
                break;
            case 1:
                holder.tvYouXiaoQi.setText("永久有效");
                break;
            case 2:
                holder.tvYouXiaoQi.setText(bean.getVCR_EndTime()+"前有效");
                break;
        }
        holder.ivChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageClick.click(v);
            }
        });

        if (bean.isCheck()) {
            holder.ivChecked.setImageResource(R.drawable.check_box_circle_checked);
        } else {
            holder.ivChecked.setImageResource(R.drawable.shape_check_box_empty);
        }
        holder.ivChecked.setTag(position);

        return view;
    }

    class ViewHolder {
        TextView tvYHMoney, tvConsumeMoney, tvType, tvYouXiaoQi,tvname,tvdianpu,tvdiejia;
        ImageView ivChecked;

        public ViewHolder(View v) {
            tvYHMoney = (TextView) v.findViewById(R.id.item_yhq_yh_money);
            tvConsumeMoney = (TextView) v.findViewById(R.id.item_yhq_hf_money);
            tvType = (TextView) v.findViewById(R.id.tv_item_yhq_type);
            tvYouXiaoQi = (TextView) v.findViewById(R.id.tv_item_yhq_youxiaoqi);
            ivChecked = (ImageView) v.findViewById(R.id.ic_item_choice);
            tvname =  (TextView) v.findViewById(R.id.tv_item_yhq_name);
            tvdianpu =  (TextView) v.findViewById(R.id.tv_item_yhq_dianpu);
            tvdiejia =  (TextView) v.findViewById(R.id.tv_item_yhq_diejia);
        }
    }

    /**
     * 选择按钮
     */
    public interface ImageClick {
        void click(View v);
    }
}
