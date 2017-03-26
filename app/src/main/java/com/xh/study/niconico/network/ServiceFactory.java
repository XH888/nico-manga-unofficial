package com.xh.study.niconico.network;

import com.xh.study.niconico.App;
import com.xh.study.niconico.util.ConstantUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xh on 12/22/16.
 */

public class ServiceFactory {
    private static OkHttpClient mOkHttpClient;

    static {
        initOkHttpClient();
    }

    public static <T> T createService(Class<T> className,String ENDPOINT){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(className);
    }


    private static class UserAgentInterceptor implements Interceptor
    {

        @Override
        public Response intercept(Chain chain) throws IOException
        {

            Request originalRequest = chain.request();
            Request requestWithUserAgent = originalRequest.newBuilder()
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", ConstantUtil.USER_AGENT)
                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    }

    private static void initOkHttpClient()
    {

        if (mOkHttpClient == null)
        {
            synchronized (ServiceFactory.class)
            {
                if (mOkHttpClient == null)
                {
                    //设置Http缓存
                    Cache cache = new Cache(new File(App.getInstance().getCacheDir(), "HttpCache"), 1024 * 1024 * 10);

                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .addInterceptor(new UserAgentInterceptor())
                            .build();
                }
            }
        }
    }
}
