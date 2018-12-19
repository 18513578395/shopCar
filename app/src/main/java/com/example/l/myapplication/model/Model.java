package com.example.l.myapplication.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.l.myapplication.OkUtils;
import com.example.l.myapplication.bean.CarBean;
import com.example.l.myapplication.bean.NewsBean;
import com.example.l.myapplication.callback.MyCallBack;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Model implements IModel{
    private Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String s= (String) msg.obj;
            Gson gson=new Gson();
           switch (type){
               case 1:
                   NewsBean newsBean = gson.fromJson(s, NewsBean.class);
                   callBack.success(newsBean);
                   break;
               case 2:
                   CarBean carBean = gson.fromJson(s, CarBean.class);
                   callBack.success(carBean);
                   break;
           }
        }
    };
    MyCallBack callBack;
    int type;
    @Override
    public void getData(String murl, final int type, MyCallBack callBack) {
        this.callBack = callBack;
        this.type = type;
        OkUtils.getInstance().getSyc(murl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("222", type +"");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                h.sendMessage(h.obtainMessage(0,string));

            }
        });
    }
}
