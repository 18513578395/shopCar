package com.example.l.myapplication.callback;

public interface MyCallBack<T> {
    void success(T data);
    void error(String error);
}
