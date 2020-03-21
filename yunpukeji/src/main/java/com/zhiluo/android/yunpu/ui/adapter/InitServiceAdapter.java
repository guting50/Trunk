package com.zhiluo.android.yunpu.ui.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;


import java.util.List;

/**
 * 作者：罗咏哲 on 2018/1/13 11:16.
 * 邮箱：137615198@qq.com
 */

public class InitServiceAdapter extends BaseAdapter {
    private Context mContext;
    private List<DataBean.InitServiceListBean> mList;
    DeleteClick deleteClick;
    CheckBoxChange boxChange;

    public InitServiceAdapter(Context mContext, List<DataBean.InitServiceListBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public InitServiceAdapter(Context mContext, List<DataBean.InitServiceListBean> mList,
                              DeleteClick deleteClick, CheckBoxChange boxChange) {
        this.mContext = mContext;
        this.mList = mList;
        this.deleteClick = deleteClick;
        this.boxChange = boxChange;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;
//        if (view == null) {
        view = LayoutInflater.from(mContext).inflate(R.layout.item_init_service, parent, false);
        holder = new ViewHolder(view);
        view.setTag(holder);
//        } else {
//            holder = (ViewHolder) view.getTag();
//        }
        holder.tvName.setText(mList.get(position).getSG_Name());
        holder.etCount.setText("" + mList.get(position).getSR_Number());
        holder.etTime.setText("" + mList.get(position).getSR_Timer());
        switch (mList.get(position).getSR_TimeUnit()) {
            case 1:
                holder.spTimeType.setSelection(0);
                break;
            case 2:
                holder.spTimeType.setSelection(1);
                break;
            case 3:
                holder.spTimeType.setSelection(2);
                break;
        }
        if (mList.get(position).getSR_Timer() > 0) {
            holder.cbLimitTime.setChecked(true);
        } else {
            holder.cbLimitTime.setChecked(false);
        }
        if (holder.cbLimitTime.isChecked()) {
            holder.etTime.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            holder.etTime.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        } else {
            holder.etTime.setBackgroundColor(mContext.getResources().getColor(R.color.background));
            holder.etTime.setInputType(InputType.TYPE_NULL);
        }
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteClick.deleteItem(v);
            }
        });
        holder.cbLimitTime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boxChange.change(buttonView, isChecked);
            }
        });
        holder.etCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    mList.get(position).setSR_Number(Integer.parseInt(s.toString()));
                } else {
                    mList.get(position).setSR_Number(0);
                }
            }
        });
        holder.etTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    mList.get(position).setSR_Timer(Integer.parseInt(s.toString()));
                } else {
                    mList.get(position).setSR_Timer(0);
                }
            }
        });
        holder.spTimeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                switch (pos) {
                    case 0:
                        mList.get(position).setSR_TimeUnit(1);
                        break;
                    case 1:
                        mList.get(position).setSR_TimeUnit(2);
                        break;
                    case 2:
                        mList.get(position).setSR_TimeUnit(3);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        holder.cbLimitTime.setTag(position);
        holder.ivDelete.setTag(position);
        return view;
    }

    class ViewHolder {
        TextView tvName;
        EditText etCount, etTime;
        CheckBox cbLimitTime;
        Spinner spTimeType;
        ImageView ivDelete;

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_item_init_service_name);
            etCount = (EditText) view.findViewById(R.id.et_item_init_service_count);
            etTime = (EditText) view.findViewById(R.id.et_item_init_service_time);
            cbLimitTime = (CheckBox) view.findViewById(R.id.cb_limit_time);
            spTimeType = (Spinner) view.findViewById(R.id.sp_item_init_service);
            ivDelete = (ImageView) view.findViewById(R.id.iv_item_init_delete);
        }
    }

    public interface DeleteClick {
        void deleteItem(View view);
    }

    public interface CheckBoxChange {
        void change(View view, boolean isChecked);
    }
}
