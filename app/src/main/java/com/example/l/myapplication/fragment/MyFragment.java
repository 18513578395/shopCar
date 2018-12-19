package com.example.l.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.l.myapplication.R;
import com.example.l.myapplication.adapter.CarAdapter;
import com.example.l.myapplication.bean.CarBean;
import com.example.l.myapplication.presenter.Presenter;
import com.example.l.myapplication.view.IView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment<T> extends Fragment implements IView<T> {


    private CheckBox qx_cb;
    private TextView qx_price;
    private TextView qx_num;
    private ExpandableListView expand_list;

    String murl="http://www.wanandroid.com/tools/mockapi/6523/restaurant-list";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_my, container, false);
        initView(v);
        Presenter presenter=new Presenter(this);

        presenter.startRequest(murl,2);
        return v;
    }

    @Override
    public void success(T data) {
        CarBean carBean = (CarBean) data;
       List<CarBean.DataBean> list=carBean.getData();
        Toast.makeText(getActivity(), carBean.getMessage(), Toast.LENGTH_SHORT).show();
        CarAdapter adapter = new CarAdapter(list, getActivity());
        expand_list.setAdapter(adapter);
        expand_list.setGroupIndicator(null);
    }

    @Override
    public void error(String error) {

    }

    private void initView(View v) {
        qx_cb = (CheckBox) v.findViewById(R.id.qx_cb);
        qx_price = (TextView) v.findViewById(R.id.qx_price);
        qx_num = (TextView) v.findViewById(R.id.qx_num);
        expand_list = (ExpandableListView) v.findViewById(R.id.expand_list);
    }
}
