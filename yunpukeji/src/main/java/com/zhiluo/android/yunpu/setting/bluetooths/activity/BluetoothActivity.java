package com.zhiluo.android.yunpu.setting.bluetooths.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.HomeActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.setting.bluetooths.BluetoothAction;
import com.zhiluo.android.yunpu.setting.bluetooths.adapter.ConecteddeviceAdapter;

import java.util.ArrayList;
import java.util.Set;


public class BluetoothActivity extends BaseActivity
{
    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private String mMaccode;
    private ArrayList<String> conecteddevice = new ArrayList<>();
    private Context context = null;
    private ConecteddeviceAdapter conecteddeviceAdapter;
    private ListView bindlistview;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.context = this;
        setTitle("蓝牙打印");
        setContentView(R.layout.bluetooth_layout);
        bindlistview = (ListView) findViewById(R.id.listDevices);
        showbounddevice();
        this.initListener();
    }
    @Override
    protected void onResume()
    {
        super.onResume();
    }
    private void showbounddevice()
    {
        // 给一组目前配对设备
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        // 如果有配对设备,
        int mCount = pairedDevices.size();
        if (mCount > 0)
        {
            for (BluetoothDevice device : pairedDevices)
            {
                conecteddevice.add(device.getName() + "\n" + device.getAddress());
            }
            conecteddeviceAdapter = new ConecteddeviceAdapter(context, conecteddevice);
            this.bindlistview.setAdapter(conecteddeviceAdapter);
            conecteddeviceAdapter.notifyDataSetChanged();
        }
        bindlistview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String info = conecteddevice.get(position);
                String address = info.substring(info.length() - 17);
                sp = context.getSharedPreferences("setting", MODE_PRIVATE);
                editor = sp.edit();
                editor.putString("adress", address);
                editor.commit();
                CustomToast.makeText(context, "蓝牙设备保存成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initListener()
    {
        ListView unbondDevices = (ListView) this
                .findViewById(R.id.unbondDevices);
        ListView bondDevices = (ListView) this.findViewById(R.id.bondDevices);
        Button switchBT = (Button) this.findViewById(R.id.openBluetooth_tb);
        Button searchDevices = (Button) this.findViewById(R.id.searchDevices);
        BluetoothAction bluetoothAction = new BluetoothAction(this.context,
                unbondDevices, bondDevices, switchBT, searchDevices,
                BluetoothActivity.this, bindlistview);
        Button returnButton = (Button) this
                .findViewById(R.id.return_Bluetooth_btn);
        bluetoothAction.setSearchDevices(searchDevices);
        bluetoothAction.initView();
        switchBT.setOnClickListener(bluetoothAction);
        searchDevices.setOnClickListener(bluetoothAction);
        returnButton.setOnClickListener(bluetoothAction);
    }
    //    //屏蔽返回键的代码:
    //    public boolean onKeyDown(int keyCode, KeyEvent event) {
    //        switch (keyCode) {
    //            case KeyEvent.KEYCODE_BACK:
    //                return true;
    //        }
    //        return super.onKeyDown(keyCode, event);
    //    }
}
