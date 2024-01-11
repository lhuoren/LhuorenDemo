package com.gac.nioapp.test.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.gac.nioapp.test.R;
import com.gac.nioapp.test.screen.ScreenUtils;
import com.syy.modulebase.http.constants.HttpUrlConstant;
import com.syy.modulebase.manager.UserInfoManager;
import com.syy.modulebase.utils.AppConstant;
import com.syy.modulebase.utils.EventConstant;
import com.tencent.mars.xlog.Log;

import java.util.ArrayList;
import java.util.List;

import cn.gacnio.it.database.SPUtils;

public class EnvironmentManager {
    private static final String TAG = "EnvironmentManager";

    private static final String[] SHORT_NAME = {
            "DEV",
            "TEST",
            "PRE",
            "PRD"
    };
    private static final String[] ENVIRONMENT_NAME = {
            "开发环境(dev)",
            "测试环境(test)",
            "预发布环境(pre)",
            "正式环境(prd)"
    };
    public static final String[] ENVIRONMENT_VALUE_HTTP = {
            "https://wxdevapplet.gac-nio.com",
            "https://wxtestapplet.gac-nio.com",
            "https://wxpreapplet.gac-nio.com",
            "https://wxprdapplet.hycan.com.cn"
    };
    public static final String[] ENVIRONMENT_VALUE_H5_HTTP = {
            "https://static-test.gac-nio.com/",
            "https://static-test.gac-nio.com/",
            "https://static-pre.gac-nio.com/",
            "https://static.hycan.com.cn/"
    };

    public static final String[] ENVIRONMENT_YG_HTTP = {
            "http://externalinter-dev.hycan.com.cn",
            "http://externalinter-test.hycan.com.cn",
            "http://externalinter-pre.hycan.com.cn",
            "https://externalinter.hycan.com.cn/"
    };

    public static final String[] ENVIRONMENT_AUTH_HEADER = {
            "Basic YXBwLWNsaWVudDphcHBjbGllbnRzZWNyZXQhMTIz",
            "Basic YXBwLWNsaWVudDphcHBjbGllbnRzZWNyZXQhMTIz",
            "Basic YXBwLWNsaWVudDphcHBjbGllbnRzZWNyZXQhMTIz",
            "Basic YXBwLWNsaWVudDphcHBjbGllbnRzZWNyZXQxMjM="
    };


    private AppCompatActivity mActivity;

    public void initSpinner(AppCompatActivity activity, Spinner spinner) {
        mActivity = activity;
        ArrayList<String> desList = new ArrayList<>();
        for (int i = 0; i < ENVIRONMENT_NAME.length; i++) {
            desList.add(ENVIRONMENT_NAME[i]);
        }
        ArrayAdapter<String> desAdapter = new ArrayAdapter<>(mActivity, R.layout.spinner_item, R.id.text, desList);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            spinner.setDropDownVerticalOffset(ScreenUtils.dp2px(activity, 50));
        }

        spinner.setAdapter(desAdapter);
        spinner.setSelection(getCurrentPosition(), false);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "----position=" + position);
                clickPosition(position, spinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i(TAG, "---onNothingSelected-");
            }
        });

    }

    public static int getCurrentPosition() {
        String environment = SPUtils.getString(HttpUrlConstant.KEY_CURRENT_ENVIRONMENT, HttpUrlConstant.getAPPURL());
        if (!TextUtils.isEmpty(environment)) {
            int i = 0;
            for (; i < ENVIRONMENT_VALUE_HTTP.length; i++) {
                if (environment.equals(ENVIRONMENT_VALUE_HTTP[i])) {
                    return i;
                }
            }
            return i - 1;
        }
        return 0;
    }

    public static String getShortName() {
        return SHORT_NAME[getCurrentPosition()];
    }

    private void clickPosition(int position, Spinner spinner) {
        if (!HttpUrlConstant.getAPPURL().equals(ENVIRONMENT_VALUE_HTTP[position])) {
            DialogUtil.show2LineAlertDialog(mActivity, "切换为" + ENVIRONMENT_NAME[position] + "?", R.drawable.act_windows_red_warn, "", "取消", "重启生效", (bundle, p) -> {
                if (p == EventConstant.ALERT_BUTTON_RIGHT) {
                    UserInfoManager.getInstance().logout();
                    SPUtils.put(HttpUrlConstant.KEY_CURRENT_ENVIRONMENT, ENVIRONMENT_VALUE_HTTP[position]);
                    SPUtils.put(HttpUrlConstant.KEY_CURRENT_H5_ENVIRONMENT, ENVIRONMENT_VALUE_H5_HTTP[position]);
                    SPUtils.put(HttpUrlConstant.KEY_CURRENT_YG_ENVIRONMENT, ENVIRONMENT_YG_HTTP[position]);
                    SPUtils.put(HttpUrlConstant.KEY_CURRENT_AUTH_BASE, ENVIRONMENT_AUTH_HEADER[position]);
                    if (position >= 2) {
                        SPUtils.put(AppConstant.DEBUG_MEGASERVERTYPE, 1 + position);
                    } else {
                        SPUtils.put(AppConstant.DEBUG_MEGASERVERTYPE, position);
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(this::killAppProcess, 1000L);
                } else {
                    spinner.setSelection(getCurrentPosition());
                }
            });
        }
    }


    public void killAppProcess() {
        ActivityManager mActivityManager = (ActivityManager) mActivity.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> mList = mActivityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : mList) {
            if (runningAppProcessInfo.pid != android.os.Process.myPid()) {
                android.os.Process.killProcess(runningAppProcessInfo.pid);
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
