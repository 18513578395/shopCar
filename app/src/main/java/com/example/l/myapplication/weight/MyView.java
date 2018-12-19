package com.example.l.myapplication.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.l.myapplication.R;

public class MyView extends RelativeLayout implements View.OnClickListener {

    private  TextView myview_jian;
    private  TextView myview_name;
    private  TextView myview_add;
    int count;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.myview,this);
        myview_jian = findViewById(R.id.myview_jian);
        myview_name = findViewById(R.id.myview_name);
        myview_add = findViewById(R.id.myview_add);
        myview_jian.setOnClickListener(this);
        myview_add.setOnClickListener(this);
    }
    public TextView getMyviewjian(){
        return  myview_jian;
    }
    public TextView getMyviewname(){
        return  myview_name;
    } public TextView getMyviewadd(){
        return  myview_add;
    }

    public void setNumber(int number){
        this.count=number;
        myview_name.setText(count+"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.myview_jian:
                if(count>0){
                    count--;
                    setNumber(count);
                    mSetMyView.setMyView(count);
                }else{
                    Toast.makeText(getContext(),"商品已售空",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.myview_add:
                count++;
                setNumber(count);
                mSetMyView.setMyView(count);
                break;
        }
    }

    //定义接口
    public interface  setMyView{
        void  setMyView(int num);
    }
    private setMyView mSetMyView;
    public void setSetMyView(setMyView setMyView){
        this.mSetMyView=setMyView;
    }
}

