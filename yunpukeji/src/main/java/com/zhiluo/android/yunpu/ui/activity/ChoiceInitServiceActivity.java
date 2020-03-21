package com.zhiluo.android.yunpu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.consume.activity.MemberRechargeActivity;
import com.zhiluo.android.yunpu.ui.adapter.ChoiceServiceAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.CreateOrder;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date: 2018/1/12 16:30
 * Author: 罗咏哲
 * Description: 选择初始服务项目
 * Version:
 */
public class ChoiceInitServiceActivity extends AppCompatActivity implements ChoiceServiceAdapter.ImageClick,
        ChoiceServiceAdapter.EditClick, View.OnClickListener {

    @Bind(R.id.tv_choice_service_back)
    TextView tvBack;
    @Bind(R.id.tv_choice_service_confirm)
    TextView tvConfirm;
    @Bind(R.id.iv_choice_service_search)
    ImageView ivSearch;
    @Bind(R.id.et_choice_service_condition)
    EditText etCondition;
    @Bind(R.id.cb_choice_all)
    CheckBox cbChoiceAll;
    @Bind(R.id.lv_choice_service)
    ListView lvChoiceService;
    @Bind(R.id.refresh_choice_service)
    WaveSwipeRefreshLayout mRefresh;
    @Bind(R.id.tv_server_count)
    TextView tvServerCount;
    private String mSearchCondition = "";
    private GoodsCheckResponseByType mServiceBean;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mList;
    private ChoiceServiceAdapter mAdapter;
    private int mPageTotal;//数据总页数
    private boolean mIsLoadMore;
    private int refreshnum = 2;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mCheckedList = new ArrayList<>();//选中列表
    private PopupWindow mPopWindow;
    private boolean hasTimerDown = false;
    private InputHandler mInputHandler = new InputHandler();
    private long pretime;//第一次的时间
    private List<DataBean.InitServiceListBean> mListEditedMsg = new ArrayList<>();//该界面ListView中的所有实体项集合


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_init_service);
        ButterKnife.bind(this);
        initData();
        getService(1, 20);
        setListener();

    }

    private void initData() {
        mListEditedMsg = (List<DataBean.InitServiceListBean>) getIntent().getSerializableExtra("mListEditedMsg");
    }

    private void getService(int index, int size) {
        RequestParams params = new RequestParams();
        params.put("PT_GID", "");
        params.put("PageIndex", index);
        params.put("PageSize", size);
        params.put("PM_IsService", "1");
        params.put("PM_CodeOrNameOrSimpleCode", mSearchCondition);
        HttpHelper.post(this, HttpAPI.API().SERVICEGOODS, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                if (mList != null && !mIsLoadMore) {
                    mList.clear();
                }
                List<GoodsCheckResponseByType.DataBean.DataListBean> list = new ArrayList<>();
                if (mList != null) {
                    list.addAll(mList);
                }
                mServiceBean = CommonFun.JsonToObj(responseString, GoodsCheckResponseByType.class);
                mList = mServiceBean.getData().getDataList();
                mPageTotal = mServiceBean.getData().getPageTotal();
                list.addAll(mList);
                mList = list;

                if (mListEditedMsg!=null&&mListEditedMsg.size()>0){
                    for (int i= 0;i<mListEditedMsg.size();i++){
                        for (int j = 0;j<mList.size();j++){
                            if (mListEditedMsg.get(i).getGID().equals(mList.get(j).getGID())){
                                mList.get(j).setChecked(true);
                                mCheckedList.add(mList.get(j));
                            }
                        }
                    }
                    if (mCheckedList.size() <mList.size()){
                        cbChoiceAll.setChecked(false);
                    }else {
                        cbChoiceAll.setChecked(true);
                    }
                    tvServerCount.setText("已选" + mCheckedList.size() + "项服务");

                }
                if (mAdapter ==null){
                    mAdapter = new ChoiceServiceAdapter(ChoiceInitServiceActivity.this,
                            mList, ChoiceInitServiceActivity.this);
                    lvChoiceService.setAdapter(mAdapter);
                }else {
                    mAdapter.setParam(mList);
                    mAdapter.notifyDataSetChanged();
                }
                mIsLoadMore = false;
                mRefresh.setRefreshing(false);
                mRefresh.setLoading(false);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(ChoiceInitServiceActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setListener() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etCondition.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pretime = System.currentTimeMillis();
                if (!hasTimerDown) {
                    doQurry();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable)) {
                    mSearchCondition = editable.toString();
                } else {
                    mSearchCondition = "";
                }
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckedList.size() > 0) {
                    Intent intent = new Intent();
                    intent.putExtra("Service", (Serializable) mCheckedList);
                    setResult(200, intent);
                    finish();
                } else {
                    CustomToast.makeText(ChoiceInitServiceActivity.this, "没有选择任何服务", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mRefresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getService(1, 20);
                refreshnum = 2;
            }

            @Override
            public void onLoad() {
                if (refreshnum <= mPageTotal) {
                    mIsLoadMore = true;
                    getService(refreshnum, 20);
                    refreshnum++;
                } else {
                    CustomToast.makeText(ChoiceInitServiceActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    mRefresh.setLoading(false);
                }
            }

            @Override
            public boolean canLoadMore() {
                return true;
            }

            @Override
            public boolean canRefresh() {
                return true;
            }
        });

        cbChoiceAll.setOnClickListener(new CompoundButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckedList.clear();
                if (cbChoiceAll.isChecked()) {
                    cbChoiceAll.setChecked(true);
                    tvServerCount.setText("已选" + mList.size() + "项服务");
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setChecked(true);
                    }
                    mCheckedList.addAll(mList);


                } else {
                    cbChoiceAll.setChecked(false);
                    tvServerCount.setText("已选0项服务");
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setChecked(false);
                    }
                    mCheckedList.clear();
                }

                mAdapter.notifyDataSetChanged();
            }
        });

//        cbChoiceAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                mCheckedList.clear();
//                if (isChecked) {
//                    tvServerCount.setText("已选" + mList.size() + "项服务");
//                    for (int i = 0; i < mList.size(); i++) {
//                        mList.get(i).setChecked(true);
//                    }
//                    mCheckedList.addAll(mList);
//                } else {
//                    tvServerCount.setText("已选0项服务");
//                    for (int i = 0; i < mList.size(); i++) {
//                        mList.get(i).setChecked(false);
//                    }
//                    mCheckedList.clear();
//                }
//
//
//                mAdapter.notifyDataSetChanged();
//            }
//        });
    }

    @Override
    public void click(View v) {
        switch (v.getId()) {
            case R.id.ic_item_choice:
                int pos = (int) v.getTag();
                if (mList.get(pos).isChecked()) {
                    mList.get(pos).setChecked(false);
                    for (int i = 0; i < mCheckedList.size(); i++) {
                        if (mList.get(pos).getGID().equals(mCheckedList.get(i).getGID())) {
                            mCheckedList.remove(i);
                            break;
                        }
                    }
                } else {
                    mList.get(pos).setChecked(true);
                    mCheckedList.add(mList.get(pos));
                }
//                if (mCheckedList.size() < 1) {
//
//                }
                if (mCheckedList.size() <mList.size()){
                    cbChoiceAll.setChecked(false);
                }else {
                    cbChoiceAll.setChecked(true);
                }

                tvServerCount.setText("已选" + mCheckedList.size() + "项服务");
                mAdapter.notifyDataSetChanged();
                break;

        }
    }

    private class InputHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0://0正在输入
                    break;
                case 1://1输入完成
                    doQurry();
                    break;

            }
        }
    }

    private void doQurry() {
        hasTimerDown = true;
        if (System.currentTimeMillis() - pretime > 500) {
            if (!TextUtils.isEmpty(etCondition.getText())) {
                getService(1,20);
                mCheckedList.clear();
                cbChoiceAll.setChecked(false);
                tvServerCount.setText("已选0项服务");
            }
            hasTimerDown = false;
        } else {
            mInputHandler.sendEmptyMessageDelayed(1, 300);
        }
    }

    private void showPopWindow() {
        mPopWindow = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.pop_ysl_edit, null, false);
        TextView cancel = view.findViewById(R.id.tv_cancel);
        TextView save = view.findViewById(R.id.tv_save);
        TextView delete = view.findViewById(R.id.tv_delete);
        EditText count = view.findViewById(R.id.ed_days);
        EditText timewithlimited = view.findViewById(R.id.ed_time_with_limited);
        CheckBox xianshi = view.findViewById(R.id.cb_xianshi);
        RelativeLayout choiseday = view.findViewById(R.id.r_choise_day);
        mPopWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        cancel.setOnClickListener(this);
        save.setOnClickListener(this);
        delete.setOnClickListener(this);
        count.setOnClickListener(this);
        timewithlimited.setOnClickListener(this);
        xianshi.setOnClickListener(this);
        choiseday.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel://取消
                mPopWindow.dismiss();
                break;

        }
    }

}
