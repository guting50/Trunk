package com.zhiluo.android.yunpu.paymanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.ShopListBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.paymanager.adapter.ChoiceExpensesMainAdapter;
import com.zhiluo.android.yunpu.paymanager.adapter.ChoiceExpenseschildAdapter;
import com.zhiluo.android.yunpu.paymanager.bean.ChoiceExpensesTypeBean;
import com.zhiluo.android.yunpu.statistics.order.activity.LimitedConsumeScreenActivity;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.SaveScreenStateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ZPH on 2019-05-23.
 */

public class PayDetailScreenActivity extends BaseActivity {

    @Bind(R.id.tv_report_screen_back)
    TextView tvReportScreenBack;
    @Bind(R.id.tv_head_title)
    TextView tvHeadTitle;
    @Bind(R.id.tv_report_screen_confirm)
    TextView tvReportScreenConfirm;
    @Bind(R.id.et_report_screen_search)
    EditText etReportScreenSearch;
    @Bind(R.id.ll_goods_consume_scan)
    LinearLayout llGoodsConsumeScan;
    @Bind(R.id.l_yout)
    LinearLayout lYout;
    @Bind(R.id.sp_type_one)
    Spinner spTypeOne;
    @Bind(R.id.sp_type_two)
    Spinner spTypeTwo;
    @Bind(R.id.btn_report_screen_clean)
    Button btnReportScreenClean;

    private LoginUpbean mLoginBean;
    private ArrayAdapter<String> mOSpinnerAdapter;
    private ArrayAdapter<String> mTSpinnerAdapter;
    private ArrayList<String> mOneList;//一级
    private ArrayList<String> mTwoList;//二级
    private String mOneGid,mTwoGid;//GID
    private int pos = 0,dpos = 0;
    private final String mFileName = "INPUTTYPE_data";
    private SaveScreenStateUtil mStateUtil;
    private String vipCard;
    private LinearLayout layout;

    private List<ChoiceExpensesTypeBean.DataBean> mChildTypeList = new ArrayList<>();
    private List<ChoiceExpensesTypeBean.DataBean> mParentTypeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_detail_screen);
        ButterKnife.bind(this);

        mStateUtil = new SaveScreenStateUtil(this, mFileName);
        vipCard = getIntent().getStringExtra("vipCard");
        initView();
        initVariable();
        setListener();
    }

    private void initView() {


        layout = findViewById(R.id.l_yout);
        if (vipCard != null) {
            layout.setVisibility(View.GONE);
        }
    }

    private void initVariable() {
        mOneGid = mStateUtil.get("One", null);
        mTwoGid = mStateUtil.get("Two", null);

        getType();

        mOneList = new ArrayList<>();
        mTwoList = new ArrayList<>();



    }

    private void setListener() {
        //返回
        findViewById(R.id.tv_report_screen_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //确定
        findViewById(R.id.tv_report_screen_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStateUtil.put("One", mOneGid == null ? "" : mOneGid);
                mStateUtil.put("Two", mTwoGid == null ? "" : mTwoGid);

                Intent intent = new Intent();
                intent.putExtra("vip", etReportScreenSearch.getText().toString());
                intent.putExtra("One", mOneGid);
                intent.putExtra("Two", mTwoGid);
                setResult(666, intent);
                finish();
            }
        });
        //清楚筛选条件
        findViewById(R.id.btn_report_screen_clean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStateUtil.clean();
                etReportScreenSearch.setText("");
                spTypeOne.setSelection(0);
                spTypeTwo.setSelection(0);

            }
        });

        spTypeOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    mOneGid = "";
                } else {
                    mOneGid = mParentTypeList.get(position - 1).getGID();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spTypeTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    mTwoGid = "";
                } else {
                    mTwoGid = mChildTypeList.get(position - 1).getGID();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void getType() {
        mParentTypeList.clear();
        mChildTypeList.clear();
        RequestParams params = new RequestParams();
        params.put("GID","");
        HttpHelper.post(this, HttpAPI.API().Expenses_TypeLsit, params,new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ChoiceExpensesTypeBean type = CommonFun.JsonToObj(responseString, ChoiceExpensesTypeBean.class);
                mOneList.add("全部");
                mTwoList.add("全部");
                //获取父类型
                for (int i = 0; i < type.getData().size(); i++) {
                    if (type.getData().get(i).getET_ParentGID() == null || "".equals(type.getData().get(i).getET_ParentGID())) {
                        mParentTypeList.add(type.getData().get(i));
                        mOneList.add(type.getData().get(i).getET_Name());

                    }
                }
                //获取子类型
                for (int j = 0; j < type.getData().size(); j++) {
                    if (type.getData().get(j).getET_ParentGID() != null && !"".equals(type.getData().get(j).getET_ParentGID())) {
                        mChildTypeList.add(type.getData().get(j));
                        mTwoList.add(type.getData().get(j).getET_Name());
                    }
                }
                for (int i = 0; i < mParentTypeList.size(); i++) {
                    if (mOneGid != null && !mOneGid.equals("") && mOneGid.equals(mParentTypeList.get(i).getGID())) {
                        pos = i + 1;
                    }
                }

                for (int i = 0; i < mChildTypeList.size(); i++) {
                    if (mTwoGid != null && !mTwoGid.equals("") && mTwoGid.equals(mChildTypeList.get(i).getGID())) {
                        dpos = i + 1;
                    }
                }
                mOSpinnerAdapter = new ArrayAdapter<String>(PayDetailScreenActivity.this,
                        R.layout.item_spinner, R.id.tv_item_spinner, mOneList);
                spTypeOne.setAdapter(mOSpinnerAdapter);
                spTypeOne.setSelection(pos);

                mTSpinnerAdapter = new ArrayAdapter<String>(PayDetailScreenActivity.this,
                        R.layout.item_spinner, R.id.tv_item_spinner, mTwoList);
                spTypeTwo.setAdapter(mTSpinnerAdapter);
                spTypeTwo.setSelection(dpos);


            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(PayDetailScreenActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
