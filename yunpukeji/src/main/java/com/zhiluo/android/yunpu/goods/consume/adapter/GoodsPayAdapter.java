package com.zhiluo.android.yunpu.goods.consume.adapter;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.consume.activity.PayConfirmActivity;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.MoneyInputFilter;

import java.text.DecimalFormat;
import java.util.List;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ${YSL} on 2018-05-09.
 */

public class GoodsPayAdapter extends RecyclerView.Adapter<GoodsPayAdapter.MyHolder> {
    private Context mContext;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mDataList;
    private ChangeCounts changeCounts;
    private ChangeMoney changeMoney;
    private boolean isspxf;
    private String money = "";
    private PayConfirmActivity.ChangeHandler changeHandler;
    private double lsMoney = 0;
    private boolean isDiscount;
    public employeeClick memployeeClick;
    private imgicClick mimgicClick;
    private boolean isStaff;


    public GoodsPayAdapter(Context mContext, List<GoodsCheckResponseByType.DataBean.DataListBean> mDataList, boolean isspxf, PayConfirmActivity.ChangeHandler changeHandler,
                           boolean isDis, employeeClick memployeeClick, imgicClick mimgicClick, boolean isStaff) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        this.isspxf = isspxf;
        this.changeHandler = changeHandler;
        this.isDiscount = isDis;
        this.memployeeClick = memployeeClick;
        this.mimgicClick = mimgicClick;
        this.isStaff = isStaff;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_goods_pay, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        GoodsCheckResponseByType.DataBean.DataListBean bean = mDataList.get(position);

        Li( "==========onBindViewHolder=====random:" + new Gson().toJson(bean));


        if (isspxf) {
            if (bean.getPM_IsService() == 1 || bean.getPM_IsService() == 3) {
                holder.edCount.setInputType(InputType.TYPE_CLASS_NUMBER);
            } else {
                holder.edCount.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
            }
        } else {

        }

        if (isDiscount) {//当折扣开关开启
            holder.edMoney.setEnabled(true);
            holder.tvSingleMoney.setEnabled(true);
        } else {
            holder.edMoney.setEnabled(false);
            holder.tvSingleMoney.setEnabled(false);
        }

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
        holder.tvSingleMoney.setText(Decima2KeeplUtil.stringToDecimal(bean.getPM_UnitPrice() + ""));
        //数量
        if (isspxf) {
            if (bean.getNum() > 0) {
                holder.edCount.setText(Decima2KeeplUtil.stringToDecimal(bean.getNum() + ""));
            } else {
                holder.edCount.setText(0.00 + "");
            }
            //金额过滤器
            MoneyInputFilter filter = new MoneyInputFilter(7, true, "最多只能输入7位金额");
            filter.setMessage("最多只能输入7位金额");
            holder.edCount.setFilters(new InputFilter[]{filter});
        } else {
            if (bean.getNum() > 0) {
                holder.edCount.setText((int) bean.getNum() + "");
            } else {
                holder.edCount.setText(0 + "");
            }
        }
        /**9650商品消费》选择商品》选了会员有折扣，后在选择提成员工，就又变成原价了 20191030 cy */
        String moneyFlag = Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getEachMoney() * mDataList.get(position).getNum() + "");
        if (mDataList.get(position).getPM_SpecialOfferMoney() > 0) {
            moneyFlag = Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getPM_SpecialOfferMoney() * mDataList.get(position).getNum() + "");
        }
        holder.edMoney.setText(moneyFlag);
        Li( "==========1111=====random:" + moneyFlag);
        /************************************************************************ 20191030 cy */
        money = holder.edMoney.getText().toString();
        mDataList.get(position).setMoney_d(money);
        senMsgToActivity();

        //修改单价
        holder.tvSingleMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                changeHandler.sendEmptyMessage(1);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("") && s.toString() != null) {
                    if ((TextUtils.isEmpty(s.toString()) ? 0 : Double.parseDouble(s.toString())) < 0) {
                        CustomToast.makeText(mContext, "单价不能小于0", Toast.LENGTH_SHORT).show();
                        holder.tvSingleMoney.setText("0");
                    }

//                    double singleMoney = Double.parseDouble(holder.tvSingleMoney.getText().toString());

                    String moneyFlag = Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getEachMoney() * mDataList.get(position).getNum() + "");
                    if (mDataList.get(position).getPM_SpecialOfferMoney() > 0) {
                        moneyFlag = Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getPM_SpecialOfferMoney() * mDataList.get(position).getNum() + "");
                    }
                    double singleMoney =Double.parseDouble(moneyFlag);
                    try {
                        mDataList.get(position).setEachMoney(singleMoney);
//                        else {
//                            mDataList.get(position).setEachMoney(Double.parseDouble(money)/mDataList.get(position).getNum());
//                        }
                        holder.edMoney.setText(singleMoney * mDataList.get(position).getNum() + "");
                        Li( "==========222=====random:" + (singleMoney * mDataList.get(position).getNum() + ""));
                        mDataList.get(position).setZHMoney(singleMoney * mDataList.get(position).getNum());
                        mDataList.get(position).setMoney_d(singleMoney * mDataList.get(position).getNum()+"");
                    } catch (Exception e) {
                    }

                } else {
                    mDataList.get(position).setZHMoney(0.00);
                    mDataList.get(position).setEachMoney(0.00);
//                        holder.edMoney.setText(0.00+"");
                }

                senMsgToActivity();
            }
        });


        //检测修改的折后金额
        holder.edMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                changeHandler.sendEmptyMessage(1);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("") && s.toString() != null) {
                    double price = mDataList.get(position).getPM_UnitPrice() * mDataList.get(position).getNum();
                    /**9650商品消费》选择商品》选了会员有折扣，后在选择提成员工，就又变成原价了 20191030 cy */
                    double priceFlag=price;
                    if (mDataList.get(position).getPM_SpecialOfferMoney() > 0) {
                        price = mDataList.get(position).getPM_SpecialOfferMoney() * mDataList.get(position).getNum();
                    }
                    DecimalFormat df = new DecimalFormat("#.00");
                    //折后价格
                    double discountPrice = Double.parseDouble(df.format(Double.parseDouble(s.toString())));
                    //原价(也包含活动打折)
                    double costPrice = Double.parseDouble(df.format(price));
                    if (discountPrice > costPrice) {
                        if (!(discountPrice == priceFlag)) {
                            CustomToast.makeText(mContext, "折后金额不能大于原价", Toast.LENGTH_SHORT).show();
                        }
                        holder.edMoney.setText(Decima2KeeplUtil.stringToDecimal(price + ""));
                        Li( "==========3333=====random:" + (Decima2KeeplUtil.stringToDecimal(price + "")));
                    }
//                    if ((TextUtils.isEmpty(s.toString()) ? 0 : Double.parseDouble(s.toString())) > Double.parseDouble(Decima2KeeplUtil.stringToDecimal(price + ""))) {
//                        CustomToast.makeText(mContext, "折后金额不能大于原价", Toast.LENGTH_SHORT).show();
//                        holder.edMoney.setText(Decima2KeeplUtil.stringToDecimal(price + ""));
//                    }
                    /************************************************************************ 20191030 cy */

                    money = holder.edMoney.getText().toString();
                    mDataList.get(position).setMoney_d(money);
                    try {
                        if (mDataList.get(position).getNum() > 0) {
                            mDataList.get(position).setEachMoney(Double.parseDouble(money) / mDataList.get(position).getNum());
                        }
//                        else {
//                            mDataList.get(position).setEachMoney(Double.parseDouble(money)/mDataList.get(position).getNum());
//                        }

                        mDataList.get(position).setZHMoney(Double.parseDouble(money));

                    } catch (Exception e) {
                    }

                } else {
                    mDataList.get(position).setZHMoney(0.00);
                    mDataList.get(position).setEachMoney(0.00);
//                        holder.edMoney.setText(0.00+"");
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
                if (s.toString().equals("") || TextUtils.isEmpty(s.toString()) || s.toString().equals(".0")) {
                    mDataList.get(position).setNum(0);

                    /**9650商品消费》选择商品》选了会员有折扣，后在选择提成员工，就又变成原价了 20191030 cy */
                    String moneyFlag = Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getEachMoney() * mDataList.get(position).getNum() + "");
                    if (mDataList.get(position).getPM_SpecialOfferMoney() > 0) {
                        moneyFlag = Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getPM_SpecialOfferMoney() * mDataList.get(position).getNum() + "");
                    }
                    holder.edMoney.setText(moneyFlag);
                    Li( "==========4444=====random:" + moneyFlag);
//                    holder.edMoney.setText(Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getEachMoney() * mDataList.get(position).getNum() + ""));
                    /************************************************************************ 20191030 cy */
//                    holder.edCount.setText("0");
                    CustomToast.makeText(mContext, "商品数量须大于0", Toast.LENGTH_SHORT).show();
                } else if (Double.parseDouble(s.toString()) >= 10000) {
                    holder.edCount.setText("9999");
                    CustomToast.makeText(mContext, "商品数量不能超过一万", Toast.LENGTH_SHORT).show();
                } else {
                    mDataList.get(position).setNum(Double.parseDouble(s.toString()));

                    /**9650商品消费》选择商品》选了会员有折扣，后在选择提成员工，就又变成原价了 20191030 cy */
                    String moneyFlag = Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getEachMoney() * mDataList.get(position).getNum() + "");
                    if (mDataList.get(position).getPM_SpecialOfferMoney() > 0) {
                        moneyFlag = Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getPM_SpecialOfferMoney() * mDataList.get(position).getNum() + "");
                    }
                    holder.edMoney.setText(moneyFlag);
                    Li( "==========5555=====random:" + moneyFlag);
//                    double nowmoney = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getEachMoney() * mDataList.get(position).getNum() + ""));
//                    holder.edMoney.setText(nowmoney + "");
                    /************************************************************************ 20191030 cy */

                    mDataList.get(position).setZHMoney(Double.parseDouble(holder.edMoney.getText().toString()));
                }
                money = holder.edMoney.getText().toString();
                mDataList.get(position).setZHMoney(Double.parseDouble(money));
                mDataList.get(position).setMoney_d(money);
                senMsgToActivity();
            }
        });
        holder.edCount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {//获得焦点

                } else {//失去焦点
                    if (holder.edCount.getText().toString().equals("") || TextUtils.isEmpty(holder.edCount.getText().toString()) || holder.edCount.getText().toString().equals(".0")) {
//                        holder.edCount.setText("0");
                        mDataList.get(position).setNum(0);
//                        if (!isVip) {
                        /**9650商品消费》选择商品》选了会员有折扣，后在选择提成员工，就又变成原价了 20191030 cy */
                        String moneyFlag = Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getEachMoney() * mDataList.get(position).getNum() + "");
                        if (mDataList.get(position).getPM_SpecialOfferMoney() > 0) {
                            moneyFlag = Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getPM_SpecialOfferMoney() * mDataList.get(position).getNum() + "");
                        }
                        holder.edMoney.setText(moneyFlag);
                        Li( "==========666=====random:" + moneyFlag);
//                        holder.edMoney.setText(Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getEachMoney() * mDataList.get(position).getNum() + ""));
                        /************************************************************************ 20191030 cy */
//
                    } else if (Double.parseDouble(holder.edCount.getText().toString()) >= 10000) {
                        holder.edCount.setText("9999");
                        mDataList.get(position).setNum(9999);
                        /**9650商品消费》选择商品》选了会员有折扣，后在选择提成员工，就又变成原价了 20191030 cy */
                        String moneyFlag = Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getEachMoney() * mDataList.get(position).getNum() + "");
                        if (mDataList.get(position).getPM_SpecialOfferMoney() > 0) {
                            moneyFlag = Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getPM_SpecialOfferMoney() * mDataList.get(position).getNum() + "");
                        }
                        holder.edMoney.setText(moneyFlag);
                        Li( "==========777=====random:" + moneyFlag);
//                        holder.edMoney.setText(Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getEachMoney() * mDataList.get(position).getNum() + ""));
                        /************************************************************************ 20191030 cy */
                    }
                }
                money = holder.edMoney.getText().toString();
                mDataList.get(position).setZHMoney(Double.parseDouble(money));
                mDataList.get(position).setMoney_d(money);
                senMsgToActivity();
            }
        });
        holder.edMoney.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {//获得焦点

                } else {//失去焦点
                    if (holder.edMoney.getText().toString().equals("") || TextUtils.isEmpty(holder.edMoney.getText().toString()) ? true : Double.parseDouble(holder.edMoney.getText().toString()) < 0.000001) {
//                        holder.edMoney.setText("0");
                        mDataList.get(position).setZHMoney(0);
                        /**9650商品消费》选择商品》选了会员有折扣，后在选择提成员工，就又变成原价了 20191030 cy */
                        String moneyFlag = Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getEachMoney() * mDataList.get(position).getNum() + "");
                        if (mDataList.get(position).getPM_SpecialOfferMoney() > 0) {
                            moneyFlag = Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getPM_SpecialOfferMoney() * mDataList.get(position).getNum() + "");
                        }
                        holder.edMoney.setText(moneyFlag);
                        Li( "==========8888=====random:" + moneyFlag);
//                        holder.edMoney.setText(Decima2KeeplUtil.stringToDecimal(mDataList.get(position).getEachMoney() * mDataList.get(position).getNum() + ""));
                        /************************************************************************ 20191030 cy */
                    }
                }
                money = holder.edMoney.getText().toString();
                mDataList.get(position).setZHMoney(Double.parseDouble(money));
                mDataList.get(position).setEachMoney(Double.parseDouble(money) / mDataList.get(position).getNum());
                mDataList.get(position).setMoney_d(money);
                senMsgToActivity();
            }
        });

        if (isStaff) {
            holder.tvEmploeeName.setVisibility(View.VISIBLE);
            holder.emImage.setVisibility(View.VISIBLE);
        } else {
            holder.tvEmploeeName.setVisibility(View.GONE);
            holder.emImage.setVisibility(View.GONE);
        }
        if (bean.getEmployeeValue() != null && !bean.getEmployeeValue().equals("")) {
            holder.tvEmploeeName.setText(bean.getEmployeeValue() + "");
        } else {
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

    private void senMsgToActivity() {
        Message message = Message.obtain();
        message.what = 1;
        message.obj = mDataList;
        changeHandler.sendMessage(message);
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
        private TextView tvGoodsName, tvEmploeeName;
        private TextView tvSingleMoney;
        private EditText edCount;
        private EditText edMoney;
        private ImageView emImage;
        private RelativeLayout rCounts;
        private RelativeLayout rMoney;

        public MyHolder(View itemView) {
            super(itemView);
            ivGoodsLogo = itemView.findViewById(R.id.img_add_goods);
            tvGoodsName = itemView.findViewById(R.id.tv_goods_name);
            tvEmploeeName = itemView.findViewById(R.id.tv_employee_name);
            emImage = itemView.findViewById(R.id.img_employee_all);
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

    public interface imgicClick {
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
