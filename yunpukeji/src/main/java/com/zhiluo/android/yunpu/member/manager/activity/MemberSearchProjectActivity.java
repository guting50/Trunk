package com.zhiluo.android.yunpu.member.manager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.manager.adapter.CheckMemberInfoAdapter;
import com.zhiluo.android.yunpu.member.manager.adapter.MemberSearchProjectAdapter;
import com.zhiluo.android.yunpu.member.manager.bean.MemberProjectListBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ZPH on 2019-05-15.
 */

public class MemberSearchProjectActivity extends BaseActivity {

    @Bind(R.id.tv_check_member_back)
    TextView tvCheckMemberBack;
    @Bind(R.id.rl_check_member_title)
    RelativeLayout rlCheckMemberTitle;
    @Bind(R.id.et_check_member_search)
    EditText etCheckMemberSearch;
    @Bind(R.id.ll_check_member_search)
    LinearLayout llCheckMemberSearch;
    @Bind(R.id.lv_check_member_info)
    BaseListView lvCheckMemberInfo;
    @Bind(R.id.refresh_check_member)
    WaveSwipeRefreshLayout refreshCheckMember;


    private InputHandler mInputHandler = new InputHandler();
    private boolean hasTimerDown = false;
    private long pretime;//第一次的时间
    private String mInputContent;//输入框输入的内容
    private int mRefreshIndex = 2;
    private boolean mIsLoadMore;//加载更多
    private int mPageTotal;//数据总页数
    private MemberProjectListBean memberProjectList;
    private MemberProjectListBean.DataBean.DataListBean memberProjectinfo;
    private MemberSearchProjectAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_search_project);
        ButterKnife.bind(this);
        initData();
        initLisenter();
    }

    private void initLisenter() {
        tvCheckMemberBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //设置输入框监听
        etCheckMemberSearch.addTextChangedListener(new TextWatcher() {
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
                mInputContent = editable.toString();
                //checkMemberInfo(mInputContent, 1, 20);
            }
        });
        //为ListView子控件添加点击监听
        lvCheckMemberInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                memberProjectinfo = memberProjectList.getData().getDataList().get(position);//获得被点击的Item的对象
                //回传给开启Activity的Activity选择项目信息实体
                Intent intent = new Intent();
                intent.putExtra("Project", memberProjectinfo);
                setResult(88888, intent);
                finish();
            }
        });

        //下拉刷新，上拉加载更多
        refreshCheckMember.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetMemberCheckInProjectList(1);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {

                    if (mRefreshIndex <= mPageTotal) {
                        mIsLoadMore = true;
                        GetMemberCheckInProjectList(mRefreshIndex);
                        mRefreshIndex++;
                    } else {
                        CustomToast.makeText(MemberSearchProjectActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                        refreshCheckMember.setLoading(false);
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


    private void initData() {

        GetMemberCheckInProjectList(1);

    }


    private void GetMemberCheckInProjectList(int PageIndex){

        RequestParams params = new RequestParams();
        params.put("PageIndex",PageIndex);
        params.put("PageSize",20);
        params.put("State",1);
        params.put("IsTrue",true);
        params.put("CodeName",mInputContent);

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                memberProjectList = CommonFun.JsonToObj(responseString,MemberProjectListBean.class);
                setAdapter();
                mIsLoadMore = false;
                refreshCheckMember.setRefreshing(false);
                refreshCheckMember.setLoading(false);

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(MemberSearchProjectActivity.this,msg,Toast.LENGTH_SHORT).show();
                mIsLoadMore = false;
                refreshCheckMember.setRefreshing(false);
                refreshCheckMember.setLoading(false);

            }
        };
        HttpHelper.post(this, HttpAPI.API().SEARCH_PROJECT,params,callBack);
    }

    /**
     * 设置适配器
     */
    private void setAdapter() {
        if (mAdapter ==null){
            mAdapter = new MemberSearchProjectAdapter(MemberSearchProjectActivity.this, memberProjectList);
            lvCheckMemberInfo.setAdapter(mAdapter);
        }else {
            mAdapter.setParam(memberProjectList);
            mAdapter.notifyDataSetChanged();
        }


    }

    private void doQurry() {
        hasTimerDown = true;
        if (System.currentTimeMillis() - pretime > 500) {
            GetMemberCheckInProjectList( 1);
            hasTimerDown = false;
        } else {
            mInputHandler.sendEmptyMessageDelayed(1, 300);
        }
    }

}
