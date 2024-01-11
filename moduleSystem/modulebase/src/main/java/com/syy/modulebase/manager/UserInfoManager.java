package com.syy.modulebase.manager;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.WorkerThread;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import com.gac.common.utils.CommonSimpleLog;
import com.google.gson.Gson;
import com.syy.modulebase.http.HttpServiceGenerator;
import com.syy.modulebase.http.bean.UserInfoBean;
import com.syy.modulebase.http.bean.UserTokenBean;
import com.syy.modulebase.http.constants.HttpKeyConstant;
import com.syy.modulebase.http.messge.LiveDataBus;
import com.syy.modulebase.http.rx.RxBus;
import com.syy.modulebase.http.service.TokenService;
import com.syy.modulebase.manager.userinfo.LoginStatus;
import com.syy.modulebase.manager.userinfo.LogoutStatus;
import com.syy.modulebase.manager.userinfo.UserStatus;
import com.syy.modulebase.utils.AppInfoManager;
import com.syy.modulebase.utils.EventConstant;
import com.syy.modulebase.utils.SharePreferenceUtil;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @Description：描述信息
 * @Author：Sai
 * @Date：2019/3/15 11:16
 */
public class UserInfoManager {

    private static final String TAG = "UserInfoManager";

    private static final String OAUTH_TOKEN = "oauthToken";
    private static final String ACCESS_TOKEN = "accessToken";
    private static final String VERIFIED = "isVerified";
    private UserTokenBean token = new UserTokenBean();
    private UserInfoBean userInfo;
    private boolean isTokenLoading = false;//用于自动登录情况下，首页请求等待，用户登录成功或失败后才加载
    private boolean isFinancialLogin = false;//用于记录用户是否是因为进入金融方案页面才需要的登录，默认false如果为true则爱车页自动进入金融页
    private boolean isRestartLogin = false;//用于记录用户是否切换账号

    private final String USER_TAG_LOGIN = "USER_TAG_LOGIN";


    //用户登录状态控制
    UserStatus userStatus = new LogoutStatus();
    private static boolean isIMLogin; //登记IM是否已经登录

    private UserInfoManager() {
    }

    private static class UserInfoManagerInstance {
        private static final UserInfoManager INSTANCE = new UserInfoManager();
    }

    public static UserInfoManager getInstance() {
        return UserInfoManager.UserInfoManagerInstance.INSTANCE;
    }

    /**
     * 登录
     */
    public void login() {
        userStatus = new LoginStatus();
        updateLiveDataStatus(true);

    }

    private void updateLiveDataStatus(boolean loginStatus) {
        if (LiveDataBus.instance().with(USER_TAG_LOGIN, Boolean.class).getValue() == null || loginStatus != LiveDataBus.instance().with(USER_TAG_LOGIN, Boolean.class).getValue()) {
            LiveDataBus.instance().with(USER_TAG_LOGIN, Boolean.class).postValue(loginStatus);
            LiveDataBus.instance().withUnPeek(USER_TAG_LOGIN, Boolean.class).postValue(loginStatus);
        }
    }

    /**
     * 退出登录
     */
    public void logout() {
        userStatus = new LogoutStatus();
        token = new UserTokenBean();
        userInfo = null;
        updateLiveDataStatus(false);
        //清空本地token
        SharePreferenceUtil.remove(AppInfoManager.getInstance().getContext(), OAUTH_TOKEN);
        SharePreferenceUtil.remove(AppInfoManager.getInstance().getContext(), ACCESS_TOKEN);
    }

    /**
     * 要判断权限跳转的，直接调用这里，不用自己先判断是否登录。这里会关联到LoginActivity，成功后自动跳转到对应的Activity
     *
     * @param context
     * @param intent
     */
    public void startActivity(Activity context, Intent intent) {
        userStatus.startActivity(context, intent);
    }

    /**
     * 要判断权限跳转的，直接调用这里，不用自己先判断是否登录。这里会关联到LoginActivity，成功后自动跳转到对应的Activity
     **/
    public void startActivity(Activity context, Intent intent, int requestCode) {
        userStatus.startActivity(context, intent, requestCode);
    }

    public void startActivity(Fragment fragment, Intent intent, int requestCode) {
        userStatus.startActivity(fragment, intent, requestCode);
    }

    public boolean isLogin() {
        return userStatus.isLogin();
    }

    public void setToken(UserTokenBean token) {
        this.token = token;
        saveToken();
    }

    public UserTokenBean getToken() {
        return token;
    }

    /**
     * token保存到本地
     */
    private void saveToken() {
        SharePreferenceUtil.put(AppInfoManager.getInstance().getContext(), OAUTH_TOKEN, token.getRefresh_token());
        SharePreferenceUtil.put(AppInfoManager.getInstance().getContext(), ACCESS_TOKEN, token.getAccess_token());
    }

    /**
     * 从本地恢复token
     */
    public void reStoreToken() {
        String s = String.valueOf(SharePreferenceUtil.get(AppInfoManager.getInstance().getContext(), OAUTH_TOKEN, ""));
        token.setRefresh_token(s);
        String accessToken = String.valueOf(SharePreferenceUtil.get(AppInfoManager.getInstance().getContext(), ACCESS_TOKEN, ""));
        token.setAccess_token(accessToken);
    }

    /**
     * 判断用户id是不是当前用户id
     *
     * @param userId
     * @return
     */
    public boolean isCurrentUser(String userId) {
        if (userInfo != null && userInfo.getUserId().equals(userId)) {
            return true;
        }
        return false;
    }

    public String getAccessToken() {
        if (token == null) {
            return "";
        }
        return token.getAccess_token();
    }

    public String getPhone() {
        if (userInfo != null && !TextUtils.isEmpty(userInfo.getPhone())) {
            return userInfo.getPhone();
        }
        return "";
    }

    public String getAvatar() {
        return userInfo == null ? "" : userInfo.getAvatar();
    }

    public String getRefreshToken() {
        if (token == null) {
            return "";
        }
        if (token.getRefresh_token() == null) {
            return "";
        }
        return token.getRefresh_token();
    }

    public Boolean isVerified() {
        return (Boolean) SharePreferenceUtil.get(AppInfoManager.getInstance().getContext(), VERIFIED, false);
    }

    public void setVerified() {
        SharePreferenceUtil.put(AppInfoManager.getInstance().getContext(), VERIFIED, true);
    }

    public int getRefreshTime() {
        return token.getExpires_in();
    }

    public String getUserId() {
        return userInfo == null || userInfo.getUserId() == null ? "" : userInfo.getUserId();
    }

    public String getNickname() {
        return userInfo == null ? "" : userInfo.getNickname();
    }

    public String getUFlag() {
        return userInfo == null ? "" : userInfo.getUflag();
    }

    public String getUserSig() {
        return userInfo == null ? "" : userInfo.getUserSig();
    }

    public void setUserInfo(UserInfoBean userInfo) {
        //获取成功之后更新本地用户信息
        Gson gson = new Gson();
        SharePreferenceUtil.put(AppInfoManager.getInstance().getContext(), HttpKeyConstant.SP_USER_INFO, gson.toJson(userInfo));
        this.userInfo = userInfo;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public boolean isTokenLoading() {
        return isTokenLoading;
    }

    public void setTokenLoading(boolean tokenLoading) {
        isTokenLoading = tokenLoading;
    }

    public boolean isFinancialLogin() {
        return isFinancialLogin;
    }

    public void setFinancialLogin(boolean financialLogin) {
        isFinancialLogin = financialLogin;
    }

    public boolean isRestartLogin() {
        return isRestartLogin;
    }

    public void setRestartLogin(boolean restartLogin) {
        isRestartLogin = restartLogin;
    }

    public boolean isIMLogin() {
        return isIMLogin;
    }

    public void setIMLogin(boolean IMLogin) {
        isIMLogin = IMLogin;
    }

    /**
     * 返回可被观测的用户登陆状态
     * true:登陆状态
     * false:退出登陆状态
     * 不可作为用户长期有效的判断，用于观测用户是否手动点击了退出登陆按钮
     *
     * @return
     */
    public LiveData<Boolean> getUnPeekLoginLiveStatus() {
        return LiveDataBus.instance().withUnPeek(USER_TAG_LOGIN, Boolean.class);
    }

    /**
     * 返回可被观测的用户登陆状态
     * true:登陆状态
     * false:退出登陆状态
     *
     * @return
     */
    public LiveData<Boolean> getLoginLiveStatus() {
        return LiveDataBus.instance().with(USER_TAG_LOGIN, Boolean.class);
    }


    //通过lock,实现等refreshToken完成后,其他已返回token过期的请求再执行
    private final static Lock lock = new ReentrantLock();

    /**
     * 刷新 AccessToken,通过重入锁实现,即使并发调用也同时仅一个token在刷新
     * 需要在非主线程调用
     * <p>
     * 如果刷新token失败,则注销
     *
     * @return true 刷新成功, false 刷新失败
     */
    @WorkerThread
    public boolean refreshToken() {
        if (lock.tryLock()) {
            try {
                Log.w(TAG, "refresh token thread holds the lock ");
                CommonSimpleLog.i(TAG, "refresh token thread holds the lock ", "jack");
                UserTokenBean userTokenBean = (UserTokenBean) Observable
                        .fromCallable((Callable<HashMap<String, Object>>) () -> {
                            HashMap<String, Object> params = new HashMap<>();
                            params.put(HttpKeyConstant.GRANT_TYPE, HttpKeyConstant.REFRESH_TOKEN);
                            params.put(HttpKeyConstant.REFRESH_TOKEN, UserInfoManager.getInstance().getRefreshToken());
                            return params;
                        })
                        .flatMap((Function<HashMap<String, Object>, ObservableSource<?>>) params ->
                                HttpServiceGenerator.create(TokenService.class).refreshToken(params)
                        )
                        .timeout(5, TimeUnit.SECONDS)//超时5秒
                        .retry(3)
                        .blockingFirst();
                Log.w(TAG, "refreshAccessToken userTokenBean");
                CommonSimpleLog.i(TAG, "refreshAccessToken userTokenBean", "jack");

                //将网络请求1转换成网络请求2，即发送网络请求2
                //获取token，设置到UserInfoManager
                UserInfoManager.getInstance().setToken(userTokenBean);
                //更改状态
                UserInfoManager.getInstance().login();
                UserInfoManager.getInstance().setTokenLoading(false);
            } catch (Exception e) {
                e.printStackTrace();
                CommonSimpleLog.e(TAG, android.util.Log.getStackTraceString(e), "jack");
                //刷新accessToken失败的情况下,退出登录状态
                UserInfoManager.getInstance().logout();
                RxBus.getInstance().post(EventConstant.EVENT_LOGOUT);
                Log.w(TAG, "logout on try refreshToken failed!");
                CommonSimpleLog.i(TAG, "logout on try refreshToken failed!", "jack");
                return false;//刷新失败
            } finally {
                Log.w(TAG, "refresh token finished. release lock");
                CommonSimpleLog.i(TAG, "refresh token finished. release lock", "jack");
                lock.unlock();//释放锁
            }
        } else {
            Log.w(TAG, "wait for token to be refreshed");
            CommonSimpleLog.i(TAG, "wait for token to be refreshed", "jack");
            lock.lock(); // this will block the thread until the thread that is refreshing
            // the token will call .unlock() method
            lock.unlock();
            Log.w(TAG, "token refreshed. retry");
            CommonSimpleLog.i(TAG, "token refreshed. retry", "jack");
        }
        return true;
    }

}
