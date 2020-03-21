package com.zhiluo.android.yunpu.ui.activity.user;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.entity.AppMenuListBean;
import com.zhiluo.android.yunpu.entity.NoticeEvent;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.adapter.RoleMenuAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;

/**
 * 新增角色
 */
public class AddRoleActivity extends BaseActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.tv_add_role_save)
    TextView tvSave;
    @Bind(R.id.et_add_role_name)
    EditText etName;
    @Bind(R.id.et_add_role_remark)
    EditText etRemark;
    @Bind(R.id.rv_role_menu)
    RecyclerView mRecyclerView;

    private AppMenuListBean mAppMenuListBean;//App菜单权限
    private RoleMenuAdapter mMenuAdapter;
    private List<String> mList;
    private JsonArray mArray;
    private List<String> AppMenuList;
    private LoginUpbean mLoginBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_role);
        ButterKnife.bind(this);
        etName.requestFocus();
        mList=new ArrayList<>();
        mArray=new JsonArray();
        AppMenuList = new ArrayList<>();
        getData();
        setListener();
        initList();
    }

    private void initList() {
        mList.add("59247358");
        mList.add("59247724");
        mList.add("59247542");
        mList.add("59247552");
        mList.add("59247690");
        mList.add("59247544");
        mList.add("59247545");
        mList.add("59247546");
        mList.add("59247547");
        mList.add("59247548");
        mList.add("59247549");
        mList.add("59247550");
        mList.add("59247551");
        mList.add("59247355");
        mList.add("59247493");
        mList.add("59247373");
        mList.add("59247553");
        mList.add("59247579");
        mList.add("59247628");
        mList.add("59247666");
        mList.add("59247357");
        mList.add("59247374");
        mList.add("59247351");
        mList.add("59247691");
        mList.add("59247698");
        mList.add("59247710");
        mList.add("59247744");
        mList.add("59247745");
        mList.add("59247754");
        mList.add("59247790");
        for (int i=0;i<mList.size();i++){
            JSONObject object=new JSONObject();
            try {
                object.put("RR_GID",mList.get(i));
                object.put("RR_IsCheck",1);
                mArray.add(String.valueOf(object));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void getData() {
        mLoginBean = (LoginUpbean) CacheData.restoreObject("login");
        MyApplication.isDingXiang = true;
        if (mLoginBean !=null){
            try {
                if (mLoginBean.getData().getShopList().get(0).getSM_FunctionList() != null && !mLoginBean.getData().getShopList().get(0).getSM_FunctionList().equals("")) {

                    JSONArray jsonArray = new JSONArray(mLoginBean.getData().getShopList().get(0).getSM_FunctionList());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (jsonObject.get("code").equals("1D0")) {
                            Integer  s = (Integer) jsonObject.get("value");
                            if (s == 1)
                                MyApplication.isDingXiang = true;
                        }
                    }
                } else {
                    MyApplication.isDingXiang = false;
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }

        getRoleMenu("");


    }

    private void setListener() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verify()) {
                    save();
                }
            }
        });
    }

    private boolean verify() {
        if (TextUtils.isEmpty(etName.getText())) {
            CustomToast.makeText(AddRoleActivity.this, "角色名称不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void save() {
        RequestParams params = new RequestParams();
        params.put("GID", "");
        /**新增角色权限,新建用户登录到主页面闪退  20191022 cy */
//        params.put("MM_GIDList[]","daf9a919-d2eb-465f-ad5f-1adbacfdba8d");
        params.put("RS_GIDList[]", "daf9a919-d2eb-465f-ad5f-1adbacfdba8d");
        //角色备注
//        params.put("RR_GIDList[]",mArray);
        /*************************************** 20191022 cy */
        //角色名称
        params.put("RM_Name", etName.getText().toString());
      /*  for (int j=0;j<mList.size();j++){
            params.put("RR_GIDList["+j+"][RR_GID]",mList.get(j));
            params.put("RR_GIDList["+j+"][RR_IsCheck]",1);
        }*/
        params.put("RM_Remark", TextUtils.isEmpty(etRemark.getText()) ? "" : etRemark.getText().toString());
        for (int i = 0; i < mAppMenuListBean.getData().size(); i++) {
            if (mAppMenuListBean.getData().get(i).getAR_IsCheck() != null) {
                if (mAppMenuListBean.getData().get(i).getAR_IsCheck().equals("1")) {
                    AppMenuList.add(mAppMenuListBean.getData().get(i).getGID());
//                    params.put("AR_GIDList[" + i + "]", mAppMenuListBean.getData().get(i).getGID());
                }
            }
        }
        /**新增角色权限,新建用户登录到主页面闪退  20191022 cy */
        AppMenuList.add("0000");
        //选择权限的GID
//        params.put("AR_GIDList[]", AppMenuList);
        params.put("AM_GIDList[]", AppMenuList);
        //系统有多少个权限
//        params.put("AR_GIDList[" + mAppMenuListBean.getData().size() + "]", "0000");
        /*************************************** 20191022 cy */

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                CustomToast.makeText(AddRoleActivity.this, "新增角色成功！", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new NoticeEvent());
                finish();
            }

            @Override
            public void onFailure(String msg) {
                if (msg.contains("CommandText property")){
                    CustomToast.makeText(AddRoleActivity.this, "请先设置APP权限", Toast.LENGTH_SHORT).show();
                }else {
                    CustomToast.makeText(AddRoleActivity.this, msg, Toast.LENGTH_SHORT).show();
                }

                Le(msg);
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(this, "Role/AddRole", params, callBack);
    }

    /**
     * 获取APP权限菜单
     *
     * @param gid
     */
    private void getRoleMenu(String gid) {
        RequestParams params = new RequestParams();
        params.put("RM_GID", gid);
        HttpHelper.post(this, "APPMenuRoleRelation/QueryAppMenuList", params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mAppMenuListBean = CommonFun.JsonToObj(responseString, AppMenuListBean.class);
                for (int i = 0; i < mAppMenuListBean.getData().size(); i++) {
                    if (mAppMenuListBean.getData().get(i).getAM_Name().equals("公共资源")) {
                        mAppMenuListBean.getData().remove(i);
                    }
                    if (mAppMenuListBean.getData().get(i).getAM_Name().equals("定项消费") && !MyApplication.isDingXiang) {
                        mAppMenuListBean.getData().remove(i);
                    }
                }
                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,
                        StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(layoutManager);
                mMenuAdapter = new RoleMenuAdapter(mAppMenuListBean);
                mRecyclerView.setAdapter(mMenuAdapter);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(AddRoleActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
