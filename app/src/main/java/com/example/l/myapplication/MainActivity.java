package com.example.l.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView aaa;
    private ImageView img_qq;
    private Button start;
    int i=3;
    private Handler h=new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            sendEmptyMessageDelayed(0,3000);
            i--;
            if(i==0){
                Intent in=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(in);
                finish();
            }
        }
    };
    private AnimatorSet set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        aaa = (TextView) findViewById(R.id.aaa);
        img_qq = (ImageView) findViewById(R.id.img_qq);
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                ObjectAnimator animator=ObjectAnimator.ofFloat(img_qq,"alpha",0f,0.8f);
                ObjectAnimator anim=ObjectAnimator.ofFloat(img_qq,"rotation",0,180);
                set = new AnimatorSet();
                set.setDuration(3000);
                set.play(animator).before(anim);
                set.start();
                h.sendEmptyMessage(0);

                break;
        }
    }
}
