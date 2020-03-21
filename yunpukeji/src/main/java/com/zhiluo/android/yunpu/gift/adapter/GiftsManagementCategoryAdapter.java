package com.zhiluo.android.yunpu.gift.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.gift.bean.GiftTypeBean;

import java.util.List;

/**
 * 礼品管理主分类
 * Created by 70700 on 2016/10/31.
 */
public class GiftsManagementCategoryAdapter extends BaseAdapter
{
    private Context context;
    List<GiftTypeBean.DataBean> typeBean;
    private int selectedPosition = -1;
    int itmposition = -1;

    public GiftsManagementCategoryAdapter(Context context, List<GiftTypeBean.DataBean> typeBean)
    {
        this.context = context;
        this.typeBean = typeBean;
    }

    @Override
    public int getCount()
    {
        return typeBean == null ? 0 : typeBean.size();
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        ViewHodler viewHodler = null;
        if (view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.item_overall_original_category, null);
            viewHodler = new ViewHodler(view);
            view.setTag(viewHodler);
        }
        else
        {
            viewHodler = (ViewHodler) view.getTag();
        }
        viewHodler.tvtybename.setText(typeBean.get(position).getGT_Name());
        itmposition = position;
        if (position == selectedPosition)
        {
            viewHodler.tvshow.setVisibility(View.VISIBLE);
            viewHodler.tvtybename.setSelected(true);
        }
        else
        {
            viewHodler.tvshow.setVisibility(View.INVISIBLE);
            viewHodler.tvtybename.setSelected(false);
        }

        return view;
    }

    public void setSelectedPosition(int position)
    {
        selectedPosition = position;

    }

    public int CallbackPosition()
    {
        return selectedPosition;
    }

    class ViewHodler
    {
        public TextView tvtybename;
        public TextView tvshow;

        public ViewHodler(View view)
        {
            tvtybename = (TextView) view.findViewById(R.id.tv_tybenamepdtype_lv);
            tvshow = (TextView) view.findViewById(R.id.tv_showpdtype_lv);
        }
    }
}
