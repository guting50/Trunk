package com.zhiluo.android.yunpu.setting.bluetooths.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.zhiluo.android.yunpu.R;
import java.util.List;

/**
 * Created by Administrator on 2017/1/5 0005.
 */

public class ConecteddeviceAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    public ConecteddeviceAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
      return list ==null? 0: list.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.bonddevice_item, null,false);
            viewHodler = new ViewHodler(view);
            view.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) view.getTag();
        }
        viewHodler.device_name.setText(list.get(position));
        return view;
    }
    class ViewHodler{
        public TextView device_name;

        public ViewHodler(View view) {
            device_name= (TextView) view.findViewById(R.id.device_name);
        }
    }
}
