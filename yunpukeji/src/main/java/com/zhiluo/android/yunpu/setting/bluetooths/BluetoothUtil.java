package com.zhiluo.android.yunpu.setting.bluetooths;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.Toast;

import com.zhiluo.android.yunpu.ui.view.CustomToast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class BluetoothUtil
{
    private static BluetoothSocket bluetoothSocket = null;
    private static OutputStream outputStream = null;
    private Context context;
    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter
            .getDefaultAdapter();
    private BluetoothDevice device = null;
    private boolean isConnection = false;
    private static final UUID PRINTER_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public BluetoothUtil(Context context)
    {
        this.context = context;
    }
    //	public static String getAdress() {
    //		return adress;
    //	}
    //	private static final String Innerprinter_Address = "00:11:22:33:44:55";
    //	public static String adress=null;
    public static BluetoothAdapter getBTAdapter()
    {
        return BluetoothAdapter.getDefaultAdapter();
    }
    public static BluetoothDevice getDevice(BluetoothAdapter bluetoothAdapter)
    {
        BluetoothDevice innerprinter_device = null;
        innerprinter_device = bluetoothAdapter.getRemoteDevice(BluetoothService.getMadress());
        //		Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
        //		for (BluetoothDevice device : devices) {
        //			Log.d("aaaaa",BluetoothService.getMadress()+"****util");
        //			if (device.getAddress().equals(BluetoothService.getMadress())) {
        //				innerprinter_device = device;
        //				break;
        //			}
        //		}
        return innerprinter_device;
    }
    public static BluetoothSocket getSocket(BluetoothDevice device) throws IOException
    {
        BluetoothSocket socket = device.createRfcommSocketToServiceRecord(PRINTER_UUID);
        socket.connect();
        return socket;
    }
    public static void sendData(byte[] bytes, BluetoothSocket socket) throws IOException
    {
        OutputStream out = socket.getOutputStream();
        out.write(bytes, 0, bytes.length);
        out.flush();
        //out.close();
    }
    /**
     * 断开蓝牙设备连接
     */
    public static void disconnect()
    {
        try
        {
            bluetoothSocket.close();
            outputStream.close();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 判断蓝牙是否打开
     *
     * @return boolean
     */
    public boolean isOpen()
    {
        //如果某设备没有蓝牙模块的话app会崩溃，所以加了个try。（主要会发生在模拟器上运行的时候）
        try
        {
            return this.bluetoothAdapter.isEnabled();
        }
        catch (Exception e)
        {
            return false;
        }
    }
    /**
     * 连接蓝牙设备
     */
    public boolean connect(String adress)
    {
        device = bluetoothAdapter.getRemoteDevice(adress);
        if (!this.isConnection)
        {
            try
            {
                bluetoothSocket = device
                        .createRfcommSocketToServiceRecord(PRINTER_UUID);
                bluetoothSocket.connect();
                outputStream = bluetoothSocket.getOutputStream();
                this.isConnection = true;
                if (this.bluetoothAdapter.isDiscovering())
                {
                    //    System.out.println("关闭适配器！");
                    this.bluetoothAdapter.isDiscovering();
                }
            }
            catch (Exception e)
            {
                CustomToast.makeText(this.context, "连接失败！", Toast.LENGTH_SHORT).show();
                return false;
            }
            //            CustomToast.makeText(this.context, this.device.getName() + "连接成功！",
            //                    Toast.LENGTH_SHORT).show();
            return true;
        }
        else
        {
            return true;
        }
    }

    private Handler handler = new Handler();

    /**
     * 发送数据
     */
    public void send(final byte[] data)
    {
        if (this.isConnection)
        {
            SharedPreferences pref = this.context.getSharedPreferences("recepits", Context.MODE_PRIVATE);
            final int recepits_num = pref.getInt("recepits_num", 2);
            handler.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    for (int i = 1; i <= recepits_num; i++)
                    {
                        try
                        {
                            bluetoothSocket = device.createRfcommSocketToServiceRecord(PRINTER_UUID);
                            bluetoothSocket.connect();
                            outputStream = bluetoothSocket.getOutputStream();
                            outputStream.write(data, 0, data.length);
                            outputStream.flush();
                            Thread.sleep(3000);
                            disconnect();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            CustomToast.makeText(context, "发送失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            },2000);
        }
        else
        {
            CustomToast.makeText(context, "设备未连接，请重新连接！", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
