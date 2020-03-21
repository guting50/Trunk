package com.zhiluo.android.yunpu.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.NoticeEvent;
import com.zhiluo.android.yunpu.entity.RoleBean;
import com.zhiluo.android.yunpu.entity.RoleListBean;
import com.zhiluo.android.yunpu.entity.UserListBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.adapter.RoleAdapter;
import com.zhiluo.android.yunpu.ui.adapter.UserListAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CommonFun;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 用户管理
 */
public class UserManagerActivity extends BaseActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.lv_user_role)
    ListView listViewRole;
    @Bind(R.id.lv_user)
    ListView listViewUser;
    @Bind(R.id.refresh_user_manager)
    WaveSwipeRefreshLayout mRefresh;
    @Bind(R.id.add_role)
    LinearLayout lAddRole;
    @Bind(R.id.add_user)
    LinearLayout lAddUSer;

    private RoleListBean mRoleListBean;//角色列表
    private UserListBean mUserListBean;//用户列表
    private String mRoleGid = "";//角色GID
    private SweetAlertDialog mSweetAlertDialog;
    private RoleAdapter mRoleAdapter;//角色列表Adapter
    private UserListAdapter mUserListAdapter;//用户列表Adapter
    private int mCurrentPos;//当期选择角色位置
    private String superAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
        }catch (Exception e){}
        savedInstanceState = null;
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_user_manager);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        getData();
        setListener();
    }

    private void getData() {
        getRole();
        getUser(mRoleGid);
    }

    /**
     * 获取角色列表
     */
    private void getRole() {
        HttpHelper.post(this, HttpAPI.API().ROLE_LIST, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mRoleListBean = CommonFun.JsonToObj(responseString, RoleListBean.class);
                RoleBean bean = new RoleBean();
                bean.setChecked(true);
                bean.setRM_Name("全部");
                bean.setGID("");
                mCurrentPos = 0;
                mRoleListBean.getData().add(0, bean);
                mRoleAdapter = new RoleAdapter(UserManagerActivity.this, mRoleListBean);
                listViewRole.setAdapter(mRoleAdapter);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(UserManagerActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 获取用户列表
     */
    private void getUser(String gid) {
        RequestParams params = new RequestParams();
        params.put("RM_GID", gid);
        HttpHelper.post(this, HttpAPI.API().USER_MANAGER_LIST, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mUserListBean = CommonFun.JsonToObj(responseString, UserListBean.class);
                mUserListAdapter = new UserListAdapter(UserManagerActivity.this, mUserListBean);
                if (mUserListBean!=null && mUserListBean.getData().size()>0){
                    for (int i = 0;i<mUserListBean.getData().size();i++){
                        if (mUserListBean.getData().get(i).getUM_IsAmin()==1){
                            superAccount = mUserListBean.getData().get(i).getUM_Acount();
                        }
                    }
                }
                listViewUser.setAdapter(mUserListAdapter);
                mRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(UserManagerActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setListener() {
        lAddUSer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新增用户
                Intent intent = new Intent(UserManagerActivity.this, AddUserActivity.class);
                intent.putExtra("User",mUserListBean);
                intent.putExtra("Role", mRoleListBean);
                intent.putExtra("superAccount",superAccount);
                startActivity(intent);
            }
        });
        lAddRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新增角色
                Intent intent = new Intent(UserManagerActivity.this, AddRoleActivity.class);
                startActivity(intent);
            }
        });
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listViewRole.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mRoleGid = mRoleListBean.getData().get(position).getGID();
                Log.i(TAG, "onItemClick: pos:" + position + "cpos:" + mCurrentPos);
                if (position != mCurrentPos) {
                    mRoleListBean.getData().get(mCurrentPos).setChecked(false);
                    mRoleListBean.getData().get(position).setChecked(true);
                }
                mRoleAdapter.notifyDataSetChanged();
                mCurrentPos = position;
                getUser(mRoleGid);

            }
        });
        //部门Item长按监听
        listViewRole.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if (position > 0 && position < mRoleListBean.getData().size()) {
                    if (mRoleListBean.getData().get(position).getRM_IsAdmin() != 1) {
                        mSweetAlertDialog = new SweetAlertDialog(UserManagerActivity.this, SweetAlertDialog.NORMAL_TYPE);
                        mSweetAlertDialog.setTitleText("选择操作")
                                .setCancelText("编辑")
                                .setConfirmText("删除")
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Intent intent = new Intent(UserManagerActivity.this, EditRoleActivity.class);
                                        intent.putExtra("Role", mRoleListBean.getData().get(position));
                                        startActivity(intent);
                                        mSweetAlertDialog.dismiss();
                                    }
                                })
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        delRole(position);
                                        mSweetAlertDialog.dismiss();
                                    }
                                })
                                .show();
                    } else {
                        //超管不能被操作
                        CustomToast.makeText(UserManagerActivity.this, "超级管理员不能被操作！", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;//true长按事件自己消费操作，false还会触发点击事件
            }
        });
        //员工详情
        listViewUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(UserManagerActivity.this, UserDetailActivity.class);
                intent.putExtra("User", mUserListBean.getData().get(position));
                intent.putExtra("Role", mRoleListBean);
                intent.putExtra("superAccount",superAccount);
                startActivity(intent);
            }
        });
        //刷新
        mRefresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUser(mRoleGid);
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

    }

    /**
     * 删除角色
     *
     * @param pos
     */
    private void delRole(final int pos) {
        RequestParams params = new RequestParams();
        params.put("GID", mRoleListBean.getData().get(pos).getGID());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mRoleListBean.getData().remove(pos);
                mRoleAdapter.notifyDataSetChanged();
                mCurrentPos = 0;
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(UserManagerActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在删除...", false);
        HttpHelper.post(this, HttpAPI.API().DELET_ROLE, params, callBack);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        getData();
//        getUser(mRoleGid);

    }

    /**
     * 新增角色完成时更新部门信息
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(NoticeEvent event) {
        getRole();
        getUser("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
