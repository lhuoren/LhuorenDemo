package com.syy.modulebase.http.convert;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.syy.modulebase.utils.JsonUtil;

import java.io.IOException;
import java.io.StringReader;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @Description：描述信息
 * @Author：Sai
 * @Date：2019/4/9 15:04
 */
final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String json = value.string();
        try {
            //匹配嵌套records列表为空
            if (!json.contains("\"records\":[{")) {
                if (json.contains("\"data\":{\"total\":0")) {
                    json = json.replaceAll("\"data\":\\{.*\\}$", "\"data\":null}");
                }
            }


//            if (json.contains("{}") || json.contains("[]")) {
//                // TODO: 2023/1/12 上报拦截替换，用于以后分析影响
//                try {
//                    HycanBugReport.report(new RuntimeException("json含有{}或者[],\nparam: " + json));
//                } catch (Exception e1) {
//                }
//            }

            //如果返回的是{} 或 [] 则是空数据，直接给null
            json = json.replaceAll("\\{\\}", "null").replaceAll("\\[\\]", "null");

//            Gson gson = new Gson();
//            HttpResult httpResult = gson.fromJson(json, HttpResult.class);
//            //这里处理一些统一code响应
//            switch (httpResult.getCode()){
//                case HttpCodeConstant
//                        .HTTPCODE_TOKENEXPIRED://token过期，退出登录状态
//                    UserInfoManager.getInstance().logout();
//                    RxBus.getInstance().post(EventConstant.EVENT_LOGOUT);
//                    break;
//            }
            if (json.contains("taskPopupExistAloneUnified")) {

                String s = JsonUtil.getValue(JsonUtil.getValue(json, "data"), "taskPopupExistAloneUnified");
                if (!TextUtils.isEmpty(s) && !s.equals("null")) {
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            /**
                             *要执行的操作
                             */
//                            IntegralDialog.start
//                                    (AppManager.getAppManager().currentActivity(),
//                                            JsonUtil.getValue(s, "title"),
//                                            JsonUtil.getValue(s, "content"),
//                                            JsonUtil.getValue(s, "cancel")
//                                    );
                        }
                    };
                    Timer timer = new Timer();
                    timer.schedule(task, 1000);//1秒后执行TimeTask的run方法

                }
            }
            return adapter.read(gson.newJsonReader(new StringReader(json)));
        } finally {
            value.close();
        }
    }
}
