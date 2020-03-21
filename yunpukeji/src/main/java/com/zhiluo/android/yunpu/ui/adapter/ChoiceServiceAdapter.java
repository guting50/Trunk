package com.zhiluo.android.yunpu.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 选择初始服务适配器
 */
public class ChoiceServiceAdapter extends BaseAdapter {
    private Context context;
    List<GoodsCheckResponseByType.DataBean.DataListBean> list;
    ImageClick mImageClick;

    public ChoiceServiceAdapter(Context context, List<GoodsCheckResponseByType.DataBean.DataListBean> list,
                                ImageClick mImageClick) {
        this.context = context;
        this.list = list;
        this.mImageClick = mImageClick;
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_choice_service, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if (list.get(position).getPM_BigImg() != null) {
            String uri;
            if (list.get(position).getPM_BigImg().contains("http")) {
                uri = list.get(position).getPM_BigImg();
            } else {
                String url = list.get(position).getPM_BigImg();
                if (url.contains("../")) {
                    String[] s = url.split("\\.\\./");
                    url = s[2];
                }
                uri = HttpAPI.API().MAIN_DOMAIN + "/" + url;
            }
            Glide.with(context).load(Uri.parse(uri)).into(viewHolder.ivPicture);
        } else {
            viewHolder.ivPicture.setImageResource(R.drawable.defalut_goods);
        }
        viewHolder.tvName.setText(list.get(position).getPM_Name());
        viewHolder.tvPrice.setText(Decima2KeeplUtil.stringToDecimal(list.get(position).getPM_UnitPrice() + ""));
        viewHolder.ivChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageClick.click(v);
            }
        });

        if (list.get(position).isChecked()) {
            viewHolder.ivChecked.setImageResource(R.drawable.check_box_circle_checked);
        } else {
            viewHolder.ivChecked.setImageResource(R.drawable.shape_check_box_empty);
        }
        viewHolder.ivChecked.setTag(position);
        return view;
    }

    public void setParam(List<GoodsCheckResponseByType.DataBean.DataListBean> mList) {
        list  = mList;
    }

    class ViewHolder {
        private ShapedImageView ivPicture;//头像
        public TextView tvName;
        private TextView tvPrice;
        private ImageView ivChecked;
        private LinearLayout lJiCi;

        public ViewHolder(View view) {
            ivPicture = (ShapedImageView) view.findViewById(R.id.iv_item_service);
            tvName = (TextView) view.findViewById(R.id.tv_item_service_name);
            tvPrice = (TextView) view.findViewById(R.id.tv_item_service_price);
            ivChecked = (ImageView) view.findViewById(R.id.ic_item_choice);
            //lJiCi = (LinearLayout) view.findViewById(R.id.l_jici_edit);
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
        void click(View view);
    }
}
