package com.xh.study.niconico.module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.xh.study.niconico.R;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by xh on 12/17/16.
 */

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        initView();

    }

    public void initView(){
        ImageView imgLogo = (ImageView) findViewById(R.id.img_logo);
        imgLogo.setImageResource(R.drawable.image_logo);

        AndroidSchedulers.mainThread().createWorker().schedule(()-> {
            startActivity(new Intent(StartActivity.this, MainActivity.class));
            StartActivity.this.finish();
        },1000, TimeUnit.MILLISECONDS);
    }


}
