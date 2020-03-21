package com.zhiluo.android.yunpu.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DatePickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.analysis.activity.GoodAnalysisActivity;
import com.zhiluo.android.yunpu.analysis.activity.MemberAnalysisActivity;
import com.zhiluo.android.yunpu.analysis.bean.StatisticsBean;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.entity.ShopListBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.myview.BezierView;
import com.zhiluo.android.yunpu.myview.CircleView;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.statistics.account.activity.MemberAccountActivity;
import com.zhiluo.android.yunpu.statistics.handover.HandOverReportActivity;
import com.zhiluo.android.yunpu.statistics.order.activity.OrderActivity;
import com.zhiluo.android.yunpu.statistics.rebate.activity.RebateActivity;
import com.zhiluo.android.yunpu.statistics.staff.activity.StaffReportActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.SaveScreenStateUtil;
import com.zhiluo.android.yunpu.yslutils.DataUtils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${YSL} on 2018-05-21.
 * 综合统计
 */

public class SummaryFragment extends Fragment implements View.OnClickListener {
    private TextView tvToday, tvThisWeekend, tvYesterday, tvOther;
    private TextView tvCrashin, tvSaoMain, tvUnionin, tvWeiXinin, tvZhiFuBaoin, tvOtherin;
    private TextView tvGoodsShopin, tvFastShopin, tvGoodsTuiHuoin, tvYueShopIn;
    private TextView tvVipCreateCardin, tvVipRechargein, tvVipInflatein, tvVipKouKuanin, tvVipDelayin;
    private TextView tvTotalIn, tvShouRuIn, tvShaoFeiIn, tvVipIn;
    private ImageView ivMore;
    private Spinner mSpinner;
    private PopupWindow popupWindow;
    private LinearLayout mLlDateSelector;
    private RelativeLayout mRlStore;
    private Button btnQuerry;
    private TextView tvStartTime, tvEndTime;
    private RelativeLayout r1, r2, r3, r4, r5, r6, r7;
    private Intent mIntent;
    private int mFlag = 1;//1.今天；2.昨天；3.本周；4本月.5最近30天；6.其它
    private StatisticsBean mStatisticsBean;
    private String mStartTime, mEndTime;
    private String shopGid;
    private int pos;
    private ArrayList<String> mStoreList;//店铺
    private LoginUpbean mLoginBean;
    private ArrayAdapter<String> mSpinnerAdapter;
    private ShopListBean mShopListBean;
    private SaveScreenStateUtil mStateUtil;
    private Dialog dateDialog;
    private double mIncome;//收入统计
    private double mShop;//消费统计
    private double mVip;//会员统计
    /**
     * 贝塞尔曲线
     */
    private List<String> dayList;
    private BezierView beiSaierView;
    private List<Point> pointList;
    private double mSomedayMoneyIn;
    private List<Double> yValueList;
    private List<Integer> yPointlist;
    private int whichOne = 0;
    private double maxPoint;
    private double minPoint;
    private int beforedays = 7;

    /**
     * 环形曲线
     */
    private CircleView circleView;

    private RelativeLayout rl_start_end_time;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                if (msg.what == 1) {
                    updateView();
                    //重绘环形图
                    circleView.setActivity(false);
                    circleView.setArrPer(calculateMoney());
                    circleView.postInvalidate();
                } else if (msg.what == 2) {
                    //重绘贝塞尔曲线
                    initPoint();
                    beiSaierView.setDayList(dayList);
                    drawBezierView();
                    beiSaierView.postInvalidate();
                }
            } catch (Exception e) {

            }
        }
    };


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        mStateUtil = new SaveScreenStateUtil(getContext());
        loadData();
        initPoint();
        initDay();
        beiSaierView.setDayList(dayList);
        drawBezierView();

    }


    private void initDay() {
        dayList = new ArrayList<>();
        for (int i = 6; i >= 0; i--) {
            dayList.add(DataUtils.getDateBefore(i).substring(5, 10));
        }
    }

    private void initPoint() {
        pointList = new ArrayList<>();
        int width = getPhoneWidth();
        pointList.add(new Point(width * 1 / 14, 200));
        pointList.add(new Point(width * 3 / 14, 150));
        pointList.add(new Point(width * 5 / 14, 300));
        pointList.add(new Point(width * 7 / 14, 198));
        pointList.add(new Point(width * 9 / 14, 200));
        pointList.add(new Point(width * 11 / 14, 250));
        pointList.add(new Point(width * 13 / 14, 99));
        beiSaierView.setPointList(pointList);


        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    shopGid = "";
                } else {
                    shopGid = mShopListBean.getData().get(position - 1).getGID();
                }

                getData(mStartTime, mEndTime);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void drawBezierView() {
        try {
            beiSaierView.setLineSmoothness(0.25f);
        } catch (NumberFormatException e) {
        }
        beiSaierView.startAnimation(2000);
    }

    /**
     * @return 获取曲线view的整体布局高度，这里我设置的300dp，当然根据需要可以自己调整，不过同时也要调整自定义曲线中的测量方法中的高度；
     */
    private int getpx() {
        return dp2px(getContext(), 300);
    }

    /**
     * dp转换成px
     */
    private int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * @return , 手机屏幕宽度
     */
    private int getPhoneWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) MyApplication.getmContext().getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    private void loadData() {
        shopGid = getArguments().getString("StoreGid");
        mFlag = 1;
        getData(mStartTime, mEndTime);
        getLatest7Day();

        mStoreList = new ArrayList<>();
        mStoreList.add("全部");
        mShopListBean = (ShopListBean) CacheData.restoreObject("shop");
        mLoginBean = (LoginUpbean) CacheData.restoreObject("LG");
        if (mShopListBean != null) {
            for (int i = 0; i < mShopListBean.getData().size(); i++) {
                mStoreList.add(mShopListBean.getData().get(i).getSM_Name());
                if (mLoginBean != null) {
                    if (mLoginBean.getData().getShopID().equals(mShopListBean.getData().get(i).getGID())) {
                        pos = i + 1;
                    }
                    if (shopGid != null && shopGid.equals(mShopListBean.getData().get(i).getGID())) {
                        pos = i + 1;
                    }
                }
            }
        }
        mSpinnerAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.item_spinner, R.id.tv_item_spinner, mStoreList);
        mSpinner.setAdapter(mSpinnerAdapter);
        mSpinner.setSelection(pos);
        if (mLoginBean.getData().getUM_IsAmin() == 1) {//判断是不是超管
            mSpinner.setEnabled(true);
        } else {
            mSpinner.setEnabled(false);
        }

    }

    private void getData(String startTime, String endTime) {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(getContext());
        client.setCookieStore(cookieStore);
        RequestParams params = new RequestParams();
        params.put("Flag", mFlag);
        params.put("SM_GID", shopGid);
        if (startTime != null && !"".equals(startTime) && endTime != null && !"".equals(endTime)) {
            params.put("StartTime", startTime);
            params.put("EndTime", endTime);
        }
        client.post(HttpAPI.API().SHIFT_AMOUNT, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mStatisticsBean = CommonFun.JsonToObj(responseString, StatisticsBean.class);
                if (mStatisticsBean != null) {
                    mHandler.sendEmptyMessage(1);
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showPop(View v) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.pop_summary, null);
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        popupWindow = new PopupWindow(getContext());
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(width / 2);
        popupWindow.setHeight(height * 11 / 20);
        popupWindow.showAsDropDown(v);
        r1 = view.findViewById(R.id.mean_r1);
        r2 = view.findViewById(R.id.mean_r2);
        r3 = view.findViewById(R.id.mean_r3);
        r4 = view.findViewById(R.id.mean_r4);
        r5 = view.findViewById(R.id.mean_r5);
        r6 = view.findViewById(R.id.mean_r6);
        r7 = view.findViewById(R.id.mean_r7);
        r1.setOnClickListener(this);
        r2.setOnClickListener(this);
        r3.setOnClickListener(this);
        r4.setOnClickListener(this);
        r5.setOnClickListener(this);
        r6.setOnClickListener(this);
        r7.setOnClickListener(this);

    }

    private void initViews(View view) {
        //贝塞尔曲线
        beiSaierView = view.findViewById(R.id.bezier);
        //环形比例图
        circleView = view.findViewById(R.id.circle_view);

        tvTotalIn = view.findViewById(R.id.tv_toatal_in);
        tvToday = view.findViewById(R.id.tv_today);
        tvThisWeekend = view.findViewById(R.id.tv_thisweekend);
        tvYesterday = view.findViewById(R.id.tv_yesterday);
        tvOther = view.findViewById(R.id.tv_other);
        ivMore = view.findViewById(R.id.iv_more);
        //显示各个统计
        tvShouRuIn = view.findViewById(R.id.tv_shouru_summmary);
        tvShaoFeiIn = view.findViewById(R.id.tv_xiaofei_summary);
        tvVipIn = view.findViewById(R.id.vip_summary_in);
        //店铺选择
        mRlStore = view.findViewById(R.id.rl_store);
        mSpinner = view.findViewById(R.id.sp_goods_order_store);
        /*时间选择*/
        mLlDateSelector = view.findViewById(R.id.ll_statistics_time_other);
        tvStartTime = view.findViewById(R.id.tv_start_date);
        tvEndTime = view.findViewById(R.id.tv_end_date);
        btnQuerry = view.findViewById(R.id.btn_date_confirm);
        /*收入统计*/
        tvCrashin = view.findViewById(R.id.tv_crash_in);
        tvSaoMain = view.findViewById(R.id.tv_shaoma_in);
        tvUnionin = view.findViewById(R.id.tv_union_in);
        tvWeiXinin = view.findViewById(R.id.tv_weixin_in);
        tvZhiFuBaoin = view.findViewById(R.id.tv_zhifubao_in);
        tvOtherin = view.findViewById(R.id.tv_other_in);
        /*消费统计*/
        tvGoodsShopin = view.findViewById(R.id.tv_goods_sale_in);
        tvFastShopin = view.findViewById(R.id.tv_fastshop_in);
        tvGoodsTuiHuoin = view.findViewById(R.id.tv_goodsback_in);
        tvYueShopIn = view.findViewById(R.id.tv_yue_in);
        /*会员统计*/
        tvVipCreateCardin = view.findViewById(R.id.tv_vip_createcard_in);
        tvVipRechargein = view.findViewById(R.id.tv_vip_recharge_in);
        tvVipInflatein = view.findViewById(R.id.tv_vip_inflate_count_in);
        tvVipKouKuanin = view.findViewById(R.id.tv_vip_koukuan_in);
        tvVipDelayin = view.findViewById(R.id.tv_vip_delay_in);
        rl_start_end_time = view.findViewById(R.id.rl_start_end_time);

        /*注册监听*/
        tvToday.setOnClickListener(this);
        tvThisWeekend.setOnClickListener(this);
        tvYesterday.setOnClickListener(this);
        tvOther.setOnClickListener(this);
        ivMore.setOnClickListener(this);
        /*时间选择*/
        tvStartTime.setOnClickListener(this);
        tvEndTime.setOnClickListener(this);
        btnQuerry.setOnClickListener(this);
    }


    private void updateView() {
        /**
         * 收入统计
         */
        //现金收入
        tvCrashin.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_CashTotal() + ""));
        //银联收入
        tvUnionin.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_UnionTotal() + ""));
        //扫码收入
        tvSaoMain.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_BarCodeTotal() + ""));
        //微信收入
        tvWeiXinin.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_WeChatpayTotal() + ""));
        //支付宝收入
        tvZhiFuBaoin.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_AlipaypayTotal() + ""));
        //其它收入
        tvOtherin.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_OtherpayTotal() + ""));
        /**
         * 消费统计
         */
        //商品消费
        tvGoodsShopin.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_ConsumptionTotal() + ""));
        //快速消费
        tvFastShopin.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_FastConsumptionTotal() + ""));
        //商品退货
        tvGoodsTuiHuoin.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_ReturnGoodsTotal() + ""));
        //余额消费
        double yueConsume = mStatisticsBean.getData().getSA_ChargeBalance() + mStatisticsBean.getData().getSA_ComboBalance() + mStatisticsBean.getData().getSA_ConsumptionBalance() + mStatisticsBean.getData().getSA_DeductionBalance() + mStatisticsBean.getData().getSA_DelayBalance() + mStatisticsBean.getData().getSA_HouseBalance() + mStatisticsBean.getData().getSA_TimeBalance() + mStatisticsBean.getData().getSA_FastConsumptionBalance() - mStatisticsBean.getData().getSA_ReturnGoodsBalance();
        double yueTotal = mStatisticsBean.getData().getSA_BalanceTotal();
        tvYueShopIn.setText(Decima2KeeplUtil.stringToDecimal(yueConsume + ""));
        /**
         *会员统计
         */
        //会员开卡
        tvVipCreateCardin.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_OpenCardTotal() + ""));
        //会员充值
        tvVipRechargein.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_RechargeTotal() + ""));
        //会员充次
        tvVipInflatein.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_ChargeTotal() + ""));
        //会员扣款
//        tvVipKouKuanin.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getLastBalance() + ""));
        //会员延期
        tvVipDelayin.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_DelayTotal() + ""));

    }

    /**
     * 计算三类金额
     */
    private float[] calculateMoney() {
        //会员统计
        float c = Float.parseFloat(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_DelayTotal() + mStatisticsBean.getData().getSA_ChargeTotal() +
                mStatisticsBean.getData().getSA_RechargeTotal() + mStatisticsBean.getData().getSA_OpenCardTotal() + ""));
        double f = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_DelayTotal() + mStatisticsBean.getData().getSA_ChargeTotal() +
                mStatisticsBean.getData().getSA_RechargeTotal() + mStatisticsBean.getData().getSA_OpenCardTotal() + ""));
        //消费统计
        float b = Float.parseFloat(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_ConsumptionTotal() + mStatisticsBean.getData().getSA_FastConsumptionTotal() -
                mStatisticsBean.getData().getSA_ReturnGoodsTotal() + ""));
        double g = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_ConsumptionTotal() + mStatisticsBean.getData().getSA_FastConsumptionTotal() -
                mStatisticsBean.getData().getSA_ReturnGoodsTotal() + ""));

        //收入统计
        float a = Float.parseFloat(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_CashTotal() + mStatisticsBean.getData().getSA_UnionTotal() + mStatisticsBean.getData().getSA_BalanceTotal() +
                mStatisticsBean.getData().getSA_WeChatpayTotal() + mStatisticsBean.getData().getSA_AlipaypayTotal() + mStatisticsBean.getData().getSA_OtherpayTotal() + ""));
        double e = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_CashTotal() + mStatisticsBean.getData().getSA_UnionTotal() +
                mStatisticsBean.getData().getSA_WeChatpayTotal() + mStatisticsBean.getData().getSA_AlipaypayTotal() + mStatisticsBean.getData().getSA_OtherpayTotal() + mStatisticsBean.getData().getSA_BarCodeTotal() + ""));


        float d = a + b + c;

        tvShouRuIn.setText(Decima2KeeplUtil.stringToDecimal(e + ""));
        tvShaoFeiIn.setText(Decima2KeeplUtil.stringToDecimal(g + ""));
        tvVipIn.setText(Decima2KeeplUtil.stringToDecimal(f + ""));
        tvTotalIn.setText(Decima2KeeplUtil.stringToDecimal(e + ""));

        if (d == 0.0f) {
            // circleView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            return new float[]{0.0f};
        } else {
            if (this.isAdded()) {
                circleView.setBackgroundColor(getResources().getColor(R.color.white));
            }
            //  Li("a--" + a + "--b---" + b + "--c---" + c + "--d----" + d + "----a/d---" + a / d + "--b/d---" + b / d + "----c/d----" + c / d);
            return new float[]{float2((a / d) * 100), float2((b / d) * 100), float2((c / d) * 100)};
        }
    }

    /**
     * @param f ,
     * @return 保留两位小数
     */
    public float float2(float f) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format(f));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_today:
                mFlag = 1;
//                mLlDateSelector.setVisibility(View.GONE);
                rl_start_end_time.setVisibility(View.GONE);
//                mRlStore.setVisibility(View.GONE);
                getData(mStartTime, mEndTime);
                setDayColor(0);
                break;
            case R.id.tv_yesterday:
                mFlag = 2;
//                mLlDateSelector.setVisibility(View.GONE);
                rl_start_end_time.setVisibility(View.GONE);
//                mRlStore.setVisibility(View.GONE);
                getData(mStartTime, mEndTime);
                setDayColor(1);
                break;
            case R.id.tv_thisweekend:
                mFlag = 3;

                mStartTime = DateTimeUtil.getNowWeekFirstDate();
                mEndTime = DateTimeUtil.getNowWeekFinalDate();
//                mLlDateSelector.setVisibility(View.GONE);
                rl_start_end_time.setVisibility(View.GONE);
//                mRlStore.setVisibility(View.GONE);
                getData(mStartTime, mEndTime);
                setDayColor(2);
                break;
            case R.id.tv_other:
                mFlag = 6;
//                mLlDateSelector.setVisibility(View.VISIBLE);
                rl_start_end_time.setVisibility(View.VISIBLE);
//                mRlStore.setVisibility(View.VISIBLE);
                setDayColor(3);
                break;
            case R.id.iv_more:
                showPop(v);
                break;
            case R.id.mean_r1://商品分析
                popupWindow.dismiss();
                mIntent = new Intent(getActivity(), GoodAnalysisActivity.class);
                startActivity(mIntent);
                break;
            case R.id.mean_r2://会员分析
                popupWindow.dismiss();
                mIntent = new Intent(getActivity(), MemberAnalysisActivity.class);
                startActivity(mIntent);
                break;
            case R.id.mean_r3://会员账户
                popupWindow.dismiss();
                mIntent = new Intent(getActivity(), MemberAccountActivity.class);
                startActivity(mIntent);
                break;
            case R.id.mean_r4://订单记录
                popupWindow.dismiss();
                mIntent = new Intent(getActivity(), OrderActivity.class);
                startActivity(mIntent);
                break;
            case R.id.mean_r5://员工提成
                popupWindow.dismiss();
                mIntent = new Intent(getActivity(), StaffReportActivity.class);
                startActivity(mIntent);
                break;
            case R.id.mean_r6://推荐返利
                popupWindow.dismiss();
                mIntent = new Intent(getActivity(), RebateActivity.class);
                startActivity(mIntent);
                break;
            case R.id.mean_r7://交班
                popupWindow.dismiss();
                mIntent = new Intent(getActivity(), HandOverReportActivity.class);
                startActivity(mIntent);
                break;
            case R.id.tv_start_date:

                if (tvStartTime.getText().toString() == null || tvStartTime.getText().toString().equals("")) {
//                    showDatePickDlg(view,DateUtil.getDateForString(DateTimeUtil.getNowDate()));
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvStartTime, 0);
                } else {
                    showDateDialog(DateUtil.getDateForString(tvStartTime.getText().toString()), tvStartTime, 0);
                }
//                showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvStartTime, 0);
                break;
            case R.id.tv_end_date:

                if (tvEndTime.getText().toString() == null || tvEndTime.getText().toString().equals("")) {
//                    showDatePickDlg(view,DateUtil.getDateForString(DateTimeUtil.getNowDate()));
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvEndTime, 1);
                } else {
                    showDateDialog(DateUtil.getDateForString(tvEndTime.getText().toString()), tvEndTime, 1);
                }
//                showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvEndTime, 1);
                break;
            case R.id.btn_date_confirm:
//                mLlDateSelector.setVisibility(View.GONE);
                mStartTime = tvStartTime.getText().toString();
                mEndTime = tvEndTime.getText().toString();

                if (mStartTime.isEmpty()) {
                    CustomToast.makeText(getContext(), "请选择开始时间！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mEndTime.isEmpty()) {
                    CustomToast.makeText(getContext(), "请选择结束时间！", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    if (!mStartTime.isEmpty() && !mEndTime.isEmpty()) {

                        if (!DateTimeUtil.isCurTime(getActivity(), mStartTime)) {
                            return;
                        } else if (!DateTimeUtil.isCurTime(getActivity(), mEndTime)) {
                            return;
                        }
                        if (DateTimeUtil.isOverTime(getActivity(), mStartTime, mEndTime)) {
                            mFlag = 6;
                            getData(mStartTime, mEndTime);
                        }
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    /**
     * @return , 获得要绘制的具体y坐标点位置
     */
    private void getEachHeight() {
        yPointlist = new ArrayList<>();
        double flags = maxPoint - minPoint;
        int Y0 = 250;
        yPointlist = new ArrayList<>();
        for (int i = 0; i < yValueList.size(); i++) {
            if (yValueList.get(i) == getMinYPoint()) {
                yPointlist.add(Y0 + 300);
            } else if (yValueList.get(i) == getMaxYPoint()) {
                yPointlist.add(Y0);
            } else {
                yPointlist.add((int) (Y0 + (1 - ((yValueList.get(i) - getMinYPoint()) / flags)) * 300));
            }
        }
    }

    /**
     * @return , 获取七天中最大值
     */
    private double getMaxYPoint() {
        double max = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = i + 1; j < 7; j++) {
                max = Math.max(yValueList.get(i), yValueList.get(j));
            }
        }
        return max;
    }

    /**
     * @return , 获取七天中最小值
     */
    private double getMinYPoint() {
        double min = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = i + 1; j < 7; j++) {
                min = Math.min(yValueList.get(i), yValueList.get(j));
            }
        }
        return min;
    }

    /**
     * @return , 查询最近七天，每一天的总收入, 0是前面第6天，1是前面第5天...6是今天
     */
    private void getLatest7Day() {
        yValueList = new ArrayList<>();
        for (; beforedays > 0; beforedays--) {
            RequestParams params = new RequestParams();
            params.put("Flag", 6);
            params.put("SM_GID", shopGid);
            params.put("StartTime", DataUtils.getDateBefore(beforedays).substring(0, 10));
            params.put("EndTime", DataUtils.getDateBefore(beforedays).substring(0, 10));
            HttpHelper.post(getContext(), HttpAPI.API().SHIFT_AMOUNT, params, new CallBack() {
                @Override
                public void onSuccess(String responseString, Gson gson) {
                    mStatisticsBean = CommonFun.JsonToObj(responseString, StatisticsBean.class);
                    if (mStatisticsBean != null) {
                        mSomedayMoneyIn = mStatisticsBean.getData().getSA_AllTotal();
                        yValueList.add(mSomedayMoneyIn);
                        if (beforedays == 1) {
                            mHandler.sendEmptyMessage(2);
                        }
                    }
                }

                @Override
                public void onFailure(String msg) {
                    CustomToast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * @param date     日期
     * @param textView textview
     * @param flags    0开始时间，1结束时间
     *                 选择时间
     */
    private void showDateDialog(List<Integer> date, final TextView textView, final int flags) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(getContext());
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {

                textView.setText(dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2])));
                if (flags == 0) {
                    mStartTime = textView.getText().toString();
                    DateTimeUtil.isCurTime(getActivity(), mStartTime);
                } else if (flags == 1) {
                    mEndTime = textView.getText().toString();
                    DateTimeUtil.isCurTime(getActivity(), mEndTime);
                }
            }

            @Override
            public void onCancel() {

            }
        })
                .setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1);
        dateDialog = builder.create();
        dateDialog.show();
    }

    private void setDayColor(int i) {
        tvToday.setTextColor(ContextCompat.getColor(getContext(), (i == 0) ? R.color.gold : R.color.white));
        tvYesterday.setTextColor(ContextCompat.getColor(getContext(), (i == 1) ? R.color.gold : R.color.white));
        tvThisWeekend.setTextColor(ContextCompat.getColor(getContext(), (i == 2) ? R.color.gold : R.color.white));
        tvOther.setTextColor(ContextCompat.getColor(getContext(), (i == 3) ? R.color.gold : R.color.white));
    }
}
