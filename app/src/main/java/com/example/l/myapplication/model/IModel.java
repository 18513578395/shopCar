package com.example.l.myapplication.model;

import com.example.l.myapplication.callback.MyCallBack;

public interface IModel {
    void getData(String murl, int type,MyCallBack callBack);
}
