package com.example.liangmutian.mypicker.calendarselecter;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Cecil on 2017/8/22.
 * Email:guixixuan1120@outlook.com
 */

public class CalendarSelector {

    private Activity mContext;
    private HashMap<String, Object> mMap;
    private ICalendarSelectorCallBack mCallBack;
    private StringScrollPicker mYearPicker;
    private StringScrollPicker mMonthPicker;
    private StringScrollPicker mDayPicker;
    private List<String> mYearList;
    private List<String> mMonthList;
    private List<String> mDayList;
    private TextView tvConfirm;
    private TextView tvCancel;
    private TextView tvAverage;
    private TextView tvLunar;
    private PopupWindow mPopupWindow;
    private String mSelectedYear;
    private String mSelectedMonth;
    private String mSelectedDay;
    private boolean isHideYear = false;
    private int mYearPosition = 0;
    private int mMonthPosition = 0;
    private int mDayPosition = 0;
    private boolean isLunar = false;

    public interface ICalendarSelectorCallBack {
        void transmitPeriod(HashMap<String, String> result);
    }

    public CalendarSelector(Activity context, int position,
                            ICalendarSelectorCallBack iCalendarSelectorCallBack) {
        this.mContext = context;
        this.mCallBack = iCalendarSelectorCallBack;
        this.mYearPosition = position;
        initData();
        initView();
        initListener();
    }

    private void initData() {
        mYearList = new ArrayList<>();
        mMonthList = new ArrayList<>();
        mDayList = new ArrayList<>();
        for (int i = 1901; i < 2100; i++) {
            mYearList.add(i + "年");
        }
        for (int i = 1; i < 13; i++) {
            mMonthList.add(i + "月");
        }
    }

    private void initView() {
        View v = LayoutInflater.from(mContext).inflate(R.layout.popup_window_calendar_selector,
                null);
        mYearPicker = (StringScrollPicker) v.findViewById(R.id.ssp_year);
        mMonthPicker = (StringScrollPicker) v.findViewById(R.id.ssp_month);
        mDayPicker = (StringScrollPicker) v.findViewById(R.id.ssp_day);
        tvConfirm = (TextView) v.findViewById(R.id.tv_confirm);
        tvCancel = (TextView) v.findViewById(R.id.tv_cancel);
        tvAverage = (TextView) v.findViewById(R.id.tv_average);
        tvLunar = (TextView) v.findViewById(R.id.tv_lunar);
        tvAverage.setBackground(ContextCompat.getDrawable(mContext, R.drawable
                .bg_average_calendar_selected));


        initPicker();
        initPopup(v);
    }

    private void initPicker() {
        mYearPicker.setIsCirculation(false);
        mYearPicker.setData(mYearList);
        mYearPicker.setSelectedPosition(mYearPosition);
        mMap = Utils.parseAverageYear(1901 + mYearPosition + "年");
        setMonthList();
        mMonthPicker.setIsCirculation(false);
        mDayPicker.setIsCirculation(false);
        mSelectedYear = mYearList.get(mYearPosition);
        mSelectedMonth = mMonthList.get(mMonthPosition);
        mSelectedDay = mDayList.get(mDayPosition);
        mYearPicker.setOnSelectedListener(new ScrollPickerView.OnSelectedListener() {
            @Override
            public void onSelected(ScrollPickerView scrollPickerView, int position) {
                mSelectedYear = mYearList.get(position);
                mYearPosition = position;
                if (isLunar) {
                    mMap = Utils.parseLunarYear(mSelectedYear);
                } else {
                    mMap = Utils.parseAverageYear(mSelectedYear);
                }
                setMonthList();
            }
        });
        mMonthPicker.setOnSelectedListener(new ScrollPickerView.OnSelectedListener() {
            @Override
            public void onSelected(ScrollPickerView scrollPickerView, int position) {
                mSelectedMonth = mMonthList.get(position);
                mMonthPosition = position;
                setDayList();
            }
        });
        mDayPicker.setOnSelectedListener(new ScrollPickerView.OnSelectedListener() {
            @Override
            public void onSelected(ScrollPickerView scrollPickerView, int position) {
                mSelectedDay = mDayList.get(position);
                mDayPosition = position;
            }
        });
    }

    private void setMonthList() {
        mMonthList = (List<String>) mMap.get("month");
        mMonthPicker.setData(mMonthList);
        mMonthPosition = mMonthPosition >= mMonthList.size() ? mMonthList.size()
                - 1 :
                mMonthPosition;
        mMonthPicker.setSelectedPosition(mMonthPosition);
        setDayList();
    }

    private void setDayList() {
        mDayList = ((List<List<String>>) mMap.get("day")).get(mMonthPosition);
        mDayPicker.setData(mDayList);
        mDayPosition = mDayPosition >= mDayList.size() ? mDayList.size() - 1 :
                mDayPosition;
        mDayPicker.setSelectedPosition(mDayPosition);
    }

    private void initPopup(View v) {
        mPopupWindow = new PopupWindow(v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Utils.backgroundAlpha(mContext, 1f);
            }
        });
    }

    private void initListener() {
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("year", mSelectedYear);
                map.put("yearpos",String.valueOf(mYearPosition));
                map.put("month", mMonthList.get(mMonthPosition));
                map.put("monthpos",String.valueOf(mMonthPosition));
                map.put("day", mSelectedDay);
                map.put("daypos",String.valueOf(mDayPosition));
                String str;
                if (isLunar){
                    str = "1";
                }else {
                    str = "0";
                }
                map.put("islunar",str);
                String cm = "0";
                if (mMonthList.size()>12){
                    for (int i = 0;i<mMonthList.size();i++){
                        if (mMonthList.get(i).length()>2){
                            cm = i+"";
                        }
                    }
                }
                map.put("CalaryMonth",cm);
                mCallBack.transmitPeriod(map);
                mPopupWindow.dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });

        tvLunar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLunar) {
                    return;
                }
                if ("1901年".equals(mYearPicker.getSelectedItem()) || "2099年".equals(mYearPicker
                        .getSelectedItem())) {
                    Toast.makeText(mContext, "因条件受限，本APP暂不提供1901年和2099年阴阳历数据的转换，感谢理解", Toast
                            .LENGTH_SHORT).show();
                    return;
                }
                isLunar = true;
                Log.d("Cecil","average2Lunar");
                mMap = Utils.average2Lunar(mYearPicker.getSelectedItem(),
                        mMonthPosition, mDayPosition);
                mMonthPosition = (int) mMap.get("monthPosition");
                mDayPosition = (int) mMap.get("dayPosition");
                mYearPicker.setSelectedPosition((int) mMap.get("yearPosition"));
                tvLunar.setBackground(ContextCompat.getDrawable(mContext, R.drawable
                        .bg_lunar_calendar_selected));
                tvAverage.setBackground(ContextCompat.getDrawable(mContext, R.drawable
                        .bg_average_calendar));
                setMonthList();
                setDayList();
            }
        });
        tvAverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLunar) {
                    return;
                }
                if ("1901年".equals(mYearPicker.getSelectedItem()) || "2099年".equals(mYearPicker
                        .getSelectedItem())) {
                    Toast.makeText(mContext, "因条件受限，本APP暂不提供1901年和2099年阴阳历数据的转换，感谢理解", Toast
                            .LENGTH_SHORT).show();
                    return;
                }
                isLunar = false;
                Log.d("Cecil","lunar2Average");
                HashMap<String, Object> map = Utils.lunar2Average(mYearPicker.getSelectedItem(),
                        mMonthPosition, mDayPosition);
                mMonthPosition = (int) map.get("monthPosition");
                mDayPosition = (int) map.get("dayPosition");
                mYearPicker.setSelectedPosition((int) map.get("yearPosition"));
                tvAverage.setBackground(ContextCompat.getDrawable(mContext, R.drawable
                        .bg_average_calendar_selected));
                tvLunar.setBackground(ContextCompat.getDrawable(mContext, R.drawable
                        .bg_lunar_calendar));
                mMap = Utils.parseAverageYear(mYearPicker.getSelectedItem());
                setMonthList();
                setDayList();
            }
        });
    }

    public void show(View v) {
        Utils.hideSoftInput(mContext);
        Utils.backgroundAlpha(mContext, 0.5f);
        mPopupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
    }
    public void setPosition(List<Integer> date,String islunar,String cm){
        int year = date.get(0)-1901;
        int month = date.get(1)-1;
        int day = date.get(2)-1;

        mMonthPosition = month;
        mDayPosition = day;
        mYearPicker.setSelectedPosition(year);
        if (!cm.equals("0")){
            mMonthPosition += 1;
        }

        if (!islunar.equals("0")) {
            mMap = Utils.parseLunarYear(mYearList.get(year));
            isLunar = true;
            tvLunar.setBackground(ContextCompat.getDrawable(mContext, R.drawable
                    .bg_lunar_calendar_selected));
            tvAverage.setBackground(ContextCompat.getDrawable(mContext, R.drawable
                    .bg_average_calendar));
        } else {
            mMap = Utils.parseAverageYear(mYearList.get(year));
        }

        setMonthList();
        setDayList();
//        mMonthPicker.setSelectedPosition(month);
//        mDayPicker.setSelectedPosition(day);

    }

}
