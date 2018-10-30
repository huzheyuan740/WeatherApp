package com.example.huzheyuan.weatherapp.util;


import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback) {
        //和服务器进行交互的代码，用于发起一条Http请求
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);//回调用于处理服务器响应
    }
}
