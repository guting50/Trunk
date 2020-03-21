package com.zhiluo.android.yunpu.ui.activity.good.adapter;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.ui.activity.good.fragment.InWareHouseFragment;
import com.zhiluo.android.yunpu.ui.activity.good.fragment.OutWareHouseFragment;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.MoneyInputFilter;

import java.util.List;

/**
 * Created by ZPH on 2019-06-03.
 */

public class InOutGoodsAdapter extends RecyclerView.Adapter<InOutGoodsAdapter.MyHolder> {

    private Context mContext;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mDataList;
    private boolean isck;
    private String money = "";
    private InWareHouseFragment.Myhandler changeHandler;
    private OutWareHouseFragment.Myhandler changeHandler1;
    private double lsMoney = 0;

    public InOutGoodsAdapter(Context mContext, List<GoodsCheckResponseByType.DataBean.DataListBean> mDataList, InWareHouseFragment.Myhandler changeHandler, boolean isck) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        this.isck = isck;
        this.changeHandler = changeHandler;


    }

    public InOutGoodsAdapter(Context mContext, List<GoodsCheckResponseByType.DataBean.DataListBean> mDataList, OutWareHouseFragment.Myhandler changeHandler, boolean isck) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        this.isck = isck;
        this.changeHandler1 = changeHandler;


    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_inoutgoods_adapter, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        GoodsCheckResponseByType.DataBean.DataListBean bean = mDataList.get(position);

        //头像
        if (bean.getPM_BigImg() != null) {
            if (bean.getPM_BigImg().contains("http")) {
                Glide.with(mContext).load(bean.getPM_BigImg()).into(holder.ivGoodsLogo);
            } else {
                String url = bean.getPM_BigImg();
                if (url.contains("../")) {
                    String[] s = url.split("\\.\\./");
                    url = s[2];
                }
                Glide.with(mContext).load(HttpAPI.API().MAIN_DOMAIN + "/" + url).into(holder.ivGoodsLogo);
            }
        } else {
            holder.ivGoodsLogo.setImageResource(R.drawable.ysl_add_goods);
        }
        //商品名字
        holder.tvGoodsName.setText(bean.getPM_Name());
        //商品单价
        holder.tvSingleMoney.setText(Decima2KeeplUtil.stringToDecimal(bean.getPM_PurchasePrice() + ""));
        if (isck){
            holder.tvSingleMoney.setEnabled(false);
        }else {
            holder.tvSingleMoney.setEnabled(true);
        }
        //数量
        if (bean.getNum() > 0) {
            holder.edCount.setText(Decima2KeeplUtil.stringToDecimal( bean.getNum() + ""));
        } else {
            holder.edCount.setText(0.00 + "");
        }
        //金额过滤器
        MoneyInputFilter filter = new MoneyInputFilter(7, true, "最多只能输入7位金额");
        filter.setMessage("最多只能输入7位金额");
        holder.edCount.setFilters(new InputFilter[]{filter});

        holder.edMoney.setText(Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getPM_PurchasePrice() * mDataList.get(position).getNum() + ""));
        money = holder.edMoney.getText().toString();

        senMsgToActivity();


        //进价修改
        holder.tvSingleMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("") && s.toString() != null) {

                    mDataList.get(position).setPM_PurchasePrice(Double.parseDouble(s.toString()));
                    holder.edMoney.setText(Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getPM_PurchasePrice() * mDataList.get(position).getNum() + ""));
                    money = holder.edMoney.getText().toString();
                    try {
                        mDataList.get(position).setZHMoney(Double.parseDouble(money));
                    } catch (Exception e) {
                    }

                } else {
                    mDataList.get(position).setZHMoney(0.00);
                    mDataList.get(position).setPM_PurchasePrice(0.00);
                    holder.edMoney.setText(0.00+"");
                }

                senMsgToActivity();
            }
        });
        //检测修改的商品数量
        holder.edCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("") || TextUtils.isEmpty(s.toString())||s.toString().equals(".0")) {
                    mDataList.get(position).setNum(0);
                    holder.edMoney.setText(Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getPM_PurchasePrice() * mDataList.get(position).getNum() + ""));
//                    holder.edCount.setText("0");
                    CustomToast.makeText(mContext, "商品数量须大于0", Toast.LENGTH_SHORT).show();
                } else if (Double.parseDouble(s.toString())>=10000){
                    holder.edCount.setText("9999");
                    CustomToast.makeText(mContext, "商品数量不能超过一万", Toast.LENGTH_SHORT).show();
                }else {
                    mDataList.get(position).setNum(Double.parseDouble(s.toString()));
                    holder.edMoney.setText(Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getPM_PurchasePrice() * mDataList.get(position).getNum() + ""));
                    mDataList.get(position).setZHMoney(Double.parseDouble(holder.edMoney.getText().toString()));
                }
                money = holder.edMoney.getText().toString();
                mDataList.get(position).setZHMoney(Double.parseDouble(money));
                senMsgToActivity();
            }
        });


    }

    private void senMsgToActivity() {
        Message message = Message.obtain();
        message.what = 1;
        message.obj = mDataList;
        if (isck){
            changeHandler1.sendMessage(message);
        }else {
            changeHandler.sendMessage(message);
        }

    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private ShapedImageView ivGoodsLogo;
        private TextView tvGoodsName;
        private TextView tvSingleMoney;
        private EditText edCount;
        private EditText edMoney;
        private RelativeLayout rCounts;
        private RelativeLayout rMoney;

        public MyHolder(View itemView) {
            super(itemView);
            ivGoodsLogo = itemView.findViewById(R.id.img_add_goods);
            tvGoodsName = itemView.findViewById(R.id.tv_goods_name);

            tvSingleMoney = itemView.findViewById(R.id.tv_single_money);
            edCount = itemView.findViewById(R.id.ed_counts);
//            rCounts = itemView.findViewById(R.id.r_layout_a);
            rMoney = itemView.findViewById(R.id.r_layout_b);
            edMoney = itemView.findViewById(R.id.ed_money);
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
    /**
     * 修改数量
     */
    public interface ChangeCounts {
        void changeItemCounts();
    }

    /**
     * 修改折后金额
     */
    public interface ChangeMoney {
        void changeItemMoney();
    }
}
