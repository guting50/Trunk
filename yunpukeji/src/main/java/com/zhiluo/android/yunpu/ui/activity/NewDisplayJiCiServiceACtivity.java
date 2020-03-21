package com.zhiluo.android.yunpu.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.adapter.ChoiceServiceAdapter;
import com.zhiluo.android.yunpu.ui.adapter.DetailInitServiceAdapter;
import com.zhiluo.android.yunpu.ui.adapter.DisplayserviceAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ${YSL} on 2018-04-16.
 */

public class NewDisplayJiCiServiceACtivity extends AppCompatActivity implements DisplayserviceAdapter.ImageClick,
        DisplayserviceAdapter.EditClick, View.OnClickListener, PopupWindow.OnDismissListener {
    @Bind(R.id.tv_back)
    TextView tvBack;//返回
    @Bind(R.id.img_ok)
    ImageView mSave;//保存
    @Bind(R.id.cb_choice_all)
    CheckBox cbChoiceAll;
    @Bind(R.id.lv_choice_service)
    ListView lvChoiceService;
    @Bind(R.id.tv_server_count)
    TextView tvServerCount;
    TextView cancel;
    EditText count;
    EditText timewithlimited;
    CheckBox xianshi;
    RelativeLayout choiseday;
    private GoodsCheckResponseByType mServiceBean;
    // private List<GoodsCheckResponseByType.DataBean.DataListBean> mList;
    private List<DataBean.InitServiceListBean> mListEditedMsg = new ArrayList<>();//该界面ListView中的所有实体项集合
    private List<DataBean.InitServiceListBean> mSelectedList = new ArrayList<>();//改版后，选中的集合
    private DisplayserviceAdapter mDispalyAdapter;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mBackList = new ArrayList<>();//选中列表
    private PopupWindow mPopWindow;
    private int mLocation;
    private Dialog chooseDialog;
    private List<String> mDayLists = new ArrayList<>();
    private TextView mTimeType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayjiciservice);
        ButterKnife.bind(this);
        inits();
        setListener();
    }

    /**
     * 展示服务列表
     */
    private void inits() {
        mListEditedMsg = new ArrayList<>();
        Intent intent = getIntent();
        if (intent!=null){
            mListEditedMsg = (List<DataBean.InitServiceListBean>) intent.getSerializableExtra("Service");
            for (int i = 0;i<mListEditedMsg.size();i++){
                if (mListEditedMsg.get(i).isChecked()){
                    mSelectedList.add(mListEditedMsg.get(i));
                    tvServerCount.setText("已选" + mSelectedList.size() + "项服务");
                }
            }
            if (mSelectedList.size() <mListEditedMsg.size()||mSelectedList.size()==0){
                cbChoiceAll.setChecked(false);
            }else {
                cbChoiceAll.setChecked(true);
            }
        }
        String[] data = getResources().getStringArray(R.array.limit_time_type);
        for (String str : data) {
            mDayLists.add(str);
        }
        TextView mAddServer = findViewById(R.id.add_server);//添加服务
        TextView mDeleteIt = findViewById(R.id.tv_clear_);//清除服务
        mDispalyAdapter = new DisplayserviceAdapter(this, mListEditedMsg,
                NewDisplayJiCiServiceACtivity.this, NewDisplayJiCiServiceACtivity.this);
        lvChoiceService.setAdapter(mDispalyAdapter);
        mAddServer.setOnClickListener(this);
        mDeleteIt.setOnClickListener(this);
    }

    private void setListener() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("Service", (Serializable) mListEditedMsg);
                setResult(200, intent1);
//                CustomToast.makeText(NewDisplayJiCiServiceACtivity.this,"您取消了计次设定",Toast.LENGTH_SHORT).show();
                NewDisplayJiCiServiceACtivity.this.finish();
            }
        });

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("Service", (Serializable) mListEditedMsg);
                setResult(200, intent1);
                CustomToast.makeText(NewDisplayJiCiServiceACtivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                NewDisplayJiCiServiceACtivity.this.finish();
            }
        });

        cbChoiceAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvServerCount.setText("已选" + mListEditedMsg.size() + "项服务");
                    for (int i = 0; i < mListEditedMsg.size(); i++) {
                        mListEditedMsg.get(i).setChecked(true);
                    }
                    mSelectedList.addAll(mListEditedMsg);
                } else {
                    mSelectedList.clear();
                    tvServerCount.setText("已选0项服务");
                    for (int i = 0; i < mListEditedMsg.size(); i++) {
                        mListEditedMsg.get(i).setChecked(false);
                    }
                    mSelectedList.clear();
                }
                mDispalyAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * @param v， 选择服务左边的item事件
     */
    @Override
    public void click(View v) {
        switch (v.getId()) {
            case R.id.ic_item_choice:
                int pos = (int) v.getTag();
                if (mListEditedMsg.get(pos).isChecked()) {
                    mListEditedMsg.get(pos).setChecked(false);
                    for (int i = 0; i < mSelectedList.size(); i++) {
                        if (mListEditedMsg.get(pos).getSC_GD().equals(mSelectedList.get(i).getSC_GD())) {
                            mSelectedList.remove(i);
                            break;
                        }
                    }
                } else {
                    mListEditedMsg.get(pos).setChecked(true);
                    mSelectedList.add(mListEditedMsg.get(pos));
                }
                if (mSelectedList.size() < 1) {
                    cbChoiceAll.setChecked(false);
                }

                tvServerCount.setText("已选" + mSelectedList.size() + "项服务");
                mDispalyAdapter.notifyDataSetChanged();
                break;
        }
    }

    /**
     * 显示编辑服务弹窗
     */
    private void showPopWindow() {
        mPopWindow = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.pop_ysl_edit, null, false);
        cancel = view.findViewById(R.id.tv_cancel);
        TextView save = view.findViewById(R.id.tv_save);
        TextView delete = view.findViewById(R.id.tv_delete);
        count = view.findViewById(R.id.ed_days);
        mTimeType = view.findViewById(R.id.tv_time_type);
        timewithlimited = view.findViewById(R.id.ed_time_with_limited);
        xianshi = view.findViewById(R.id.cb_xianshi);
        choiseday = view.findViewById(R.id.r_choise_day);
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        mPopWindow.setContentView(view);
        mPopWindow.setBackgroundDrawable(new ColorDrawable());
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setFocusable(true);
        mPopWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopWindow.setWidth(width * 7 / 8);
        mPopWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        cancel.setOnClickListener(this);
        save.setOnClickListener(this);
        delete.setOnClickListener(this);
        count.setOnClickListener(this);
        timewithlimited.setOnClickListener(this);
        choiseday.setOnClickListener(this);
        mPopWindow.setOnDismissListener(this);
        if (!(mListEditedMsg.get(mLocation).getSR_Number() + "").equals("")) {
            count.setText(mListEditedMsg.get(mLocation).getSR_Number()+"");
        }
        xianshi.setChecked(mListEditedMsg.get(mLocation).isChecked());
        if (mListEditedMsg.get(mLocation).isChecked()){
            timewithlimited.setEnabled(true);
        }else {
            timewithlimited.setEnabled(false);
        }
        if (!(mListEditedMsg.get(mLocation).getSR_TimeUnit()+"").equals("")){
            if (mListEditedMsg.get(mLocation).getSR_TimeUnit() ==1){
                mTimeType.setText("天");
            }else if (mListEditedMsg.get(mLocation).getSR_TimeUnit() ==2){
                mTimeType.setText("月");
            }else if (mListEditedMsg.get(mLocation).getSR_TimeUnit() ==3){
                mTimeType.setText("年");
            }
        }
        timewithlimited.setText(mListEditedMsg.get(mLocation).getSR_Timer()+"");
        xianshi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    timewithlimited.setEnabled(true);
                    timewithlimited.setClickable(true);
                    choiseday.setClickable(true);
                    choiseday.setEnabled(true);
                } else {
                    timewithlimited.setEnabled(false);
                    timewithlimited.setClickable(false);
                    timewithlimited.setText("");
                    choiseday.setClickable(false);
                    choiseday.setEnabled(false);
                }
            }
        });

        count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("0")||s.toString().equals("00")) {
                    CustomToast.makeText(NewDisplayJiCiServiceACtivity.this, "只能输入大于0整数", Toast.LENGTH_SHORT).show();
                    count.setText("1");
                }
            }
        });

        timewithlimited.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("0")||s.toString().equals("00")) {
                    CustomToast.makeText(NewDisplayJiCiServiceACtivity.this, "只能输入大于0整数", Toast.LENGTH_SHORT).show();
                    timewithlimited.setText("1");
                }
            }
        });
    }

    private void showChooseDialog(List<String> mDayList, final TextView mTextView) {
        int j = 0;
        for (int i = 0;i<mDayList.size();i++){
            if (mDayList.get(i).equals(mTextView.getText().toString())){
                j= i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mDayList).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel://取消
                YSLUtils.setBackgroundAlpha(1.0f, this);
                mPopWindow.dismiss();
                break;
            case R.id.add_server://添加服务
                Intent intent = new Intent(NewDisplayJiCiServiceACtivity.this, ChoiceInitServiceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mListEditedMsg", (Serializable) mListEditedMsg);
                intent.putExtras(bundle);
                startActivityForResult(intent, 101);
                break;
            case R.id.tv_clear_:
                mListEditedMsg.clear();
                mSelectedList.clear();
                //清除全部之后，把全选按钮设为false
                cbChoiceAll.setChecked(false);
                mDispalyAdapter.notifyDataSetChanged();
                tvServerCount.setText("已选0项服务");
                break;
            case R.id.tv_save://保存
                if (count.getText().toString() == null||count.getText().toString().equals("")||count.getText().toString().equals("0")) {
                    CustomToast.makeText(NewDisplayJiCiServiceACtivity.this, "请设置次数", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    mListEditedMsg.get(mLocation).setSR_Number(Integer.parseInt(count.getText().toString()));
                }
                if (xianshi.isChecked()) {
                    if (timewithlimited.getText().toString() == null||timewithlimited.getText().toString().equals("")||timewithlimited.getText().toString().equals("0")) {
                        CustomToast.makeText(NewDisplayJiCiServiceACtivity.this, "请设置限时时间", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        mListEditedMsg.get(mLocation).setSR_Timer(Integer.parseInt(timewithlimited.getText().toString()));
                    }
                    mListEditedMsg.get(mLocation).setChecked(true);
                } else {
                    mListEditedMsg.get(mLocation).setSR_Timer(0);
                    mListEditedMsg.get(mLocation).setChecked(false);
                }
                if (mTimeType.getText().toString().equals("天")) {
                    mListEditedMsg.get(mLocation).setSR_TimeUnit(1);
                } else if (mTimeType.getText().toString().equals("月")) {
                    mListEditedMsg.get(mLocation).setSR_TimeUnit(2);
                } else if (mTimeType.getText().toString().equals("年")) {
                    mListEditedMsg.get(mLocation).setSR_TimeUnit(3);
                }
                mDispalyAdapter.notifyDataSetChanged();
                CustomToast.makeText(NewDisplayJiCiServiceACtivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
                break;
            case R.id.tv_delete://删除
                if (mSelectedList.size() > 0) {
                    for (int i = 0; i < mSelectedList.size(); i++) {
                        if (mSelectedList.get(i).getSC_GD().equals(mListEditedMsg.get(mLocation).getSC_GD())) {
                            mSelectedList.remove(i);
                        }
                    }
                }
                mListEditedMsg.remove(mLocation);
                tvServerCount.setText("已选" + mSelectedList.size() + "项服务");
                mDispalyAdapter.notifyDataSetChanged();
                mPopWindow.dismiss();
                break;
            case R.id.r_choise_day:
                showChooseDialog(mDayLists, mTimeType);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 &&resultCode ==200) {
            mBackList = (List<GoodsCheckResponseByType.DataBean.DataListBean>) data.getSerializableExtra("Service");
            //去掉多余的实体项
            if (mBackList != null && mBackList.size() > 0) {
//                for (int j = 0; j < mBackList.size(); j++) {
//                    for (int s = 0; s < mListEditedMsg.size(); s++) {
//                        if (mBackList.get(j).getPM_Name().equals(mListEditedMsg.get(s).getSG_Name())) {
//                            mBackList.remove(j);
//                        }
//
//                    }
//                    //添加筛选后的实体项到集合
//                }

                for (int j = 0; j < mListEditedMsg.size(); j++) {
                    for (int s = 0; s < mBackList.size(); s++) {
                        if (mBackList.get(s).getPM_Name().equals(mListEditedMsg.get(j).getSG_Name())) {
                            mBackList.remove(s);
                        }
                    }
                    //添加筛选后的实体项到集合
                }


                for (int i = 0; i < mBackList.size(); i++) {
                    DataBean.InitServiceListBean bean = new DataBean.InitServiceListBean();
                    bean.setGID(mBackList.get(i).getGID());
                    bean.setSC_GD(mBackList.get(i).getGID());
                    bean.setSG_Name(mBackList.get(i).getPM_Name());
                    bean.setSR_Number(1);
                    mListEditedMsg.add(bean);
                }
                if (mBackList.size()>0){
                    cbChoiceAll.setChecked(false);
                }
                //刷新适配器
                mDispalyAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onDismiss() {
        YSLUtils.setBackgroundAlpha(1.0f, this);
        mPopWindow.dismiss();
    }

    /**
     * @param view 。
     *             adapter item右边的编辑服务事件，
     */
    @Override
    public void editClick(View view) {
        mLocation = (int) view.getTag();
        YSLUtils.setBackgroundAlpha(0.5f, this);
        showPopWindow();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent1 = new Intent();
            intent1.putExtra("Service", (Serializable) mListEditedMsg);
            setResult(200, intent1);
//            CustomToast.makeText(NewDisplayJiCiServiceACtivity.this,"您取消了计次设定",Toast.LENGTH_SHORT).show();
            NewDisplayJiCiServiceACtivity.this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
