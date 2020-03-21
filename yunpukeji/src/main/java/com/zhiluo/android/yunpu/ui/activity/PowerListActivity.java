package com.zhiluo.android.yunpu.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.adapters.PowerListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限展示列表.
 */
public class PowerListActivity extends AppCompatActivity {

    private TextView tv_back;
    private RecyclerView recyler_view;

    private List<String> mList = new ArrayList<>();
    private PowerListAdapter mAdapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_power_list);

        initView();
        setListener();
        getData();
        initRecyclerView();
    }

    /**
     * 展示服务列表
     */
    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        recyler_view = (RecyclerView) findViewById(R.id.recyler_view);
    }

    private void setListener() {
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyler_view.setLayoutManager(mLayoutManager);

        mAdapter = new PowerListAdapter(this, mList);
        recyler_view.setAdapter(mAdapter);
    }

    private void getData() {
        mList.add("网络");
        mList.add("读写存储空间");
        mList.add("允许程序进行发现和配对蓝牙设备");
        mList.add("允许访问摄像头进行拍照");
        mList.add("网络定位");
        mList.add("允许程序读取或写入系统设置");
        mList.add("安装程序");
        mList.add("NFC");
        mList.add("打印机权限");
        mList.add("接触式IC卡读卡权限");
        mList.add("访问磁条卡读卡器设备权限");
        mList.add("pos机权限");
    }

}
