package com.zhiluo.android.yunpu.member.consume.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.consume.jsonbean.DiscountTypeBean;
import com.zhiluo.android.yunpu.member.consume.bean.InputBean;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ${YSL} on 2018-05-10.
 */

public class VariableMoneyAdapter extends RecyclerView.Adapter<VariableMoneyAdapter.MyHolder> {
    private List<DiscountTypeBean.DataBean> mRechargeTypeList;
    private Context mContext;
    private int type = 0;
    private OnOneItemClickListener oneClickListener;
    private OnTwoItemClickListener twoClickListener;
    private OnThreeItemClickListener threeClickListener;
    private MyHolder holder;
    @SuppressLint("HandlerLeak")
    public Handler vHandler;
    private AllMemberListBean.DataBean.DataListBean mMemberInfo;

    public VariableMoneyAdapter(List<DiscountTypeBean.DataBean> mRechargeTypeList, Context mContext, OnOneItemClickListener oneItemClickListener,
                                OnTwoItemClickListener twoItemClickListener, OnThreeItemClickListener threeItemClickListener, AllMemberListBean.DataBean.DataListBean mMemberInfo) {
        this.mRechargeTypeList = mRechargeTypeList;
        this.mContext = mContext;
        this.oneClickListener = oneItemClickListener;
        this.twoClickListener = twoItemClickListener;
        this.threeClickListener = threeItemClickListener;
        this.mMemberInfo = mMemberInfo;


    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder myHolder;
        View view = null;
        switch (viewType) {
            case 1:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_variable_money, null);
                break;
            case 2:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_variable_money_one, null);
                break;
            case 3:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_variable_money_two, null);
                break;
        }
        myHolder = new MyHolder(view);
        holder = myHolder;
        return myHolder;
    }


    @SuppressLint({"HandlerLeak", "ClickableViewAccessibility"})
    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        Li(type + "--type");
        switch (type) {
            case 1:
                    holder.tvMoney.setText(Decima2KeeplUtil.stringToDecimal(mRechargeTypeList.get(position).getRP_RechargeMoney() + "") + "元");
                    holder.tvDetail.setText("赠送" + Decima2KeeplUtil.stringToDecimal(mRechargeTypeList.get(position).getRP_GiveMoney() + "") + "元  ");
                    if (mRechargeTypeList.get(position).isChecked()) {
                        holder.lOne.setBackgroundResource(R.drawable.shape_vip_recharge_money);
                    } else {
                        holder.lOne.setBackgroundResource(R.drawable.ysl_shape_cancel);
                    }
                    holder.lOne.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            oneClickListener.oneItemClick(v, position);
                            Li("1发送---threeItemClick");
                        }
                    });
                /*else {
                    CustomToast.makeText(mContext,"请选择会员", Toast.LENGTH_SHORT).show();
                }*/

                break;
            case 2:
                holder.lTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        twoClickListener.twoItemClick(v);
                        Li("2发送---threeItemClick");
                    }
                });
                break;
            case 3:

                if (mRechargeTypeList.get(position).isChecked()) {
                    holder.lThree.setBackgroundResource(R.drawable.shape_vip_recharge_money);
                } else {
                    holder.lThree.setBackgroundResource(R.drawable.ysl_shape_cancel);
                    holder.edContent.setText("");
                }
                if (vHandler == null) {
                    vHandler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);
                            if (msg.what == 1) {
                                try {
                                    holder.edContent.setText(Decima2KeeplUtil.stringToDecimal((String) msg.obj));
                                } catch (Exception e) {
                                    holder.edContent.setText(Decima2KeeplUtil.stringToDecimal(((StringBuilder) msg.obj).toString()));
                                }
                                Li("接收-----holder.edContent");

                            }
                        }
                    };
                }
//                holder.edContent.setOnTouchListener(new View.OnTouchListener() {
//                    //按住和松开的标识
//                    int touch_flag = 0;
//
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        touch_flag++;
//                        if (touch_flag == 2) {
//                            touch_flag = 0;
//                            threeClickListener.threeItemClick((EditText) v);
//                            Li("3发送---threeItemClick");
//
//                        }
//                        return false;
//                    }
//                });

               holder.edContent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        threeClickListener.threeItemClick((TextView) v,position);
                        Li("3发送---threeItemClick");
                    }
                });


                break;
        }

    }


    @Override

    public int getItemViewType(int position) {
        if ((position + 1) <= mRechargeTypeList.size() && mRechargeTypeList.size() > 0) {
            type = 1;
        }
        if (mRechargeTypeList.size() == 0) {
            type = 2;
        }
        if ((position + 1) == mRechargeTypeList.size()) {
            type = 3;
        }
        return type;
    }


    @Override
    public int getItemCount() {
        return mRechargeTypeList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvMoney;//充值金额
        private TextView tvDetail;//充值描述
        private LinearLayout lOne;
        private LinearLayout lTwo;
        private LinearLayout lThree;
        private TextView edContent;

        public MyHolder(View itemView) {
            super(itemView);
            tvMoney = itemView.findViewById(R.id.tv_money);
            tvDetail = itemView.findViewById(R.id.tv_detail);
            lOne = itemView.findViewById(R.id.l_one);
            lTwo = itemView.findViewById(R.id.l_two);
            lThree = itemView.findViewById(R.id.l_three);
            edContent = itemView.findViewById(R.id.ed_content);
        }
    }

    public interface OnOneItemClickListener {
        void oneItemClick(View view, int position);
    }

    public interface OnTwoItemClickListener {
        void twoItemClick(View view);
    }

    public interface OnThreeItemClickListener {
        void threeItemClick(TextView view,int position);
    }


}
