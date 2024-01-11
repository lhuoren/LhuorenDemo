package com.syy.modulebase.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.syy.modulebase.okhttp.exception.OkHttpException;
import com.syy.modulebase.okhttp.listener.DisposeDataHandle;
import com.syy.modulebase.okhttp.listener.DisposeDataListener;
import com.syy.modulebase.okhttp.listener.DisposeHandleCookieListener;
import com.syy.modulebase.utils.LogUtils;
import com.syy.modulebase.utils.SharedPrefaceUtils;
import com.syy.modulebase.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

/**
 * @author vision
 * @function 专门处理JSON的回调
 */
public class CommonJsonCallback implements Callback {

    /**
     * the logic layer exception, may alter in different app
     */
    protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";
    protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it
    // can has the value of
    // set-cookie2

    /**
     * the java layer exception, do not same to the logic error
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error

    /**
     * 将其它线程的数据转发到UI线程
     */
    private Handler mDeliveryHandler;
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle handle) {
        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(final Call call, final IOException ioexception) {
        /**
         * 此时还在非UI线程，因此要转发
         */
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, ioexception));
            }
        });
    }

    @Override
    public void onResponse(final Call call, final Response response) throws IOException {

        final String result = response.body().string();
        final ArrayList<String> cookieLists = handleCookie(response.headers());
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {

                LogUtils.e("response.code", "code:" + response.code());
//                if (response.code() == 400 || response.code() == 404 || response.code() == 405) {
//                    mListener.onFailure(new OkHttpException(OTHER_ERROR, "invalid_accessToken"));
//                    return;
//                }

                handleResponse(result, response.code());
                /**
                 * handle the cookie
                 */
                if (mListener instanceof DisposeHandleCookieListener) {
                    ((DisposeHandleCookieListener) mListener).onCookie(cookieLists);
                }
            }
        });
    }

    private ArrayList<String> handleCookie(Headers headers) {
        ArrayList<String> tempList = new ArrayList<String>();
        for (int i = 0; i < headers.size(); i++) {
            if (headers.name(i).equalsIgnoreCase(COOKIE_STORE)) {
                tempList.add(headers.value(i));
            }
        }
        return tempList;
    }

    private void handleResponse(Object responseObj, int code) {
        LogUtils.i("responseObj", responseObj.toString());
        if (code != 200 || responseObj.toString().trim().equals("") || "The upstream server is timing out".equals(responseObj)) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }

        try {
            /**
             * 协议确定后看这里如何修改
             */
//            String jsonString = "{ \"error_description\":\"invalid_accessToken\",\"error\":\"invalid_accessToken\"}";
            JsonObject result = JsonParser.parseString(responseObj.toString()).getAsJsonObject();
//            JsonObject result = new JsonParser().parse(responseObj.toString()).getAsJsonObject();
//            JSONObject result = new JSONObject(responseObj.toString());
            LogUtils.i("responseObj_result", result.toString());
//            {"error_description":"invalid_accessToken","error":"invalid_accessToken"}

            if (result.has("error") && result.get("error") != null) {
                mListener.onFailure(new OkHttpException(OTHER_ERROR, result.get("error_description")));
                return;
            }

            Object resultJson;
            if (result.has("data")) {
                resultJson = result.get("data").toString();
            } else {
                resultJson = result.toString();
            }

            LogUtils.i("responseObj_obj", resultJson.toString());
            if (result != null) {
                mListener.onSuccess(resultJson);
            } else {
                mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
            }

        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
            e.printStackTrace();
        }
    }
}