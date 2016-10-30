package com.adgvcxz.adgble.api;

import com.adgvcxz.adgble.BuildConfig;
import com.adgvcxz.adgble.content.Comment;
import com.adgvcxz.adgble.content.Shot;
import com.adgvcxz.adgble.util.RxUtil;

import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/10.
 */

public class RetrofitSingleton {

    private static ApiService sApiService = null;
    private static Retrofit sRetrofit = null;
    private static OkHttpClient sOkHttpClient = null;

    private RetrofitSingleton() {
        init();
    }

    private void init() {
        initOkHttp();
        initRetrofit();
        sApiService = sRetrofit.create(ApiService.class);
    }

    public static RetrofitSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RetrofitSingleton INSTANCE = new RetrofitSingleton();
    }


    private void initOkHttp() {
        OkHttpClient.Builder builder = OkHttpUtil.generateOkHttpClient();
        builder.addInterceptor(chain -> {
            Request request = chain.request();
            HttpUrl originUrl = request.url();
            HttpUrl url = originUrl.newBuilder().addQueryParameter("access_token", ApiService.ClientAccessToken).build();
            return chain.proceed(request.newBuilder().url(url).addHeader("Connection", "close").build());
        });
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        sOkHttpClient = builder.build();
    }

    private void initRetrofit() {
        sRetrofit = new Retrofit.Builder().baseUrl("https://api.dribbble.com/v1/")
                .client(sOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public Observable<List<Shot>> getShots(int page, String sort) {
        return sApiService.getShots(page, 40, sort).compose(RxUtil.rxScheduleHelper());
    }

    public Observable<List<Comment>> getComments(int id, int page) {
        return sApiService.getComments(id, page, 40).compose(RxUtil.rxScheduleHelper());
    }
}
