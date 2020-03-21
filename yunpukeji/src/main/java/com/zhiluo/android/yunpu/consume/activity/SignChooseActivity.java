package com.zhiluo.android.yunpu.consume.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.manager.activity.AddMemberActivity;
import com.zhiluo.android.yunpu.member.manager.adapter.SignAdapter;
import com.zhiluo.android.yunpu.member.manager.adapter.addCostomfieldsAdapter;
import com.zhiluo.android.yunpu.member.manager.bean.LabelBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberLabel;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by YSL on 2018-10-18.
 */

public class SignChooseActivity extends BaseActivity implements SignAdapter.ICallBack {


    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.tv_sign_confirm)
    TextView tvSignConfirm;
    @Bind(R.id.lv_sign_commission)
    BaseListView lvSignlist;


    private MemberLabel mMemberLabel;//会员标签对象
    private List<String> mLabelList;//会员标签数据
    private List<LabelBean> mLabList;
    private List<LabelBean> havelablist;
    private SignAdapter signAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        mLabList =new ArrayList<>();
        havelablist = new ArrayList<>();
        loadData();
        setListenter();
    }


    private void loadData() {
        Intent intent = getIntent();
        if (intent!=null){
            havelablist = (List<LabelBean>) intent.getSerializableExtra("sign");
        }

        getMemberLabel();//获取会员标签

    }


    private void setListenter() {
        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvSignConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignChooseActivity.this, AddMemberActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("sign", (Serializable) mLabList);
                intent.putExtras(bundle);
                setResult(666,intent);
                finish();
            }
        });


    }

    /**
     * 获取会员标签
     */
    private void getMemberLabel() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("ML_Name", "");
        client.post(HttpAPI.API().MEMBER_LABEL, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mMemberLabel = CommonFun.JsonToObj(responseString, MemberLabel.class);
                if (mLabList != null) {
                    mLabList.clear();
                }
                for (int i = 0; i < mMemberLabel.getData().size(); i++) {
                    LabelBean labelBean = new LabelBean();
                    labelBean.setItemName(mMemberLabel.getData().get(i).getML_Name());
                    labelBean.setItemGID(mMemberLabel.getData().get(i).getML_GID());
                    labelBean.setItemColor(mMemberLabel.getData().get(i).getML_ColorValue());
                    mLabList.add(labelBean);
                }

                if (havelablist!=null){
                    for (int i = 0;i<havelablist.size();i++){
                        for (int j = 0;j<mLabList.size();j++){
                            if (havelablist.get(i).getItemGID().equals(mLabList.get(j).getItemGID())){
                                if (havelablist.get(i).isChecked()){
                                    mLabList.get(j).setChecked(true);
                                }else {
                                    mLabList.get(j).setChecked(false);
                                }
                            }
                        }
                    }
                }
                signAdapter =new SignAdapter(SignChooseActivity.this,mLabList,SignChooseActivity.this);
                lvSignlist.setAdapter(signAdapter);
                //  mHandler.sendEmptyMessage(4);
            }

            @Override
            public void onFailure(String msg) {

            }
        });

    }

    @Override
    public void click(View view) {
        int pos = (int) view.getTag();
        LabelBean bean = mLabList.get(pos);
        if (bean.isChecked()) {
            bean.setChecked(false);
        } else {
            bean.setChecked(true);
        }
        signAdapter.notifyDataSetChanged();

    }


}
