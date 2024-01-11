package com.syy.moduledbbase.db.utils;

import android.os.Bundle;

public class AbAppConfig {
    public static int UI_WIDTH = 720;
    public static int UI_HEIGHT = 1280;
    public static int UI_DENSITY = 2;
    public static String SHARED_PATH = "app_share";
    public static String DOWNLOAD_ROOT_DIR = "enersun";
    public static String DOWNLOAD_IMAGE_DIR = "images";
    public static String DOWNLOAD_FILE_DIR = "files";
    public static String CACHE_DIR = "cache";
    public static String DB_DIR = "db";
    public static long DISK_CACHE_EXPIRES_TIME = 60000000L;
    public static int MAX_CACHE_SIZE_INBYTES = 10485760;
    public static int MAX_DISK_USAGE_INBYTES = 20971520;
    public static String CONNECT_EXCEPTION = "无法连接到网络";
    public static String UNKNOWN_HOST_EXCEPTION = "连接远程地址失败";
    public static String SOCKET_EXCEPTION = "网络连接出错，请重试";
    public static String SOCKET_TIMEOUT_EXCEPTION = "连接超时，请重试";
    public static String NULL_POINTER_EXCEPTION = "抱歉，远程服务出错了";
    public static String NULL_MESSAGE_EXCEPTION = "抱歉，程序出错了";
    public static String CLIENT_PROTOCOL_EXCEPTION = "Http请求参数错误";
    public static String MISSING_PARAMETERS = "参数没有包含足够的值";
    public static String REMOTE_SERVICE_EXCEPTION = "抱歉，远程服务出错了";
    public static String NOT_FOUND_EXCEPTION = "请求的资源无效404";
    public static String FORBIDDEN_EXCEPTION = "没有权限访问资源";
    public static String UNTREATED_EXCEPTION = "未处理的异常";
    public static String ERRORDATA = "数据错误";
    public static boolean NO_PIC = false;
    public static String BASEURL = "";
    public static String IMAGEBASEURL = "";
    public static String ACCESS_TK = "";
    public static String REFRESH_TK = "";
    public static String REFRESH_TOKEN_URL = "";
    public static String CLIENT_ID = "";
    public static String CLIENT_SECRET = "";
    public static String VPN = "";
    public static String APN = "";
    public static String WAPI = "";
    public static String ALGORITHMSTR = "";
    public static String AAA = "";
    public static String TENANT_ID = "";
    public static Class<?> LOGIN_ACTIVITY;
    public static Bundle LOGIN_BUNDLE;
    public static String ACCESS_ERROR_INFO;
    public static boolean IS_NEW_ENERSUN_FRAMEWORK;
    public static final int REQUEST_LOGIN = 8888;
    public static boolean IS_FINISH_ALL_ACTIVITY;

    public AbAppConfig() {
    }
}
