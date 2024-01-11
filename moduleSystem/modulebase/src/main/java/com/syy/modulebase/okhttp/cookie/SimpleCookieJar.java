package com.syy.modulebase.okhttp.cookie;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public final class SimpleCookieJar implements CookieJar, dji.thirdparty.okhttp3.CookieJar {
    private final List<Cookie> allCookies = new ArrayList<>();
    private final List<dji.thirdparty.okhttp3.Cookie> djiAllCookies = new ArrayList<>();

    @Override
    public synchronized void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        allCookies.addAll(cookies);
    }

    @Override
    public synchronized List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> result = new ArrayList<>();
        for (Cookie cookie : allCookies) {
            if (cookie.matches(url)) {
                result.add(cookie);
            }
        }
        return result;
    }

    @Override
    public void saveFromResponse(dji.thirdparty.okhttp3.HttpUrl url, List<dji.thirdparty.okhttp3.Cookie> cookies) {
        djiAllCookies.addAll(cookies);
    }

    @Override
    public List<dji.thirdparty.okhttp3.Cookie> loadForRequest(dji.thirdparty.okhttp3.HttpUrl url) {
        List<dji.thirdparty.okhttp3.Cookie> result = new ArrayList<>();
        for (dji.thirdparty.okhttp3.Cookie cookie : djiAllCookies) {
            if (cookie.matches(url)) {
                result.add(cookie);
            }
        }
        return result;
    }
}
