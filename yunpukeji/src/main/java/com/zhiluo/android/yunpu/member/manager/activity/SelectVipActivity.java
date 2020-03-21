package com.zhiluo.android.yunpu.member.manager.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.adapters.SelectVipAdapter;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.manager.bean.YuangongEnyity;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${YSL} on 2018-04-08.
 */

public class SelectVipActivity extends AppCompatActivity {
    private SelectVipAdapter mAdapter;
    private List<String> nameList;
    private RecyclerView mRecyclerView;
    private TextView levelTa,levelTb,levelTc,levelTd,levelTe,levelTf,levelTg,levelTh;
    private TextView LabTa,LabTb,LabTc,LabTd,LabTe,LabTf,LabTg,LabTh;
    private TextView StatusTa,StatusTb,StatusTc;
    private TextView BirthdayTa,BirthdayTb,BirthdayTc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_shaixuan);
        initViews();
        initDatas();
    }

    private void initDatas() {
        nameList = new ArrayList<>();
        getNameList();
        mAdapter = new SelectVipAdapter(this, nameList);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.recycle_select_status);

    }

    private List<String> getNameList() {
        qurryNameList();
        return nameList;
    }

    private List<String> qurryNameList() {
        RequestParams params = new RequestParams();
        params.put("DM_GID", "");
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                YuangongEnyity enyity = CommonFun.JsonToObj(responseString, YuangongEnyity.class);
                nameList.add("全部");
                for (int i = 0; i < enyity.getData().size(); i++) {
                    nameList.add(enyity.getData().get(i).getEM_Name());
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        };
        HttpHelper.post(this, "Empl/QueryEmpl", params, callBack);
        return nameList;
    }

}
