package com.example.l.myapplication.presenter;

import android.util.Log;

import com.example.l.myapplication.callback.MyCallBack;
import com.example.l.myapplication.model.Model;
import com.example.l.myapplication.view.IView;

public class Presenter implements IPresenter {
    IView iView;
    Model model;
    public Presenter(IView iView) {
        this.iView = iView;
        model=new Model();
    }

    @Override
    public void startRequest(String murl, final int type) {
        Log.e("www", type+"" );
        model.getData(murl, type, new MyCallBack() {

            @Override
            public void success(Object data) {
                switch (type){
                    case 1:
                        iView.success(data);
                        break;
                    case 2:
                        iView.success(data);
                        break;
                }
            }

            @Override
            public void error(String error) {
                switch (type){
                    case 1:
                        iView.error(error);
                        break;
                    case 2:
                        iView.error(error);
                        break;
                }
            }
        });
    }
}
