package com.zhiluo.android.yunpu.consume.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.consume.adapter.DiscountTypeAdapter;
import com.zhiluo.android.yunpu.consume.jsonbean.DiscountTypeBean;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.yslutils.DataUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 优惠活动选择页面
 * 作者：罗咏哲 on 2017/6/24 15:49.
 * 邮箱：137615198@qq.com
 */
public class DiscountTypeActivity extends AppCompatActivity {
    DiscountTypeBean mDiscountTypeBean;
    ListView listView;
    private TextView tv_save;
    DiscountTypeAdapter mAdapter;
    List<DiscountTypeBean.DataBean> mDataList;
    List<DiscountTypeBean.DataBean> mDataBeans;
    private static final String TAG = "lyz";
    private int mDiscountType;//优惠类型 1：充值优惠，2：消费优惠
    //价格
    private double mBeforePrice = 0;
    //是否是会员
    private String mUserCard;
    //会员生日,不是的话就00000
    private String mUserBirthady;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_type);
        listView = findViewById(R.id.lv_discount_type);
        tv_save = findViewById(R.id.tv_save);

        initData();
        getDiscountList();
        setListener();
    }

    private void initData() {
        mDiscountType = getIntent().getIntExtra("consume_type", 0);
        String priceFlag = getIntent().getStringExtra("before_price");
        mUserCard = getIntent().getStringExtra("user_card");
        mUserBirthady = getIntent().getStringExtra("user_birthady");
        Li(priceFlag + "=============mBeforePrice=======random:");
        Li(mUserBirthady + "=============mUserBirthady=======random:" + mUserCard);
        if (!priceFlag.equals("")) {
            mBeforePrice = Double.parseDouble(priceFlag);
        }
        Li("======mBeforePrice=========random:" + mBeforePrice);
        mDataList = new ArrayList<>();
        mAdapter = new DiscountTypeAdapter(mDataList, DiscountTypeActivity.this);
        listView.setAdapter(mAdapter);
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("type", mDataList.get(position));
                intent.putExtras(bundle);
                setResult(1001, intent);
                finish();
            }
        });

        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiscountTypeBean.DataBean bean = new DiscountTypeBean.DataBean();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("type", bean);
                intent.putExtras(bundle);
                setResult(1001, intent);
                finish();
            }
        });
    }

    /**
     * 获取优惠信息列表
     */
    private void getDiscountList() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        client.post(HttpAPI.API().DISSCOUNT_ACTIVITY, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mDiscountTypeBean = CommonFun.JsonToObj(responseString, DiscountTypeBean.class);
                mDataBeans = mDiscountTypeBean.getData();
                for (int i = 0; i < mDataBeans.size(); i++) {
                    //1充值2消费
                    if (mDataBeans.get(i).getRP_RechargeMoney() <= mBeforePrice && mDataBeans.get(i).getRP_Type() == 2) {
                        //1固定时间断2按每周3按每月4会员生日当天
                        if (mDataBeans.get(i).getRP_ValidType() == 4) {
                            if (mUserCard != null && mUserCard.equals("00000")) {
                                continue;
                            } else if (mUserBirthady != null && (!mUserBirthady.equals("00000")) && mUserBirthady.substring(5, 10).equals(DataUtils.getDataTime().substring(5, 10))) {
                                //会员生日券
                                mDataList.add(mDataBeans.get(i));
                            } else {
                                continue;
                            }
                        } else {
                            mDataList.add(mDataBeans.get(i));
                        }
                    }
                }
                //折扣
                List<DiscountTypeBean.DataBean> breakList = new ArrayList<>();
                //减
                List<DiscountTypeBean.DataBean> reduceList = new ArrayList<>();
                //送
                List<DiscountTypeBean.DataBean> giveList = new ArrayList<>();
                for (int j = 0, len = mDataList.size(); j < len; j++) {
                    DiscountTypeBean.DataBean dataBean = mDataList.get(j);
                    if (dataBean.getRP_Discount() > 0) {
                        breakList.add(dataBean);
                    } else if (dataBean.getRP_ReduceMoney() > 0) {
                        reduceList.add(dataBean);
                    } else if (dataBean.getRP_GiveMoney() > 0) {
                        giveList.add(dataBean);
                    }
                }
                if (breakList.size() > 0) {
                    Collections.sort(breakList, new Comparator<DiscountTypeBean.DataBean>() {
                        /*
                         * 返回负数表示：p1 小于p2，
                         * 返回0 表示：p1和p2相等，
                         * 返回正数表示：p1大于p2
                         */
                        public int compare(DiscountTypeBean.DataBean p1, DiscountTypeBean.DataBean p2) {
                            if (p1.getRP_Discount() > p2.getRP_Discount()) {
                                return 1;
                            }
                            if (p1.getRP_Discount() == p2.getRP_Discount()) {
                                return 0;
                            }
                            return -1;
                        }
                    });
                }
                if (reduceList.size() > 0) {
                    Collections.sort(reduceList, new Comparator<DiscountTypeBean.DataBean>() {
                        public int compare(DiscountTypeBean.DataBean p1, DiscountTypeBean.DataBean p2) {
                            if (p1.getRP_ReduceMoney() < p2.getRP_ReduceMoney()) {
                                return 1;
                            }
                            if (p1.getRP_ReduceMoney() == p2.getRP_ReduceMoney()) {
                                return 0;
                            }
                            return -1;
                        }
                    });
                }
                if (giveList.size() > 0) {
                    Collections.sort(giveList, new Comparator<DiscountTypeBean.DataBean>() {
                        public int compare(DiscountTypeBean.DataBean p1, DiscountTypeBean.DataBean p2) {
                            if (p1.getRP_GiveMoney() < p2.getRP_GiveMoney()) {
                                return 1;
                            }
                            if (p1.getRP_GiveMoney() == p2.getRP_GiveMoney()) {
                                return 0;
                            }
                            return -1;
                        }
                    });
                }
                mDataList.clear();
                mDataList.addAll(breakList);
                mDataList.addAll(reduceList);
                mDataList.addAll(giveList);
                Li("=============优惠券列表=======random:"+new Gson().toJson(mDataList));
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

}
