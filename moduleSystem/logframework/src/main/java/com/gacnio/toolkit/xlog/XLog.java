package com.gacnio.toolkit.xlog;

import android.app.Application;
import android.content.Context;

import com.tencent.mars.xlog.Log;
import com.tencent.mars.xlog.Xlog;

import java.io.File;

/**
 * @package： com.gacnio.toolkit.xlog
 * @describe：
 * @author： liming
 * @time： 2020/6/12 2:35 PM
 * @e-mail： liming@gac-nio.com
 */
public class XLog {
    private static final String LOG_CACHE_PATH = "xlog_cache";
    private static final String LOG_PATH = "xlog";

    public static boolean appenderClose = false;

    public static boolean isInit = false;

    /**
     * 初始化
     *
     * @param application
     * @param debug
     */
    public static void init(Application application, boolean debug) {
        System.loadLibrary("c++_shared");
        System.loadLibrary("marsxlog");
        final String logPath = getDefaultLogPath(application).getAbsolutePath();
        // this is necessary, or may crash for SIGBUS
        final String cachePath = getDefaultLogCachePath(application).getAbsolutePath();
        //init xlog
        if (debug) {
            Xlog.appenderOpen(Xlog.LEVEL_DEBUG, Xlog.AppednerModeAsync, cachePath, logPath, "hycanLog", 0, "");
            Xlog.setConsoleLogOpen(true);
        } else {
            Xlog.appenderOpen(Xlog.LEVEL_INFO, Xlog.AppednerModeAsync, cachePath, logPath, "hycanLog", 0, "");
            Xlog.setConsoleLogOpen(false);
        }
        Log.setLogImp(new Xlog());
        isInit = true;
        appenderClose = false;
    }

    public static void init(Application application, boolean debug, String processName) {
        System.loadLibrary("c++_shared");
        System.loadLibrary("marsxlog");
        final String logPath = getDefaultLogPath(application).getAbsolutePath();
        // this is necessary, or may crash for SIGBUS
        final String cachePath = getDefaultLogCachePath(application).getAbsolutePath();
        //init xlog
        if (debug) {
            Xlog.appenderOpen(Xlog.LEVEL_DEBUG, Xlog.AppednerModeAsync, cachePath, logPath, "hycan_" + processName + "_Log", 0, "");
            Xlog.setConsoleLogOpen(true);
        } else {
            Xlog.appenderOpen(Xlog.LEVEL_INFO, Xlog.AppednerModeAsync, cachePath, logPath, "hycan_" + processName + "_Log", 0, "");
            Xlog.setConsoleLogOpen(false);
        }
        Log.setLogImp(new Xlog());
        isInit = true;
        appenderClose = false;
    }

    public static void exit() {
        Log.appenderClose();
        appenderClose = true;
        isInit = false;
    }

    private static File getDefaultLogCachePath(Context context) {
        File file = context.getExternalFilesDir(LOG_CACHE_PATH);
        if (file != null && !file.exists()) {
            file.mkdir();
        }
        return file;
    }

    private static File getDefaultLogPath(Context context) {
        File file = context.getExternalFilesDir(LOG_PATH);
        if (file != null && !file.exists()) {
            file.mkdir();
        }
        return file;
    }
}
