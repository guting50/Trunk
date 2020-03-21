package com.zhiluo.android.yunpu.member.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.member.jsonbean.VipListBean;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.util.List;

/**
 * 会员列表适配器
 * Created by Administrator on 2016/9/6.
 */
public class MemberAdapter extends BaseAdapter
{
    private Context context;
    List<VipListBean.DataBean.DataListBean> list;


    public MemberAdapter(Context context, List<VipListBean.DataBean.DataListBean> list)
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
            view = LayoutInflater.from(context).inflate(R.layout.item_member, null);
            viewHodler = new ViewHodler(view);
            view.setTag(viewHodler);
        }
        else
        {
            viewHodler = (ViewHodler) view.getTag();
        }
        viewHodler.tv_vipname.setText(list.get(position).getVIP_Name());
        viewHodler.tv_vipjifen.setText(Decima2KeeplUtil.stringToDecimal(list.get(position).getMA_AvailableIntegral()));
        viewHodler.tv_vipyue.setText(Decima2KeeplUtil.stringToDecimal(list.get(position).getMA_AvailableBalance()));
        viewHodler.tv_vipcellphone.setText((String) list.get(position).getVIP_CellPhone());
        return view;
    }


    class ViewHodler
    {
        public TextView tv_vipname;
        public TextView tv_vipcellphone;
        public TextView tv_vipyue;
        public TextView tv_vipjifen;

        public ViewHodler(View view)
        {
            tv_vipname = (TextView) view.findViewById(R.id.tv_vipname);
            tv_vipcellphone = (TextView) view.findViewById(R.id.tv_vipcellphone);
            tv_vipyue = (TextView) view.findViewById(R.id.tv_vipyue);
            tv_vipjifen = (TextView) view.findViewById(R.id.tv_vipjifen);
        }
    }
}
