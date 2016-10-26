package com.wanli.com.multitypedemo.api.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wanli on 2016/10/25.
 */

public class ServiceFactory {
    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;


    //测试是否等于null
    public static <T> T createService(Class<T> serviceClass) {
        if (retrofit == null) {
            return buildRetrofit(URL.URL_DOUBAN).create(serviceClass);
        }
        return retrofit.create(serviceClass);
    }


    //重新构造对象
    public static <T> T createService(String baseUri, Class<T> serviceClass) {
        return buildRetrofit(baseUri).create(serviceClass);
    }


    public static Retrofit buildRetrofit(String baseuri) {
        return retrofit = new Retrofit.Builder()
                .baseUrl(baseuri)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            return new OkHttpClient.Builder().build();
        }
        return okHttpClient;
    }
}
