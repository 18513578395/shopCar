package com.example.l.myapplication.view;

public interface IView<T> {
    void success(T data);
    void error(String error);
}
