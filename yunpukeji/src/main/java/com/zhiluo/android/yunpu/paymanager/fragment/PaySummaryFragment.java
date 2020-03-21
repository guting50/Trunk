package com.zhiluo.android.yunpu.paymanager.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.myview.BezierView;
import com.zhiluo.android.yunpu.paymanager.bean.PaySummaryBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.yslutils.DataUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ZPH on 2019-05-21.
 */

public class PaySummaryFragment extends Fragment {

    @Bind(R.id.tv_pay_day)
    TextView tvPayDay;
    @Bind(R.id.tv_day_summmary)
    TextView tvDaySummmary;
    @Bind(R.id.tv_pay_month)
    TextView tvPayMonth;
    @Bind(R.id.tv_month_summmary)
    TextView tvMonthSummmary;
    @Bind(R.id.tv_pay_year)
    TextView tvPayYear;
    @Bind(R.id.tv_year_summmary)
    TextView tvYearSummmary;
    @Bind(R.id.bezier)
    BezierView beiSaierView;
    @Bind(R.id.rl_pay_summary)
    LinearLayout rlPaySummary;

    /**
     * 贝塞尔曲线
     */
    private List<String> dayList;
    private int[] moneyList = {0,0,0,0,0,0,0,0,0,0,0,0};
    private List<Point> pointList;
    private PaySummaryBean mPaySummaryBean;


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                if (msg.what == 2) {
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay_summary, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initPoint();
        initDay();
        beiSaierView.setDayList(dayList);
        drawBezierView();

//        if (inputReceiver == null) {
//            inputReceiver = new PayInputFragment.InputReceiver();
//        }
//        IntentFilter filer1 = new IntentFilter();
//        filer1.addAction("android.intent.action.INPUT_BROADCAST");
//        getActivity().registerReceiver(inputReceiver, filer1);
    }

    private void initData() {
        GetExpensesDetailAnalysis();
//        initDay();
//        mHandler.sendEmptyMessage(2);
    }


    private void initDay() {
        dayList = new ArrayList<>();
        dayList.add("一月");
        dayList.add("二月");
        dayList.add("三月");
        dayList.add("四月");
        dayList.add("五月");
        dayList.add("六月");
        dayList.add("七月");
        dayList.add("八月");
        dayList.add("九月");
        dayList.add("十月");
        dayList.add("十一月");
        dayList.add("十二月");

//        for (int i = 6; i >= 0; i--) {
//            dayList.add(DataUtils.getDateBefore(i).substring(5, 10));
//        }
    }


    private void initPoint() {
        pointList = new ArrayList<>();
        int width = getPhoneWidth();
        for (int i = 1;i<13;i++){
            pointList.add(new Point(width * i / 13, moneyList[i-1]));
        }

//        pointList.add(new Point(width * 2 / 13, 150));
//        pointList.add(new Point(width * 3 / 13, 300));
//        pointList.add(new Point(width * 4 / 13, 198));
//        pointList.add(new Point(width * 5 / 13, 200));
//        pointList.add(new Point(width * 6 / 13, 250));
//        pointList.add(new Point(width * 7 / 13, 99));
//        pointList.add(new Point(width * 8 / 13, 99));
//        pointList.add(new Point(width * 9 / 13, 99));
//        pointList.add(new Point(width * 10 / 13, 99));
//        pointList.add(new Point(width * 11 / 13, 99));
//        pointList.add(new Point(width * 12 / 13, 99));

        beiSaierView.setPointList(pointList);

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void GetExpensesDetailAnalysis(){
        RequestParams params = new RequestParams();
        params.put("StartTime", DateTimeUtil.getNowYearFirstDate());
        params.put("EndTime",DateTimeUtil.getNowYearFinalDate());

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mPaySummaryBean = CommonFun.JsonToObj(responseString,PaySummaryBean.class);
                moneyList = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                String str = DateTimeUtil.getNowDate().substring(0,4);
                for (int i = 0;i<mPaySummaryBean.getData().size();i++){
                    if (mPaySummaryBean.getData().get(i).getED_Time().contains(str+"-01")){
                        moneyList[0] +=  (int)mPaySummaryBean.getData().get(i).getED_Money();
                    }else if (mPaySummaryBean.getData().get(i).getED_Time().contains(str+"-02")){
                        moneyList[1] +=  (int)mPaySummaryBean.getData().get(i).getED_Money();
                    }else if (mPaySummaryBean.getData().get(i).getED_Time().contains(str+"-03")){
                        moneyList[2] +=  (int)mPaySummaryBean.getData().get(i).getED_Money();
                    }else if (mPaySummaryBean.getData().get(i).getED_Time().contains(str+"-04")){
                        moneyList[3] +=  (int)mPaySummaryBean.getData().get(i).getED_Money();
                    }else if (mPaySummaryBean.getData().get(i).getED_Time().contains(str+"-05")){
                        moneyList[4] +=  (int)mPaySummaryBean.getData().get(i).getED_Money();
                    }else if (mPaySummaryBean.getData().get(i).getED_Time().contains(str+"-06")){
                        moneyList[5] +=  (int)mPaySummaryBean.getData().get(i).getED_Money();
                    }else if (mPaySummaryBean.getData().get(i).getED_Time().contains(str+"-07")){
                        moneyList[6] +=  (int)mPaySummaryBean.getData().get(i).getED_Money();
                    }else if (mPaySummaryBean.getData().get(i).getED_Time().contains(str+"-08")){
                        moneyList[7] +=  (int)mPaySummaryBean.getData().get(i).getED_Money();
                    }else if (mPaySummaryBean.getData().get(i).getED_Time().contains(str+"-09")){
                        moneyList[8] +=  (int)mPaySummaryBean.getData().get(i).getED_Money();
                    }else if (mPaySummaryBean.getData().get(i).getED_Time().contains(str+"-10")){
                        moneyList[9] +=  (int)mPaySummaryBean.getData().get(i).getED_Money();
                    }else if (mPaySummaryBean.getData().get(i).getED_Time().contains(str+"-11")){
                        moneyList[10] +=  (int)mPaySummaryBean.getData().get(i).getED_Money();
                    }else if (mPaySummaryBean.getData().get(i).getED_Time().contains(str+"-12")){
                        moneyList[11] +=  (int)mPaySummaryBean.getData().get(i).getED_Money();
                    }

                    //今日
                    if (mPaySummaryBean.getData().get(i).getED_Time().contains(DateTimeUtil.getNowDate())){

                    }
                    //本月
                    if (mPaySummaryBean.getData().get(i).getED_Time().contains(DateTimeUtil.getNowDate())){

                    }
                    //本年
                    if (mPaySummaryBean.getData().get(i).getED_Time().contains(DateTimeUtil.getNowDate())){

                    }

                }

                mHandler.sendEmptyMessage(2);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(getActivity(),msg, Toast.LENGTH_SHORT).show();

            }
        };
        HttpHelper.post(getActivity(), HttpAPI.API().Expenses_DetailAnalysis,params,callBack);
    }



}
