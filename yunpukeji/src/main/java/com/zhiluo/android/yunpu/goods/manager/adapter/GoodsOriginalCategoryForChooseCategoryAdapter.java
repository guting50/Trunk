package com.zhiluo.android.yunpu.goods.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsTypeA;

import java.util.List;

/**
 * 商品主分类列表适配器
 * Created by Administrator on 2016/9/6.
 */
public class GoodsOriginalCategoryForChooseCategoryAdapter extends BaseAdapter
{
    private Context context;
    List<GoodsTypeA> list;
    private int selectedPosition = -1;
    int itmposition = -1;

    public GoodsOriginalCategoryForChooseCategoryAdapter(Context context, List<GoodsTypeA> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount()
    {
        return list == null ? 0 : list.size();
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
        viewHodler.tvtybename.setText(list.get(position).getPT_Name());
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
