package com.zhiluo.android.yunpu.ui.activity.staff;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.entity.DepBean;
import com.zhiluo.android.yunpu.entity.DepListBean;
import com.zhiluo.android.yunpu.entity.NoticeEvent;
import com.zhiluo.android.yunpu.entity.StaffBean;
import com.zhiluo.android.yunpu.entity.StaffListBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.activity.LoginActivity;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.manager.activity.MemberListActivity;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.adapter.StaffDepAdapter;
import com.zhiluo.android.yunpu.ui.adapter.StaffListAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 员工管理
 */
public class StaffManagerActivity extends BaseActivity implements PopupWindow.OnDismissListener {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.lv_staff)
    ListView mListView;
    @Bind(R.id.refresh_staff_manager)
    WaveSwipeRefreshLayout mRefresh;
    @Bind(R.id.lv_staff_dep)
    ListView mDepListView;
    @Bind(R.id.add_part)
    LinearLayout lAddPart;
    @Bind(R.id.add_staff)
    LinearLayout lAddStaff;

    private StaffListBean mStaffListBean;
    private StaffListAdapter mStaffListAdapter;//员工列表Adapter
    private String mDepGid = "";//部门GID
    private DepListBean mDepListBean;
    private StaffDepAdapter mStaffDepAdapter;//部门Adapter
    private int mCurrentPos;//当期选择部门位置
    private SweetAlertDialog mSweetAlertDialog;
    private TextView choiseparent;//部门名称
    private PopupWindow mChildTypePW;
    private DisplayMetrics outMetrics;
    private EditText edit;//备注信息
    private StaffBean shopinfo;
    private int shopsign = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(StaffManagerActivity.this);
        setContentView(R.layout.activity_staff_manager);
        mChildTypePW = new PopupWindow(StaffManagerActivity.this);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        outMetrics = new DisplayMetrics();
        getData();
        setListener();
    }

    private void getData() {
        getDep();
        getEmpl("");
    }

    private void setListener() {
        mChildTypePW.setOnDismissListener(this);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //部门ListView点击事件监听
        mDepListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击切换
                mDepGid = mDepListBean.getData().get(position).getGID();
                if (position != mCurrentPos) {
                    if (mCurrentPos >= 0) {
                        mDepListBean.getData().get(mCurrentPos).setChecked(false);
                        mDepListBean.getData().get(position).setChecked(true);
                    } else {
                        mDepListBean.getData().get(position).setChecked(true);
                    }
                }
                mStaffDepAdapter.notifyDataSetChanged();
                mCurrentPos = position;
                getEmpl(mDepGid);
            }
        });
        //部门Item长按监听
        mDepListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if (position > 0 && position < mDepListBean.getData().size()) {
                    mSweetAlertDialog = new SweetAlertDialog(StaffManagerActivity.this, SweetAlertDialog.NORMAL_TYPE);
                    mSweetAlertDialog.setTitleText("选择操作")
                            .setCancelText("编辑")
                            .setConfirmText("删除")
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    Intent intent = new Intent(StaffManagerActivity.this, EditDepActivity.class);
                                    intent.putExtra("Dep", mDepListBean.getData().get(position));
                                    startActivity(intent);
                                    mSweetAlertDialog.dismiss();
                                }
                            })
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    delDep(position);
                                    mSweetAlertDialog.dismiss();
                                    if (mCurrentPos == position) {
                                        mCurrentPos = -1;
                                    }
                                }
                            })
                            .show();
                }
                return true;//true长按事件自己消费操作，false还会触发点击事件
            }
        });
        //员工详情
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StaffManagerActivity.this, StaffDetailActivity.class);
                intent.putExtra("Staff", mStaffListBean.getData().get(position));
                intent.putExtra("Dep", mDepListBean);
                startActivity(intent);
            }
        });
        //刷新
        mRefresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getEmpl(mDepGid);
            }

            @Override
            public void onLoad() {

            }

            @Override
            public boolean canLoadMore() {
                return false;
            }

            @Override
            public boolean canRefresh() {
                return true;
            }
        });
        lAddStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffManagerActivity.this, AddStaffActivity.class);
                if (mStaffListBean.getData().size()>0){
                    intent.putExtra("shopname", shopinfo);
                }
                intent.putExtra("Dep", mDepListBean);
                startActivity(intent);
            }
        });
        lAddPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新增部门
                // Intent intent = new Intent(StaffManagerActivity.this, AddDepActivity.class);
                //startActivity(intent);
                showAddPartPopWindow();
                YSLUtils.setBackgroundAlpha(0.5f, StaffManagerActivity.this);
            }
        });
    }


    /**
     * 新增部门弹窗
     */
    private void showAddPartPopWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_add_partment, null, false);
        edit = view.findViewById(R.id.ed_edit_child_type);
        mChildTypePW.setContentView(view);
        TextView save = view.findViewById(R.id.tv_save);
        TextView delete = view.findViewById(R.id.tv_delete);
        choiseparent = view.findViewById(R.id.te_choise_parent_type);
        mChildTypePW.setBackgroundDrawable(new ColorDrawable());
        mChildTypePW.setOutsideTouchable(true);
        mChildTypePW.setFocusable(true);
        mChildTypePW.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mChildTypePW.setWidth(YSLUtils.getPhoneWidth()*7/8);
        mChildTypePW.setAnimationStyle(R.style.pop_show_style);
        mChildTypePW.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YSLUtils.setBackgroundAlpha(1.0f, StaffManagerActivity.this);
                mChildTypePW.dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(choiseparent.getText()) || choiseparent.getText().toString().equals("")) {
                    CustomToast.makeText(StaffManagerActivity.this, "部门名称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    addDep();
                    YSLUtils.setBackgroundAlpha(1.0f, StaffManagerActivity.this);
                    mChildTypePW.dismiss();
                }
            }
        });
    }

    /**
     * 新增部门
     */
    private void addDep() {
        RequestParams params = new RequestParams();
        params.put("DM_Name", choiseparent.getText().toString());
        params.put("DM_Remark", TextUtils.isEmpty(edit.getText()) ? "" : edit.getText().toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                CustomToast.makeText(StaffManagerActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                getDep();
                getEmpl("");
                getAllMessage();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(StaffManagerActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在提交...", false);
        HttpHelper.post(this, "Dept/AddDept", params, callBack);
    }

    /**
     * 获取部门
     */
    private void getDep() {
        HttpHelper.post(this, "Dept/GetDeptList", new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mDepListBean = CommonFun.JsonToObj(responseString, DepListBean.class);
                DepBean bean = new DepBean();
                bean.setChecked(true);
                bean.setDM_Name("全部");
                bean.setGID("");
                mDepListBean.getData().add(0, bean);
                mCurrentPos = 0;

                mStaffDepAdapter = new StaffDepAdapter(StaffManagerActivity.this, mDepListBean);
                mDepListView.setAdapter(mStaffDepAdapter);

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(StaffManagerActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 获取员工列表
     */
    private void getEmpl(String gid) {
        RequestParams params = new RequestParams();
        params.put("DM_GID", gid);//部门GID
        HttpHelper.post(this, "Empl/QueryEmpl", params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mStaffListBean = CommonFun.JsonToObj(responseString, StaffListBean.class);
                mStaffListAdapter = new StaffListAdapter(StaffManagerActivity.this, mStaffListBean);
                mListView.setAdapter(mStaffListAdapter);
                mRefresh.setRefreshing(false);
                if (mStaffListBean.getData().size()>0&& shopsign == 1){
                    shopinfo = mStaffListBean.getData().get(0);
                    shopsign = 2;
                }

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(StaffManagerActivity.this, msg, Toast.LENGTH_SHORT).show();
                mRefresh.setRefreshing(false);
            }
        });
    }

    /**
     * 删除部门
     *
     * @param pos
     */
    private void delDep(final int pos) {
        RequestParams params = new RequestParams();
        params.put("GID", mDepListBean.getData().get(pos).getGID());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mDepListBean.getData().remove(pos);
                mStaffDepAdapter.notifyDataSetChanged();
                getAllMessage();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(StaffManagerActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在删除...", false);
        HttpHelper.post(this, "Dept/DelDept", params, callBack);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getData();
    }

    /**
     * 新增部门完成时更新部门信息
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(NoticeEvent event) {
        getDep();
        getAllMessage();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDismiss() {
        YSLUtils.setBackgroundAlpha(1.0f, StaffManagerActivity.this);
    }


    /***
     *
     * 更新部门员工缓存
     *
     * **/

    private void getAllMessage(){
        HttpHelper.post(this, HttpAPI.API().PRE_LOAD, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ReportMessageBean reportbean = CommonFun.JsonToObj(responseString, ReportMessageBean.class);
                if (reportbean!=null){

                    List<ReportMessageBean.DataBean.DepartmentListBean> deptbean = reportbean.getData().getDepartmentList();
                    CacheData.saveObject("dept", deptbean);//缓存部门列表到本地
                    List<ReportMessageBean.DataBean.EmplistBean> staffbean = reportbean.getData().getEmplist();
                    CacheData.saveObject("staff", staffbean);//缓存员工列表到本地
                    Log.i("staff缓存成功", "onSuccess: staff缓存成功");
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(StaffManagerActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
