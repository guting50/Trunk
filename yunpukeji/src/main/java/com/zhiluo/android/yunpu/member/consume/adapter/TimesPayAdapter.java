package com.zhiluo.android.yunpu.member.consume.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.gift.bean.ChargeListBean;

import com.zhiluo.android.yunpu.goods.consume.adapter.GoodsPayAdapter;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;


import java.util.List;

/**
 * Created by YSL on 2018-08-16.
 */

public class TimesPayAdapter extends RecyclerView.Adapter<TimesPayAdapter.MyHolder> {


    private List<ChargeListBean.DataBean> List;
    private Context context;

    public employeeClick memployeeClick;
    private imgicClick mimgicClick;
    private boolean isStaff;



    public TimesPayAdapter(Context context, List<ChargeListBean.DataBean> mList, employeeClick memployeeClick, imgicClick mimgicClick, boolean isStaff){
        this.context = context;
        this.List = mList;
        this.memployeeClick = memployeeClick;
        this.mimgicClick = mimgicClick;
        this.isStaff = isStaff;

    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.times_pay_item, null);
        TimesPayAdapter.MyHolder myHolder = new TimesPayAdapter.MyHolder(view);
        return myHolder;

    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        ChargeListBean.DataBean bean = List.get(position);
        holder.tvtype.setText(bean.getSG_Name());//商品名称
        holder.tvcount.setText("消费次数：" + bean.getCount());//消费次数

        if (isStaff){
            holder.tvEmploeeName.setVisibility(View.VISIBLE);
            holder.emImage.setVisibility(View.VISIBLE);
        }else {
            holder.tvEmploeeName.setVisibility(View.GONE);
            holder.emImage.setVisibility(View.GONE);
        }
        if (bean.getEmployeeValue()!=null &&!bean.getEmployeeValue().equals("")){
            holder.tvEmploeeName.setText(bean.getEmployeeValue()+"");
        }else {
            holder.tvEmploeeName.setText("点击选择员工");
        }
        holder.tvEmploeeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memployeeClick.click(v);
            }
        });
        holder.emImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mimgicClick.imagicClick(v);
            }
        });
        holder.emImage.setTag(position);
        holder.tvEmploeeName.setTag(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return List == null ? 0 : List.size();
    }



    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvtype;
        private TextView tvcount,tvEmploeeName;
        private ImageView emImage;


        public MyHolder(View itemView) {
            super(itemView);
            tvtype = itemView.findViewById(R.id.tv_type);
            tvcount = itemView.findViewById(R.id.tv_left_counts);
            tvEmploeeName = itemView.findViewById(R.id.tv_employee_name);
            emImage = itemView.findViewById(R.id.img_employee_all);
        }
    }

    /**
     * 选择员工按钮
     */
    public interface employeeClick {
        void click(View v);
    }

    public interface imgicClick{
        void imagicClick(View view);
    }
}
