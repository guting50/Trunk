package com.zhiluo.android.yunpu.ui.activity.good.adapter;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.consume.adapter.GoodsPayAdapter;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.ui.activity.good.InWareRetureActivity;
import com.zhiluo.android.yunpu.ui.activity.good.bean.InReCoderDetailBean;
import com.zhiluo.android.yunpu.ui.adapter.InitTCAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;

import java.util.List;

/**
 * Created by ZPH on 2019-04-19.
 */

public class InWareRetureAdapter extends  RecyclerView.Adapter<InWareRetureAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private InReCoderDetailBean inReCoderDetailBean;
    private IItemClick itemClick;
    private InWareRetureActivity.ChangeHandler changeHandler;



    public InWareRetureAdapter(Context mContext, InReCoderDetailBean inReCoderDetailBean,
                         IItemClick itemClick,InWareRetureActivity.ChangeHandler changeHandler) {
        this.mContext = mContext;
        this.inReCoderDetailBean = inReCoderDetailBean;
        this.itemClick = itemClick;
        this.changeHandler = changeHandler;

    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return inReCoderDetailBean.getData()==null?0:inReCoderDetailBean.getData().size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.inware_reture_item, null);
        ViewHolder myHolder = new ViewHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String cont, cont1;
        cont = (inReCoderDetailBean.getData().get(position).getNowAccount() + "");
        cont1=cont.substring(cont.length()-2,cont.length());
        holder.tvName.setText(inReCoderDetailBean.getData().get(position).getPM_Name());
        holder.etNum.setText( cont1.equals(".0") ? cont.substring(0, cont.length() - 2) : cont);
        holder.etprice.setText(inReCoderDetailBean.getData().get(position).getPM_UnitPrice() + "");

        //数量
        if (inReCoderDetailBean.getData().get(position).getNowAccount() > 0) {
            holder.ivMove.setVisibility(View.VISIBLE);//数量不为0时显示移除图标
        } else {
            holder.ivMove.setVisibility(View.INVISIBLE);//数量为0时隐藏移除图标
        }
        holder.etNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty() && !s.toString().equals("") && !s.toString().equals(".")){
                    if (Double.parseDouble(s.toString())<=inReCoderDetailBean.getData().get(position).getSID_Amount()){
                        inReCoderDetailBean.getData().get(position).setNowAccount(Double.parseDouble(s.toString()));
                        senMsgToActivity();
                    }else {
                        CustomToast.makeText(mContext, "退货数量不能大于采购数量", Toast.LENGTH_SHORT).show();
                        holder.etNum.setText(inReCoderDetailBean.getData().get(position).getSID_Amount() +"");
                        holder.etNum.setSelection(holder.etNum.getText().toString().length());
                    }

                }else {
                    holder.etNum.setText("0");
                    holder.etNum.setSelection(1);
                }
            }
        });

        holder.etprice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty() && !s.toString().equals("") && !s.toString().equals(".")) {
                    inReCoderDetailBean.getData().get(position).setPM_UnitPrice(Double.parseDouble(s.toString()));
                    senMsgToActivity();
                }else {
                    holder.etNum.setText("0");
                    holder.etNum.setSelection(1);
                }
            }
        });


        holder.ivMove.setOnClickListener(this);
        holder.ivAdd.setOnClickListener(this);
        holder.ivMove.setTag(position);
        holder.ivAdd.setTag(position);

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    private void senMsgToActivity() {
        Message message = Message.obtain();
        message.what = 1;
        message.obj = inReCoderDetailBean;
        changeHandler.sendMessage(message);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        EditText etprice,etNum;
        ImageView ivMove;//移除
        ImageView ivAdd;//增加

        public ViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_goods_name);
            etprice = (EditText) view.findViewById(R.id.tv_single_money);
            etNum = (EditText) view.findViewById(R.id.tv_item_init_num);
            ivMove = (ImageView) view.findViewById(R.id.iv_reduce);
            ivAdd = (ImageView) view.findViewById(R.id.iv_add);

        }
    }

    @Override
    public void onClick(View v) {
        itemClick.itemClick(v);
    }

    public interface IItemClick {
        void itemClick(View view);
    }

}
