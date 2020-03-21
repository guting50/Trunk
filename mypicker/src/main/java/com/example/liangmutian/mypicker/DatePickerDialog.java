package com.example.liangmutian.mypicker;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class DatePickerDialog extends Dialog {

    private static  int MIN_YEAR = 1901;
    private static  int MAX_YEAR = 2100;
    private Params params;

    public DatePickerDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    private void setParams(DatePickerDialog.Params params) {
        this.params = params;
    }

    public interface OnDateSelectedListener {
         void onDateSelected(int[] dates) throws ParseException;
         void onCancel();
    }



    private static final class Params {
        private boolean shadow = true;
        private boolean canCancel = true;
        private LoopView loopYear, loopMonth, loopDay,loopHour,loopMin,loopSec;
        private OnDateSelectedListener   callback;
    }

    public static class Builder {
        private final Context context;
        private final DatePickerDialog.Params params;
        private Integer minYear;
        private Integer maxYear;
        private Integer selectYear;
        private Integer selectMonth;
        private Integer selectDay;
        private Integer minMonth;
        private Integer maxMonth;
        private Integer minDay;
        private Integer maxDay;
        private Integer selecthour;
        private Integer selectmin;
        private Integer selectsec;
        private Integer isdetail;

        public Integer getIsdetail() {
            return isdetail;
        }

        public void setIsdetail(Integer isdetail) {
            this.isdetail = isdetail;
        }
        public Integer getSelecthour() {
            return selecthour;
        }

        public Builder setSelecthour(int selecthour) {
            this.selecthour = selecthour;
            return this;
        }

        public Integer getSelectmin() {
            return selectmin;
        }

        public Builder setSelectmin(int selectmin) {
            this.selectmin = selectmin;
            return this;
        }

        public Integer getSelectsec() {
            return selectsec;
        }

        public Builder setSelectsec(int selectsec) {
            this.selectsec = selectsec;
            return this;
        }
        public Builder(Context context) {
            this.context = context;
            params = new DatePickerDialog.Params();
        }

        public Builder setMinYear(int year){
            minYear=year;
            return this;
        }

        public Builder setMaxYear(int year){
            maxYear=year;
            return this;
        }

        public Builder setMinMonth(int month){
            minMonth=month;
            return this;
        }

        public Builder setMaxMonth(int month){
            maxMonth=month;
            return this;
        }

        public Builder setMinDay(int day){
            minDay=day;
            return this;
        }

        public Builder setMaxDay(int day){
            maxDay=day;
            return this;
        }

        public Builder setSelectYear(int year){
            this.selectYear=year;
            return this;
        }

        public Builder setSelectMonth(int month){
            this.selectMonth=month;
            return this;
        }

        public Builder setSelectDay(int day){
            this.selectDay=day;
            return this;
        }

        /**
         * 获取当前选择的日期
         *
         * @return int[]数组形式返回。例[1990,6,15]
         */
        private final int[] getCurrDateValues() {
            int currYear = Integer.parseInt(params.loopYear.getCurrentItemValue());
            int currMonth = Integer.parseInt(params.loopMonth.getCurrentItemValue());
            int currDay = Integer.parseInt(params.loopDay.getCurrentItemValue());
            int currHour = Integer.parseInt(params.loopHour.getCurrentItemValue());
            int currMin = Integer.parseInt(params.loopMin.getCurrentItemValue());
            int currSec = Integer.parseInt(params.loopSec.getCurrentItemValue());
            return new int[]{currYear, currMonth, currDay,currHour,currMin,currSec};
        }

        public Builder setOnDateSelectedListener(OnDateSelectedListener onDateSelectedListener) {
            params.callback = onDateSelectedListener;
            return this;
        }


        public DatePickerDialog create() {
            final DatePickerDialog dialog = new DatePickerDialog(context, params.shadow ? R.style.Theme_Light_NoTitle_Dialog : R.style.Theme_Light_NoTitle_NoShadow_Dialog);
            View view = LayoutInflater.from(context).inflate(R.layout.layout_picker_date, null);

            view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    params.callback.onCancel();
                }
            });
            view.findViewById(R.id.tv_clear).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    params.callback.onCancel();
                }
            });

            if (isdetail!=null){
                if (isdetail==1){
                    view.findViewById(R.id.loop_hour_ll).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.loop_min_ll).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.loop_sec_ll).setVisibility(View.VISIBLE);
                }else {
                    view.findViewById(R.id.loop_hour_ll).setVisibility(View.GONE);
                    view.findViewById(R.id.loop_min_ll).setVisibility(View.GONE);
                    view.findViewById(R.id.loop_sec_ll).setVisibility(View.GONE);
                }
            }
            Calendar c = Calendar.getInstance();


            final LoopView loopDay = (LoopView) view.findViewById(R.id.loop_day);
            loopDay.setArrayList(d(1, 30));

            if(selectDay!=null){
                loopDay.setCurrentItem(selectDay);
            }else{
                loopDay.setCurrentItem(c.get(Calendar.DATE));
            }
            loopDay.setNotLoop();

            final LoopView loopYear = (LoopView) view.findViewById(R.id.loop_year);
            loopYear.setArrayList(d(MIN_YEAR, MAX_YEAR - MIN_YEAR + 1));
            if(selectYear!=null){
                loopYear.setCurrentItem(selectYear-MIN_YEAR+1);
            }else{
                loopYear.setCurrentItem(MAX_YEAR);
            }
            loopYear.setNotLoop();

            final LoopView loopMonth = (LoopView) view.findViewById(R.id.loop_month);
            loopMonth.setArrayList(d(1, 12));
            if(selectMonth!=null){
                loopMonth.setCurrentItem(selectMonth);
            }else{
                loopMonth.setCurrentItem(c.get(Calendar.MONTH));
            }
            loopMonth.setNotLoop();

            final LoopView loopHour = (LoopView) view.findViewById(R.id.loop_hour);
            loopHour.setArrayList(d(1, 24));
            if(selecthour!=null){
                loopHour.setCurrentItem(selecthour);
            }else{
                loopHour.setCurrentItem(c.get(Calendar.HOUR_OF_DAY));
            }
            loopHour.setNotLoop();

            final LoopView loopMin = (LoopView) view.findViewById(R.id.loop_min);
            loopMin.setArrayList(d(1, 60));
            if(selectmin!=null){
                loopMin.setCurrentItem(selectmin);
            }else{
                loopMin.setCurrentItem(c.get(Calendar.MINUTE));
            }
            loopMin.setNotLoop();
            final LoopView loopSec = (LoopView) view.findViewById(R.id.loop_sec);
            loopSec.setArrayList(d(1, 60));
            if(selectsec!=null){
                loopSec.setCurrentItem(selectsec);
            }else{
                loopSec.setCurrentItem(c.get(Calendar.SECOND));
            }



            final LoopListener maxDaySyncListener = new LoopListener() {
                @Override
                public void onItemSelect(int item) {
                    Calendar c = Calendar.getInstance();
                    boolean needFixed=true;
                    if(minYear!=null){
                        if(Integer.parseInt(loopYear.getCurrentItemValue())==minYear ){
                            if(minMonth!=null){
                                if(Integer.parseInt(loopMonth.getCurrentItemValue())<minMonth){
                                    loopMonth.setCurrentItem(minMonth - 1);
                                }
                            }
                        }else if(Integer.parseInt(loopYear.getCurrentItemValue())<minYear){
                            loopYear.setCurrentItem(minYear-MIN_YEAR);
                        }
                    }

                    if(maxYear!=null){
                        if(Integer.parseInt(loopYear.getCurrentItemValue())==maxYear ){
                            if(maxMonth!=null){
                                if(Integer.parseInt(loopMonth.getCurrentItemValue())>maxMonth){
                                    loopMonth.setCurrentItem(maxMonth - 1);
                                }
                            }
                        }else if(Integer.parseInt(loopYear.getCurrentItemValue())>maxYear){
                            loopYear.setCurrentItem(maxYear-MIN_YEAR);
                        }
                    }

                    c.set(Integer.parseInt(loopYear.getCurrentItemValue()), Integer.parseInt(loopMonth.getCurrentItemValue()) - 1, 1);
                    c.roll(Calendar.DATE, false);

                    if(needFixed){
                        int maxDayOfMonth = c.get(Calendar.DATE);
                        int fixedCurr = loopDay.getCurrentItem();
                        loopDay.setArrayList(d(1, maxDayOfMonth));
                        // 修正被选中的日期最大值
                        if (fixedCurr > maxDayOfMonth) fixedCurr = maxDayOfMonth - 1;
                        loopDay.setCurrentItem(fixedCurr);
                    }
                }
            };

            final LoopListener dayLoopListener = new LoopListener() {
                @Override
                public void onItemSelect(int item) {
                    if(minYear!=null && minMonth!=null && minDay!=null
                            && Integer.parseInt(loopYear.getCurrentItemValue())==minYear
                            && Integer.parseInt(loopMonth.getCurrentItemValue())==minMonth
                            && Integer.parseInt(loopDay.getCurrentItemValue())<minDay
                            ){
                        loopDay.setCurrentItem(minDay-1);
                    }

                    if(maxYear!=null && maxMonth!=null && maxDay!=null
                            && Integer.parseInt(loopYear.getCurrentItemValue())==maxYear
                            && Integer.parseInt(loopMonth.getCurrentItemValue())==maxMonth
                            && Integer.parseInt(loopDay.getCurrentItemValue())>maxDay
                            ){
                        loopDay.setCurrentItem(maxDay-1);
                    }
                }
            };
            loopYear.setListener(maxDaySyncListener);
            loopMonth.setListener(maxDaySyncListener);
            loopDay.setListener(dayLoopListener);

            view.findViewById(R.id.tx_finish).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    try {
                        params.callback.onDateSelected(getCurrDateValues());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });

            Window win = dialog.getWindow();
            win.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            win.setAttributes(lp);
            win.setGravity(Gravity.BOTTOM);
            win.setWindowAnimations(R.style.Animation_Bottom_Rising);

            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(params.canCancel);
            dialog.setCancelable(params.canCancel);

            params.loopYear = loopYear;
            params.loopMonth = loopMonth;
            params.loopDay = loopDay;
            params.loopHour = loopHour;
            params.loopMin = loopMin;
            params.loopSec= loopSec;

            dialog.setParams(params);

            return dialog;
        }

        /**
         * 将数字传化为集合，并且补充0
         *
         * @param startNum 数字起点
         * @param count    数字个数
         * @return
         */
        private static List<String> d(int startNum, int count) {
            String[] values = new String[count];
            for (int i = startNum; i < startNum + count; i++) {
                String tempValue = (i < 10 ? "0" : "") + i;
                values[i - startNum] = tempValue;
            }
            return Arrays.asList(values);
        }

    }
}
