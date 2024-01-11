package com.syy.modulebase.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicReference;

public class RootManagerUtil {

    public static boolean RootCommand(Context mContext) {
        String apkRootCommand = "chmod 777 " + mContext.getPackageCodePath();
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(apkRootCommand + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            LogUtils.d("*** DEBUG ***", "ROOT REE" + e.getMessage());
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LogUtils.d("*** DEBUG ***", "Root SUC ");
        return true;
    }

    public static boolean isRoot() {
        boolean root = false;
        try {
            root = (new File("/system/bin/su").exists()) || (new File("/system/xbin/su").exists());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return root;
    }

    public static boolean checkSuFile() {
        Process process = null;
        try {
            //   /system/xbin/which 或者  /system/bin/which
            process = Runtime.getRuntime().exec(new String[]{"which", "su"});
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (in.readLine() != null) return true;
            return false;
        } catch (Throwable t) {
            return false;
        } finally {
            if (process != null) process.destroy();
        }
    }

//    public static boolean checkRootFile() {
//        AtomicReference<File> file = null;
//        String[] paths = {"/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
//                "/system/bin/failsafe/su", "/data/local/su"};
//        for (String path : paths) {
//            file.set(new File(path));
//            if (file.get().exists()) return true;
//        }
//        return false;
//    }

    /**
     * 是否存在busybox命令，并且有执行权限
     *
     * @return 存在busybox命令，并且有执行权限返回true
     */
    public static boolean isSuEnable() {
        File file = null;
        String[] paths = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/", "/su/bin/"};
        try {
            for (String path : paths) {
                file = new File(path + "busybox");
                if (file.exists() && file.canExecute()) {
                    return true;
                }
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
        return false;
    }

    public static boolean checkSuperuserApk() {
        try {
            File file = new File("/system/app/Superuser.apk");
            if (file.exists()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean checkDeviceDebuggable() {
        String buildTags = android.os.Build.TAGS;
        if (buildTags != null && buildTags.contains("test-keys")) {
            return true;
        }
        return false;
    }

}
