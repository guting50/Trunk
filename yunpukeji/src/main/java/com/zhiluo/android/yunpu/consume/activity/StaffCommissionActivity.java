package com.zhiluo.android.yunpu.consume.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.consume.adapter.RuleAdapter;
import com.zhiluo.android.yunpu.consume.adapter.StaffCommAdapter;
import com.zhiluo.android.yunpu.entity.DeptBean;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.NoDoubleClickListener;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 员工提成
 * 作者：罗咏哲 on 2017/7/6 17:01.
 * 邮箱：137615198@qq.com
 */
public class StaffCommissionActivity extends BaseActivity implements StaffCommAdapter.ICallBack {
    private StaffCommAdapter mAdapter;
    private BaseListView mListView, mRuleListView;
    private TextView tvConfirm;
    private List<ReportMessageBean.DataBean.EmplistBean> mStaffInfoList;//
    private List<ReportMessageBean.DataBean.EmplistBean> mCommissionBean;//所以员工信息
    private List<ReportMessageBean.DataBean.EmplistBean> mCheckedStaffInfo;//被选中提成的员工信息（回传给上一个Activity）
    private List<ReportMessageBean.DataBean.EmplistBean> mDataBean;
    private String mPageFlag, mTypeFlag;//页面标识
    private SweetAlertDialog mSweetAlertDialog;
    private static String TAG = "lyz";
    private List<ReportMessageBean.DataBean.DepartmentListBean> mRuleBean = new ArrayList<>();//有效部门
    private DeptBean deptbean;
    private RuleAdapter mRuleAdapter;
    private List<ReportMessageBean.DataBean.DepartmentListBean> mRuleList;
    private int mCurrentPos = 0;
    private Map<String, List<ReportMessageBean.DataBean.EmplistBean>> mMap;
    private GoodsCheckResponseByType.DataBean.DataListBean mGoodsMsg;
    private String VGID = "";
    private String PGID = "";
    private String PTGID = "";
    private int Type = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_commission);
        initView();
        initData();
        setListener();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mListView = (BaseListView) findViewById(R.id.lv_staff_commission);
        mRuleListView = (BaseListView) findViewById(R.id.lv_staff_rule);
        tvConfirm = (TextView) findViewById(R.id.tv_staff_commission_confirm);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        Intent intent = getIntent();
        mPageFlag = intent.getStringExtra("PAGE_FLAG");
        VGID = intent.getStringExtra("VG_GID");
        if (mPageFlag.equals("SPXF")) {
            mGoodsMsg = (GoodsCheckResponseByType.DataBean.DataListBean) intent.getSerializableExtra("goodsMsg");
            PGID = mGoodsMsg.getGID();
            PTGID = mGoodsMsg.getPT_ID();
        }

        if (mPageFlag.equals("KSJC")) {
            mPageFlag = "JCXF";
            mTypeFlag = "KSJC";
        }

        mStaffInfoList = new ArrayList<>();
        mCheckedStaffInfo = new ArrayList<>();
        mCheckedStaffInfo = (List<ReportMessageBean.DataBean.EmplistBean>) intent.getSerializableExtra("mStaffInfo");

        mDataBean = new ArrayList<>();
//        mRuleBean = (List<ReportMessageBean.DataBean.DepartmentListBean>) CacheData.restoreObject("dept");
        mCommissionBean = (List<ReportMessageBean.DataBean.EmplistBean>) CacheData.restoreObject("staff");

        if (mPageFlag.equals("SPXF")) {//商品消费50 快速消费40  //会员充值20 计次消费60 会员充次30 会员开卡10
            getDepartment(50);
        }
//        else if (mPageFlag.equals("KSXF")){
//            getDepartment(40);
//        }else if (mPageFlag.equals("HYCZ")){
//            getDepartment(20);
//        }else if (mPageFlag.equals("HYCC")){
//            getDepartment(30);
//        }else if (mPageFlag.equals("ADD")){
//            getDepartment(10);
//        }else if (mPageFlag.equals("JCXF")){
//            getDepartment(60);
//        }

        else {
            if (mRuleBean.size() == 0 || mCommissionBean == null) {
                getRule();
            } else {
                setDeptAdapter();
                setStaffAdapter();
            }
        }
    }


    /**
     * 设置监听
     */
    private void setListener() {
        //返回
        findViewById(R.id.tv_back_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //确认
        tvConfirm.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                //  Set keySet = mMap.keySet();
                //  Iterator it = mMap.entrySet().iterator();
                if (mMap != null) {
                    Iterator<Map.Entry<String, List<ReportMessageBean.DataBean.EmplistBean>>> it = mMap.entrySet().iterator();
                    int flags = 0;
                    while (it.hasNext()) {
                        // Map.Entry entry = (Map.Entry) it.next();
                        Map.Entry<String, List<ReportMessageBean.DataBean.EmplistBean>> entry = it.next();
                        Object key = entry.getKey();
                        Object value = entry.getValue();
                        List<ReportMessageBean.DataBean.EmplistBean> beans = (List<ReportMessageBean.DataBean.EmplistBean>) value;
                        for (int i = 0; i < beans.size(); i++) {
                            if (beans.get(i).isCheck()) {
                                mDataBean.add(beans.get(i));
                            }
                        }

                    }

                    for (int x = 0; x < mDataBean.size(); x++) {
                        for (int y = x + 1; y < mDataBean.size(); y++) {
                            if (mDataBean.get(x).getGID().equals(mDataBean.get(y).getGID())) {
                                mDataBean.remove(y);
                            }
                        }
                    }
                    mCheckedStaffInfo = new ArrayList<>();
                    mCheckedStaffInfo = mDataBean;
                    mMap.entrySet();
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();

                    Li("=========提成员工=============: random:"+new Gson().toJson(mDataBean));

                    bundle.putSerializable("staff", (Serializable) mCheckedStaffInfo);
                    intent.putExtras(bundle);
                    setResult(1002, intent);
                    finish();
                }


            }
        });
        mRuleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mRuleList.get(position).setCheck(true);
                if (position != mCurrentPos) {
                    mRuleList.get(mCurrentPos).setCheck(false);
                    mAdapter = new StaffCommAdapter(StaffCommissionActivity.this,
                            mMap.get(mRuleList.get(position).getDM_Name()),
                            StaffCommissionActivity.this);
                    mListView.setAdapter(mAdapter);
                }
                mCurrentPos = position;
                mRuleAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 筛选符合提成条件的员工
     */
    private void getCommStaffInfo(List<ReportMessageBean.DataBean.EmplistBean> List) {
        for (int i = 0; i < List.size(); i++) {
            switch (mPageFlag) {
                case "KSXF":
                    if (List.get(i).getEM_TipFastConsume() == 1) {
                        mStaffInfoList.add(List.get(i));
                    }
                    break;
                case "HYCZ":
                    if (List.get(i).getEM_TipRecharge() == 1) {
                        mStaffInfoList.add(List.get(i));
                    }
                    break;
                case "SPXF":
                    if (List.get(i).getEM_TipGoodsConsume() == 1) {
                        mStaffInfoList.add(List.get(i));
                    }
                    break;
                case "HYCC":
                    if (List.get(i).getEM_TipChargeTime() == 1) {
                        mStaffInfoList.add(List.get(i));
                    }
                    break;
                case "ADD":
                    if (List.get(i).getEM_TipCard() == 1) {
                        mStaffInfoList.add(List.get(i));
                    }
                    break;
                case "TCXF":
                    if (List.get(i).getEM_TipComboConsume() == 1) {
                        mStaffInfoList.add(List.get(i));
                    }
                    break;
                case "JCXF":
                    if (List.get(i).getEM_TipTimesConsume() == 1) {
                        mStaffInfoList.add(List.get(i));
                    }
                    break;

            }
        }
        if (mCheckedStaffInfo != null) {
            for (int i = 0; i < mStaffInfoList.size(); i++) {
                for (int j = 0; j < mCheckedStaffInfo.size(); j++) {
                    if (mStaffInfoList.get(i).getGID().equals(mCheckedStaffInfo.get(j).getGID())) {
                        mStaffInfoList.get(i).setCheck(true);
                    }
                }
            }
        }
    }

    /**
     * 提示对话框
     *
     * @param msg
     */
    private void hintDialog(String msg) {
        mSweetAlertDialog = new SweetAlertDialog(StaffCommissionActivity.this, SweetAlertDialog.WARNING_TYPE);
        mSweetAlertDialog.setTitleText("提示")
                .setContentText(msg + "!")
                .setConfirmText("了解")
                .show();
    }

    @Override
    protected void onDestroy() {
        if (mSweetAlertDialog != null) {
            mSweetAlertDialog.dismiss();
        }
        super.onDestroy();
    }


    private void getDepartment(int type) {

        RequestParams params = new RequestParams();
//        Type	提成类型	int	否
//        VGID	等级GID	string	是
//        PGID	商品GID	string	是
        params.put("Type", type);//商品消费50 快速消费40  //会员充值20 计次消费60 会员充次30 会员开卡10
        params.put("VGID", VGID);
        params.put("PGID", PGID);
        params.put("PTGID", PTGID);
        if (VGID.equals("")) {
            params.put("VIP_Card", "00000");
        }
        HttpHelper.post(this, HttpAPI.API().EMPLOEE_DEP, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                deptbean = CommonFun.JsonToObj(responseString, DeptBean.class);
                spxfSetAdapter();
            }

            @Override
            public void onFailure(String msg) {
                if (mRuleBean != null) {
                    mRuleBean.clear();
                    spxfSetAdapter();
                }

                CustomToast.makeText(StaffCommissionActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void spxfSetAdapter() {
        mRuleBean.clear();
        if (deptbean != null && deptbean.getData().size() > 0) {
            for (int i = 0; i < deptbean.getData().size(); i++) {
                ReportMessageBean.DataBean.DepartmentListBean bean = new ReportMessageBean.DataBean.DepartmentListBean();
                bean.setCY_GID(deptbean.getData().get(i).getCY_GID());
                bean.setDM_Creator(deptbean.getData().get(i).getDM_Creator());
                bean.setDM_Name(deptbean.getData().get(i).getDM_Name());
                bean.setDM_Remark(deptbean.getData().get(i).getDM_Remark());
                bean.setGID(deptbean.getData().get(i).getGID());
                bean.setDM_UpdateTime(deptbean.getData().get(i).getDM_UpdateTime());
                bean.setCheck(deptbean.getData().get(i).isCheck());
                mRuleBean.add(bean);
            }

        }
        setDeptAdapter();
        setStaffAdapter();
    }

    /**
     * 获取部门及员工数据
     *
     * @param
     */
    private void getRule() {
        RequestParams params = new RequestParams();
        HttpHelper.post(this, HttpAPI.API().PRE_LOAD, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ReportMessageBean reportbean = CommonFun.JsonToObj(responseString, ReportMessageBean.class);
                mRuleBean = reportbean.getData().getDepartmentList();
                CacheData.saveObject("dept", mRuleBean);//缓存部门列表到本地

                mCommissionBean = reportbean.getData().getEmplist();
                CacheData.saveObject("staff", mCommissionBean);//缓存员工列表到本地

                setDeptAdapter();
                setStaffAdapter();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(StaffCommissionActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void click(View view, int postion) {
        int pos = (int) view.getTag();
        ReportMessageBean.DataBean.EmplistBean bean = mMap.get(mRuleList.get(mCurrentPos).getDM_Name()).get(pos);
        if (bean.isCheck()) {
            bean.setCheck(false);
        } else {
            bean.setCheck(true);
        }
        if (mPageFlag.equals("ADD") || (mTypeFlag != null && mTypeFlag.equals("KSJC"))) {
            for (int i = 0; i < mMap.get(mRuleList.get(0).getDM_Name()).size(); i++) {
                if (!bean.getGID().equals(mMap.get(mRuleList.get(0).getDM_Name()).get(i).getGID())) {
                    mMap.get(mRuleList.get(0).getDM_Name()).get(i).setCheck(false);
                }
            }
        }
        mAdapter.notifyDataSetChanged();

    }

    /**
     * 部门adapter设置
     ***/

    private void setDeptAdapter() {
        if (mRuleBean.size() > 0) {
            mRuleList = new ArrayList<>();
            ReportMessageBean.DataBean.DepartmentListBean bean = new ReportMessageBean.DataBean.DepartmentListBean();
            bean.setDM_Name("全部");
            bean.setCheck(true);
            mRuleList.add(bean);
            mRuleList.addAll(mRuleBean);
            mRuleAdapter = new RuleAdapter(StaffCommissionActivity.this, mRuleList);
            mRuleListView.setAdapter(mRuleAdapter);

        }
    }

    /***
     *
     * 员工adatper设置
     *
     * ***/
    private void setStaffAdapter() {
        //员工list设置
        LoginUpbean loginUpbean = (LoginUpbean) CacheData.restoreObject("login");
        String ShopGID = loginUpbean.getData().getShopID();
        for (int i = 0; i < mCommissionBean.size(); i++) {//这个for是排除非当前店铺，但不排除没有设置店铺的员工
            if (mCommissionBean.get(i).getSM_GID() != null && !mCommissionBean.get(i).getSM_GID().equals(ShopGID)) {
                mCommissionBean.remove(i);
            }
        }

        List<ReportMessageBean.DataBean.EmplistBean> List = new ArrayList<>();
        //   过滤调没有提成部门的员工
        for (ReportMessageBean.DataBean.EmplistBean em : mCommissionBean) {
            for (ReportMessageBean.DataBean.DepartmentListBean valiRuleMsg : mRuleBean) {
                if (em.getDM_GID().equals(valiRuleMsg.getGID())) {
                    List.add(em);
                }
            }
        }

        getCommStaffInfo(List);//筛选符合条件的员工
        if (mStaffInfoList != null) {
            mMap = new HashMap<>();
            if (mRuleList != null && mRuleList.size() > 0) {
                for (int i = 0; i < mRuleList.size(); i++) {
                    String name = mRuleList.get(i).getDM_Name();
                    List<ReportMessageBean.DataBean.EmplistBean> list = new ArrayList<>();
                    if (name.equals("全部")) {
                        list.addAll(mStaffInfoList);
                    }
                    for (int j = 0; j < mStaffInfoList.size(); j++) {
                        if (mStaffInfoList.get(j).getDM_Name().equals(name)) {
                            list.add(mStaffInfoList.get(j));
                        }
                    }
                    mMap.put(name, list);
                }
                mAdapter = new StaffCommAdapter(StaffCommissionActivity.this,
                        mMap.get(mRuleList.get(0).getDM_Name()), StaffCommissionActivity.this);
                mListView.setAdapter(mAdapter);
            }
        }
    }
}
