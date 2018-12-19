package com.example.l.myapplication.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.l.myapplication.OkUtils;
import com.example.l.myapplication.R;
import com.example.l.myapplication.adapter.MyAdapter;

import com.example.l.myapplication.bean.BannerBean;
import com.example.l.myapplication.bean.NewsBean;
import com.example.l.myapplication.presenter.Presenter;
import com.example.l.myapplication.view.IView;
import com.google.gson.Gson;
import com.recker.flybanner.FlyBanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment<T> extends Fragment  implements IView<T> {


    private EditText search_header;
    private LinearLayout line;
    private FlyBanner flybanner;
    private RecyclerView recycle;
    String bannerUrl="http://www.zhaoapi.cn/ad/getAd";
    String newsUrl="http://www.xieast.com/api/news/news.php";
    List<String> banner_list=new ArrayList<>();

    private Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String s= (String) msg.obj;
            Gson gson=new Gson();
            BannerBean bannerBean = gson.fromJson(s, BannerBean.class);
            for (int i = 0; i <bannerBean.getData().size() ; i++) {
                String icon = bannerBean.getData().get(i).getIcon();
                banner_list.add(icon);
            }
            flybanner.setImagesUrl(banner_list);


        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        initView(v);
        bannerPlay();//轮播图
        Presenter presenter=new Presenter(this);
        presenter.startRequest(newsUrl,1);
        return v;
    }



    private void initView(View v) {
        search_header = (EditText) v.findViewById(R.id.search_header);
        line = (LinearLayout) v.findViewById(R.id.line);
        flybanner = (FlyBanner) v.findViewById(R.id.flybanner);
        recycle = (RecyclerView) v.findViewById(R.id.recycle);
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recycle.setLayoutManager(manager);
    }


    @Override
    public void success(T data) {
        NewsBean newsBean= (NewsBean) data;

        List<NewsBean.DataBean> newsBeanData = newsBean.getData();
        Log.e("----",newsBeanData+"----------");
        MyAdapter adapter=new MyAdapter(newsBeanData,getActivity());
        recycle.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void error(String error) {

    }
    private void bannerPlay() {
        OkUtils.getInstance().getSyc(bannerUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                h.sendMessage(h.obtainMessage(0,string));
            }
        });
    }
}
