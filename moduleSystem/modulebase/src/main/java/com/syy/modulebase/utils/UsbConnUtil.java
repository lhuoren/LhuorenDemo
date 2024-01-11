package com.syy.modulebase.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class UsbConnUtil {

    private static final String TAG = "UsbDevicesInit";

    private UsbManager manager; // USB管理器

    private UsbDevice mUsbDevice = null; // 找到的USB设备

    private Context mContext;

    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";

    private boolean usbConn = false;

    public UsbConnUtil(Context context){
        mContext = context;
        UsbDevicesInit();
    }
    /**
     * 1. 初始化USB
     */
    private void UsbDevicesInit() {

        // 获取USB设备
        manager = (UsbManager) mContext.getSystemService(Context.USB_SERVICE);
        if (manager == null) {
            return;
        } else {
            Log.i(TAG, "usb设备1：" + manager.toString());
        }

        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
        Log.i(TAG, "usb设备：" + deviceList.size());
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        ArrayList<String> USBDeviceList = new ArrayList<String>(); // 存放USB设备的数量
        while (deviceIterator.hasNext()) {
            UsbDevice device = deviceIterator.next();

            USBDeviceList.add(String.valueOf(device.getVendorId()));
            USBDeviceList.add(String.valueOf(device.getProductId()));
            // 在这里添加处理设备的代码
            if (device.getVendorId() == 0x0000 || device.getVendorId() == 0x04b4 || device.getVendorId() == 0x0951 ||
                    device.getVendorId() == 0x483 || device.getVendorId() == 0xAAAA) {
                mUsbDevice = device;
                Log.i(TAG, "找到设备");
            }
        }
        findEndpotints();
    }

    int interfaceCount = 0;
    UsbInterface[] mInterfaceArray = new UsbInterface[5];
    private UsbEndpoint epIn_Data;
    /**
     * 2. 查询服务通道
     */
    private void findEndpotints() {
        Log.e(TAG, "mUsbDevice:"+mUsbDevice);
        if (mUsbDevice == null) {
            Log.i(TAG, "没有找到设备");
            return;
        }

        interfaceCount = mUsbDevice.getInterfaceCount();
        Log.e(TAG, "interfaceCount:"+interfaceCount);
        if (interfaceCount < 1)
            return;

        for (int i = 0; i < interfaceCount; i++) {
            // 获取设备接口，一般都是一个接口，你可以打印getInterfaceCount()方法查看接
            // 口的个数，在这个接口上有两个端点，OUT 和 IN
            mInterfaceArray[i] = mUsbDevice.getInterface(i);
        }

        Log.e(TAG, "mInterfaceArray:"+mInterfaceArray[0]);
        if (mInterfaceArray[0] != null) {
            UsbDeviceConnection connection = null;
            // 判断是否有权限
            PendingIntent permissionIntent = PendingIntent.getBroadcast(mContext, 0, new Intent(ACTION_USB_PERMISSION), 0);
            manager.requestPermission(mUsbDevice, permissionIntent);

            if (manager.hasPermission(mUsbDevice)) {
                // 打开设备，获取 UsbDeviceConnection 对象，连接设备，用于后面的通讯
                connection = manager.openDevice(mUsbDevice);
                Log.e(TAG, "connection:"+connection);
                if (connection == null) {
                    return;
                }

                setUsbConn(true);

            } else {
                Log.e(TAG, "没有权限");
//                AppConfig.mHandler.post(() -> Toast.makeText(mContext,"没有权限,确认给予usb权限",Toast.LENGTH_SHORT).show());
                // 重新验证权限
                PendingIntent usbPermissionIntent = PendingIntent.getBroadcast(mContext, 0, new Intent(ACTION_USB_PERMISSION), 0);
                manager.requestPermission(mUsbDevice, usbPermissionIntent);
            }
        } else {
            Log.i(TAG, "没有找到接口");
        }
    }

    public boolean isUsbConn() {
        return usbConn;
    }

    public void setUsbConn(boolean usbConn) {
        this.usbConn = usbConn;
    }
}
