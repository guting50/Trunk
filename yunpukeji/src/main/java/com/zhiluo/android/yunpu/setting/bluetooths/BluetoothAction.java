package com.zhiluo.android.yunpu.setting.bluetooths;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.R;


public class BluetoothAction implements OnClickListener
{
    private Button switchBT = null;
    private Button searchDevices = null;
    private Activity activity = null;
    private ListView unbondDevices = null;
    private ListView bondDevices = null;
    private ListView bindlistview = null;
    private Context context = null;
    private BluetoothService bluetoothService = null;
    public BluetoothAction(Context context, ListView unbondDevices,
                           ListView bondDevices, Button switchBT, Button searchDevices,
                           Activity activity, ListView bindlistview)
    {
        super();
        this.context = context;
        this.unbondDevices = unbondDevices;
        this.bondDevices = bondDevices;
        this.switchBT = switchBT;
        this.searchDevices = searchDevices;
        this.bindlistview = bindlistview;
        this.activity = activity;
        this.bluetoothService = new BluetoothService(this.context,
                this.unbondDevices, this.bondDevices, this.switchBT,
                this.searchDevices);
    }
    public void setSwitchBT(Button switchBT)
    {
        this.switchBT = switchBT;
    }
    public void setSearchDevices(Button searchDevices)
    {
        this.searchDevices = searchDevices;
    }
    public void setUnbondDevices(ListView unbondDevices)
    {
        this.unbondDevices = unbondDevices;
    }
    /**
     * 初始化界面
     */
    public void initView()
    {
        if (this.bluetoothService.isOpen())
        {
            System.out.println("蓝牙有开!");
            switchBT.setText("关闭蓝牙");
        }
        if (!this.bluetoothService.isOpen())
        {
            System.out.println("蓝牙没开!");
            this.searchDevices.setEnabled(false);
        }
    }
    //搜索蓝牙设备
    private void searchDevices()
    {
        if (Build.VERSION.SDK_INT >= 23)
        {
            if (isGpsEnable(context))
            {
                bluetoothService.searchDevices();
            }
        }
        bluetoothService.searchDevices();
    }
    /**
     * 判断GPS是否可用
     * 目的为android6.0以上的机型搜索外部蓝牙设备时需要打开GPS的权限
     *
     * @param context
     * @return
     */
    public static final boolean isGpsEnable(final Context context)
    {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network)
        {
            return true;
        }
        CustomToast.makeText(context, "请在-‘设置’-中打开-‘定位’！且允许本软件使用‘定位’权限！", Toast.LENGTH_LONG).show();
        //跳转到gps设置页
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        }, 2000);
        return false;
    }
    /**
     * 各种按钮的监听
     */
    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.searchDevices)
        {
            bondDevices.setVisibility(View.VISIBLE);
            bindlistview.setVisibility(View.GONE);
            this.searchDevices();
        }
        else if (v.getId() == R.id.return_Bluetooth_btn)
        {
            activity.finish();
        }
        else if (v.getId() == R.id.openBluetooth_tb)
        {
            if (!this.bluetoothService.isOpen())
            {
                // 蓝牙关闭的情况
                System.out.println("蓝牙关闭的情况");
                this.bluetoothService.openBluetooth(activity);
            }
            else
            {
                // 蓝牙打开的情况
                System.out.println("蓝牙打开的情况");
                this.bluetoothService.closeBluetooth();
            }
        }
    }
}
