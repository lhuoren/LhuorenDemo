package com.syy.modulebase.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Sai on 2018/3/16.
 * JSON解释封装
 */

public class JsonUtil {

    // 采取单例模式
    private static Gson gson = new Gson();

    private JsonUtil() {
    }

    /**
     * @param src :将要被转化的对象
     * @return :转化后的JSON串
     * @MethodName : toJson
     * @Description : 将对象转为JSON串，此方法能够满足大部分需求
     */
    public static String toJson(Object src) {
        if (null == src) {
            return gson.toJson(JsonNull.INSTANCE);
        }
        try {
            return gson.toJson(src);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param json
     * @param classOfT
     * @return
     * @MethodName : fromJson
     * @Description : 用来将JSON串转为对象，但此方法不可用来转带泛型的集合
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return gson.fromJson(json, (Type) classOfT);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param json
     * @param classOfT
     * @return
     * @MethodName : fromJson
     * @Description : 用来将JSON串转为对象，但此方法不可用来转带泛型的集合
     */
    public static <T> T fromJson(Object json, Class<T> classOfT) {
        return fromJson(toJson(json), classOfT);
    }

    /**
     * @param json
     * @param typeOfT
     * @return
     * @MethodName : fromJson
     * @Description : 用来将JSON串转为对象，此方法可用来转带泛型的集合，如：Type为 new
     * TypeToken<List<T>>(){}.getReportType()
     * ，其它类也可以用此方法调用，就是将List<T>替换为您想要转成的类
     */
    public static Object fromJson(String json, Type typeOfT) {
        try {
            return gson.fromJson(json, typeOfT);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取json中的某个值
     *
     * @param json
     * @param key
     * @return
     */
    public static String getValue(String json, String key) {
        try {
            JSONObject object = new JSONObject(json);
            return object.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getArray(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取json中的list值
     *
     * @param json
     * @return
     */
    public static String getListValue(String json) {
        try {
            JSONObject object = new JSONObject(json);
            return object.getString("list");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Double getDoubleValue(String json, String key) {
        try {
            JSONObject object = new JSONObject(json);
            return object.getDouble(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getIntValue(String json, String key) {
        try {
            JSONObject object = new JSONObject(json);
            return object.getInt(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void format(String str) {
        if (str.contains("http/1.1")) {
            Log.i("url", str);
        } else if (str.contains("code")) {
            JSONObject obj;
            try {
                obj = new JSONObject(str);
                String body = obj.toString(1);

                if (body.length() > 4000) {
                    for (int i = 0; i < body.length(); i += 4000) {
                        if (i + 4000 < body.length()) {
                            Log.i("url" + i, body.substring(i, i + 4000));
                        } else {
                            Log.i("url" + i, body.substring(i, body.length()));
                        }
                    }
                } else {
                    Log.i("url", body);
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Log.i("str", str);
            }
        } else {
            Log.i("header", str);
        }
    }
    /**
     * 对象生成post的body
     *
     * @param target
     * @return
     */
    public static RequestBody generateBody(Object target) {
        return RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),
                new Gson().toJson(target));
    }


}
