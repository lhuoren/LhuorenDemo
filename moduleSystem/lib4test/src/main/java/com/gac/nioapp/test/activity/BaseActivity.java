package com.gac.nioapp.test.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gac.nioapp.test.manager.AppManager;
import com.gac.nioapp.test.model.RefreshPageEvent;
import com.gac.nioapp.test.rx.RxBus;
import com.gac.nioapp.test.theme.DayNightManager;
import com.gac.nioapp.test.utils.AntiClickUtils;
import com.gac.nioapp.test.utils.DensityUtil;
import com.gac.nioapp.test.utils.StatusBarUtil;
import com.gac.nioapp.test.view.GlobalNativeToast;
import com.growingio.android.sdk.collection.GrowingIO;
import com.syy.modulebase.utils.AppInfoManager;
import com.tencent.mars.xlog.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sai on 2018/3/15.
 * 基类Activity
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected BaseActivity currActivity;
    protected String TAG = "xyz" + this.getClass().getSimpleName();
    protected int LOCATION_PERMISSION_REQUEST_CODE = 888;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 获取系统window支持的模式
            Display.Mode[] modes = getWindowManager().getDefaultDisplay().getSupportedModes();
            // 对获取的模式，基于刷新率的大小进行排序，从小到大排序
            for (int i = 0; i < modes.length; i++) {

                if (modes[i].getRefreshRate() < 70 && modes[i].getRefreshRate() > 30) {
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.preferredDisplayModeId = modes[i].getModeId();
                    getWindow().setAttributes(lp);
                    Log.i("SplashActivity", "RefreshRate:" + modes[i].getRefreshRate());
                    break;
                }
            }
        }

        super.onCreate(savedInstanceState);

        Log.e(TAG, "当前进入的页面: " + this.getClass().getSimpleName());
        setUpViewAndData();

        RxBus.getInstance().toObservable(RefreshPageEvent.class).subscribe(event -> {
            //从接口拦截登录完成，刷新接口所在界面
            if (BaseActivity.this.getClass().getName().equals(event.getPageName())) {
                recreate();
            }
        });
        //页面埋点
        String pageName = getIntent().getStringExtra("pageName");
        if (pageName != null && !pageName.isEmpty()) {
            GrowingIO gio = GrowingIO.getInstance();
            gio.setPageVariable(this, "pageNameForGrowingIO", pageName);
        }
    }


    protected boolean isVideoActivity() {
        return false;
    }


    private void setUpViewAndData() {
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果您的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果您是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则您可以选择性注释掉这个if内容
        boolean isDark = !DayNightManager.isNightMode(this); //状态栏普通模式为黑色，暗夜模式为白色
        setStatusBar(isDark);
        currActivity = this;
        DensityUtil.setDefault(this);
        Log.e(TAG, "正常 setUpViewAndData");
        setContentView(getLayoutResID());
        AppManager.getAppManager().addActivity(this);
        initView();
        initData();
        initListener();
    }

    /**
     * 设置状态栏颜色
     *
     * @param isDark true 为黑色字体，白色背景
     *               false 为base字体，黑色背景
     */
    public void setStatusBar(boolean isDark) {
        if (!StatusBarUtil.setStatusBarDarkTheme(this, isDark)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (!isVideoActivity()) {
            int mode = DayNightManager.getDayNightMode();
            int mSysThemeConfig = newConfig.uiMode & Configuration.UI_MODE_NIGHT_MASK;
            switch (mSysThemeConfig) {
                case Configuration.UI_MODE_NIGHT_NO:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    AppInfoManager.getInstance().setDarkMode(false);
                    break;
                case Configuration.UI_MODE_NIGHT_YES:
                    //深色模式
                    AppInfoManager.getInstance().setDarkMode(true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    break;
            }
            recreate();
        }
    }


    /**
     * 布局resId
     *
     * @return
     */
    protected abstract int getLayoutResID();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 舒适化数据，在initView之后
     */
    protected abstract void initData();

    protected void initListener() {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
//        currActivity = null;
    }


    public void showToast(String msg) {
        GlobalNativeToast.show(getApplication(), msg, Toast.LENGTH_SHORT);
    }

    public void showToast(int msgId) {
        GlobalNativeToast.show(getApplication(), getApplicationContext().getString(msgId), Toast.LENGTH_SHORT);
    }

    public void showToastLong(int msgId) {
        GlobalNativeToast.show(getApplication(), getApplicationContext().getString(msgId), Toast.LENGTH_LONG);

    }

    public void showToastLong(String msg) {
        GlobalNativeToast.show(getApplication(), msg, Toast.LENGTH_LONG);
    }

    public void onTopBarBack(View view) {
        finish();
    }

    public void onTopBarRightClick(View v) {

    }

    /**
     * Find出来的View，自带防抖功能,使用此方法，在onClick禁止使用AntiClickUtils.isInvalidClick
     * 推荐kotlin的setOnRxClick
     */
    @Deprecated
    public <T extends View> T findClickView(int id) {

        T view = findViewById(id);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!AntiClickUtils.isInvalidClick(view)) {
                    BaseActivity.this.onClick(view);
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        //子类重写，替换 EventListener() ,反射耗费性能
    }

    private Map<Integer, View> viewMaps = new HashMap<>();

    //google 早已废弃了 kotlin-android-extensions,建议后续使用ViewBinding方式,
    protected <T extends View> T fv(int id) {
        if (viewMaps.get(id) == null) {
            viewMaps.put(id, findViewById(id));
        }
        return (T) viewMaps.get(id);
    }

    protected TextView fvT(int id) {
        if (viewMaps.get(id) == null) {
            viewMaps.put(id, findViewById(id));
        }
        return (TextView) viewMaps.get(id);
    }

    protected Button fvB(int id) {
        if (viewMaps.get(id) == null) {
            viewMaps.put(id, findViewById(id));
        }
        return (Button) viewMaps.get(id);
    }

    protected ImageView fvI(int id) {
        if (viewMaps.get(id) == null) {
            viewMaps.put(id, findViewById(id));
        }
        return (ImageView) viewMaps.get(id);
    }

    protected EditText fvE(int id) {
        if (viewMaps.get(id) == null) {
            viewMaps.put(id, findViewById(id));
        }
        return (EditText) viewMaps.get(id);
    }

    protected RecyclerView fvRv(int id) {
        if (viewMaps.get(id) == null) {
            viewMaps.put(id, findViewById(id));
        }
        return (RecyclerView) viewMaps.get(id);
    }

    protected View fvV(int id) {
        if (viewMaps.get(id) == null) {
            viewMaps.put(id, findViewById(id));
        }
        return viewMaps.get(id);
    }

    protected void checkAndRequestLocationPermissions() {
        // 检查是否已经拥有位置权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // 如果权限未授予，则请求权限
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // 已经拥有权限，执行需要位置权限的操作
             performLocationRelatedTask();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 用户授予了位置权限，执行需要位置权限的操作
                 performLocationRelatedTask();
            } else {
                // 用户拒绝了位置权限请求，可以根据需要执行相应的操作
                 handlePermissionDenied();
            }
        }
    }

    protected void performLocationRelatedTask() {

    }

    protected void handlePermissionDenied() {

    }

}
