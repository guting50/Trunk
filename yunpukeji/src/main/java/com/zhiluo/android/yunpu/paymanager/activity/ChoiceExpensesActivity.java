package com.zhiluo.android.yunpu.paymanager.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.paymanager.adapter.ChoiceExpensesMainAdapter;
import com.zhiluo.android.yunpu.paymanager.adapter.ChoiceExpenseschildAdapter;
import com.zhiluo.android.yunpu.paymanager.bean.ChoiceExpensesTypeBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;

/**
 * Created by ZPH on 2019-05-22.
 */

public class ChoiceExpensesActivity extends BaseActivity implements PopupWindow.OnDismissListener{


    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.tv_titlerg_activity)
    TextView tvTitlergActivity;
    @Bind(R.id.tv_choice_type_confirm)
    TextView tvChoiceTypeConfirm;
    @Bind(R.id.lv_choice_type_main)
    ListView lvChoiceTypeMain;
    @Bind(R.id.refresh_choice_type_main)
    WaveSwipeRefreshLayout refreshChoiceTypeMain;
    @Bind(R.id.lv_choice_type_child)
    ListView lvChoiceTypeChild;
    @Bind(R.id.refresh_choice_type_child)
    WaveSwipeRefreshLayout refreshChoiceTypeChild;
    @Bind(R.id.add_main_type_button)
    LinearLayout addmaintypebutton;//主分类按钮
    @Bind(R.id.add_child_type_button)
    LinearLayout addchildtypebutton;//子分类按钮
    private List<ChoiceExpensesTypeBean.DataBean> msChildTypeList = new ArrayList<>();
    private List<ChoiceExpensesTypeBean.DataBean> mChildTypeList = new ArrayList<>();
    private List<ChoiceExpensesTypeBean.DataBean> mParentTypeList = new ArrayList<>();
    private Map<ChoiceExpensesTypeBean.DataBean, List<ChoiceExpensesTypeBean.DataBean>> map = new HashMap<>();
    private ChoiceExpensesMainAdapter mMainAdapter;
    private ChoiceExpenseschildAdapter mChildAdapter;
    private Handler mHandler;
    private int mMainPosition = 0, mChildPosition;
    // private LinearLayout llHeadView;
    private String mParentGid, mParentName;//主分类GID，名称
    //    private int mParentSync, mChildSync = 0;//主分类同步状态
    private PopupWindow mMainTypePW;
    private PopupWindow mChildTypePW;
    private WindowManager manager;
    private DisplayMetrics outMetrics;
    private Dialog chooseDialog;
    private List<String> mList = new ArrayList<>();
    private String parentGID = "";
    private TextView choiseparent;

    private String PT_Name;
    private String PT_GID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_type);
        ButterKnife.bind(this);
        tvTitlergActivity.setText("支出类别");
        mMainTypePW = new PopupWindow(this);
        mChildTypePW = new PopupWindow(this);
        manager = this.getWindowManager();
        outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        loadData();
        setListener();

    }

    private void loadData() {
        mChildPosition = 0;
        Intent intent = getIntent();
        if (intent != null) {
            PT_Name = intent.getStringExtra("ET_Name");
            PT_GID = intent.getStringExtra("ET_GID");
        }

        getType();
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        if (PT_GID != null && !PT_GID.equals("")) {
                            for (int i = 0; i < mParentTypeList.size(); i++) {
                                if (map.get(mParentTypeList.get(i)).size() < 1) {
                                    if (PT_GID.equals(mParentTypeList.get(i).getGID())) {
                                        mMainPosition = i;
//                                    mParentTypeList.get(mMainPosition).setChecked(true);
                                        break;
                                    }

                                } else {
                                    for (int j = 0; j < map.get(mParentTypeList.get(i)).size(); j++) {
                                        if (PT_GID.equals(map.get(mParentTypeList.get(i)).get(j).getGID())) {
                                            mMainPosition = i;
                                            mChildPosition = j;
//                                            mParentTypeList.get(mMainPosition).setChecked(true);
                                            map.get(mParentTypeList.get(mMainPosition)).get(mChildPosition).setChecked(true);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (mParentTypeList.size() > 0) {
                            mParentTypeList.get(mMainPosition).setChecked(true);
                            mParentGid = mParentTypeList.get(mMainPosition).getGID();
                            mParentName = mParentTypeList.get(mMainPosition).getET_Name();
//                            mParentSync = mParentTypeList.get(mMainPosition).getPT_SynType();
                        }
                        mMainAdapter = new ChoiceExpensesMainAdapter(ChoiceExpensesActivity.this, mParentTypeList);
                        lvChoiceTypeMain.setAdapter(mMainAdapter);
                        if (mParentTypeList.size() > 0) {
                            // lvChoiceTypeChild.removeHeaderView(llHeadView);

                            mChildAdapter = new ChoiceExpenseschildAdapter(ChoiceExpensesActivity.this, map.get(mParentTypeList.get(mMainPosition)));
                            //lvChoiceTypeChild.addHeaderView(llHeadView);
                            //lvChoiceTypeChild.addFooterView(llHeadView);
                            lvChoiceTypeChild.setAdapter(mChildAdapter);
                        }
                        break;
                }
                return false;
            }
        });
    }

    private void showMainTypePW(final int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_edit_main_type, null, false);
        TextView cancel = view.findViewById(R.id.tv_cancel);
        final EditText edit = view.findViewById(R.id.ed_edit_main_type);
        final EditText remark = view.findViewById(R.id.et_remark_detail);
        TextView save = view.findViewById(R.id.tv_save);
        TextView delete = view.findViewById(R.id.tv_delete);
        mMainTypePW.setContentView(view);
        mMainTypePW.setBackgroundDrawable(new ColorDrawable());
        mMainTypePW.setOutsideTouchable(true);
        mMainTypePW.setFocusable(true);
        mMainTypePW.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mMainTypePW.setWidth(YSLUtils.getPhoneWidth() * 7 / 8);
        mMainTypePW.setAnimationStyle(R.style.pop_show_style);
        mMainTypePW.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        edit.setText(mParentTypeList.get(position).getET_Name());
        remark.setText(mParentTypeList.get(position).getET_Remark());
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YSLUtils.setBackgroundAlpha(1.0f, ChoiceExpensesActivity.this);
                mMainTypePW.dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edit.getText().toString()) || edit.getText().toString().equals("")) {
                    CustomToast.makeText(ChoiceExpensesActivity.this, "一级分类名称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    submitMianType(position, edit.getText().toString(), remark.getText().toString());
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edit.getText().toString()) || edit.getText().toString().equals("")) {
                    CustomToast.makeText(ChoiceExpensesActivity.this, "一级分类名称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    deletMaintype(position);
                }
            }
        });

    }

    /**
     * @param position ,集合中元素集体位置
     */
    private void showCdildTypePW(final int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_edit_child_type, null, false);
        TextView cancel = view.findViewById(R.id.tv_cancel);
        final EditText edit = view.findViewById(R.id.ed_edit_child_type);
        final EditText etRemark = view.findViewById(R.id.et_remark_detail);
        TextView save = view.findViewById(R.id.tv_save);
        TextView delete = view.findViewById(R.id.tv_delete);
        choiseparent = view.findViewById(R.id.te_choise_parent_type);
        mChildTypePW.setContentView(view);
        mChildTypePW.setBackgroundDrawable(new ColorDrawable());
        mChildTypePW.setOutsideTouchable(true);
        mChildTypePW.setFocusable(true);
        mChildTypePW.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mChildTypePW.setWidth(YSLUtils.getPhoneWidth() * 7 / 8);
        mChildTypePW.setAnimationStyle(R.style.pop_show_style);
        mChildTypePW.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        choiseparent.setText(mParentTypeList.get(mMainPosition).getET_Name());
        parentGID = mParentTypeList.get(mMainPosition).getGID();
        edit.setText(map.get(mParentTypeList.get(mMainPosition)).get(position).getET_Name());
        etRemark.setText(map.get(mParentTypeList.get(mMainPosition)).get(position).getET_Remark());
        choiseparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseDialog(mList, choiseparent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YSLUtils.setBackgroundAlpha(1.0f, ChoiceExpensesActivity.this);
                mChildTypePW.dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(choiseparent.getText().toString()) || choiseparent.getText().toString().equals("")) {
                    CustomToast.makeText(ChoiceExpensesActivity.this, "一级分类名称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(edit.getText().toString()) || edit.getText().toString().equals("")) {
                    CustomToast.makeText(ChoiceExpensesActivity.this, "二级分类名称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    submitChildType(position, edit.getText().toString(), etRemark.getText().toString());
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edit.getText().toString()) || edit.getText().toString().equals("")) {
                    CustomToast.makeText(ChoiceExpensesActivity.this, "二级分类名称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    deletChildtype(position);
                }
            }
        });

    }

    private void deletChildtype(int position) {
        RequestParams params = new RequestParams();
        params.put("GID", map.get(mParentTypeList.get(mMainPosition)).get(position).getGID());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                CustomToast.makeText(ChoiceExpensesActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                loadData();
                mChildTypePW.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, ChoiceExpensesActivity.this);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(ChoiceExpensesActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(this, HttpAPI.API().Expenses_DelteType, params, callBack);
    }

    /**
     * @param position,
     * @param s,        编辑二级分类
     */
    private void submitChildType(int position, String s, String remark) {
        RequestParams params = new RequestParams();
        params.put("GID", map.get(mParentTypeList.get(mMainPosition)).get(position).getGID());
        params.put("Name", s);
        params.put("ParentGID", parentGID);
        params.put("Remark", remark);

        Le(msChildTypeList.get(position).getGID() + "-----" + parentGID);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                CustomToast.makeText(ChoiceExpensesActivity.this, "编辑成功", Toast.LENGTH_SHORT).show();
                loadData();
                mChildTypePW.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, ChoiceExpensesActivity.this);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(ChoiceExpensesActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(this, HttpAPI.API().Expenses_EditType, params, callBack);
    }

    private void showChooseDialog(List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0; i < mlist.size(); i++) {
            if (mlist.get(i).equals(mTextView.getText().toString())) {
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        for (int i = 0; i < mParentTypeList.size(); i++) {
                            if (choiseparent.getText().toString().equals(mParentTypeList.get(i).getET_Name())) {
                                parentGID = mParentTypeList.get(i).getGID();
                            }
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();

    }

    /**
     * @param position 当前主类在集合中的位置
     *                 删除主类
     */
    private void deletMaintype(int position) {
        RequestParams params = new RequestParams();
        params.put("GID", mParentTypeList.get(position).getGID());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                CustomToast.makeText(ChoiceExpensesActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                loadData();
                mMainTypePW.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, ChoiceExpensesActivity.this);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(ChoiceExpensesActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(this, HttpAPI.API().Expenses_DelteType, params, callBack);
    }

    /**
     * @param position 当前主类在集合中的位置
     * @param content  修改后的主类名称
     *                 编辑主类
     */
    private void submitMianType(int position, String content, String remark) {
        RequestParams params = new RequestParams();
        params.put("GID", mParentTypeList.get(position).getGID());
        params.put("Name", content);
        params.put("ParentGID", "");
        params.put("Remark", remark);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                CustomToast.makeText(ChoiceExpensesActivity.this, "编辑成功", Toast.LENGTH_SHORT).show();
                loadData();
                mMainTypePW.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, ChoiceExpensesActivity.this);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(ChoiceExpensesActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(this, HttpAPI.API().Expenses_EditType, params, callBack);
    }

    private void setListener() {
        mMainTypePW.setOnDismissListener(this);
        mChildTypePW.setOnDismissListener(this);
        addchildtypebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mParentGid != null) {
                    Intent intent = new Intent(ChoiceExpensesActivity.this, AddExpenseschildTypeActivity.class);
                    intent.putExtra("ET_GID", mParentGid);
                    intent.putExtra("ET_NAME", mParentName);
                    startActivity(intent);
                } else {
                    CustomToast.makeText(ChoiceExpensesActivity.this, "请先添加一级分类", Toast.LENGTH_SHORT).show();
                }

            }
        });
        addmaintypebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceExpensesActivity.this, AddExpensesMainTypeActivity.class);
                startActivity(intent);
            }
        });
        lvChoiceTypeMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != mMainPosition) {
                    mParentTypeList.get(mMainPosition).setChecked(false);
                    mParentTypeList.get(position).setChecked(true);
                    for (int i = 0; i < map.get(mParentTypeList.get(mMainPosition)).size(); i++) {
                        map.get(mParentTypeList.get(mMainPosition)).get(i).setChecked(false);
                    }
                    mMainPosition = position;
                    mChildPosition = 0;
//                    mChildSync = 0;
                } else {
                    mParentTypeList.get(position).setChecked(true);
                }
                mParentGid = mParentTypeList.get(position).getGID();
                mParentName = mParentTypeList.get(position).getET_Name();
//                mParentSync = mParentTypeList.get(position).getPT_SynType();
                mMainAdapter.notifyDataSetChanged();
                mChildAdapter = new ChoiceExpenseschildAdapter(ChoiceExpensesActivity.this,
                        map.get(mParentTypeList.get(position)));
                lvChoiceTypeChild.setAdapter(mChildAdapter);
            }
        });
        lvChoiceTypeMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                YSLUtils.setBackgroundAlpha(0.5f, ChoiceExpensesActivity.this);
                showMainTypePW((int) id);
                return false;
            }
        });
        lvChoiceTypeChild.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //选择子分类
                if (position != mChildPosition) {
                    map.get(mParentTypeList.get(mMainPosition)).get(position).setChecked(true);
                    map.get(mParentTypeList.get(mMainPosition)).get(mChildPosition).setChecked(false);
                    mChildPosition = position;
                } else {
                    map.get(mParentTypeList.get(mMainPosition)).get(position).setChecked(true);
                }
//                mChildSync = map.get(mParentTypeList.get(mMainPosition)).get(position).getPT_SynType();
                mChildAdapter.notifyDataSetChanged();


            }
        });
        lvChoiceTypeChild.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mList.clear();
                mList.add("无上级分类");
                for (int i = 0; i < mParentTypeList.size(); i++) {
                    mList.add(mParentTypeList.get(i).getET_Name());
                }
                YSLUtils.setBackgroundAlpha(0.5f, ChoiceExpensesActivity.this);
                showCdildTypePW((int) id);
                return false;
            }
        });
        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvChoiceTypeConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                for (int i = 0; i < mParentTypeList.size(); i++) {
                    //没有子分类
                    if (map.get(mParentTypeList.get(i)).size() < 1) {
                        if (mParentTypeList.get(i).isChecked()) {
                            intent.putExtra("ET_Name", mParentTypeList.get(i).getET_Name());
                            intent.putExtra("ET_GID", mParentTypeList.get(i).getGID());
                            setResult(55, intent);
                            finish();
                        }
                    } else {
                        //有子分类
                        if (mParentTypeList.get(i).isChecked()) {
                            boolean flag = true;
                            for (int j = 0; j < map.get(mParentTypeList.get(i)).size(); j++) {
                                if (map.get(mParentTypeList.get(i)).get(j).isChecked()) {
                                    flag = false;
                                    intent.putExtra("ET_Name", mParentTypeList.get(i).getET_Name()+"-"+map.get(mParentTypeList.get(i)).get(j).getET_Name());
                                    intent.putExtra("ET_GID", map.get(mParentTypeList.get(i)).get(j).getGID());
                                    setResult(55, intent);
                                    finish();
                                }
                            }
                            if (flag) {
                                CustomToast.makeText(ChoiceExpensesActivity.this, "请还未选择分类", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

            }
        });
        refreshChoiceTypeMain.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mChildPosition = 0;
                mMainPosition = 0;
                getType();
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
        refreshChoiceTypeChild.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mChildPosition = 0;
                mMainPosition = 0;
                getType();
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

    private void getType() {
        mParentTypeList.clear();
        map.clear();
        RequestParams params = new RequestParams();
        params.put("GID","");
        HttpHelper.post(this, HttpAPI.API().Expenses_TypeLsit, params,new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ChoiceExpensesTypeBean type = CommonFun.JsonToObj(responseString, ChoiceExpensesTypeBean.class);

                //获取父类型
                for (int i = 0; i < type.getData().size(); i++) {
                    if (type.getData().get(i).getET_ParentGID() == null || "".equals(type.getData().get(i).getET_ParentGID())) {
                        mParentTypeList.add(type.getData().get(i));
                    }
                }
                //获取子类型
                for (int i = 0; i < mParentTypeList.size(); i++) {
                    mChildTypeList = new ArrayList<>();
                    for (int j = 0; j < type.getData().size(); j++) {
                        if (!"".equals(mParentTypeList.get(i).getGID()) &&
                                mParentTypeList.get(i).getGID().equals(type.getData().get(j).getET_ParentGID())) {
                            mChildTypeList.add(type.getData().get(j));
                        }
                    }
                    msChildTypeList.addAll(mChildTypeList);
                    if (mChildTypeList != null) {
                        map.put(mParentTypeList.get(i), mChildTypeList);
                        mChildTypeList = null;
                    }
                }
                mHandler.sendEmptyMessage(1);
                refreshChoiceTypeChild.setRefreshing(false);
                refreshChoiceTypeMain.setRefreshing(false);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(ChoiceExpensesActivity.this, msg, Toast.LENGTH_SHORT).show();
                refreshChoiceTypeChild.setRefreshing(false);
                refreshChoiceTypeMain.setRefreshing(false);
            }
        });
    }

    @Override
    public void onDismiss() {
        YSLUtils.setBackgroundAlpha(1.0f, this);
    }
}