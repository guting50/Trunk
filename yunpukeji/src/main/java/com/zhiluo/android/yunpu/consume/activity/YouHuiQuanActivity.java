package com.zhiluo.android.yunpu.consume.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.consume.adapter.YHQAdapter;
import com.zhiluo.android.yunpu.consume.bean.YHQBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.manager.bean.MemberInfoBean;
import com.zhiluo.android.yunpu.mvp.presenter.PostVipPresenter;
import com.zhiluo.android.yunpu.mvp.view.IPostVipView;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

public class YouHuiQuanActivity extends AppCompatActivity implements YHQAdapter.ImageClick {


    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.tv_choice_yhq_confirm)
    TextView tvChoiceYhqConfirm;
    @Bind(R.id.iv_choice_yhj_search)
    ImageView ivChoiceYhjSearch;
    @Bind(R.id.et_choice_yhj_condition)
    EditText etChoiceYhjCondition;
    @Bind(R.id.lv_yhq)
    BaseListView lvYhq;


    private String mVipCard;
    private double orderMoney;
    private MemberInfoBean memberInfoBean;
    private YHQAdapter mAdapter;

    private YHQBean mYhqBean;
    private List<YHQBean.DataBean> yhqList = new ArrayList<>();
    private List<YHQBean.DataBean> mCheckedList = new ArrayList<>();
    private PostVipPresenter vippresenter;
    private IPostVipView vipView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_hui_quan);
        ButterKnife.bind(this);

        loadData();
        setLisenter();

    }

    private void setLisenter() {

        tvChoiceYhqConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Li("=======优惠券=== random:" +new Gson().toJson(mCheckedList));
                Intent intent = new Intent();
                intent.putExtra("YHQ", (Serializable) mCheckedList);
                setResult(77, intent);
                finish();
            }
        });


        findViewById(R.id.tv_back_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etChoiceYhjCondition.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    if (s.length() == 11) {
                        checkYHQ(s.toString());
                    }
                }
            }
        });
    }

    private void loadData() {
        mVipCard = getIntent().getStringExtra("VIP");
        orderMoney = getIntent().getDoubleExtra("orderMoney", 0.00);
        Li("============orderMoney=========== random:" +orderMoney);
        mCheckedList = (List<YHQBean.DataBean>) getIntent().getSerializableExtra("mCheckedList");


        mAdapter = new YHQAdapter(yhqList, YouHuiQuanActivity.this, YouHuiQuanActivity.this);
        lvYhq.setAdapter(mAdapter);


        vippresenter = new PostVipPresenter(this);
        vipView = new IPostVipView() {
            @Override
            public void getvipsuccesss(MemberInfoBean bean) {
                memberInfoBean = bean;
                addData();

            }

            @Override
            public void getvipfail(String result) {
                CustomToast.makeText(YouHuiQuanActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        };
        vippresenter.attachView(vipView);

        if (!mVipCard.equals("00000")) {
            vippresenter.postVip(mVipCard, true);
        }

    }


    @Override
    public void click(View v) {
        switch (v.getId()) {
            case R.id.ic_item_choice:
//                if (mCheckedList.size() > 0) {
//                    CustomToast.makeText(YouHuiQuanActivity.this, "不可叠加优惠券只能使用一张", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                int pos = (int) v.getTag();
                if (yhqList.get(pos).isCheck()) {
                    yhqList.get(pos).setCheck(false);
                    for (int i = 0; i < mCheckedList.size(); i++) {
                        if (yhqList.get(pos).getGID().equals(mCheckedList.get(i).getGID())) {
                            mCheckedList.remove(i);
                            break;
                        }
                    }
                } else {
//                    if (yhqList.get(pos).getEC_DiscountType() == 2) {
//                        for (int i = 0; i < mCheckedList.size(); i++) {
//                            if (mCheckedList.get(i).getEC_DiscountType() == 2) {
//                                CustomToast.makeText(YouHuiQuanActivity.this, "不可叠加优惠券只能使用一张", Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                        }
//                    }
                    if (yhqList.get(pos).getEC_Denomination() > orderMoney) {
                        CustomToast.makeText(YouHuiQuanActivity.this, "未达到使用金额", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (yhqList.get(pos).getEC_IsOverlay() == 1) {
                        Li("============111=========== random:" );
                        yhqList.get(pos).setCheck(true);
                        mCheckedList.add(yhqList.get(pos));
                    } else {
                        if (mCheckedList.size() > 0) {
                            Li("============222=========== random:" );
                            Li("========a== random:" +yhqList.get(pos).getEC_GID());
                            int z = 0;
                            for (int i = 0; i < mCheckedList.size(); i++) {
                                Li("=======b=== random:" +new Gson().toJson(mCheckedList.get(i).getEC_GID()));
                                if (yhqList.get(pos).getEC_GID().equals(mCheckedList.get(i).getEC_GID())) {
                                    CustomToast.makeText(YouHuiQuanActivity.this, "该优惠券id不可叠加使用", Toast.LENGTH_SHORT).show();
                                    break;
                                }else if(mCheckedList.get(i).getEC_IsOverlay() == 0){
                                    CustomToast.makeText(YouHuiQuanActivity.this, "该优惠券不可叠加使用", Toast.LENGTH_SHORT).show();
                                    break;
                                } else{
                                    z = i + 1;
                                }
                            }
                            if (z == mCheckedList.size()) {
                                yhqList.get(pos).setCheck(true);
                                mCheckedList.add(yhqList.get(pos));
                            }
                        } else {
                            Li("============333=========== random:" );
                            yhqList.get(pos).setCheck(true);
                            mCheckedList.add(yhqList.get(pos));
                        }
                    }
                }
                mAdapter.notifyDataSetChanged();
                break;

        }
    }


    private void addData() {
        for (int i = 0; i < memberInfoBean.getData().getCouponsList().size(); i++) {
            YHQBean.DataBean bean = new YHQBean.DataBean();
            bean.setEC_Discount(memberInfoBean.getData().getCouponsList().get(i).getEC_Discount());
            bean.setEC_Denomination(memberInfoBean.getData().getCouponsList().get(i).getEC_Denomination());
            bean.setEC_DiscountType(memberInfoBean.getData().getCouponsList().get(i).getEC_DiscountType());
            bean.setVCR_IsForver(memberInfoBean.getData().getCouponsList().get(i).getVCR_IsForver());
            bean.setVCR_EndTime(memberInfoBean.getData().getCouponsList().get(i).getVCR_EndTime());
            bean.setVCR_StatrTime(memberInfoBean.getData().getCouponsList().get(i).getVCR_StatrTime());
            bean.setGID(memberInfoBean.getData().getCouponsList().get(i).getGID());
            bean.setSM_Name(memberInfoBean.getData().getCouponsList().get(i).getSM_Name());
            bean.setEC_IsOverlay(memberInfoBean.getData().getCouponsList().get(i).getEC_IsOverlay());
            bean.setEC_GID(memberInfoBean.getData().getCouponsList().get(i).getEC_GID());
            bean.setEC_Name(memberInfoBean.getData().getCouponsList().get(i).getEC_Name());
            bean.setCY_GID(memberInfoBean.getData().getCouponsList().get(i).getCY_GID());
            bean.setSM_GID(memberInfoBean.getData().getCouponsList().get(i).getSM_GID());
            bean.setOrderMoney(orderMoney);

            if (mCheckedList.size() > 0) {
                for (int j = 0; j < mCheckedList.size(); j++) {
                    if (memberInfoBean.getData().getCouponsList().get(i).getGID().equals(mCheckedList.get(j).getGID())) {
                        bean.setCheck(true);
                    }
                }
            }
            yhqList.add(bean);
        }
    }

    /**
     * 检查优惠券
     *
     * @param s
     */
    private void checkYHQ(String s) {
        RequestParams params = new RequestParams();
        params.put("Code", s);
        params.put("Money", orderMoney);
//        params.put("Type", type);
        HttpHelper.post(this, "CouponManager/QueryCouponsByCode", params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mYhqBean = CommonFun.JsonToObj(responseString, YHQBean.class);
                if (mCheckedList.size() > 0) {
                    for (int j = 0; j < mCheckedList.size(); j++) {
                        if (mYhqBean.getData().getGID().equals(mCheckedList.get(j).getGID())) {
                            mYhqBean.getData().setCheck(true);
                        }
                    }
                }
                mYhqBean.getData().setOrderMoney(orderMoney);
                yhqList.add(mYhqBean.getData());
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(YouHuiQuanActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
