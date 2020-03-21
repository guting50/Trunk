package com.zhiluo.android.yunpu.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;

import java.util.List;


/**
 * Created by ${YSL} on 2018-04-17.
 * 已选择或者编辑的服务展示
 */
public class DisplayserviceAdapter extends BaseAdapter {
    //private List<GoodsCheckResponseByType.DataBean.DataListBean> list;
    public ImageClick mImageClick;
    public EditClick mEditClick;
    private Context mContext;
    private List<DataBean.InitServiceListBean> mList;

    public DisplayserviceAdapter(Context mContext, List<DataBean.InitServiceListBean> mList, ImageClick mImageClick, EditClick mEditClick) {
        this.mContext = mContext;
        this.mList = mList;
        this.mImageClick = mImageClick;
        this.mEditClick = mEditClick;
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        StringBuilder mContent = new StringBuilder("");
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.tiem_displayservice_adapter, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvName.setText(mList.get(position).getSG_Name());
        mContent.append(mList.get(position).getSR_Number() + "次");
        if (mList.get(position).getSR_Timer() > 0) {
            mContent.append("限时");
            switch (mList.get(position).getSR_TimeUnit()) {
                case 1:
                    mContent.append(mList.get(position).getSR_Timer() + "天");
                    break;
                case 2:
                    mContent.append(mList.get(position).getSR_Timer() + "月");
                    break;
                case 3:
                    mContent.append(mList.get(position).getSR_Timer() + "年");
                    break;
            }
        }
        //viewHolder.tvName.setText(list.get(position).getPM_Name());
        viewHolder.tvPrice.setText(mContent.toString());
        viewHolder.ivChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageClick.click(v);
            }
        });

        if (mList.get(position).isChecked()) {
            viewHolder.ivChecked.setImageResource(R.drawable.check_box_circle_checked);
        } else {
            viewHolder.ivChecked.setImageResource(R.drawable.shape_check_box_empty);
        }
        viewHolder.ivChecked.setTag(position);
        viewHolder.edit.setTag(position);
        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditClick.editClick(v);
            }
        });
        return view;
    }

    class ViewHolder {
        public TextView tvName;//商品名字
        private TextView tvPrice;//服务描述
        private ImageView ivChecked;//选择button
        private RelativeLayout edit;//编辑按钮

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_item_service_name);
            tvPrice = (TextView) view.findViewById(R.id.tv_item_service_price);
            ivChecked = (ImageView) view.findViewById(R.id.ic_item_choice);
            edit = view.findViewById(R.id.r_edit);
        }
    }

    /**
     * 选择按钮
     */
    public interface ImageClick {
        void click(View v);
    }

    /**
     * 编辑按钮
     */
    public interface EditClick {
        void editClick(View view);
    }
}
