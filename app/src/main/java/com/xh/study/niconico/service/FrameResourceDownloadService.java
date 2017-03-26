package com.xh.study.niconico.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xh on 1/31/17.
 * 用于后台下载浏览漫画图片的服务
 */

public class FrameResourceDownloadService extends IntentService {

    public static final int RESULT_CODE = 0;
    public static final int INVALID_URL_CODE = 1;
    public static final int ERROR_CODE = 2;
    private static final String TAG = FrameResourceDownloadService.class.getSimpleName();

    private final OkHttpClient client = new OkHttpClient();

    public FrameResourceDownloadService(){
        super("Download");
        Log.i("DemoLog", "DownloadIntentService构造函数, Thread: " + Thread.currentThread().getName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("DemoLog", "DownloadIntentService -> onCreate, Thread: " + Thread.currentThread().getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("DemoLog", "DownloadIntentService -> onStartCommand, Thread: " + Thread.currentThread().getName() + " , startId: " + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Request request = new Request.Builder()
                .url("https://lohas.nicoseiga.jp/thumb/6355533p?1482646262")
                .build();

        //异步下载图片
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               Bitmap img = BitmapFactory.decodeByteArray(response.body().bytes(),0, response.body().bytes().length) ;

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("DemoLog", "DownloadIntentService -> onDestroy, Thread: " + Thread.currentThread().getName());
    }
}