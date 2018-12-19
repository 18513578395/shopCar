package com.example.l.myapplication;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkUtils {
    private OkHttpClient okHttpClient;

    private OkUtils(){
        okHttpClient=new OkHttpClient();
    }

    static class OkHolder {
        private static  final OkUtils okutils=new OkUtils();
    }
    public static  OkUtils getInstance(){
        return OkHolder.okutils;
    }
    public void getSyc(String murl, Callback callback){
        Request request=new Request.Builder().url(murl).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
