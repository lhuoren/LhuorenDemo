package com.gac.nioapp.test.router;


import static android.os.Build.MODEL;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.gac.nioapp.test.BaseApplication;
import com.gac.nioapp.test.R;

import java.io.Serializable;

public class ARouterManager {
    private static ARouterManager INSTANCE = null;
    private static Application mContext;

    /**
     * 初始化客户端配置
     */
    public static void init(Application context, boolean isAppDebug) {
        if (isAppDebug) {
            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
            ARouter.openDebug(); // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openLog();// 打印日志
        }
        ARouter.init(context);// 尽可能早，推荐在Application中初始化
        mContext = context;
    }

    /**
     * 初始化信息
     */
    public static synchronized ARouterManager getInstance() {
        // 这个方法比上面有所改进，不用每次都进行生成对象，只是第一次
        // 使用时生成实例，提高了效率！
        if (INSTANCE == null) INSTANCE = new ARouterManager();
        return INSTANCE;
    }

    /**
     * 调用activity
     * 接口
     **/
    public void startARActivitySble(String path, Serializable mSble) {
        if (mSble == null) {
            startARActivity(path);
        } else {
            ARouter.getInstance().build(path)
                    .withSerializable(path, mSble)
                    .navigation();
        }

    }

    /**
     * 调用activity
     * 接口
     **/
    public void startARActivityPble(String path, Parcelable mPble) {
        //webview 的过滤掉低版本
        if (path.equals(RouterConstant.PATH_TO_WEBVIEW_ACTIVITY)) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                Toast.makeText(mContext, R.string.android_os_low_version, Toast.LENGTH_LONG).show();
                return;
            }
            try {
                Intent intent = (Intent) mPble;
                String url = intent.getStringExtra(RouterConstant.URL);
                if (needJump2Agent(url)) {
                    startSystemWeb(url);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (mPble == null) {
            startARActivity(path);
        } else {
            ARouter.getInstance().build(path)
                    .withParcelable(path, mPble)
                    .navigation();
        }
    }

    /**
     * 页面埋点新增
     *
     * @param path
     * @param mPble
     * @param type
     */
    public void startARActivityPble(String path, Intent mPble, int type) {
        //webview 的过滤掉低版本
        if (path.equals(RouterConstant.PATH_TO_WEBVIEW_ACTIVITY)) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                Toast.makeText(mContext, R.string.android_os_low_version, Toast.LENGTH_LONG).show();
                return;
            }
            try {
                String url = mPble.getStringExtra(RouterConstant.URL);
                if (needJump2Agent(url)) {
                    startSystemWeb(url);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (mPble == null) {
            ARouter.getInstance().build(path)
                    .withInt("pageType", type)
                    .navigation();
        } else {
            ARouter.getInstance().build(path)
                    .withParcelable(path, mPble)
                    .withInt("pageType", type)
                    .navigation();
        }
    }

    private boolean needJump2Agent(String url) {

        if (!TextUtils.isEmpty(url) && (url.startsWith("http://iot.yiheszcloud.com") || url.startsWith("http://h5test.yhxyiot.com/hckj"))) {
            return true;
        }
        return false;
    }


    /**
     * 调用activity
     * 接口
     **/
    public void startARActivityPble(String path, Parcelable mPble, NavigationCallback callback) {
        //webview 的过滤掉低版本
        if (path.equals(RouterConstant.PATH_TO_WEBVIEW_ACTIVITY)) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                Toast.makeText(mContext, R.string.android_os_low_version, Toast.LENGTH_LONG).show();
                return;
            }
        }
        if (mPble == null) {
            startARActivity(path, callback);
        } else {
            ARouter.getInstance().build(path)
                    .withParcelable(path, mPble)
                    .navigation(null, callback);
        }
    }

    /**
     * 调用activity
     * 接口
     **/
    public void startARActivityPbleWithObj(String path, Parcelable mPble, Object object) {
        if (mPble == null) {
            startARActivity(path);
        } else {
            ARouter.getInstance().build(path)
                    .withParcelable(path, mPble)
                    .withObject(MODEL, object)
                    .navigation();
        }
    }


    /*
     * activity 普通跳转，带返回码
     * */
    public void startActivityForResult(String path, Activity activity, int requestCode) {
        ARouter.getInstance().build(path)
                .greenChannel()
                .navigation(activity, requestCode);
    }

    /*
     * activity 普通跳转，带返回码
     * */
    public void startActivityForResult(String path, Parcelable value, Activity activity, int requestCode) {
        ARouter.getInstance().build(path)
                .withParcelable(path, value)
                .greenChannel()
                .navigation(activity, requestCode);
    }


    /*
     * activity 普通跳转，带返回码
     * */
    public void startActivityForResult(String path, String key, Parcelable value, Activity activity, int requestCode) {
        ARouter.getInstance().build(path)
                .withParcelable(key, value)
                .greenChannel()
                .navigation(activity, requestCode);
    }

    public void startActivityWithFlag(String path, String key, int inter, int activityFlag) {
        ARouter.getInstance().build(path)
                .withFlags(activityFlag)
                .withInt(key, inter)
                .greenChannel()
                .navigation();
    }

    public void startActivityWithFlag(String path, Parcelable parcelable, int activityFlag) {
        ARouter.getInstance().build(path)
                .withFlags(activityFlag)
                .withParcelable(path, parcelable)
                .greenChannel()
                .navigation();
    }

    public void startActivityWithFlag(String path, String key, int inter, String keyFlag, boolean valueFlag, int activityFlag) {
        ARouter.getInstance().build(path)
                .withFlags(activityFlag)
                .withInt(key, inter)
                .withBoolean(keyFlag, valueFlag)
                .greenChannel()
                .navigation();
    }

    public void startActivityWithFlag(String path,
                                      String key, int inter,
                                      String key2, String value2,
                                      int activityFlag) {
        ARouter.getInstance().build(path)
                .withFlags(activityFlag)
                .withInt(key, inter)
                .withString(key2, value2)
                .greenChannel()
                .navigation();
    }

    public void startActivityWithFlag(String path, int activityFlag) {
        ARouter.getInstance().build(path)
                .withFlags(activityFlag)
                .greenChannel()
                .navigation();
    }

    /**
     * activity 普通跳转 不携带参数
     *
     * @param path
     */
    public void startARActivity(String path) {
        ARouter.getInstance().build(path)
                .navigation();
    }

    /**
     * 页面埋点新增
     *
     * @param path
     * @param type
     */
    public void startARActivity(String path, int type) {
        ARouter.getInstance().build(path)
                .withInt("pageType", type)
                .navigation();
    }

    /**
     * activity 普通跳转 不携带参数
     *
     * @param path
     */
    public void startARActivity(String path, NavigationCallback callback) {
        ARouter.getInstance().build(path)
                .navigation(null, callback);
    }

    /**
     * activity 绿色跳转跳转 不携带参数 不被拦截，适用带网页的activity
     *
     * @param path
     */
    public void startGreenARActivity(String path) {
        ARouter.getInstance().build(path)
                .greenChannel()
                .navigation();
    }

    /**
     * activity 普通跳转 携带单个string参数
     *
     * @param path
     */
    public void startARActivityWithString(String path, String key, String value) {
        ARouter.getInstance().build(path)
                .withString(key, value)
                .navigation();
    }

    /**
     * 页面埋点新增
     *
     * @param path
     * @param key
     * @param value
     * @param type
     */
    public void startARActivityWithString(String path, String key, String value, int type) {
        ARouter.getInstance().build(path)
                .withString(key, value)
                .withInt("pageType", type)
                .navigation();
    }

    /**
     * activity 普通跳转 携带单个int参数
     *
     * @param path
     */
    public void startARActivityWithInt(String path, String key, int value) {
        ARouter.getInstance().build(path)
                .withInt(key, value)
                .navigation();
    }

    public void startARActivity(final String path, int... flags) {
        Postcard postcard = ARouter.getInstance().build(path);
        if (flags != null && flags.length > 0) {
            for (int flag : flags) {
                postcard.withFlags(flag);
            }
        }
        postcard.navigation();
    }

    /**
     * activity 普通跳转 携带单个Boolean参数
     *
     * @param path
     */
    public void startARActivityWithBoolean(String path, String key, Boolean value) {
        ARouter.getInstance().build(path)
                .withBoolean(key, value)
                .navigation();
    }

    /*
     * activity 普通跳转，带返回码
     * */
    public void startActivityForResult(String path, String key, String value, Activity activity, int requestCode) {
        ARouter.getInstance().build(path)
                .withString(key, value)
                .greenChannel()
                .navigation(activity, requestCode);
    }

    /**
     * int参数值 & 返回值
     *
     * @param path
     * @param key
     * @param value
     * @param activity
     * @param requestCode
     */
    public void startActivityForResult(String path, String key, int value, Activity activity, int requestCode) {
        ARouter.getInstance().build(path)
                .withInt(key, value)
                .greenChannel()
                .navigation(activity, requestCode);
    }

    /*
     * activity 普通跳转,如：标记TOKEN失效,TOKEN失效专用
     * */
    public void startActivityForResultFlag(String path, Activity activity, String key, int forResult, int activityFlag) {
        ARouter.getInstance().build(path)
                .withFlags(activityFlag)
                .withInt(key, forResult)
                .greenChannel()
                .navigation(activity, forResult);
    }

    /**
     * 调用activity
     * 接口
     **/
    public void startARActivityBundle(String path, Bundle mBundle) {
        if (mBundle == null) {
            ARouter.getInstance().build(path)
                    .navigation();
        } else {
            ARouter.getInstance().build(path)
                    .withBundle(path, mBundle)
                    .navigation();
        }
    }
//--------------------------------------------------service--------------------------------------------------------

    /**
     * 调用activity
     * 接口
     **/
    public void startARService(String path, Bundle mBundle) {
        ARouter.getInstance().build(path)
                .withBundle(path, mBundle)
                .navigation();
    }

    /**
     * 调用activity
     * 接口
     **/
    public void startARFragment(String path, Bundle mBundle) {
        ARouter.getInstance().build(path)
                .withBundle(path, mBundle)
                .navigation();
    }

    private void startSystemWeb(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        BaseApplication.getInstance().startActivity(intent);
    }

}
