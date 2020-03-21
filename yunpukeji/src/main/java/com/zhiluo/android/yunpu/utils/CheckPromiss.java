package com.zhiluo.android.yunpu.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v4.content.ContextCompat;

import com.zhiluo.android.yunpu.member.manager.activity.AddMemberActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by YSL on 2018-09-04.
 */

public class CheckPromiss {

    /**
     * 检查拍照权限
     */
    public static boolean checkPermission(Context context) {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 进入这儿表示没有权限

//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
//                // 提示已经禁止
//                CustomToast.makeText(AddMemberActivity.this, "未获取到相机权限，请确认后再尝试！", Toast.LENGTH_SHORT).show();
//            } else {
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
//            }
            return false;
        }
        return true;
    }

    public static boolean hasCamera() {
        return hasBackFacingCamera() || hasFrontFacingCamera();
    }

    /**检查设备是否有后置摄像头
     * @return  true,有后置摄像头；false,后置摄像头
     */
    public static boolean hasBackFacingCamera() {
        final int CAMERA_FACING_BACK = 0;
        return checkCameraFacing(CAMERA_FACING_BACK);
    }

    /**检查设备是否有前置摄像头
     * @return  true,有前置摄像头；false,前置摄像头
     */
    public static boolean hasFrontFacingCamera() {
        final int CAMERA_FACING_BACK = 1;
        return checkCameraFacing(CAMERA_FACING_BACK);
    }

    private static boolean checkCameraFacing(final int facing) {
//        if (getSdkVersion() < Build.VERSION_CODES.GINGERBREAD) {
//            return false;
//        }
        final int cameraCount = Camera.getNumberOfCameras();
        Camera.CameraInfo info = new Camera.CameraInfo();
        for (int i = 0; i < cameraCount; i++) {
            Camera.getCameraInfo(i, info);
            if (facing == info.facing) {
                return true;
            }
        }
        return false;
    }

}
