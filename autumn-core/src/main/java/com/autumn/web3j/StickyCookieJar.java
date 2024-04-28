package com.autumn.web3j;


import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.ArrayList;
import java.util.List;

public class StickyCookieJar implements CookieJar {

    private List<Cookie> cookies;


    @Override
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        this.cookies = list;
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        if (cookies != null)
            return cookies;
        return new ArrayList<>();

    }
}
