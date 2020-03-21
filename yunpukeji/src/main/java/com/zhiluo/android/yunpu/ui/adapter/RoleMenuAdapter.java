package com.zhiluo.android.yunpu.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.AppMenuBean;
import com.zhiluo.android.yunpu.entity.AppMenuListBean;

/**
 * 角色菜单Adapter
 * Created by dell on 2018/2/26.
 */

public class RoleMenuAdapter extends RecyclerView.Adapter<RoleMenuAdapter.ViewHolder> {
    private AppMenuListBean data;

    public RoleMenuAdapter(AppMenuListBean data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app_menu, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        AppMenuBean bean = data.getData().get(position);
        holder.checkBox.setText(bean.getAM_Name());
        holder.checkBox.setTag(position);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    data.getData().get(position).setAR_IsCheck("1");
                } else {
                    data.getData().get(position).setAR_IsCheck("0");
                }
            }
        });
        if (data.getData().get(position).getAR_IsCheck() != null) {
            String obj = data.getData().get(position).getAR_IsCheck();
//            if (obj instanceof Double){
                if (obj.equals("1")){
                    holder.checkBox.setChecked(true);
                }else {
                    holder.checkBox.setChecked(false);
                }
//            }
//            if (obj instanceof Integer){
//                if ((int) obj ==1){
//                    holder.checkBox.setChecked(true);
//                }else {
//                    holder.checkBox.setChecked(false);
//                }
//            }
        } else {
            holder.checkBox.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return data.getData().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View menuView;
        CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            menuView = itemView;
            checkBox = itemView.findViewById(R.id.cb_item_app_menu);
        }
    }
}
