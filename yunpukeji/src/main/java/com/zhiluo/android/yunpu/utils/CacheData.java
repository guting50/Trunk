package com.zhiluo.android.yunpu.utils;

import android.os.Environment;
import android.widget.Toast;

import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.ui.view.CustomToast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 作者：罗咏哲 on 2017/9/21 10:29.
 * 邮箱：137615198@qq.com
 */

public class CacheData {
    /**
     * 把一个对象保存到本地
     *
     * @param fileName
     * @param saveObject
     */
    public static void saveObject(String fileName, Object saveObject) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            String filePath = Environment.getExternalStorageDirectory().getCanonicalPath() + "/VipManage/cache";
            File f = new File(filePath);
            if (!f.exists()) {
                f.mkdirs();
            } /*else if (f.exists()) {
                f.delete();
                f.createNewFile();
            }*/
            fos = new FileOutputStream(f + "/" + fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(saveObject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 把一个对象从本地取出
     *
     * @param fileName
     * @return
     */
    public static final Object restoreObject(String fileName) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Object object = null;
        try {
            String filePath = Environment.getExternalStorageDirectory().getCanonicalPath() + "/VipManage/cache";
            File f = new File(filePath + "/" + fileName);
            if (!f.exists()) {
                Li(fileName + "文件不存在");
                return null;
            } else {
                Li(fileName + "文件存在");
            }
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            object = ois.readObject();
            return object;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return object;
    }


    /**
     * 删除文件夹和文件夹里面的文件
     *
     * @param pPath
     */
    public static void deleteDir(final String pPath) {
        File dir = new File(pPath);
        deleteDirWithFile(dir);
    }

    public static void deleteDir() {
       try {
            String filePath = Environment.getExternalStorageDirectory().getCanonicalPath() + "/VipManage/cache";
            File dir = new File(filePath);
            deleteDirWithFile(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteDirWithFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWithFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }

    public static void deleteFile(String fileName) {
        try {
            String filePath = Environment.getExternalStorageDirectory().getCanonicalPath() + "/VipManage/cache" + "/" + fileName;
            File dir = new File(filePath);
            if (dir.exists()) {
                dir.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
