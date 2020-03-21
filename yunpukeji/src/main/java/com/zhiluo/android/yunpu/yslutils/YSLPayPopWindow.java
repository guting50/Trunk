package com.zhiluo.android.yunpu.yslutils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;

import java.util.List;

import butterknife.Bind;


/**
 * Created by ${YSL} on 2018-04-04.
 * 支付选择
 */


public class YSLPayPopWindow extends PopupWindow implements View.OnClickListener {

    private ImageView ivCash,ivBalance,ivAlipay,ivCard,ivWechat,ivSaoma,ivOther;
    private View mView;//PopWindow布局
    private LinearLayout mCash, mYue, mCard, mWeiXin, mZhiFuBao, mSaoma, mPos, lOther;//现金，余额，银联，微信，支付宝支付,Pos机支付
    private OnItemClickListener mListener;
    private RelativeLayout mUnion;//联合
    private TextView mPayNum, mCancel;
    private Context mContext;
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;


    /**
     * @param context  上下文
     * @param payTotal 需要支付的钱
     */
    public YSLPayPopWindow(Context context, String payTotal, List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity) {
        super(context);
        this.mContext = context;
        this.mSwitchEntity = mSwitchEntity;
        init(context);
        loadSwitch();
//        setWidget();
        setPopWindow();
        mPayNum.setText(payTotal);
        //        mPos.setOnClickListener(this);
    }


    private void loadSwitch() {
        if (mSwitchEntity.size() > 0) {
            for (int i = 0; i < mSwitchEntity.size(); i++) {
                //现金
                if ("101".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        ivCash.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ysl_cash_pay));
                    }else {
                        ivCash.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.cash_pay_f));
                    }
                }
                //余额
                if ("102".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        ivBalance.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ysl_yue_pay));
                    }else {
                        ivBalance.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.balance_pay_f));
                    }
                }
                //积分
//                if ("107".equals(mSwitchEntity.get(i).getSS_Code())) {
//                    if (mSwitchEntity.get(i).getSS_State() == 1) {
//                        ivCash.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ysl_yue_pay));
//                    }else {
//                        ivCash.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.balance_pay_f));
//                    }
//                }
                //银联
                if ("103".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        ivCard.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ysl_union_pay));
                    }else {
                        ivCard.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.union_card_f));
                    }
                }
                //支付宝记账
                if ("106".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        ivAlipay.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ysl_zhiufubao_pay));
                    }else {
                        ivAlipay.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.alipay_f));
                    }
                }
                //微信记账
                if ("105".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        ivWechat.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ysl_wei_pay));
                    }else {
                        ivWechat.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.wechat_f));
                    }
                }
                //扫码
                if ("111".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        ivSaoma.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ysl_saoma_pay));
                    }else {
                        ivSaoma.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.saoma_pay_f));
                    }
                }
                //其他支付
                if ("113".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        ivOther.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.other_pay_t));
                    }else {
                        ivOther.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.other_pay_f));
                    }
                }

            }
        }
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mView = inflater.inflate(R.layout.layout_pupwindos, null);
        mCash = mView.findViewById(R.id.l_cash);
        mYue = mView.findViewById(R.id.l_yue);
        mUnion = mView.findViewById(R.id.r_union);
        mCard = mView.findViewById(R.id.l_card);
        mWeiXin = mView.findViewById(R.id.l_weixin);
        mZhiFuBao = mView.findViewById(R.id.l_zhifubao);
        mSaoma = mView.findViewById(R.id.l_saoma);
        mPayNum = mView.findViewById(R.id.tv_pay_num);
        mCancel = mView.findViewById(R.id.tv_cancel);
        lOther = mView.findViewById(R.id.l_other);
        ivCash = mView.findViewById(R.id.iv_cash);
        ivBalance = mView.findViewById(R.id.iv_balance);
        ivAlipay = mView.findViewById(R.id.iv_alipay);
        ivCard = mView.findViewById(R.id.iv_card);
        ivWechat = mView.findViewById(R.id.iv_wechat);
        ivSaoma = mView.findViewById(R.id.iv_saoma);
        ivOther = mView.findViewById(R.id.iv_other);

        mCash.setOnClickListener(this);
        mYue.setOnClickListener(this);
        mUnion.setOnClickListener(this);
        mCard.setOnClickListener(this);
        mWeiXin.setOnClickListener(this);
        mZhiFuBao.setOnClickListener(this);
        mSaoma.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        lOther.setOnClickListener(this);
    }

    /**
     * 设置相关控件属性
     */
    private void setWidget() {
        //世麦机器
        if (MyApplication.IS_BASEWIN_POS_DEVICE) {
            //            mCard.setVisibility(View.INVISIBLE);
            mSaoma.setVisibility(View.INVISIBLE);
        }
        //SUNMI机器
        else if (MyApplication.IS_SUNMI_POS_P1_DEVICE) {
            //            mCard.setVisibility(View.INVISIBLE);
            mSaoma.setVisibility(View.INVISIBLE);
        }
        //拉卡拉机器
        else if (MyApplication.IS_LAKALA_POS_DEVICE) {
            mSaoma.setVisibility(View.VISIBLE);
        }
        //手机
        else {
            mSaoma.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 设置PopWindow相关属性
     */
    private void setPopWindow() {
        this.setContentView(mView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(RadioGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);//设置允许弹出窗口
        this.setAnimationStyle(R.style.pop_show_style);//设置弹出动画
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景透明
        mView.setOnTouchListener(new View.OnTouchListener() {//设置触摸位置在窗口外则销毁
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mView.findViewById(R.id.pop_layout) != null) {
                    int height = mView.findViewById(R.id.pop_layout).getTop();
                    int y = (int) motionEvent.getY();
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        if (y < height) {
                            dismiss();
                        }
                    }
                    return true;
                }
                return false;

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.setOnItemClick(view);
        }
    }


    public interface OnItemClickListener {
        void setOnItemClick(View v);
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }


}
