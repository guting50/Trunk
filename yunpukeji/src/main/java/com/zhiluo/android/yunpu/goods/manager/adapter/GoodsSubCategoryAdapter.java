package com.zhiluo.android.yunpu.goods.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsTypeC;

import java.util.List;

/**
 * 选择商品分类小分类适配器
 * Created by 70700 on 2016/11/17.
 */
public class GoodsSubCategoryAdapter extends BaseAdapter
{

    private Context context;
    private List<GoodsTypeC> list;

    public GoodsSubCategoryAdapter(Context context, List<GoodsTypeC> list)
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
            view = LayoutInflater.from(context).inflate(R.layout.item_overall_choose_subcategory, null);
            viewHodler = new ViewHodler(view);
            view.setTag(viewHodler);
        }
        else
        {
            viewHodler = (ViewHodler) view.getTag();
        }
        viewHodler.tvtybename.setText(list.get(position).getPT_Name());
        return view;
    }

    class ViewHodler
    {
        public TextView tvtybename;

        public ViewHodler(View view)
        {
            tvtybename = (TextView) view.findViewById(R.id.tv_tybenamepdtype_lv);
        }
    }
}
